package com.geekmake.plugin.arthas;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 *
 *
 * @author pez1420@gmail.com
 * @version $Id: ArthasJadAnAction.java v 0.1 2020/3/26 9:53 下午 pez1420 Exp $$
 */
public class ArthasJadAnAction extends BaseAnAction {

    /**
     * jad cn.com.servyou.irc.shared.auth.impl.strategy.authorize.standard.StandardDefaultAuthorizeStrategy
     *
     * @param actionEvent ActionEvent
     */
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        String command = String.format("jad %s", actionEvent.getClassName());
        ClipboardUtils.setClipboardContent(command);
        NotificationUtils.showMessage();
    }
}
