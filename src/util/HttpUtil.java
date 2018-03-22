package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class HttpUtil {

//	private static final Logger logger = LoggerFactory
//			.getLogger(HttpUtil.class);
	
	public static final String UNKONWN_FAIL = "unknown fail";
	
	public static final String SUCCESS = "success";
	
	public static final String ERR_HTTP_CONN_EXCP = "http conn fail";
	
	public static final String SO_TIME_OUT_EXCP = "http fail read timed out";
	
	private static final int SO_TIMEOUT = 10000;//读取数据超时
	
	private static final int REQ_CONN_TIME_OUT = 5000;//请求连接的超时时间
	
	private static HttpClient httpClient;
	
	static{
		
		RequestConfig reqConfig = RequestConfig.custom().setConnectTimeout(REQ_CONN_TIME_OUT).build();
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(SO_TIMEOUT).build();//设置超时,防止服务器不返回任何信息
		
		httpClient = HttpClientBuilder.create()
				.setDefaultSocketConfig(socketConfig)
				.setDefaultRequestConfig(reqConfig)
				.build();
	}
	

	public static String clientGet(Map<String, String> param, String ip,
			int port, String context) throws ClientProtocolException, IOException {
		
		// 创建Get方法实例
		String httpStr = "http://${ip}:${port}${context}?${params}";
		StringBuilder paramSb = new StringBuilder();
		paramSb.append("1=1");
		for (Entry<String, String> tmpEntry : param.entrySet()) {
			paramSb.append("&").append(tmpEntry.getKey()).append("=")
					.append(tmpEntry.getValue());
		}

		httpStr = httpStr.replace("${ip}", ip).replace("${port}", port + "").replace("${context}", context)
				.replace("${params}", paramSb.toString());

		HttpGet httpget = new HttpGet(httpStr);
		
		try{
			HttpResponse response = httpClient.execute(httpget);
			String charset = "UTF-8";
			return dealHttpResponse(response, charset);
		} catch(ConnectException e){
//			logger.error("error:", e);
			return ERR_HTTP_CONN_EXCP;
		} catch(SocketTimeoutException e){
			return SO_TIME_OUT_EXCP;
		} catch(ConnectTimeoutException e){
			return ERR_HTTP_CONN_EXCP;
		}catch(Exception e){
			e.printStackTrace();
			return UNKONWN_FAIL;
		}finally{
			httpget.abort();
		}
	}
	
	/**
	 * post请求,请求参数是json对象
	 * @param response
	 * @param charset
	 * @return
	 */
	public static String clientPost(String ip, int port, String context, JSONObject json, Map<String, String> reqParam){
		
		String url = "http://${ip}:${port}${context}?${params}";
		StringBuilder paramSb = new StringBuilder();
		paramSb.append("1=1");
		
		if(reqParam != null){
			for (Entry<String, String> tmpEntry : reqParam.entrySet()) {
				paramSb.append("&").append(tmpEntry.getKey()).append("=")
						.append(tmpEntry.getValue());
			}
		}
		
		url = url.replace("${ip}", ip).replace("${port}", port+"").replace("${context}", context).replace("${params}", paramSb.toString());
		HttpPost httpPost = new HttpPost(url);
		HttpEntity entity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
		
		httpPost.setEntity(entity);
		
		try {
			HttpResponse response  = httpClient.execute(httpPost);
			String charset = "UTF-8";
			return dealHttpResponse(response, charset);
		} catch(ConnectException e){
//			logger.error("error:", e);
			return ERR_HTTP_CONN_EXCP;
		} catch(SocketTimeoutException e){
			return SO_TIME_OUT_EXCP;
		} catch(ConnectTimeoutException e){
			return ERR_HTTP_CONN_EXCP;
		} catch(Exception e){
			return UNKONWN_FAIL;
		}finally{
			httpPost.abort();
		}
	}
	
	
	public static String dealHttpResponse(HttpResponse response, String charset){
		
		String result = UNKONWN_FAIL;
		try{
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				charset = getContentCharSet(entity);
				// 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1
				result = EntityUtils.toString(entity, charset);
				return result.equals("") ? SUCCESS : result;
			}
		}catch(Exception e){
//			logger.error("error:", e);
		}
		
		return result;
		
	}
	
	/**
	 * 检查URL是否可用
	 * @param ip
	 * @param port
	 * @param checkTimes 如果url不可用,多检查几次
	 * @return
	 */
	public static boolean checkURLAvalid(String ip, int port, int checkTimes) {
		boolean avalid = false;
		int haveCheckTime = 0;
		try {
			while(!avalid){
				
				if(haveCheckTime >= checkTimes){
					break;
				}
				
				try{
					Socket client = new Socket(ip, port);
				    avalid = true;
				    break;
				}catch(Exception e){
//				    logger.info("", e);
				}
				
				haveCheckTime++;
			}
		} catch (Exception e) {
//			logger.info("check ip:{} port:{} open INFO:", ip, port);
			return false;
		}
		
		return avalid;

	}

	public static String convertStreamToString(InputStream is, String cs)
			throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is, cs));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String getContentCharSet(final HttpEntity entity)
			throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		return charset;
	}
	
}
