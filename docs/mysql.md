## Mysql

### Mysql死锁排查

$ show OPEN TABLES where In_use > 0;
> 查询是否锁表


$ SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCKS;
> 查看正在锁的事务


$ SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCK_WAITS;
> 查看等待锁的事务


$ show engine innodb status;
> 获取死锁命令信息


$ show variables like 'max_connections';
```
这个参数实际起作用的最大值（实际最大可连接数）为16384，即该参数最大值不能超过16384，即使超过也以16384为准；
增加max_connections参数的值，不会占用太多系统资源。系统资源（CPU、内存）的占用主要取决于查询的密度、效率等；
该参数设置过小的最明显特征是出现”Too many connections”错误；
```