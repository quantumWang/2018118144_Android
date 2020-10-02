# 活动的启动模式
活动的启动模式一共有四种，分别是**standard、singleTop、singleTask**和**singleInstance**  

如何选择活动的启动模式？  
在AndroidManifest.xml中通过给`<activity>`标签制定`android:launchMode`属性选择启动模式  

## standard模式  
standard模式：在不进行显式指定的情况下，所有活动默认都会自动使用这种启动模式  

在standard模式下，每当启动一个新的活动，它就会在返回栈中入栈，并处于栈顶的位置。对于使用该模式的活动，系统不会在乎这个活动是否已经在返回栈中存在，每次启动都会创建该活动的一个新的实例 

### 测试standard模式  
修改FirstActivity中onCreate()方法的代码  

` Log.d("FirstActivity",this.toString());//调用toString()方法打印FirstActivity的哈希值，通过观察logcat中打印的哈希值验证是否创建出新的FirstActivity实例` 

`Intent intent=new Intent(FirstActivity.this,FirstActivity.class);//在FirstActivity的基础上启动FirstActivity`  
`startActivity(intent);`  

### 测试结果  
  
![standard模式下打印的日志](img/standard.png)  

从logcat打印信息可以看出，每点击一次按钮就会创建一个新的FirstActivity实例，也就是说，每点击一次按钮，就会往返回栈中添加一个新的实例。退出程序时，添加了几个实例就要点击几次back键  

## singleTop模式  
两种启动模式的对比  

| 启动模式| 返回栈 | 是否创建新实例 |  
| -- | :--:| :--: |
| standard | 系统不会在乎活动是否在返回栈中存在，直接创建新活动 | 是 |  
| singleTop | 系统检查返回栈顶是否存在该活动，若存在，直接调用 | 否 |  

### 测试singleTop模式  
只需在AndroidManifest.xml`<activity>`标签下添加`android:launchMode`属性即可  

`android:launchMode="singleTop"`  

### 测试结果  
运行程序，从logcat打印信息可以看到已经创建了一个FirstActivity实例  

点击按钮，发现无论点击多少次按钮都不会再出现新的打印信息，这是因为启动活动时FirstActivity已经处于返回栈栈顶，每当想要启动一个FirstActivity时都会直接使用栈顶的活动。因此FirstActivity只有一个实例，仅按一次back键即可退出程序  

那么，FirstActivity并未处于栈顶位置时，再启动FirstActivity，是否会创建新实例？  
测试  
首先修改FirstActivity中onCreate()方法代码  

`Intent intent = new Intent(FirstActivity.this, SecondActivity.class);//在FirstActivity的基础上启动SecondActivity`  

点击按钮启动后启动SecondActivity，相应地，修改修改SecondActivity中onCreate()方法代码  

`Intent intent = new Intent(SecondActivity.this, FirstActivity.class);//在SecondActivity基础上启动FirstActivity`
`startActivity(intent);`  

运行程序，查看logcat信息：  

