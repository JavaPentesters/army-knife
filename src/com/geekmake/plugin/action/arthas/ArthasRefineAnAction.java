package com.geekmake.plugin.action.arthas;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 * @author pez1420@gmail.com
 * @version $Id: ArthasRefineAnAction.java v 0.1 2020/3/26 9:40 下午 pez1420 Exp $$
 */
public class ArthasRefineAnAction extends BaseAnAction {

    /**
     * redefine /tmp/com/example/demo/arthas/user/UserController.class
     *
     * @param actionEvent ActionEvent
     */
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        String className = toPathClass(actionEvent.getClassName());
        String command = String.format("redefine %s", className);

        ClipboardUtils.setClipboardContent(command);
        NotificationUtils.showMessage();

    }

    private String toPathClass(String className) {
        if (className == null) {
            return null;
        }

        String pathClassName = "/" + className.replace(".", "/") + ".class";
        return pathClassName;
    }
}
