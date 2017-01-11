package nocompanydomain.directconnect;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
}
