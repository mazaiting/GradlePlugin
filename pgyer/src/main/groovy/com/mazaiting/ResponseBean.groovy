package com.mazaiting

/**
 * 响应实体
 *{*     "code": 0,
 *     "message": "",
 *     "data": {*         "buildKey": "ca4503916847e36b6d57587ba814907e",
 *         "buildType": "2",
 *         "buildIsFirst": "0",
 *         "buildIsLastest": "1",
 *         "buildFileKey": "4747c67d4d93eee4de9e89d9ea0d600c.apk",
 *         "buildFileName": "15_11_30_卡托交接_uat.apk",
 *         "buildFileSize": "14702781",
 *         "buildName": "卡托交接",
 *         "buildVersion": "1.0.0",
 *         "buildVersionNo": "1",
 *         "buildBuildVersion": "1",
 *         "buildIdentifier": "com.mazaiting.use_cato",
 *         "buildIcon": "4430020f3002c3c011deb884ac253cc3",
 *         "buildDescription": "",
 *         "buildUpdateDescription": "",
 *         "buildScreenshots": "",
 *         "buildShortcutUrl": "EZOP",
 *         "buildCreated": "2020-05-16 08:59:06",
 *         "buildUpdated": "2020-05-16 08:59:06",
 *         "buildQRCodeURL": "https://www.pgyer.com/app/qrcodeHistory/0797dfc9d4e2316665d9c9733f6aa69bb69e20bb811c6122e55a179d057b2650"
 *}*}*/
class ResponseBean {
    /**
     * 响应码
     */
    private String code
    /**
     * 消息内容
     */
    private String message
    /**
     * 数据
     */
    private Data data
    /**
     * 异常信息键值
     */
    private static Map<String, String> MAP = new HashMap<>()

    static {
        MAP.put("1001", "_api_key 不能为空")
        MAP.put("1002", "_api_key 错误")
        MAP.put("1003", "同步用户信息到Tracup出错信息")
        MAP.put("1007", "搜索词太短")
        MAP.put("1008", "Build Key 和 appKey 不能同时为空")
        MAP.put("1009", "没有找到该App相关信息")
        MAP.put("1010", "App Key 不能为空")
        MAP.put("1011", "Build Id 不能为空")
        MAP.put("1012", "User key 不能为空")
        MAP.put("1013", "没有找到该用户")
        MAP.put("1014", "应用类型不能为空")
        MAP.put("1015", "文件或App类型错误")
        MAP.put("1016", "无效的包名")
        MAP.put("1017", "短链接已被使用或者不符合规范")
        MAP.put("1018", "App 数量超过套餐的上限")
        MAP.put("1019", "未实名认证")
        MAP.put("1020", "用户被禁止上传")
        MAP.put("1021", "文件无效")
        MAP.put("1022", "文件过大")
        MAP.put("1023", "build key 不能为空")
        MAP.put("1024", "发布范围超过限制")
        MAP.put("1025", "文件上传失败")
        MAP.put("1026", "无效文件，文件类型错误")
        MAP.put("1027", "应用名称长度不符合规范")
        MAP.put("1028", "未找到应用的标识符")
        MAP.put("1029", "短链接已被使用或不符合规范")
        MAP.put("1030", "发布企业签名数量超过套餐最大值")
        MAP.put("1031", "短链接不能为空")
        MAP.put("1032", "短链接无效")
        MAP.put("1033", "只能修改自己的应用")
        MAP.put("1035", "修改的字段超过限制")
        MAP.put("1036", "修改内容不能为空")
        MAP.put("1037", "图片必须以数组的形式上传")
        MAP.put("1038", "图片数据不能为空")
        MAP.put("1039", "应用截图最多不能超过5张")
        MAP.put("1040", "应用截图上传失败")
        MAP.put("1041", "应用截图key不能为空")
        MAP.put("1042", "反馈内容不能为空")
        MAP.put("1043", "反馈内容过长，不能超过200字")
        MAP.put("1044", "添加反馈出错")
        MAP.put("1045", "无效的 user Key")
        MAP.put("1047", "该应用收费，请使用手机进行安装")
        MAP.put("1048", "应用已过期")
        MAP.put("1049", "应用下载次数已用完")
        MAP.put("1050", "密码错误")
        MAP.put("1051", "应用违规")
        MAP.put("1052", "应用仅认证用户可以下载")
        MAP.put("1053", "下载速度超过限制")
        MAP.put("1054", "今日下载次数已用完")
        MAP.put("1055", "无效的 api key")
        MAP.put("1056", "请在 iOS 系统中打开该链接")
        MAP.put("1057", "同步专家测试企业签名应用参数错误")
        MAP.put("1058", "解析应用出错")
        MAP.put("1059", "废弃的方法")
        MAP.put("1060", "请输入你的邮箱")
        MAP.put("1061", "请输入你的密码")
        MAP.put("1062", "用户名或者密码不正确")
        MAP.put("1063", "该邮箱已存在")
        MAP.put("1064", "请输入你的用户名")
        MAP.put("1065", "用户名不能超过15个字符")
        MAP.put("1066", "请输入你的姓名")
        MAP.put("1067", "请输入你的公司")
        MAP.put("1068", "请输入你的职业")
        MAP.put("1069", "该手机号码已存在")
        MAP.put("1070", "请输入验证码")
        MAP.put("1071", "验证码无效")
        MAP.put("1072", "该邮箱不存在")
        MAP.put("1073", "请输入正确的账户信息")
        MAP.put("1075", "App group key 不能为空")
        MAP.put("1076", "App key 不正确")
        MAP.put("1079", "录音上传失败")
        MAP.put("1080", "反馈失败")
        MAP.put("1081", "非法请求")
        MAP.put("1082", "Feedback Key 不能为空")
        MAP.put("1083", "反馈信息未找到")
        MAP.put("1084", "日志信息不能为空")
        MAP.put("1085", "系统类型不正确")
        MAP.put("1086", "crash id 不能为空")
        MAP.put("1087", "crash 信息没有找到")
        MAP.put("1089", "获取平台参数不能为空")
        MAP.put("1090", "平台参数为windows或者mac")
        MAP.put("1091", "版本参数不对")
        MAP.put("1092", "版本信息没有找到")
        MAP.put("1093", "没有找到信息")
        MAP.put("1094", "app group key 不正确")
        MAP.put("1095", "应用名称过长")
        MAP.put("1096", "错误的方法")
        MAP.put("1097", "签名错误")
        MAP.put("1098", "Api 请求达到每小时的上限")
        MAP.put("1099", "更新 App 失败")
        MAP.put("1100", "没有找到 App 分组信息")
        MAP.put("1102", "请输入邮箱验证码")
        MAP.put("1103", "您输入的验证码不正确")
        MAP.put("1104", "您输入的邮箱地址无效")
        MAP.put("1105", "该账号已存在")
        MAP.put("1106", "真实姓名的长度必须小于15")
        MAP.put("1107", "请填写密码")
        MAP.put("1108", "请正确填写您的手机号码")
        MAP.put("1109", "请输入您的6位验证码")
        MAP.put("1110", "注册失败")
        MAP.put("1111", "请输入你的账号")
        MAP.put("1112", "该手机号码绑定多个账号，请使用密码进行登录")
        MAP.put("1113", "请填写邮箱地址")
        MAP.put("1115", "用户不存在")
        MAP.put("1116", "手机号码或密码不正确")
        MAP.put("1117", "邮箱地址或密码不正确")
        MAP.put("1118", "请输入手机号码")
        MAP.put("1120", "每次发送短信的间隔必须在30秒以上")
        MAP.put("1121", "验证失败")
        MAP.put("1122", "权限不足")
        MAP.put("1123", "统计类型出错")
        MAP.put("1124", "授权信息失败")
        MAP.put("1125", "绑定已有账号")
        MAP.put("1126", "邮箱未更改")
        MAP.put("1127", "邮箱地址已存在")
        MAP.put("1128", "权限不足，只能删除自己上传的应用")
        MAP.put("1129", "该账号已绑定微信号，请重新输入账号")
        MAP.put("1130", "请输入您的新密码")
        MAP.put("1131", "请再次输入密码")
        MAP.put("1132", "两次输入密码不一致，请重新输入")
        MAP.put("1133", "JSCode 不能为空")
        MAP.put("1134", "JSCode 无效")
        MAP.put("1135", "没有找到资质文件")
        MAP.put("1136", "删除资质文件失败")
        MAP.put("1137", "上传资质文件出错")
        MAP.put("1138", "文件数量超过最大限制")
        MAP.put("1139", "一种类型只能上传两张文件")
        MAP.put("1140", "添加资质文件失败")
        MAP.put("1141", "交易类型不能为空")
        MAP.put("1142", "当前版本不可隐藏")
        MAP.put("1143", "encryptedData和iv不能为空")
        MAP.put("1144", "请输入安装开始时间及结束时间")
        MAP.put("1145", "安装结束时间必须大于开始时间")
        MAP.put("1148", "请上传身份证正面照片")
        MAP.put("1149", "请上传身份证反面照片")
        MAP.put("1150", "请上传手持身份证照片")
        MAP.put("1151", "企业名称不能为空")
        MAP.put("1152", "营业执照号码不能为空")
        MAP.put("1153", "请上传营业执照照片")
        MAP.put("1154", "省份不能为空")
        MAP.put("1155", "城市不能为空")
        MAP.put("1156", "当日自动审核次数已用完")
        MAP.put("1157", "自动审核未通过")
        MAP.put("1158", "图片不能大于10M")
        MAP.put("1159", "图片不能小于15k")
        MAP.put("1160", "图片类型不正确")
        MAP.put("1161", "fileType 不能为空")
        MAP.put("1162", "名称不能为空")
        MAP.put("1163", "描述不能为空")
        MAP.put("1164", "appKeys 不能为空")
        MAP.put("1165", "描述文字太长")
        MAP.put("1166", "分组名称不符合规则")
        MAP.put("1167", "分组的应用，至少两个")
        MAP.put("1168", "网址后缀不能为空")
        MAP.put("1169", "新号码和旧号码不能一样")
        MAP.put("1170", "手机号码错误")
        MAP.put("1171", "真实姓名不能为空")
        MAP.put("1172", "身份证号不能为空")
        MAP.put("1173", "真实姓名不匹配")
        MAP.put("1174", "身份证号不匹配")
        MAP.put("1175", "发布时间不能为空")
        MAP.put("1176", "请输入正确的发布时间")
        MAP.put("1177", "获取应用信息失败")
        MAP.put("1178", "应用类型不能为空")
        MAP.put("1179", "不能合并空白应用")
        MAP.put("1180", "应用已合并")
        MAP.put("1181", "请合并iOS应用")
        MAP.put("1182", "请合并Android应用")
        MAP.put("1183", "必须是自己的应用")
        MAP.put("1184", "不能和自己合并")
        MAP.put("1185", "图标已存在不等上传图标")
        MAP.put("1186", "应用未发布")
        MAP.put("1187", "请升级您的版本")
        MAP.put("1188", "发生错误")
        MAP.put("1189", "暂时不能更改文件")
        MAP.put("1190", "请上传文网文")
        MAP.put("1191", "请上传ICP 许可证")
        MAP.put("1192", "上传软件著作权登记证")
        MAP.put("1193", "上传营业热照")
        MAP.put("1194", "请上传金融牌照")
        MAP.put("1195", "请上传其他证件")
        MAP.put("1196", "请上传有关资质文件")
        MAP.put("1197", "请上传信息网络传播视听节目许可证")
        MAP.put("1198", "请重新上传审核不通过的文件")
        MAP.put("1199", "相关文件数目不匹配")
        MAP.put("1200", "申诉理由不能少于20个字符,不能多于500字符")
        MAP.put("1201", "申诉图片不能大于5张")
        MAP.put("1202", "不能删除所有可下载版本，如需删除应用，可在设置中删除应用")
        MAP.put("1203", "不能删除所有显示的版本")
        MAP.put("1212", "渠道短链接无效，请检查短链接是否正确")
        MAP.put("1213", "仅支持 iOS 应用证书检测")
        MAP.put("1214", "请上传 p12 文件")
        MAP.put("1215", "请上传 mobileprovision 文件")
        MAP.put("1216", "文件上传失败")
        MAP.put("1217", "文件保存失败")
        MAP.put("1218", "添加证书失败")
    }

    // 应用名称 buildName
    // 应用版本
    // 应用构建版本
    // 下载地址
    /**
     * 获取应用名称
     * @return 应用名
     */
    public String buildName() {
        if (null != this.data) {
            return this.data.buildName
        }
        return ""
    }

    /**
     * 获取应用版本
     * @return 应用版本
     */
    public String buildVersion() {
        if (null != this.data) {
            return this.data.buildVersion
        }
        return ""
    }

    /**
     * 获取应用构件版本
     * @return 应用构件版本
     */
    public String buildBuildVersion() {
        if (null != this.data) {
            return this.data.buildBuildVersion
        }
        return ""
    }

    /**
     * 获取应用下载地址
     * @return 应用下载地址
     */
    public String buildUrl() {
        if (null != this.data) {
            return "http://www.pgyer.com/" + this.data.buildShortcutUrl
        }
        return ""
    }

    /**
     * 判断是否成功
     * @return
     */
    public boolean isSuccess() {
        return this.code == "0"
    }

    /**
     * 获取失败消息
     * @return 失败信息
     */
    public String getFailedMessage() {
        def msg = MAP[this.code]
        if (null == msg || "" == msg) {
            msg = "未知异常"
        }
        return msg
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data.toString() +
                '}';
    }

    /**
     * 数据类
     */
    static class Data {
        private String buildKey
        private String buildType
        private String buildIsFirst
        private String buildIsLastest
        private String buildFileKey
        private String buildFileName
        private String buildFileSize
        private String buildName
        private String buildVersion
        private String buildVersionNo
        private String buildBuildVersion
        private String buildIdentifier
        private String buildIcon
        private String buildDescription
        private String buildUpdateDescription
        private String buildScreenshots
        private String buildShortcutUrl
        private String buildCreated
        private String buildUpdated
        private String buildQRCodeURL


        @Override
        public String toString() {
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
                    '}';
        }
    }

}