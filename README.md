# GradlePlugin

#### 介绍
 Gradle 工具插件
- pgyer 蒲公英上传插件

#### 使用说明

1. 在项目根目录下的 build.gradle 添加依赖

```
// 自定义插件
classpath 'com.mazaiting.plugin:pgyer:<版本号>'
```

2. 在需要上传包的模块下添加

```
apply plugin: 'com.mazaiting'
```

3. 在需要上传包的模块下添加配置

```
pgyer {
    apiKey '' // 蒲公英中的 API_KEY(必填)
    file 'apk/15_11_30_卡托交接_uat.apk' // 文件路径(必填) 需要上传的ipa或者apk文件
    installType '2'	// (必填)应用安装方式，值为(2,3)。2：密码安装，3：邀请安装
    password '123456' // (必填) 设置App安装密码
    updateDescription '1. 更新日志' // (选填) 版本更新描述，请传空字符串，或不传。
    name '应用名称' // (选填) 应用名称
    installDate '2' // (选填)是否设置安装有效期，值为：1 设置有效时间， 2 长期有效，如果不填写不修改上一次的设置
    installStartDate '2018-01-01' // (选填)安装有效期开始时间，字符串型，如：2018-01-01
    installEndDate '2018-12-31' // (选填)安装有效期结束时间，字符串型，如：2018-12-31
    channelShortcut 'abcd' // (选填)所需更新的指定渠道的下载短链接，只可指定一个渠道，字符串型，如：abcd
}
```

4. 执行上传命令

```
./gradlew uploadPgyer
```

### 版本

- v1.0.0
1. 完成蒲公英命令行上传

#### Contribution

1. [简书地址](https://www.jianshu.com/u/5d2cb4bfeb15)
2. [码云地址](https://gitee.com/)
3. [邮箱](mailto:zaitingma@foxmail.com)
4. [新浪微博](http://blog.sina.com.cn/mazaiting)
