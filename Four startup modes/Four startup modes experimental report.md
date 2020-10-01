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
