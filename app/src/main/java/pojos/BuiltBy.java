
package pojos;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class BuiltBy implements Parcelable {

    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("href")
    private String mHref;
    @SerializedName("username")
    private String mUsername;

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mAvatar);
        dest.writeString(this.mHref);
        dest.writeString(this.mUsername);
    }

    public BuiltBy() {
    }

    protected BuiltBy(Parcel in) {
        this.mAvatar = in.readString();
        this.mHref = in.readString();
        this.mUsername = in.readString();
    }

    public static final Parcelable.Creator<BuiltBy> CREATOR = new Parcelable.Creator<BuiltBy>() {
        @Override
        public BuiltBy createFromParcel(Parcel source) {
            return new BuiltBy(source);
        }

        @Override
        public BuiltBy[] newArray(int size) {
            return new BuiltBy[size];
        }
    };
}
