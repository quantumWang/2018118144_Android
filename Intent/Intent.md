# Intent应用  

Intent是Android程序中各组件之间进行交互的一种重要方式，不仅可以指明当前组件想要执行的动作，还可以在不同组件之间传递数据。  
Intent使用场景：  
+ 启动活动  
+ 启动服务  
+ 发送广播 

## 显式Intent  
显式意图明确指明了启动活动的上下文和想要启动的目标活动和Intent应该传递给哪个组件。  

### 使用显式Intent  

1. 创建活动FirstActivity，并设置为主活动，代码如下：  

```
<intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```

2. 创建活动SecondActivity，修改second_layout.xml中的代码如下：  

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/button_2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="It's button 2" />
</LinearLayout>
```  

3. 修改FirstActivity中的按钮点击事件：  
          

```
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
```  
4. 运行程序，点击FirstActivity界面的按钮，成功启动SecondActivity这个活动，如图：  

![Alt text](img/intent1.png)  

## 隐式Intent    
隐式意图：没有明确指定组件名的Intent为隐式意图。 Android系统会根据隐式意图中设置的动作(action)、类别(category)、数据（URI和数据类型）找到最合适的组件来处理这个意图。  

### 使用隐式Intent  
1. 配置`<intent-filter>`标签的内容，指定SecondActivity活动能够响应的action和category，代码如下：

```
<activity android:name=".SecondActivity">
            <intent-filter>
                <action android:name="com.example.myintenttest.ACTION_START" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
```  
2. 修改FirstActivity中的按钮点击事件，代码如下：  
            


```
public void onClick(View view) {
    Intent intent = new Intent("com.example.myintenttest.ACTION_START");//与intent-filter中的action标签相匹配
    startActivity(intent);
}
```  
3. 重新运行程序，点击FirstActivity界面的按钮，成功启动SecondActivity，只不过这次使用隐式方式启动，说明<activity>标签下配置的action和category内容已生效。  

![Alt text](img/intent2.png)  

### 隐式Intent的其他用法  

隐式Intent不仅可以启动自己程序内的活动，还可以启动其他程序的活动，使得Android多个应用程序之间的功能共享成为可能。      

#### 使用隐式Intent调用系统浏览器  
1. 修改FirstActivity中按钮点击事件的代码：  
          
```
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("http://www.baidu.com"));
startActivity(intent);
```  
2.  重新运行程序，点击FirstActivity界面的按钮，可以看到系统浏览器打开并成功访问百度主页，如图：  
![Alt text](img/intent3.png)  







