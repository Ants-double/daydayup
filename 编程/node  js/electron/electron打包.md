1. 原始的方式打包
- 下载对应的版本号的[Release Electron](https://github.com/electron/electron/releases)
- 然后把对应的项目方便整理成这样的目录结构(Windows下)  **node_modules**重新安装，不然可能启动失败
![catalog](https://www.cnblogs.com/images/cnblogs_com/ants_double/1413361/o_003.jpg) 
- 把整文件夹给别人就可以了如果想改名子可以用[改名工具rcedit](https://github.com/electron/rcedit)
2. 应用程序打包成一个文件
- 为了缓解windows路径名过长的问题（就是有可能无法顺利的进行copy),以及隐藏代码可以把应用打包成asar文件
（就相当于把文件夹压缩一下，而此种压缩Electron不用解压可以直接读取)
- 全局安装asar
```
npm install -g asar
```
- 生成asar文件
```
asar pack your-app app.asar
```
- 拷到对应的文件夹下
```
electron/resources/
└── app.asar
```
**1和2如果要想生成对应的安装包可以借用第三方安装包生成工具进行生成如[Inno Setup](http://www.jrsoftware.org/isdl.php)**  
- 如果本地安装那就需要写一个js脚本文件来执行
``` javascript
var asar = require('asar');

var src = '../electronpicture/'; //工程目录
var dest = 'app.asar'; //输出

asar.createPackage(src, dest, function() {
  console.log('done.');
})
```
3. 借助第三方打包工具
- 打包工具有很三种常见的分别是  
1 [Electron-forge](https://github.com/electron-userland/electron-forge)  
2 [electron-builder](https://github.com/electron-userland/electron-builder)  
3 [electron-packager](https://github.com/electron-userland/electron-packager)  
- 我们这里使用第二种
- 安装 
```
yarn add electron-builder --dev
npm electron-builder --dev
```
- 配置package.json
``` json
"scripts": {
    "test": "node main.js",
    "dist": "electron-builder --win --x64"
  },
  "asar": {
    "unpackDir": "node_modules/edge-cs/**"
  },
  "build": {
    "appId": "electronpicture",
    "mac": {
      "target": [
        "dmg",
        "zip"
      ]
    },
    "win": {
      "target": [
        "nsis",
        "zip"
      ],
      "icon": "icon/006.ico"
    }
  },
  ``` 

- 最后在根目录命令行运行
```
npm run dist
```
