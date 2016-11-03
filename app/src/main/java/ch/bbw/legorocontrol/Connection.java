package ch.bbw.legorocontrol;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

    public boolean testConnection() throws IOException {

        con.setConnectTimeout(10 * 1000);          // = 10 Sekunden
        con.connect();
        if (con.getReadTimeout() == 200) {        // 200 = "OK" http Verbindung ist OK
            System.out.println("testConnection ist OK");
            return true;
        } else {
            return false;
        }
    }

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


  /*  public void sendRequest(String request){ //AJAX
        try {
            //final URL url = new URL("http://localhost:8080/SearchPerson.aspx/PersonSearch");
            //final URLConnection urlConnection = url.openConnection();
            urlRobot.setDoOutput(true);
            urlRobot.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            urlRobot.connect();
            final OutputStream outputStream = urlRobot.getOutputStream();
            outputStream.write(("{\"fNamn\": \"" + request + "\"}").getBytes("UTF-8"));
            outputStream.flush();
            final InputStream inputStream = urlRobot.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void connect() throws IOException {
        con = (HttpURLConnection) url.openConnection();//
    }

    public void sendRequest(String urlParameters) throws Exception {
        //String url = "http://10.10.12.189";
        //URL url = new URL(this.url);

        // HttpURLConnection con = (HttpURLConnection) url.openConnection();//

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        //int responseCode =
        con.getResponseCode();
        //System.out.println("\nSending 'POST' request to URL : " + url);
        //System.out.println("Post parameters : " + urlParameters);
        //System.out.println("Response Code : " + responseCode);

        //BufferedReader in = new BufferedReader(
        //        new InputStreamReader(con.getInputStream()));
        //String inputLine;
        //StringBuffer response = new StringBuffer();

        //while ((inputLine = in.readLine()) != null) {
        //   response.append(inputLine);
        //}
        //in.close();

        //print result
        // System.out.println(response.toString());

    }
}
