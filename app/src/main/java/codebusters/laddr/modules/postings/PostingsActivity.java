package codebusters.laddr.modules.postings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import butterknife.ButterKnife;
import codebusters.laddr.R;
import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.Posting;
import codebusters.laddr.modules.home.HomeActivity_;
import codebusters.laddr.utility.DividerItemDecoration;
import codebusters.laddr.utility.GetAllPostingsTask;

public class PostingsActivity extends AppCompatActivity {

    private static GlobalState globalState;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postings);
        ButterKnife.bind(this);

        globalState = (GlobalState) this.getApplication();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Postings");
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));

        final IProfile profile = new ProfileDrawerItem().withName("Full Name").withEmail("Email").withIcon(R.drawable.profile);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile
                )
                .build();

        new DrawerBuilder().withActivity(this).build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
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
                        switch (position) {
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
                .build();

        result.setSelection(4);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {

            if (globalState.getToken() != null) {
                //get all the postings
                final ArrayList<Posting> postings = new GetAllPostingsTask(this).execute().get();
                mAdapter = new PostingsAdapter(postings);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);

                //Add a listener to each list object
                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Posting posting = postings.get(position);
                        Toast.makeText(getApplicationContext(), posting.getLocation()+" is selected!", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
