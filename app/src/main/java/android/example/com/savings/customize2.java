package android.example.com.savings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class customize2 extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize2);
        TextView customname=(TextView)findViewById(R.id.custom2name);
        TextView customvalue=(TextView)findViewById(R.id.custom2value);
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        customname.setText("The name for this custom button currently is: " + prefs.getString("custom2name", "Custom 2"));
        customvalue.setText("The value for this custom button current is: $" + getDouble(prefs, "custom2", 10));
        TextView custom2time=(TextView)findViewById(R.id.custom2timershow);
        custom2time.setText("custom button 2 timer: "+(int)(prefs.getLong("custom2timer",3600000))/3600000+" hours");
    }
    public void putcustom2(View view) {
        EditText goalin = (EditText) findViewById(R.id.putvalue2);
        Double j=Double.valueOf(goalin.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        j=limitdecimal(j);
        putDouble(editor, "custom2", j);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void putname(View view) {
        EditText namein = (EditText) findViewById(R.id.custom2);
        String name=namein.getText().toString();
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("custom2name", name);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void putcustom2timer(View view) {
        EditText custom2timer=(EditText)findViewById(R.id.putcustom2timer);
        int hours=Integer.valueOf(custom2timer.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        long hourstime=hours*3600000;
        editor.putLong("custom2timer", hourstime);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
