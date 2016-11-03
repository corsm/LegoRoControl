package ch.bbw.legorocontrol;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.IOError;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 02.11.2016.
 */
public class TestingClass extends AppCompatActivity {



    @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_test);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

                /*System.out.println("angelndubauer");
                String connection;
                InetAddress inetAddress = null;
                connection = "10.10.12.192";
                inetAddress.getByName(connection);
                if (inetAddress.isReachable(5000)) {
                    System.out.println("OK");
                } else {
                    System.out.println("FFS");
                }
              //  System.out.println(urlConnection.getReadTimeout());
                Thread.sleep(1000000);
            }
            catch(MalformedURLException e){
                System.out.println(e.getStackTrace().toString());
            }
                catch(IOException e){
                    System.out.println(e.getStackTrace().toString());
                }
        catch(InterruptedException e){
        System.out.println(e.getStackTrace().toString());
        }*/
            Connection con = new Connection("10.10.12.189");

                try {
                    con.connect();
                    con.sendRequest("m=100");
                } catch (Exception e) {
                    e.printStackTrace();
                }



}}