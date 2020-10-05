

# army-knife idea plugin

### Background
作为一名开发人员,不仅需要掌握系统开发技能,更应该具备处理线上问题的能力。
我们排查线上问题时经常需要使用一些常用命令，有些命令又非常繁琐或者程序员一时想不起来。
工欲善其事必先利其器，排查线上问题时是争分夺秒的，我们需要以最快速、便捷的方式拷贝到需要的命令,army-knife IDEA就是用来解决这个问题的。

### Key Features

+ 插件提供一套定位线上问题的方法论
    + **Arthas**是Alibaba开源的Java诊断工具，它是用于排查线上问题的一款利器。因此，插件提供了快捷使用**Arthas**常用命令的快速方式。
    + 插件提供了Linux常用性能分析工具以及命令，如top（cpu）、free（内存）、df（磁盘）、dstat（网络流量）、pstack、vmstat、strace（底层系统调用）
+ 插件提供了查找业务日志的常用命令，如grep、tail
+ 插件提供了分析GC日志的常用命令
+ 插件提供了排查Mysql线上问题的常用命令，如deadlock死锁问题
+ 插件提供了Dubbo、Redis、Nginx(Openresty)等常用命令
+ 支持JDK8 +
+ 插件支持Linux/Mac

### Quick Start





CodingEasier:
https://gitee.com/Chave-Z/CodingEasier



### Psi常用类
PsiFile: 对应于应用中的文件，如.Java的文件或者是.xml的文件等；
PsiDirectory: 对应于应用中的目录
PsiJavaFile: 对应于Java源文件，如Test.java
PsiClass: 对应于某个类，其中一个文件中可能会有多个类；
PsiMethod: 对应于类中的某个方法
PsiField: 对应于类中的某个属性
PsiAnnotation: 对应于注解；


PsiClass的常用方法：
获取所有属性：getFields、getAllFields
查找属性：findFieldByName()，其中第二个参数是是否查找父类的属性；
获取所有方法：getMethods/getAllMethods
查找方法：findMethodsByName


<dependency>
    <groupId>com.squareup</groupId>
    <artifactId>javapoet</artifactId>
    <version>1.8.0</version>
</dependency>


### 开发intellij 界面组件
http://corochann.com/intellij-plugin-development-introduction-gui-form-designing-876.html

```
大多数插件可能添加个action，再加点图形界面就差不多了，界面就是Swing那一套，当年写了那么多Swing程序，没想到现在还用的上。IntelliJ IDEA的API很多，
可能很难都去读，用到什么现查吧，大多数直接用JDK就可以了。
如果有些地方不太清楚的话，可以去Github上看看一些开源的插件是怎么搞的，比着葫芦画瓢估计也够用了。
```

插件开发中遇到一些琐碎的东西搞不清楚，文档也不是很全，最终基本上也都找到解决方案，列几个Tips吧：

+ Toolbar图标：

plugin.xml中的action可以设置icon属性，但若把图片放在普通路径下会提示找不到，看到文档说放在固定目录下，但貌似也不起作用，最终放在out>production>project下才可以，
要知道这个目录下都是一些编译好的class文件，直接放在这也是醉了。

+ Dialog居中显示：

setLocationRelativeTo（null）可以使其屏幕居中，但如果IDE不全屏，显示的效果就不好看了，想使Dialog在IDE窗口居中显示，可以这样设置：
setLocationRelativeTo(WindowManager.getInstance().getFrame(actionEvent.getProject())

https://juejin.im/post/5a0550f56fb9a04527250da1

### Mysql

1、查看最大连接数
show variables like 'max_connections';

```
这个参数实际起作用的最大值（实际最大可连接数）为16384，即该参数最大值不能超过16384，即使超过也以16384为准；
增加max_connections参数的值，不会占用太多系统资源。系统资源（CPU、内存）的占用主要取决于查询的密度、效率等；
该参数设置过小的最明显特征是出现”Too many connections”错误；
```

发布IDEA插件

当你的插件准备好了，你可以将它发布到插件仓库这样所以人都可以获取到插件并进行安装。你可以发布到
JetBrains Plugins Repository 或者是 custom plugin repository.

https://jetbrains.org/intellij/sdk/docs/basics/getting_started/publishing_plugin.html

pez1420@gmail.com 直接登陆

pez1420@qwer1qaz!QAZ
