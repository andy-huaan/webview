package com.jiuxianqiao.txq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NewWorkReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction(); 
		if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {//只接受CONNECTIVITY_ACTION类型广播
			ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo  netInfo=connectivityManager.getActiveNetworkInfo();
	        if(netInfo != null && netInfo.isAvailable()) {  
	            /////////////网络连接  
	           //String name = netInfo.getTypeName();  
	           if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){ 
	        	   System.out.println("-------------------WIFI网络--------------");
	           }else if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
	        	   System.out.println("-------------------有线网络--------------"); 
	           }else if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){  
	        	   System.out.println("-------------------3G网络--------------");
	           }  
	       } else { 
	    	   System.out.println("-------------------网络断开----------");
	       } 
		} else{
			System.out.println("------------不接受此广播---------------------");
		}
	}
}
