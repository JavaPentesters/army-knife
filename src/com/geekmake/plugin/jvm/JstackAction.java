package com.geekmake.plugin.jvm;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;

/**
 *
 * String command = "top -Hp pid \r\nprintf '%x\\n' tid\r\n jstack pid | grep tid -C 30 --color";
 * ClipboardUtils.setClipboardContent(command);
 * NotificationUtils.showMessage();
 *         
 * @author pez1420@gmail.com
 * @version $Id: JThreadAction.java v 0.1 2020/3/26 6:14 下午 pez1420 Exp $$
 */
public class JstackAction extends BaseAnAction {

    @Override
    protected void execute(IdeActionEvent e) {

    }
}
