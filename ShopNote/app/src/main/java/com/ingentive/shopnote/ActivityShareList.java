package com.ingentive.shopnote;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.ManageSectionModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ActivityShareList extends Activity {

    private Toolbar mToolbar;
    private List<ManageSectionModel> sectionData;
    private List<CurrentListModel> currList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_share_list);
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
        String[] TO = {"azharabbas1105@gmail.com"};
        String[] CC = {""};

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd");
        String formattedDate = df.format(c.getTime());
        System.out.println("day, month " + formattedDate);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL,"");
//        emailIntent.putExtra(Intent.EXTRA_CC, "");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Shopnote–" + formattedDate);
        sectionData = DatabaseHandler.getInstance(ActivityShareList.this).getSectionData();
        String str = "Shopnote shopping list for " + formattedDate + "\n\n\n";
        for (int i = 0; i < sectionData.size(); i++) {
            currList = DatabaseHandler.getInstance(ActivityShareList.this).getShoppingData(sectionData.get(i).getManageSectionId());
            if (currList.size() != 0) {
                str = str.concat(sectionData.get(i).getSectionName());
                str = str.concat("\n");
                for (int j = 0; j < currList.size(); j++) {
                    str = str.concat("\t\t\t●\t\t");
                    str = str.concat(currList.get(j).getItemName().toString());
                    str = str.concat("\n");
                }
                str = str.concat("\n");
            }
        }
        emailIntent.putExtra(Intent.EXTRA_TEXT, str);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ActivityShareList.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
