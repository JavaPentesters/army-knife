
## Jmap

### 1.1 jmap

+$ jmap -heap 3831
>  显示Java堆详细信息

+$ jmap -histo[:live] 3831 | sort -k 2 -g -r
> 查看对象数最多的对象，按降序输出

+$ jmap -dump:format=b,file=dump.hprof 3831
> dump jvm当前内存中的情况到文件

