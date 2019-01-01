# weixin-menu-setting
微信自定义菜单的设置，特别适合用于测试号的自定义菜单。
使用IDEA编写的，weixin-java-mp的版本号为3.3.0
推荐使用IDEA直接打开整个项目。

另外如果对按钮有其他需要，请参考weixin-java-mp提供的例子，WxMenuController.java(直接从官网的例子中copy过来的)。

如果有其他问题，欢迎交流。

#使用教程
1. 使用Idea打开这个项目，等项目Maven导入所有jar包。
2. 配置测试号。在application.yml文件中修改appid和appsecret。
3. 配置测试号按钮。在MenuController.java中修改java内容配置按钮。
4. 运行项目。
5. 访问localhost/menu 查看返回结果。
