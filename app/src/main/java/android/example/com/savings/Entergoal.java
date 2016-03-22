package android.example.com.savings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class Entergoal extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entergoal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void setgoal(View view) {
        Intent backtomain=new Intent(this, MainActivity.class);
        EditText goalin = (EditText) findViewById(R.id.edit_message);
        int j=Integer.valueOf(goalin.getText().toString());
        storegoal(j);
        //goal=j;
        startActivity(backtomain);


    }


}
