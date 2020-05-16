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