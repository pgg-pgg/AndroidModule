package pgg.lib_common.glide;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by PDD on 2018/3/10.
 */

public class OkHttpUrlLoader implements ModelLoader<GlideUrl,InputStream> {

    private final OkHttpClient client;

    public OkHttpUrlLoader(OkHttpClient client) {
        this.client = client;
    }
    /**
     * The default factory for {@link OkHttpUrlLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<GlideUrl,InputStream>{
        private static volatile OkHttpClient internalClient;
        private OkHttpClient client;
        private static OkHttpClient getInternalClient(){
            if (internalClient==null){
                synchronized (Factory.class){
                    if (internalClient==null){
                        internalClient=new OkHttpClient();
                    }
                }
            }
            return internalClient;
        }

        /**
         * Constructor for a new Factory that runs requests using a static singleton client.
         */
        public Factory() {
            this(getInternalClient());
        }

        /**
         * Constructor for a new Factory that runs requests using given client.
         */
        public Factory(OkHttpClient client) {
            this.client = client;
        }
        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new OkHttpUrlLoader(client);
        }

        @Override
        public void teardown() {

        }
    }
    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        return new OkHttpStreamFetcher(client,model);
    }
}
