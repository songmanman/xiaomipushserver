package xiaomi;

import java.util.ArrayList;
import java.util.List;

import pxing.MArticleBean;
import pxing.MArticleJsoupService;
import pxing.UrlUtils;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.TargetedMessage;

public class demo {
	static String MY_PACKAGE_NAME = "com.open.pxing";
	static String APP_SECRET_KEY = "kootiUKfsCSGfDCyv2UOTA==";
	static String APP_ID = "2882303761517603982";
//	static String APP_KEY="5111760359982";
//	static String regId="4CAD836FE10FA34BA42D98DC1FCA31EBB264EBAE";
	static String alias = "865932027267366";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			sendMessageToAlias();
//			sendMessageToAliases();
//			sendMessage();
//			sendMessages();
//			sendBroadcast();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void sendMessage() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload= "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     String regId="0";
	     sender.send(message, regId, 0); //根据regID，发送消息到指定设备上，不重试。
	     System.out.println("sendMessage");
	}

	private static void sendMessages() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     List<TargetedMessage> messages = new ArrayList<TargetedMessage>();
	     TargetedMessage targetedMessage1 = new TargetedMessage();
	     targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, alias);
	     String messagePayload1= "This is a message1";
	     String title1 = "notification title1";
	     String description1 = "notification description1";
	     Message message1 = new Message.Builder()
	                .title(title1)
	                .description(description1).payload(messagePayload1)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     targetedMessage1.setMessage(message1);
	     messages.add(targetedMessage1);
	     
	     TargetedMessage targetedMessage2 = new TargetedMessage();
	     targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, alias);
	     String messagePayload2= "This is a message2";
	     String title2 = "notification title2";
	     String description2 = "notification description2";
	     Message message2 = new Message.Builder()
	                .title(title2)
	                .description(description2).payload(messagePayload2)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     targetedMessage2.setMessage(message2);
	     messages.add(targetedMessage2);
	     sender.send(messages, 0); //根据alias，发送消息到指定设备上，不重试。
	     System.out.println("sendMessages");
	}

	private static void sendMessageToAlias() throws Exception {
	     Constants.useOfficial();
	     List<MArticleBean>  list = MArticleJsoupService.parsePXMainTopPager(UrlUtils.PXING,1);
	     java.util.Random random=new java.util.Random();// 定义随机类
		 int result=random.nextInt(list.size());// 返回[0,10)集合中的整数，注意不包括10
		 MArticleBean bean = list.get(result);
		 
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload = "This is a message";
	     String title = "pxing 养眼美女";
	     String description = bean.getAlt();
//	     String alias = "865932027267366";    //alias非空白，不能包含逗号，长度小于128。
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.sendToAlias(message, alias, 0); //根据alias，发送消息到指定设备上，不重试。
	     System.out.println("sendMessageToAlias");
	}

	private static void sendMessageToAliases() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload = "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     List<String> aliasList = new ArrayList<String>();
	     aliasList.add(alias);  //alias非空白，不能包含逗号，长度小于128。
	     aliasList.add("testAlias2");  //alias非空白，不能包含逗号，长度小于128。
	     aliasList.add("testAlias3");  //alias非空白，不能包含逗号，长度小于128。
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.sendToAlias(message, aliasList, 0); //根据aliasList，发送消息到指定设备上，不重试。
	}

	private static void sendBroadcast() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload = "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     String topic = "testTopic";
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.broadcast(message, topic, 0); //根据topic，发送消息到指定一组设备上，不重试。
	}
//	
//	private Message buildMessage() throws Exception {
//	     
//	     String messagePayload = "This is a message";
//	     String title = "notification title";
//	     String description = "notification description";
//	     Message message = new Message.Builder()
//	             .title(title)
//	             .description(description).payload(messagePayload)
//	             .restrictedPackageName(MY_PACKAGE_NAME)
//	             .passThrough(1)  //消息使用透传方式
////	             .notifyType(1)     // 使用默认提示音提示
//	             .extra("flow_control", "4000")     // 设置平滑推送, 推送速度4000每秒(qps=4000)
//	             .build();
//	     return message;
//	}
//	
//	private List<TargetedMessage> buildMessages() throws Exception {
//	     List<TargetedMessage> messages = new ArrayList<TargetedMessage>();
//	     TargetedMessage message1 = new TargetedMessage();
//	     message1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias1");
//	     message1.setMessage(buildMessage());
//	     messages.add(message1);
//	     TargetedMessage message2 = new TargetedMessage();
//	     message2.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias2");
//	     message2.setMessage(buildMessage());
//	     messages.add(message2);
//	     return messages;
//	}

}
