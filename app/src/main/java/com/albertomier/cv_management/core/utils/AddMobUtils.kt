package com.albertomier.cv_management.core.utils

import android.content.Context
import android.util.Log

//var mInterstitialAd: InterstitialAd? = null
//
////load the interstitial ad
//fun loadInterstitial(context: Context) {
//    InterstitialAd.load(
//        context,
//        context.getString(R.string.ad_id_interstitial),
//        AdRequest.Builder().build(),
//        object : InterstitialAdLoadCallback() {
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                mInterstitialAd = interstitialAd
//                Log.d("Admob", "onAdLoaded: Ad was loaded.")
//            }
//
//            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
//                mInterstitialAd = null
//                Log.d("Admob", "onAdFailedToLoad: ${loadAdError.message}")
//            }
//        }
//    )
//}
//
//fun showInterstitial(context: Context) {
//    val activity = context.findActivity()
//
//    if (mInterstitialAd != null) {
//        mInterstitialAd?.show(activity!!)
//    } else {
//        Log.d("Admob", "showInterstitial: The interstitial ad wasn't ready yet.")
//    }
//}