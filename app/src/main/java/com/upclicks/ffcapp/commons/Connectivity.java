package com.upclicks.ffcapp.commons;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.upclicks.ffcapp.base.BaseViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class Connectivity {
    Context context;
    ConnectivityManager cm;
    ConnectivityManager.NetworkCallback networkCallback;
    @Inject public Connectivity(@ApplicationContext Context context) {
        this.context=context;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public NetworkInfo getNetworkInfo(){
        return cm.getActiveNetworkInfo();
    }

    public boolean isConnected(){
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected());
    }

    public boolean isConnectedWifi(){
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     * @return
     */
    public boolean isConnectedMobile(){
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * Check if there is fast connectivity
     * @return
     */
    public boolean isConnectedFast(){
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && isConnectionFast(info.getType(),info.getSubtype()));
    }

    /**
     * Check if the connection is fast
     * @param type
     * @param subType
     * @return
     */
    public boolean isConnectionFast(int type, int subType){
        if(type==ConnectivityManager.TYPE_WIFI){
            return true;
        }else if(type==ConnectivityManager.TYPE_MOBILE){
            switch(subType){
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400-7000 kbps
                /*
                 * Above API level 7, make sure to set android:targetSdkVersion
                 * to appropriate level to use these
                 */
                case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                    return true; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                    return true; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                    return true; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                    return false; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                    return true; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        }else{
            return false;
        }
    }


    public String getNetworkConnectionType() {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnected())
            return "0"; // not connected
        if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return "1"; //Wifi
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:     // api< 8: replace by 11
                case TelephonyManager.NETWORK_TYPE_GSM:      // api<25: replace by 16
                    return "3"; //2G Data
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:   // api< 9: replace by 12
                case TelephonyManager.NETWORK_TYPE_EHRPD:    // api<11: replace by 14
                case TelephonyManager.NETWORK_TYPE_HSPAP:    // api<13: replace by 15
                case TelephonyManager.NETWORK_TYPE_TD_SCDMA: // api<25: replace by 17
                    return "4"; //3G Data
                case TelephonyManager.NETWORK_TYPE_LTE:      // api<11: replace by 13
                case TelephonyManager.NETWORK_TYPE_IWLAN:    // api<25: replace by 18
                case 19: // LTE_CA
                    return "5"; //4G Data
                case TelephonyManager.NETWORK_TYPE_NR:       // api<29: replace by 20
                    return "6"; //5G Data
                default:
                    return "0"; //Unknown
            }
        }
        return "?";
    }


    public void observeConnection(BaseViewModel baseViewModel) {
        if(networkCallback==null) {
            networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    baseViewModel.noConnection.postValue(false);
                }

                @Override
                public void onLost(Network network) {
                    baseViewModel.noConnection.postValue(true);
                }
            };
        }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cm.registerDefaultNetworkCallback(networkCallback);
            } else {
                NetworkRequest request = new NetworkRequest.Builder()
                        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
                cm.registerNetworkCallback(request, networkCallback);
            }
        }
}
