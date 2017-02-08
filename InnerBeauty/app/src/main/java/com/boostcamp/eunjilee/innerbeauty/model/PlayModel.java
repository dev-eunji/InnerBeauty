package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class PlayModel implements Serializable {
    @SerializedName("play_id")
    int playId;
    @SerializedName("play_code")
    int playCode;
    @SerializedName("play_title")
    String playTitle;
    @SerializedName("play_actors") //TODO : Actor객체로 바꿔야하지 않을까. Actor 테이블을 만들어서 join해서 사용해야하나?
    String playActors;
    @SerializedName("start_date")
    String startDate;
    @SerializedName("end_date")
    String endDate;
    @SerializedName("play_place")
    String playPlace;
    @SerializedName("play_address")
    String playAddress;
    @SerializedName("play_picture")
    String playPicture;
    @SerializedName("play_price_adult")
    int priceAdult;
    @SerializedName("play_price_student")
    int priceStudent;
    @SerializedName("play_price_children")
    int priceChildren;
    @SerializedName("play_price_old_infirm")
    int priceOldInfirm;
    @SerializedName("play_price_special")
    int priceSpecial;
    @SerializedName("play_call")
    String playCall;
    @SerializedName("play_site")
    String playSite;

    int playclicked;
    int playLiked;
    int playShared;
    String playComments;

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    public int getPlayCode() {return playCode; }

    public void setPlayCode(int playCode) {
        this.playCode = playCode;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    public String getPlayActors() {
        return playActors;
    }

    public void setPlayActors(String playActors) {
        this.playActors = playActors;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlayPlace() {
        return playPlace;
    }

    public void setPlayPlace(String playPlace) {
        this.playPlace = playPlace;
    }

    public String getPlayAddress() {
        return playAddress;
    }

    public void setPlayAddress(String playAddress) {
        this.playAddress = playAddress;
    }

    public String getPlayPicture() {
        return playPicture;
    }

    public void setPlayPicture(String playPicture) {
        this.playPicture = playPicture;
    }

    public int getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(int priceAdult) {
        this.priceAdult = priceAdult;
    }

    public int getPriceStudent() {
        return priceStudent;
    }

    public void setPriceStudent(int priceStudent) {
        this.priceStudent = priceStudent;
    }

    public int getPriceChildren() {
        return priceChildren;
    }

    public void setPriceChildren(int priceChildren) {
        this.priceChildren = priceChildren;
    }

    public int getPriceOldInfirm() {
        return priceOldInfirm;
    }

    public void setPriceOldInfirm(int priceOldInfirm) {
        this.priceOldInfirm = priceOldInfirm;
    }

    public String getPlayCall() {
        return playCall;
    }

    public void setPlayCall(String playCall) {
        this.playCall = playCall;
    }

    public String getPlaySite() {
        return playSite;
    }

    public void setPlaySite(String playSite) {
        this.playSite = playSite;
    }

    public int getPlayclicked() {
        return playclicked;
    }

    public void setPlayclicked(int playclicked) {
        this.playclicked = playclicked;
    }

    public int getPlayLiked() {
        return playLiked;
    }

    public void setPlayLiked(int playLiked) {
        this.playLiked = playLiked;
    }

    public int getPlayShared() {
        return playShared;
    }

    public void setPlayShared(int playShared) {
        this.playShared = playShared;
    }

    public String getPlayComments() {
        return playComments;
    }

    public void setPlayComments(String playComments) {
        this.playComments = playComments;
    }

    public int getPriceSpecial() {
        return priceSpecial;
    }

    public void setPriceSpecial(int priceSpecial) {
        this.priceSpecial = priceSpecial;
    }
}
