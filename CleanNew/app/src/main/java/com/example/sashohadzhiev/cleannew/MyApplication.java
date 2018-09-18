package com.example.sashohadzhiev.cleannew;

import android.app.Application;
import android.util.Log;

import com.example.sashohadzhiev.cleannew.BuildConfig;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by sashohadzhiev on 9/10/18.
 */

public class MyApplication extends Application {

    @Variable(group="sashoTest") public static String sashoTestVar = "sasho test var";
    @Variable(name="Powerups.Speed.Price") public static double speedPrice = 10;
    @Variable(name="Powerups.Speed.Duration") public static double speedDuration = 5;
    @Variable(name="Powerups.Speed.OrderInStore") public static int speedOrder = 0;


    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        String appId = "app_HvWGVd0RLCrUAbfQuohAwAvV5ylyeGPEc0l4XTTXH9s";
        String prodKey = "prod_ooYVXJMxoqGOZ3IR8OGp2IphGeeuX2BBtDhpHSfcLG4";
        String devKey = "dev_X4WCI9bNEXIXN97tX0cUJCZfaSNwiSDyvPP5cl57Ubs";

        if (BuildConfig.DEBUG) {
            System.out.println("DEV MODE");
            Leanplum.setAppIdForDevelopmentMode(appId, devKey);
        } else {
            System.out.println("PROD MODE");
            Leanplum.setAppIdForProductionMode(appId, prodKey);
        }

        Leanplum.start(this);

        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.e("   Leanplum ", "Variables Changed And No Downloads Pending");
            }
        });
    }
}
