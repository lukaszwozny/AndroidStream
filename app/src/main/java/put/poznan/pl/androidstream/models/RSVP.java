package put.poznan.pl.androidstream.models;

import com.google.gson.annotations.SerializedName;

public class RSVP {
    @SerializedName("response")
    String mResponse;

    @SerializedName("member")
    Member mMember;

    @Override
    public String toString() {
        return mMember.mName + " says " + mResponse + "!";
    }
}
