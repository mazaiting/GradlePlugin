package com.mazaiting

import com.android.builder.model.BuildType
import java.io.File
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
 * @description 蒲公英扩展, 存储信息
 */
open class PgyerExtension(
    /**
     * _api_key	String	(必填) API Key 点击获取_api_key
     */
    var apiKey: String = "",

    /**
     * buildPassword	String	(必填) 设置App安装密码
     */
    var password: String = "",

    /**
     * buildInstallType	Integer	(必填)应用安装方式，值为(2,3)。2：密码安装，3：邀请安装
     */
    var installType: String = "",

    /**
     * file	文件路径	(必填) 需要上传的ipa或者apk文件
     */
    var file: String = "",

    /**
     * buildUpdateDescription	String	(选填) 版本更新描述，请传空字符串，或不传。
     */
    var updateDescription: String = "",

    /**
     * buildName	String	(选填) 应用名称
     */
    var name: String = "",

    /**
     * buildInstallDate	Interger	(选填)是否设置安装有效期，值为：1 设置有效时间， 2 长期有效，如果不填写不修改上一次的设置
     */
    var installDate: String = "",

    /**
     * buildInstallStartDate	String	(选填)安装有效期开始时间，字符串型，如：2018-01-01
     */
    var installStartDate: String = "",

    /**
     * buildInstallEndDate	String	(选填)安装有效期结束时间，字符串型，如：2018-12-31
     */
    var installEndDate: String = "",

    /**
     * buildChannelShortcut	String	(选填)所需更新的指定渠道的下载短链接，只可指定一个渠道，字符串型，如：abcd
     */
    var channelShortcut: String = ""
) {

    companion object {
        /**
         * 检测字符串是否为空
         * @param text 字符串
         * @return true: 为空; false: 不为空
         */
        fun isEmpty(text: String?): Boolean {
            return null == text || text.isEmpty()
        }
    }

    /**
     * 检测参数
     * @return
     */
    fun checkParam(): String {
        // 检测 API_KEY 是否为空
        if (isEmpty(apiKey)) {
            return "apiKey属性: API_KEY 为空, 请先设置该属性"
        }
        // 检测密码
        if (isEmpty(password)) {
            return "password 属性: 密码为空, 请先设置密码"
        }
        // 检测安装类型
        if (isEmpty(installType)) {
            installType = "2"
        } else {
            if ("2" !== installType && "3" !== installType) {
                return "installType 属性: 安装类型设置错误, 请设置正确的安装类型(2：密码安装，3：邀请安装)"
            }
        }

        // 更新描述 buildUpdateDescription
        // 应用名称 buildName

        // 判断安装有效期
        if (!isEmpty(installDate)) {
            if ("1" !== installDate && "2" !== installDate) {
                return "installDate 属性: 安装有效期设置错误, 请设置正确的安装有效期(1:设置有效时间，2:长期有效)"
            }
        }
        // 格式化
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        // 判断是否为空
        if (!isEmpty(installStartDate)) {
            // 校验安装开始时间
            try {
                format.parse(installStartDate)
            } catch (ignored: ParseException) {
                return "installStartDate 属性: 安装有效期开始时间格式设置错误, 请设置形如`2018-01-01`类型"
            }
        }
        // 判断结束日期日否为空
        if (!isEmpty(installEndDate)) {
            // 校验安装结束时间
            try {
                format.parse(installEndDate)
            } catch (ignored: ParseException) {
                return "installEndDate 属性: 安装有效期结束时间格式设置错误, 请设置形如`2018-12-31`类型"
            }
        }
        // 校验渠道
        if (!isEmpty(channelShortcut)) {
            if (channelShortcut.startsWith("http://") || channelShortcut.startsWith("https://")) {
                return "channelShortcut 属性: 下载短链接设置错误, 请设置形如`abcd`格式字符串, 只可指定一个渠道"
            }
        }
        return ""
    }

    /**
     * 检查文件是否合法
     * @param path 文件路径
     */
    fun checkFile(path: String): String {
        // 检测文件路径是否为空
        if (!isEmpty(path)) {
            if (!path.endsWith(".apk") && !path.endsWith(".ipa")) {
                return "file 属性: 文件设置错误, 请设置后缀名为.apk 或.ipa 的安装包"
            }
            // 获取文件
            val file = File(path)
            // 是否存在
            if (!file.exists()) {
                uploadLog(file.absolutePath)
                return "file 属性: 文件不存在, 请检查文件路径或文件名"
            }
            // 判断文件是否为路径
            if (file.isDirectory) {
                uploadLog(file.absolutePath)
                return "file 属性: 文件路径为文件夹, 请设置正确的文件路径"
            }
            // 判断文件大小
            if (file.length() <= 0) {
                return "file 属性: 文件长度过小, 请检查"
            }
        }
        return ""
    }

    override fun toString(): String {
        return "PgyerExtension{" +
                "apiKey='" + apiKey + '\'' +
                ", file='" + file + '\'' +
                ", installType='" + installType + '\'' +
                ", password='" + password + '\'' +
                ", updateDescription='" + updateDescription + '\'' +
                ", name='" + name + '\'' +
                ", installDate='" + installDate + '\'' +
                ", installStartDate='" + installStartDate + '\'' +
                ", installEndDate='" + installEndDate + '\'' +
                ", channelShortcut='" + channelShortcut + '\'' +
                '}'
    }
}
