package ch.bbw.legorocontrol;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 02.11.2016.
 */
public class Connection {

    //final String hostAndIp;
    private URL url;

    private final String USER_AGENT = "Mozilla/5.0";

    private HttpURLConnection con;

    public Connection(String hostIP) {
        try {
            //System.out.println("Konstruktor anfang...");
            url = new URL("http://" + hostIP);
            //urlRobot = url.openConnection();
            //urlRobot.setConnectTimeout(10 * 1000);          // = 10 Sekunden
            //urlRobot.connect();
            System.out.println("neue Connection aufgerufen");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean testConnection() throws Exception{

        //con.setConnectTimeout(10 * 1000);          // = 10 Sekunden


            if (con.getResponseCode() == 200) {        // 200 = "OK" http Verbindung ist OK oder con.getResponseCode();
                System.out.println("testConnection ist OK");
                return true;
            } else {
                return false;
            }
    }



    public boolean isWall(){

        String wertSonic = "6"; //default = fahren

        try {
            con = (HttpURLConnection) url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            String inhaltSeite = new String(baos.toByteArray(), encoding);

            int indexOfComma = inhaltSeite.indexOf(',');
            wertSonic = inhaltSeite.substring(0, indexOfComma);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Integer.getInteger(wertSonic)>5){
            return false; //keine Wand vorne -> fahren
        }
        else{
            return true; //Wand vorne -> nict fahren
        }

    }



    public void connect() throws IOException {
        con = (HttpURLConnection) url.openConnection();//
    }


    public void sendRequest(String urlParameters) throws Exception {


        //connection muss jedes mal neu aufgesetzt werden
        //TODO: neue lösung für POST request finden, dass keine erneute verbindung erfordert

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        // Send request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        //int responseCode =
        con.getResponseCode();


    }

     /* public boolean testConnection() {


        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(url.toString(), 139);
            socket.connect(socketAddress, 100);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }*/


    /*public boolean checkNewHost(String hostIP){ //mit einer neuen IP Adresse
        try {
            URL url = new URL("http://"+hostIP);
            HttpURLConnection urlRobo = (HttpURLConnection) url.openConnection();
            urlRobo.setConnectTimeout(10 * 1000);          // = 10 Sekunden
            urlRobo.connect();
            if (urlRobo.getResponseCode() == 200) {        // 200 = "OK" http Verbindung ist OK
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
*/
}
