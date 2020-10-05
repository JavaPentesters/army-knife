package com.geekmake.plugin.test;

import java.io.Serializable;

/**
 * @author pez1420@gmail.com
 * @version $Id: InputDO.java v 0.1 2020/3/30 7:05 下午 pez1420 Exp $$
 */
public class InputDO implements Serializable {

    public static final String NAME = "STATIC";

    private String             username;

    private String             password;

    /**
     * Getter for property 'username'.
     *
     * @return username Value for property 'username'.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for property 'username'.
     *
     * @param username Value to set for property 'username'.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for property 'password'.
     *
     * @return password Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
