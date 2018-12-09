package com.ilucky.web.javaagent.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lubov on 2018/10/13.
 */
public class HttpUtil {

    public static String httpPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
        }
        finally{
            try{
                if(out!=null){out.close();}
                if(in!=null){in.close();}
            }
            catch(IOException ex){
            }
        }
        return sb.toString();
    }


    public static void main(String[] args){
//        HttpUtil.httpPost("http://10.0.3.52:8081/school/index.jsp",null);

//        String respData = "{\n" +
//                "\t\"agentId\": \"7f8d14fd-442e-42ae-bf21-974ac659aa39\",\n" +
//                "\t\"busiId\": {\n" +
//                "\t\t\"body\": \"body\",\n" +
//                "\t\t\"cookie\": [\n" +
//                "\t\t\t\"c1\",\n" +
//                "\t\t\t\"c2\"\n" +
//                "\t\t],\n" +
//                "\t\t\"header\": [\n" +
//                "\t\t\t\"h1\",\n" +
//                "\t\t\t\"h2\"\n" +
//                "\t\t],\n" +
//                "\t\t\"session\": [\n" +
//                "\t\t\t\"s1\",\n" +
//                "\t\t\t\"s2\"\n" +
//                "\t\t],\n" +
//                "\t\t\"urls\": [\n" +
//                "\t\t\t\"p1\",\n" +
//                "\t\t\t\"p2\"\n" +
//                "\t\t]\n" +
//                "\t},\n" +
//                "\t\"keyTranList\": [{\n" +
//                "\t\t\t\"method\": \"POST\",\n" +
//                "\t\t\t\"body\": \"body\",\n" +
//                "\t\t\t\"cookie\": [\n" +
//                "\t\t\t\t\"c1\",\n" +
//                "\t\t\t\t\"c2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"header\": [\n" +
//                "\t\t\t\t\"h1\",\n" +
//                "\t\t\t\t\"h2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"session\": [\n" +
//                "\t\t\t\t\"s1\",\n" +
//                "\t\t\t\t\"s2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"url\": \"/roleController/test/*\",\n" +
//                "\t\t\t\"urls\": [\n" +
//                "\t\t\t\t\"p1\",\n" +
//                "\t\t\t\t\"p2\"\n" +
//                "\t\t\t]\n" +
//                "\t\t},\n" +
//                "\t\t{\n" +
//                "\t\t\t\"method\": \"GET\",\n" +
//                "\t\t\t\"body\": \"body\",\n" +
//                "\t\t\t\"cookie\": [\n" +
//                "\t\t\t\t\"c1\",\n" +
//                "\t\t\t\t\"c2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"header\": [\n" +
//                "\t\t\t\t\"h1\",\n" +
//                "\t\t\t\t\"h2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"session\": [\n" +
//                "\t\t\t\t\"s1\",\n" +
//                "\t\t\t\t\"s2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"url\": \"/userController/test/*\",\n" +
//                "\t\t\t\"urls\": [\n" +
//                "\t\t\t\t\"p1\",\n" +
//                "\t\t\t\t\"p2\"\n" +
//                "\t\t\t]\n" +
//                "\t\t},\n" +
//                "\t\t{\n" +
//                "\t\t\t\"method\": \"POST\",\n" +
//                "\t\t\t\"body\": \"body\",\n" +
//                "\t\t\t\"cookie\": [\n" +
//                "\t\t\t\t\"c1\",\n" +
//                "\t\t\t\t\"c2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"header\": [\n" +
//                "\t\t\t\t\"h1\",\n" +
//                "\t\t\t\t\"h2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"session\": [\n" +
//                "\t\t\t\t\"s1\",\n" +
//                "\t\t\t\t\"s2\"\n" +
//                "\t\t\t],\n" +
//                "\t\t\t\"url\": \"/orderController/test/*\",\n" +
//                "\t\t\t\"urls\": [\n" +
//                "\t\t\t\t\"p1\",\n" +
//                "\t\t\t\t\"p2\"\n" +
//                "\t\t\t]\n" +
//                "\t\t}\n" +
//                "\t]\n" +
//                "}";
//
//
//        RemoteAgentConfig remoteAgentConfig = JSONObject.parseObject(respData,RemoteAgentConfig.class);
//        System.out.println();

//
//        System.out.println(RemoteKeyTransSymbol.any.toString());
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add("aaa");
//        jsonArray.add("bbb");
//        System.out.println(jsonArray.toJSONString());
//
//
//
        String testParameter = "{\n" +
                "\"name\": \"trans\",\n" +
                "\"values\": [\n" +
                "    {\n" +
                "\t\"type\":\"header\",\n" +
                "\t \"key\":\"u1\",\n" +
                "\t \"value\":\"u1\"\n" +
                "   },\n" +
                "   {\n" +
                "\t\"type\":\"header\",\n" +
                "\t \"key\":\"u1\",\n" +
                "\t \"value\":\"u1\"\n" +
                "   }\n" +
                "]\n" +
                "}";



    }




}
