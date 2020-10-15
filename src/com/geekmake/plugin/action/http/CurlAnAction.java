package com.geekmake.plugin.action.http;

import com.geekmake.plugin.action.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 * @author pez1420@gmail.com
 * @version $Id: CurlAnAction.java v 0.1 2020/10/5 4:46 下午 pez1420 Exp $$
 */
public class CurlAnAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        String command = "curl -H \"Content-Type: application/json\" -H \"xkey: 123\" -X POST  --data '{\"data\":\"1\"}' -v http://127.0.0.1:8080/path";
        ClipboardUtils.setClipboardContent(command);
        NotificationUtils.showMessage(command);
    }
}
