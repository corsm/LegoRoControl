package ch.bbw.legorocontrol;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 02.11.2016.
 */
public class LegoControl extends AppCompatActivity implements SensorEventListener{

    private LegoRobot robot;
    private static Connection connection;
    private SensorManager sManager;
    private TextView tv;
    private Map<String, Float> directions;
    private String legoIP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lego_control);

        tv = (TextView) findViewById(R.id.textView);

        //hook
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), 5);
        //SensorManager.SENSOR_DELAY_FASTEST); fastest war zu schnell, führte zu request overflow bei der connection

        Intent intent = getIntent();
        legoIP = intent.getStringExtra(MainActivity.CONNECTION_STRING);

        //connection = new Connection(legoIP);

        //TODO: neue Lösung für POST request um Roboter Klasse wieder verwenden zu können
        //robot = new LegoRobot(connection);


    }

    public void steuerungStarten(View view) throws Exception{

        directions =  new HashMap<>();
        float speed = 100;
        directions.put("m", speed);
        //robot.drive(directions);

        connection = new Connection(legoIP);

        connection.connect();
        //connection.sendRequest("m=100");
    }





        //when this Activity starts
        @Override
        protected void onResume()
        {
            super.onResume();
                //register listener
            sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
        }


        @Override
        protected void onStop()
        {
            //unregister listener
            sManager.unregisterListener(this);
            super.onStop();
        }

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1)
        {
            //nichts machen
        }

        @Override
        public void onSensorChanged(SensorEvent event)
        {
            //textoutput zur info
            tv.setText("Orientation X (Roll) :"+ Float.toString(event.values[2]) +"\n"+
                    "Orientation Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+
                    "Orientation Z (Yaw) :"+ Float.toString(event.values[0]));


            //nur fahren wenn keine Wand vor dem Roboter ist
            //isWall() returnt true bei Wand vorne !
            //if(connection.isWall()){
            //TODO: isWall() debuggen
            //}
          //  else {
            if (connection!=null){
            try {

                //directions.put("m", event.values[1]);
                //robot.drive(directions);


                String driveForward;
                String driveLeft;
                String driveRight;

                //nur fahren wenn nach vorne gekippt
                if (event.values[1]>15){ //nur bei starker kippung
                    int tryForward = (int) ((event.values[1]*1)); //war *100, jetzt kleinere werte
                    driveForward = "m="+tryForward;
                    connection.connect();
                    connection.sendRequest(driveForward);
                }

                //wert von der X achse in links oder rechts kippung umrechnen
                if (event.values[2]<-15){//nur bei starker kippung
                    int tryRight = (int) ((Math.abs(event.values[2])*1));//war *100, jetzt kleinere werte
                    driveRight = "l="+tryRight;
                    connection.connect();
                    connection.sendRequest(driveRight);
                }
                else if(event.values[2]>15){//nur bei starker kippung
                    int tryLeft = (int) ((event.values[2]*1));//war *100, jetzt kleinere werte
                    driveLeft = "r="+tryLeft;
                    connection.connect();
                    connection.sendRequest(driveLeft);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }}
            //connection.sendRequest("l=");
//        }
    }
}

