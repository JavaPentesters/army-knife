



#### 1. invoke

+$ invoke cn.com.servyou.xqy.gateway.facade.GatewayFacade.login({"areacode":"SN","password":"RjhfTE4R","username":"9161013631115113XP","accountId":"xx","customerId":"bb"})
{"errorContext":null,"success":true,"entity":{"businessXml":null,"acwTc":null,"tgc":null,"redirectUrl":null,"reqDatagram":null,"resDatagram":null,"loginstate":null,"dzswj_tgc":"TGT-44056-pjcH2IpDfwv9O9CcvNDJkkR32HCIcg5KINTJLKCQzFtNPdamX9-gddzswj","ssoLoginInfo":null,"taxCollectorInfoResult":null}}

+$ invoke AuthConfigFacade.listByBizCode({"bizCode":"isTests1","bizType":1,"locationCode":"130000","sourceApp":"workbench","class":"cn.com.servyou.irc.facade.auth.dto.request.AuthConfigFacadeDTO"})


#### 2. status -l

status -l: 显示状态列表

shutdown -t 1000: 延迟 1000 毫秒关闭 dubbo 应用

#### 3. count



count
count XxxService

统计1次服务任意方法的调用情况。

count XxxService 10

统计10次服务任意方法的调用情况。

count XxxService xxxMethod

统计1次服务方法的调用情况。

count XxxService xxxMethod 10

统计10次服务方法的调用情况。


http://alibaba.github.io/dubbo-doc-static/Telnet+Command+Reference-zh-showComments=true&showCommentArea=true.htm




#### arthas

1、当DUBBO遇上Arthas - 排查问题的实践

https://github.com/alibaba/arthas/issues/327

2、用户案例

https://github.com/alibaba/arthas/issues?q=label%3Auser-case

$ wget https://alibaba.github.io/arthas/arthas-boot.jar

$ java -jar arthas-boot.jar

https://alibaba.github.io/arthas/heapdump.html


