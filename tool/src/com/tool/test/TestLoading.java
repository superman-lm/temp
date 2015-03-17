package com.tool.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tool.eastair.entity.MUFlight;
import com.tool.eastair.entity.MUItem;
import com.tool.eastair.entity.MUResponse;
import com.tool.eastair.entity.MUSegment;
import com.tool.util.frequent.HttpClientUtils;
import com.tool.util.frequent.UtilForJson;

public class TestLoading {
	
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
//		Loading loading = new Loading();
//		loading.init();
		
//		File file = new File("D:\\RSS\\2015-1-6\\content\\3800631e6ce9428f9133487b441b32f0\\3800631e6ce9428f9133487b441b32f0-e79214b642b04d6eb1812876876d7d6d.txt");
//		System.out.println((file.length()/1024)+1);
		
		//查询
//		Charset charset = Charset.forName("UTF-8");
//		String url = "http://www.ceair.com/booking/flight-search!doFlightSearch.shtml";
//    	String json = "{\"tripType\":\"OW\",\"adtCount\":1,\"chdCount\":1,\"infCount\":0,\"currency\":\"CNY\",\"sortType\":\"t\",\"segmentList\":[{\"deptCdTxt\":\"\",\"deptCd\":\"PVG#\",\"deptNation\":\"CN\",\"deptRegion\":\"CN\",\"deptCityCode\":\"BJS\",\"arrCd\":\"TAO#\",\"arrCdTxt\":\"\",\"arrNation\":\"CN\",\"arrRegion\":\"CN\",\"arrCityCode\":\"HGH\",\"deptDt\":\"2015-01-31\"}]}";
//    	System.out.println(json);
//    	final Map<String, String> entitys = new HashMap<String, String>();
//        entitys.put("searchCond", json);
//        HttpResponse res = HttpClientUtils.doPost(url, entitys, charset,null);
//        String resJson = EntityUtils.toString(res.getEntity(), charset);
//        System.out.println(resJson);
//        resJson = resJson.replace("flightSearchResultDto =flightSearchResultDto = ", "");
//        UtilForJson forJson = new UtilForJson();
//        MUResponse response = forJson.readeValue(resJson, MUResponse.class);
//        for (MUItem item : response.getTripItemList()) {
//			for (MUFlight flight : item.getAirRoutingList()) {
//				for (MUSegment seg : flight.getFlightList()) {
//					System.out.println(seg.getFlightNo());
//				}
//				System.out.println();
//			}
//		}
        
        
        //预定
//        Charset charset = Charset.forName("UTF-8");
//		String url = "http://www.ceair.com/booking/paxinfo-input!showBookingInfoNew.shtml?_=0.8971036900766194";
//    	String allPaxInfo = "[{\"id\":\"44740965\",\"paxType\":\"ADT\",\"paxName\":\"张三\",\"paxNameLast\":\"\",\"paxNameFirst\":\"\",\"birthday\":\"\",\"idNo\":\"987654321\",\"idValidDt\":\"\",\"mobile\":\"18311112222\",\"email\":\"\",\"docaNationCode\":\"CN\",\"ffpAirline\":\"MU\",\"ffpNo\":\"\",\"idIssueNation\":\"\",\"idType\":\"\",\"nationality\":\"\",\"docaState\":\"\",\"docaStreet\":\"\",\"docaCity\":\"\",\"docaPostCode\":\"\",\"infCarrierName\":\"\",\"add\":true,\"insuranceName\":\"\",\"insurance\":false,\"gender\":\"M\"}]";
//    	String contactInfo = "{\"contactName\":\"奥特曼\",\"contactMobile\":\"18322222222\",\"contactEmail\":\"\"}";
//    	String sessionVersion = "2015-01-28 15:53:15.55";
//    	System.out.println(allPaxInfo);
//    	System.out.println(contactInfo);
//    	final Map<String, String> entitys = new HashMap<String, String>();
//        entitys.put("allPaxInfo", allPaxInfo);
//        entitys.put("contactInfo", contactInfo);
//        entitys.put("sessionVersion", sessionVersion);
//        String res = (String)HttpClientUtils.doPost(url, entitys, charset);
//        System.out.println(res);
        
		//登录
//        Charset charset = Charset.forName("UTF-8");
//		String url = "https://passport.ceair.com/cesso/login!auth.shtml";
//    	//String json = "{\"tripType\":\"OW\",\"adtCount\":1,\"chdCount\":0,\"infCount\":0,\"currency\":\"CNY\",\"sortType\":\"t\",\"segmentList\":[{\"deptCdTxt\":\"北京\",\"deptCd\":\"NAY#\",\"deptNation\":\"CN\",\"deptRegion\":\"CN\",\"deptCityCode\":\"BJS\",\"arrCd\":\"PVG#\",\"arrCdTxt\":\"上海\",\"arrNation\":\"CN\",\"arrRegion\":\"CN\",\"arrCityCode\":\"SHA\",\"deptDt\":\"2015-01-31\"}]}";
//    	//System.out.println(json);
//    	final Map<String, String> entitys = new HashMap<String, String>();
//        entitys.put("at", "2");
//        entitys.put("dynamicPassword", "996880");
//        entitys.put("mobileNo", "18310728878");
//        entitys.put("site", "0");
//        HttpResponse res = HttpClientUtils.doPost(url, entitys, charset,null);
////        Header head = res.getLastHeader("Location");
////        res = HttpClientUtils.doPost(head.getValue(), entitys, charset);
////        System.out.println(EntityUtils.toString(res.getEntity(), charset));
//        
//        
//        String booking_url = "http://www.ceair.com/booking/paxinfo-input!checkDataNew.shtml?_";
//    	String allPaxInfo = "[{\"id\":\"44740965\",\"paxType\":\"ADT\",\"paxName\":\"张三\",\"paxNameLast\":\"\",\"paxNameFirst\":\"\",\"birthday\":\"\",\"idNo\":\"987654321\",\"idValidDt\":\"\",\"mobile\":\"18311112222\",\"email\":\"\",\"docaNationCode\":\"CN\",\"ffpAirline\":\"MU\",\"ffpNo\":\"\",\"idIssueNation\":\"\",\"idType\":\"\",\"nationality\":\"\",\"docaState\":\"\",\"docaStreet\":\"\",\"docaCity\":\"\",\"docaPostCode\":\"\",\"infCarrierName\":\"\",\"add\":true,\"insuranceName\":\"\",\"insurance\":false,\"gender\":\"M\"}]";
//    	String contactInfo = "{\"contactName\":\"奥特曼\",\"contactMobile\":\"18322222222\",\"contactEmail\":\"\"}";
//    	String sessionVersion = "2015-01-28 15:53:15.55";
//    	System.out.println(allPaxInfo);
//    	System.out.println(contactInfo);
//    	final Map<String, String> entityss = new HashMap<String, String>();
//    	entityss.put("allPaxInfo", allPaxInfo);
//    	entityss.put("contactInfo", contactInfo);
//    	entityss.put("sessionVersion", sessionVersion);
//        HttpResponse bkres = HttpClientUtils.doPost(booking_url, entitys, charset,res);
//        System.out.println(EntityUtils.toString(bkres.getEntity(), charset));
        
//		HttpResponse res = HttpClientUtils.doGet("http://www.ceair.com");
//		System.out.println(EntityUtils.toString(res.getEntity(), charset));
	}

}
