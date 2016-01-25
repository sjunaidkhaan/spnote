package com.ingentive.shopnote;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityFeedBack extends Activity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_feed_back);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sendEmail();
    }
    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"shopnote@outlook.com"};
        String[] CC = {""};

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd");
        String formattedDate = df.format(c.getTime());
        System.out.println("day, month " + formattedDate);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_CC, "");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Shopnote–"+formattedDate+" Feedback");

        String str = "How often do you use Shopnote:\n";
        str = str.concat("\t\t\t●\t\tBarely ever\n");
        str = str.concat("\t\t\t●\t\tSometimes\n");
        str = str.concat("\t\t\t●\t\tRegularly\n\n");

        str = str.concat("Features I use the most:\n");
        str = str.concat("\t\t\t●\t\t_\n");
        str = str.concat("\t\t\t●\t\t_\n");
        str = str.concat("\t\t\t●\t\t_\n\n");

        str = str.concat("Features I use the least:\n");
        str = str.concat("\t\t\t●\t\t_\n");
        str = str.concat("\t\t\t●\t\t_\n");
        str = str.concat("\t\t\t●\t\t_\n\n");

        str = str.concat("Features I would like to see:\n\n");

        str = str.concat("Remarks on the design (colors, logo, icons) or other comments:\n\n");

        emailIntent.putExtra(Intent.EXTRA_TEXT, str);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending emal", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ActivityFeedBack.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}