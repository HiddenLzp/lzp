package com.lzp.demo.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestUtil {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String CHARSET = "UTF-8";


    private static final CloseableHttpClient httpClient;

    static{
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000)
                .setSocketTimeout(200000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }


    public static String httpRequest(String requestUrl, String requestMethod, Map<String, Object> params) {
        URL url = null;
        try {
            url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(200000);//读取超时 单位毫秒
            //发送post请求必须设置如下俩行
            httpURLConnection.setDoOutput(true);
            //获取URLConnection对象对应的输入流
            // 发送请求参数
            OutputStreamWriter out = new OutputStreamWriter(
                    httpURLConnection.getOutputStream(), "UTF-8");
            // 发送请求params参数
            out.write(parseParams(params));
            out.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int length = 0;
            byte[] arr = new byte[1024];
            while ((length = bis.read(arr)) != -1) {
                bos.write(arr, 0, length);
                bos.flush();
            }
            bos.close();
            return bos.toString("UTF-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    private static String parseParams(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String key : params.keySet()) {
            sb.append(key + "=" + params.get(key) + "&");
        }
        return sb.substring(0, sb.length() - 1);
    }


    /**
     *
     * @param method  请求方式
     * @param url   请求地址
     * @param params    参数地址
     * @param jsonStr  post请求 的参数  如果是GET 请求 填写null
     * @return  返回值
     */
    public static Map<String,Object> httpRequest(String method,String url, Map<String,Object> params,String jsonStr){
        if(!StringUtils.hasText(url)){
            return null;
        }
        try{
            CloseableHttpResponse response = null;
            if(params != null && params.size() > 0){
                List<NameValuePair> pairs = new ArrayList<>(params.size());
                for (String key : params.keySet()) {
                    pairs.add(new BasicNameValuePair(key,params.get(key).toString()));
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs,CHARSET));
            }
            if(method.equals("GET")){
                HttpGet httpGet = new HttpGet(url);
                response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode != 200){
                    httpGet.abort();
                    throw new RuntimeException("HttpClient, error status code : " + statusCode);
                }
            }else if(method.equals("POST")){
                HttpPost httpPost = new HttpPost(url);
                StringEntity entitystr = new StringEntity(jsonStr,CHARSET);
                entitystr.setContentEncoding(CHARSET);
                entitystr.setContentType("application/json");
                httpPost.setEntity(entitystr);
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode != 200){
                    httpPost.abort();
                    throw new RuntimeException("HttpClient, error status code : " + statusCode);
                }
            }
            HttpEntity entity = response.getEntity();
            String result = "";
            if(entity != null){
                result = EntityUtils.toString(entity,CHARSET);
            }
            Map<String,Object> resultMap = (Map<String, Object>) JSON.parse(result);
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
