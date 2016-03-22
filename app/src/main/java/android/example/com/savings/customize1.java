package android.example.com.savings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class customize1 extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize1);
        TextView customname=(TextView)findViewById(R.id.custom1name);
        TextView customvalue=(TextView)findViewById(R.id.custom1value);
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        customname.setText("The name for this custom button currently is: "+prefs.getString("custom1name","Custom 1"));
        customvalue.setText("The value for this custom button current is: $"+getDouble(prefs, "custom1", 10));
        TextView custom1time=(TextView)findViewById(R.id.custom1timershow);
        custom1time.setText("custom button 1 timer: "+(int)(prefs.getLong("custom1timer",3600000))/3600000+" hours");
    }

    public void putcustom1(View view) {
        EditText goalin = (EditText) findViewById(R.id.putvalue1);
        Double j=Double.valueOf(goalin.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        j=limitdecimal(j);
        putDouble(editor, "custom1", j);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void putname(View view) {
        EditText namein = (EditText) findViewById(R.id.custom1);
        String name=namein.getText().toString();
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("custom1name", name);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void putcustom1timer(View view) {
        EditText custom1timer=(EditText)findViewById(R.id.putcustom1timer);
        int hours=Integer.valueOf(custom1timer.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        long hourstime=hours*3600000;
        editor.putLong("custom1timer", hourstime);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
