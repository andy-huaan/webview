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
        
        //������ҳ
        wv = (WebView)findViewById(R.id.webView);
        initWebView();
    }

    //������ҳ
    @SuppressLint("NewApi") private void initWebView(){
    	//����web��Դ
    	wv.loadUrl(URL);
    	//����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
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
}
