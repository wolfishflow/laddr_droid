package codebusters.laddr.modules.postings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
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

import java.util.ArrayList;

import butterknife.BindView;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.Posting;
import codebusters.laddr.modules.home.HomeActivity_;
import codebusters.laddr.utility.GetAllPostingsTask;
import codebusters.laddr.utility.LoginTask;

public class PostingsActivity extends AppCompatActivity {

    private static GlobalState globalState;

    @BindView(R.id.toolbar_home)
    Toolbar myToolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postings);

        //get globalState
        globalState = (GlobalState) this.getApplication();


        myToolbar = (Toolbar) this.findViewById(R.id.toolbar_home);

        myToolbar.setTitle("Postings");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        //getContext().getColor(R.color.md_white_1000) //API is 19+ but this call needs 23+

        final IProfile profile = new ProfileDrawerItem().withName("Full Name").withEmail("Email").withIcon(R.drawable.profile);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();

        new DrawerBuilder().withActivity(this).build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Profile").withIcon(FontAwesome.Icon.faw_user).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Forum").withIcon(FontAwesome.Icon.faw_commenting).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Postings").withIcon(FontAwesome.Icon.faw_sticky_note).withIdentifier(4),
                        new SectionDrawerItem().withName("Sub-Menu"),
                        new SecondaryDrawerItem().withName("Settings").withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName("Sign Out").withIcon(FontAwesome.Icon.faw_sign_out)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){
                            case 1:
                                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity_.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(getApplicationContext(), "Forum", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(getApplicationContext(), "Postings", Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                break;
                            case 6:
                                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                                break;
                            case 7:
                                Toast.makeText(getApplicationContext(), "Sign Out", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        result.setSelection(4);

        try {
            //log in
            //new LoginTask(this).execute("DatBoi", "oshitwhaddup").get();

            if (globalState.getToken() != null) {
                //get all the postings
                ArrayList<Posting> postings = new GetAllPostingsTask(this).execute().get();

                //get a reference to the ListView and fill it with our postings
                ListView postingsList = (ListView) findViewById(R.id.postings);
                postingsList.setAdapter(new ArrayAdapter<Posting>(this, android.R.layout.simple_list_item_1, postings));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
