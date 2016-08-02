package com.jiuxianqiao.txq;

import java.lang.Thread.UncaughtExceptionHandler;  

import android.app.Application;  
import android.content.Intent;  
import android.content.IntentFilter;
import android.net.ConnectivityManager;
  
/**
 * ��������˳����Զ�����
 * @author ZHA
 *
 */
public class AppContext extends Application{  
    protected static AppContext instance;  
    public void onCreate() {  
        super.onCreate();  
        instance = this;  
        Thread.setDefaultUncaughtExceptionHandler(restartHandler); // �������ʱ�����߳�  �������������������쳣    
        
        
        //��̬ע��㲥���˹㲥���ڼ�������״̬
        IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION); 
        registerReceiver(new NewWorkReceiver(), mFilter);
    }  
    // �����������ڲ�������쳣    
    private UncaughtExceptionHandler restartHandler = new UncaughtExceptionHandler() {    
        public void uncaughtException(Thread thread, Throwable ex) {    
            restartApp();//���������쳣ʱ,����Ӧ��    
        }    
    };    
    public void restartApp(){  
        Intent intent = new Intent(instance,MainActivity.class);  
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        instance.startActivity(intent);  
        //android.os.Process.killProcess(android.os.Process.myPid());  //��������֮ǰ���԰�������ע�������˳����������δ���֮ǰ  
    }  
}  
