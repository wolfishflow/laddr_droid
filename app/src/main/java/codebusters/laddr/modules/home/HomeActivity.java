package codebusters.laddr.modules.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import butterknife.BindView;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.modules.postings.PostingsActivity;
import codebusters.laddr.modules.postings.PostingsFragment;
import codebusters.laddr.modules.postings.PostingsFragment_;
import codebusters.laddr.modules.profile.ProfileFragment_;

@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    public final String TAG = "HomeActivity";
    private static GlobalState globalState;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    Fragment fr;
    FragmentManager fm;
    FragmentTransaction ft;

    Bundle savedInstanceState;

    @AfterViews
    void setVars() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        globalState = (GlobalState) getApplication();

        final IProfile profile = new ProfileDrawerItem()
                .withName(globalState.getUserValue().getFirstName() + " " + globalState.getUserValue().getLastName())
                .withEmail(globalState.getUserValue().getEmail())
                .withIcon(R.drawable.profile);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile
//                        ,
//                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
//                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(getActivity(), GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(1),
//                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1).withSetSelected(true),
                        new PrimaryDrawerItem().withName("Profile").withIcon(FontAwesome.Icon.faw_user).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Forum").withIcon(FontAwesome.Icon.faw_commenting),
                        new PrimaryDrawerItem().withName("Postings").withIcon(FontAwesome.Icon.faw_sticky_note),
                        new SectionDrawerItem().withName("Sub-Menu"),
                        new SecondaryDrawerItem().withName("Settings").withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName("Sign Out").withIcon(FontAwesome.Icon.faw_sign_out)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                                fr = new ProfileFragment_();
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.home_fragment_container, fr);
                                ft.addToBackStack(null);
                                ft.commit();
                                break;
                            case 3:
                                Toast.makeText(HomeActivity.this, "Forum", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                //Toast.makeText(getActivity(), "Postings", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(HomeActivity.this, PostingsActivity.class);
                                //startActivity(intent);
                                Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                                fr = new PostingsFragment_();
                                fm = getFragmentManager();
                                ft = fm.beginTransaction();
                                ft.replace(R.id.home_fragment_container, fr);
                                ft.addToBackStack(null);
                                ft.commit();
                                break;
                            case 5:
                                break;
                            case 6:
                                Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                                break;
                            case 7:
                                Toast.makeText(HomeActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;

                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        Fragment fr = new HomeFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fragment_container, fr);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.savedInstanceState = savedInstanceState;

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
}
