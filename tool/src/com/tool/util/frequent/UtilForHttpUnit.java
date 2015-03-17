package com.tool.util.frequent;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

public class UtilForHttpUnit {

	public static WebClient createWebClient() {
		WebClient client = new WebClient(BrowserVersion.FIREFOX_24);
		//client.setWebConnection(new HttpWebConnection(client));
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setTimeout(35000);
		client.getOptions().setRedirectEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());
		
		//client.setCssErrorHandler(new SilentCssErrorHandler());
		//client.getOptions().setPopupBlockerEnabled(false);
		//client.getCookieManager().setCookiesEnabled(true);
		//client.waitForBackgroundJavaScript(10000);
		return client;
	}
	
	public static ThreadLocal<WebClient> client = new ThreadLocal<WebClient>() {
		protected synchronized WebClient initialValue() {
			WebClient webClient = new WebClient(BrowserVersion.CHROME);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setTimeout(35000);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			return webClient;
		}
	};

}
