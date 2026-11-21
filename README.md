# Laboratory

This is a slimefun addon.

此附属原为 1.12 版本的 Slimefun 附属。
原附属并未找到源代码仓库，故此仓库的代码均为反编译而来。
已支持至 1.20 版本。
鉴于版本跨度过大，可能会有一些功能失效或不稳定。

## 前置

- Paper 1.20+（`plugin.yml` 声明 `api-version: 1.20`）
- **Slimefun4**（强依赖，`depend: [Slimefun]`）
- **GuizhanLibPlugin**（编译期依赖，运行期由服务端提供）

## 安装

把 `build/libs/Laboratory-<version>.jar` 丢进 `plugins/`，重启服务端。

## 命令

| 命令 | 说明 | 权限 |
| --- | --- | --- |
| `/charge`（别名 `/cg`） | 给手上的物品充能 | `laboratory.commands.charge`（默认 OP） |
| `/techitems` | 取调试物品 | `laboratory.commands.techitems`（默认 OP） |

## 构建

需要 JDK 21+（构建产物为 Java 17 字节码，用 `options.release = 17` 生成）。

```bash
./gradlew clean build     # 产物在 build/libs/
./gradlew spotlessApply   # 格式化（手动触发，不在构建链路里）
```

版本号唯一来源是 `gradle.properties` 的 `version=`，改版本只改这一处。

## 兼容性

CI 会在 **1.20.1** 和 **26.2** 两个 Paper 版本上真实开服，检查插件是否成功 `Enabling`
（矩阵见 `.github/workflows/test-compatibility.yml`，可手动指定版本触发）。

## 自动化

| 流程 | 触发 | 作用 |
| --- | --- | --- |
| `build.yml` | push / PR 到 `main` | 编译 + 上传 jar 产物 |
| `test-compatibility.yml` | push / PR / 手动 | 多版本 Paper 开服加载测试 |
| `release.yml` | `gradle.properties` 版本号变更 | 自动打 tag 并发 Release |
| `dependabot.yml` | 每月 | Gradle 依赖更新检查 |

## 反馈

请走仓库的 [Issues](../../issues)。

## 许可

见 [LICENSE](LICENSE)。
