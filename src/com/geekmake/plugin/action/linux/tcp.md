
Linux查看TCP连接情况

netstat -n | awk '/^tcp/ {++state[$NF]} END {for(key in state) print key,"\t",state[key]}'

netstat -nat|grep ESTABLISHED|wc -l

查看哪个进程占用了18005端口
netstat -antp|grep 8080

统计80端口连接数:
netstat -nat|grep -i "80"|wc -l



+ wireshark使用说明：

google后发现[TCP Retransmission]这类包是由于通讯超时产生的重传包，分析网络性能差的原因时常常可以看到这类型的包，

ip.addr eq 10.199.137.180 // 都能显示来源IP和目标IP

ip.addr eq 125.119.46.221

Retransmission:

TCP RST： TCP 重置，是TCP协议结束异常连接的一种方式，通过flog中的reset=1标记。当TCP连接无法通过正常的4次挥手结束时，一方可以通过发送携带reset标志的TCP包结束TCP连接。

tcpdump使用说明：
tcpdump -i eth0 src host 10.200.100.149




相关TCP状态解释:

LISTEN:       侦听来自远方的TCP端口的连接请求;

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




