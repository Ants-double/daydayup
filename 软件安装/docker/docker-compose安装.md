### linux

- 执行获取命令

  ``` shell
  sudo curl -L "https://github.com/docker/compose/releases/download/1.25.3/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  ```

- 添加可执行权限及添加软连

  ``` shell
  sudo chmod +x /usr/local/bin/docker-compose
  ```

  ``` shell
  sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
  ```

  

- 查看版本

  ``` shell
  docker-compose --version
  ```

  