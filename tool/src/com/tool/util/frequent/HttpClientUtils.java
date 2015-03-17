package com.tool.util.frequent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	private static final int CONNECT_TIME_OUT = 5000;
    private static final int READ_TIME_OUT = 180000;
    private static final int CONNECTION_REQUEST_TIME_OUT = 5000;
    public static final Charset charset = Charset.forName("UTF-8");
    private static CookieStore cookieStore = null;
    private static HttpClientContext context = null;
//    /**
//     * spring http components httpclient doGet
//     * 
//     * @author niuguodong
//     * 
//     * @param url
//     * @param uriVariables
//     * @return
//     */
//    public static String doGet(String url, String... uriVariables) throws Exception {
//        final HttpComponentsClientHttpRequestFactory httpClientRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpClientRequestFactory.setConnectTimeout(CONNECT_TIME_OUT);
//        httpClientRequestFactory.setReadTimeout(READ_TIME_OUT);
//        
//        final RestTemplate httpClientRestTemplate = new RestTemplate();
//        httpClientRestTemplate.setRequestFactory(httpClientRequestFactory);
//        final String result = httpClientRestTemplate.getForObject(url, String.class, uriVariables);
//        return result;
//    }
//
//    /**
//     * spring http components httpclient doPost
//     * 
//     * @author niuguodong
//     * 
//     * @param url
//     * @param uriVariables
//     * @return
//     */
//    public static String doPost(String url, String uriVariables) throws Exception {
//        final HttpComponentsClientHttpRequestFactory httpClientRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpClientRequestFactory.setConnectTimeout(CONNECT_TIME_OUT);
//
//        final RestTemplate httpClientRestTemplate = new RestTemplate();
//        httpClientRestTemplate.setRequestFactory(httpClientRequestFactory);
//        final String params = Encodes.urlEncode(uriVariables);
//        final String result = httpClientRestTemplate.postForObject(url, uriVariables, String.class, params);
//        return result;
//    }

    /**
     * http client post 方法
     * 
     * @author niuguodong
     * 
     * @param url
     * @param entity
     * @param charSet
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
//    public static String doPost(final String url, final String entity, Charset charSet) throws ClientProtocolException,
//            IOException {
//        if ((StringUtils.isBlank(url)) || (StringUtils.isBlank(entity))) {
//            return null;
//        }
//        charSet = charSet == null ? charset : charSet;
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(getRequestConfig());
//        StringEntity stringEntity = new StringEntity(entity, charSet);
//        httpPost.setEntity(stringEntity);
//        HttpResponse response = getHttpClient().execute(httpPost);
//        return EntityUtils.toString(response.getEntity(), charSet);
//    }

    /**
     * http client post 方法
     * 
     * @author niuguodong
     * 
     * @param url
     * @param entitys
     * @param charSet
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private static HttpClient client=getHttpClient();
    static HttpResponse response = null;
    public static HttpResponse doPost(final String url, final Map<String, String> entitys, Charset charSet,HttpResponse response)
            throws ClientProtocolException, IOException, URISyntaxException {
        if ((StringUtils.isBlank(url)) || (entitys == null)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(getRequestConfig());
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        for (Entry<String, String> entity : entitys.entrySet()) {
            params.add(new BasicNameValuePair((String) entity.getKey(), (String) entity.getValue()));
        }
        charSet = charSet == null ? charset : charSet;
        StringEntity stringEntity = new UrlEncodedFormEntity(params, charSet);
        httpPost.setEntity(stringEntity);
        if (response!=null) {
        	httpPost.addHeader("Referer", "http://www.ceair.com/flight/passenger.html");
        	httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        	httpPost.setURI(new URI(url+sessionId));
		}
        response =client .execute(httpPost,context);
//        Header[] cookies = response.getHeaders("Cookie");
        for (Header head : response.getAllHeaders()) {
			System.out.println(head.getName()+"\t:\t"+head.getValue());
		}
        setCookieStore(response);
        setContext();
        System.out.println("\n\n");
//        
//        CloseableHttpClient clients = HttpClients.custom()
//                .setDefaultCookieStore(cookieStore).build();
//        
//        
//        Header location = response.getLastHeader("Location");
//        System.out.println(location);
//        HttpGet httpGet = new HttpGet(location.getValue());
//        HttpResponse resp = clients.execute(httpGet,context);
//        System.out.println();
        
//        return EntityUtils.toString(response.getEntity(), charSet);
        return response;
    }
    
    private static String sessionId = null;

    public static void setCookieStore(HttpResponse httpResponse) {
        cookieStore = new BasicCookieStore();
        String setCookie = httpResponse.getFirstHeader("Set-Cookie")
            .getValue();
       
        String JSESSIONID = setCookie.substring("JSESSIONID=".length()-1,
            setCookie.indexOf(";"));
        System.out.println("JSESSIONID:" + JSESSIONID);
        sessionId = JSESSIONID;
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
        		JSESSIONID);
        Header[] cookies = httpResponse.getHeaders("Set-Cookie");
        for (Header head : cookies) {
        	String[] values = head.getValue().split("; ");
        	
        	for (String str : values) {
        		if (str.indexOf("=")>-1) {
        			String[] va = str.split("=");
            		cookie.setAttribute(va[0], va[1]);
    			}
			}
        	
		}
        
        cookie.setDomain(".ceair.com");
        cookie.setPath("/");
        cookie.setAttribute("ffpno", "18310728878");
        cookie.setAttribute("passportId", "18310728878");
        cookie.setVersion(0);
        cookie.setDomain(".ceair.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
      }
    
    public static void setContext() {
        context = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder
            .<CookieSpecProvider> create()
            .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
            .register(CookieSpecs.BROWSER_COMPATIBILITY,
                new BrowserCompatSpecFactory()).build();
        context.setCookieSpecRegistry(registry);
        context.setCookieStore(cookieStore);
      }
    
    
    /**
     * http client get 方法
     * 
     * @author niuguodong
     * 
     * @param url
     * @param charSet
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doGet(String url, Charset charSet) throws ClientProtocolException, IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getRequestConfig());
        HttpResponse response = getHttpClient().execute(httpGet);
        charSet = charSet == null ? charset : charSet;
        return EntityUtils.toString(response.getEntity(), charSet);
    }

    private static RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT).build();
        return requestConfig;
    }

    private static HttpClient getHttpClient() {
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        return httpClient;
    }
//    
//    
//    public static void main(String[] args) throws ClientProtocolException, IOException {
//        String url = "http://114.113.230.125:6004/web/fligtBack.action";
//        String entity = "{\"OTAAirRoot\": {\"OTAAirHeader\": {\"timeStamp\": \"2014-03-01 01:11:54\" },\"OTAAirBody\": { \"bodyResponse\": {    \"msgCode\": \"CAE_00000\", \"msgInfo\": \"SUCCESS\",\"orderNo\":\"\",  \"tradeNo\":\"\",   \"tradeStatus\":\"\",   \"fee\" :\"\"    } }}";
//        HttpClientUtils.doPost(url, entity, Constants.charset);
//    }
//    
//    private static HttpClient getHttpClient() {
//        HttpClientBuilder httpClientBuilder = HttpClients.custom();
//        CloseableHttpClient httpClient = httpClientBuilder.build();
//        return httpClient;
//    }
    
    
    
    public static HttpResponse doGet(String url) throws ClientProtocolException, IOException {
    	HttpGet httpGet = new HttpGet(url);
    	return client.execute(httpGet);
	}
}
