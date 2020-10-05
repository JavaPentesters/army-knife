package com.geekmake.plugin.action.jvm.enums;

/**
 * @author pez1420@gmail.com
 * @version $Id: ArthasTtCommandEnum.java v 0.1 2020/9/29 11:11 上午 pez1420 Exp $$
 */
public enum ArthasTtCommandEnum {


    TT_I_INDEX("tt -i 1003", "-i 参数后边跟着对应的 INDEX 编号查看详细信息"),
    TT_PLAY_I("tt --play -i 1000", "tt --play重放编号为1000请求");


    private String code;

    private String msg;

    ArthasTtCommandEnum(String code, String msg) {
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
