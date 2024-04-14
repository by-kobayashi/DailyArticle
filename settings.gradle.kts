pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://maven.aliyun.com/nexus/content/groups/public/")
        maven(url = "https://maven.aliyun.com/repository/public/")
        maven(url = "https://maven.aliyun.com/repository/google/")
        maven(url = "https://maven.aliyun.com/repository/jcenter/")
        maven(url = "https://maven.aliyun.com/repository/central/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://artifact.bytedance.com/repository/byteX/")
        maven(url = "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.aliyun.com/nexus/content/groups/public/")
        maven(url = "https://maven.aliyun.com/repository/public/")
        maven(url = "https://maven.aliyun.com/repository/google/")
        maven(url = "https://maven.aliyun.com/repository/jcenter/")
        maven(url = "https://maven.aliyun.com/repository/central/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://artifact.bytedance.com/repository/byteX/")
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "DailyArticle"
include(":app")
 