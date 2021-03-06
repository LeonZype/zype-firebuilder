package com.zype.fire.api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny Cherkasov on 27.03.2019.
 */
public class ZobjectContentData {
    @SerializedName("_id")
    @Expose
    public String id;

    @Expose
    public Boolean active;

    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @Expose
    public String description;

    @SerializedName("friendly_title")
    @Expose
    public String friendlyTitle;

    @SerializedName("help_url")
    @Expose
    public List<Object> keywords = new ArrayList<>();

    @Expose
    public int limit;

    @Expose
    public String message;

    @SerializedName("refresh_rate")
    @Expose
    public String refreshRate;

    @SerializedName("site_id")
    @Expose
    public String siteId;

    @Expose
    public String title;

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @SerializedName("video_ids")
    @Expose
    public List<Object> videoIds = new ArrayList<>();

    @SerializedName("zobject_type_id")
    @Expose
    public String zobjectTypeId;

    @SerializedName("zobject_type_title")
    @Expose
    public String zobjectTypeTitle;

    @SerializedName("playlistid")
    @Expose
    public String playlistid;

    @SerializedName("pictures")
    @Expose
    public List<Image> images = new ArrayList<>();


    @SerializedName("videoid")
    @Expose
    public String videoid;


    @SerializedName("priority")
    @Expose
    public int priority;

}
