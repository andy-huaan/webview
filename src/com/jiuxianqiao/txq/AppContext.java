package com.jiuxianqiao.txq;

import java.lang.Thread.UncaughtExceptionHandler;  

import android.app.Application;  
import android.content.Intent;  
import android.content.IntentFilter;
import android.net.ConnectivityManager;
  
/**
 * 程序崩溃退出，自动重启
 * @author ZHA
 *
 */
public class AppContext extends Application{  
    protected static AppContext instance;  
    public void onCreate() {  
        super.onCreate();  
        instance = this;  
        Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程  以下用来捕获程序崩溃异常    
        
        
        //动态注册广播，此广播用于监听网络状态
        IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION); 
        registerReceiver(new NewWorkReceiver(), mFilter);
    }  
    // 创建服务用于捕获崩溃异常    
    private UncaughtExceptionHandler restartHandler = new UncaughtExceptionHandler() {    
        public void uncaughtException(Thread thread, Throwable ex) {    
            restartApp();//发生崩溃异常时,重启应用    
        }    
    };    
    public void restartApp(){  
        Intent intent = new Intent(instance,MainActivity.class);  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        instance.startActivity(intent);  
        //android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前  
    }  
}  
