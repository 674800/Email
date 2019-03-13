package org.lxz.utils.android.debug;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import aa.utils.init.InitAppValue;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class LocalLogRunnable implements Runnable {

	private Context mContext;
	private Throwable ex;
	private String log_file_path;

	public LocalLogRunnable(Context mContext, Throwable ex) {
		super();
		this.mContext = mContext;
		this.ex = ex;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		String dir = Environment.getExternalStorageDirectory().getPath()
				+ "/log/";

		try {
			new File(dir).mkdirs();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		log_file_path = dir + "log_" + System.currentTimeMillis() + ".txt";
		;

		File file = new File(log_file_path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		accessFile(log_file_path,
				"system is The breakdown of the system, this is the final collapse of the log.");
		accessFile(log_file_path, "\n---------------------------------------\n");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间

		accessFile(log_file_path, "exception-time;" + time + "\n");
		accessFile(log_file_path, "exception-Class;" + ex.toString() + "\n");
		accessFile(log_file_path, "exception-Message;" + ex.getMessage() + "\n");
		accessFile(log_file_path,
				"exception-LocalizedMessage;" + ex.getLocalizedMessage() + "\n");

		accessFile(log_file_path, "\n---------------------------------------\n");
		StringBuffer sb = new StringBuffer();
		sb.append(ex.toString());
		StackTraceElement[] ste = ex.getStackTrace();
		for (StackTraceElement e : ste) {
			accessFile(log_file_path, e.toString() + "\n");
		}

		if (InitAppValue.getInstance().isOpenSystemCrash()) {
			Intent intent = new Intent();
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// 设置intent的Action属性
			intent.setAction(Intent.ACTION_VIEW);
			// 获取文件file的MIME类型
			String type = getMIMEType(file);
			// 设置intent的data和Type属性。
			intent.setDataAndType(Uri.fromFile(file), type);
			// 跳转
			mContext.startActivity(intent);
		}
		if( ! InitAppValue.getInstance().isSendErrorToEmail())
		{
	    	 android.os.Process.killProcess(android.os.Process.myPid());
		}

	}

	public String getLog_file_path() {
		return log_file_path;
	}

	public void setLog_file_path(String log_file_path) {
		this.log_file_path = log_file_path;
	}

	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 * 
	 * @param file
	 */
	private String getMIMEType(File file) {

		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	/** 对指定文件追加内容 */
	public synchronized static void accessFile(String fileName, String content) {
		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(fileName, "rw");
			long fileLength = randomFile.length();

			randomFile.seek(fileLength);
			randomFile.write(content.getBytes("utf-8"));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 打开方式 */
	private final String[][] MIME_MapTable = {
			// {后缀名， MIME类型}
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" },
			{ ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" },
			{ ".c", "text/plain" },
			{ ".class", "application/octet-stream" },
			{ ".conf", "text/plain" },
			{ ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" },
			{ ".h", "text/plain" },
			{ ".htm", "text/html" },
			{ ".html", "text/plain" },
			{ ".jar", "application/java-archive" },
			{ ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" },
			{ ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" },
			{ ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" },
			{ ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" },
			{ ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
			{ ".z", "application/x-compress" },
			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };

}
