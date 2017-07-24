package app.com.zolospace.ui.component.register;

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
import app.com.zolospace.ui.component.Login.LoginActivity;
import app.com.zolospace.utils.RealmController;
import app.com.zolospace.utils.ResourcesUtil;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.realm.Realm;


public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    @Inject
    RegisterPresenter splashPresenter;
    @BindView(R.id.phone)
    EditText mPhoneView;
    @BindView(R.id.email)
    EditText mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.name)
    EditText mNameView;
    @BindView(R.id.refferal)
    EditText mReferralView;
    @BindView(R.id.text_login)
    TextView mTextLogin;
    @BindView(R.id.submit_button)
    Button mSubmitButton;
    @BindView(R.id.auto_fill_button)
    Button mAutoFillButton;
    private Realm realm;


    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(RegisterActivity.this);
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
        return R.layout.activity_register;
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

    @OnFocusChange(R.id.email)
    void onFocusEmailChanged(boolean hasFocus) {
        String mail = mEmailView.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            mEmailView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_black_24dp, 0);
        } else
            mEmailView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @OnFocusChange(R.id.name)
    void onFocusNameChanged(boolean hasFocus) {
        String name = mNameView.getText().toString();
        if (!TextUtils.isEmpty(name) && name.length() > 2) {
            mNameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_black_24dp, 0);
        } else
            mNameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @OnFocusChange(R.id.password)
    void onFocusPasswordChanged(boolean hasFocus) {
        String password = mPasswordView.getText().toString();
        if (!TextUtils.isEmpty(password) && password.length() > 4) {
            mPasswordView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_black_24dp, 0);
        } else
            mPasswordView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @OnFocusChange(R.id.refferal)
    void onFocusReferralChanged(boolean hasFocus) {
        String referral = mReferralView.getText().toString();
        if (!TextUtils.isEmpty(referral) && referral.length() > 4) {
            mReferralView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_black_24dp, 0);
        } else
            mReferralView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    boolean isValidated() {
        if (TextUtils.isEmpty(mPhoneView.getText().toString())) {
            mPhoneView.setError("" + getString(R.string.error_field_required));
            return false;
        }
        if (TextUtils.isEmpty(mEmailView.getText().toString())) {
            mEmailView.setError("" + getString(R.string.error_field_required));
            return false;
        }
        if (TextUtils.isEmpty(mNameView.getText().toString())) {
            mNameView.setError("" + getString(R.string.error_field_required));
            return false;
        }
        if (TextUtils.isEmpty(mPasswordView.getText().toString())) {
            mPasswordView.setError("" + getString(R.string.error_field_required));
            return false;
        }
        if (TextUtils.isEmpty(mReferralView.getText().toString())) {
            mReferralView.setError("" + getString(R.string.error_field_required));
            return false;
        }
        return true;
    }

    @OnClick(R.id.submit_button)
    void onClickCreateAccount(View view) {
        ResourcesUtil.hideKeyboard(this);

        if (isValidated()) {
            if (setDetailsInDB()) {
                Intent intent = new Intent(RegisterActivity.this, VerifyActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    boolean setDetailsInDB() {
        try {
            int position = realm.where(LoginData.class).findAll().size();
//            if (position > 0)
            {
                LoginData item = realm.where(LoginData.class)
                        .equalTo("phone", mPhoneView.getText().toString().trim())
                        .findFirst();

                if (item != null && item.getPhone().equals(mPhoneView.getText().toString().trim())) {
                    ResourcesUtil.showSnackbar(this, getString(R.string.error_number_already));
                    return false;
                } else {
                    realm.beginTransaction();

                    LoginData loginItem = realm.createObject(LoginData.class);
                    loginItem.setId(position + 1);
                    loginItem.setEmail(mEmailView.getText().toString().trim());
                    loginItem.setPassword(mPasswordView.getText().toString().trim());
                    loginItem.setPhone(mPhoneView.getText().toString().trim());
                    loginItem.setName(mNameView.getText().toString().trim());
                    loginItem.setReferral(mReferralView.getText().toString().trim());
                    realm.commitTransaction();
                    return true;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

    @OnClick(R.id.text_login)
    void onClickLogin(View view) {
        ResourcesUtil.hideKeyboard(this);

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
