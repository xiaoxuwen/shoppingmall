package com.etn.shoppingmall.common.notify;

import com.etn.shoppingmall.common.notify.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 商城通知服务类
 */
@Service("shoppingMallNotifyService")
public class ShoppingMallNotifyService {
    @Autowired
    private MailSendService mailSendService;
    @Autowired
    private SMSSendService smsSendService;
    @Autowired
    private WXTemplateSendService wxTemplateSendService;

    /**
     * 微信模版消息通知
     *
     * @param token      通过wxMAService获取token或者通过url请求token
     * @param touser     接收者openId
     * @param formId     表单ID或者 prepayId
     * @param notifyType 通知类别，通过该枚举值在配置文件中获取相应的模版ID
     * @param params     通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
     */
    @Async("notifyAsync")
    public void notifyWXTemplate(String token, String touser, String formId, ConfigUtil.NotifyType notifyType, String[] params) {
        if (!wxTemplateSendService.config.isEnable())
            return;

        String templateId = ConfigUtil.getTemplateId(notifyType, wxTemplateSendService.config.getTemplate());

        if (templateId != "")
            wxTemplateSendService.sendWechatMsg(token, touser, templateId, formId, "", "", params);
    }

    /**
     * 短信模版通知
     *
     * @param phoneNumber 接收通知的电话号码
     * @param notifyType  通知类别，通过该枚举值在配置文件中获取相应的模版ID
     * @param params      通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
     */
    public String notifySMSTemplate(String phoneNumber, ConfigUtil.NotifyType notifyType, String[] params) {
        return smsSendService.sendSMSWithTemplate(phoneNumber, ConfigUtil.getTemplateId(notifyType, smsSendService.config.getTemplate()), params[0]);
    }


    /**
     * 发送邮件通知,接收者在spring.mail.sendto中指定
     *
     * @param setSubject 邮件标题
     * @param setText    邮件内容
     */
    @Async("notifyAsync")
    public void notifyMailMessage(String setSubject, String setText) {
        if (!mailSendService.config.isEnable())
            return;

        mailSendService.sendEmail(setSubject, setText);
    }
}
