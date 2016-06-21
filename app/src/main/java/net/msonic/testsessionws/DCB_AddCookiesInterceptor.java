package net.msonic.testsessionws;


/**
 * Created by user on 2015-06-17.
 */


import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * This interceptor put all the Cookies in Preferences in the Request.
 * Your implementation on how to get the Preferences MAY VARY.
 * <p>
 */
public class DCB_AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        Map<String,String> preferences = Session.getInstance().valores;//(HashSet) PreferenceHelper.getDefaultPreferences().getStringSet(PreferenceHelper.PREF_BOX_COOKIES, new HashSet<String>());

        for (Map.Entry<String,String> entry : preferences.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.addHeader(key , value);
        }


        return chain.proceed(builder.build());
    }
}