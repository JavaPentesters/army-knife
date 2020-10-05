package com.geekmake.plugin.linux;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 * ip.addr eq 10.199.137.180 // 都能显示来源IP和目标IP
 *
 * @author pez1420@gmail.com
 * @version $Id: WiresharkAction.java v 0.1 2020/9/25 5:02 下午 pez1420 Exp $$
 */
public class WiresharkAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        String content = String.format("%s \n %s", "thread -n 3", "当前最消耗CPU的N个线程");
        ClipboardUtils.setClipboardContent(content);
        NotificationUtils.showMessage();
    }
}
