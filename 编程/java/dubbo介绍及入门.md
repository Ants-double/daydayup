# 写在前面的话
- dubbo 现在是apache组织旗下的项目，相信国内也有很多人使用。最近一个同事离职，我就接手了他的项目。远程通讯就是用的dubbo框架来实现的。使用Intelij idea 写了一个maven和spring-boot的Demo，代码放在最后。
- 其实学习一个知识点最好的地方就是官网。但是官网有点穷举的意思，不能每次学习都去读一遍，所以这里记录一下如何使用的问题，官网可以用作一个词典之类的工具。
# 介绍
- dubbo 这是一个分布式框架，主要为了解决单点服务器带来的压力和性能以及难以维护的问题
- dubbo的优点就是基本分布式的全部优点，并且因为注册中心的加弛让框架有了更好的连通性和健壮性和伸缩性，也可以实现灰度发布
# 架构
![架构图](https://www.cnblogs.com/images/cnblogs_com/ants_double/1413361/o_dubbo-architecture.jpg)
# 实操
1. 我们可以发现服务发布和消费都是通过注册中心来连接的，官文上也说了，可以不要注册中心采用直连的方式的。官方推荐的是zookeeper，我们项目中也是用的zookeeper所以，我们第一步就是开启zookeeper服务
我们可以在这里[下载](https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/)
https://mirrors.tuna.tsinghua.edu.cn/apache/zookeeper/
记得使用的端口。
2. 新建一个工程 api 主要提供服务发布者和消费者共用的接口
3. 服务发布者添加对应的配置文件并依赖api工程
intellij idea 添加不同的模块依赖可以参考一个博文，以及配置文件会自动导入命名空间，不要手动导入配一堆。反而不好用。
4. 消费者同样添加对api工程的引用和项目文件
基本上就可以通信了，源码可以到github上去找到
# 采坑
1. 第一次用接手的代码乱。发现没有zookeeper也可以启动，但是不能实现RPC,原因是check并了，没有进行检查
2. 一定要看好dubbo的jar包依赖，这的很不好搞，花了很长的时间。不可大意
3. 注意配置文件的优先级 消费者>发布者>全局
4. 方法级别配置优于接口级别

源码
https://github.com/Ants-double/BlogCode/tree/master/java































































