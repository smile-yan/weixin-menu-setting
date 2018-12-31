package cn.smileyan.weixin.controller;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smileyan
 * 其中appid与appsecret是读取application.yml中的数据
 */
@RestController
public class MenuController {

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appsecret}")
    private String appsecret;

    @RequestMapping("/menu")
    private String setMenu() {
        // 1.根据appid和appsecret和回调地址配置微信授权
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(appid);
        wxMpConfigStorage.setSecret(appsecret);
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);

        /**
         *  2. 设置按钮
         *  menu对象是总的按钮,button是具体的按钮
         */
        WxMenu menu = new WxMenu();
        WxMenuButton button1 = new WxMenuButton();
        button1.setType(WxConsts.MenuButtonType.VIEW);
        button1.setName("泛舟网络");
        button1.setUrl("https://www.smileyan.cn/movie/login/login");

        WxMenuButton button2 = new WxMenuButton();
        button2.setType(WxConsts.MenuButtonType.VIEW);
        button2.setName("影院");
        button2.setUrl("https://www.smileyan.cn/movie/login/welcome");

        // 3. 添加到menu
        menu.getButtons().add(button1);
        menu.getButtons().add(button2);
        String result = menu.toJson().toString();
        System.out.println(result);

        // 根据运行结果返回相应的字符串
        try {
            wxMpService.getMenuService().menuCreate(result);
            return "SUCCESS"+" "+result;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "FAILURE"+result;
    }
}
