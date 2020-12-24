# 服务的基本用法  

## 如何定义一个服务  

### 步骤  
新建一个项目，命名为`MyServiceTest`。依次点击`com.example.myservicetest→New→Service→Service`，`AndroidStudio`弹出窗口如下：  

![Alt text](./service1.png)  

### 服务的两个属性  
|  属性  | 说明  |
|  ----  | ----  |
| Exported | 表示是否允许除了当前程序之外的其他程序访问此服务 |
| Enabled | 表示是否启用这个服务 |  

### Service类与方法  
+ MyService代码如下：  

```
public class MyService extends Service {

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
```  

+ Service中的方法    
|  方法   | 调用时间  | 说明 |
|  ----  | ----  | ---- | 
| `onCreate()`  | 在服务第一次创建时调用 | 无 |
| `onStartCommand()`  | 在每次服务启动的时候调用 | 若希望服务一旦启动就立刻去执行某个动作，可将逻辑写在此方法中 |  
| `onDestroy()` | 在服务销毁的时候调用 | 服务销毁时，应在此方法中去回收那些不再使用的资源 |  

## 启动和停止服务  

### 启动和停止服务实践  
1. 在`activity_main.xml`中加入两个按钮分别用于启动和停止服务  

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/start_service"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Start Service" />

    <Button
        android:id="@+id/stop_service"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Stop Service" />

</LinearLayout>
```  

2. 在`onCreate()`方法中获取**Start Service**和**Stop Service**按钮的实例，并给它们注册点击事件  

```
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = (Button) findViewById(R.id.start_service);
        Button stopService = (Button) findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
    }
```  

3. 在**Start Service**按钮的点击事件里，构建出一个**Intent**对象，调用`startService()`方法来启动**MyService**这个服务。同理，停止**MyService**服务只需在**Stop Service**中采用同样的操作即可，代码如下：  

```
Override
public void onClick(View view) {
    switch (view.getId()) {
        case R.id.start_service:
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
            break;

        case R.id.stop_service:
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
            break;
        default:
            break;
    }
}
```  

4. 在**MyService**的几个方法中加入打印日志，证实服务已成功启动或停止  

```
@Override
public void onCreate() {
    super.onCreate();
    Log.d("MyService", "onCreate executed");
}

@Override
public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d("MyService", "onStartCommand executed");
    return super.onStartCommand(intent, flags, startId);
}

@Override
public void onDestroy() {
    super.onDestroy();
    Log.d("MyService", "onDestroy executed");
}
```  

5. 测试程序  
主界面：  
![Alt text](./service2.png)  

点击**START SERVICE**按钮，启动服务时的打印日志：  
![Alt text](./service3.png)  

点击**STOP SERVICE**按钮，停止服务时的打印日志：  
![Alt text](./service4.png)  

## 活动和服务之间进行通信  

### 实践   
1. 在**MyService**里提供一个下载功能，然后在活动中可以决定何时开始下载，以及随时查看下载进度。  
2.   创建一个**Binder**对象对下载功能进行管理。
	+ 新建**DownLoadBinder**类，并让它继承**Binder**，这里添加了两个模拟开始下载以及查看下载进度的方法。
	+ 创建**DownLoadBinder**实例，并在`onBind()`方法中返回该实例。  修改**MyService**中的代码如下：  

```
private DownLoadBinder mBinder = new DownLoadBinder();

    class DownLoadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }
```  

```
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
```  

3. 在布局文件中添加用于绑定服务和取消绑定服务的按钮，代码如下：  

```
<Button
    android:id="@+id/bind_service"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Bind Service" />

<Button
    android:id="@+id/unbind_service"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Unbind Service" />
```  

4. 绑定活动与服务，修改**MainActivity**中的代码如下：  

```
private MyService.DownLoadBinder downLoadBinder;
private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        downLoadBinder = (MyService.DownLoadBinder) iBinder;
        downLoadBinder.startDownload();
        downLoadBinder.getProgress();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
};
```  

```
Button bindService = (Button) findViewById(R.id.bind_service);
Button unbindService = (Button) findViewById(R.id.unbind_service);
bindService.setOnClickListener(this);
unbindService.setOnClickListener(this);
```  

```
case R.id.bind_service:
    Intent bindIntent = new Intent(this, MyService.class);
    bindService(bindIntent, connection, BIND_AUTO_CREATE);
    break;
case R.id.unbind_service:
    unbindService(connection);
    break;
```  
5. 重新运行程序
+ 主界面  
![Alt text](./service5.png)

+ 点击Bind Service按钮，打印日志如下：  

![Alt text](./service6.png)  



















  





