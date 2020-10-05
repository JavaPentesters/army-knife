package com.geekmake.plugin.action.arthas;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 *
 * @author pez1420@gmail.com
 * @version $Id: InstallArthasAnAction.java v 0.1 2020/9/27 3:27 下午 pez1420 Exp $$
 */
public class InstallArthasAnAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        String command = "curl -sk https://arthas.aliyun.com/arthas-boot.jar -o arthas-boot.jar && java -jar arthas-boot.jar";

        ClipboardUtils.setClipboardContent(command);
        NotificationUtils.showMessage("安装arthas " + command);
    }
}
