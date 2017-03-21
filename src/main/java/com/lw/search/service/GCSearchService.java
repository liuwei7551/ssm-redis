package com.lw.search.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Repository
public class GCSearchService {

  public String search(String start, String q) {
    String urlStr = "https://www.googleapis.com/customsearch/v1element?key=AIzaSyCVAXiUzRYsML1Pv6RwSG1gunmMikTzQqY&rsz=filtered_cse&num=10&hl=zh_CN&prettyPrint=false&source=gcsc&gss=.com&sig=581c068e7ad56cae00e4e2e8f7dc3837&start="
        + start + "&cx=010502968661485219443:isl0jvwcpgk&q=" + q
        + "&sort=&googlehost=www.google.com&callback=google.search.Search.apiary18086&nocache=1490011480790";
    URI url;
    CloseableHttpClient httpClient = null;
    try {
      url = new URI(urlStr);
      httpClient = HttpClients.createDefault();
      HttpGet httpGet = new HttpGet(url);
      System.out.println("executing request " + httpGet.getURI());
      CloseableHttpResponse response = httpClient.execute(httpGet);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        String result = EntityUtils.toString(entity);
        result = StringUtils.substring(result, StringUtils.indexOf(result, "(") + 1, result.length() - 2);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        return jsonArray.toString();
      }
    } catch (URISyntaxException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }finally{
      try {
        httpClient.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return null;
  }

  public static void main(String[] args) throws Exception {
    String q = "java";
    String start = "0"; // ��ʼҳ
    String urlStr = "https://www.googleapis.com/customsearch/v1element?key=AIzaSyCVAXiUzRYsML1Pv6RwSG1gunmMikTzQqY&rsz=filtered_cse&num=10&hl=zh_CN&prettyPrint=false&source=gcsc&gss=.com&sig=581c068e7ad56cae00e4e2e8f7dc3837&start="
        + start + "&cx=010502968661485219443:isl0jvwcpgk&q=" + q
        + "&sort=&googlehost=www.google.com&callback=google.search.Search.apiary18086&nocache=1490011480790";
    URI url = new URI(urlStr);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(url);
    System.out.println("executing request " + httpGet.getURI());
    CloseableHttpResponse response = httpClient.execute(httpGet);
    try {
      // 获取响应实体
      HttpEntity entity = response.getEntity();
      System.out.println("--------------------------------------");
      // 打印响应状态
      System.out.println(response.getStatusLine());
      if (entity != null) {
        String result = EntityUtils.toString(entity);
        result = StringUtils.substring(result, StringUtils.indexOf(result, "(") + 1, result.length() - 2);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        // 打印响应内容长度
        System.out.println("Response content length: " + jsonArray.size());
        for (Object object : jsonArray) {
          System.out.println(object.toString());
        }
      }
      System.out.println("------------------------------------");
    } catch (Exception e) {

    }
  }
}
