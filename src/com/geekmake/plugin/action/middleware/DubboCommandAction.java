package com.geekmake.plugin.action.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geekmake.plugin.action.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.DubboCommandDialog;
import com.geekmake.plugin.utils.ClipboardUtils;

/**
 *
 *
 * @author pez1420@gmail.com
 * @version $Id: DubboInvokeAction.java v 0.1 2020/3/30 2:12 下午 pez1420 Exp $$
 */
public class DubboCommandAction extends BaseAnAction {

    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClipboardUtils.class);

    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new DubboCommandDialog(actionEvent.getProject(), actionEvent).open("Dubbo 常用命令");
    }
}
