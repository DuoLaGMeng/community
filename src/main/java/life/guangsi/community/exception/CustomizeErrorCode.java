package life.guangsi.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换个试试~"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "未登录无法进行评论，请先登录！"),
    SYS_ERROR(2004,"服务冒烟了，要不你稍后再试试！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误，或不存在"),
    COMMENT_NOT_FOUND(2006,"评论不在了，请换个试试！"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"不可以读取别人的信息哦！"),
    NOTIFICATION_NOT_FOUND(2009,"你读的评论没有找到！")
    ;


    private Integer code;

    private String message;



    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
