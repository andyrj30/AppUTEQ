package com.example.andres_dell.uteqdemo.fragments;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.example.andres_dell.uteqdemo.R;


public class WebViewFragment extends android.support.v4.app.Fragment {

    View view;
    private WebView webview;


    public WebViewFragment() {
        // Required empty public constructor
    }

    public static WebViewFragment newInstance(String url) {
        WebViewFragment f = new WebViewFragment();
        f.setArguments(arguments(url));
        return f;
    }

    public static Bundle arguments(String url) {
        return new Bundler()
                .putString("url", url)
                .get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_web_view, container, false);
        final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setProgressStyle(ProgressDialog.BUTTON_POSITIVE);//Aqui podemos cambiar el estilo del mensaje de carga
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        webview = (WebView) view.findViewById(R.id.wvMenuUniversidaad);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");

        //JavaScript
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {
            // android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"
            //android:configChanges="keyboard|keyboardHidden|orientation|screenSize">

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //esto elimina ProgressBar.
                progressDialog.dismiss();
            }

        });
        webview.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
                    webview.goBack();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public boolean canGoBack() {
        return webview.canGoBack();
    }
}