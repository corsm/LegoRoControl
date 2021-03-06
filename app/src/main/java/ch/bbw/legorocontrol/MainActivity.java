package ch.bbw.legorocontrol;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    EditText accessIP;
    public final static String CONNECTION_STRING = "ch.bbw.legorocontrol.CONNECTION_STRING";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_bot);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
//        setContentView(R.layout.activity_main);

/*
        final Button connectButton = (Button) findViewById(R.id.connectButton);
        final EditText accessIP = (EditText) findViewById(R.id.accessIP);

        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("accessIP", accessIP.getText().toString());
                //InetAddress.getByName(host).isReachable(timeOut)

                try {
                    URL url = new URL("http://"+accessIP);
                    HttpURLConnection urlRobot = (HttpURLConnection) url.openConnection();
                    urlRobot.setConnectTimeout(10 * 1000);          // = 10 Sekunden
                    urlRobot.connect();
                    if (urlRobot.getResponseCode() == 200) {        // 200 = "OK" http Verbindung ist OK
                        System.out.println("LegoRoboter verbunden");
                    } else {
                        //TODO: fehler Meldung in GUI
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                {
                }
            }
        });*/

    }

    public void connectRobo(View view) {
        accessIP = (EditText) findViewById(R.id.accessIP);

        Log.v("accessIP", accessIP.getText().toString());

        try { //TODO: ersetzen mit Connection Klasse
            if (!accessIP.getText().equals("")||!accessIP.equals(null)) {
                Connection connection = new Connection(accessIP.getText().toString());
                connection.connect();
                if (connection.testConnection()) {
                    System.out.println("MainActivity connection test OK");
                    Intent intent = new Intent(this, LegoControl.class);
                    intent.putExtra(CONNECTION_STRING, accessIP.getText().toString());
                    startActivity(intent);
                } else {
                    //TODO: fehler Meldung auf UI
                    System.out.println("Fehler in MainActivity testConnection");
                }
            }

            }catch(IOException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }


    }
}
