package com.geekmake.plugin.action.linux;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.LinuxOpenFileDialog;

/**
 * @author pez1420@gmail.com
 * @version $Id: OpenFileAction.java v 0.1 2020/9/24 11:26 上午 pez1420 Exp $$
 */
public class LinuxOpenFileAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new LinuxOpenFileDialog(actionEvent.getProject()).open("Linux 文件具柄常见命令");
    }
}
