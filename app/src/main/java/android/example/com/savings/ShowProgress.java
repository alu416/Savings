package android.example.com.savings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowProgress extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progress);
        TextView FractionView=(TextView)findViewById(R.id.FractionView);
        goal=getgoal();
        savings=getsaving();
        savings=limitdecimal(savings);
        FractionView.setText(savings+"/"+goal);


    }

    public void clearsavings(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete savings")
                .setMessage("Are you sure you want to delete this savings?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        storesaving(0);
                        TextView FractionView=(TextView)findViewById(R.id.FractionView);
                        goal=getgoal();
                        savings=getsaving();
                        FractionView.setText(savings + "/" + goal);
                        backtomain();
                        // continue with delete
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
    public void backtomain(){
        Intent backtomain=new Intent(this, MainActivity.class);
        startActivity(backtomain);

    }
}
