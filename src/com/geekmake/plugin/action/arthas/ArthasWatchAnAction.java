package com.geekmake.plugin.action.arthas;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 * @author pez1420@gmail.com
 * @version $Id: ArthasWatchAction.java v 0.1 2020/3/26 6:07 下午 pez1420 Exp $$
 */
public class ArthasWatchAnAction extends BaseAnAction {

    /**
     * watch com.geekmake.plugin.arthas.ArthasThreadAnAction * '{params,returnObj,throwExp}' -n 5 -x 3
     *
     * @param event ActionEvent
     */
    @Override
    protected void execute(IdeActionEvent event) {
        String express = "'{params, returnObj}'";
        String conditionExpress = "";
        // 参数里-n 3，表示只执行3次
        String command = String.format("watch %s %s %s %s -n 3 -x 3", event.getClassName(),
            event.getMethodName(), express, conditionExpress);
        ClipboardUtils.setClipboardContent(command);
        NotificationUtils.showMessage();
    }
}
