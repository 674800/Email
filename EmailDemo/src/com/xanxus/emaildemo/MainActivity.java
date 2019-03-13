package com.xanxus.emaildemo;

import org.lxz.utils.android.debug.SystemCrashLog;

import com.xanxus.emaildemo.R;

import aa.utils.init.InitAppValue;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	private Button sendButton = null;
	TextView tv = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SystemCrashLog.init(this);
		sendButton = (Button) this.findViewById(R.id.send_btn);
		sendButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ("".equals(InitAppValue.getInstance().getSendMailUserName())) {
					Toast.makeText(getApplicationContext(),
							"发送的邮箱者不能为空，请在configuration_appinit.xml中配置", 1)
							.show();
					return;
				}
				if ("".equals(InitAppValue.getInstance().getSendMailPassWord())) {
					Toast.makeText(getApplicationContext(),
							"发送的邮箱者密码不能为空，请在configuration_appinit.xml中配置", 1)
							.show();
					return;
				}
				if ("".equals(InitAppValue.getInstance()
						.getReceiveMailUserName())) {
					Toast.makeText(getApplicationContext(),
							"接收到的邮箱者账号不能为空，请在configuration_appinit.xml中配置", 1)
							.show();
					return;
				}
				tv.setText("adb");
				// int i = 1 / 0;

			}
		});
	}

}
