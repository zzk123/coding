package com.zzk.testDemo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.checkerframework.checker.units.qual.A;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 导出小demo，从某个接口拉取分页数据，解析，导出到本地
 * @Author zzk
 * @Dare 2024/1/30
 **/
public class ExportDemo {

    public static void main(String[] args) throws Exception {
        File excelFile = new File("C:/");
        if(!excelFile.exists()){
            excelFile.createNewFile();
        }
        ExcelWriter builder = EasyExcel.write(excelFile, ExportModel.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("导出").build();
        try{
            List<ExportModel> exportModels = null;
            for(int i=1; ; i++) {
                try {
                    String result = getResponResult(50, 50 * i);
                    exportModels = getExportModel(result);
                }catch (Exception e){
                    exportModels = null;
                    e.printStackTrace();
                }
                if(CollectionUtils.isNotEmpty(exportModels)){
                    builder.write(exportModels, writeSheet);
                }else{
                    break;
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        builder.finish();
    }

    public static List<ExportModel> getExportModel(String result) throws Exception {
        List<ExportModel> exportDemoList = new ArrayList<>();
        JsonArray array = JsonParser.parseString(result).getAsJsonObject().getAsJsonObject("result").getAsJsonArray("data");
        System.out.println(array);
        JsonObject obj = null;
        ExportModel exportModel = null;
        for(int i=0; i<array.size(); i++){
            exportModel = new ExportModel();
            obj = array.get(i).getAsJsonObject();
            exportModel.setName(obj.get("name") != null && !obj.get("name").isJsonNull() ? obj.get("name").getAsString() : null);
            exportModel.setPhone(obj.get("tel") != null && !obj.get("tel").isJsonNull() ? obj.get("tel").toString() : null);
            exportModel.setContactPerson(obj.get("134343") != null && !obj.get("134343").isJsonNull() ? obj.get("134343").getAsString() : null);
            exportModel.setContactWay(obj.get("134344") != null && !obj.get("134344").isJsonNull() ? obj.get("134344").getAsString() : null);
            exportDemoList.add(exportModel);
        }
        return exportDemoList;
    }


    /**
     * 请求查询
     * @param limit
     * @param offset
     * @return
     * @throws Exception
     */
    public static String getResponResult(Integer limit, Integer offset) throws Exception {
        String url = "https://xxxxx";
        CloseableHttpClient httpClient = createSSLInsecureClient();
        //POST 请求
        HttpPost httpPost = new HttpPost(url);
        //设置接口请求头信息
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");


        JsonObject object = new JsonObject();
        object.add("customize", new JsonObject());
        object.addProperty("limit", limit);
        object.addProperty("offset", offset);
        object.addProperty("labelNotInclude", 0);
        object.add("labelIds", new JsonArray());

        HttpEntity postEntity = new StringEntity(object.toString(), ContentType.APPLICATION_JSON);

        httpPost.setEntity(postEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        }
        //5.关闭资源
        response.close();
        httpClient.close();
        return null;
    }

    /**
     * 创建信任所有证书的httpclient
     *
     * @return
     */
    private static CloseableHttpClient createSSLInsecureClient() {
        try {
            SSLContext sslContext = new org.apache.http.ssl.SSLContextBuilder().loadTrustMaterial(
                    null,
                    new TrustStrategy() {
                        // 信任所有证书
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    }
            ).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
