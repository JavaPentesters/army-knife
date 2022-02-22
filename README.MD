

# army-knife idea plugin

## Background
作为一名开发人员,不仅需要掌握系统开发技能,更应该具备处理线上问题的能力。
我们需要借助一些工具或命令来定位线上问题，有些命令非常繁琐或者脑子一时短路突然想不起来哈。
工欲善其事必先利其器，排查线上问题时是争分夺秒的，我们需要以最快速、便捷的方式拷贝到需要的命令,army-knife IDEA就是用来解决这个问题。

## Key Features

+ 插件提供了一套用于定位线上问题的方法论
+ 插件提供了大量简便且实用的命令，用于排查Java应用自身以及OS、中间件引发的各种线上问题。即能够学习常用的命令，也可以以全局的视角看待线上问题
    + **Arthas**是Alibaba开源的Java诊断工具，它是用于排查线上问题的一款利器。因此，插件提供了快捷使用**Arthas**常用命令的快速方式。
    + 插件提供了Linux常用性能分析工具以及命令，如top（cpu）、free（内存）、df（磁盘）、dstat（网络流量）、pstack、vmstat、strace（底层系统调用）
    + 插件提供了查找业务日志的常用命令，如grep、tail
    + 插件提供了分析GC日志的常用命令
    + 插件提供了排查Mysql线上问题的常用命令，如deadlock死锁问题
    + 插件提供了排查Java中间件的常见命令，如Dubbo、Redis、Netty、RocketMQ、Nginx(Openresty)等常用命令
    + 插件提供了Http请求相关的命令，如curl发起JSON类型请求等
+ 插件提供了Java代码静态审查和安全漏洞quick fix修复功能
    + 1001: Mybatis xml SQL Injection
    + 1002: XStream反序列化风险
+ 插件针对每一条命令提供了详尽的案例，使你能够理论联系实际，知其然更知其所以然。
+ 插件支持JDK7 +
+ 插件支持Linux/Mac

## Quick Start

+ 本地磁盘方式安装
> Preferences/Plugins/Installed/Install from disk，选择army-knife.jar

+ Jetbrain plugin repository方式安装

## RoadMap

|  Version  |  Milestone   | Features  |
|  ----  | ----  |  ----  |
| 1.2.0 | 2020.10  | 发布正式release版本 |
| 1.3.0 | 2020.11  | 支持ShadowSocks协议的客户端 |




