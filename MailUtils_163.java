package com.itheima.store.utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils_163 {

	private static String userName = "发件人邮箱账号";//发 送 邮 件的1 6 3邮 箱 账 号  
    private static String password = "发件人邮箱密码";//1 6 3 邮 箱 的 授 权 码，若 没 有 则 使 用 密 码  
    private static String userName2 = "收件人邮箱账号";//接 收 邮 件 的 1 6 3 邮 箱 账 号  
    private static String port = "25";//端 口 号  
    /** 
     * 该 方 法 用 来 发 送 邮 件 
     * @param to:给 谁 发 邮 件 
     * **/  
    public static void sendMain(String to) throws AddressException, MessagingException, GeneralSecurityException{  
        //1、创 建 连 接 对 象，连 接 到 邮 箱 服 务 器  
        Properties props = new Properties();  
        //开 启debug调试    
        props.setProperty("mail.debug", "true");    
        //stmp服务器需要进行身份验证，也就是有 户 名和密 码的校验，这样才能通过验证  
        props.setProperty("mail.smtp.auth", "true");   
        //发送邮件协议名称    
        props.setProperty("mail.transport.protocol", "smtp");    
        //设置邮件服务器主机名    
        props.setProperty("mail.host", "smtp.163.com");//设置成163的发件服务器  
          
        //设端口号(该配置可写可不写)  
        props.setProperty("mail.smtp.port", port);  
          
        //密 码、授 权 码  
        props.setProperty("mail.smtp.password",password);  
          
        //Authenticator:认证信息  
        Session session = Session.getInstance(props, new Authenticator(){  
            @Override  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(userName,password);//使用它给其他账户发邮件  
            }  
        });  
          
        //2、创建邮件对象  
        Message message = new MimeMessage(session);  
        //2.1设置发件人  
        message.setFrom(new InternetAddress(userName));  
        //2、2设置收件人  
        message.setRecipient(RecipientType.TO, new InternetAddress(to));  
        //2.3邮件的主题  
        message.setSubject("测试发消息");  
        //2.4邮件的正文（即邮件的内容）  
        message.setContent("2018-1-30:测试邮件:javaMail-网 易 邮 箱测试","text/html;charset=utf-8");  
          
        //3.发送邮 件  
        Transport trans = session.getTransport();  
        //连接邮 件服务器  
        trans.connect(userName, password);    
        //发送邮 件  
        trans.sendMessage(message, message.getAllRecipients());    
        //关 闭连接  
        trans.close();  
          
        //Transport.send(message);(两种方式都可以)  
        System.out.println("发送成功");  
    }  
      
    public static void main(String[] args) {  
        try {  
            sendMain(userName2);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
    }  
	
}
