package org.lxz.utils.android.info;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.Log;

public class ApplitionInfo {
	
    /**
     * 获取状态栏
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        Rect frame = new Rect();
        if (context instanceof Activity) {
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            return frame.top;
        }
        return 20;
    }
    
//    /**
//     * 获取屏幕高度
//     *
//     * @param context
//     * @return
//     */
//    public static int getHeightPixels(Context context) {
//        if (heightPixels == 0) {
//            heightPixels = getDisPlayMetrics(context).heightPixels;
//        }
//        return heightPixels;
//    }
//    
//    /**
//     * 获取屏幕分辨率
//     *
//     * @param context
//     * @return
//     */
//    public static float getDensity(Context context) {
//        if (density == 0) {
//            if (metrics == null) {
//                getDisPlayMetrics(context);
//            }
//            density = metrics.density;
//        }
//        return density;
//    }
//
//    
//    /**
//     * 获取
//     *
//     * @param context
//     * @return
//     */
//    public static DisplayMetrics getDisPlayMetrics(Context context) {
//        if (metrics == null) {
//            metrics = new DisplayMetrics();
//            if (context instanceof Activity) {
//                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
//            }
//        }
//        return metrics;
//    }
    
    /**
     * @return the ANDROID_ID that identify the device, or the "emulator" string
     * on the emulator.
     */
    public static String getAndroidId(Context context) {
        String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        if (androidId == null || androidId.length() <= 0) {
            androidId = "emulator";
        }
        return androidId;
    }
    /**
     * @return the version name of the application
     */
    public static String getVersionName(Context context) {
        String versionName = "unknown version";
        try {
            android.content.pm.PackageManager packageMng = context.getPackageManager();
            if (packageMng != null) {
                PackageInfo packageInfo = packageMng.getPackageInfo(context.getPackageName(), 0);
                if (packageInfo != null) {
                    versionName = packageInfo.versionName;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return versionName;
    }



    /**
     * @return the version code of the application
     */
    public static int getVersionCode(Context context) {
        if (context == null) {
            return -1;
        }
        int versionCode = -1;
        try {
            PackageManager packageMng = context.getPackageManager();
            if (packageMng != null) {
                PackageInfo packageInfo = packageMng.getPackageInfo(context.getPackageName(), 0);
                if (packageInfo != null) {
                    versionCode = packageInfo.versionCode;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }

        return versionCode;
    }
    
    /**
     * @return Android SDK, Version, Manufacturer, Model, Device
     */
    @SuppressLint("DefaultLocale")
    public static String getSDK() {
        return String.format("%d-%s", android.os.Build.VERSION.SDK_INT, android.os.Build.VERSION.RELEASE);
    }
    
    /**
     * @return true if the app is debuggable, false otherwise
     */
    public static boolean isDebuggable(Context context) {
        if (context == null) {
            return false;
        }

        ApplicationInfo appInfo = context.getApplicationInfo();
        if (appInfo != null) {
            return ((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
        } else {
            return (ApplicationInfo.FLAG_DEBUGGABLE != 0);
        }
    }


    /**
     * @return true if this device has Amazon Market App installed
     */
    public static boolean hasAmazonMarketApp(Context context) {
        if (context == null) {
            return false;
        }
        try {
            PackageManager packageMng = context.getPackageManager();
            if (packageMng != null) {
                return (null != packageMng.getPackageInfo("com.amazon.venezia", 0));
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
