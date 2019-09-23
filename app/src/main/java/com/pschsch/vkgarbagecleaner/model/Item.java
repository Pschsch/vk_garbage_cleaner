package com.pschsch.vkgarbagecleaner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("photo_130")
    @Expose
    private String photo130;
    @SerializedName("photo_320")
    @Expose
    private String photo320;
    @SerializedName("photo_640")
    @Expose
    private String photo640;
    @SerializedName("is_favorite")
    @Expose
    private Boolean isFavorite;
    @SerializedName("adding_date")
    @Expose
    private Integer addingDate;
    @SerializedName("player")
    @Expose
    private String player;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("can_add")
    @Expose
    private Integer canAdd;
    @SerializedName("can_comment")
    @Expose
    private Integer canComment;
    @SerializedName("can_repost")
    @Expose
    private Integer canRepost;
    @SerializedName("repeat")
    @Expose
    private Integer repeat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getPhoto130() {
        return photo130;
    }

    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    public String getPhoto320() {
        return photo320;
    }

    public void setPhoto320(String photo320) {
        this.photo320 = photo320;
    }

    public String getPhoto640() {
        return photo640;
    }

    public void setPhoto640(String photo640) {
        this.photo640 = photo640;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Integer getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Integer addingDate) {
        this.addingDate = addingDate;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(Integer canAdd) {
        this.canAdd = canAdd;
    }

    public Integer getCanComment() {
        return canComment;
    }

    public void setCanComment(Integer canComment) {
        this.canComment = canComment;
    }

    public Integer getCanRepost() {
        return canRepost;
    }

    public void setCanRepost(Integer canRepost) {
        this.canRepost = canRepost;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }
}
