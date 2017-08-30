package com.zzr.rem.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zzr.rem.R;
import com.zzr.rem.activity.ProductDetailsActivity;
import com.zzr.rem.cons.NetworkConst;

/**
 * Created by lean on 16/10/28.
 */

public class ProductDetailsFragment extends BaseFragment {
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_details,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    @Override
    protected void initUI() {
        mWebView = (WebView) getActivity().findViewById(R.id.webview);
        ProductDetailsActivity activity = (ProductDetailsActivity) getActivity();
        Log.v("aa", "http://mall.520it.com/productDetails?productId=" + activity.mProductId);

        // TODO
        // mWebView.loadUrl(NetworkConst.PRODUCTDETAILS_URL + "?productId=" + activity.mProductId);
        mWebView.loadUrl("http://mall.520it.com/productDetail?productId=" + activity.mProductId);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
    }
}
