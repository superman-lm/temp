package com.tool.test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tool.entity.eastair.ODSearch;
import com.tool.util.frequent.UtilForHttpUnit;
@SuppressWarnings("unused")
public class TestDome {
	/*** ��¼ҳ��·�� **/
	public static final String LOGIN_URL = "https://passport.ceair.com/cesso/login2!check.shtml?ltv=1&at=2&site=0&redirectUrl=http://www.ceair.com";
	/*** �б�ҳ��·�� **/
	public static String PATH = "http://www.ceair.com/flight2014/{depCity}-{arrCity}-{depDate}_CNY.html?#fliterParam=basecabin-all";
	/*** ���ڸ�ʽ **/
	private static final String DATE_FORMAT_PATTERN = "YYMMdd";
	/*** �˻� **/
	public static final String ACCOUNT = "18310728878";
	/*** ��̬�� **/
	public static final String PASSWORD = "996880";
	/*** ��׺��ȡ��\r\n�� **/
	public static final String SUFFIX_RN = "\r\n";
	/*** ��׺��ȡ���ո� **/
	public static final String SUFFIX_SPACE = " ";
	/*** �Ƿ�ֱ�� **/
	public static boolean isDirect = Boolean.TRUE;
	/*** �Ƿ����Ԥ��ҳ **/
	public static boolean isTurn = Boolean.FALSE;
	/*** �Ƿ������� **/
	public static boolean isSale = Boolean.FALSE;
	/** ������ ***/
	public static String machine;
	/** ȼ�ͷ� ***/
	public static String oil;
	/** Ʊ��� ***/
	public static String ticket;
	
	

	public List<?> analysis(Object obj) {
		try {
			HtmlElement element = (HtmlElement) obj;
			if (isDirect) {
				return direct(element);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void spider(List<ODSearch> ods, CountDownLatch latch) {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
		try {
//			final WebClient client = HtmlUnitUtils.getWebClient();
//			HtmlUnitUtils.setClientOptions(client);
//			login(client);
//			HtmlPage listpage = null;
//			HtmlElement cont = null;
//			HtmlElement out = null;
//			List<HtmlElement> list = null;
//			HtmlElement head = null;
//			int size = 0;
//			String index = null;// ��ҳ
			//final CountDownLatch start = new CountDownLatch(1);  
			final CountDownLatch done = new CountDownLatch(ods.size());
			final WebClient client = UtilForHttpUnit.client.get();
			login(client);
			for (final ODSearch odSearch : ods) {
				new Thread(new Runnable() {
					public void run() {
						try {
							worker(odSearch,client);
							done.countDown(); 
					        //done.countDown(); 
						} catch (Exception e) {
							e.printStackTrace();
						}  
					}
				}).start();
//				try {
//					for (int i = 0; i < odSearch.getPeriod(); i++) {
//						System.out.println("------------------��"+(i+1)+"������------------------>>>");
//						final DateTime dt = new DateTime();
//		                DateTime temp = dt.plusDays(i);
//		                final String date = temp.toString(DATE_FORMAT_PATTERN);
//		                String url = getURL(odSearch,date,index);
//		                try {
//		    				do {
//		    					if (!isTurn) {
//		    						listpage = client.getPage(url);
//		    						Thread.sleep(5000);
//		    						cont = (HtmlElement) listpage.getElementById("flight-info");
//		    						list = cont.getElementsByAttribute("div", "class", "flight-section");
//		    					}
//		    					if (list.size() == 0 || list == null) {
//		    						continue;
//		    					}
//		    					HtmlElement item = list.get(size);
//		    					if (item.getElementsByAttribute("span", "class", "price") != null) {
//		    						out = item.getElementsByAttribute("span", "class", "price").get(0);
//		    					}
//		    					if (item.getElementsByAttribute("div", "class", "head") != null) {
//		    						head = item.getElementsByAttribute("div", "class", "head").get(0);
//		    					}
//		    					if (out == null || head == null) {
//		    						continue;
//		    					}
//		    					if (head.getChildElementCount() > 2) {
//		    						isDirect = Boolean.FALSE;
//		    					}
//		    					if ("������".equals(out.asText())) {
//		    						break;
//		    					} else {
//		    						analysis(item);
//		    						size++;
//		    					}
//		    					if (size == list.size()) {
//		    						size = 0;
//		    						// TODO ��ҳ����
//		    						// url = getURL(odSearch, index);
//		    						// isSale = Boolean.FALSE;
//		    						break;
//		    					}
//		    				} while (!isSale);
//		    				isTurn = Boolean.FALSE;
//		    				isSale = Boolean.FALSE;
//		    				size = 0;
//		    			} catch (Exception e) {
//		    				log.error("mu spider is error, exception : ", e);
//		    				continue;
//		    			}
//					}
//				} catch (Exception e) {
//					log.error("");
//				}
			}
//			HtmlUnitUtils.closeAllWindows(client);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

	

	/***
	 * ģ���¼
	 */
	private void login(WebClient client) {
		try {
			HtmlPage page = client.getPage(LOGIN_URL);
			HtmlElement form = (HtmlElement) page.getElementById("form_login1");
			HtmlElement name = form.getElementsByAttribute("input", "class", "input long").get(0);
			HtmlElement pwd = (HtmlElement) form.getElementsByAttribute("input", "class", "input code").get(0);
			HtmlElement submit = (HtmlElement) form.getElementsByAttribute("input", "class", "button-red").get(0);
			name.setAttribute("value", ACCOUNT);
			pwd.setAttribute("value", PASSWORD);
			HtmlPage newpage = submit.click();
			WebWindow newwin = newpage.getEnclosingWindow();
			newwin.getEnclosedPage();
			// HtmlPage mainPage = (HtmlPage) newwin.getEnclosedPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * ��ȡ����ֱ������
	 * 
	 * @param element
	 */
	public List<?> direct(HtmlElement element) {
		int count = 1;
		HtmlElement booking = null;
		HtmlElement bookingInfo = null;
		do {
			try {
				List<HtmlElement> books = element.getElementsByTagName("ul");
				for (HtmlElement item : books) {// ��λ����
					if (item.asXml().indexOf("��׼���ò�") > 0) {
						bookingInfo = item;
						booking = item.getElementsByAttribute("input", "name", "select").get(0);
						ticket = partPrice(item.getElementsByAttribute("li", "class", "c-5").get(0).asText());
						break;
					}
				}
				if (bookingInfo == null || booking == null) {// �ֶ�������ǰѭ��
					break;
				}
//				if (!isTurn) {// û�н����Ԥ��ҳ�����½���
//					HtmlPage infopage = booking.click();
//					Thread.sleep(30000);
//					WebWindow infowin = infopage.getEnclosingWindow();
//					HtmlPage infoPage = (HtmlPage) infowin.getEnclosedPage();
//					HtmlElement infoFloat = (HtmlElement) infoPage.getElementById("infoFloat");
//					HtmlElement info = (HtmlElement) infoPage.getElementById("totalPrice");
//					if (info != null) {
//						List<HtmlElement> dl = infoFloat.getElementsByAttribute("dl", "class", "num");
//						if (Integer.parseInt(partPrice(info.asXml())) > 0) {
//							isTurn = Boolean.TRUE;// �޸�Ϊ�ѽ���
//						}
//						if (dl.size() > 0) {
//							getParam(element, bookingInfo, dl);
//						} else {
//							isTurn = Boolean.FALSE;// �޸�Ϊδ����
//						}
//					}
//				} else {
//					getParam(element, bookingInfo, null);
//				}
				getParam(element, bookingInfo, null);
				isTurn = Boolean.TRUE;// �޸�Ϊ�ѽ���
			} catch (Exception e) {
				if (count == 3) {// ͬһ���г�ÿһ�κ��ೢ�Ի�ȡ����
					e.printStackTrace();
					break;
				}
				count++;
			}
		} while (!isTurn);
		isDirect = Boolean.TRUE;
		return null;
	}

	
	
	
	/***
	 * ��ȡ������Ϣ
	 * 
	 * @param bookingInfo
	 * @param fname
	 * @param mark
	 * @param dl
	 */
	private Object getParam(HtmlElement element, HtmlElement bookingInfo, List<HtmlElement> dl) {
		try {
			final HtmlElement mark;
			HtmlElement fName = null;
			String airLineInfo = null;
			String cabin = null;
			// if (dl!=null) {
			// ticket = partPrice(dl.get(0).asXml());//Ʊ���
			// machine = partPrice(dl.get(1).asXml());//������
			// oil = partPrice(dl.get(2).asXml());//ȼ�ͷ�
			// }
			// ��λ
			if (bookingInfo.getElementsByAttribute("li", "class", "c-1") != null) {
				cabin = bookingInfo.getElementsByAttribute("li", "class", "c-1").get(0).asText();
			}
			if (element.getElementsByAttribute("dt", "class", "fName") != null) {
				fName = element.getElementsByAttribute("dt", "class", "fName").get(0);
				airLineInfo = fName.getElementsByAttribute("span", "class", "tip").get(0).asText();
			}
			if (airLineInfo == null || airLineInfo.indexOf(SUFFIX_RN) == -1) {
				return null;
			}
			String[] line = airLineInfo.split(SUFFIX_RN);
			String[] depinfo = line[2].split(SUFFIX_SPACE);
			String[] arrinfo = line[3].split(SUFFIX_SPACE);
			String note = null;// ��ע
			String airLine = line[0];// �����
			String depDate = depinfo[0];// ��������
			String depTime = depinfo[1];// ����ʱ��
			String depAirport = depinfo[2];// ��������
			String arrDate = arrinfo[0];// ��������
			String arrTime = arrinfo[1];// ����ʱ��
			String arrAirport = arrinfo[2];// �������
			if (element.getElementsByAttribute("div", "class", "div-tip") != null) {
				mark = element.getElementsByAttribute("div", "class", "div-tip").get(0);
				if (mark != null) {
					if (mark.asText().indexOf("ʵ�ʳ���") > -1) {
						note = mark.asText();
					}
				}
			}
			String adtPrice = ticket;// ���˼�
			// String chdPrice =
			// String.valueOf((Integer.parseInt(ticket)/2));//��ͯ��
			// if (chdPrice.substring(chdPrice.length()-1).equals("5")) {
			// chdPrice =String.valueOf(Integer.parseInt(chdPrice)+5);
			// }
			String mache = machine;// ������
			String oils = oil;// ȼ�ͷ�
			System.out.println("����ţ�" + airLine + "\t����ʱ�䣺" + depTime + "-" + arrTime + "\t������" + depAirport + "--->"
					+ arrAirport + "\tƱ�ۣ�" + adtPrice + "��ע��" + note);
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	
	/***
	 * ������ѯ·��
	 * 
	 * @param odSearch
	 */
	private String getURL(ODSearch odSearch,String date,String index) {
		String PATH = "http://www.ceair.com/flight2014/{depCity}-{arrCity}-{depDate}_CNY.html?#fliterParam=basecabin-all";
		String url = PATH.replace("{depCity}", odSearch.getDeparture()).replace("{arrCity}", odSearch.getArrive())
				.replace("{depDate}", date);
		return url;
	}
	
	/***
	 * ɸѡ�۸�
	 * 
	 * @param str
	 * @return
	 */
	private String partPrice(String str) {
		String reg = "[^\\d]+";
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(str);
		return mat.replaceAll("");
	}

	
//	@Override
//	public void run() {
//		String json = Thread.currentThread().getName();
//		JsonMapper mapper = new JsonMapper();
//		ODSearch search = mapper.readeValue(json, ODSearch.class);
//		
//	}
	
	
	private void worker(ODSearch odSearch,WebClient client) {
		
//		for (int i = 0; i < 10; i++) {
//			System.out.println("���ڻ�ȡ"+odSearch.getDeparture()+"---"+odSearch.getArrive()+"��"+(i+1)+"������...");
//		}
		System.out.println("���ڻ�ȡ"+odSearch.getDeparture()+"---"+odSearch.getArrive());
		try {
			HtmlPage listpage = null;
			HtmlElement cont = null;
			HtmlElement out = null;
			List<HtmlElement> list = null;
			HtmlElement head = null;
			int size = 0;
			String index = null;// ��ҳ
			for (int i = 0; i < odSearch.getPeriod(); i++) {
				System.out.println("���ڻ�ȡ"+odSearch.getDeparture()+"---"+odSearch.getArrive()+"------------------��"+(i+1)+"������------------------>>>");
				final DateTime dt = new DateTime();
                DateTime temp = dt.plusDays(i);
                final String date = temp.toString(DATE_FORMAT_PATTERN);
                String url = getURL(odSearch,date,index);
                try {
    				do {
    					if (!isTurn) {
    						listpage = client.getPage(url);
    						Thread.sleep(5000);
    						cont = (HtmlElement) listpage.getElementById("flight-info");
    						list = cont.getElementsByAttribute("div", "class", "flight-section");
    					}
    					if (list.size() == 0 || list == null) {
    						continue;
    					}
    					HtmlElement item = list.get(size);
    					if (item.getElementsByAttribute("span", "class", "price") != null) {
    						out = item.getElementsByAttribute("span", "class", "price").get(0);
    					}
    					if (item.getElementsByAttribute("div", "class", "head") != null) {
    						head = item.getElementsByAttribute("div", "class", "head").get(0);
    					}
    					if (out == null || head == null) {
    						continue;
    					}
    					if (head.getChildElementCount() > 2) {
    						isDirect = Boolean.FALSE;
    					}
    					if ("������".equals(out.asText())) {
    						break;
    					} else {
    						analysis(item);
    						size++;
    					}
    					if (size == list.size()) {
    						size = 0;
    						// TODO ��ҳ����
    						// url = getURL(odSearch, index);
    						// isSale = Boolean.FALSE;
    						break;
    					}
    				} while (!isSale);
    				isTurn = Boolean.FALSE;
    				isSale = Boolean.FALSE;
    				size = 0;
    			} catch (Exception e) {
    				e.printStackTrace();
    				continue;
    			}
			}
			client.closeAllWindows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
