package com.mazaiting

import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * 蒲公英插件
 */
class PgyerExtension {
    /**
     * _api_key	String	(必填) API Key 点击获取_api_key
     */
    String apiKey = ""
    /**
     * file	文件路径	(必填) 需要上传的ipa或者apk文件
     */
    String file = ""
    /**
     * buildInstallType	Integer	(必填)应用安装方式，值为(2,3)。2：密码安装，3：邀请安装
     */
    String installType = ""
    /**
     * buildPassword	String	(必填) 设置App安装密码
     */
    String password = ""
    /**
     * buildUpdateDescription	String	(选填) 版本更新描述，请传空字符串，或不传。
     */
    String updateDescription = ""
    /**
     * buildName	String	(选填) 应用名称
     */
    String name = ""
    /**
     * buildInstallDate	Interger	(选填)是否设置安装有效期，值为：1 设置有效时间， 2 长期有效，如果不填写不修改上一次的设置
     */
    String installDate = ""
    /**
     * buildInstallStartDate	String	(选填)安装有效期开始时间，字符串型，如：2018-01-01
     */
    String installStartDate = ""
    /**
     * buildInstallEndDate	String	(选填)安装有效期结束时间，字符串型，如：2018-12-31
     */
    String installEndDate = ""
    /**
     * buildChannelShortcut	String	(选填)所需更新的指定渠道的下载短链接，只可指定一个渠道，字符串型，如：abcd
     */
    String channelShortcut = ""

    /**
     * 检测参数
     * @return
     */
    public String checkParam() {
        // 检测 API_KEY 是否为空
        if (isEmpty(this.apiKey)) {
            return "apiKey属性: API_KEY 为空, 请先设置该属性"
        }
        // 检测文件路径是否为空
        if (isEmpty(this.file)) {
            return "file 属性: 文件路径为空, 请先设置该属性"
        } else {
            if (!this.file.endsWith(".apk") && !this.file.endsWith(".ipa")) {
                return "file 属性: 文件设置错误, 请设置后缀名为.apk 或.ipa 的安装包"
            }
            // 获取文件
            def file = new File(this.file)
            // 是否存在
            if (!file.exists()) {
                return "file 属性: 文件不存在, 请检查文件路径或文件名"
            }
            // 判断文件是否为路径
            if (file.isDirectory()) {
                return "file 属性: 文件路径为文件夹, 请设置正确的文件路径"
            }
            // 判断文件大小
            if (file.length() <= 0) {
                return "file 属性: 文件长度过小, 请检查"
            }
        }
        // 检测安装类型
        if (isEmpty(this.installType)) {
            return "installType 属性: 安装类型为空, 请先设置该属性"
        } else {
            if ("2" != this.installType && "3" != this.installType) {
                return "installType 属性: 安装类型设置错误, 请设置正确的安装类型(2：密码安装，3：邀请安装)"
            }
        }
        // 检测密码
        if (isEmpty(this.password)) {
            return "password 属性: 密码为空, 请先设置密码"
        }

        // 更新描述 buildUpdateDescription
        // 应用名称 buildName

        // 判断安装有效期
        if (!isEmpty(this.installDate)) {
            if ("1" != this.installDate && "2" != this.installDate) {
                return "installDate 属性: 安装有效期设置错误, 请设置正确的安装有效期(1:设置有效时间，2:长期有效)"
            }
        }
        // 格式化
        def format = new SimpleDateFormat("yyyy-MM-dd")
        // 判断是否为空
        if (!isEmpty(this.installStartDate)) {
            // 校验安装开始时间
            try {
                format.parse(this.installStartDate)
            } catch (ParseException ignored) {
                return "installStartDate 属性: 安装有效期开始时间格式设置错误, 请设置形如`2018-01-01`类型"
            }
        }
        // 判断结束日期日否为空
        if (!isEmpty(this.installEndDate)) {
            // 校验安装结束时间
            try {
                format.parse(this.installEndDate)
            } catch (ParseException ignored) {
                return "installEndDate 属性: 安装有效期结束时间格式设置错误, 请设置形如`2018-12-31`类型"
            }
        }
        // 校验渠道
        if (!isEmpty(this.channelShortcut)) {
            if (this.channelShortcut.startsWith("http://") || this.channelShortcut.startsWith("https://")) {
                return "channelShortcut 属性: 下载短链接设置错误, 请设置形如`abcd`格式字符串, 只可指定一个渠道"
            }
        }
        return ""
    }

    @Override
    public String toString() {
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
                '}';
    }

    /**
     * 检测字符串是否为空
     * @param text 字符串
     * @return true: 为空; false: 不为空
     */
    public static boolean isEmpty(String text) {
        return null == text || text.length() == 0
    }
}