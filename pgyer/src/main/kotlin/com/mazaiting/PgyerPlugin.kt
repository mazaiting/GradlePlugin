package com.mazaiting

import com.mazaiting.tasks.UploadTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 蒲公英插件
 */
class PgyerPlugin : Plugin<Project> {
    companion object {
        /**
         * 扩展名
         */
        private const val EXTENSION_NAME = "pgyer"

        /**
         * 任务组
         */
        private const val EXTENSION_GROUP = "pgyer"
    }

    override fun apply(project: Project) {
        // 注册一个扩展容器，扩展容器可以使我们在一个闭包中为task赋值
        project.extensions.create(EXTENSION_NAME, PgyerExtension::class.java)
        // 工程最后执行
        project.afterEvaluate {
            // 获取扩展
            val extension: PgyerExtension? = project.extensions.findByType(PgyerExtension::class.java)
            // 创建上传任务
            val uploadTask = createUploadTask(it)
            // 依赖任务
            dependOnTask(project, uploadTask)
        }
    }

    /**
     * 依赖任务
     * @param project 工程
     * @param uploadTask 上传任务
     */
    private fun dependOnTask(project: Project, uploadTask: UploadTask) {
        // 查找任务
        val assembleRelease = project.tasks.findByName("assembleRelease")
        // 依赖于打包任务
        uploadTask.dependsOn(assembleRelease)
    }

    /**
     * 创建上传任务
     * @param project 工程
     */
    private fun createUploadTask(project: Project): UploadTask {
        // 创建上传任务
        val uploadTask = project.tasks.create(UploadTask.UPLOAD_NAME, UploadTask::class.java)
        // 设置组
        uploadTask.group = EXTENSION_GROUP
        return uploadTask
    }
}