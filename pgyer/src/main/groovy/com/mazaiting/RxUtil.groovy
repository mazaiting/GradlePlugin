package com.mazaiting

import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

/**
 * 网络工具类
 * @author mazaiting* @date 2018/2/6
 */

class RxUtil {

    private static String API_KEY = "c65f609fd16e7d4834a1f7e46e105ca2"

    private static String URL = "http://www.pgyer.com/apiv2/"
    /**
     * 获取拦截器
     * @return
     */
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            void log(String message) {
                if (null != message && "" != message) {
                    println(message)
                }
            }
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * 获取OkHttpClient.Builder
     * @return OkHttpClient.Builder
     */
    private static OkHttpClient.Builder getOkHttpClientBuilder() {

        return new OkHttpClient.Builder()
        // 设置连接超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
        // 设置读取超时时间
                .readTimeout(15, TimeUnit.SECONDS)
                .callTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
        // 设置网络请求拦截器
//                .addInterceptor(httpLoggingInterceptor)
    }

    /**
     * 获取 Retrofit
     */
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL) //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClientBuilder().build())
                .build()
    }


    /**
     * 获取 Api
     */
    private static Api getApi() {
        return getRetrofit().create(Api.class)
    }

    /**
     * 上传APK
     * @return
     */
    static Observable<ResponseBean> upload() {
        // app key
        def apiKey = MultipartBody.Part.createFormData("_api_key", API_KEY)
        // file
        def file = new File("apk/15_11_30_卡托交接_uat.apk")
        println("文件路径: ${file.absolutePath}")
        def currentProgress = 0L
        def requestFile = FileRequestBody.create(MediaType.parse("multipart/form-data"), file)
        def fileRequestFile = new FileRequestBody(requestFile, new FileRequestBody.LoadingListener() {

            @Override
            void onProgress(long currentLength, long contentLength) {
                // 计算进度
                def progress = Math.round(currentLength * 1.0 / contentLength * 100)
                // 如果进度不相等则打印
                if (currentProgress != progress) {
                    println("当前上传进度: " + progress + "%")
                    currentProgress = progress
                }
            }
        })
        def body = MultipartBody.Part.createFormData("file", file.getName(), fileRequestFile)
        // 安装类型
        def buildInstallType = MultipartBody.Part.createFormData("buildInstallType", "2")
        // 密码
        def buildPassword = MultipartBody.Part.createFormData("buildPassword", "123456")
        // 更新描述
        def buildUpdateDescription = MultipartBody.Part.createFormData("buildUpdateDescription", "")
        // 构建名称
        def buildName = MultipartBody.Part.createFormData("buildName", "卡托交接")
        // 安装有效期
        def buildInstallDate = MultipartBody.Part.createFormData("buildInstallDate", "2")
        // 开始日期
        def buildInstallStartDate = MultipartBody.Part.createFormData("buildInstallStartDate", "")
        // 结束日期
        def buildInstallEndDate = MultipartBody.Part.createFormData("buildInstallEndDate", "")
        // 渠道
        def buildChannelShortcut = MultipartBody.Part.createFormData("buildChannelShortcut", "")
        return api.upload(apiKey, body, buildInstallType, buildPassword, buildUpdateDescription,
                buildName, buildInstallDate, buildInstallStartDate, buildInstallEndDate, buildChannelShortcut)
    }
}
