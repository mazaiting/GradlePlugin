package com.mazaiting.tasks

import com.mazaiting.PgyerExtension
import com.mazaiting.ResponseBean
import com.mazaiting.uploadLog
import com.mazaiting.utils.RxUtil
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.FileOutputStream

/***
 *
 *
 *                                                    __----~~~~~~~~~~~------___
 *                                   .  .   ~~//====......          __--~ ~~
 *                   -.            \_|//     |||\\  ~~~~~~::::... /~
 *                ___-==_       _-~o~  \/    |||  \\            _/~~-
 *        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
 *    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
 *  .~       .~       |   \\ -_    /  /-   /   ||      \   /
 * /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
 * |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
 *          '         ~-|      /|    |-~\~~       __--~~
 *                      |-~~-_/ |    |   ~\_   _-~            /\
 *                           /  \     \__   \/~                \__
 *                       _--~ _/ | .-~~____--~-/                  ~~==.
 *                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
 *                                 -_     ~\      ~~---l__i__i__i--~~_/
 *                                 _-~-__   ~)  \--______________--~~
 *                               //.-~~~-~_--~- |-------~~~~~~~~
 *                                      //.-~~~--\
 *                               神兽保佑
 *                              代码无BUG!
 * @author mazaiting
 * @date 2020/7/8
 * @description 上传任务
 */
open class UploadTask : DefaultTask() {

    companion object {
        /**
         * 任务名
         */
        const val UPLOAD_NAME = "upload"

        /**
         * 写入 JSON 文件名
         */
        private const val JSON_PGYER_FILE_NAME = "pgyer.json"
    }

    /**
     * 扩展参数
     */
    @Input
    var extension: PgyerExtension? = null

    /**
     * 构建类型
     */
    @Input
    var typeName: String = "release"

    @TaskAction
    fun upload() {
        extension?.let {
            // 如果文件为空则设置默认文件路径
            val path = setDefaultFile(it)
            // 检测参数
            val checkParam = it.checkParam()
            // 如果不为空则说明参数校验未通过
            if (checkParam.isNotEmpty()) {
                uploadLog(checkParam)
            } else {
                // 检测文件是否合法
                val checkFile = it.checkFile(path)
                // 检测异常信息是否为空
                if (checkFile.isEmpty()) {
                    uploadLog("UploadTask start upload")
                    RxUtil.upload(it, path)
                        .subscribe({ responseBean ->
                            // 成功
                            if (responseBean.isSuccess) {
                                uploadLog("UploadTask upload success")
                                uploadLog("应用名称: " + responseBean.buildName())
                                uploadLog("应用安装密码: " + it.password)
                                uploadLog("应用版本: " + responseBean.buildVersion())
                                uploadLog("应用构件版本: " + responseBean.buildBuildVersion())
                                uploadLog("应用下载地址: " + responseBean.buildUrl())
                                writePgyerJson(responseBean)
                            } else {
                                uploadLog("UploadTask upload failed -- " + responseBean.failedMessage)
                            }
                        }, { throwable ->
                            val msg: String = RxUtil.getErrorMessage(throwable!!)
                            uploadLog("UploadTask upload error -- $msg")
                        })
                } else {
                    uploadLog(checkFile)
                }
            }
        }
    }

    /**
     * 写入 assets 文件夹中的 pgyer.json 文件
     */
    private fun writePgyerJson(responseBean: ResponseBean) {
        // 定义文件
        val dir = File("${project.name}/src/main/assets")
        // 判断文件是否为路径,并且存在
        if (!dir.exists()) {
            // 创建新文件
            dir.mkdir()
        }
        // 创建文件
        val file = File(dir, JSON_PGYER_FILE_NAME)

        // 设置下一版本构建号
        val buildBuildVersion: Int =
            responseBean.buildBuildVersion()?.toInt()?.plus(1) ?: 0
        // 设置内容
        val text = "{\"build_version\": $buildBuildVersion}"
        try {
            val fos = FileOutputStream(file)
            fos.write(text.toByteArray())
            fos.close()
        } catch (e: Exception) {
            uploadLog("异常: " + e.message)
            uploadLog("注: 如此处报异常, 请手动修改${file.absolutePath}文件中build_version字段.")
        }
    }

    /**
     * 如果文件为空则设置默认文件
     * @param extension 扩展
     */
    private fun setDefaultFile(extension: PgyerExtension)
            = if (extension.file.isEmpty()) {
                // 文件默认路径
                "${project.name}/build/outputs/apk/$typeName/app-$typeName.apk"
            } else {
                extension.file
            }
}