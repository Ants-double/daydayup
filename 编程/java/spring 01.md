# 描述
- aop
- di ioc
# ioc
反转控制 ，容器主动报资源推送给使用者。反转资源获取的方向，组件以合适的方式获取资源
DI 依赖容器把资源获取到
我们所有的场合都使用ApplicationContext而非使用底层的BeanFactory
ApplicationContext有两个实现类一个是类径下的，一个是文件系统中的
 ApplicationContext可以使容器具用刷新和关闭的功能
- 获取bean的方式
1. ID 
2. 类型获取 类名.class不用强转，缺点是有多个的不唯一会报错
3. 
- 注入有二种
- 属性
- 构造方法
- 

# aop

1. 代码混乱 
2. 代码重复的代码
3. 