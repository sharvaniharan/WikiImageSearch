package com.androiddev.sharvani.wikiimagesearch.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sharvani on 7/2/17.
 */

public class NetworkUtils {
    public static boolean hasConnection(Context context) {
        return getConnectivityType(context) != Type.NOT_CONNECTED;
    }

    public static int getConnectivityType(Context context) {
        int type = Type.NOT_CONNECTED;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                type = Type.WIFI;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                type = Type.MOBILE;
            }
        }

        return type;
    }

    public static final class Type {
        public static int NOT_CONNECTED = 0;
        public static int WIFI = 1;
        public static int MOBILE = 2;
    }

}
