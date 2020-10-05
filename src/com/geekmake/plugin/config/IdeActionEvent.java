package com.geekmake.plugin.config;

/**
 * @author pez1420@gmail.com
 * @version $Id: ActionEvent.java v 0.1 2020/3/26 9:16 下午 pez1420 Exp $$
 */
public class IdeActionEvent extends BaseEvent {

    /**
     * 当前光标对应的类名
     */
    private String className;

    /**
     * 当前光标对应的方法名
     */
    private String methodName;

    /**
     * Getter for property 'className'.
     *
     * @return className Value for property 'className'.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter for property 'className'.
     *
     * @param className Value to set for property 'className'.
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Getter for property 'methodName'.
     *
     * @return methodName Value for property 'methodName'.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter for property 'methodName'.
     *
     * @param methodName Value to set for property 'methodName'.
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
