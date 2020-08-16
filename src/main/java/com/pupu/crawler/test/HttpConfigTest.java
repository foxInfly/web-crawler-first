package com.pupu.crawler.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author : lipu
 * @since : 2020-08-15 22:15
 */
public class HttpConfigTest {

    public static void main(String[] args){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)//创建链接的最长时间，单位毫秒
                    .setConnectionRequestTimeout(500)//设置获取链接的最长时间，单位毫秒
                    .setSocketTimeout(10*1000)//设置数据传输的最长时间，单位毫秒
                    .build();

        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
