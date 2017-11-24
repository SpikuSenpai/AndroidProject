package cutingapp.cuting.org.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebViewCutingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_cuting);

        String webpageToshow = this.getIntent().getStringExtra("webpageToShow");
        WebView v = (WebView) findViewById(R.id.cuting_webpage);
        v.setWebChromeClient(new WebChromeClient());
        v.loadUrl(webpageToshow);
    }
}
