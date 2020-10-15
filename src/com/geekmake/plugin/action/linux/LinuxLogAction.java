package com.geekmake.plugin.action.linux;

import com.geekmake.plugin.action.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.LinuxLogCommandDialog;

/**
 *
 *  1、找出调用耗时大于3位数的dao方法，把3改成4就是大于4位数
 *  grep '[0-9]\{3,\}ms' common-dal-digest.log
 *
 *  2、在日志中查找响应时间超过5s的请求
 *  grep '[5-9][0-9]\{3\}ms' *.log
 *
 *  3、统计某个rpc接口在14点到15点之间的调用次数
 *  grep '2020-09-24 1[4-5]:' common-sal-digest.log.2020-09-24 | grep IpLocatorFacade |wc -l
 *
 *  4、Nginx中访问最频繁的100个ip
 *
 *  awk '{print$1}' access.log | sort -n | uniq -c | sort -rn | head -n 100
 *
 * @author pez1420@gmail.com
 * @version $Id: LinuxLogAction.java v 0.1 2020/9/25 下午2:58 pez1420 Exp $$
 */
public class LinuxLogAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new LinuxLogCommandDialog(actionEvent.getProject()).open("Linux 日志排查线上问题常见命令");
    }
}
