package app.com.zolospace.ui.component.reset;

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
import app.com.zolospace.utils.ResourcesUtil;
import butterknife.BindView;
import butterknife.OnClick;


public class ResetActivity extends BaseActivity implements ResetContract.View {

    @Inject
    ResetPresenter splashPresenter;
    @BindView(R.id.phone)
    EditText mPhoneView;
    @BindView(R.id.text_cancel)
    TextView mCancelView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.continue_button)
    Button mContinueButton;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(ResetActivity.this);
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
        return R.layout.activity_reset;
    }

    @Override
    public void initializeView(LoginItem newsItem) {
    }

    @OnClick(R.id.continue_button)
    void onClickForgotPassword(View view) {
        ResourcesUtil.hideKeyboard(this);

        Intent intent = new Intent(ResetActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.text_cancel)
    void onClickCacnel(View view) {
        ResourcesUtil.hideKeyboard(this);

        Intent intent = new Intent(ResetActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
