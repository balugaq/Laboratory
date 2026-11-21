plugins {
    java
    alias(libs.plugins.spotless)
}

group = "me.Freeze_Dolphin"
version = providers.gradleProperty("version").get()

java {
    // 不用 toolchain：本机的 JBR-21 缺 jmods，Gradle 判定为不完整 JDK，
    // 而 CI 与本机 JVM 版本不一致。改用当前 JVM 编译 + release=17，
    // 保证无论 JDK 21 还是 26 都产出 Java 17 字节码。
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    // 全部 provided：服务端运行期由 Paper / Slimefun / GuizhanLib 提供，不打进 jar
    compileOnly(libs.paper.api)
    compileOnly(libs.slimefun4)
    compileOnly(libs.guizhan.lib.plugin)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks {
    compileJava {
        // 源码里的中文注释与字符串靠显式 UTF-8 才不会在 Windows 上变 GBK
        options.encoding = "UTF-8"
        options.release = 17
    }

    processResources {
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            // Maven 的 ${project.version} 换成 Gradle 的 ${version}，值由这里注入
            expand("version" to project.version.toString())
        }
    }

    jar {
        archiveBaseName.set("Laboratory")
        archiveVersion.set(project.version.toString())
    }
}

spotless {
    // 不把 spotlessCheck 挂到 check/build 上：存量代码来自反编译、从未格式化，
    // 一旦进构建链路就会因全仓库格式不合规而红，且 apply 后会产生整文件级 diff。
    // 需要时手动执行 ./gradlew spotlessApply。
    isEnforceCheck = false

    java {
        // 与 pom 里的 Maven Spotless 配置保持一致，避免格式基准漂移
        palantirJavaFormat("2.38.0")
        removeUnusedImports()
        formatAnnotations()
    }
}
