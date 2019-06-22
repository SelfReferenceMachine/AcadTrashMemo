package com.nomoressay.acadtrashmemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeFragment extends Fragment {

    private WebView webview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_home, null);

        //webView存在严重的安全问题，但由于本案例中不涉及到账号传输等问题只是显示界面进行内容的复制粘贴，所以仍然使用webView。

        webview = (WebView) view.findViewById(R.id.lib_link);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);//支持页面中的js显示

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        //支持页面的放大与缩小
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setAllowContentAccess(true);

        //最初在android manifest中添加允许网络访问之后，打开一个网页之后虚拟机出现access denied问题，未找到解决方案；使用网络上已有的错误抓取代码输出结果显示并不是程序和网络的问题；
        // 使用真机尝试发现可以在外部跳转https（虽然http不能），但是想要使用的网址都是http网址。查阅有关资料，需要在Android Manifest中的application进一步添加<application android:usesCleartextTraffic="true">这一代码。

        /**
         * 发现经过调试后，使用webview之后可以实现界面的打开，但是会跳转到别的页面，查阅资料后得知WebViewClient中的shouldOverrideUrlLoading方法默认返回为false。
         *         要使用内部的 WebView 加网页就要重写 shouldOverrideUrlLoading 方法，使其返回 true。 但添加的重写方法存在android版本不兼容问题的报错。
         *         尝试运行发现可行，问题是无法实现在页面内部的跳转，于是决定直接设置一个不存在动作的网页内容。
         */

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl("http://kns.cnki.net/kcms/detail/detail.aspx?filename=XTIB201001009&dbcode=CJFQ&dbname=CJFD2010&v=");
        return view;
    }

}
