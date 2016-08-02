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
		if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {//ֻ����CONNECTIVITY_ACTION���͹㲥
			ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo  netInfo=connectivityManager.getActiveNetworkInfo();
	        if(netInfo != null && netInfo.isAvailable()) {  
	            /////////////��������  
	           //String name = netInfo.getTypeName();  
	           if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){ 
	        	   System.out.println("-------------------WIFI����--------------");
	           }else if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
	        	   System.out.println("-------------------��������--------------"); 
	           }else if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){  
	        	   System.out.println("-------------------3G����--------------");
	           }  
	       } else { 
	    	   System.out.println("-------------------����Ͽ�----------");
	       } 
		} else{
			System.out.println("------------�����ܴ˹㲥---------------------");
		}
	}
}
