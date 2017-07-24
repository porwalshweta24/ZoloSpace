package app.com.zolospace.data.remote.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class LoginItem implements Parcelable {
    public static final Creator<LoginItem> CREATOR = new Creator<LoginItem>() {
        @Override
        public LoginItem createFromParcel(Parcel source) {
            return new LoginItem(source);
        }

        @Override
        public LoginItem[] newArray(int size) {
            return new LoginItem[size];
        }
    };
    private int id;
    @SerializedName("phone")
    private String phone;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("referral")
    private String referral;

    public LoginItem() {
    }

    protected LoginItem(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.password = in.readString();
        this.referral = in.readString();

    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.password);
        dest.writeString(this.phone);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.referral);
    }
}
