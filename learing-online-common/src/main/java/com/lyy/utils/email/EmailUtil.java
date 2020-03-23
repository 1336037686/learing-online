package com.lyy.utils.email;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lyy.utils.MathUtil;
import com.lyy.utils.sms.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 邮件操作工具类
 * @author LGX_TvT
 * @date 2019-11-28 16:00
 */
@Slf4j
@Component
public class EmailUtil {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private EmailParam emailParam;

    /**
     * 邮件验证发送
     * @param address
     * @return 验证码
     */
    public String sendEmail(String address) {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        //下面填写密钥
        IClientProfile profile = DefaultProfile.getProfile(emailParam.getRegionId(), emailParam.getAccessKeyId(), emailParam.getAccessKeySelect());
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        //生成验证码
        String code = MathUtil.vcode(emailParam.getEmailCodeLength());
        //使用https加密连接
        request.setProtocol(com.aliyuncs.http.ProtocolType.HTTPS);
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName(emailParam.getAccountName());
            //发信人昵称
            request.setFromAlias(emailParam.getFromAlias());
            request.setAddressType(1);
            //控制台创建的标签
            request.setTagName(emailParam.getTagName());
            //是否需要回信功能
            request.setReplyToAddress(emailParam.getToAddress());
            //request.setToAddress("邮箱1,邮箱2");
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            request.setToAddress(address);
            //邮件主题
            request.setSubject(emailParam.getSubject());
            //邮件正文,使用{{code}}作为占位符
            request.setHtmlBody(emailParam.getEmailHtmlBody().replace("{{code}}", code));
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            return code;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 这个直接发tips里的内容
     * 如果想发验证码就调用一下SmsUtil.vcode()就能获得验证码然后作为tips发送
     * 也可以自定义tips里的格式然后发送
     * 如果有什么固定格式的邮件只有个别变量的那种可以和我说，我写模板套变量就好
     * @param address 发送地址
     * @param tips 发送内容
     * @return 成功发送后我会返回tips内容，如果没有就返回空
     */
    public String save(String address,String tips) {
        IClientProfile profile = DefaultProfile.getProfile(emailParam.getRegionId(), emailParam.getAccessKeyId(), emailParam.getAccessKeySelect());
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setProtocol(ProtocolType.HTTPS);

        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName(emailParam.getAccountName());
            //发信人昵称
            request.setFromAlias(emailParam.getFromAlias());
            request.setAddressType(1);
            //控制台创建的标签
            request.setTagName(emailParam.getTagName());
            //是否需要回信功能
            request.setReplyToAddress(emailParam.getToAddress());
            //request.setToAddress("邮箱1,邮箱2");
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            request.setToAddress(address);
            //邮件主题
            request.setSubject(emailParam.getSubject());
            request.setHtmlBody(tips);
            SingleSendMailResponse httpResponse = (SingleSendMailResponse)client.getAcsResponse(request);
            return tips;
        } catch (ServerException var6) {
            var6.printStackTrace();
        } catch (ClientException var7) {
            var7.printStackTrace();
        }
        return null;
    }


}
