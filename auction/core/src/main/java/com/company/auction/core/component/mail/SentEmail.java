package com.company.auction.core.component.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentEmail {

    private static final Logger logger = LoggerFactory.getLogger(SentEmail.class);
    private static final Properties mailConfig;
    private  MailSenderInfo mailInfo;
       static {
           mailConfig = new Properties();
           try (InputStream in = SentEmail.class.getResourceAsStream("mail-config.properties")) {
               mailConfig.load(in);
           } catch (IOException ioe) {
               logger.warn("Failed to load error configuration file", ioe);
           }
       }

    public SentEmail(){
        mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost( mailConfig.getProperty("mail.smtp.host"));
        mailInfo.setMailServerPort(mailConfig.getProperty("mail.smtp.port"));
        mailInfo.setValidate(true);
        mailInfo.setUserName(mailConfig.getProperty("mail"));
        mailInfo.setPassword(mailConfig.getProperty("password"));//您的邮箱密码
        mailInfo.setFromAddress(mailConfig.getProperty("mail"));
    }

    /**
     * 根据传入的文件路径创建附件并返回
     */
    public static MimeBodyPart createAttachment(String fileName) throws Exception {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(fileName);
        attachmentPart.setDataHandler(new DataHandler(fds));
        attachmentPart.setFileName(fds.getName());
        return attachmentPart;
    }

//    public static void sentEmail(String fromAddress, String password, String toAddress, String filePath) throws Exception {
//        MailSenderInfo mailInfo = new MailSenderInfo();
//        mailInfo.setMailServerHost("smtp.qq.com");
//        mailInfo.setMailServerPort("25");
//        mailInfo.setValidate(true);
//        mailInfo.setUserName(fromAddress);
//        mailInfo.setPassword(password);//您的邮箱密码
//        mailInfo.setFromAddress(fromAddress);
//        mailInfo.setToAddress(toAddress);
//        mailInfo.setSubject("安悦e贸通产品简介");
//        mailInfo.setContent("<p>尊敬的用户您好：</p><p>欢迎您关注安悦e贸通。请到邮件附件中查看您关注的产品信息。"
//            + "更多产品和服务，请登录安悦e贸通电商平台网站：www.anyomall.com</p><p>安悦e贸通市场部</p>"
//            + "<p>上海市嘉定区博乐南路125号</p><p>热线电话：4008 215 292</p>"
//            + "<p>传真：021-59161125</p>");
//        MimeBodyPart attachment = createAttachment(filePath);
//        mailInfo.setAttachment(attachment);
//        SimpleMailSender sms = new SimpleMailSender();
//        //   sms.sendTextMail(mailInfo);//发送文体格式
//        sms.sendHtmlMail(mailInfo); //发送html格式
//    }

    public boolean sent(String toAddress,String subject, String content) throws Exception {

        mailInfo.setToAddress(toAddress);
        mailInfo.setSubject(subject);
        mailInfo.setContent(content);
        SimpleMailSender sms = new SimpleMailSender();
        //   sms.sendTextMail(mailInfo);//发送文体格式
        return sms.sendMailHtml(mailInfo); //发送html格式   ,无文件
    }

    public static void main(String[] args) throws Exception {
        //这个类主要是设置邮件
//        CaseManage case_ =new CaseManage();
//        case_.setBirthday(new Date());
//        case_.setCreatetime(new Date());
//
        //SentEmailUtils.sentEmailNullFile("735181886@qq.com", "test","This is a Test");

    }
}
