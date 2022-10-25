package com.example.infits;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.infits.Config;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMail{
    private Context context;
    private Session session;
    private String email;
    private String message;
    private ProgressDialog progressDialog;
    public SendMail(Context context, String email,  String message){
        System.out.println("Hi");
        this.context = context;
        this.email = email;
        this.message = message;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kasim102017@gmail.com","qwertyuiop@1212");
            }
        });
        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress("muskan2000.24@gmail.com"));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress("muskan2000.24@gmail.com"));
            mm.setSubject("Order");
            mm.setText("message");
            Transport.send(mm);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}