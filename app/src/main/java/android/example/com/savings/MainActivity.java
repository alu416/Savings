package android.example.com.savings;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class MainActivity extends ActionBarActivity {

    public int goal=1;
    public double savings=0;
    private static final String FORMAT = "%02d:%02d:%02d";
    int seconds , minutes;
    boolean snacksclick=false;
    boolean Drinksclick=false;
    boolean Mealclick=false;
    boolean custom1click=false;
    boolean custom2click=false;
    boolean custom3click=false;
    CountDownTimer snackscountdown,drinkscountdown,mealcountdown,custom1countdown,custom2countdown,custom3countdown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goal=getgoal();
        savings=getsaving();
        TextView Goalview = (TextView) findViewById(R.id.GoalView);
        Goalview.setText((int) (((double) savings / goal) * 100) + "%");
        CircleProgressBar bar=(CircleProgressBar)findViewById(R.id.custom_progressBar);
        bar.setProgress((int) (((double) savings / goal) * 100));

        Button custom1=(Button)findViewById(R.id.customize1);
        Button custom2=(Button)findViewById(R.id.customize2);
        Button custom3=(Button)findViewById(R.id.customize3);
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        custom1.setText(prefs.getString("custom1name","Custom 1"));
        custom2.setText(prefs.getString("custom2name","Custom 2"));
        custom3.setText(prefs.getString("custom3name","Custom 3"));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showsettings();
            return true;
        }
        if(id==R.id.action_show){
            showprogress(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showsettings() {
        Intent intent=new Intent(this,Settings.class);
        startActivity(intent);
    }

    public void goalActivity(View view){
        Intent intent=new Intent(this,Entergoal.class);
        startActivity(intent);
    }

    public void addsnacks(View view) {
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        savings = savings + getDouble(prefs,"snacks", 1.0);
        storesaving(savings);
        TextView Goalview = (TextView) findViewById(R.id.GoalView);
        Goalview.setText((int)(((double)savings / goal) * 100) + "%");
        CircleProgressBar bar=(CircleProgressBar)findViewById(R.id.custom_progressBar);
        bar.setProgress((int) (((double) savings / goal) * 100));

    }
    public void snackstimer(View view){

        if(snacksclick==true){
            new AlertDialog.Builder(this)
                    .setTitle("Reset Timer")
                    .setMessage("Are you sure you want to reset timer?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final TextView snackstimer=(TextView)findViewById(R.id.TimerSnacks);
                            snackscountdown.cancel();
                            snackstimer.setText("00:00:00");
                            snacksclick=false;

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }

        else{
            SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
            final TextView snackstimer=(TextView)findViewById(R.id.TimerSnacks);
            snacksclick=true;
            snackscountdown =new CountDownTimer(prefs.getLong("snackstimer",3600000), 1000){ // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    snackstimer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    snackstimer.setText("00:00:00");
                    addsnacks(null);
                    snacksclick=false;
                }
            }.start();


        }}

    public void addDrinks(View view) {
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        savings = savings + getDouble(prefs,"Drinks", 5.0);
        storesaving(savings);
        TextView Goalview = (TextView) findViewById(R.id.GoalView);
        Goalview.setText((int) (((double) savings / goal) * 100) + "%");
        CircleProgressBar bar=(CircleProgressBar)findViewById(R.id.custom_progressBar);
        bar.setProgress((int) (((double) savings / goal) * 100));
    }
    public void Drinkstimer(View view){
        if(Drinksclick==true){
            new AlertDialog.Builder(this)
                    .setTitle("Reset Timer")
                    .setMessage("Are you sure you want to reset timer?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final TextView drinkstimer=(TextView)findViewById(R.id.TimerDrinks);
                            drinkscountdown.cancel();
                            drinkstimer.setText("00:00:00");
                            Drinksclick=false;

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        else{
            SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
            final TextView Drinkstimer=(TextView)findViewById(R.id.TimerDrinks);
            Drinksclick=true;
            drinkscountdown=new CountDownTimer(prefs.getLong("Drinkstimer",3600000), 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    Drinkstimer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    Drinkstimer.setText("Done!");
                    addDrinks(null);
                    Drinksclick=false;
                }
            }.start();


        }}
    public void addmeal(View view) {
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        savings = savings + getDouble(prefs,"Meal", 10.0);
        storesaving(savings);
        TextView Goalview = (TextView) findViewById(R.id.GoalView);
        Goalview.setText((int)(((double)savings / goal)*100) + "%");
        CircleProgressBar bar=(CircleProgressBar)findViewById(R.id.custom_progressBar);
        bar.setProgress((int) (((double) savings / goal) * 100));

    }
    public void Mealtimer(View view){
        if(Mealclick==true){
            new AlertDialog.Builder(this)
                    .setTitle("Reset Timer")
                    .setMessage("Are you sure you want to reset timer?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final TextView mealtimer=(TextView)findViewById(R.id.TimerEating);
                            mealcountdown.cancel();
                            mealtimer.setText("00:00:00");
                            Mealclick=false;

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        else{
            SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
            final TextView Mealtimer=(TextView)findViewById(R.id.TimerEating);
            Mealclick=true;
            mealcountdown= new CountDownTimer(prefs.getLong("Mealtimer",3600000), 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    Mealtimer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    Mealtimer.setText("Done!");
                    addmeal(null);
                    Mealclick=false;
                }
            }.start();


        }}
    public void custom1(View view) {
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        savings = savings + getDouble(prefs,"custom1", 10.0);
        storesaving(savings);
        TextView Goalview = (TextView) findViewById(R.id.GoalView);
        Goalview.setText((int)(((double)savings / goal)*100) + "%");
        CircleProgressBar bar=(CircleProgressBar)findViewById(R.id.custom_progressBar);
        bar.setProgress((int) (((double) savings / goal) * 100));
    }
    public void custom1timer(View view){
        if(custom1click==true){
            new AlertDialog.Builder(this)
                    .setTitle("Reset Timer")
                    .setMessage("Are you sure you want to reset timer?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final TextView custom1timer=(TextView)findViewById(R.id.Timercustom1);
                            custom1countdown.cancel();
                            custom1timer.setText("00:00:00");
                            custom1click=false;

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

        else{
            SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
            final TextView custom1timer=(TextView)findViewById(R.id.Timercustom1);
            custom1click=true;
            custom1countdown=new CountDownTimer(prefs.getLong("custom1timer",3600000), 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    custom1timer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    custom1timer.setText("Done!");
                    custom1(null);
                    custom1click=false;
                }
            }.start();


        }}
    public void custom2(View view) {
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        savings = savings + getDouble(prefs,"custom2", 10.0);
        storesaving(savings);
        TextView Goalview = (TextView) findViewById(R.id.GoalView);
        Goalview.setText((int)(((double)savings / goal)*100) + "%");
        CircleProgressBar bar=(CircleProgressBar)findViewById(R.id.custom_progressBar);
        bar.setProgress((int) (((double) savings / goal) * 100));
    }
    public void custom2timer(View view){
        if(custom2click==true){

            new AlertDialog.Builder(this)
                    .setTitle("Reset Timer")
                    .setMessage("Are you sure you want to reset timer?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final TextView custom2timer=(TextView)findViewById(R.id.Timercustom2);
                            custom2countdown.cancel();
                            custom2timer.setText("00:00:00");
                            custom2click=false;

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        else{
            SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
            final TextView custom2timer=(TextView)findViewById(R.id.Timercustom2);
            custom2click=true;
            custom2countdown=new CountDownTimer(prefs.getLong("custom2timer",3600000), 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    custom2timer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    custom2timer.setText("Done!");
                    custom2(null);
                    custom2click=false;
                }
            }.start();


        }}
    public void custom3(View view) {
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        savings = savings + getDouble(prefs,"custom3", 10.0);
        storesaving(savings);
        TextView Goalview = (TextView) findViewById(R.id.GoalView);
        Goalview.setText((int)(((double)savings / goal)*100) + "%");
        CircleProgressBar bar=(CircleProgressBar)findViewById(R.id.custom_progressBar);
        bar.setProgress((int) (((double) savings / goal) * 100));
    }
    public void custom3timer(View view){
        if(custom3click==true){
            new AlertDialog.Builder(this)
                    .setTitle("Reset Timer")
                    .setMessage("Are you sure you want to reset timer?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final TextView custom3timer=(TextView)findViewById(R.id.Timercustom3);
                            custom3countdown.cancel();
                            custom3timer.setText("00:00:00");
                            custom3click=false;

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

        else{
            SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
            final TextView custom3timer=(TextView)findViewById(R.id.Timercustom3);
            custom3click=true;
            custom3countdown=new CountDownTimer(prefs.getLong("custom3timer",3600000), 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    custom3timer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    custom3timer.setText("Done!");
                    custom3(null);
                    custom3click=false;
                }
            }.start();


        }}

    public void showprogress(View view){
        storesaving(savings);
        Intent showprogress=new Intent(this,ShowProgress.class);
        startActivity(showprogress);
    }
    public void storegoal(int s){

        SharedPreferences prefs=getSharedPreferences("goal",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt("goal",s);
        editor.commit();



    }
    public int getgoal(){

        SharedPreferences prefs=getSharedPreferences("goal", MODE_PRIVATE);
        int s=prefs.getInt("goal", 1);
        return s;
    }
    public void storesaving(double s){
        SharedPreferences prefs=getSharedPreferences("saving",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        //editor.putInt("saving",s);
        putDouble(editor,"saving",s);
        editor.commit();
    }
    public double getsaving(){
        SharedPreferences prefs=getSharedPreferences("saving",MODE_PRIVATE);
        double s=getDouble(prefs, "saving", 0.0);
        return s;
    }
    SharedPreferences.Editor putDouble(final SharedPreferences.Editor edit, final String key, final double value) {
        return edit.putLong(key, Double.doubleToRawLongBits(value));
    }

    double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        if ( !prefs.contains(key))
            return defaultValue;

        return Double.longBitsToDouble(prefs.getLong(key, 0));
    }
    public double limitdecimal(double i){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(i));
    }

}
