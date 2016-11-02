package ch.bbw.legorocontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 02.11.2016.
 */
public class LegoControl extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lego_control);

        Intent intent = getIntent();
        String legoIP = intent.getStringExtra(MainActivity.CONNECTION_STRING);


        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }
}
