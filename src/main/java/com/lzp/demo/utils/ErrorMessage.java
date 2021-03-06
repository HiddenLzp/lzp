package com.lzp.demo.utils;

/**
 * @Author：lzp
 * @Description：
 * @Date：2019-06-04
 */
public interface ErrorMessage {

    String LOGIN_ERROR = "账号或密码错误";

    String RIGISTER_ERROR_TEL = "该手机已被注册！";

    String RIGISTER_ERROR_EMAIL = "该邮箱已被注册！";

    String SEND_EMAIL_ERROR = "邮件发送失败!";

    String MSG_CODE_ISFAIL = "验证码不匹配";

    String INFO_iS_FAIL = "信息不完整!";

    String LOGIN_TIMEOUT = "登录已失效";

    String SERVER_ERROR = "服务器内部错误";
}
