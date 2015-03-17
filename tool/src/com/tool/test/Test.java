package com.tool.test;

import java.util.concurrent.CountDownLatch;










public class Test {
	
	static String url = "http://api.panatrip.cn/1.0/av/";
	
	
	public static void main(String[] args) throws Exception {
//		String json = \\"{\\\"OTAAirRoot\\\":{\\\"OTAAirHeader\\\":{\\\"timeStamp\\\":\\\"2014-11-26 22:12:42\\\",\\\"account\\\":\\\"etripbc@panatrip.net\\\",\\\"password\\\":\\\"91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203\\\"},\\\"OTAAirBody\\\":{\\\"bodyRequest\\\":{\\\"passengerInfos\\\":[{\\\"avkey\\\":\\\"b2c5a813eb386b74263bbb3884a09b27b74_ORD-ADT\\\",\\\"firstName\\\":\\\"JIA\\\",\\\"lastName\\\":\\\"QUANCAI\\\",\\\"sex\\\":\\\"M\\\",\\\"birthday\\\":\\\"1992-06-12\\\",\\\"country\\\":\\\"CHN\\\",\\\"passengerId\\\":\\\"ORD\\\",\\\"passengerType\\\":\\\"ADT\\\",\\\"IDType\\\":\\\"1\\\",\\\"issueCountry\\\":\\\"HKG\\\",\\\"ID\\\":\\\"888888\\\",\\\"expiryDate\\\":\\\"2018-08-08\\\",\\\"phone\\\":\\\"18610044578\\\",\\\"frequentType\\\":null,\\\"frequentId\\\":null,\\\"residentialInfo\\\":null}],\\\"avInfo\\\":{\\\"avMap\\\":[{\\\"no\\\":\\\"01\\\",\\\"departure\\\":\\\"PEK\\\",\\\"arrival\\\":\\\"HKG\\\",\\\"departureDate\\\":\\\"2014-11-27\\\",\\\"arrivalDate\\\":\\\"2014-11-27\\\"}],\\\"segMap\\\":[{\\\"no\\\":\\\"01\\\",\\\"segs\\\":[{\\\"segrph\\\":\\\"\\\",\\\"airLine\\\":\\\"CZ\\\",\\\"equipment\\\":\\\"321\\\",\\\"fltNo\\\":\\\"310\\\",\\\"departureAirport\\\":\\\"PEK\\\",\\\"arrivalAirport\\\":\\\"HKG\\\",\\\"departureDate\\\":\\\"2014-11-27\\\",\\\"departureTime\\\":\\\"09:05\\\",\\\"duration\\\":\\\"3:5\\\",\\\"arrivalDate\\\":\\\"2014-11-27\\\",\\\"arrivalTime\\\":\\\"12:10\\\",\\\"cabin\\\":\\\"V\\\",\\\"cabinNum\\\":\\\"\\\",\\\"term\\\":{\\\"dep\\\":\\\"\\\",\\\"arr\\\":\\\"\\\"},\\\"avService\\\":{\\\"tv\\\":\\\"\\\",\\\"meal\\\":\\\"\\\"}}]}],\\\"personMap\\\":[{\\\"type\\\":\\\"ORD-ADT\\\",\\\"baseFare\\\":\\\"1300\\\",\\\"tax\\\":\\\"226\\\",\\\"commission\\\":\\\"0\\\",\\\"priceSource\\\":\\\"\\\"}],\\\"policys\\\":[{\\\"type\\\":\\\"ORD-ADT\\\",\\\"infos\\\":{\\\"id\\\":\\\"2200000000000000423-8300000000000004027\\\",\\\"baseFare\\\":\\\"1230\\\",\\\"tax\\\":\\\"226\\\",\\\"commission\\\":\\\"0\\\",\\\"netFareTotal\\\":\\\"1456\\\",\\\"cacheKey\\\":\\\"b2c5a813eb386b74263bbb3884a09b27b74_ORD-ADT\\\"}}],\\\"route\\\":\\\"1\\\",\\\"cabinLev\\\":\\\"\\\",\\\"isFly\\\":\\\"TRUE\\\",\\\"airLine\\\":\\\"CZ\\\",\\\"connectNum\\\":\\\"\\\",\\\"rule\\\":\\\"{\\\\\"OTAAirRoot\\\\\":{\\\\\"OTAAirHeader\\\\\":{\\\\\"timeStamp\\\\\":\\\\\"2014-11-2621:55:04\\\\\",\\\\\"account\\\\\":\\\\\"etripbc@panatrip.net\\\\\",\\\\\"password\\\\\":\\\\\"91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203\\\\\"},\\\\\"OTAAirBody\\\\\":{\\\\\"bodyRequest\\\\\":{\\\\\"resultType\\\\\":\\\\\"1\\\\\",\\\\\"filingAirlineCode\\\\\":\\\\\"CZ\\\\\",\\\\\"arrivalAirport\\\\\":\\\\\"HKG\\\\\",\\\\\"departureAirportCode\\\\\":\\\\\"BJS\\\\\",\\\\\"departureDate\\\\\":\\\\\"2014-11-27T09:05:00\\\\\",\\\\\"fareReference\\\\\":\\\\\"VPOPQH6\\\\\",\\\\\"references\\\\\":{\\\\\"ref1\\\\\":\\\\\"GEPY01(USER(BJS,'1E',<>,Y,DEPT(<>,<>),<>,<>,<>),PF2(Y,[(AGENCY(BJS177),IATANUM('08301576'),N)],[],[],[],N,<>),<>)\\\\\",\\\\\"ref2\\\\\":\\\\\"884H062EHNADTY0030000100ATP2P\\\\\"}}}}}\\\",\\\"advancePurchase\\\":\\\"2014-11-26\\\"},\\\"remark\\\":null,\\\"returnUrl\\\":\\\"http://192.168.1.12:8088/www/callBack\\\",\\\"contact\\\":{\\\"mobile\\\":\\\"18610044578\\\",\\\"name\\\":\\\"admin\\\"}}}}}\\";
//		System.out.println(json);
//		String json1 = \\"{\\\"OTAAirRoot\\\":{\\\"OTAAirHeader\\\":{\\\"timeStamp\\\":\\\"2014-11-2621:55:04\\\",\\\"account\\\":\\\"etripbc@panatrip.net\\\",\\\"password\\\":\\\"91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203\\\"},\\\"OTAAirBody\\\":{\\\"bodyRequest\\\":{\\\"resultType\\\":\\\"1\\\",\\\"filingAirlineCode\\\":\\\"CZ\\\",\\\"arrivalAirport\\\":\\\"HKG\\\",\\\"departureAirportCode\\\":\\\"BJS\\\",\\\"departureDate\\\":\\\"2014-11-27T09:05:00\\\",\\\"fareReference\\\":\\\"VPOPQH6\\\",\\\"references\\\":{\\\"ref1\\\":\\\"GEPY01(USER(BJS,'1E',<>,Y,DEPT(<>,<>),<>,<>,<>),PF2(Y,[(AGENCY(BJS177),IATANUM('08301576'),N)],[],[],[],N,<>),<>)\\\",\\\"ref2\\\":\\\"884H062EHNADTY0030000100ATP2P\\\"}}}}}\\";
//		System.out.println(json1);
//		System.out.println(\\"{\\\"OTAAirRoot\\\":{\\\"OTAAirHeader\\\":{\\\"timeStamp\\\":\\\"2014-11-26 18:18:44\\\",\\\"account\\\":\\\"etripbc@panatrip.net\\\",\\\"password\\\":\\\"91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203\\\"},\\\"OTAAirBody\\\":{\\\"bodyRequest\\\":{\\\"passengerInfos\\\":[{\\\"avkey\\\":\\\"b2cc340fcee36af49ceba73324522449dab_ORD-ADT\\\",\\\"firstName\\\":\\\"JIA\\\",\\\"lastName\\\":\\\"QUANCAI\\\",\\\"sex\\\":\\\"M\\\",\\\"birthday\\\":\\\"1992-06-12\\\",\\\"country\\\":\\\"CHN\\\",\\\"passengerId\\\":\\\"ORD\\\",\\\"passengerType\\\":\\\"ADT\\\",\\\"IDType\\\":\\\"1\\\",\\\"issueCountry\\\":\\\"HKG\\\",\\\"ID\\\":\\\"888888\\\",\\\"expiryDate\\\":\\\"2018-08-08\\\",\\\"phone\\\":\\\"18610044578\\\",\\\"frequentType\\\":\\\"\\\",\\\"frequentId\\\":\\\"\\\",\\\"residentialInfo\\\":null}],\\\"avInfo\\\":{\\\"avMap\\\":[{\\\"no\\\":\\\"01\\\",\\\"departure\\\":\\\"PEK\\\",\\\"arrival\\\":\\\"SFO\\\",\\\"departureDate\\\":\\\"2014-11-27\\\",\\\"arrivalDate\\\":\\\"2014-11-28\\\"}],\\\"segMap\\\":[{\\\"no\\\":\\\"01\\\",\\\"segs\\\":[{\\\"segrph\\\":\\\"\\\",\\\"airLine\\\":\\\"CA\\\",\\\"equipment\\\":\\\"773\\\",\\\"fltNo\\\":\\\"985\\\",\\\"departureAirport\\\":\\\"PEK\\\",\\\"arrivalAirport\\\":\\\"SFO\\\",\\\"departureDate\\\":\\\"2014-11-27\\\",\\\"departureTime\\\":\\\"16:00\\\",\\\"duration\\\":\\\"19:20\\\",\\\"arrivalDate\\\":\\\"2014-11-28\\\",\\\"arrivalTime\\\":\\\"11:20\\\",\\\"cabin\\\":\\\"G\\\",\\\"cabinNum\\\":\\\"\\\",\\\"term\\\":{\\\"dep\\\":\\\"\\\",\\\"arr\\\":\\\"\\\"},\\\"avService\\\":{\\\"tv\\\":\\\"\\\",\\\"meal\\\":\\\"\\\"}}]}],\\\"personMap\\\":[{\\\"type\\\":\\\"ORD-ADT\\\",\\\"baseFare\\\":\\\"4260\\\",\\\"tax\\\":\\\"1431\\\",\\\"commission\\\":\\\"0\\\",\\\"priceSource\\\":\\\"\\\"}],\\\"policys\\\":[{\\\"type\\\":\\\"ORD-ADT\\\",\\\"infos\\\":{\\\"id\\\":\\\"2200000000000000356-8300000000000009990\\\",\\\"baseFare\\\":\\\"4091\\\",\\\"tax\\\":\\\"1431\\\",\\\"commission\\\":\\\"0\\\",\\\"netFareTotal\\\":\\\"5522\\\",\\\"cacheKey\\\":\\\"b2cc340fcee36af49ceba73324522449dab_ORD-ADT\\\"}}],\\\"route\\\":\\\"1\\\",\\\"cabinLev\\\":\\\"\\\",\\\"isFly\\\":\\\"TRUE\\\",\\\"airLine\\\":\\\"CA\\\",\\\"connectNum\\\":\\\"\\\",\\\"rule\\\":\\\"\\\",\\\"advancePurchase\\\":\\\"2014-11-26\\\"},\\\"remark\\\":\\\"\\\",\\\"returnUrl\\\":\\\"\\\"}}}}\\");
//		String json = \\"{\\\"OTAAirRoot\\\":{\\\"OTAAirHeader\\\":{\\\"timeStamp\\\":\\\"2013-12-25 03:56:13\\\",\\\"account\\\":\\\"fenxiaoshang@163.com\\\",\\\"password\\\":\\\"91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203\\\",\\\"subAccount\\\":\\\"xiaoshou24@163.com\\\"},\\\"OTAAirBody\\\":{\\\"bodyRequest\\\":{\\\"cabinPref\\\":{\\\"code\\\":\\\"ECONOMY\\\"},\\\"travelerAvail\\\":{\\\"passengerTypeQuantity\\\":[{\\\"type\\\":\\\"ORD-ADT\\\",\\\"quantity\\\":\\\"1\\\"}, {\\\"type\\\":\\\"ORD-CNN\\\",\\\"quantity\\\":\\\"1\\\"}]},\\\"searchRQ\\\":{\\\"directFlightsOnly\\\":\\\"false\\\",\\\"odInfos\\\":[{\\\"rph\\\":\\\"01\\\",\\\"originLocationCode\\\":\\\"PEK\\\",\\\"destinationLocationCode\\\":\\\"FRA\\\",\\\"departureDate\\\":\\\"2014-07-23\\\"}],\\\"odType\\\":\\\"01\\\"}}}}}\\";
//		System.out.println(json);
//		String json1 = \\"{\\\"OTAAirRoot\\\":{\\\"OTAAirHeader\\\":{\\\"timeStamp\\\":\\\"2013-12-25 03:56:13\\\",\\\"account\\\":\\\"niuguodong@gmail.com\\\",\\\"password\\\":\\\"91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203\\\",\\\"subAccount\\\":\\\"\\\"},\\\"OTAAirBody\\\":{\\\"bodyRequest\\\":{\\\"cabinPref\\\":{\\\"code\\\":\\\"ECONOMY\\\"},\\\"travelerAvail\\\":{\\\"passengerTypeQuantity\\\":[{\\\"type\\\":\\\"ORD-ADT\\\",\\\"quantity\\\":\\\"1\\\"}, {\\\"type\\\":\\\"ORD-CNN\\\",\\\"quantity\\\":\\\"1\\\"}]},\\\"searchRQ\\\":{\\\"directFlightsOnly\\\":\\\"false\\\",\\\"odInfos\\\":[{\\\"rph\\\":\\\"01\\\",\\\"originLocationCode\\\":\\\"PEK\\\",\\\"destinationLocationCode\\\":\\\"SYD\\\",\\\"departureDate\\\":\\\"2014-09-01\\\"}, {\\\"rph\\\":\\\"01\\\",\\\"originLocationCode\\\":\\\"SYD\\\",\\\"destinationLocationCode\\\":\\\"PEK\\\",\\\"departureDate\\\":\\\"2014-09-10\\\"}],\\\"odType\\\":\\\"02\\\"}}}}}\\";
//		System.out.println(json1);
		
//		String json = "{\"OTAAirRoot\":{\"OTAAirHeader\":{\"timeStamp\":\"2014-11-27 17:28:27\",\"account\":\"etripbc@panatrip.net\",\"password\":\"91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203\"},\"OTAAirBody\":{\"bodyRequest\":{\"cabinInfos\":[{\"airLine\":\"CA101\",\"cabin\":\"F\",\"odInfos\":{\"originLocationCode\":\"PEK\",\"destinationLocationCode\":\"HKG\",\"departureDate\":\"2014-11-28\"}},{\"airLine\":\"CA108\",\"cabin\":\"F\",\"odInfos\":{\"originLocationCode\":\"HKG\",\"destinationLocationCode\":\"PEK\",\"departureDate\":\"2014-11-30\"}}]}}}}";
//		System.out.println(json);
//		 UUID uuid = UUID.randomUUID();
//	     System.out.println(uuid.toString().replace("-", ""));
		
//		String content = "fenxiaoshang@163.com|91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203";
//		String password ="1234567890123456";
//		System.out.println("加密前："+content);
//		byte[] bs = UtilForEncrypt.enaes(content, password);
//		String result = UtilForEncoding.parseByte2To16(bs);
//		System.out.println("加密后："+result);
//		
//		bs = UtilForEncoding.parseHex16To2(result);
//		bs = UtilForEncrypt.deaes(bs, password);
//		System.out.println("解密后："+new String(bs));
		
		
//		System.out.println(UtilForEncoding.encode("/", "UTF-8"));
//		String str = "请授权OFFICE号BJS177";
//		str = str.replace("OFFICE","");
//		String reg = "[^0-9|a-z|A-Z]";
//		Pattern pat = Pattern.compile(reg);
//		Matcher mat = pat.matcher(str);
//		System.out.println(mat.replaceAll(""));
//		String json = "{\"cid\":\"fly2save\",\"sessionId\":\"310725147475900285\",\"orderNo\":\"TR140925001051bf\",\"pnrCode\":\"JV7E52\",\"routing\":{\"data\": \"f450db329bac4293ba26e62ecce07a2b\",\"fromSegments\": [{\"carrier\": \"CA\",\"depAirport\": \"PEK\",\"depTime\": \"201412201645\",\"arrAirport\": \"NRT\",\"arrTime\": \"201412202100\",\"stopCities\": \"\",\"codeShare\": false,\"cabin\": \"U\",\"aircraftCode\": \"\",\"flightNumber\": \"CA421\"}],\"retSegments\": [{\"carrier\": \"CA\",\"depAirport\": \"HND\",\"depTime\": \"201412250830\",\"arrAirport\": \"PEK\",\"arrTime\": \"201412251130\",\"stopCities\": \"\",\"codeShare\": false,\"cabin\": \"U\",\"aircraftCode\": \"\",\"flightNumber\": \"CA184\"}]}}";
//		System.out.println(json);
		
		
		
		
//		String str = "最短停留:3天最长停留:1个月退改规定:1.退票:自愿退票允许，收取手续费500人民币。2.更改:自愿订票允许，每人每次收取手续费200人民币。3.误机:无限制。";
//		String[] regs = new String[]{"(\\d\\.[^。]+)"};
////		Pattern pat = Pattern.compile(regs[0]);
////		Matcher mat = pat.matcher(str);
////		str = mat.replaceAll("");
////		System.out.println(str);
//		
//		
//		
////		if (!mat.find()) {
////			System.out.println("false");
////		}
//		
//		Pattern pat = Pattern.compile(regs[0]);
//		Matcher mat = pat.matcher(str);
//		int size = 0;
//		while (mat.find()) {
//			str =  mat.group();
//			int item = mat.end()-mat.start();
//			if (item>size) {
//				size = item;
//			}
//			System.out.println(mat.group()+"\t"+mat.start()+"----"+mat.end());
//		}
//		System.out.println(size);
		
		
//		String url = "http://news.baidu.com/ns?word=%B4%BA%BD%DA&tn=newsrss&sr=0&cl=2&rn=20&ct=0";
//		TestM.parseXml(new URL(url));
//		System.out.println(TestM.createXml());
		
		
		
		//HttpClientUtils.doPost(url, entitys, charSet)
		
//		System.out.println(TestM.method("199205121355", 4));
		
		
//			char[] chars = "199902011215".toCharArray();
//			StringBuffer buffer = new StringBuffer();
//			for (int i = 0; i < chars.length; i++) {
//				buffer.append(chars[i]);
//				if (i==3||i==5) {
//					buffer.append("-");
//				}
//				if (i==7) {
//					buffer.append(" ");
//				}
//				if (i==9) {
//					buffer.append(":");
//				}
//			}
//		System.out.println(buffer.toString().trim());
		
		
		
		
		 //String rule = "最短停留:7天最长停留:2个月。退改规定:1.退票：中国始发-        不允许。2.更改：中国始发-        允许，收取手续费1500人民币。        3.误机：中国始发-        退票，不允许。具体退改规定以各家航空公司为准。";
//		 String rule = "<span class=\"tip\">MU3178<div class=\"div-tip\" style=\"display: none;\"></div></span>";
//		 String reg = "[^<div>]\\S+[^</div>]";
//         Pattern pat = Pattern.compile(reg);
//         Matcher mat = pat.matcher(rule);
//         
//		System.out.println(mat.replaceAll(""));
		
//		String price = "1995";
//		if (price.substring(price.length()-1).equals("5")) {
//			price =String.valueOf( Integer.parseInt(price)+5);
//		}
//		System.out.println(price);
	       
		
	}
}
