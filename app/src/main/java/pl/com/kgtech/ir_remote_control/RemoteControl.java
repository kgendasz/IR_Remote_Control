package pl.com.kgtech.ir_remote_control;

import android.hardware.ConsumerIrManager;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;


/**
 * Created by Krzysiek_G on 2016-04-12.
 */
public  class RemoteControl {

    ConsumerIrManager mCIR;

    //------- Konstruktor --------
    public RemoteControl(AppCompatActivity ap){
        // Get a reference to the ConsumerIrManager
        mCIR = (ConsumerIrManager) ap.getSystemService(Context.CONSUMER_IR_SERVICE);



    }
}
