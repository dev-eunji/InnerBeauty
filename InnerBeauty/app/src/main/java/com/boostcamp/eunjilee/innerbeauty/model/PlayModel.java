package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class PlayModel implements Serializable {
    @SerializedName("play_id")
    private int playId;
    @SerializedName("play_code")
    private int playCode;
    @SerializedName("play_title")
    private String playTitle;
    @SerializedName("play_actors") //TODO : Actor객체로 바꿔야하지 않을까. Actor 테이블을 만들어서 join해서 사용해야하나?
    private String playActors;
    @SerializedName("start_date")
    private  String startDate;
    @SerializedName("end_date")
    private  String endDate;
    @SerializedName("close_date")
    private  String closeDate;
    @SerializedName("play_time")
    private  String playTime;
    @SerializedName("play_run_time")
    private  String playRunTime;
    @SerializedName("play_place")
    private  String playPlace;
    @SerializedName("play_address")
    private  String playAddress;
    @SerializedName("play_picture")
    private  String playPicture;
    @SerializedName("play_price_adult")
    private  int priceAdult;
    @SerializedName("play_price_student")
    private  int priceStudent;
    @SerializedName("play_price_children")
    private  int priceChildren;
    @SerializedName("play_price_old_infirm")
    private  int priceOldInfirm;
    @SerializedName("play_price")
    private  String pricePlay;
    @SerializedName("play_call")
    private  String playCall;
    @SerializedName("play_site")
    private  String playSite;
    @SerializedName("play_detail_info")
    private  String playDetailInfo;
    @SerializedName("play_ticket_site1")
    private  String playTicketSite1;
    @SerializedName("play_ticket_site2")
    private  String playTicketSite2;

    private int playClicked;
    private int playLiked;
    private int playShared;
    private String playComments;

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

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getPlayRunTime() {
        return playRunTime;
    }

    public void setPlayRunTime(String playRunTime) {
        this.playRunTime = playRunTime;
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

    public int getPlayClicked() {
        return playClicked;
    }

    public void setPlayClicked(int playClicked) {
        this.playClicked = playClicked;
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

    public String getPricePlay() {
        return pricePlay;
    }

    public void setPricePlay(String pricePlay) {
        this.pricePlay = pricePlay;
    }

    public String getPlayDetailInfo() {
        return playDetailInfo;
    }

    public void setPlayDetailInfo(String playDetailInfo) {
        this.playDetailInfo = playDetailInfo;
    }

    public String getPlayTicketSite1() {
        return playTicketSite1;
    }

    public void setPlayTicketSite1(String playTicketSite1) {
        this.playTicketSite1 = playTicketSite1;
    }

    public String getPlayTicketSite2() {
        return playTicketSite2;
    }

    public void setPlayTicketSite2(String playTicketSite2) {
        this.playTicketSite2 = playTicketSite2;
    }
}
