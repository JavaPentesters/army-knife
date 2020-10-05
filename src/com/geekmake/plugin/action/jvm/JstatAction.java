package com.geekmake.plugin.action.jvm;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.JstatCommandDialog;

/**
 *         String command = "jstat -gc pid";
 *         ClipboardUtils.setClipboardContent(command);
 *         NotificationUtils.showMessage();
 *         
 * @author pez1420@gmail.com
 * @version $Id: JstatAction.java v 0.1 2020/3/27 1:19 上午 pez1420 Exp $$
 */
public class JstatAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent e) {
        new JstatCommandDialog(e.getProject()).open("jstat 常用命令");
    }
}
