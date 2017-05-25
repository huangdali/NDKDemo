## android studio 编译c代码为动态库步骤

### 1、创建一个用于关联c代码的类（映射类）

方法名需要加native修饰

    public class NDKUtils {
    	public static native int getSum(int a, int b);
    }
    
### 2、使用javah生成头文件【可选】-->目的是为了拿到函数的声明

进入到app\src\main\java ，使用javah 全类名（上面创建的映射类，不要有中文注释）

找到生成的c文件拿到函数声明（注意此处是没有参数名的哦）：

    JNIEXPORT jint JNICALL Java_com_jwkj_ffmpeg_NDKUtils_getSum
      (JNIEnv *, jclass, jint, jint);




### 3、创建c实现的文件（eg:ndkutils.c）

android studio切换到Project预览模式，找到src/main，新建jni文件夹，然后新建一个对应的c文件，将上面拿到的函数声明拷贝到c文件中。（记得自己带上参数名，如env clazz a b）

    #include "stdio.h"
    #include "jni.h"
    
    JNIEXPORT jint JNICALL Java_com_jwkj_ffmpeg_NDKUtils_getSum
    (JNIEnv *env, jclass clazz, jint a, jint b) {
    	return a+b;
    }
    
### 4、配置gradle

找到app的gradle，在defaultConfig里面添加：
    
    ndk {
		    moduleName "ndkDemo" //生成的so库名字
		    abiFilters "armeabi", "armeabi-v7a", "x86"  //输出指定三种abi体系结构下的so库。
     }

### 5、Make Project

Make成功之后，即可在app/build/intermediates/ndk下产生so动态库文件


### 6、为映射类关联库文件
1、在src/main下面建立jniLibs文件夹；

![](http://i.imgur.com/x8fxHXe.png)

2、将动态库文件（连同文件夹一起）拷贝到src/main


    public class NDKUtils {
	    static {
	    	System.loadLibrary("ndkDemo");
	    }
	    public static native int getSum(int a, int b);
    }
    
    
3、此时可以将jni目录下的代码全部删除（已经不需要）

4、最后的目录结构图和gragle配置图

![](http://i.imgur.com/wbGOa2b.png)

![](http://i.imgur.com/coP073g.png)


### 7、使用ndk

    public class MainActivity extends AppCompatActivity {
    
    private TextView tvReuslt;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    tvReuslt = (TextView) findViewById(R.id.tv_result);
	    tvReuslt.append(NDKUtils.getSum(4, 2) + "");
    }
    }

运行结果：
    
![](http://i.imgur.com/RanQpqO.png)








