package aa.utils.init;

import org.lxz.utils.android.debug.EmailRunnable;

import android.app.Application;

public class InitApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		InitAppValue.init(this);
		InitAppValue value = InitAppValue.getInstance();
		EmailRunnable.setUser(value.getSendMailUserName());
		EmailRunnable.setPasswrod(value.getSendMailPassWord());
		EmailRunnable.setReceiver(value.getReceiveMailUserName());
		EmailRunnable.setMailhost(value.getSendMailHostUrl());

	}
}
