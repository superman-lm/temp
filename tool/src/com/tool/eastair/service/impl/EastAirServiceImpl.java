package com.tool.eastair.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.Event;
import com.tool.eastair.entity.Flight;
import com.tool.eastair.entity.ODSearch;
import com.tool.eastair.entity.Passage;
import com.tool.eastair.service.EastAirService;
import com.tool.util.frequent.UtilForHttpUnit;

public class EastAirServiceImpl implements EastAirService {

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

	@Override
	public void flight(List<ODSearch> ods) {
		String index = null;// ��ҳ
		try {
			final WebClient client = UtilForHttpUnit.createWebClient();
			login(client);
			for (final ODSearch odSearch : ods) {
				try {
					for (int i = 0; i < odSearch.getPeriod(); i++) {
						System.out.println("���ڻ�ȡ" + odSearch.getDeparture() + "---" + odSearch.getArrive()
								+ "---------------��" + (i + 1) + "������------------------>>>");
						final DateTime dt = new DateTime();
						DateTime temp = dt.plusDays(i);
						final String date = temp.toString(DATE_FORMAT_PATTERN);
						String url = getURL(odSearch, date, index);
						List<HtmlElement> elements = getElementList(client, url);
						iteratorElement(client, elements);
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void booking(Flight flight, List<Passage> passages) {
		try {
			final WebClient client = UtilForHttpUnit.createWebClient();
			login(client);
			String url = PATH.replace("{depCity}", flight.getDepCity()).replace("{arrCity}", flight.getArrCity())
					.replace("{depDate}", flight.getDepDate());
			List<HtmlElement> elements = getElementList(client, url);
			HtmlElement fName = null;
			String airLineInfo = null;
			List<HtmlElement> cabin = null;// ��λ
			HtmlPage order = null;
			HtmlPage orderDetails = null;
			boolean isOrder = Boolean.FALSE;
			for (HtmlElement element : elements) {
				if (element.asText().indexOf("������") == -1) {
					if (element.getElementsByAttribute("dt", "class", "fName") != null) {
						fName = element.getElementsByAttribute("dt", "class", "fName").get(0);
						airLineInfo = fName.getElementsByAttribute("span", "class", "tip").get(0).asText();
					}
					if (airLineInfo == null || airLineInfo.indexOf(SUFFIX_RN) == -1) {
						continue;
					}
					String airLine = airLineInfo.split(SUFFIX_RN)[0];
					if (!airLine.equals(flight.getAirLine())) {
						continue;
					}
					List<HtmlElement> books = element.getElementsByTagName("ul");
					for (HtmlElement item : books) {
						cabin = item.getElementsByAttribute("li", "class", "c-1");
						if ((cabin!=null)&&(cabin.get(0).asText().indexOf(flight.getCabin())>-1)) {
							HtmlElement booking = item.getElementsByAttribute("input", "name", "select").get(0);
							HtmlPage infopage = booking.click();
							Thread.sleep(40000);
							WebWindow infowin = infopage.getEnclosingWindow();
							order = (HtmlPage) infowin.getEnclosedPage();
							isOrder = Boolean.TRUE;
							break;
						}
					}
					if (isOrder) {
						break;
					}
				}
			}
			if (isOrder&&order!=null) {
//				int index = 0;
//				for (Passage pasg : passages) {
//					if (index == 0) {
//						HtmlElement pasgParent = (HtmlElement) order.getElementById("paxInput");
//						HtmlElement pasgEle = pasgParent.getElementsByAttribute("div", "class", "box init").get(0);
//						HtmlInput pasgName = (HtmlInput) pasgEle.getElementsByAttribute("input", "name", "paxName").get(0);
//						pasgName.setAttribute("value", pasg.getName());
//						HtmlInput pasgMobile = (HtmlInput) pasgEle.getElementsByAttribute("input", "name", "mobile").get(0);
//						order.setFocusedElement(pasgMobile);
//						order = pasgMobile.click();
//						pasgMobile.setAttribute("value", pasg.getPhone());
//						HtmlInput pasgIdNo = (HtmlInput) pasgEle.getElementsByAttribute("input", "name", "idNo").get(0);
//						pasgIdNo.focus();
//						pasgIdNo.setAttribute("value", pasg.getNumber());
//						index++;
//						break;
//					}
//				}
				HtmlElement pasgParent = (HtmlElement) order.getElementById("paxInput");
				HtmlCheckBoxInput per = (HtmlCheckBoxInput) pasgParent.getElementsByAttribute("input", "title", "����").get(0);
				order = (HtmlPage) per.setChecked(true);
				System.out.println(order.getElementById("infoFloat").asXml());
				
				HtmlElement address = (HtmlElement) order.getElementById("contactInfo");
				HtmlElement person = address.getElementsByAttribute("input", "name", "contactName").get(0);
				person.setAttribute("value", "������");
				HtmlElement phone = address.getElementsByAttribute("input", "name", "contactMobile").get(0);
				phone.setAttribute("value", "18378964123");
				HtmlCheckBoxInput checkBoxInput = (HtmlCheckBoxInput) order.getElementById("pax_confirm");
				HtmlPage dddd = (HtmlPage) checkBoxInput.setChecked(true);
				HtmlInput confirmBooking = (HtmlInput) dddd.getElementById("btn_passenger");
				HtmlPage orderinfo = confirmBooking.click();
				Thread.sleep(30000);
				WebWindow win = orderinfo.getEnclosingWindow();
				orderDetails = (HtmlPage) win.getEnclosedPage();
				System.out.println(orderinfo.getUrl());
				System.out.println(orderinfo.asXml());
				System.out.println(orderinfo.asText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean pricing(Flight flight) {
		try {
			final WebClient client = UtilForHttpUnit.createWebClient();
			login(client);
			String url = PATH.replace("{depCity}", flight.getDepCity()).replace("{arrCity}", flight.getArrCity())
					.replace("{depDate}", flight.getDepDate());
			List<HtmlElement> elements = getElementList(client, url);
			List<HtmlElement> cabin = null;// ��λ
			List<HtmlElement> price = null;// Ʊ��
			HtmlElement fName = null;
			String airLineInfo = null;
			for (HtmlElement element : elements) {
				if (element.asText().indexOf("������") == -1) {
					List<HtmlElement> books = element.getElementsByTagName("ul");
					if (element.getElementsByAttribute("dt", "class", "fName") != null) {
						fName = element.getElementsByAttribute("dt", "class", "fName").get(0);
						airLineInfo = fName.getElementsByAttribute("span", "class", "tip").get(0).asText();
					}
					if (airLineInfo == null || airLineInfo.indexOf(SUFFIX_RN) == -1) {
						continue;
					}
					String airLine = airLineInfo.split(SUFFIX_RN)[0];
					if (!airLine.equals(flight.getAirLine())) {
						continue;
					}
					for (HtmlElement item : books) {
						cabin = item.getElementsByAttribute("li", "class", "c-1");
						price = item.getElementsByAttribute("li", "class", "c-5");
						if (cabin.size() > 0 && price.size() > 0) {
							if (cabin.get(0).asText().indexOf(flight.getCabin()) > -1) {
								if (Integer.parseInt(flight.getPrice()) >= Integer.parseInt(partPrice(price.get(0)
										.asText()))) {
									return true;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/***
	 * ��ȡ�����б�Ԫ��
	 * 
	 * @param client
	 * @param url
	 * @return
	 */
	private List<HtmlElement> getElementList(WebClient client, String url) {
		HtmlPage listpage = null;
		HtmlElement cont = null;
		List<HtmlElement> list = null;
		try {
			listpage = client.getPage(url);
			Thread.sleep(5000);
			if (listpage != null) {
				cont = (HtmlElement) listpage.getElementById("flight-info");
			}
			if (cont != null) {
				list = cont.getElementsByAttribute("div", "class", "flight-section");
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * ���������б�
	 * 
	 * @param client
	 * @param list
	 */
	private void iteratorElement(WebClient client, List<HtmlElement> list) {
		try {
			HtmlElement mark = null;
			HtmlElement fName = null;
			String airLineInfo = null;
			String cabin = null;// ��λ
			for (HtmlElement element : list) {
				if (element.asText().indexOf("������") == -1) {
					List<HtmlElement> books = element.getElementsByTagName("ul");
					for (HtmlElement item : books) {
						if (item.getElementsByAttribute("li", "class", "c-1") != null) {
							cabin = item.getElementsByAttribute("li", "class", "c-1").get(0).asText();
						}
						if (element.getElementsByAttribute("dt", "class", "fName") != null) {
							fName = element.getElementsByAttribute("dt", "class", "fName").get(0);
							airLineInfo = fName.getElementsByAttribute("span", "class", "tip").get(0).asText();
						}
						if (airLineInfo == null || airLineInfo.indexOf(SUFFIX_RN) == -1) {
							continue;
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
						// ���˼�
						String adtPrice = partPrice(item.getElementsByAttribute("li", "class", "c-5").get(0).asText());
						String mache = null;// ������
						String oils = null;// ȼ�ͷ�
						System.out.println("����ţ�" + airLine + "\t��λ��" + cabin + "\t����ʱ�䣺" + depTime + "-" + arrTime
								+ "\t������" + depAirport + "--->" + arrAirport + "\tƱ�ۣ�" + adtPrice + "��ע��" + note);

						// TODO �������
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/***
	 * ģ���¼
	 * 
	 * @param client
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
			System.out.println("��===================================login success===================================��");
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	/***
	 * ������ѯ·��
	 * 
	 * @param odSearch
	 */
	private String getURL(ODSearch odSearch, String date, String index) {
		String url = PATH.replace("{depCity}", odSearch.getDeparture()).replace("{arrCity}", odSearch.getArrive())
				.replace("{depDate}", date);
		return url;
	}

}
