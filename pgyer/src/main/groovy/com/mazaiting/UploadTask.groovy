package com.mazaiting

import io.reactivex.functions.Consumer
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskAction

/**
 * 上传任务
 */
class UploadTask extends DefaultTask {
    /**
     * 扩展名
     */
    private static final String EXTENSION_NAME = "pgyer"

    /**
     * 开始任务
     */
    @TaskAction
    void start() {
        logger.println('UploadTask start')
        PgyerExtension extension = project.extensions.findByName(EXTENSION_NAME)
        logger.println(extension.toString())
        logger.println('check param start')
        // 参数检测结果
        String result = extension.checkParam()
        // 如果字符串长度为空, 则说明没有异常数据
        if (result.length() == 0) {
            logger.println('UploadTask start upload')
            RxUtil.upload(extension)
                    .subscribe(new Consumer<ResponseBean>() {
                        @Override
                        void accept(ResponseBean responseBean) throws Exception {
                            if (responseBean.isSuccess()) {
                                logger.println('UploadTask upload success')
                                println("应用名称: " + responseBean.buildName())
                                println("应用版本: " + responseBean.buildVersion())
                                println("应用构件版本: " + responseBean.buildBuildVersion())
                                println("应用下载地址: " + responseBean.buildUrl())
                            } else {
                                logger.println('UploadTask upload failed -- ' + responseBean.getFailedMessage())
                                println()
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        void accept(Throwable throwable) throws Exception {
                            def msg = RxUtil.getErrorMessage(throwable)
                            println('UploadTask upload error -- ' + msg)
                        }
                    })
        } else {
            logger.println('check param error--' + result)
        }

    }


    def copyApkFile(Set<Task> tasks, Project project) {
        for (task in tasks) {
            def dir = project.getProjectDir().getAbsolutePath()
            println "assemble release dir = $dir"
            def file = new File(dir + "/build/outputs/apk")

            //在生成此apk之前，先将之前生成的apk删除
            if (file.exists()) {
                def var = delete(dir + "/build/outputs/apk")
                def var1 = delete(rootProject.getRootDir().getAbsolutePath() + "/outputs/" + project.getName())
                println "clear before create $var , $var1------------------"
            }

            task.doLast {
                if (file.exists()) {
                    //将生成的apk拷贝出来
                    copy { //copy是gradle中的project提供的方法，用于拷贝，里面有两个特定的属性
                        //from srcDir ， into desDir （当这个目录不存在的时候会尝试创建这个文件夹）
                        println "copy file -------------------"
                        from dir + "/build/outputs/apk"
                        into rootProject.getRootDir().getAbsolutePath() + "/apk/" + project.getName()
                    }
                }
            }
        }
    }
}