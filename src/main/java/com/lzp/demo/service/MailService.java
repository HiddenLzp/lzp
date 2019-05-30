package com.lzp.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * @Author LiZePing
 * @date2019/5/30 8:20
 */
@Service
public class MailService {

    //默认编码
    public static final String DEAULT_ENCODING = "UTF-8";

    //记录日志
    private Logger logger = LoggerFactory.getLogger(MailService.class);

    //本身邮件发送者
    @Value("${spring.mail.username}")
    private String userName;

    //模板引擎解析对象，用于解析模板
    @Resource
    private TemplateEngine templateEngine;

    //邮件发送的对象，用于邮件发送
    @Resource
    private JavaMailSender mailSender;

    /**
     *  发送一个简单的文本邮件，可以附带附件:文本邮件发送的基本方法
     * @param emailTheme 邮件主题，即邮件的邮件名称
     * @param content 邮件内容
     * @param toWho 需要发送的人
     * @param ccPeoples 需要抄送的人
     * @param bccPeoples 需要密送的人
     * @param attachments 需要附带的附件，附件请保证一定要存在，否则将会被忽略掉
     */
    public void sendSimpleTextMailActual(String emailTheme,String content,String[] toWho,String[] ccPeoples,String[] bccPeoples,String[] attachments){

        if(emailTheme == null || toWho == null || toWho.length == 0 || content == null){
            logger.error("邮件-> {} 无法继续执行，因为缺少基本的参数：邮件主题、收件人、邮件内容",emailTheme);

            throw new RuntimeException("模板邮件无法继续发送，因为缺少必要的参数！");
        }

        logger.info("开始发送简单文本邮件：主题->{}，收件人->{}，抄送人->{}，密送人->{}，附件->{}",emailTheme,toWho,ccPeoples,bccPeoples,attachments);

        if(attachments != null && attachments.length > 0){

            try {
                //附件处理需要进行二进制传输
                MimeMessage mimeMessage = mailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,DEAULT_ENCODING);

                //设置邮件的基本信息：这些函数都会在后面列出
                boolean basicInfo = handlerBasicInfo(helper, emailTheme, content, toWho, ccPeoples, bccPeoples);
                if( !basicInfo ){
                    logger.error("邮件基本信息出错:主题->{}",emailTheme);
                    return;
                }
                //处理邮件
                handleAttachment(helper,emailTheme,attachments);

                //发送邮件
                mailSender.send(mimeMessage);

            }catch (Exception e){
                e.printStackTrace();

                logger.error("发送邮件失败: 主题->{}",emailTheme);
            }

        }else {
            //创建一个简单的邮件信息对象
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置邮件的基本信息
            handlerBasicInfo(simpleMailMessage,emailTheme,content,toWho,ccPeoples,bccPeoples);
            //发送邮件
            mailSender.send(simpleMailMessage);
            logger.info("发送邮件成功: 主题->{}",emailTheme,toWho,ccPeoples,bccPeoples,attachments);
        }
    }


    /**
     *  处理二进制邮件的基本信息，比如需要带附件的文本邮件，HTML文件，图片邮件，模板邮件等
     * @param helper 为禁止文件的包装类
     * @param emailTheme 邮件主题
     * @param content 邮件内容
     * @param toWho 邮件收件人
     * @param ccPeoples 抄送人
     * @param bccPeoples 密送人
     * @return
     */
    private boolean handlerBasicInfo(MimeMessageHelper helper,String emailTheme,String content,String[] toWho,String[] ccPeoples,String[] bccPeoples){
         try{
             //设置必要的邮件元素
             //设置发件人
             helper.setFrom(userName);

             helper.setSubject(emailTheme);
             helper.setText(content);
             helper.setTo(toWho);

             if(ccPeoples != null){
                //设置邮件的抄送人  MimeMessageHelper # Assert.notNull(cc, "Cc address array must not be null");
                 helper.setCc(ccPeoples);
             }
             if(bccPeoples != null){
                //密送人  MimeMessageHelper # Assert.notNull(bcc, "Bcc address array must not be null");
                 helper.setBcc(bccPeoples);
             }
             return true;
         } catch (MessagingException e) {
             e.printStackTrace();
             logger.error("邮件基本信息出错->{}",emailTheme);
         }
         return false;

    }

    /**
     * 用于填充简单文本邮件的基本嘻嘻
     * @param simpleMailMessage 文本邮件信息对象
     * @param emailTheme 邮件主题
     * @param content 邮件内容
     * @param toWho 收件人
     * @param ccPeoples 抄送人
     * @param bccPeoples 密送人
     */
    private void handlerBasicInfo(SimpleMailMessage simpleMailMessage,String emailTheme,String content,String[] toWho,String[] ccPeoples,String[] bccPeoples){
        //设置发件人
        simpleMailMessage.setFrom(userName);
        //设置邮件主题
        simpleMailMessage.setSubject(emailTheme);
        //设置邮件内容
        simpleMailMessage.setText(content);
        //设置邮件的收件人
        simpleMailMessage.setTo(toWho);
        //设置邮件的抄送人
        simpleMailMessage.setCc(ccPeoples);
        //设置邮件的密送人
        simpleMailMessage.setBcc(bccPeoples);

    }

    /**
     *  用于处理附件信息，附件需要MimeMessage对象
     * @param mimeMessageHelper 处理附件信息的对象
     * @param emailTheme 邮件的主题，用于日志记录
     * @param attachmentFilePaths 附件文件的路径，改路径要求可以定位到本机的一个资源
     */
    private void handleAttachment(MimeMessageHelper mimeMessageHelper,String emailTheme,String[] attachmentFilePaths){
        //判断是否需要处理邮件的附件
        if(attachmentFilePaths != null && attachmentFilePaths.length > 0){

            FileSystemResource resource;
            String fileName;

            for (String attachmentFilePath : attachmentFilePaths) {
                //获取该路径所对应的而文件资源对象
                resource = new FileSystemResource(new File(attachmentFilePath));
                //判断该资源是否存在，当不存在是仅仅回答引一条警告日志，不会中断处理程序。
                //也就是说在附件出现异常的情况下，邮件是可以正常发送的，所以请你确定你发送的邮件附件在本机存在
                if(!resource.exists()){

                    logger.warn("邮件->{}的附件->{}不存在",emailTheme,attachmentFilePath);
                    continue;
                }

                fileName = resource.getFilename();

                try{
                    //添加附件
                    mimeMessageHelper.addAttachment(fileName,resource);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    logger.error("邮件->{}添加附件->{}出现异常->出现异常->{}",emailTheme,attachmentFilePath,e.getMessage());
                }
            }
        }
    }


}
