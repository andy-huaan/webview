package com.jiuxianqiao.txq;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 权限检查
 * @author ZHA
 *
 */
public class PermissionCheck {
	
	public static final String PERMISSION_SAY_HELLO = "com.zhaohuaan.permission.test";
	
	public static void sayHello(Context context){
		int checkResult = context.checkCallingOrSelfPermission(PERMISSION_SAY_HELLO);
		if(checkResult == PackageManager.PERMISSION_DENIED){//无权限
			throw new SecurityException("没有分配com.zhaohuaan.permission.test权限");
		}
		if(checkResult == PackageManager.PERMISSION_GRANTED){//有权限
			System.out.println("已经分配了权限");
		}
	}
}
