package app.com.zolospace.ui.component.register;

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
import app.com.zolospace.ui.component.forgot.ForgotActivity;
import app.com.zolospace.utils.ResourcesUtil;
import butterknife.BindView;
import butterknife.OnClick;


public class VerifyActivity extends BaseActivity implements VerifyContract.View {

    @Inject
    VerifyPresenter splashPresenter;
    @BindView(R.id.txt_pin_entry)
    EditText mOTPView;
    @BindView(R.id.text_back)
    TextView mTextBack;
    @BindView(R.id.resend)
    TextView mTextResend;
    @BindView(R.id.verify_button)
    Button mVerifyButton;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(VerifyActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ResourcesUtil.showSnackbar(this, getString(R.string.validation_for_now));
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_verification;
    }

    @Override
    public void initializeView(LoginItem newsItem) {


    }

    @OnClick(R.id.verify_button)
    void onClickVerify(View view) {
        if (mOTPView.getText().toString().equals("123456")) {
            Intent intent = new Intent(VerifyActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else //
            ResourcesUtil.showSnackbar(this, getString(R.string.validation_for_now));
    }

    @OnClick(R.id.text_back)
    void onClickBack(View view) {
        Intent intent = new Intent(VerifyActivity.this, ForgotActivity.class);
        startActivity(intent);
        finish();
    }


}
