# AGENTS.md

本文件给在本仓库干活的 AI Agent 看的。先读完再动手。

## 项目是什么

**Laboratory** —— 一个 Minecraft 服务端插件，Slimefun4（粘液科技）的附属插件（addon）。
作者 Freeze_Dolphin，上游仓库 `Freeze_Dolphin/Laboratory`。

- 运行环境：Paper 服务端（`paper-api` 1.18.2-R0.1-SNAPSHOT，`provided`），`api-version: 1.20`
- 强依赖 Slimefun4（`plugin.yml` 里 `depend: [Slimefun]`），另依赖 GuizhanLibPlugin
- 内容：一整套「实验室科技线」物品与机器——电磁斧、电弧剑、电动钻头、各类电容/电池、分子重组仪、RTG 发电机、龙息生成器等

## ⚠️ 代码来源（必读）

本仓库代码**全部反编译自 1.12 版本的旧附属**，原版没有开源仓库。后果：

1. 代码风格不统一，存在大量反编译痕迹（奇怪的变量名 `lo`/`b`/`i`、扁平 `for` 循环等）。**不要"顺手重构"**，改什么动什么。
2. README 明说：版本跨度太大（1.12 → 1.20），**部分功能可能失效或不稳定**。改 bug 前先确认是不是这个历史遗留问题。
3. `original-magnetizer.bak` 是备份文件，别动、别删。

## 构建

```bash
# 完整构建（pom 的 defaultGoal 就是 clean package）
mvn clean package

# 代码格式化（spotless + Palantir 风格，构建时自动执行）
mvn spotless:apply
mvn spotless:check
```

- JDK 17（`java.version=17`），Maven 工程，产物 jar 在 `target/`
- 四个依赖全是 `provided` scope：GuizhanLibPlugin 1.7.6、paper-api 1.18.2、Slimefun4 2024.3、Lombok 1.18.34
- 需要联网拉依赖（jitpack / codemc / papermc / sonatype 仓库）
- `src/main/resources` 开启 filtering，`plugin.yml` 里用 `${project.version}` 占位

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
- **不要在批量改动时对整个文件跑 spotless:apply**，只对自己写的代码块负责

## 注意事项（坑）

1. **`pom.xml` 里 spotless-maven-plugin 声明了两次**（`<plugins>` 下两个完全相同的 block）。已知冗余，暂时无副作用，清理前先确认
2. 物品 ID、research ID、config key 是**面向存档的契约**——改名会让玩家存档里的物品变石头/失效，改名=数据迁移，想清楚再动
3. 电容类物品用电量是 `float`，机器/电网用 `int`，互转注意精度
4. `Laboratory.capacities` 等静态 Map 的 key 是 `ItemStack`，**没有重写 equals/hashCode**，靠 `SlimefunUtils.isItemSimilar` 逐条比对——别改成依赖 Map 查找的写法
5. 提交权归主人：Agent **只改工作区，不 commit、不 push、不动 git history**
6. 验证手段：`mvn clean package` 编译通过 + `mvn spotless:check`。没有单元测试；游戏内表现需服主自行测试

## 提交信息（建议）

上游历史用 `Resolves #<issue号>` / `fix` / `bump version` 风格。建议提交前把工作区整理干净，提交信息由主人定。
