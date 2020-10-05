package com.geekmake.plugin.linux;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.RedisCommandDialog;

/**
 * @author pez1420@gmail.com
 * @version $ID: RedisCommandAction.java v 0.1 2020/9/25 5:08 下午 pez1420 Exp $$
 */
public class RedisCommandAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new RedisCommandDialog(actionEvent.getProject()).open("Redis 常见命令");
    }
}
