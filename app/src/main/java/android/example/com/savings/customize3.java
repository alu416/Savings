package android.example.com.savings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class customize3 extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize3);
        TextView customname=(TextView)findViewById(R.id.custom3name);
        TextView customvalue=(TextView)findViewById(R.id.custom3value);
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        customname.setText("The name for this custom button currently is: " + prefs.getString("custom3name", "Custom 3"));
        customvalue.setText("The value for this custom button current is: $" + getDouble(prefs, "custom3", 10));
        TextView custom3time=(TextView)findViewById(R.id.custom3timershow);
        custom3time.setText("custom button 2 timer: "+(int)(prefs.getLong("custom3timer",3600000))/3600000+" hours");
    }
    public void putcustom3(View view) {
        EditText goalin = (EditText) findViewById(R.id.putvalue3);
        Double j=Double.valueOf(goalin.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        j=limitdecimal(j);
        putDouble(editor, "custom3", j);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void putname(View view) {
        EditText namein = (EditText) findViewById(R.id.custom3);
        String name=namein.getText().toString();
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("custom3name", name);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void putcustom3timer(View view) {
        EditText custom3timer=(EditText)findViewById(R.id.putcustom3timer);
        int hours=Integer.valueOf(custom3timer.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        long hourstime=hours*3600000;
        editor.putLong("custom3timer", hourstime);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
