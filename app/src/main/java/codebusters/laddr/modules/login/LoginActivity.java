package codebusters.laddr.modules.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import butterknife.BindView;
import codebusters.laddr.R;


@EActivity(R.layout.fragment_login)
public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_A_TAG = "LOGIN_ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.btn_login)
    void loginClicked(){
        Toast.makeText(LoginActivity.this, "yum", Toast.LENGTH_SHORT).show();

    }

}
