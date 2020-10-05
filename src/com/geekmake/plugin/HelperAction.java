package com.geekmake.plugin;

import org.jetbrains.annotations.NotNull;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author pez1420@gmail.com
 * @version $Id: HelperAction.java v 0.1 2020/9/24 11:00 上午 pez1420 Exp $$
 */
public class HelperAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        BrowserUtil.browse("http://www.geek-make.com");
    }
}
