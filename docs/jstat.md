
## jstat

查看堆内存各部分的使用量,以及加载类的数量

$ jstat -gc 3831 5000
> 每5秒一次显示jvmGC情况

$ stat -gcutil 3831 5000
> 每5秒一次显示jvmGC情况