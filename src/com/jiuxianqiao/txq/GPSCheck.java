package com.jiuxianqiao.txq;

import java.util.List;

import android.content.Context;
import android.location.LocationManager;

/**
 * ÅÐ¶ÏGPSÊÇ·ñ´ò¿ª
 * @author ZHA
 *
 */
public class GPSCheck {
	
	public boolean isGpsEnabled(Context context) {  
        LocationManager locationManager = ((LocationManager) context  
        		.getSystemService(Context.LOCATION_SERVICE));   
        List<String> accessibleProviders = locationManager.getProviders(true);  
        return accessibleProviders != null && accessibleProviders.size() > 0;  
    }  
}
