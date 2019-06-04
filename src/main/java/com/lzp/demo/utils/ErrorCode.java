package com.lzp.demo.utils;

/**
 * @Author：lzp
 * @Description：
 * @Date：2019-06-04
 */
public interface ErrorCode {


    int LOGIN_ERROR = 101;//登录失败

    int RIGISTER_ERROR_TEL_ERROR = 102;//注册失败--手机

    int RIGISTER_ERROR_EMAIL_ERROR = 103;//注册失败--邮箱

    int SEND_EMAIL_ERROR = 104;//发送邮件失败

    int MSG_CODE_ISFAIL = 105;//验证码不匹配

    int INFO_iS_FAIL = 106;//信息不完整

}
