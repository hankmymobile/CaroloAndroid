package com.gcarolo.loyalty.modules.webview;

import static com.gcarolo.loyalty.R.string.urlDefaultWebView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;


public class WebViewFragment extends BaseFragment {

    private View rootView;

    public WebViewFragment() {

    }


    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (null == this.rootView) {
            this.rootView = inflater.inflate(R.layout.fragment_webview, container, false);
            configViews();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void configViews() {

        // Find the WebView by its unique ID
        WebView webView = rootView.findViewById(R.id.webview);

        // loading https://www.geeksforgeeks.org url in the WebView.
        String url = getArguments().getString(URL,  getString(urlDefaultWebView));
        webView.loadUrl(url);

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());

    }
}