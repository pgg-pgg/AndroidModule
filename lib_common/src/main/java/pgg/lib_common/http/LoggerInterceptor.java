package pgg.lib_common.http;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by PDD on 2018/3/10.
 */

public class LoggerInterceptor implements Interceptor {

    public static final String TAG = "HttpClient";
    private String tag;
    private boolean showResponse;

    public LoggerInterceptor(String tag, boolean showResponse) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    public LoggerInterceptor(String tag) {
        this(tag, false);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        logForRequest(request);
        Response proceed = chain.proceed(request);
        return logForResponse(proceed);
    }

    /**
     * 按照格式打印出响应结果
     * @param response
     * @return
     */
    private Response logForResponse(Response response) {
       try{
           Response.Builder builder = response.newBuilder();
           Response build = builder.build();
           Logger.d("url : " + build.request().url() + "  ║  code : " + build.code() + "  ║  protocol : " + build.protocol());
           if (!TextUtils.isEmpty(build.message()))Logger.d("message : " + build.message());
           if (showResponse){
               ResponseBody body = build.body();
               if (body!=null){
                   MediaType mediaType = body.contentType();
                   if (mediaType!=null){
                       if (isText(mediaType)){
                           String string = body.string();
                           switch (mediaType.subtype()){
                               case "xml":
                                   Logger.xml(string);
                                   break;
                               case "json":
                                   Logger.json(string);
                                   break;
                               default:
                                   Logger.d(string);
                                   break;
                           }
                           body = ResponseBody.create(mediaType, string);
                           return response.newBuilder().body(body).build();
                       }else {
                           Logger.e("responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                       }
                   }
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }

    /**
     * 打印出请求的http
     * @param request
     */
    private void logForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();
            Logger.d("method : " + request.method() + "  ║  url : " + url);
            if (headers != null && headers.size() > 0) {
                //Logger.d("headers : " + headers.toString());
            }

            RequestBody body = request.body();
            if (body!=null){
                MediaType mediaType = body.contentType();
                if (mediaType!=null){
                    if (isText(mediaType)){
                        Logger.d("requestBody's content : " + bodyToString(request));
                    }else {

                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String bodyToString(Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }

    private boolean isText(MediaType mediaType) {
        if(mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }
}
