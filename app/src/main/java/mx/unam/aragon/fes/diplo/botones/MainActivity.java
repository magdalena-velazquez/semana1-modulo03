package mx.unam.aragon.fes.diplo.botones;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonVideo).setOnClickListener(this);
        findViewById(R.id.buttonEvento).setOnClickListener(this);
        findViewById(R.id.buttonTemporizador).setOnClickListener(this);
        //findViewById(R.id.buttonGoogle).setOnClickListener(this);
    }


    public void btnGoogle(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String urlWeb = "http://www.google.com.mx";
        intent.setData(Uri.parse(urlWeb));
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()) {
            case R.id.buttonVideo:
                i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
                break;
            case R.id.buttonEvento:
                i = new Intent(Intent.ACTION_INSERT);
                i.setData(CalendarContract.Events.CONTENT_URI);
                startActivity(i);
                break;
            case R.id.buttonTemporizador:
                Calendar date = Calendar.getInstance();
                int h = date.get(Calendar.HOUR_OF_DAY);
                int m = date.get(Calendar.MINUTE) + 2;
                i = new Intent(AlarmClock.ACTION_SET_TIMER)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "Mensaje Alarma")
                        .putExtra(AlarmClock.EXTRA_HOUR, h)
                        .putExtra(AlarmClock.EXTRA_MINUTES, m);
                if (i.resolveActivity(getPackageManager()) !=
                        null) {
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No se puede iniciar el temporizador",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            }


        }
    }

