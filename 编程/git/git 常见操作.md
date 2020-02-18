## 添加追踪文件的修改
``` git
git add newfileorstagefilename
git commit -a -m 'readme' //添加暂存文件的修改
```  
## 移除文件
``` git
git rm fileName //移除文件，此文件还没有被暂存
git rm -f fileName //移除文件，此文件已经被暂存
git rm --cached filename //此文件从Git仓库中移除，但依然保存在磁盘上

```

## 移动文件

``` git 
git mv file_from file_to //移动文件
git mv oldname newname //可以进行重命名
```

## 提交日志
``` git 
 git log // 查看所有的
 git log -p -2 //查看-p显示提交的内容差异 2是显示几条
 git log --stat //查看提交的统计信息
 --pretty //子参数

```

## 撤消操作

``` git

git commit --amend //amend 选项的提交命令尝试重新提交 最终你只会有一个提交 - 第二次提交将代替第一次提交的结果
git reset HEAD <file> //取消暂存
git checkout -- <file> //撤消对文件的修改


```
## 远程仓库

``` git 
git remote -v //会显示需要读写远程仓库使用的 Git 保存的简写与其对应的 URL
git remote add pb urlpath//添加一个新的远程 Git 同时指定一个你可以轻松引用的简写
git fetch [remote-name] //访问远程仓库，从中拉取所有你还没有的数据
git pull //通常会从最初克隆的服务器上抓取数据并自动尝试合并到当前所在的分支
git push origin master//推送 并且之前没有人推送过时不然就要拉取合并后再推送
git remote show [remote-name] //查看远程仓库
git remote rename oldname newname//远程仓库的移除与重命名
```  
## 如何避免每次输入密码
如果你正在使用 HTTPS URL 来推送，Git 服务器会询问用户名与密码。 默认情况下它会在终端中提示服务器是否允许你进行推送。

如果不想在每一次推送时都输入用户名与密码，你可以设置一个 “credential cache”。 最简单的方式就是将其保存在内存中几分钟，可以简单地运行 git config --global credential.helper cache 来设置它。  
## 删除远程分支
```
git push origin --delete serverfix
```  
## 变基  
``` 
$ git checkout experiment
$ git rebase master 
$ git checkout master
$ git merge experiment
```  
```  
$ git rebase --onto master server client
```