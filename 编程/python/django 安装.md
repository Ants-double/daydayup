# 安装django 只适应用windows
- python [下载](https://www.python.org/downloads/)  

  **一定要把python添加到环境变量**
- pip 
**安装python时安装pip**

- 然后在shell窗口执行以下命令，创建虚拟环境
``` python
pip install virtualenvwrapper-win
mkvirtualenv myproject
workon myproject
```
- 安装django
```
pip install django
```
- 执行以下命令
```
django-admin --version
``` 
如果出现django的版本号一切就正常了，django安装完毕