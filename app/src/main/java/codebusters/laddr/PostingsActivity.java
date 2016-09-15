package codebusters.laddr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import codebusters.laddr.data.GlobalState;
import codebusters.laddr.data.Posting;
import codebusters.laddr.utility.GetAllPostingsTask;
import codebusters.laddr.utility.LoginTask;

public class PostingsActivity extends AppCompatActivity {

    private static GlobalState globalState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postings);

        //get globalState
        globalState = (GlobalState) this.getApplication();

        try {
            //log in
            new LoginTask(this).execute("DatBoi", "oshitwhaddup").get();

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
