
## Linux

### 1. 文件句柄

$ ss -ss
> 显示处于活动状态的套接字信息

$ ulimit -a
> 查看Linux系统默认的最大文件句柄数

$ lsof -n | grep 5950 -c
> 查看Linux系统某个进程打开的文件句柄数量
> PS：lsof（list open files）是一个列出当前系统打开文件的工具。在linux环境下，任何事物都以文件的形式存在，通过文件不仅仅可以访问常规数据，还可以访问网络连接和硬件。
> 因为应用程序打开文件的描述符列表提供了大量关于这个应用程序本身的信息，因此通过lsof工具能够查看这个列表对系统监测以及排错将是很有帮助的。
> 在终端下输入lsof即可显示系统打开的文件，因为 lsof 需要访问核心内存和各种文件，所以必须以 root 用户才能运行它。

$ lsof -n | awk '{print $2}' | sort | uniq -c | sort -nr
> 查看Linux系统某个进程打开的文件句柄数量


### 2、 Linux查看TCP连接情况

$ netstat -n | awk '/^tcp/ {++state[$NF]} END {for(key in state) print key,"\t",state[key]}'
> Linux查看TCP连接情况

$ netstat -nat|grep ESTABLISHED|wc -l
> Linux查看TCP连接状态为'ESTABLISHED'的情况

$ netstat -antp|grep 8080
> 查看哪个进程占用了18005端口

$ netstat -nat|grep -i "80"|wc -l
> 统计80端口连接数


### 3、 wireshark使用说明

+ wireshark使用说明

// 都能显示来源IP和目标IP
ip.addr eq 10.199.137.180

ip.addr eq 125.119.46.221

[TCP Retransmission]这类包是由于通讯超时产生的重传包，分析网络性能差的原因时常常可以看到这类型的包，

Retransmission:

+ TCP RST： TCP 重置，是TCP协议结束异常连接的一种方式，通过flog中的reset=1标记。当TCP连接无法通过正常的4次挥手结束时，一方可以通过发送携带reset标志的TCP包结束TCP连接。
+ [TCP Retransmission]这类包是由于通讯超时产生的重传包，分析网络性能差的原因时常常可以看到这类型的包，

+ tcpdump使用说明

tcpdump -i eth0 src host 10.200.100.149


+ TCP状态解释:

> LISTEN:       侦听来自远方的TCP端口的连接请求;
SYN_SENT:     在发送连接请求后等待匹配的连接请求;
SYN_RECV: 在收到和发送一个连接请求后等待对方对连接请求的确认;
ESTABLISHED:  代表一个打开的连接;
FIN_WAIT1:   等待远程TCP连接中断请求, 或先前的连接中断请求的确认;
FIN_WAIT2:   从远程TCP等待连接中断请求;
CLOSE_WAIT:   等待从本地用户发来的连接中断请求;
CLOSING:      等待远程TCP对连接中断的确认;
LAST_ACK:     等待原来的发向远程TCP的连接中断请求的确认;
TIME_WAIT:    等待足够的时间以确保远程TCP接收到连接中断请求的确认;
CLOSE:        没有任何连接状态;




