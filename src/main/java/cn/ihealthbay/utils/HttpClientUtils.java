package cn.ihealthbay.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public final class HttpClientUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    public static final Charset CHARSET = Charset.forName("utf-8");
    public static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getJson(String uri) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        String json="";
        try {
            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                json =  IOUtils.toString(entity.getContent(), CHARSET);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return JsonUtils.deserialize(json);
    }
}
