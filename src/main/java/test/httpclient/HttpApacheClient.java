package test.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by mmamula on 16.3.2017.
 */
public class HttpApacheClient {

    public static void basicReq() {
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse res = client.execute(new HttpGet("http://46.101.176.121:9999/test.json"));
            System.out.println(res.getStatusLine().getStatusCode());
            System.out.println(res.getStatusLine().getReasonPhrase());
            Arrays.stream(res.getAllHeaders()).forEach((e) -> { System.out.println(e.getName() + ": " + e.getValue()); });
            System.out.println(getStringFromResponseEntity(res.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loginReq() {
        HttpClient client = HttpClientBuilder.create().build();
        try {
//            HttpPost post = new HttpPost("http://46.101.176.121:8080/login");
//            List<NameValuePair> data = new ArrayList<>();
//            data.add(new BasicNameValuePair("username", "demo"));
//            data.add(new BasicNameValuePair("password", "demo"));
//            post.setEntity(new UrlEncodedFormEntity(data));

            HttpResponse res = client.execute(
                    RequestBuilder
                            .post("http://46.101.176.121:8080/login")
                            .addParameter("username", "demo")
                            .addParameter("password", "demo")
                            .build()
            );
            System.out.println(res.getStatusLine().getStatusCode());
            System.out.println(res.getStatusLine().getReasonPhrase());
            Arrays.stream(res.getAllHeaders()).forEach((e) -> { System.out.println(e.getName() + ": " + e.getValue()); });
            System.out.println(EntityUtils.toString(res.getEntity()));

            HttpGet getReq = new HttpGet("http://46.101.176.121:8080/api/auth-status");
            getReq.setHeader("Accept", "application/json");
            HttpResponse statusRes = client.execute(getReq);
            System.out.println(statusRes.getStatusLine().getStatusCode());
            System.out.println(statusRes.getStatusLine().getReasonPhrase());
            Arrays.stream(statusRes.getAllHeaders()).forEach((e) -> { System.out.println(e.getName() + ": " + e.getValue()); });
            System.out.println(EntityUtils.toString(statusRes.getEntity()));

            HttpResponse logoutRes = client.execute(new HttpGet("http://46.101.176.121:8080/logout"));
            System.out.println(logoutRes.getStatusLine().getStatusCode());
            System.out.println(logoutRes.getStatusLine().getReasonPhrase());
            Arrays.stream(logoutRes.getAllHeaders()).forEach((e) -> { System.out.println(e.getName() + ": " + e.getValue()); });
            System.out.println(EntityUtils.toString(logoutRes.getEntity()));

            statusRes = client.execute(getReq);
            System.out.println(statusRes.getStatusLine().getStatusCode());
            System.out.println(statusRes.getStatusLine().getReasonPhrase());
            Arrays.stream(statusRes.getAllHeaders()).forEach((e) -> { System.out.println(e.getName() + ": " + e.getValue()); });
            System.out.println(EntityUtils.toString(statusRes.getEntity()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringFromResponseEntity(HttpEntity entity) throws IOException {
        int size = 1024 * 10;
        if (!(entity.getContentLength() == -1)) { size = (int) entity.getContentLength(); }
        try (InputStream resBodyStream = entity.getContent()) {
            System.out.println(entity.getContentLength());
            byte[] resBodyBytes = new byte[size];
            resBodyStream.read(resBodyBytes);
                return new String(resBodyBytes, "UTF-8");
        } catch (IOException e) {
            throw e;
        }
    }

}
