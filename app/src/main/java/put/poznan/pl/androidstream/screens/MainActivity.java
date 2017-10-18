package put.poznan.pl.androidstream.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.screens.meetup.MeetupFragment;
import put.poznan.pl.androidstream.screens.opengl.OpenGLActivity;
import put.poznan.pl.androidstream.screens.opengl.OpenGLFragment;
import put.poznan.pl.androidstream.screens.stream.StreamFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        showScreenById(R.id.action_opengl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        showScreenById(id);

        return super.onOptionsItemSelected(item);
    }

    private void showScreenById(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.action_meetup:
                fragment = new MeetupFragment();
                break;
            case R.id.action_stream:
                fragment = new StreamFragment();
                break;
            case R.id.action_opengl:
//                fragment = new OpenGLFragment();
                Intent intent = new Intent(this, OpenGLActivity.class);
                startActivity(intent);
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();
        }
    }
}
