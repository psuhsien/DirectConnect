package nocompanydomain.directconnect;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import jcifs.Config;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (wifi.isWifiEnabled()) {

            String IPBin = Integer.toBinaryString(wifi.getConnectionInfo().getIpAddress());
            String IP = Integer.parseInt(IPBin.substring(0,8), 2) + "." +
                    Integer.parseInt(IPBin.substring(8,16), 2) + "." +
                    Integer.parseInt(IPBin.substring(16,24), 2) + "." +
                    Integer.parseInt(IPBin.substring(24,32), 2);


            ((TextView) findViewById(R.id.WiFi_SSID)).setText("SSID: " + wifi.getConnectionInfo().getSSID());
            ((TextView) findViewById(R.id.WiFi_IP)).setText("IP: " + IP);


            jcifs.Config.setProperty("jcifs.netbios.wins", "usernameIP");

            //new BackgroundAsyncTask().execute(0);


        }
        else {
            // make dialog to ask enable wifi

        }

        // start waiting connection


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    class BackgroundAsyncTask extends AsyncTask<Integer, Integer, Boolean> {
        String user = "user";
        String pass = "pass";
        String sharedFolder = "ShareFolder";
        String path = "smb://usernameIP/" + sharedFolder + "/text.txt";

        protected Boolean doInBackground(Integer... params) {

            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", user, pass);

            try {
                SmbFile smbFile = new SmbFile(path, auth);
                SmbFileOutputStream  smbfos = new SmbFileOutputStream(smbFile);
                smbfos.write("testing....and writing to a file. POI".getBytes());
                System.out.println("Done");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }
}
