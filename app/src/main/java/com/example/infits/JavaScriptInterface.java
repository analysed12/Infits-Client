package com.example.infits;

import android.webkit.JavascriptInterface;

public class JavaScriptInterface {
    LiveAct liveAct;
    JavaScriptInterface(LiveAct liveAct){
        this.liveAct = liveAct;
    }
    @JavascriptInterface
    public void onPeerConnected(){
        liveAct.onPeerConnected();
    }
}
