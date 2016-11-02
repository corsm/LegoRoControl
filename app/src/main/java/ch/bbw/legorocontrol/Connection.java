package ch.bbw.legorocontrol;

import android.content.Intent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 02.11.2016.
 */
public class Connection {

    //final String hostAndIp;
    private URL url;
    private HttpURLConnection urlRobot;

    public Connection(String hostIP){
        try {
            url = new URL("http://" + hostIP);
            urlRobot = (HttpURLConnection) url.openConnection();
            urlRobot.setConnectTimeout(10 * 1000);          // = 10 Sekunden
            urlRobot.connect();
        }
     catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    public boolean checkConnection() throws IOException {
            if (urlRobot.getResponseCode() == 200) {        // 200 = "OK" http Verbindung ist OK
                return true;
            } else {
                return false;
            }
    }

    public boolean checkHost(String hostIP){
        try {
            URL url = new URL("http://"+hostIP);
            HttpURLConnection urlRobot = (HttpURLConnection) url.openConnection();
            urlRobot.setConnectTimeout(10 * 1000);          // = 10 Sekunden
            urlRobot.connect();
            if (urlRobot.getResponseCode() == 200) {        // 200 = "OK" http Verbindung ist OK
                return true;
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        {
        }
        return false;
    }


    public void sendRequest(String request){

    }
}
