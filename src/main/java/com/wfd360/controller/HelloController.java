package com.wfd360.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {
    /**
     * 获取模拟数据
     */
    @RequestMapping("/data")
    @ResponseBody
    public String data() {
        System.out.println("---------656565656088080898--------");
        return "hello world 87879878，。了。了。87";
    }
}


// 推荐使用token  
String JENKINS_TOKEN = "112c7c08043e02449e7fb97d253a657ede";

/**
 * 单独提取的一个公共方法
 *
 * @param url 请求地址
 * @param httpRequest 请求方法对象
 * @return
 */
private String customHttpMsg(String url, HttpRequest httpRequest) throws URISyntaxException, IOException {
    URI uri = new URI(url);
    HttpHost host = new HttpHost(uri.getHost(), uri.getPort());
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	// 注意这边  我把密码换成了token
    credentialsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()),
            new UsernamePasswordCredentials(JENKINS_USERNAME, JENKINS_TOKEN));
    AuthCache authCache = new BasicAuthCache();
    BasicScheme basicScheme = new BasicScheme();
    authCache.put(host,basicScheme);
    try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build()) {
        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setAuthCache(authCache);
        CloseableHttpResponse response = httpClient.execute(host, httpRequest, httpClientContext);
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }
}

// 使用token 调用OK
@Test
public void testAddWithToken() throws IOException, URISyntaxException {
    // http://192.168.1.107:8081/jenkins/createItem/api/json?name=test
    String url = JENKINS_URL+"/createItem/api/json?name="+JENKINS_PROJECT_NAME;
    HttpPost httpPost = new HttpPost(url);
    httpPost.setEntity(new StringEntity(JENKINS_PROJECT_XML, ContentType.create("text/xml", "utf-8")));
    String res = customHttpMsg(url, httpPost);
    log.info("res is {}", res);
