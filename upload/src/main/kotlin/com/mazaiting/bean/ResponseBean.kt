package com.mazaiting

/**
 * 响应实体
 * {*     "code": 0,
 * "message": "",
 * "data": {*         "buildKey": "ca4503916847e36b6d57587ba814907e",
 * "buildType": "2",
 * "buildIsFirst": "0",
 * "buildIsLastest": "1",
 * "buildFileKey": "4747c67d4d93eee4de9e89d9ea0d600c.apk",
 * "buildFileName": "15_11_30_卡托交接_uat.apk",
 * "buildFileSize": "14702781",
 * "buildName": "卡托交接",
 * "buildVersion": "1.0.0",
 * "buildVersionNo": "1",
 * "buildBuildVersion": "1",
 * "buildIdentifier": "com.mazaiting.use_cato",
 * "buildIcon": "4430020f3002c3c011deb884ac253cc3",
 * "buildDescription": "",
 * "buildUpdateDescription": "",
 * "buildScreenshots": "",
 * "buildShortcutUrl": "EZOP",
 * "buildCreated": "2020-05-16 08:59:06",
 * "buildUpdated": "2020-05-16 08:59:06",
 * "buildQRCodeURL": "https://www.pgyer.com/app/qrcodeHistory/0797dfc9d4e2316665d9c9733f6aa69bb69e20bb811c6122e55a179d057b2650"
 * }*} */
internal class ResponseBean {
    /**
     * 响应码
     */
    private val code: String? = null

    /**
     * 消息内容
     */
    private val message: String? = null

    /**
     * 数据
     */
    private val data: Data? = null

    companion object {
        /**
         * 异常信息键值
         */
        private val MAP: Map<String, String> = mapOf(
            "key" to "test",
            "1001" to "_api_key 不能为空",
            "1002" to "_api_key 错误",
            "1003" to "同步用户信息到Tracup出错信息",
            "1007" to "搜索词太短",
            "1008" to "Build Key 和 appKey 不能同时为空",
            "1009" to "没有找到该App相关信息",
            "1010" to "App Key 不能为空",
            "1011" to "Build Id 不能为空",
            "1012" to "User key 不能为空",
            "1013" to "没有找到该用户",
            "1014" to "应用类型不能为空",
            "1015" to "文件或App类型错误",
            "1016" to "无效的包名",
            "1017" to "短链接已被使用或者不符合规范",
            "1018" to "App 数量超过套餐的上限",
            "1019" to "未实名认证",
            "1020" to "用户被禁止上传",
            "1021" to "文件无效",
            "1022" to "文件过大",
            "1023" to "build key 不能为空",
            "1024" to "发布范围超过限制",
            "1025" to "文件上传失败",
            "1026" to "无效文件，文件类型错误",
            "1027" to "应用名称长度不符合规范",
            "1028" to "未找到应用的标识符",
            "1029" to "短链接已被使用或不符合规范",
            "1030" to "发布企业签名数量超过套餐最大值",
            "1031" to "短链接不能为空",
            "1032" to "短链接无效",
            "1033" to "只能修改自己的应用",
            "1035" to "修改的字段超过限制",
            "1036" to "修改内容不能为空",
            "1037" to "图片必须以数组的形式上传",
            "1038" to "图片数据不能为空",
            "1039" to "应用截图最多不能超过5张",
            "1040" to "应用截图上传失败",
            "1041" to "应用截图key不能为空",
            "1042" to "反馈内容不能为空",
            "1043" to "反馈内容过长，不能超过200字",
            "1044" to "添加反馈出错",
            "1045" to "无效的 user Key",
            "1047" to "该应用收费，请使用手机进行安装",
            "1048" to "应用已过期",
            "1049" to "应用下载次数已用完",
            "1050" to "密码错误",
            "1051" to "应用违规",
            "1052" to "应用仅认证用户可以下载",
            "1053" to "下载速度超过限制",
            "1054" to "今日下载次数已用完",
            "1055" to "无效的 api key",
            "1056" to "请在 iOS 系统中打开该链接",
            "1057" to "同步专家测试企业签名应用参数错误",
            "1058" to "解析应用出错",
            "1059" to "废弃的方法",
            "1060" to "请输入你的邮箱",
            "1061" to "请输入你的密码",
            "1062" to "用户名或者密码不正确",
            "1063" to "该邮箱已存在",
            "1064" to "请输入你的用户名",
            "1065" to "用户名不能超过15个字符",
            "1066" to "请输入你的姓名",
            "1067" to "请输入你的公司",
            "1068" to "请输入你的职业",
            "1069" to "该手机号码已存在",
            "1070" to "请输入验证码",
            "1071" to "验证码无效",
            "1072" to "该邮箱不存在",
            "1073" to "请输入正确的账户信息",
            "1075" to "App group key 不能为空",
            "1076" to "App key 不正确",
            "1079" to "录音上传失败",
            "1080" to "反馈失败",
            "1081" to "非法请求",
            "1082" to "Feedback Key 不能为空",
            "1083" to "反馈信息未找到",
            "1084" to "日志信息不能为空",
            "1085" to "系统类型不正确",
            "1086" to "crash id 不能为空",
            "1087" to "crash 信息没有找到",
            "1089" to "获取平台参数不能为空",
            "1090" to "平台参数为windows或者mac",
            "1091" to "版本参数不对",
            "1092" to "版本信息没有找到",
            "1093" to "没有找到信息",
            "1094" to "app group key 不正确",
            "1095" to "应用名称过长",
            "1096" to "错误的方法",
            "1097" to "签名错误",
            "1098" to "Api 请求达到每小时的上限",
            "1099" to "更新 App 失败",
            "1100" to "没有找到 App 分组信息",
            "1102" to "请输入邮箱验证码",
            "1103" to "您输入的验证码不正确",
            "1104" to "您输入的邮箱地址无效",
            "1105" to "该账号已存在",
            "1106" to "真实姓名的长度必须小于15",
            "1107" to "请填写密码",
            "1108" to "请正确填写您的手机号码",
            "1109" to "请输入您的6位验证码",
            "1110" to "注册失败",
            "1111" to "请输入你的账号",
            "1112" to "该手机号码绑定多个账号，请使用密码进行登录",
            "1113" to "请填写邮箱地址",
            "1115" to "用户不存在",
            "1116" to "手机号码或密码不正确",
            "1117" to "邮箱地址或密码不正确",
            "1118" to "请输入手机号码",
            "1120" to "每次发送短信的间隔必须在30秒以上",
            "1121" to "验证失败",
            "1122" to "权限不足",
            "1123" to "统计类型出错",
            "1124" to "授权信息失败",
            "1125" to "绑定已有账号",
            "1126" to "邮箱未更改",
            "1127" to "邮箱地址已存在",
            "1128" to "权限不足，只能删除自己上传的应用",
            "1129" to "该账号已绑定微信号，请重新输入账号",
            "1130" to "请输入您的新密码",
            "1131" to "请再次输入密码",
            "1132" to "两次输入密码不一致，请重新输入",
            "1133" to "JSCode 不能为空",
            "1134" to "JSCode 无效",
            "1135" to "没有找到资质文件",
            "1136" to "删除资质文件失败",
            "1137" to "上传资质文件出错",
            "1138" to "文件数量超过最大限制",
            "1139" to "一种类型只能上传两张文件",
            "1140" to "添加资质文件失败",
            "1141" to "交易类型不能为空",
            "1142" to "当前版本不可隐藏",
            "1143" to "encryptedData和iv不能为空",
            "1144" to "请输入安装开始时间及结束时间",
            "1145" to "安装结束时间必须大于开始时间",
            "1148" to "请上传身份证正面照片",
            "1149" to "请上传身份证反面照片",
            "1150" to "请上传手持身份证照片",
            "1151" to "企业名称不能为空",
            "1152" to "营业执照号码不能为空",
            "1153" to "请上传营业执照照片",
            "1154" to "省份不能为空",
            "1155" to "城市不能为空",
            "1156" to "当日自动审核次数已用完",
            "1157" to "自动审核未通过",
            "1158" to "图片不能大于10M",
            "1159" to "图片不能小于15k",
            "1160" to "图片类型不正确",
            "1161" to "fileType 不能为空",
            "1162" to "名称不能为空",
            "1163" to "描述不能为空",
            "1164" to "appKeys 不能为空",
            "1165" to "描述文字太长",
            "1166" to "分组名称不符合规则",
            "1167" to "分组的应用，至少两个",
            "1168" to "网址后缀不能为空",
            "1169" to "新号码和旧号码不能一样",
            "1170" to "手机号码错误",
            "1171" to "真实姓名不能为空",
            "1172" to "身份证号不能为空",
            "1173" to "真实姓名不匹配",
            "1174" to "身份证号不匹配",
            "1175" to "发布时间不能为空",
            "1176" to "请输入正确的发布时间",
            "1177" to "获取应用信息失败",
            "1178" to "应用类型不能为空",
            "1179" to "不能合并空白应用",
            "1180" to "应用已合并",
            "1181" to "请合并iOS应用",
            "1182" to "请合并Android应用",
            "1183" to "必须是自己的应用",
            "1184" to "不能和自己合并",
            "1185" to "图标已存在不等上传图标",
            "1186" to "应用未发布",
            "1187" to "请升级您的版本",
            "1188" to "发生错误",
            "1189" to "暂时不能更改文件",
            "1190" to "请上传文网文",
            "1191" to "请上传ICP 许可证",
            "1192" to "上传软件著作权登记证",
            "1193" to "上传营业热照",
            "1194" to "请上传金融牌照",
            "1195" to "请上传其他证件",
            "1196" to "请上传有关资质文件",
            "1197" to "请上传信息网络传播视听节目许可证",
            "1198" to "请重新上传审核不通过的文件",
            "1199" to "相关文件数目不匹配",
            "1200" to "申诉理由不能少于20个字符,不能多于500字符",
            "1201" to "申诉图片不能大于5张",
            "1202" to "不能删除所有可下载版本，如需删除应用，可在设置中删除应用",
            "1203" to "不能删除所有显示的版本",
            "1212" to "渠道短链接无效，请检查短链接是否正确",
            "1213" to "仅支持 iOS 应用证书检测",
            "1214" to "请上传 p12 文件",
            "1215" to "请上传 mobileprovision 文件",
            "1216" to "文件上传失败",
            "1217" to "文件保存失败",
            "1218" to "添加证书失败"
        )
    }
    // 应用名称 buildName
    // 应用版本
    // 应用构建版本
    // 下载地址
    /**
     * 获取应用名称
     * @return 应用名
     */
    fun buildName(): String? {
        return if (null != data) {
            data.buildName
        } else ""
    }

    /**
     * 获取应用版本
     * @return 应用版本
     */
    fun buildVersion(): String? {
        return if (null != data) {
            data.buildVersion
        } else ""
    }

    /**
     * 获取应用构件版本
     * @return 应用构件版本
     */
    fun buildBuildVersion(): String? {
        return if (null != data) {
            data.buildBuildVersion
        } else ""
    }

    /**
     * 获取应用下载地址
     * @return 应用下载地址
     */
    fun buildUrl(): String {
        return if (null != data) {
            "http://www.pgyer.com/" + data.buildShortcutUrl
        } else ""
    }

    /**
     * 判断是否成功
     * @return
     */
    val isSuccess: Boolean
        get() = code == "0"

    /**
     * 获取失败消息
     * @return 失败信息
     */
    val failedMessage: String
        get() {
            var msg: String? = MAP[code]
            if (null == msg || "" === msg) {
                msg = "未知异常"
            }
            return msg
        }

    override fun toString(): String {
        return "ResponseBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data.toString() +
                '}'
    }

    /**
     * 数据类
     */
    internal class Data {
        private val buildKey: String? = null
        private val buildType: String? = null
        private val buildIsFirst: String? = null
        private val buildIsLastest: String? = null
        private val buildFileKey: String? = null
        private val buildFileName: String? = null
        private val buildFileSize: String? = null
        internal val buildName: String? = null
        internal val buildVersion: String? = null
        private val buildVersionNo: String? = null
        internal val buildBuildVersion: String? = null
        private val buildIdentifier: String? = null
        private val buildIcon: String? = null
        private val buildDescription: String? = null
        private val buildUpdateDescription: String? = null
        private val buildScreenshots: String? = null
        internal val buildShortcutUrl: String? = null
        private val buildCreated: String? = null
        private val buildUpdated: String? = null
        private val buildQRCodeURL: String? = null
        override fun toString(): String {
            return "Data{" +
                    "buildKey='" + buildKey + '\'' +
                    ", buildType='" + buildType + '\'' +
                    ", buildIsFirst='" + buildIsFirst + '\'' +
                    ", buildIsLastest='" + buildIsLastest + '\'' +
                    ", buildFileKey='" + buildFileKey + '\'' +
                    ", buildFileName='" + buildFileName + '\'' +
                    ", buildFileSize='" + buildFileSize + '\'' +
                    ", buildName='" + buildName + '\'' +
                    ", buildVersion='" + buildVersion + '\'' +
                    ", buildVersionNo='" + buildVersionNo + '\'' +
                    ", buildBuildVersion='" + buildBuildVersion + '\'' +
                    ", buildIdentifier='" + buildIdentifier + '\'' +
                    ", buildIcon='" + buildIcon + '\'' +
                    ", buildDescription='" + buildDescription + '\'' +
                    ", buildUpdateDescription='" + buildUpdateDescription + '\'' +
                    ", buildScreenshots='" + buildScreenshots + '\'' +
                    ", buildShortcutUrl='" + buildShortcutUrl + '\'' +
                    ", buildCreated='" + buildCreated + '\'' +
                    ", buildUpdated='" + buildUpdated + '\'' +
                    ", buildQRCodeURL='" + buildQRCodeURL + '\'' +
                    '}'
        }
    }
}