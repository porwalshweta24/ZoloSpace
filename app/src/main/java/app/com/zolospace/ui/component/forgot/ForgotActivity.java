package app.com.zolospace.ui.component.forgot;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import app.com.zolospace.App;
import app.com.zolospace.R;
import app.com.zolospace.data.remote.dto.LoginItem;
import app.com.zolospace.ui.base.BaseActivity;
import app.com.zolospace.ui.component.Login.LoginActivity;
import app.com.zolospace.ui.component.reset.ResetActivity;
import app.com.zolospace.utils.ResourcesUtil;
import butterknife.BindView;
import butterknife.OnClick;


public class ForgotActivity extends BaseActivity implements ForgotContract.View {

    @Inject
    ForgotPresenter splashPresenter;
    @BindView(R.id.phone)
    EditText mPhoneView;
    @BindView(R.id.text_login)
    TextView mTextLogin;
    @BindView(R.id.submit_button)
    Button mSubmitButton;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(ForgotActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgot;
    }


    @Override
    public void initializeView(LoginItem newsItem) {

    }

    @OnClick(R.id.text_login)
    void onClickLogin(View view) {
        ResourcesUtil.hideKeyboard(this);

        Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.submit_button)
    void onClickSubmit(View view) {
        ResourcesUtil.hideKeyboard(this);

        Intent intent = new Intent(ForgotActivity.this, ResetActivity.class);
        startActivity(intent);
        finish();
    }

}
