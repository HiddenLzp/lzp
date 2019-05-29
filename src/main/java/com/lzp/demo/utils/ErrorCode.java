package com.lzp.demo.utils;

/**
 * @Author：罗皖西
 * @Description：
 * @Date：2019/1/11
 */
public interface ErrorCode {

    int SERVER_ERROR = 99;

    int UNKNOWN_ERROR = 100; // 未知错误

    int SAVE_ERROR = 101; //保存失败

    int NO_CORRECTQUESTION = 102; //无订正成功题目

    int NOT_EXISTS = 103; //信息不存在

    int SEND_ERROR = 104; //赠送好友积分失败

    int LOGIN_OVERDUE = 105; //登录已过期

    int UNAUTHORIZED_ACCESS = 106; //非法访问

    int REMOVE_ERROR = 107; //删除失败

    int STUDENT_NOT_EXISTS = 108; //该同学不存在

    int IS_ALREADY_FRIENDS = 109; //该同学已经是你的好友

    int REGISTER_ERROR = 110; //注册失败

    int PARAM_IS_NULL = 111; //参数为空

    int NOT_ARRANGE_MISS_LESSON = 112; //未安排补课

    int UPLOAD_RESULT_ERROR = 113; //上传结果失败

    int NOT_JOIN_CLASSES = 114; //未加入班级

    int IS_ALREADY_REFUSED = 115; //已拒绝好友添加申请

    int AGREED_OR_REFUSED = 116; //已经是好友或已被拒绝

    int MODIFY_PWD_ERROR = 117; //修改密码失败

    int PWD_ERROR = 118; //密码错误

    int LOGIN_ERROR = 119; //登录失败

    int NOT_ARRANGE_MAKE_MISS_LESSON = 120; //未安排补课

    int ALERADY_PUBLISH_MAKE_MISS_LESSON = 121; //已安排补课

    int MODIFY_HEADIMG_ERROR = 122; //头像修改失败

    int NOT_REMOVE_SAME_CLASS_FRIEND = 123; //不能删除同班级好友

    int TYPE_NOT_MATCH = 124; //学生类型不匹配，无法登录

}
