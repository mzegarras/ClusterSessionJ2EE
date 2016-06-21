package net.msonic.testsessionws;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;



/**
 * This Interceptor add all received Cookies to the app DefaultPreferences.
 * Your implementation on how to save the Cookies on the Preferences MAY VARY.
 * <p>
 * Created by tsuharesu on 4/1/15.
 */
public class DCB_ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        /*if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
                //Timber.d("DCB_ReceivedCookiesInterceptor : " + header); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp


            }

            //PreferenceHelper.getDefaultPreferences().edit().putStringSet(PreferenceHelper.PREF_BOX_COOKIES, cookies).apply();

        }*/

        return originalResponse;
    }
}