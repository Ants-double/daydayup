##  传单个参数
```
#{参数名}
```  
mybatis 不会做特殊处理，参数名可以随便写

## 多个参数
mybatis 会做特殊处理 #{}就是从map中取值  

key 就是param1...paramn  或者参数索引

value 传入的参数值   

 
1. 命名参数：明确指出封装参数时map的key值 @Param()
2. 正好是业务的pojo 可以直接传pojo #{属性名}
3. 直接传map
 - 如果经常使用，可以编写一个TO，专门用来做数据传输
## 特殊参数
- Collection (list set )或者数组也会封装一个map
- list 的key 就是参数名
- array 的key 就是array


