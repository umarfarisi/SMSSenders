package learn.com.smssender.application;

import android.app.Application;

import learn.com.smssender.utils.GlobalVariable;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class SMSSenderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalVariable.APPLICATION_CONTEXT = this;
    }
}
