package android.example.com.savings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class setEating extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_eating);
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        TextView MealView=(TextView)findViewById(R.id.Mealvalue);
        MealView.setText("The current value for Meals:" + " $" + getDouble(prefs, "Meal", 10));
        TextView Mealtime=(TextView)findViewById(R.id.mealtimershow);
        Mealtime.setText("Eating out timer: "+(int)(prefs.getLong("Mealtimer",3600000))/3600000+" hours");
    }
    public void putMeal(View view) {
        EditText goalin = (EditText) findViewById(R.id.edit_message);
        Double j=Double.valueOf(goalin.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        j=limitdecimal(j);
        putDouble(editor, "Meal", j);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void putmealtimer(View view) {
        EditText mealtimer=(EditText)findViewById(R.id.putmealtimer);
        int hours=Integer.valueOf(mealtimer.getText().toString());
        SharedPreferences prefs=getSharedPreferences("saving", MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        long hourstime=hours*3600000;
        editor.putLong("Mealtimer", hourstime);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
