package com.tool.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

public class TestHttpClientCookies {
	private static Charset charset = Charset.forName("UTF-8");
	private static final int CONNECT_TIME_OUT = 10000;
	private static final int CONNECTION_REQUEST_TIME_OUT = 10000;
	private static HttpResponse response = null;
	private static HttpClientBuilder httpClientBuilder = null;
	private static CloseableHttpClient client = null;
	private static BasicCookieStore cookieStore = new BasicCookieStore();

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		client = HttpClients.createDefault();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		// 登录
		HttpUriRequest login = RequestBuilder.get()
				.setUri(new URI("https://passport.ceair.com/cesso/login!auth.shtml"))
				.addParameter("dynamicPassword", "").addParameter("mobileNo", "")
				.addParameter("at", "2")
				// .addParameter("site", "0")
				.build();
		CloseableHttpResponse response = client.execute(login);
//		for (Cookie cookie : cookieStore.getCookies()) {
//			System.out.println(cookie.getName() + "\t:\t" + cookie.getValue());
//		}
//		System.out.println("--------------------------------------------");
//		for (Header head : response.getAllHeaders()) {
//			System.out.println(head.getName() + "\t:\t" + head.getValue());
//		}
//		System.out.println("============================================");
//
//		StringBuffer buffer = new StringBuffer();
//		int count = 0;
//		for (Cookie cookie : cookieStore.getCookies()) {
//			if (count > 0) {
//				buffer.append(";");
//			}
//			buffer.append(cookie.getName()).append("=").append(cookie.getValue());
//			count++;
//		}
//		count = 0;
//		// 确认订单
//		HttpUriRequest confirm = RequestBuilder.get()
//				.setUri(new URI("http://www.ceair.com/flight/flight_booking_confirm.html"))
//				.addParameter(
//						"allPaxInfo",
//						"[{\"id\":\"44740965\",\"paxType\":\"ADT\",\"paxName\":\"张三\",\"paxNameLast\":\"\",\"paxNameFirst\":\"\",\"birthday\":\"\",\"idNo\":\"987654321\",\"idValidDt\":\"\",\"mobile\":\"18311112222\",\"email\":\"\",\"docaNationCode\":\"CN\",\"ffpAirline\":\"MU\",\"ffpNo\":\"\",\"idIssueNation\":\"\",\"idType\":\"\",\"nationality\":\"\",\"docaState\":\"\",\"docaStreet\":\"\",\"docaCity\":\"\",\"docaPostCode\":\"\",\"infCarrierName\":\"\",\"add\":true,\"insuranceName\":\"\",\"insurance\":false,\"gender\":\"M\"}]")
//				.addParameter("contactInfo",
//						"{\"contactName\":\"奥特曼\",\"contactMobile\":\"18322222222\",\"contactEmail\":\"\"}").build();
//		confirm.setHeaders(login.getAllHeaders());
////		confirm.addHeader("Cookie", buffer.toString());
//		client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//		response = client.execute(confirm);
////		System.out.println(EntityUtils.toString(response.getEntity(), charset));
//		
//		for (Cookie cookie : cookieStore.getCookies()) {
//			System.out.println(cookie.getName() + "\t:\t" + cookie.getValue());
//		}
//		System.out.println("--------------------------------------------");
//		for (Header head : response.getAllHeaders()) {
//			System.out.println(head.getName() + "\t:\t" + head.getValue());
//		}
//		System.out.println("============================================");
//		
//		buffer.delete(0,buffer.length());
//		for (Cookie cookie : cookieStore.getCookies()) {
//			if (count > 0) {
//				buffer.append(";");
//			}
//			buffer.append(cookie.getName()).append("=").append(cookie.getValue());
//			count++;
//		}
		
		// 预定
		HttpUriRequest booking = RequestBuilder.get()
				.setUri(new URI("http://www.ceair.com/booking/booking!booking.shtml"))
				// .addParameter("sessionVersion", "2015-01-31 14:27:15.55")
				.build();
//		booking.setHeaders(confirm.getAllHeaders());
		booking.addHeader("Cookie", "Webtrends=124.65.158.26.1422685813979707; JSESSIONID=0000DHaGcdZfJVWWN3_0LrjQZgd:17o8bn9i9; Hm_lvt_78f4a647b0173245e793a01999ad882b=1422586044,1422667473,1422683735,1422684810; Hm_lpvt_78f4a647b0173245e793a01999ad882b=1422695698; com.ceair.cesso=F8771279F6A15794A5E439A88EBA4D0125846D66EADB78CA5CBBB104D64A9395; ffpno=18310728878; passportId=C59C939F1E6294BE69970CA929F6E26A; flightParam=~'tripType'*'OW'%2C'adtCount'*1%2C'chdCount'*0%2C'infCount'*0%2C'currency'*'CNY'%2C'sortType'*'t'%2C'segmentList'*%5B~'deptCdTxt'*'%E5%8C%97%E4%BA%AC'%2C'deptCd'*'NAY%23'%2C'deptNation'*'CN'%2C'deptRegion'*'CN'%2C'deptCityCode'*'BJS'%2C'arrCd'*'HFE%23'%2C'arrCdTxt'*'%E5%90%88%E8%82%A5'%2C'arrNation'*'CN'%2C'arrRegion'*'CN'%2C'arrCityCode'*'HFE'%2C'deptDt'*'2015-02-07'!%5D!; Hm_lvt_ee5764a7e879b473a7e73ec86161fbaf=1422683927,1422684810,1422687417,1422689952; Hm_lpvt_ee5764a7e879b473a7e73ec86161fbaf=1422695822");
//		client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		response = client.execute(booking);
		

		System.out.println("============================================");
		for (Header head : response.getAllHeaders()) {
			System.out.println(head.getName() + "\t:\t" + head.getValue());
		}
		System.out.println("--------------------------------------------");
		for (Cookie cookie : cookieStore.getCookies()) {
			System.out.println(cookie.getName() + "\t:\t" + cookie.getValue());
		}
		
		System.out.println(EntityUtils.toString(response.getEntity(), charset));
		
		
		
	}

	public static void setCookieStore(HttpResponse httpResponse) {
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();

		String JSESSIONID = setCookie.substring("JSESSIONID=".length() - 1, setCookie.indexOf(";"));
		System.out.println("JSESSIONID:" + JSESSIONID);
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
		// Header[] cookies = httpResponse.getHeaders("Set-Cookie");
		// for (Header head : cookies) {
		// String[] values = head.getValue().split("; ");
		//
		// for (String str : values) {
		// if (str.indexOf("=")>-1) {
		// String[] va = str.split("=");
		// cookie.setAttribute(va[0], va[1]);
		// }
		// }
		//
		// }

		cookie.setDomain(".ceair.com");
		cookie.setPath("/");
		cookie.setVersion(0);
		cookieStore.addCookie(cookie);
	}

}
