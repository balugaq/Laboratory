# AGENTS.md

本文件给在本仓库干活的 AI Agent 看的。先读完再动手。

## 项目是什么

**Laboratory** —— 一个 Minecraft 服务端插件，Slimefun4（粘液科技）的附属插件（addon）。
作者 Freeze_Dolphin，上游仓库 `Freeze_Dolphin/Laboratory`。

- 运行环境：Paper 服务端（`paper-api` 1.18.2-R0.1-SNAPSHOT，`compileOnly`），`api-version: 1.20`
- 强依赖 Slimefun4（`plugin.yml` 里 `depend: [Slimefun]`），另依赖 GuizhanLibPlugin
- 内容：一整套「实验室科技线」物品与机器——电磁斧、电弧剑、电动钻头、各类电容/电池、分子重组仪、RTG 发电机、龙息生成器等

## ⚠️ 代码来源（必读）

本仓库代码**全部反编译自 1.12 版本的旧附属**，原版没有开源仓库。后果：

1. 代码风格不统一，存在大量反编译痕迹（奇怪的变量名 `lo`/`b`/`i`、扁平 `for` 循环等）。**不要"顺手重构"**，改什么动什么。
2. README 明说：版本跨度太大（1.12 → 1.20），**部分功能可能失效或不稳定**。改 bug 前先确认是不是这个历史遗留问题。
3. `original-magnetizer.bak` 是备份文件，别动、别删。

## 构建

```bash
# 完整构建（产物：build/libs/Laboratory-<version>.jar）
./gradlew clean build

# 代码格式化（spotless + Palantir 风格，手动触发，不绑构建）
./gradlew spotlessApply
./gradlew spotlessCheck
```

- Java 17 字节码（`options.release = 17`），Gradle 9.8 + Kotlin DSL，产物 jar 在 `build/libs/`
- 版本号唯一来源是 `gradle.properties` 的 `version=`，`release.yml` 读它打 tag；改版本只改这一处
- 依赖全部 `compileOnly`（不打进 jar，运行期由服务端提供）：GuizhanLibPlugin 2.5.0、paper-api 1.18.2、Slimefun4 2025.1、Lombok 1.18.48，统一在 `gradle/libs.versions.toml` 管理
- 仓库只有 papermc / jitpack / central 三个源（GuizhanLib 在 central，不在 codemc）
- `plugin.yml` 里用 `${version}` 占位，由 `processResources` 的 `expand` 注入
- **spotlessCheck 刻意没挂到 check/build 上**：存量代码来自反编译、从未格式化，挂上去会让 CI 直接红。要格式化就手动跑，且只对自己写的文件跑

## 代码结构

入口：`me.Freeze_Dolphin.lab.Laboratory`（`JavaPlugin implements SlimefunAddon`）

```
src/main/java/me/Freeze_Dolphin/lab/
├── Laboratory.java          主类：onEnable 注册指令/监听器/物品，两个定时任务（电容充放电、蓝宝石隐身）
├── Variables.java           全局静态状态（可充电电池列表、放射性物品、config 引用等）
├── U.java                   工具类：随机、合成表快捷数组、消息前缀、研究注册
├── Resource.java            物品注册（矿/材料类），onEnable 中第一个加载
├── Tech.java                物品注册（科技元件类），最大的一块
├── Food.java / Lab.java / Nuclear.java / ArmorWeapon.java
│                            其余物品注册（食物/实验室装备/核能/护甲武器）
├── ChargeableItem.java      可充电物品基类（带 getMaxItemCharge）
├── ChargeableBlock.java / ChargeableBow.java
├── ItemEnergy.java          电量存取工具（PDC 持久化）
├── AdvancedAContainer.java  AContainer 子类基类（高级机器）
├── BlockMenuUtil.java / StackUtils.java / SkullUtil.java / MachineHelper.java
├── CustomArmor.java / CustomPotion.java / RadioactiveAlloyIngot.java / ADrill.java
├── StartupTasks.java        放射性debuff定时任务
├── listeners/               Bukkit 事件监听（约 20 个，全部在主类手动 registerEvents）
├── machines/                机器实现（Magnetizer、BlastFurnace、LaserDrill…）
├── commands/                Charge（/charge 充能）、TechItems（/techitems 调试物品）
├── bugrepair/               可选的 bug 修复监听器，由 config 的 bugfixers.* 开关控制
├── events/entity/           自定义事件
├── api/                     Str2Item 等对外 API
└── geo/                     矿物生成相关（CertusQuartz、Iridium）
```

## 核心约定

### 物品注册

- **所有物品 ID 以 `LAB_` 开头**，如 `LAB_ELECTRIC_AXE_1`、`LAB_SAPPHIRE_CAPACITY`
- 注册类（Resource/Tech/Food/Lab/Nuclear/ArmorWeapon）在 `onEnable` 里**按上述顺序依次 new**，后注册的会引用先注册的物品（如 `Lab` 用 `Tech.IRIDIUM`）。**新增注册类要注意顺序依赖，别打乱**
- SlimefunItemStack 的 lore 全部用 `&` 色号，玩家消息统一走 `U.scolor` / config 的 `messages.*`
- 研究（Research）ID 从 `config.yml` 的 `research-identify-start`（450000）起算，**不要乱改**
- 兼容性检测：`onEnable` 用 `Class.forName` 探测 Nar 重置版 Slimefun，结果存 `Variables.Nar`

### 配置与资源

- `config.yml`：中文注释，改默认值/加消息都在这。消息用 `§` 色号
- `mt.yml`：分子重组仪合成表，`saveResource` 单独落盘，**不能被配置文件重载器重载**
- 新配置项必须在 `config.yml` 里给默认值，否则 NPE

### 代码风格

- Java 17 语法在用（`instanceof` 模式匹配、`var` 局部推断），新代码保持一致
- Lombok 可用，但存量代码基本没用，新代码别混用太多
- 反编译遗留代码能不动就不动；确需修改时保持周围风格，不要全文件格式化（会和 spotless 拉齐产生巨大 diff）
- **不要在批量改动时对整个文件跑 spotlessApply**，只对自己写的代码块负责

## 注意事项（坑）

1. **构建系统已从 Maven 迁到 Gradle**（2026-11-22）。`pom.xml` 已删除，任何 `mvn` 命令都会失败，统一用 `./gradlew`
2. 物品 ID、research ID、config key 是**面向存档的契约**——改名会让玩家存档里的物品变石头/失效，改名=数据迁移，想清楚再动
3. 电容类物品用电量是 `float`，机器/电网用 `int`，互转注意精度
4. `Laboratory.capacities` 等静态 Map 的 key 是 `ItemStack`，**没有重写 equals/hashCode**，靠 `SlimefunUtils.isItemSimilar` 逐条比对——别改成依赖 Map 查找的写法
5. 提交权归主人：Agent **只改工作区，不 commit、不 push、不动 git history**
6. 验证手段：`./gradlew clean build` 编译通过（CI 另有 1.20.1 / 26.2 开服加载测试）。没有单元测试；游戏内表现需服主自行测试

## 提交信息（建议）

上游历史用 `Resolves #<issue号>` / `fix` / `bump version` 风格。建议提交前把工作区整理干净，提交信息由主人定。
