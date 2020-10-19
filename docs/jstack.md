## Jstack

### 1.1 jstack三板斧

$ top -Hp pid
> 查询进程下所有线程的运行情况

$ printf '%x\n' tid
> 找到cpu最高的线程tid并转换为16进制

$ jstack pid | grep tid -C 30 --color
> 根据16进制线程tid找到线程堆栈信息

$  jstack -l pid |grep "BLOCKED"|wc -l
> 查看处于BLOCKED状态的线程




