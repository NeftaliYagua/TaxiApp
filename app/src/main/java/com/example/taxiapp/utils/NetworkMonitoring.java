package com.example.taxiapp.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.List;

public class NetworkMonitoring extends LiveData<Boolean> {

    private ConnectivityManager.NetworkCallback networkCallback;
    private final ConnectivityManager connectivityManager;
    private List<Network> validNetworks;

    public NetworkMonitoring(Context context) {

        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void checkValidNetworks() {
        postValue(validNetworks.size()>0);
    }

    @Override
    protected void onActive() {
        super.onActive();
        networkCallback = createNetworkCallback();
        NetworkRequest networkRequest = new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
        connectivityManager.registerNetworkCallback(networkRequest,networkCallback);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    private ConnectivityManager.NetworkCallback createNetworkCallback() {
        return new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                boolean isInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                if (isInternet) validNetworks.add(network);
                checkValidNetworks();
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                validNetworks.remove(network);
                checkValidNetworks();
            }
        };
    }
}
