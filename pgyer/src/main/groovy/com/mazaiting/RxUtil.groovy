package com.mazaiting

import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
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
    /**
     * 应用密钥
     */
    private static String API_KEY = ""
//    "apk/15_11_30_卡托交接_uat.apk"
    /**
     * 网络地址
     */
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
                .connectTimeout(30, TimeUnit.SECONDS)
        // 设置读取超时时间
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
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
     * 获取异常信息
     */
    public static getErrorMessage(Throwable throwable) {
        def msg = throwable.getMessage()
        if (throwable.getMessage() == "timeout") {
            msg = "上传文件超时"
        }
        return msg
    }

    /**
     * 上传APK
     * @param extension 扩展字段
     * @return
     */
    public static Observable<ResponseBean> upload(PgyerExtension extension) {
        // app key
        def apiKey = MultipartBody.Part.createFormData("_api_key", extension.apiKey)
        // file
        def file = new File(extension.file)
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
                if (currentLength == contentLength) {
                    println("上传完成!")
                }
            }
        })
        def body = MultipartBody.Part.createFormData("file", file.getName(), fileRequestFile)
        // 安装类型
        def buildInstallType = MultipartBody.Part.createFormData("buildInstallType", extension.installType)
        // 密码
        def buildPassword = MultipartBody.Part.createFormData("buildPassword", extension.password)
        // 更新描述
        def buildUpdateDescription = MultipartBody.Part.createFormData("buildUpdateDescription", extension.updateDescription)
        // 构建名称
        def buildName = MultipartBody.Part.createFormData("buildName", extension.name)
        // 安装有效期
        def buildInstallDate = MultipartBody.Part.createFormData("buildInstallDate", extension.installDate)
        // 开始日期
        def buildInstallStartDate = MultipartBody.Part.createFormData("buildInstallStartDate", extension.installStartDate)
        // 结束日期
        def buildInstallEndDate = MultipartBody.Part.createFormData("buildInstallEndDate", extension.installEndDate)
        // 渠道
        def buildChannelShortcut = MultipartBody.Part.createFormData("buildChannelShortcut", extension.channelShortcut)

        return api.upload(apiKey, body, buildInstallType, buildPassword, buildUpdateDescription,
                buildName, buildInstallDate, buildInstallStartDate, buildInstallEndDate, buildChannelShortcut)
    }
}
