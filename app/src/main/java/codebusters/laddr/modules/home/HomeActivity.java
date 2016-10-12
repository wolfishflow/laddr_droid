package codebusters.laddr.modules.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import org.androidannotations.annotations.EActivity;

import codebusters.laddr.R;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    public final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isTaskRoot())
            Log.d(TAG, "onCreate: Is Task Root");

        Fragment fr = new HomeFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fragment_container, fr);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();


        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            //super.onBackPressed();
            //additional code
            new AlertDialog.Builder(this)
                    .setIcon(new IconicsDrawable(this)
                            .icon(FontAwesome.Icon.faw_exclamation_triangle)
                            .color(Color.RED)
                            .sizeDp(24))
                    //.setIcon(FontAwesome.Icon.faw_exclamation_triangle)
                    .setTitle("Exit?")
                    .setMessage("Are you sure you want to exit right now?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            getFragmentManager().popBackStack();
        }


    }
}
