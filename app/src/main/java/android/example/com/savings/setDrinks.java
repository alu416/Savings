package android.example.com.savings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class setDrinks extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_drinks);
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        TextView DrinksView=(TextView)findViewById(R.id.Drinksvalue);
        DrinksView.setText("The current value for Drinks:" + " $" + getDouble(prefs, "Drinks", 5));
        TextView Drinkstime=(TextView)findViewById(R.id.Drinkstimershow);
        Drinkstime.setText("Drinks timer: "+(int)(prefs.getLong("Drinkstimer",3600000))/3600000+" hours");
    }

    public void putDrinks(View view) {
        EditText goalin = (EditText) findViewById(R.id.edit_message);
        Double j=Double.valueOf(goalin.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        j=limitdecimal(j);
        putDouble(editor, "Drinks", j);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void putDrinkstimer(View view) {
        EditText Drinkstimer=(EditText)findViewById(R.id.putDrinkstimer);
        int hours=Integer.valueOf(Drinkstimer.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        long hourstime=hours*3600000;
        editor.putLong("Drinkstimer", hourstime);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
