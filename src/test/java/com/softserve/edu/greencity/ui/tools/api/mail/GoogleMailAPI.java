package com.softserve.edu.greencity.ui.tools.api.mail;

import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;
import com.sun.mail.imap.protocol.FLAGS;
import com.sun.mail.imap.protocol.UIDSet;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class use gmail api to be and take 1st unread msg to be continue
 */
public class GoogleMailAPI {
    private static BaseMailAPI emailUtils;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @SneakyThrows(Exception.class)
    @Step
    public  GoogleMailAPI connectToEmail(String mail, String pass) {
        emailUtils = new BaseMailAPI(mail, pass, "smtp.gmail.com", BaseMailAPI.EmailFolder.INBOX);
        return this;
    }

    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public Message[] getMassagesBySubject(String subject, boolean unread, int maxToSearch, long timeToWait){
        waitFroMassagesWithSubject(subject,unread,maxToSearch,timeToWait);
        return emailUtils.getMessagesBySubject(subject, unread,  maxToSearch);
    }

    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public Message[] getMassagesBySubject(String subject, boolean unread, int maxToSearch){
        return emailUtils.getMessagesBySubject(subject, unread,  maxToSearch);
    }

    @SneakyThrows
    @Step("get Messages By Subject")
    public  void clearMail(String mail, String pass) {
        connectToEmail(mail,pass);
        Message[] msg = emailUtils.getAllMessages();
        for (Message message :msg) {
            message.setFlag(FLAGS.Flag.DELETED, true);
        }
    }

    //TODO: split logic to small methods,
    //TODO: split Matcher to another class as individual functional
    @Step("get green city auth confirm link from first mail")
    @SneakyThrows(Exception.class)
    public String getconfirmURL(String mail, String pass,int maxTries) {
        connectToEmail(mail,pass);
        waitFroMassagesWithSubject("Verify your email address",true,5,10);
        String link = "";
        int count = 0;
        while (true) {
            Message[] email = emailUtils.getMessagesBySubject("Verify your email address", true, 5);
            String mailContent = emailUtils.getMessageContent(email[0]).trim().replaceAll("\\s+", "");
            Pattern pattern = Pattern.compile("https://greencity[^\"]+");
            final Matcher m = pattern.matcher(mailContent);
            m.find();
            link = mailContent.substring( m.start(), m.end() )
                    .replace("3D","")
                    .replace("amp;","")
                    .replace("=","")
                    .replace("token","token=")
                    .replace("user_id","user_id=");
            if (++count == maxTries) {
                return null;
            }
            return link;
        }
    }

    @Step("get green city auth confirm link from first mail")
    @SneakyThrows(Exception.class)
    public String getconfirmURL(String subject, String mail, String pass,String regex) {
        connectToEmail(mail,pass);
        waitFroMassagesWithSubject(subject,true,5,10);
        String link = "";
            Message[] email = emailUtils.getMessagesBySubject("Verify your email address", true, 5);
            String mailContent = emailUtils.getMessageContent(email[0]).trim().replaceAll("\\s+", "");
            Pattern pattern = Pattern.compile(regex);
            final Matcher m = pattern.matcher(mailContent);
            m.find();
            link = mailContent.substring( m.start(), m.end() )
                    .replace("3D","")
                    .replace("amp;","")
                    .replace("=","")
                    .replace("token","token=")
                    .replace("user_id","user_id=");
            return link;
    }

    @SneakyThrows
    public int getNumberMailsBySubject(String mail, String password, String subject, int maxTries) {
        connectToEmail(mail, password);
            Message[] emails = emailUtils.getMessagesBySubject(subject, true, 5);
            return  emails.length;
    }
    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public void waitFroMassagesWithSubject(String subject, boolean unread, int maxToSearch, long timeToWait){
        logger.info("Wait for email with subject" + subject);
        long start = System.nanoTime()/ 1000000000;
        long end = start + ((long) timeToWait);
        while (true) {
           int a = emailUtils.getMessagesBySubject(subject, unread,  maxToSearch).length ;
            if (a > 0
            || System.nanoTime()/1000000000 - end == 0 )
            {logger.info("emails with subject founds: " + a);
            break;
            }
        }
    }

    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public void waitForMassagesWithSubject(String subject, boolean unread, int maxToSearch, long timeToWait,String email, String emailPassword){
        logger.info("Wait for email with subject: " + subject);
        long start = System.nanoTime()/ 1000000000;
        long end = start + ((long) timeToWait);
        connectToEmail(email, emailPassword);
        while (true) {
            int emailCount = emailUtils.getMessagesBySubject(subject, unread,  maxToSearch).length ;
            if (emailCount > 0
                    || System.nanoTime()/1000000000 - end == 0 )
            {logger.info("emails with " + subject +" founds: " + emailCount);
                break;
            }
        }

    }
}

