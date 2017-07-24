package app.com.zolospace.ui.component.profile;

import android.content.DialogInterface;
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
import app.com.zolospace.utils.ZoloPreference;
import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;


public class ProfileActivity extends BaseActivity implements ProfileContract.View {

    @Inject
    ProfilePresenter splashPresenter;
    @BindView(R.id.phone)
    EditText mPhoneView;
    @BindView(R.id.email)
    EditText mEmailView;
    @BindView(R.id.name)
    EditText mNameView;
    @BindView(R.id.password)
    EditText mOldPasswordView;
    @BindView(R.id.newpassword)
    EditText mNewPasswordView;
    @BindView(R.id.refferal)
    EditText mReferralView;
    @BindView(R.id.update_button)
    Button mUpdateButton;
    @BindView(R.id.text_logout)
    TextView mTextLogout;
    private Realm realm;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(ProfileActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //get realm instance
        this.realm = RealmController.with(this).getRealm();
        // refresh the realm instance
        RealmController.with(this).refresh();
        setData();

    }

    void setData() {

        {
            mPhoneView.setText(ZoloPreference.getInstance().getStringValue(ZoloPreference.USERENUMMBER));
            mEmailView.setText(ZoloPreference.getInstance().getStringValue(ZoloPreference.USEREMAIL));
            mNameView.setText(ZoloPreference.getInstance().getStringValue(ZoloPreference.USERNAME));
            mReferralView.setText(ZoloPreference.getInstance().getStringValue(ZoloPreference.USEREReferral));

        }
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public void initializeView(LoginItem newsItem) {
    }

    boolean isValidate() {
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
        if (!TextUtils.isEmpty(mOldPasswordView.getText().toString().trim()) &&
                !TextUtils.isEmpty((mNewPasswordView.getText().toString()))) {

            if (TextUtils.isEmpty(mOldPasswordView.getText().toString())) {
                mOldPasswordView.setError("" + getString(R.string.error_field_required));
                return false;
            }
            if (!mOldPasswordView.getText().toString().trim().
                    equals(ZoloPreference.getInstance().getStringValue(ZoloPreference.USEREPASSWORD))) {
                mOldPasswordView.setError("" + getString(R.string.error_pasword));
                return false;
            }
            if (TextUtils.isEmpty(mNewPasswordView.getText().toString())) {
                mNewPasswordView.setError("" + getString(R.string.error_field_required));
                return false;
            }
            if (mOldPasswordView.getText().toString().trim().equals(mNewPasswordView.getText().toString().trim())) {
                mNewPasswordView.setError("" + getString(R.string.error_password_same));
                return false;
            }

        }

        return true;
    }

    @OnClick(R.id.update_button)
    void onClickUpdate(View view) {
        ResourcesUtil.hideKeyboard(this);
        if (isValidate()) {

            LoginData kanjoComp = realm.where(LoginData.class)
                    .equalTo("id", ZoloPreference.getInstance().getIntValue(ZoloPreference.USERID))
                    .findFirst();
            realm.beginTransaction();
            kanjoComp.setEmail(mEmailView.getText().toString().trim());
            kanjoComp.setPhone(mPhoneView.getText().toString().trim());
            kanjoComp.setReferral(mReferralView.getText().toString().trim());
            kanjoComp.setName(mNameView.getText().toString().trim());
            if (!TextUtils.isEmpty(mOldPasswordView.getText().toString().trim()) &&
                    !TextUtils.isEmpty((mNewPasswordView.getText().toString()))) {
                kanjoComp.setPassword(mNewPasswordView.getText().toString().trim());
            }
            realm.commitTransaction();
            ResourcesUtil.showSnackbar(this, "" + getString(R.string.success_update));

            ZoloPreference.getInstance().saveStringValue(ZoloPreference.USEREMAIL, "" + mEmailView.getText().toString().trim());
            ZoloPreference.getInstance().saveStringValue(ZoloPreference.USERNAME, "" + mNameView.getText().toString().trim());
            ZoloPreference.getInstance().saveStringValue(ZoloPreference.USERENUMMBER, "" + mPhoneView.getText().toString().trim());
            if (!TextUtils.isEmpty(mOldPasswordView.getText().toString().trim()) &&
                    !TextUtils.isEmpty((mNewPasswordView.getText().toString()))) {
                ZoloPreference.getInstance().saveStringValue(ZoloPreference.USEREPASSWORD, "" + mNewPasswordView.getText().toString().trim());
            }
            ZoloPreference.getInstance().saveStringValue(ZoloPreference.USEREReferral, "" + mReferralView.getText().toString().trim());

        }
    }

    @OnClick(R.id.text_logout)
    void onLogout(View view) {

        try {
            ResourcesUtil.showAlertBoxForConfirmation(this, "" + getString(R.string.alert_logout), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        ZoloPreference.getInstance().clearPreference();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
