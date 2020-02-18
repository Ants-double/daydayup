## Failed to obtain JDBC Connection; nested exception is java.sql.SQLException: Access denied for user ''@'localhost' (using password: YES)
## 原因是配置文件有变动
- 错误的
```
#spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ants_double?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false
spring.datasource.password=456123
spring.datasource.name=root
#spring.profiles.active=dev
``` 
- 正确的
```
#spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ants_double?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false
spring.datasource.password=456123
spring.datasource.username=root
#spring.profiles.active=dev
```
