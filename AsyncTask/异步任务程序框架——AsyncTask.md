# 异步任务程序框架——AsyncTask  

## AsyncTask定义  
+ 一个Android已封装好的轻量级异步类。是一个**抽象类**，使用时须创建一个子类去继承它  
+ 一个简单的自定义AsyncTask可写成如下方式：  

```
public abstract class AsyncTask<Params, Progress, Result> { 
 ... 
 }  
```  

## AsyncTask作用  
+ 实现多线程
	+ 例：在主线程中执行耗时任务  

+ 异步通信、消息传递
	+ 实现工作线程、主线程（UI线程）之间的通信，即：将工作线程的执行结果传递给主线程，从而在主线程中执行相关的UI操作，保证线程安全  

## AsyncTask优点  
+ 方便实现异步通信
	+ 无需考虑异步消息处理机制，也无需专门使用Handler来发送和接收消息。
+ 节省资源
	+ 采用线程池的缓存线程和复用线程，避免了频繁创建或销毁线程所带来的系统资源开销  

## AsyncTask类与方法  

### AsyncTask类中3种泛型参数  

子类继承AsyncTask时可指定3个泛型参数，用途如下：  

|  泛型参数   | 用途  |
|  ----  | ----  |
| Params  | 开始异步任务执行时传入的参数类型，可用于在后台任务中使用 |
| Progress  | 异步任务执行过程中，若需要显示当前进度，则使用这里指定的泛型作为进度单位 |
| Result   | 异步任务执行完成后，返回的结果类型，与`doInBackground()`的返回值类型保持一致 |

### AsyncTask核心方法  
|  方法   | 作用  | 调用时刻 | 备注 |
|  ----  | ----  | ---- | ---- |
| `onPreExecute()`  | 执行线程任务前的操作 | 执行线程任务前系统自动调用 | 用于进行一些界面上的初始化操作，例如显示一个进度条对话框 |
| `doInBackground(Params…)`  | 接收输入参数，处理任务中的耗时操作，返回线程任务执行的结果 | 执行线程任务时系统自动调用 | 不可进行UI操作，如果需要更新UI元素，可以调用`publishProgress(Progress…)`方法来完成|
| `onProgressUpdate(Progress…)`| 在主线程显示线程任务执行的进度 | 调用`publishProgress(Progress…)`方法时，自动调用| 该方法可对UI进行操作，利用参数中的数值就可以对界面元素进行相应更新 |  
| `onPostExecute(Result)` | 接收线程任务的执行结果，将结果显示到UI组件 | 后台任务执行完毕时，自动调用 | 可利用返回数据进行一些UI操作，例如提醒任务执行结果、关闭进度条对话框等 |  

## AsyncTask实践  

### 实验目的  
1. 点击按钮，开启线程执行线程任务  
2. 显示加载进度  
3. 加载完毕后更新UI组件，若中途点击取消按钮，则取消加载  

### 核心代码  
+ **MainActivity.java**  

```
public class MainActivity extends AppCompatActivity {

    MyTask mTask;

    Button button, cancel; 
    TextView text;
    ProgressBar progressBar; 

    private class MyTask extends AsyncTask<String, Integer, String> {
    
        @Override
        protected void onPreExecute() {
            text.setText("加载中");
            // 执行前显示提示
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                int count = 0;
                int length = 1;
                while (count < 99) {
                
                    count += length;
                    publishProgress(count);
                    // 模拟耗时任务
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progresses) {

            progressBar.setProgress(progresses[0]);
            text.setText("loading..." + progresses[0] + "%");

        }
        
        @Override
        protected void onPostExecute(String result) {
            // 执行完毕后，则更新UI
            text.setText("加载完毕");
        }

        @Override
        protected void onCancelled() {

            text.setText("已取消");
            progressBar.setProgress(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 绑定UI组件
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.cancel);
        text = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        
        mTask = new MyTask();

        // 加载按钮按按下时，则启动AsyncTask
        // 任务完成后更新TextView的文本
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
                mTask.execute();
            }
        });

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消一个正在执行的任务,onCancelled方法将会被调用
                mTask.cancel(true);
            }
        });

    }

}
 
```
+ **activity_main.xml**  

```
<Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="点我加载" />

    <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/button"
        android:layout_centerInParent="true"
        android:text="未加载!" />

    <ProgressBar
        android:id="@+id/progress_bar"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/text"
        android:max="100"
        android:progress="0" />

    <Button
        android:id="@+id/cancel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/progress_bar"
        android:layout_centerInParent="true"
        android:text="cancel" />
```  

### 运行结果  
1. 点击按钮执行线程任务并显示进度
![Alt text](./AsyncTask1.png)  

2. 点击**CANCEL**按钮，取消加载
![Alt text](./AsyncTask2.png)  












