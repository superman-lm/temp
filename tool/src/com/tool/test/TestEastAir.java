package com.tool.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tool.entity.eastair.Flight;
import com.tool.entity.eastair.Leg;
import com.tool.entity.eastair.QueryParam;
import com.tool.util.frequent.UtilForHttpUnit;

public class TestEastAir {
	
	/***��¼ҳ��·��**/
	public static String LOGIN_URL = "https://passport.ceair.com/cesso/login2!check.shtml?ltv=1&at=2&site=0&redirectUrl=http://www.ceair.com";
	/***�б�ҳ��·��**/
	public static String PATH = "http://www.ceair.com/flight2014/nay-pvg-150131_CNY.html?chd=1#fliterParam=basecabin-all";
	/***�˻�**/
	public static final String ACCOUNT = "18310728878";
	/***��̬��**/
	public static final String PASSWORD = "996880";
	/***�Ƿ�ֱ��**/
	public static boolean isDirect = Boolean.TRUE;
	/***�Ƿ����Ԥ��ҳ**/
	public static boolean turn = Boolean.FALSE;
	/***�Ƿ�������**/
	public static boolean sale = Boolean.FALSE;
	/**������***/
	public static String machine;
	/**ȼ�ͷ�***/
	public static String oil;
	/**Ʊ���***/
	public static String ticket;
	
	public static void main(String[] args) {
		try {
			
			List<List<Flight>> lists = new ArrayList<List<Flight>>();
			List<String> path = new ArrayList<String>();
			path.add("http://www.ceair.com/flight2014/nay-pvg-150131_CNY.html?chd=1#fliterParam=basecabin-all");
			path.add("http://www.ceair.com/flight2014/nay-hgh-150228_CNY.html?chd=1#fliterParam=basecabin");
			LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
					"org.apache.commons.logging.impl.NoOpLog");
			WebClient client = UtilForHttpUnit.createWebClient();
			login(client);
			HtmlPage listpage = null;
			HtmlElement cont = null;
			List<HtmlElement> list = null;
			int size = 0;
			for (String url : path) {
				List<Flight> flights = new ArrayList<Flight>();
				do {
					Flight flight = new Flight();
					if (!turn) {
						listpage = client.getPage(url);
						Thread.sleep(5000);
						cont = (HtmlElement) listpage.getElementById("flight-info");
						list = cont.getElementsByAttribute("div", "class", "flight-section");
					}
					HtmlElement item = list.get(size);
					HtmlElement out = item.getElementsByAttribute("span", "class", "price").get(0);
					HtmlElement head = item.getElementsByAttribute("div", "class","head").get(0);
					if (head.getChildElementCount()>2) {
						isDirect = Boolean.FALSE;
					}
					if (size==list.size()-1) {
						sale = Boolean.TRUE;
						break;
					}
					if (!"������".equals(out.asText())) {
						if (isDirect) {
							direct(client, item,flight);
						}else {
							//transit(client, list.get(size),flight);
						}
						flights.add(flight);
						size++;
					}else {
						sale = Boolean.TRUE;
						break;
					}
				} while (!sale);
				lists.add(flights);
				turn = Boolean.FALSE;
				sale = Boolean.FALSE;
				size = 0;
			}
			syso(lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * ����ֱ��
	 */
	public static void direct(WebClient client,HtmlElement element,Flight flight) {
		int count = 1;
		HtmlElement booking = null;
		HtmlElement bookingInfo = null;
		HtmlElement mark  = null;
		HtmlElement fName  = null;
		do {
			try {
				List<HtmlElement> books = element.getElementsByTagName("ul");
				if (element.getElementsByAttribute("dt", "class", "fName")!=null) {
					fName = element.getElementsByAttribute("dt", "class", "fName").get(0);
				}
				if (element.getElementsByAttribute("div", "class", "div-tip")!=null) {
					mark = element.getElementsByAttribute("div", "class", "div-tip").get(0);
				}
				for (HtmlElement item : books) {
					if (item.asXml().indexOf("��׼���ò�")>0) {
						bookingInfo = item;
						booking = item.getElementsByAttribute("input", "name", "select").get(0);
					}
				}
				if (bookingInfo==null) {
					break;
				}
				if (!turn) {
					System.out.println("---------------------->");
					HtmlPage infopage = booking.click();
					Thread.sleep(30000);
					//client.waitForBackgroundJavaScript(30000);
					WebWindow infowin = infopage.getEnclosingWindow();
					HtmlPage infoPage = (HtmlPage) infowin.getEnclosedPage();
					HtmlElement infoFloat = (HtmlElement) infoPage.getElementById("infoFloat");
					HtmlElement info = (HtmlElement) infoPage.getElementById("totalPrice");
					if (info != null) {
						int price = Integer.parseInt(part(info.asXml()));
						if (price > 0) {
							//flag = Boolean.TRUE;
							turn = Boolean.TRUE;
						}
						List<HtmlElement> dl = infoFloat.getElementsByAttribute("dl", "class", "num");
						getParam(flight, bookingInfo, fName, mark, dl);
					}
				}else {
					getParam(flight, bookingInfo, fName, mark, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��"+count+"�λ�ȡʧ��...");
				if (count==3) {
					break;
				}
				count++;
			}
		} while (!turn);
	}
	
	/***
	 * ������ת
	 */
	public static void transit(WebClient client,HtmlElement element,Flight flight) {
		
	}
	
	
	/***
	 * ģ���¼
	 */
	public static void login(WebClient client) {
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
			//HtmlPage mainPage = (HtmlPage) newwin.getEnclosedPage();
			System.out.println("��==============================��¼�ɹ�================================��");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/***
	 * ��������
	 */
	public static void getParam(Flight flight,HtmlElement bookingInfo,HtmlElement fname,HtmlElement mark,List<HtmlElement> dl) {
		try {
			List<Leg> legs = new ArrayList<Leg>();
			Leg leg = new Leg();
			if (dl!=null) {
				ticket = part(dl.get(0).asXml());
				machine = part(dl.get(1).asXml());
				oil = part(dl.get(2).asXml());
			}
			leg.setCabin(bookingInfo.getElementsByAttribute("li", "class", "c-1").get(0).asText());
			String airLine = fname.getElementsByAttribute("span", "class","tip").get(0).asText();
			String[] line = airLine.split("\r\n");
			String[] depinfo = line[2].split(" ");
			String[] arrinfo = line[3].split(" ");
			leg.setAirLine(line[0]);
			leg.setDepDate(depinfo[0]);
			leg.setDepTime(depinfo[1]);
			leg.setDepAirport(depinfo[2]);
			leg.setArrDate(arrinfo[0]);
			leg.setArrTime(arrinfo[1]);
			leg.setArrAirport(arrinfo[2]);
			if (mark!=null) {
				if (mark.asText().indexOf("ʵ�ʳ���")>-1) {
					leg.setRemark(mark.asText());
				}
			}
			flight.setAdtPrice(ticket);
			String chd = String.valueOf((Integer.parseInt(ticket)/2));
			if (chd.substring(chd.length()-1).equals("5")) {
				chd =String.valueOf(Integer.parseInt(chd)+5);
			}
			flight.setChdPrice(chd);
			flight.setTax(String.valueOf((Integer.parseInt(machine)+Integer.parseInt(oil))));
			legs.add(leg);
			flight.setLegs(legs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * ����·��
	 */
	public static String getURL(QueryParam param ) {
		try {
			String url = null;
			return url;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/***
	 * ɸѡ�۸�
	 * @param str
	 * @return
	 */
	public static String part(String str) {
		String reg = "[^\\d]+";
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(str);
		return mat.replaceAll("");
	}
	
	/***
	 * ���˺����
	 * @param str
	 * @return
	 */
	public static String line(String str) {
		String reg = "[<f>]\\S+[</f>]";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        return mat.replaceAll("");
	}
	
	
	public static void syso(List<List<Flight>> lists) {
		for (List<Flight> list : lists) {
			for (Flight flight : list) {
				String mark = null;
				for (Leg leg : flight.getLegs()) {
					System.out.print("����ţ�"+leg.getAirLine());
					System.out.print("\t��λ��"+leg.getCabin());
					System.out.print("\t����ʱ�䣺"+leg.getDepTime());
					System.out.print("\t����ʱ�䣺"+leg.getArrTime());
					System.out.print("\t�����أ�"+leg.getDepAirport());
					System.out.print("\tĿ�ĵأ�"+leg.getArrAirport());
					mark = leg.getRemark();
				}
				System.out.print("\t���˼ۣ�"+flight.getAdtPrice());
				System.out.print("\t��ͯ�ۣ�"+flight.getChdPrice());
				System.out.print("\t˰�ۣ�"+flight.getTax());
				System.out.print("\t��ע��"+mark+"\n");
			}
			System.out.println("------------------------------------------------------------->>>");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public void booking(QueryParam queryParam) {
		
	}
	
}
