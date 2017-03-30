package practicaltest01.eim.systems.cs.pub.ro.practicaltest01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PracticalTest01MainActivity extends AppCompatActivity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private EditText leftEditText = null;
    private EditText rightEditText = null;
    private Button leftButton = null;
    private Button rightButton = null;
    private Button stergeSt = null;
    private Button stergeDr = null;
    private Button trece = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int leftNumberOfClicks = Integer.parseInt(leftEditText.getText().toString());
            int rightNumberOfClicks = Integer.parseInt(rightEditText.getText().toString());
            switch(view.getId()) {
                case R.id.left_button:
                    leftNumberOfClicks++;
                    leftEditText.setText(String.valueOf(leftNumberOfClicks));
                    break;
                case R.id.right_button:
                    rightNumberOfClicks++;
                    rightEditText.setText(String.valueOf(rightNumberOfClicks));
                    break;
                case R.id.sterge_St:
                    leftEditText.setText(String.valueOf(0));
                    break;
                case R.id.sterge_Dr:
                    rightEditText.setText(String.valueOf(0));
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), newactivity.class);
                    int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) + Integer.parseInt(rightEditText.getText().toString());
                    intent.putExtra("numberOfClicks", numberOfClicks);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        leftEditText = (EditText)findViewById(R.id.left_edit_text);
        rightEditText = (EditText)findViewById(R.id.right_edit_text);
        SharedPreferences settings;
        settings = getSharedPreferences("TWO_INT_SAVING", MODE_PRIVATE);


        leftEditText.setText(String.valueOf(settings.getInt("FIRST_INT", 0)));
        rightEditText.setText(String.valueOf(settings.getInt("SECOND_INT", 0)));

        leftButton = (Button)findViewById(R.id.left_button);
        rightButton = (Button)findViewById(R.id.right_button);
        stergeDr = (Button)findViewById(R.id.sterge_Dr);
        stergeSt = (Button)findViewById(R.id.sterge_St);
        trece = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        leftButton.setOnClickListener(buttonClickListener);
        rightButton.setOnClickListener(buttonClickListener);
        stergeDr.setOnClickListener(buttonClickListener);
        stergeSt.setOnClickListener(buttonClickListener);
        trece.setOnClickListener(buttonClickListener);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //pastrez vechile valori
        SharedPreferences settings;
        settings = getSharedPreferences("TWO_INT_SAVING", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        int pr = Integer.parseInt(leftEditText.getText().toString());
        int doi = Integer.parseInt(rightEditText.getText().toString());
        editor.putInt("FIRST_INT", pr);
        editor.putInt("SECOND_INT", doi);
        editor.commit();
        //////
    }
    @Override
    public  void onPause() {
        super.onPause();
        ///la pause resetez valorile
        //leftEditText.setText(String.valueOf(0));
        //rightEditText.setText(String.valueOf(0));
        ///////

    }
}
