package pl.com.kgtech.ir_remote_control;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ConsumerIrManager mCIR;
    SparseArray<String> irData;

    IrRemoteControl oIrRemoteControl;

    Button buttonVolumeUp;
    Button buttonVolumeDown;
    Button buttonChannelUp;
    Button buttonChannelDown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonVolumeUp = (Button) findViewById(R.id.buttonVolumeUp);
        buttonVolumeDown = (Button) findViewById(R.id.buttonVolumeDown);
        buttonChannelUp = (Button) findViewById(R.id.buttonChannelUp);
        buttonChannelDown = (Button) findViewById(R.id.buttonChannelDown);

        oIrRemoteControl = new IrRemoteControl(this);

        // Get a reference to the ConsumerIrManager
        mCIR = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);


    }

    public void onEndClick(View view) {

        finish();
    }



    private static final int SAMSUNG_FREQ = 38028;
    private static final int[] SAMSUNG_POWER_TOGGLE_COUNT = {169,168,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,63,21,63,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,63,21,21,21,21,21,21,21,21,21,21,21,21,21,64,21,21,21,63,21,63,21,63,21,63,21,63,21,63,21,1794,169,168,21,21,21,3694};

    private static final int[] SAMSUNG_POWER_TOGGLE_DURATION = {4368,546,1638,546,1638,546,1638,546,546,546,546,546,546,546,546,546,546,546,1638,546,1638,546,1638,546,546,546,546,546,546,546,546,546,546,546,546,546,1638,546,546,546,546,546,546,546,546,546,546,546,546,546,1664,546,546,546,1638,546,1638,546,1638,546,1638,546,1638,546,1638,546,46644,4394,4368,546,546,546,96044};

    public void buttonClick(View view) {

        Button oButton = (Button)view;

        switch (view.getId())
        {
            case R.id.buttonVolumeUp:
                oIrRemoteControl.VolumeUp();
                break;
            case R.id.buttonVolumeDown:
                oIrRemoteControl.VolumeDown();
                break;
            case R.id.buttonChannelUp:
                oIrRemoteControl.ChannelUp();
                break;
            case R.id.buttonChannelDown:
                oIrRemoteControl.ChannelDown();
                break;

        }


        return;

       /* String data = hex2dec("0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 003f 0015 003f 0015 0015 0015 0040 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e");

        if (data != null) {
            String values[] = data.split(",");
            int[] pattern = new int[values.length - 1];

            for (int i = 0; i < pattern.length; i++) {
                pattern[i] = Integer.parseInt(values[i + 1]);
            }

            mCIR.transmit(Integer.parseInt(values[0]), pattern);
            Log.e("My app", "Finish transmit\n");
        }
*/
    }


    protected String hex2dec(String irData) {
        List<String> list = new ArrayList<String>(Arrays.asList(irData
                .split(" ")));
        list.remove(0); // dummy
        int frequency = Integer.parseInt(list.remove(0), 16); // frequency
        list.remove(0); // seq1
        list.remove(0); // seq2

        for (int i = 0; i < list.size(); i++) {
            list.set(i, Integer.toString(Integer.parseInt(list.get(i), 16)));
        }

        frequency = (int) (1000000 / (frequency * 0.241246));
        list.add(0, Integer.toString(frequency));

        irData = "";
        for (String s : list) {
            irData += s + ",";
        }
        return irData;
    }








}
