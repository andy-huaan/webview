package com.jiuxianqiao.txq;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Ȩ�޼��
 * @author ZHA
 *
 */
public class PermissionCheck {
	
	public static final String PERMISSION_SAY_HELLO = "com.zhaohuaan.permission.test";
	
	public static void sayHello(Context context){
		int checkResult = context.checkCallingOrSelfPermission(PERMISSION_SAY_HELLO);
		if(checkResult == PackageManager.PERMISSION_DENIED){//��Ȩ��
			throw new SecurityException("û�з���com.zhaohuaan.permission.testȨ��");
		}
		if(checkResult == PackageManager.PERMISSION_GRANTED){//��Ȩ��
			System.out.println("�Ѿ�������Ȩ��");
		}
	}
}
