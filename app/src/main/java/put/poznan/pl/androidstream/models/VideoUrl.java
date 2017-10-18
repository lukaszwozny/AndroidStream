package put.poznan.pl.androidstream.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoUrl {

    @SerializedName("vid_url")
    @Expose
    private String vidUrl;

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl(String vidUrl) {
        this.vidUrl = vidUrl;
    }
}
