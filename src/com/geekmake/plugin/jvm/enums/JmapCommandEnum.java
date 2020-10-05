package com.geekmake.plugin.jvm.enums;

/**
 * @author pez1420@gmail.com
 * @version $Id: JvmCommandEnum.java v 0.1 2020/9/23 4:08 下午 pez1420 Exp $$
 */
public enum JmapCommandEnum {
    JMAP_HEAP("jmap -heap 3831", "显示Java堆详细信息"),
    JMAP_HISTO_1("jmap -histo[:live] 3831 | sort -k 2 -g -r", "查看对象数最多的对象，按降序输出"),
    JMAP_HISTO_2("jmap -histo[:live] 3831 | sort -k 3 -g -r", "查看内存的对象，按降序输出"),
    JMAP_DUMP(" jmap -dump:format=b,file=dump.hprof 3831", "Dump jvm当前内存中的情况到文件");


    private String code;

    private String msg;

    JmapCommandEnum(String code, String msg) {
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
