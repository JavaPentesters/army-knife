package com.geekmake.plugin.action.http;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.FormatJsonDialog;

/**
 * @author pez1420@gmail.com
 * @version $Id: CompressJsonAnAction.java v 0.1 2020/10/5 4:51 下午 pez1420 Exp $$
 */
public class FormatJsonAnAction extends BaseAnAction {

    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new FormatJsonDialog(actionEvent).open("格式化JSON字符串");
    }
}
