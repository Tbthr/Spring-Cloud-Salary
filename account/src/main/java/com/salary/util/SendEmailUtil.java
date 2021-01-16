package com.salary.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

public class SendEmailUtil {
    public static HashMap<String, String> codeMap = new HashMap<>();

    public static String send(String addr) throws MessagingException, GeneralSecurityException {
        //创建一个配置文件并保存
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");

        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1976907019@qq.com", "hfojplngsmgndhea");
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();
        //连接服务器
        transport.connect("smtp.qq.com", "1965907019@qq.com", "hfojplngsmgndhea");

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("1965907019@qq.com"));
        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(addr));
        //邮件标题
        mimeMessage.setSubject("某大学工资管理系统");
        //邮件内容
        String code = getCode();
        String mes = "您的验证码是：" + code + "。请于10分钟内填写。如非本人操作，请忽略本条邮件！";
        mimeMessage.setContent(mes, "text/html;charset=UTF-8");
        //发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
        return code;
    }

    private static String getCode() {
        Random random = new Random(System.currentTimeMillis());
        String s = "123TMXCVBNt4sdfASDFGHJyug56kYU7iOPKLopwcvbnm678zxQW3Z2l7890erjIah18ER1290q34545690";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int p = random.nextInt(72);
            code.append(s.charAt(p));
        }
        return code.toString();
    }
}