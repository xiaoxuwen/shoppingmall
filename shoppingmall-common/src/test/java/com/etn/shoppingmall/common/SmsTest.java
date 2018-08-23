package com.etn.shoppingmall.common;

import com.etn.shoppingmall.common.notify.ShoppingMallNotifyService;
import com.etn.shoppingmall.common.notify.util.ConfigUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 测试短信发送服务
 * <p>
 * 注意LitemallNotifyService采用异步线程操作
 * 因此测试的时候需要睡眠一会儿，保证任务执行
 * <p>
 * 开发者需要确保：
 * 1. 在腾讯云短信平台设置短信签名和短信模板notify.properties已经设置正确
 * 2. 在腾讯云短信平台设置短信签名和短信模板
 * 3. 在当前测试类设置好正确的手机号码
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SmsTest {

    @Autowired
    private ShoppingMallNotifyService shoppingMallNotifyService;

    @Test
    public void testCaptcha() {
        String phone = "15083386023";
        String[] params = new String[]{"123456"};

        String str = shoppingMallNotifyService.notifySMSTemplate(phone, ConfigUtil.NotifyType.CAPTCHA, params);

        System.out.println(str);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaySucceed() {
        String phone = "xxxxxxxxxxx";
        String[] params = new String[]{"123456"};

        shoppingMallNotifyService.notifySMSTemplate(phone, ConfigUtil.NotifyType.PAY_SUCCEED, params);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
