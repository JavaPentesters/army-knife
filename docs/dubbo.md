
#### 1. invoke

+$ invoke cn.com.XxxService.login({"password":"xxx","username":"xxxx"})

+$ invoke XxxService.login({"password":"xxx","username":"xxxx"})


#### 2. status -l

+$ status -l: 显示状态列表

+$  shutdown -t 1000: 延迟 1000 毫秒关闭 dubbo 应用

#### 3. count

+$  count XxxService
> 统计1次服务任意方法的调用情况。

+$  count XxxService 10

> 统计10次服务任意方法的调用情况。

+$  count XxxService xxxMethod

> 统计1次服务方法的调用情况。

+$  count XxxService xxxMethod 10

> 统计10次服务方法的调用情况。


http://alibaba.github.io/dubbo-doc-static/Telnet+Command+Reference-zh-showComments=true&showCommentArea=true.htm




