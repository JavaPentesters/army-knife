package com.geekmake.plugin.action.jvm;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;

/**
 * head -n 100 gc.log | grep "CMS"
 *
 * grep 'Full GC'
 *
 * @author pez1420@gmail.com
 * @version $Id: GcAction.java v 0.1 2020/10/5 4:31 下午 pez1420 Exp $$
 */
public class GcAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {

    }
}
