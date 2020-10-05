package com.geekmake.plugin.action.jvm.enums;

/**
 * @author pez1420@gmail.com
 * @version $Id: JstatCommandEnum.java v 0.1 2020/9/23 5:24 下午 pez1420 Exp $$
 */
public enum JstatCommandEnum {

    JSTAT_GC("jstat -gc 3831 5000", "每5秒一次显示jvmGC情况"),
    JSTAT_GCUTIL("jstat -gcutil 3831 5000", "每5秒一次显示jvmGC情况");


    private String code;

    private String msg;

    JstatCommandEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Getter for property 'code'.
     *
     * @return code Value for property 'code'.
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for property 'msg'.
     *
     * @return msg Value for property 'msg'.
     */
    public String getMsg() {
        return msg;
    }
}
