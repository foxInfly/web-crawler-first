package com.pupu.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 模拟访问浏览器
 *    1.创建一个Httpclient对象（打开浏览器）
 *    2.输入网址(HttpGet\HttpPost)
 *    3.按回车，发起请求，获取响应
 *    4.解析响应，获取数据
 *
 * @author : lipu
 * @since : 2020-08-15 21:28
 */
public class CrawlerFirst {


    public static void main(String[] args) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }

    }
}
