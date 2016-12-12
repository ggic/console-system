package com.snoweagle.console.rest.constants;

/**
 * Created by wangshaobo on 2015/10/20.
 */
public enum ResponseStatus {

    SUCCESS("SUCCESS", "成功"), FAILURE("FAILURE", "失败");
    // 成员变量
    private String code;
    private String name;

    // 构造方法
    private ResponseStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.code + "_" + this.name;
    }
}

