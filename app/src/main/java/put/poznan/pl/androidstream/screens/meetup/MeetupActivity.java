package put.poznan.pl.androidstream.screens.meetup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import put.poznan.pl.androidstream.R;

public class MeetupActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.meetup_content, new MeetupFragment())
                .commit();
    }
}
