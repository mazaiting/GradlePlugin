package com.mazaiting

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import java.util.function.Consumer

class Pgyer implements Plugin<Project> {
    /**
     * 扩展名
     */
    private static final String EXTENSION_NAME = "pgyer"

    @Override
    void apply(Project project) {
        // 注册一个扩展容器，扩展容器可以使我们在一个闭包中为task赋值
        project.extensions.create(EXTENSION_NAME, PgyerExtension)
        addTask(project)
    }

    /**
     * 添加任务
     * @param project 工程
     */
    static void addTask(Project project) {
        project.task('uploadPgyer', type: UploadTask, group: 'pgyer', description: '蒲公英上传应用生产包')
//                {
//            // 获取项目绝对路径
//            def dir = project.getProjectDir().getAbsolutePath()
//            println "项目路径路径 = $dir"
//            def file = new File(dir + "/build/outputs/apk")
//            // 将生成的apk拷贝出来
//            doFirst {
//                println "assembleDebug doLast"
//                if (file.exists()) {
//                    // copy是gradle中的project提供的方法，用于拷贝，里面有两个特定的属性
//                    // from srcDir ， into desDir （当这个目录不存在的时候会尝试创建这个文件夹）
//                    println "copy file -------------------"
//                    project.copy() {
//                        from dir + "/build/outputs/apk"
//                        into project.rootProject.getRootDir().getAbsolutePath() + "/apk/" + project.getName()
//                    }
//                }
//            }
//        }
    }

    /**
     * 初始化依赖
     * @param project 工程
     */
    void initDepend(Project project) {
        project.getTasks().forEach(new Consumer<Task>() {
            @Override
            void accept(Task task) {
                println task.name
            }
        })
        project.getTasksByName('assembleDebug', false).forEach(new Consumer<Task>() {
            @Override
            void accept(Task task) {
                println task.name
            }
        })
//        // 依赖打包
//        def assembleDebug = project.getTasksByName('assembleDebug', false)[0]
//        println assembleDebug
//        // 获取项目绝对路径
//        def dir = project.getProjectDir().getAbsolutePath()
//        println "项目路径路径 = $dir"
//        def file = new File(dir + "/build/outputs/apk")
//        // 在生成此apk之前，先将之前生成的apk删除
//        assembleDebug.doFirst {
//            println "assembleDebug doFirst"
//            if (file.exists()) {
//                def var = project.delete(dir + "/build/outputs/apk")
//                def var1 = project.delete(rootProject.getRootDir().getAbsolutePath() + "/outputs/" + project.getName())
//                println "clear before create $var , $var1------------------"
//            }
//        }
//
//        // 将生成的apk拷贝出来
//        assembleDebug.doLast {
//            println "assembleDebug doLast"
//            if (file.exists()) {
//                copy { //copy是gradle中的project提供的方法，用于拷贝，里面有两个特定的属性
//                    //from srcDir ， into desDir （当这个目录不存在的时候会尝试创建这个文件夹）
//                    println "copy file -------------------"
//                    from dir + "/build/outputs/apk"
//                    into rootProject.getRootDir().getAbsolutePath() + "/apk/" + project.getName()
//                }
//            }
//        }
//
//        project.tasks.create('uploadPgyerAssemble', type: Exec, group: 'pgyer') {
//            commandLine "./gradlew", "assembleDebug"
//        }
    }
}