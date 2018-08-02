package pgg.lib_common.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pgg.lib_common.utils.CloseUtils;

/**
 * Created by PDD on 2018/3/10.
 */

public class OkHttpStreamFetcher implements DataFetcher<InputStream> {

    private OkHttpClient client;
    private GlideUrl glideUrl;
    private InputStream inputStream;
    private ResponseBody body;

    public OkHttpStreamFetcher(OkHttpClient client, GlideUrl glideUrl){
        this.client=client;
        this.glideUrl=glideUrl;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        Request.Builder requestBuilder = new Request.Builder().url(glideUrl.toURL());
        for (Map.Entry<String,String> entry :glideUrl.getHeaders().entrySet()){
            String key=entry.getKey();
            String value=entry.getValue();
            requestBuilder.addHeader(key,value);
        }
        Request request=requestBuilder.build();
        Response response = client.newCall(request).execute();
        body = response.body();
        if (!response.isSuccessful()){
            throw new IOException("Request failed with code: " + response.code());
        }

        long len= body.contentLength();
        inputStream = ContentLengthInputStream.obtain(body.byteStream(), len);
        return inputStream;
    }

    @Override
    public void cleanup() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                // Ignored
            }
        }
        if (body != null) {
            CloseUtils.closeIO(body);
        }
    }

    @Override
    public String getId() {
        return glideUrl.getCacheKey();
    }

    @Override
    public void cancel() {

    }
}
