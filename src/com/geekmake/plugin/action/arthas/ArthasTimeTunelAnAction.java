package com.geekmake.plugin.action.arthas;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.ArthasTimeTunelCommandDialog;

/**
 *
 * @author pez1420@gmail.com
 * @version $Id: ArthasTimeTunelAnAction.java v 0.1 2020/9/29 10:43 上午 pez1420 Exp $$
 */
public class ArthasTimeTunelAnAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new ArthasTimeTunelCommandDialog(actionEvent).open("Arthas TimeTunel");
    }
}
