package com.pupu.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author : lipu
 * @since : 2020-08-16 08:20
 */
public class HttpClientPooltest {


    public static void main(String[] args) {
        //1.创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //最大连接数
        cm.setMaxTotal(100);
        //每个主机的连接数（host，就是你访问的主机。目的：爬取数据不能总爬同一个host）
        cm.setDefaultMaxPerRoute(10);


        //使用连接池管理发起请求
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();


        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

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
                //不能关闭httpClient，由连接池管理httpClient
//                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
