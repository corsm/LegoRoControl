package ch.bbw.legorocontrol;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class LegoControlSensor extends Activity implements SensorEventListener
{
    //a TextView
    private TextView tv;
    private float xCord;
    private float yCord;
    //the Sensor Manager
    private SensorManager sManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lego_control_sensor);

        tv = (TextView)findViewById(R.id.textView);

        //get a hook to the sensor service
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);



    }

    //Wird aufgerufen, wenn die Aktivität gestartet wird
    @Override
    protected void onResume()
    {
        super.onResume();
        /*register the sensor listener to listen to the gyroscope sensor, use the
        callbacks defined in this class, and gather the sensor information as quick
        as possible*/
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
    }

    //Wird aufgerufen, wenn die Aktivität nicht mehr sichtbar ist
    @Override
    protected void onStop()
    {
        //unregister the sensor listener
        sManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        //Do nothing.
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        //Überprüft ob Sensor vertrauenswürdig ist
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }

        xCord = event.values[2];
        yCord = event.values[1];

        tv.setText("Orientation X (Roll) :"+ Float.toString(xCord) +"\nOrientation Y (Pitch) :"+ Float.toString(yCord) +"\nOrientation Z (Yaw) :"+ Float.toString(event.values[0]));

    }

    public float getxCord() {
        return xCord;
    }

    public float getyCord() {
        return yCord;
    }
}