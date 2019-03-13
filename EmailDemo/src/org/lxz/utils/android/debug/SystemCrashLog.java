package org.lxz.utils.android.debug;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lxz.utils.android.info.ApplitionInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.util.Log;


/**
 * 系统崩溃日志
 * @author Aiushtha
 */
@SuppressLint("SimpleDateFormat")
public class SystemCrashLog implements UncaughtExceptionHandler,Runnable{
	
	
	/**单例*/
	private static SystemCrashLog INSTANCE ;
	/**上下文环境*/
	private Context mContext;
	/**错误*/
	private Throwable ex;

	/**初始化*/
	public static SystemCrashLog init(Context context) {
		return INSTANCE=(INSTANCE==null?new SystemCrashLog(context):INSTANCE);
	}
    /**构造方法*/
	public SystemCrashLog(Context ctx) {
		mContext = ctx;
		Thread.setDefaultUncaughtExceptionHandler(this);
	}
	/**捕获异常并处理*/
	@Override
	public void uncaughtException(Thread thread, final Throwable ex) {
		this.ex=ex;
		LocalLogRunnable  localLogRunnable=new LocalLogRunnable(mContext,ex);

		
		String subject="应用程序"+" "+"EmailDemo"+" "+"发生了一个崩溃";
		StringBuffer sb=new StringBuffer();
		sb.append("android-id:"+ApplitionInfo.getAndroidId(mContext)+"\n")
		.append("android-code:"+ApplitionInfo.getVersionCode(mContext)+"\n")
		.append("android-version:"+ApplitionInfo.getVersionName(mContext)+"\n");

		
		localLogRunnable.run();
		
	    EmailRunnable emailRunnable=new EmailRunnable(mContext,ex);
	    emailRunnable.setSubject(subject);
	    emailRunnable.setBody(sb.toString());
	    emailRunnable.setAttachment(localLogRunnable.getLog_file_path());
		new Thread(emailRunnable).start();;
	}

	public String getApplicationName() { 
        PackageManager packageManager = null; 
        ApplicationInfo applicationInfo = null; 
        try { 
            packageManager = mContext.getApplicationContext().getPackageManager(); 
            applicationInfo = packageManager.getApplicationInfo(mContext.getApplicationContext().getPackageName(), 0); 
        } catch (PackageManager.NameNotFoundException e) { 
            applicationInfo = null; 
        } 
        String applicationName =  
        (String) packageManager.getApplicationLabel(applicationInfo); 
        return applicationName; 
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	} 
 
     



}