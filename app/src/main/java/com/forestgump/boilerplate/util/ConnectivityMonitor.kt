package com.forestgump.boilerplate.util

/* ktlint-disable no-wildcard-imports */
import android.net.*
import androidx.lifecycle.*
/* ktlint-enable no-wildcard-imports */

class ConnectivityMonitor(
    private val connectivityManager: ConnectivityManager
) : LifecycleObserver {

    private var isActive = false

    private val status = MutableLiveData<Boolean>()
    val connectedStatus: LiveData<Boolean>
        get() = status

    private val connectivityCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            status.postValue(true)
            connectivityManager.unregisterNetworkCallback(this)
            isActive = false
        }

        override fun onLost(network: Network) {
            status.postValue(false)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stop() {
        if (isActive) {
            connectivityManager.unregisterNetworkCallback(connectivityCallback)
            isActive = false
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun start() {
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        val connected = activeNetworkInfo != null && activeNetworkInfo.isConnected
        status.postValue(connected)
        if (!connected) {
            // we don't have internet connection, so we listen to notifications in connection status
            connectivityManager.registerNetworkCallback(
                    NetworkRequest.Builder()
                            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build(),
                    connectivityCallback
            )
            isActive = true
        }
    }
}
