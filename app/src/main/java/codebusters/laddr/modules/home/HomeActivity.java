package codebusters.laddr.modules.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import butterknife.BindView;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.modules.forums.ForumsFragment_;
import codebusters.laddr.modules.postings.PostingsFragment_;
import codebusters.laddr.modules.profile.ProfileFragment_;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    public final String TAG = "HomeActivity";

    @BindView(R.id.toolbar)
    private Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    private Fragment fr;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private Bundle savedInstanceState;
    private static GlobalState globalState;

    @AfterViews
    void setVars() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        globalState = (GlobalState) getApplication();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    //Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    Fragment fr = new HomeFragment_();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frlt_fragment_container_home, fr);
                } else if (tabId == R.id.tab_profile){
                    fr = new ProfileFragment_();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frlt_fragment_container_home, fr);
                    ft.addToBackStack(null);
                    ft.commit();
                } else if (tabId == R.id.tab_forums){
                    fr = new ForumsFragment_();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frlt_fragment_container_home, fr);
                    ft.addToBackStack(null);
                    ft.commit();
                } else if (tabId == R.id.tab_postings){
                    fr = new PostingsFragment_();
                    fm = getFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.frlt_fragment_container_home, fr);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                } else if (tabId == R.id.tab_profile){
                } else if (tabId == R.id.tab_forums){
                } else if (tabId == R.id.tab_postings){
                }
            }
        });

        Fragment fr = new HomeFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frlt_fragment_container_home, fr);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            new AlertDialog.Builder(this)
                    .setIcon(new IconicsDrawable(this)
                            .icon(FontAwesome.Icon.faw_exclamation_triangle)
                            .color(Color.RED)
                            .sizeDp(24))
                    .setTitle("Exit?")
                    .setMessage("Are you sure you want to exit right now?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }
}
