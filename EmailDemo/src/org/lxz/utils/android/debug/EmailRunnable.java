package org.lxz.utils.android.debug;

import java.io.File;
import java.io.IOException;

import org.lxz.utils.android.email.MailSender;
import org.lxz.utils.android.info.ApplitionInfo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class EmailRunnable implements Runnable {
	
	private static String user;
	private static String passwrod;
	private static String mailhost;
	private static String receiver;	
	
	private String subject;
	private String body;

	private MailSender sender;
	private static String attachment;
	

	private Context mContext;
	private Throwable ex;
	public EmailRunnable(Context mContext, Throwable ex) {
		// TODO Auto-generated constructor stub
		this.mContext=mContext;
		this.ex=ex;
	}



	@Override
	public void run() {
		MailSender sender = new MailSender(user, passwrod);
		sender.setMailhost(mailhost);
		
//		attachment=Environment.getExternalStorageDirectory()
//				.getPath()+"/a.txt";
//		
//		try {
//			new File(attachment).createNewFile();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		try {
			sender.sendMail(subject, body, user, receiver,attachment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}



	public String getUser() {
		return user;
	}



	public static void setUser(String user) {
		EmailRunnable.user = user;
	}



	public String getPasswrod() {
		return passwrod;
	}



	public static void setPasswrod(String passwrod) {
		EmailRunnable.passwrod = passwrod;
	}



	public String getMailhost() {
		return mailhost;
	}



	public static void setMailhost(String mailhost) {
		EmailRunnable.mailhost = mailhost;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public String getReceiver() {
		return receiver;
	}



	public static  void setReceiver(String receiver) {
		EmailRunnable.receiver = receiver;
	}







	public String getAttachment() {
		return attachment;
	}



	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}



	@Override
	public String toString() {
		return "EmailRunnable [user=" + user + ", passwrod=" + passwrod
				+ ", mailhost=" + mailhost + ", subject=" + subject + ", body="
				+ body + ", receiver=" + receiver + ", sender=" + sender
				+ ", attachment=" + attachment + ", mContext=" + mContext
				+ ", ex=" + ex + "]";
	}



	public MailSender getSender() {
		return sender;
	}



	public void setSender(MailSender sender) {
		this.sender = sender;
	}



	public Context getmContext() {
		return mContext;
	}



	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}



	public Throwable getEx() {
		return ex;
	}



	public void setEx(Throwable ex) {
		this.ex = ex;
	}
	
	

}
