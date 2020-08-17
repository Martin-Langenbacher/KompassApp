package de.androidnewcomer.kompass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class KompassnadelView extends View {

    private float winkel=0;
    private Paint zeichenfarbe = new Paint();


        // Konstruktor:
    public KompassnadelView(Context context) {
        super(context);

        // Eigenschaft des Pinsels setzen:
        zeichenfarbe.setAntiAlias(true);    // AntiAlias sorgt für glatte Kanten, indem -albe Pixel- durchscheinend gezeichnet werden. Das vermeidet unschöne Treppcheneffekte
        zeichenfarbe.setColor(Color.WHITE);
        zeichenfarbe.setStyle(Paint.Style.FILL); // alles was wir zeichnen kann einen Rand (STROKE), eine Fläche (FILL) oder beides haben (FILL_AND_STROKE)
    }


    public void setWinkel(float winkel) {
        this.winkel = winkel;
        invalidate();
    }
        // die Methode invalidate() teilt Android mit, dass der zuletzt gezeichnete Inhalt der View nicht mehr aktuell ist. Der Aufruf bewirkt, dass Android bei nächster Gelegenheit dafür sorgt, dass die View neu gezeichnet wird.


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);

            // Länge der Nadel des Kompasses --> der kleinere Wert des Handies (Höhe, Breite);
        int breite = canvas.getWidth();
        int hoehe = canvas.getHeight();
        int laenge = Math.min(breite, hoehe);

        Path pfad = new Path();
        pfad.moveTo(0, -laenge/2);
        pfad.lineTo(laenge/20, laenge/2);
        pfad.lineTo(-laenge/20, laenge/2);
        pfad.close();

        canvas.translate(breite/2, hoehe/2);
        canvas.rotate(winkel);
        canvas.drawPath(pfad, zeichenfarbe);
    }



}
