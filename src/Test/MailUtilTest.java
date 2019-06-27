package Test;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import org.junit.Test;

import javax.mail.Session;

/*
测试发邮件功能
底层依赖的是：javamail:mail.jar activation.jar
 */
public class MailUtilTest  {
    /*
    发邮件
     */
    @Test
    public void testSend() throws  Exception{
        /*
        1登录邮件服务器
        2创建邮件对象
        发件人
        收件人
        主题
        正文
        3发送
           session
           邮件对象
         */

        Session session = MailUtils.createSession("smtp.qq.com", "1742049180@qq.com", "aizcntbtjttthjcj");
        Mail mail = new Mail("1742049180@qq.com", "759805810@qq.com", "测试邮件一封", "查收test");
        MailUtils.send(session,mail);

    }
}
