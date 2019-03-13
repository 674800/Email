package aa.utils.init;

import org.dom4j.Element;
import org.lxz.utils.myjava.xml.XmlElementFactory;

import com.xanxus.emaildemo.R;

import android.content.Context;
import android.util.Log;

/***
<?xml version="1.0" encoding="utf-8"?>
<Application-configuration xmlns:android="http://schemas.android.com/apk/res/android">
    <!--是否测试环境-->
	<IsTestEnvironment value="true" />
	<!--是否显示测试Toast-->
	<IsDebugToast value="true" />
	<!--是否显示测试数据-->
	<IsDebugLog value="true" />
	<!--是否使用测试数据-->
	<IsDebugData value="false" />
	<!--是否单元测试-->
	<IsUnitTest value="false" />
    <!--字符编码-->
	<Charset value="utf-8" />
	
    <!--是否在SdCard上生成日志-->
	<IsCreateFileLog value="true" />
	<!--SdCard上生成日志路径-->
	<CreateFileLogPath value="\Log\" />
	
	<!--是否在崩溃时掉以html或者方式打开-->
	<IsOpenSystemCrash value="true" />
    <!--是否在崩溃时发送日志到指定邮箱-->
	<IsSendErrorToEmail value="true" />
	<!--发送邮箱的用户名-->
	<SendMailHostUrl value="stmp.qq.com" />
	<!--发送邮箱的用户名-->
	<SendMailUserName value="" />
	<!--发送邮箱的密码-->
	<SendMailPassWord value="" />
	<!--要发送的邮箱-->
	<ReceiveMailUserName value="" />
	
</Application-configuration>
	***/
public class InitAppValue {

	public static void init(Context context){
		InitAppValue.context=context;
	};
	private static Context context;
	private static InitAppValue initAppValue;
	public static InitAppValue getInstance(){
		return initAppValue==null?initAppValue=new InitAppValue(context):initAppValue;
	}
	
	public InitAppValue(Context context) {
		// TODO Auto-generated constructor stub
		element = XmlElementFactory.decodeRoot(context.getResources().openRawResource(R.raw.configuration_appinit));
		
		isTestEnvironment=getElementBoolean("IsTestEnvironment");
		isDebugToast=getElementBoolean("IsDebugToast");
		isDebugLog=getElementBoolean("IsDebugLog");
		isDebugData=getElementBoolean("IsDebugData");
		isUnitTest=getElementBoolean("IsUnitTest");
		charset=getElementValue("Charset");
		isCreateFileLog=getElementBoolean("IsCreateFileLog");
		createFileLogPath=getElementValue("CreateFileLogPath");
		isOpenSystemCrash=getElementBoolean("IsOpenSystemCrash");
		isSendErrorToEmail=getElementBoolean("IsSendErrorToEmail");
		sendMailHostUrl=getElementValue("SendMailHostUrl");
		sendMailUserName=getElementValue("SendMailUserName");
		sendMailPassWord=getElementValue("SendMailPassWord");
		receiveMailUserName=getElementValue("ReceiveMailUserName");
		
	}

	
	private Element element;
	
	private boolean isTestEnvironment;
	private boolean isDebugToast;
	private boolean isDebugLog;
	private boolean isDebugData;
	private boolean isUnitTest;
	private String charset;
	private boolean isCreateFileLog;
	private String createFileLogPath;
	private boolean isOpenSystemCrash;
	private boolean isSendErrorToEmail;
	private String sendMailHostUrl;
	private String sendMailUserName; //<!--发送邮箱的用户名-->
	private String sendMailPassWord;
	private String receiveMailUserName;
	
	private boolean getElementBoolean(String key){
		return Boolean.valueOf(((Element)element.selectSingleNode("//"+key)).attributeValue("value"));
		
	}
	
	private String getElementValue(String key){
		return ((Element)element.selectSingleNode("//"+key)).attributeValue("value");
		
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		InitAppValue.context = context;
	}

	public InitAppValue getInitAppValue() {
		return initAppValue;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public boolean isTestEnvironment() {
		return isTestEnvironment;
	}

	public void setTestEnvironment(boolean isTestEnvironment) {
		this.isTestEnvironment = isTestEnvironment;
	}

	public boolean isDebugToast() {
		return isDebugToast;
	}

	public void setDebugToast(boolean isDebugToast) {
		this.isDebugToast = isDebugToast;
	}

	public boolean isDebugLog() {
		return isDebugLog;
	}

	public void setDebugLog(boolean isDebugLog) {
		this.isDebugLog = isDebugLog;
	}

	public boolean isDebugData() {
		return isDebugData;
	}

	public void setDebugData(boolean isDebugData) {
		this.isDebugData = isDebugData;
	}

	public boolean isUnitTest() {
		return isUnitTest;
	}

	public void setUnitTest(boolean isUnitTest) {
		this.isUnitTest = isUnitTest;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isCreateFileLog() {
		return isCreateFileLog;
	}

	public void setCreateFileLog(boolean isCreateFileLog) {
		this.isCreateFileLog = isCreateFileLog;
	}

	public String getCreateFileLogPath() {
		return createFileLogPath;
	}

	public void setCreateFileLogPath(String createFileLogPath) {
		this.createFileLogPath = createFileLogPath;
	}

	public boolean isOpenSystemCrash() {
		return isOpenSystemCrash;
	}

	public void setOpenSystemCrash(boolean isOpenSystemCrash) {
		this.isOpenSystemCrash = isOpenSystemCrash;
	}

	public boolean isSendErrorToEmail() {
		return isSendErrorToEmail;
	}

	public void setSendErrorToEmail(boolean isSendErrorToEmail) {
		this.isSendErrorToEmail = isSendErrorToEmail;
	}

	public String getSendMailHostUrl() {
		return sendMailHostUrl;
	}

	public void setSendMailHostUrl(String sendMailHostUrl) {
		this.sendMailHostUrl = sendMailHostUrl;
	}

	public String getSendMailUserName() {
		return sendMailUserName;
	}

	public void setSendMailUserName(String sendMailUserName) {
		this.sendMailUserName = sendMailUserName;
	}

	public String getSendMailPassWord() {
		return sendMailPassWord;
	}

	public void setSendMailPassWord(String sendMailPassWord) {
		this.sendMailPassWord = sendMailPassWord;
	}

	public String getReceiveMailUserName() {
		return receiveMailUserName;
	}

	public void setReceiveMailUserName(String receiveMailUserName) {
		this.receiveMailUserName = receiveMailUserName;
	}

	@Override
	public String toString() {
		return "InitAppValue [isTestEnvironment=" + isTestEnvironment
				+ ", isDebugToast=" + isDebugToast + ", isDebugLog="
				+ isDebugLog + ", isDebugData=" + isDebugData + ", isUnitTest="
				+ isUnitTest + ", charset=" + charset + ", isCreateFileLog="
				+ isCreateFileLog + ", createFileLogPath=" + createFileLogPath
				+ ", isOpenSystemCrash=" + isOpenSystemCrash
				+ ", isSendErrorToEmail=" + isSendErrorToEmail
				+ ", sendMailHostUrl=" + sendMailHostUrl
				+ ", sendMailUserName=" + sendMailUserName
				+ ", sendMailPassWord=" + sendMailPassWord
				+ ", receiveMailUserName=" + receiveMailUserName + "]";
	}
}
