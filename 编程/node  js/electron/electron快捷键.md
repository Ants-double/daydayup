- 我们分为在主进程中注册快捷键和在渲染进程中注册快捷键
1. 在主进程中我们有两种方式

一 利用[Menu]来模拟快捷键,只有app获得焦点时才生效,很少使用
```
const { Menu, MenuItem } = require('electron')
const menu = new Menu()

menu.append(new MenuItem({
  label: 'Print',
  accelerator: 'CmdOrCtrl+P',
  click: () => { console.log('time to print stuff') }
}))
```
二 就是全局快捷键（意思就是不强求app获得焦点）,r所以这就不是模拟菜单事件了，我们要监听键盘，因为快捷键的根本操作就是响应键盘的按键
我们可以用globalShortcut来实现

* 引用globalShortcut模块
```
const { app, BrowserWindow, ipcMain, globalShortcut } = require('electron');
```
* 注册快捷键（在mac10.14 上，客户端没有被信任操作音视频的快捷键失效  
```
const ret = globalShortcut.register('CommandOrControl+X', () => {
        console.log('CommandOrControl+X is pressed')
    });  
```

* 检测是否注册成功
```
 if (!ret) {
        console.log('registration failed')
    }
// 检查快捷键是否注册成功
    console.log(globalShortcut.isRegistered('CommandOrControl+X'))
```
* 注销快捷键
```
app.on('will-quit', () => {
    // 注销快捷键
    globalShortcut.unregister('CommandOrControl+X')
  
    // 注销所有快捷键
    globalShortcut.unregisterAll()
  })  
  ```  
 2.在渲染进程中我们也有二种方式  
 一 可以利用浏览窗口监听键盘事件,这是一种常规的方式，自己判断什么键按下
 ```
 window.addEventListener('keyup', doSomething, true)
 ```  
 注意第三个参数 true，这意味着当前监听器总是在其他监听器之前接收按键，以避免其它监听器调用 stopPropagation()。  
 
 二 我们可以利用第三方模块比如[MOUSETRAP](https://craig.is/killing/mice)  
 +  安装模块,我们也可以用Script标签引入
 ```
 
 npm install mousetrap --save
 ``` 
 + 使用(我们在html页面引入index.js文件，文件内容如下)
 ```
 const Mousetrap = require('mousetrap');

// #region 快捷键
// single keys
Mousetrap.bind('esc', () => {
    console.log('escape');
}, 'keyup');
Mousetrap.bind('4', () => {
    console.log('4');
})
Mousetrap.bind("?", () => {
    console.log('show shortcuts!');
});
// combinations
Mousetrap.bind('command+shift+k', () => {
    console.log('command shift k');
});
// map multiple combinations to the same callback
Mousetrap.bind(['command+k', 'ctrl+k'], () => {
    console.log('command k or control k');
    // return false to prevent default browser behavior
    // and stop event from bubbling
    return false;
});

// gmail style sequences
Mousetrap.bind('g i', () => {
    console.log('go to inbox');
});
Mousetrap.bind('* a', () => {
    console.log('select all');
});

// konami code!
Mousetrap.bind('up up down down left right left right b a enter', () => {
    console.log('konami code');
});
// #endregion
 ```  
 + html文件
 ```
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <p>This is test pag!</p>
    <script type="text/javascript" src="./views/js/index.js"></script>
</body>
</html>
```  

开发工作中如果要主进程响应的就用全局快键盘，如果是页面中的快键操作，就可以直接用mousetrap。
 
 