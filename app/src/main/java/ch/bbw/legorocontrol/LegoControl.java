package ch.bbw.legorocontrol;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 02.11.2016.
 */
public class LegoControl extends AppCompatActivity{

    private LegoRobot robot;
    private Connection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lego_control);

        Intent intent = getIntent();
        String legoIP = intent.getStringExtra(MainActivity.CONNECTION_STRING);

        connection = new Connection(legoIP);

        robot = new LegoRobot(connection);


    }

    public void steuerungStarten(View view){

        SensorManager sensorManager = new S;

        Map<String, Float> directions = new HashMap<>();

        float speed = 100;

        directions.put("m", speed);

        robot.drive(directions);
    }
}
