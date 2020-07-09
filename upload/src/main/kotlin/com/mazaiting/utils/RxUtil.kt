package com.mazaiting.utils

import com.mazaiting.Api
import com.mazaiting.PgyerExtension
import com.mazaiting.ResponseBean
import com.mazaiting.uploadLog
import com.mazaiting.utils.FileRequestBody.LoadingListener
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

/**
 * 网络工具类
 * @author mazaiting* @date 2018/2/6
 */
internal object RxUtil {
    /**
     * 应用密钥
     */
    private const val API_KEY = ""
    /**
     * 网络地址
     */
    private const val URL = "http://www.pgyer.com/apiv2/"

    /**
     * 获取拦截器
     * @return
     */
    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                    if ("" !== message) {
                        println(message)
                    }
                })
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

    /**
     * 获取OkHttpClient.Builder
     * @return OkHttpClient.Builder
     */
    private val okHttpClientBuilder: OkHttpClient.Builder
        get() = OkHttpClient.Builder() // 设置连接超时时间
            .connectTimeout(30, TimeUnit.SECONDS) // 设置读取超时时间
            .readTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(httpLoggingInterceptor)

    /**
     * 获取 Retrofit
     */
    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(URL) //设置网络请求的Url地址
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()

    /**
     * 获取 Api
     */
    private val api: Api
        get() = retrofit.create(Api::class.java)

    /**
     * 获取异常信息
     */
    fun getErrorMessage(throwable: Throwable): String {
        var msg: String? = throwable.message
        if (throwable.message === "timeout") {
            msg = "上传文件超时"
        }
        return msg ?: ""
    }

    /**
     * 上传APK
     * @param extension 扩展字段
     * @param path 文件路径
     * @return
     */
    @JvmStatic
    fun upload(extension: PgyerExtension, path: String): Observable<ResponseBean> {
        // app key
        val apiKey = MultipartBody.Part.createFormData("_api_key", extension.apiKey)
        // file
        val file = File(path)
        uploadLog("文件路径: ${file.absolutePath}")
        var currentProgress = 0L
        val requestFile =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val fileRequestFile = FileRequestBody(requestFile, object : LoadingListener {
            override fun onProgress(
                currentLength: Long,
                contentLength: Long
            ) {
                // 计算进度
                val progress: Long = (currentLength * 1.0 / contentLength * 100).roundToLong()
                // 如果进度不相等则打印
                if (currentProgress != progress) {
                    println("当前上传进度: $progress%")
                    currentProgress = progress
                }
                if (currentLength == contentLength) {
                    println("上传完成!")
                }
            }
        })
        val body = MultipartBody.Part.createFormData("file", file.getName(), fileRequestFile)
        // 安装类型
        val buildInstallType =
            MultipartBody.Part.createFormData("buildInstallType", extension.installType)
        // 密码
        val buildPassword =
            MultipartBody.Part.createFormData("buildPassword", extension.password)
        // 更新描述
        val buildUpdateDescription =
            MultipartBody.Part.createFormData("buildUpdateDescription", extension.updateDescription)
        // 构建名称
        val buildName = MultipartBody.Part.createFormData("buildName", extension.name)
        // 安装有效期
        val buildInstallDate =
            MultipartBody.Part.createFormData("buildInstallDate", extension.installDate)
        // 开始日期
        val buildInstallStartDate =
            MultipartBody.Part.createFormData("buildInstallStartDate", extension.installStartDate)
        // 结束日期
        val buildInstallEndDate =
            MultipartBody.Part.createFormData("buildInstallEndDate", extension.installEndDate)
        // 渠道
        val buildChannelShortcut =
            MultipartBody.Part.createFormData("buildChannelShortcut", extension.channelShortcut)
        return api.upload(
            apiKey,
            body,
            buildInstallType,
            buildPassword,
            buildUpdateDescription,
            buildName,
            buildInstallDate,
            buildInstallStartDate,
            buildInstallEndDate,
            buildChannelShortcut
        )
    }
}