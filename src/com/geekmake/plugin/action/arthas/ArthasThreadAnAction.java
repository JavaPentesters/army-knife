package com.geekmake.plugin.action.arthas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geekmake.plugin.action.BaseAnAction;
import com.geekmake.plugin.action.jvm.JstackAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.utils.ClipboardUtils;
import com.geekmake.plugin.utils.NotificationUtils;

/**
 *  References: https://arthas.aliyun.com/doc/thread.html
 *
 * @author pez1420@gmail.com
 * @version $Id: ArthasThreadAnAction.java v 0.1 2020/3/26 8:18 下午 pez1420 Exp $$
 */
public class ArthasThreadAnAction extends BaseAnAction {

    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(JstackAction.class);

    /**
     *  thread -n 3 : 当前最消耗CPU的N个线程
     *  
     * @param actionEvent
     */
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        String content = String.format("%s \n %s", "thread -n 3", "当前最消耗CPU的N个线程");
        ClipboardUtils.setClipboardContent(content);
        NotificationUtils.showMessage();
    }
}
