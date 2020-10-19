## Linuxlog

$ grep '[0-9]\{3,\}ms' common-dal-digest.log
> 找出调用耗时大于3位数的dao方法，把3改成4就是大于4位数

$ grep '[5-9][0-9]\{3\}ms' *.log
> 在日志中查找响应时间超过5s的请求

$ grep '2020-09-24 1[4-5]:' common-sal-digest.log.2020-09-24 | grep IpLocatorFacade |wc -l
> 统计某个rpc接口在14点到15点之间的调用次数

$ awk '{print$1}' access.log | sort -n | uniq -c | sort -rn | head -n 100
>  Nginx中访问最频繁的100个ip

$ awk -f ' ' '{print $5}' | sort | uniq -c | sort -nr
> Tomcat Access 文件计算服务在每秒内处理多少个请求,管道命令
