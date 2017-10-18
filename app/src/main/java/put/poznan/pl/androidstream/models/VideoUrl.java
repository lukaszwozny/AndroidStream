package put.poznan.pl.androidstream.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoUrl {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vid_url")
    @Expose
    private String vidUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl(String vidUrl) {
        this.vidUrl = vidUrl;
    }
}
