# 常用UI控件的使用及基本布局  

## TextView  
功能：主要用于在界面上显示一段文本信息  

下面通过代码测试TextView的相关用法  

修改activity_main.xml中默认生成的代码：  

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical">

<TextView
	android:id="@+id/text_view"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:text="This is my first TextView！"></TextView>

</LinearLayout> 
```  
首先关注控件具有的一些属性，总结如下  

|  属性     |     说明 |
| :--------: | :--------:|
| android:id    |   给当前控件定义唯一标识符 |
|android:layout_width|指定控件的宽度。match_parent:当前控件的大小和父布局相同，wrap_content：让当前控件的大小能够刚好包含住里面内容|
|android:layout_height|指定控件的高度|
|android:visibility|指定控件的可见性。visible：可见；invisible：不可见，但仍然占据原来的屏幕空间；gone：不可见，但不会占据屏幕空间|  

运行程序，效果如图   

![TextView运行效果](img/TextView01.png)  

**注：TextView的文字默认居左上角对齐**  

修改TestView的文字对齐方式，只需设置android:gravity属性即可  

```
 android:gravity="center"
```  
重新运行程序，效果如图  

![Alt text](img/TextView02.png)  

几个注意的点：
 + android:gravity用于指定文字的对齐方式，可选值有top、bottom、left、right、center等，可以用 | 来同时指定多个值 
 + 设置文字对齐方式的属性是 android:gravity，而不是**android:layout_gravity（坑） **

还可以对TextView中文字的大小和颜色进行修改 ，添加代码  
```
	android:textSize="30sp"
	android:textColor="#800080"
```  
重新运行程序，效果如图  

![Alt text](img/TextView03.png)

总结：  

| 属性      |     说明 |
| :--------: | :--------:|
| android:text    |   指定TextView中显示的文本内容 |
| android:gravity | 指定文字的对齐方式，可选值有 top、bottom、left、right、center 等，可以用竖线来同时指定多个值|
| android:textSize | 指定文字的大小 |
| android:textColor | 指定文字的颜色 |
