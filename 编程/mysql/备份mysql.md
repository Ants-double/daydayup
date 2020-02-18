## 导出脚本
```
假想环境：
MySQL   安装位置：C:/MySQL
论坛数据库名称为：bbs
MySQL root   密码：123456
数据库备份目的地：D:/db_backup/

@echo off
set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%"
C:/MySQL/bin/mysqldump --opt -u root --password=123456 bbs > D:/db_backup/bbs_%Ymd%.sql
@echo on

```

## mysqldump --opt的解释

```
mysqldump -uroot -p --opt databasename [table] > xxx.sql
```
- 默认Mysqldump导出的SQL文件中不但包含了导出的数据，还包括导出数据库中所有数据表的结构信息。
 
- –opt：此Mysqldump命令参数是可选的，如果带上这个选项代表激活了Mysqldump命令的quick，add-drop-table，add-locks，extended-insert，lock-tables参数，也就是通过–opt参数在使用Mysqldump导出Mysql数据库信息时不需要再附加上述这些参数。
 
- –quick：代表忽略缓冲输出，Mysqldump命令直接将数据导出到指定的SQL文件。
　　–add-drop-table：顾名思义，就是在每个CREATE TABEL命令之前增加DROP-TABLE IF EXISTS语句，防止数据表重名。
 
- –add-locks：表示在INSERT数据之前和之后锁定和解锁具体的数据表，你可以打开Mysqldump导出的SQL文件，在INSERT之前会出现LOCK TABLES和UNLOCK TABLES语句。
　　–extended-insert (-e)：此参数表示可以多行插入。