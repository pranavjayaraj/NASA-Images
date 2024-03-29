package app.pranavjayaraj.apod.Model;

import android.support.annotation.NonNull;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by pranav.
 */

@Entity(tableName = "picture")
public class Image {

    @PrimaryKey
    @NonNull
    @SerializedName("date")
    @Expose
    @ColumnInfo(name = "date")
    private String date;

    @SerializedName("copyright")
    @Expose
    @ColumnInfo(name = "copyright")
    private String copyright;

    @SerializedName("explanation")
    @Expose
    @ColumnInfo(name = "explanation")
    private String explanation;

    @SerializedName("hdurl")
    @Expose
    @ColumnInfo(name = "hdurl")
    private String hdurl;

    @SerializedName("media_type")
    @Expose
    @ColumnInfo(name = "media_type")
    private String mediaType;

    @SerializedName("service_version")
    @Expose
    @ColumnInfo(name = "service_version")
    private String serviceVersion;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    private String url;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
