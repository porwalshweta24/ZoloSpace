package app.com.zolospace.ui.component.Login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import app.com.zolospace.App;
import app.com.zolospace.R;
import app.com.zolospace.data.remote.dto.LoginData;
import app.com.zolospace.data.remote.dto.LoginItem;
import app.com.zolospace.ui.base.BaseActivity;
import app.com.zolospace.ui.component.forgot.ForgotActivity;
import app.com.zolospace.ui.component.profile.ProfileActivity;
import app.com.zolospace.ui.component.register.RegisterActivity;
import app.com.zolospace.utils.RealmController;
import app.com.zolospace.utils.ResourcesUtil;
import app.com.zolospace.utils.ZoloPreference;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.realm.Realm;


public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginPresenter splashPresenter;
    @BindView(R.id.phone)
    EditText mPhoneView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.text_forgot_password)
    TextView mForgotPassword;
    @BindView(R.id.sign_in_button)
    Button mSignInButton;
    @BindView(R.id.create_account_button)
    Button mCreateAccountButton;
    private Realm realm;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(LoginActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //get realm instance
        this.realm = RealmController.with(this).getRealm();
        // refresh the realm instance
        RealmController.with(this).refresh();
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initializeView(LoginItem newsItem) {

    }

    @OnFocusChange(R.id.phone)
    void onFocusPhoneChanged(boolean hasFocus) {
        String phone = mPhoneView.getText().toString();
        if (!TextUtils.isEmpty(phone) && phone.length() >= 9) {
            mPhoneView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_black_24dp, 0);
        } else
            mPhoneView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @OnFocusChange(R.id.password)
    void onFocusPasswordChanged(boolean hasFocus) {
        String password = mPasswordView.getText().toString();
        if (!TextUtils.isEmpty(password) && password.length() >= 6) {
            mPasswordView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_black_24dp, 0);
        } else
            mPasswordView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    boolean isValidate() {
        if (TextUtils.isEmpty(mPhoneView.getText().toString())) {
            mPhoneView.setError("" + getString(R.string.error_field_required));
            return false;
        }
        if (TextUtils.isEmpty(mPasswordView.getText().toString())) {
            mPasswordView.setError("" + getString(R.string.error_field_required));
            return false;
        }
        return true;
    }

    @OnClick(R.id.create_account_button)
    void onClickCreateAccount(View view) {
        ResourcesUtil.hideKeyboard(this);

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.sign_in_button)
    void onClickLogIn(View view) {
        ResourcesUtil.hideKeyboard(this);
        if (isValidate()) {
            try {
                int position = realm.where(LoginData.class).findAll().size();
                if (position > 0) {
                    try {
                        LoginData item = RealmController.getInstance().getDetailsPhone(mPhoneView.getText().toString().trim());
                        if (item != null)
                            if (item.getPassword().equals(mPasswordView.getText().toString().trim())) {
                                ResourcesUtil.showSnackbar(LoginActivity.this, getString(R.string.success_login));

                                ZoloPreference.getInstance().saveIntValue(ZoloPreference.USERID, item.getId());
                                ZoloPreference.getInstance().saveStringValue(ZoloPreference.USEREMAIL, "" + item.getEmail());
                                ZoloPreference.getInstance().saveStringValue(ZoloPreference.USERNAME, "" + item.getName());
                                ZoloPreference.getInstance().saveStringValue(ZoloPreference.USERENUMMBER, "" + item.getPhone());
                                ZoloPreference.getInstance().saveStringValue(ZoloPreference.USEREPASSWORD, "" + item.getPassword());
                                ZoloPreference.getInstance().saveStringValue(ZoloPreference.USEREReferral, "" + item.getReferral());

                                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();

                            } else
                                ResourcesUtil.showSnackbar(LoginActivity.this, getString(R.string.error_invalid_credentials));

                    } catch (Exception e) {
                        ResourcesUtil.showSnackbar(LoginActivity.this, getString(R.string.error_no_data));
                        e.printStackTrace();
                    }
                } else {
                    ResourcesUtil.showSnackbar(LoginActivity.this, getString(R.string.error_invalid_credentials));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.text_forgot_password)
    void onClickForgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
        startActivity(intent);
        finish();
    }
}
