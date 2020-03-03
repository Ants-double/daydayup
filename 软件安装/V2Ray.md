## V2Ray服务

1. 安装
	 ``` shell
   bash <(curl -L -s https://install.direct/go.sh)   
   ```
2. 查看或更改配置

   ``` shell
   cat /etc/v2ray/config.json
   ```

   3. 启动服务
   
      ``` shell
      systemctl enable v2ray
      systemctl start v2ray
      systemctl restart v2ray
      
      ```
   
      

## V2Ray客户端

1. 下载

   https://tlanyan.me/download.php?filename=/v2/windows/v2rayN-v3.5.zip

2. 配置

   点“服务器”下拉菜单中的添加“vmess”服务器

   根据服务端信息填写服务器地址（域名或ip）、端口、用户id、额外id，**这几项非常重要**！**加密方式一般都是auto，传输协议一般是tcp**。别名随便写就可以，比如“洛杉矶不限流量”。**如果使用了伪装等高级技术，**伪装域名要填写配置的主机名，输入伪装路径，同时底层传输安全要选择tls，allowinsecure选择true（没使用伪装不要选！）

3. 启动pac

   点击主界面上的“参数设置”，在“Core:基础设置”中将“Http代理”选择“开启PAC，并自动配置PAC（PAC模式）”，当然也可以选择全局模式。其它设置如果不清楚保持默认即可：

4. 

## 其它

防火墙

``` shell
firewall-cmd --zone=public --add-port=8080/udp --permanent
firewall-cmd --zone=public --remove-port=8080/tcp --permanent
firewall-cmd --reload
firewall-cmd --list-ports
```

