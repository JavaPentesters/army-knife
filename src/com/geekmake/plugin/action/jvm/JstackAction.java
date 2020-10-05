package com.geekmake.plugin.action.jvm;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.JstackCommandDialog;

/**
 *
 * jstack -l pid |wc -l
 * jstack -l pid |grep "BLOCKED"|wc -l
 * jstack -l pid |grep "Waiting on condition"|wc -l
 *
 * 线程block问题通常是等待io、等待网络、等待监视器锁等造成，可能会导致请求超时、造成造成线程数暴涨导致系统502等。
 * 假设出现这样的问题，主要是关注jstack 出来的BLOCKED、Waiting on condition、Waiting on monitor entry等状态信息。
 * 假设大量线程在“waiting for monitor entry”：可能是一个全局锁堵塞住了大量线程。
 * 假设短时间内打印的 thread dump 文件反映。随着时间流逝。waiting for monitor entry 的线程越来越多，没有降低的趋势，可能意味着某些线程在临界区里呆的时间太长了，以至于越来越多新线程迟迟无法进入临界区。
 * 假设大量线程在“waiting on condition”：可能是它们又跑去获取第三方资源，迟迟获取不到Response，导致大量线程进入等待状态。
 * 假设发现有大量的线程都处在 Wait on condition，从线程堆栈看，正等待网络读写，这可能是一个网络瓶颈的征兆，由于网络堵塞导致线程无法运行。
 *         
 * @author pez1420@gmail.com
 * @version $Id: JstackAction.java v 0.1 2020/3/26 6:14 下午 pez1420 Exp $$
 */
public class JstackAction extends BaseAnAction {

    @Override
    protected void execute(IdeActionEvent e) {
        new JstackCommandDialog(e.getProject()).open("通过Jstack分析进程CPU使用率高的原因");
    }
}
