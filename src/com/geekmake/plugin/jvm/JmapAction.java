package com.geekmake.plugin.jvm;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.JmapCommandDialog;

/**
 * @author pez1420@gmail.com
 * @version $Id: JdumpAction.java v 0.1 2020/3/27 1:16 上午 pez1420 Exp $$
 */
public class JmapAction extends BaseAnAction {

    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new JmapCommandDialog(actionEvent.getProject()).open("jmap 常用命令");
    }
}
