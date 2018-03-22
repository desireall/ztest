package exercise;

import java.net.URLEncoder;

import org.apache.http.client.methods.HttpGet;

public class UrlError {

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		String url = "http://ma62.gmsdk.gameyw.netease.com/app/gen_token.json?pid=ma62&uid=t123457_t123456&type=1&game_server=2&game_uid=2000003&nickname=%nonameyet&vip=0&level=1&os=other&time=1479695548727&sign=e35040f67b96b334fc34120129965297";

		System.err.println(url.charAt(132));

		String aa = "%nonameyet";

		try {

//			String newUrl = URLEncoder.encode("name=" + aa, "UTF-8");
			System.err.println(url);
			HttpGet request = new HttpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
