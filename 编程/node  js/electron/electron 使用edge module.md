# 使用Electron-builder 打包
- edge-cs.dll cannot be loaded in electron app when packaged in asar  
1. 解决方法
2. 在edge-cs/lib/edge-cs.js添加.replace('app.asar', 'app.asar.unpacked')
```
var path = require('path');

exports.getCompiler = function () {
	return process.env.EDGE_CS_NATIVE || (process.env.EDGE_USE_CORECLR ? 'Edge.js.CSharp' : path.join(__dirname, 'edge-cs.dll').replace('app.asar', 'app.asar.unpacked'));
};

exports.getBootstrapDependencyManifest = function() {
	return path.join(__dirname, 'bootstrap', 'bin', 'Release', 'netstandard1.6', 'bootstrap.deps.json');
}
```  
3. 在package文件添加
```  
"asar":{
        "unpackDir": "node_modules/edge-cs/**"
},
```  
- 安装edge格块失败
1. 安装 
```
npm install --save-dev electron-edge-js  
```  
