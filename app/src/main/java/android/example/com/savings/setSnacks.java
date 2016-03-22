package android.example.com.savings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class setSnacks extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_snacks);
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        TextView snacksView=(TextView)findViewById(R.id.snacksvalue);
        snacksView.setText("The current value for snacks:"+ " $"+getDouble(prefs, "snacks", 1));
        TextView snackstime=(TextView)findViewById(R.id.snackstimershow);
        snackstime.setText("Snacks timer: "+(int)(prefs.getLong("snackstimer",3600000))/3600000+" hours");
    }


    public void putSnacks(View view) {
        EditText goalin = (EditText) findViewById(R.id.edit_message);
        Double j=Double.valueOf(goalin.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        j=limitdecimal(j);
        putDouble(editor,"snacks", j);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void putsnackstimer(View view) {
        EditText snackstimer=(EditText)findViewById(R.id.putsnackstimer);
        int hours=Integer.valueOf(snackstimer.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        long hourstime=hours*3600000;
        editor.putLong("snackstimer", hourstime);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
