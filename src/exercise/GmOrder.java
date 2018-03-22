package exercise;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GmOrder {
	
	public static void main(String[] args) {
//		send();
		sendMail();
	}
	
	
	
	public static void send(){
		Map<String, String> reqMap = new HashMap<String, String>();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("gm_order", "floatball_msg");
//		jsonObj.put("gm_order", "broad_msg");
//		jsonObj.put("gm_order", "serverop");
//		jsonObj.put("gm_order", "silenced");
//		jsonObj.put("gm_order", "unsilenced");
//		jsonObj.put("gm_order", "collect_mail");
//		jsonObj.put("gm_order", "kick_player");
//		jsonObj.put("gm_order", "skip_guide");
//		jsonObj.put("gm_order", "client_notify");
//		jsonObj.put("gm_order", "collect_chat");
//		jsonObj.put("gm_order", "role_mail");
//		jsonObj.put("gm_order", "query_account");
//		jsonObj.put("aid", 100);
		jsonObj.put("serverid", 11); 	
//		jsonObj.put("optype", "reload");
		jsonObj.put("roleid", 11000510);
//		jsonObj.put("channel", 1);
//		jsonObj.put("mailid", 1320874029582902l);
//		jsonObj.put("time", 200);
		jsonObj.put("msg", ".a.a.a.a..a.a.a.a.a");
//		jsonObj.put("gm_order", "server_mail");
//		JSONArray serverList = new JSONArray();
//		serverList.add(0);
//		jsonObj.put("serverlist", serverList);
		JSONArray  roleList = new JSONArray();
		roleList.add(2000032);
		jsonObj.put("rolelist", roleList);
		JSONArray  itemList = new JSONArray();
		for (int i = 0; i < 2 ; i ++){
			JSONObject item = new JSONObject();
			item.put("itemid", 100);
			item.put("num", 50);
			itemList.add(item);
		}
		JSONObject mailObj = new JSONObject();
		mailObj.put("subject", "gm补偿");
		mailObj.put("sendname", "发送者");
		mailObj.put("content", "内容");
		mailObj.put("attached", itemList);
		jsonObj.put("mail", mailObj);
		
		//mail":{"subject":"gm补偿","sendname":"发送者"，"content":"内容","attached":[{"itemid":100,"num":50},{"itemid":101,"num":50}]}}
		reqMap.put("type", "gm");
		String rst = HttpUtil.clientPost("192.168.1.222",9009,"",jsonObj,reqMap);
		System.out.println(rst);
	} 
	
	
	
	public static void sendMail(){
		Map<String, String> reqMap = new HashMap<String, String>();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("gm_order", "server_mail");
		jsonObj.put("serverid", 2); 	
		JSONArray  itemList = new JSONArray();
		for (int i = 0; i < 2 ; i ++){
			JSONObject item = new JSONObject();
			item.put("itemid", 100);
			item.put("num", 50);
			itemList.add(item);
		}
		JSONObject mailObj = new JSONObject();
		mailObj.put("subject", "gm补偿");
		mailObj.put("sendname", "发送者");
		mailObj.put("content", new Date().toLocaleString());
		mailObj.put("attached", itemList);
		jsonObj.put("mail", mailObj);
		reqMap.put("type", "gm");
		String rst = HttpUtil.clientPost("192.168.1.222",9009,"",jsonObj,reqMap);
		System.out.println(rst);
	} 
	
	
	
	
	

}
