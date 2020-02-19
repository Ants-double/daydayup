# node.js

### 更新软件源

``` shell
sudo apt update
```



### 安装npm

``` shell
sudo apt install npm
```



### 安装n模块

``` shell
sudo npm install n -g
```



### 安装node 

``` shell
sudo n lts 
# 长期支持版本
```

- latest – 最新版，但不一定稳定
- stable – 最新的稳定版，比 latest 老一点，优点是稳定，但不是长期支持版（api 可能会变动）
- lts – 最新的长期支持版，这是最推荐使用的版本。虽然比 stable 老，但是是长期支持的版本（当然也是稳定版本）。是 long term support 的缩写

### 查看版本

``` shell
node -v
```

 可以通过n模块切换不同的版本号

``` shell
sudo n lts 
# 切换到版本
```

