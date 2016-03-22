package android.example.com.savings;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

public class Settings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void setSnacks(View view) {
        Intent intent=new Intent(this, setSnacks.class);
        startActivity(intent);
    }
    public void setDrinks(View view){
        Intent intent=new Intent(this,setDrinks.class);
        startActivity(intent);
    }
    public void setEating(View view){
        Intent intent=new Intent(this, setEating.class);
        startActivity(intent);
    }

    public void setCustomize1(View view) {
        Intent intent=new Intent(this, customize1.class);
        startActivity(intent);
    }

    public void setCustomize2(View view) {
        Intent intent=new Intent(this, customize2.class);
        startActivity(intent);
    }

    public void setCustomize3(View view) {
        Intent intent=new Intent(this, customize3.class);
        startActivity(intent);
    }
}
