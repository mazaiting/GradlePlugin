package com.mazaiting

import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * 接口
 */
internal interface Api {
    /**
     * 蒲公英上传 APK
     * _api_key	String	(必填) API Key 点击获取_api_key
     * file	File	(必填) 需要上传的ipa或者apk文件
     * buildInstallType	Integer	(必填)应用安装方式，值为(2,3)。2：密码安装，3：邀请安装
     * buildPassword	String	(必填) 设置App安装密码
     * buildUpdateDescription	String	(选填) 版本更新描述，请传空字符串，或不传。
     * buildName	String	(选填) 应用名称
     * buildInstallDate	Interger	(选填)是否设置安装有效期，值为：1 设置有效时间， 2 长期有效，如果不填写不修改上一次的设置
     * buildInstallStartDate	String	(选填)安装有效期开始时间，字符串型，如：2018-01-01
     * buildInstallEndDate	String	(选填)安装有效期结束时间，字符串型，如：2018-12-31
     * buildChannelShortcut	String	(选填)所需更新的指定渠道的下载短链接，只可指定一个渠道，字符串型，如：abcd
     */
    @Multipart
    @POST("app/upload")
    fun upload(
        @Part api_key: MultipartBody.Part?,
        @Part file: MultipartBody.Part?,
        @Part buildInstallType: MultipartBody.Part?,
        @Part buildPassword: MultipartBody.Part?,
        @Part buildUpdateDescription: MultipartBody.Part?,
        @Part buildName: MultipartBody.Part?,
        @Part buildInstallDate: MultipartBody.Part?,
        @Part buildInstallStartDate: MultipartBody.Part?,
        @Part buildInstallEndDate: MultipartBody.Part?,
        @Part buildChannelShortcut: MultipartBody.Part?
    ): Observable<ResponseBean>
}