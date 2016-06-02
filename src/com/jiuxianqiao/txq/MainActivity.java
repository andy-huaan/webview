package com.jiuxianqiao.txq;

import com.jiuxianqiao.txq.R;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String URL = "http://192.168.8.185";
	private WebView wv;
	ProgressBar pb;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //����״̬����ɫ
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintColor(Color.rgb(24, 106, 231));
        
        //������ҳ
        wv = (WebView)findViewById(R.id.webView);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        initWebView();
    }

    //������ҳ
    @SuppressLint("NewApi") 
    private void initWebView(){
    	//����web��Դ
    	wv.loadUrl(URL);
    	wv.setWebChromeClient(new WebChromeClient());
    	//����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
    	wv.setWebViewClient(new WebViewClient() {
    		public boolean shouldOverrideUrlLoading(WebView view, String url) {
    			if(url.startsWith("mqqwpa")){
    				if(!checkApkExist(MainActivity.this,"com.tencent.mqq")){
	    				Toast toast = Toast.makeText(getApplicationContext(),
							     "����QQʧ��,��ȷ���Ѱ�װQQ", Toast.LENGTH_LONG);
						toast.show();
						view.loadUrl(URL);
    				}
    			}
    			return false;
    		}            
    		@Override            
    		public WebResourceResponse shouldInterceptRequest(WebView view,String url) {   
    			if (url.startsWith("http") || url.startsWith("https")) {
    				return super.shouldInterceptRequest(view, url);
    			} else if(url.startsWith("mqqwpa")){
    				Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));                    
    				startActivity(in); 
    				return null; 
    			}else{
    				return null;
    			}
    		} 
    	});
    	initSettint(wv);
    }
    
    @SuppressLint("NewApi") 
    private void initSettint(WebView wv){
    	WebSettings webSettings = wv.getSettings();
    	//����֧��JavaScript
    	webSettings.setJavaScriptEnabled(true);  
    	
    	//webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//����ʹ�û���
    	webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//��ʹ�û���
    	webSettings.setJavaScriptEnabled(true);  
    	webSettings.setJavaScriptCanOpenWindowsAutomatically(true);  
    	webSettings.setUseWideViewPort(true);//�ؼ���  
    	
    	webSettings.setDisplayZoomControls(false);  
    	webSettings.setJavaScriptEnabled(true); // ����֧��javascript�ű�  
    	webSettings.setAllowFileAccess(true); // ��������ļ�  
    	webSettings.setBuiltInZoomControls(true); // ������ʾ���Ű�ť  
    	webSettings.setSupportZoom(true); // ֧������  
    	  
    	webSettings.setLoadWithOverviewMode(true);  
    	DisplayMetrics metrics = new DisplayMetrics();  
    	getWindowManager().getDefaultDisplay().getMetrics(metrics);  
    	int mDensity = metrics.densityDpi;  
    	if (mDensity == 240) {   
    		webSettings.setDefaultZoom(ZoomDensity.FAR);  
    	} else if (mDensity == 160) {  
    	    webSettings.setDefaultZoom(ZoomDensity.MEDIUM);  
    	} else if(mDensity == 120) {  
    		webSettings.setDefaultZoom(ZoomDensity.CLOSE);  
    	}else if(mDensity == DisplayMetrics.DENSITY_XHIGH){  
    		webSettings.setDefaultZoom(ZoomDensity.FAR);   
    	}else if (mDensity == DisplayMetrics.DENSITY_TV){  
    		webSettings.setDefaultZoom(ZoomDensity.FAR);   
    	}else{
    	    webSettings.setDefaultZoom(ZoomDensity.MEDIUM);  
    	}  
    	  
    	/**  
    	 * ��WebView��ʾͼƬ����ʹ��������� ������ҳ�������ͣ� 1��LayoutAlgorithm.NARROW_COLUMNS ��  
    	 * ��Ӧ���ݴ�С 2��LayoutAlgorithm.SINGLE_COLUMN:��Ӧ��Ļ�����ݽ��Զ�����  
    	 */  
    	webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
    }
    
    //��д�������������ص��߼�
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
        	if(wv.canGoBack()) {//������һҳ��
                wv.goBack();
                return true;
            }else{
            	return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    
    public boolean checkApkExist(Context context, String packageName) {
    	if (packageName == null || "".equals(packageName))
    		return false;
    	try {
    		ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
    				PackageManager.GET_UNINSTALLED_PACKAGES);
    		return true;
    	} catch (NameNotFoundException e) {
    		return false;
    	}
    }
    public class WebChromeClient extends android.webkit.WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if (newProgress == 100) {
				pb.setVisibility(View.GONE);
			}else{
				pb.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}

	}
    
    @TargetApi(19) 
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
}
