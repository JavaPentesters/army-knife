package com.geekmake.plugin.action.middleware;

import com.geekmake.plugin.BaseAnAction;
import com.geekmake.plugin.config.IdeActionEvent;
import com.geekmake.plugin.dialog.MysqlCommandDialog;

/**
 * 1、查询是否锁表
 * show OPEN TABLES where In_use > 0;
 *
 * 2、查看正在锁的事务
 * SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCKS;
 *
 * 3、查看等待锁的事务
 * SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCK_WAITS;
 *
 * 4、获取死锁命令信息
 * show engine innodb status;
 *
 * kill 12041
 *
 * explain sql语句
 *
 *
 * @author pez1420@gmail.com
 * @version $id: MysqlCommandAction.java v 0.1 2020/9/25 5:08 下午 pez1420 Exp $$
 */
public class MysqlCommandAction extends BaseAnAction {
    @Override
    protected void execute(IdeActionEvent actionEvent) {
        new MysqlCommandDialog(actionEvent.getProject()).open("Mysql 常见指令");

    }
}
