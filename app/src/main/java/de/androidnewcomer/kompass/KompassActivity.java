package de.androidnewcomer.kompass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class KompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magnetfeldSensor;
    private KompassnadelView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new KompassnadelView(this);
        setContentView(view);
        //setContentView(R.layout.main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetfeldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }


    // SensorManager ist asynchron; darum melden wir ihn bei einem Listener an: Der wird benachrichtigt, wenn es etwsa Neues gibt.

    // Vorgehen:
    // 1. Sobald die Activity aktiviert wird, abonnieren wir Sensor-Ereignisse
    // 2. Sobald ein Sensor-Ereignis eintrifft, zeichnen wir die Kompassnadel im richtigen Drehwinkel
    // 3. Sobald die Activity gestoppt wird, kündigen wir unser Abo.

    // --> Ereignisbehandlungsmethode der Activity-Klasse: Wird eine Activity in den Vordergrund geholt, wird onResume() aufgerufen:
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, magnetfeldSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
    // a) SensorManager ist für die Verwaltung der Abo zuständig; nicht der Sensor. --> 2 Parameter übergeben:
    //      i) Als ersten Parameter müssen wir einen SensorEventListener angeben, genauer gesagt ein Objekt, das dieses Interface anbietet.
    //      ii) Dritter Parameter des registerListener()-Aufrufs definiert die gewünschte zeitliche Auflösung - SENSOR_DELAY_FASTEST genemigt sich zu viel Batterieladung...



    @Override
    public void onSensorChanged(SensorEvent event) {
        if(view!=null) {
            view.setWinkel(-event.values[0]);
        }
    }

        // nicht verwendet, muss aber da sein!
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    // Listener wird in onPause() agbemeldet:
    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }



}