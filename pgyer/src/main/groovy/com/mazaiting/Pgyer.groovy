package com.mazaiting

import io.reactivex.functions.Consumer
import org.gradle.api.Plugin
import org.gradle.api.Project

class Pgyer implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println(project)
        // 创建任务
        project.task("hello") {
            RxUtil.upload()
                    .subscribe(new Consumer<ResponseBean>() {
                        @Override
                        void accept(ResponseBean responseBean) throws Exception {
                            println(responseBean.toString())
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        void accept(Throwable throwable) throws Exception {
                            println(throwable.getMessage())
                        }
                    })
            doLast {
                println "finish"
            }
        }
    }
}