
## Redis

$ slowlog get
> 查询慢日志

$ client list
> 获取当前连接到redis端的所有客户端以及相关状态

$ redis-cli -h 192.168.1.1 -p 6379 client list | awk '{print $2}'......
> client连接分布

$ slowlog get
> 查询慢日志