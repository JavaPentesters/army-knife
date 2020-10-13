package com.geekmake.plugin.action.http.enums;

/**
 * @author pez1420@gmail.com
 * @version $Id: FormatEnum.java v 0.1 2020/10/10 4:42 下午 pez1420 Exp $$
 */
public enum FormatEnum {

                        JSON("json"), XML("xml");

    FormatEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
