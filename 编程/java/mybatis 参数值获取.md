## 两种方式
- #{}
以预编译的形式 
- ${}
取出的直接拼装sql中（有安全问题） 
一般在sql是拼接表名时使用，排序等原生sql不支持

## 大多情况下应该用#号
#{} 可以指定一些参数  

javaType

jdbcType :通常在数据为null的时候要设置
oracle null会报错 jdbcType Other 因为mybatis 所有的null对应数据库的other
#{,jdbcType=NULL}
也可以在全局设置那设置
   <setting name="jdbcTypeForNull" value="NULL"></setting>

resultMap
