package com.mazaiting

import com.android.build.gradle.AppExtension
import com.android.builder.model.BuildType
import com.mazaiting.actions.UploadAction
import com.mazaiting.tasks.UploadTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

/**
 * 蒲公英插件
 */
open class PgyerPlugin : Plugin<Project> {
    companion object {
        /**
         * 扩展名
         */
        private const val EXTENSION_NAME = "pgyer"

        /**
         * Android 扩展名
         */
        private const val EXTENSION_ANDROID = "android"

        /**
         * 任务组
         */
        private const val EXTENSION_GROUP = "pgyer"

        /**
         * APP 插件
         */
        private const val PLUGIN_APP = "com.android.application"
    }

    override fun apply(project: Project) {
        // 注册一个扩展容器，扩展容器可以使我们在一个闭包中为task赋值
        project.extensions.create(EXTENSION_NAME, PgyerExtension::class.java)
        // 工程最后执行
        project.afterEvaluate {
            // 根据变体创建任务
            createProjectByVariants(it)
        }
    }

    /**
     * 根据构建类型创建任务
     * @param project 工程
     */
    private fun createProjectByVariants(
        project: Project
    ) {
        // 判断是否存在 APP 插件
        if (project.plugins.hasPlugin(PLUGIN_APP)) {
            // 获取 Android 扩展名
            val app = project.extensions.getByName(EXTENSION_ANDROID) as AppExtension
            // 遍历
            app.applicationVariants.forEach {
//                if (it.buildType.name != "release") {
                    // 创建上传任务
                    val uploadTask = createUploadTask(project, it.buildType)
                    // 依赖任务
                    dependOnTask(project, uploadTask, it.buildType)
//                }
            }
        } else {
            uploadLog("当前 gradle 不存在 plugin($EXTENSION_ANDROID), 请正确配置!")
        }
    }

    /**
     * 依赖任务
     * @param project 工程
     * @param uploadTask 上传任务
     * @param buildType 构建类型
     */
    private fun dependOnTask(
        project: Project,
        uploadTask: UploadTask,
        buildType: BuildType
    ) {
        // 查找任务
        val assemble = project.tasks.findByName(
            String.format(
                "assemble%s%s",
                buildType.name.substring(0, 1).toUpperCase(Locale.CHINA),
                buildType.name.substring(1)
            )
        )
        assemble?.let {
            // 依赖于打包任务
            uploadTask.dependsOn(assemble)
        }
    }

    /**
     * 创建上传任务
     * @param project 工程
     * @param buildType 构建类型
     */
    private fun createUploadTask(
        project: Project,
        buildType: BuildType
    ): UploadTask {
        // 获取扩展
        val extension: PgyerExtension? =
            project.extensions.findByType(PgyerExtension::class.java)

        // 创建上传任务
        val uploadTask = project.tasks.create(
            String.format(
                "${UploadTask.UPLOAD_NAME}%s%s",
                buildType.name.substring(0, 1).toUpperCase(Locale.CHINA),
                buildType.name.substring(1)
            ),
            UploadTask::class.java,
            UploadAction(extension, buildType.name)
        )
        // 设置组
        uploadTask.group = EXTENSION_GROUP
        return uploadTask
    }
}