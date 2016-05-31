package com.jiuxianqiao.txq;

import com.jiuxianqiao.txq.R;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private static final String URL = "http://192.168.8.199";
	private WebView wv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //加载网页
        wv = (WebView)findViewById(R.id.webView);
        initWebView();
    }

    //加载网页
    @SuppressLint("NewApi") private void initWebView(){
    	//加载web资源
    	wv.loadUrl(URL);
    	//覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
    	wv.setWebViewClient(new WebViewClient() {
    		public boolean shouldOverrideUrlLoading(WebView view, String url) {
    			return false;
    		}            
    		@Override            
    		public WebResourceResponse shouldInterceptRequest(WebView view,String url) {   
    			if (url.startsWith("http") || url.startsWith("https")) {
    				return super.shouldInterceptRequest(view, url);
    			} else {
    				Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));                    
    				startActivity(in);                    
    				return null;                
    			}            
    		}        
    	});
    	initSettint(wv);
    }
    
    @SuppressLint("NewApi") 
    private void initSettint(WebView wv){
    	WebSettings webSettings = wv.getSettings();
    	//设置支持JavaScript
    	webSettings.setJavaScriptEnabled(true);  
    	
    	//webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//优先使用缓存
    	webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存
    	webSettings.setJavaScriptEnabled(true);  
    	webSettings.setJavaScriptCanOpenWindowsAutomatically(true);  
    	webSettings.setUseWideViewPort(true);//关键点  
    	
    	webSettings.setDisplayZoomControls(false);  
    	webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本  
    	webSettings.setAllowFileAccess(true); // 允许访问文件  
    	webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮  
    	webSettings.setSupportZoom(true); // 支持缩放  
    	  
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
    	 * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：  
    	 * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放  
    	 */  
    	webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
    }
    
    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
        	if(wv.canGoBack()) {//返回上一页面
                wv.goBack();
                return true;
            }else{
            	return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
