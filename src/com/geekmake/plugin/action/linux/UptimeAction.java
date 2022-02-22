package com.geekmake.plugin.action.linux;

import com.geekmake.plugin.action.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 * 查看机器的启动时间、登陆用户、系统平均负载 （系统最近1分钟、5分钟、15分钟）
 *
 * 目标：快速确定操作系统的重启时间
 *
 * @author pez1420@gmail.com
 * @version $Id: UptimeAction.java v 0.1 2020/12/28 10:59 上午 pez1420 Exp $$
 */
public class UptimeAction extends BaseAnAction {

    @Override
    protected void execute(IdeActionEvent actionEvent) {
        String command = "uptime";
        ClipboardUtils.setClipboardContent(command);
        NotificationUtils.showMessage(command);

    }
}
