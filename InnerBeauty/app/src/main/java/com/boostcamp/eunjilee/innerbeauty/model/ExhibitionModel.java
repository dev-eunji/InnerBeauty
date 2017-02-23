package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eunjilee on 06/02/2017.
 */


public class ExhibitionModel implements Serializable {
    @SerializedName("exhibition_id")
    private int exhibitionId;
    @SerializedName("exhibition_code")
    private int exhibitionCode;
    @SerializedName("exhibition_title")
    private String exhibitionTitle;
    @SerializedName("exhibitiion_artist")
    private String exhibitionArtist;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("close_date")
    private String closeDate;
    @SerializedName("open_time")
    private String openTime;
    @SerializedName("close_time")
    private String closeTime;
    @SerializedName("exhibition_place")
    private String exhibitionPlace;
    @SerializedName("exhibition_address")
    private String exhibitionAddress;
    @SerializedName("exhibition_picture")
    private String exhibitionPicture;
    @SerializedName("exhibition_price_adult")
    private int priceAdult;
    @SerializedName("exhibition_price_student")
    private int priceStudent;
    @SerializedName("exhibition_price_children")
    private int priceChildren;
    @SerializedName("exhibition_price_old_infirm")
    private int priceOldInfirm;
    @SerializedName("exhibition_price")
    private String priceExhibition;
    @SerializedName("exhibition_call")
    private String exhibitionCall;
    @SerializedName("exhibition_site")
    private String exhibitionSite;
    @SerializedName("exhibition_detail_info")
    private  String exhibitionDetailInfo;
    @SerializedName("exhibition_ticket_site1")
    private  String exhibitionTicketSite1;
    @SerializedName("exhibition_ticket_site2")
    private  String exhibitionTicketSite2;

    private int exhibitionclicked;
    private int exhibitionLiked;
    private int exhibitionShared;
    private String exhibitionComments;


    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public int getExhibitionCode() {
        return exhibitionCode;
    }

    public void setExhibitionCode(int exhibitionCode) {
        this.exhibitionCode = exhibitionCode;
    }

    public String getExhibitionTitle() {
        return exhibitionTitle;
    }

    public void setExhibitionTitle(String exhibitionTitle) {
        this.exhibitionTitle = exhibitionTitle;
    }

    public String getExhibitionArtist() {
        return exhibitionArtist;
    }

    public void setExhibitionArtist(String exhibitionArtist) {
        this.exhibitionArtist = exhibitionArtist;
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

    public String getExhibitionPlace() {
        return exhibitionPlace;
    }

    public void setExhibitionPlace(String exhibitionPlace) {
        this.exhibitionPlace = exhibitionPlace;
    }

    public String getExhibitionAddress() {
        return exhibitionAddress;
    }

    public void setExhibitionAddress(String exhibitionAddress) {
        this.exhibitionAddress = exhibitionAddress;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getExhibitionPicture() {
        return exhibitionPicture;
    }

    public void setExhibitionPicture(String exhibitionPicture) {
        this.exhibitionPicture = exhibitionPicture;
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

    public String getExhibitionCall() {
        return exhibitionCall;
    }

    public void setExhibitionCall(String exhibitionCall) {
        this.exhibitionCall = exhibitionCall;
    }

    public String getExhibitionSite() {
        return exhibitionSite;
    }

    public void setExhibitionSite(String exhibitionSite) {
        this.exhibitionSite = exhibitionSite;
    }

    public int getExhibitionclicked() {
        return exhibitionclicked;
    }

    public void setExhibitionclicked(int exhibitionclicked) {
        this.exhibitionclicked = exhibitionclicked;
    }

    public int getExhibitionLiked() {
        return exhibitionLiked;
    }

    public void setExhibitionLiked(int exhibitionLiked) {
        this.exhibitionLiked = exhibitionLiked;
    }

    public int getExhibitionShared() {
        return exhibitionShared;
    }

    public void setExhibitionShared(int exhibitionShared) {
        this.exhibitionShared = exhibitionShared;
    }

    public String getExhibitionComments() {
        return exhibitionComments;
    }

    public void setExhibitionComments(String exhibitionComments) {
        this.exhibitionComments = exhibitionComments;
    }

    public String getPriceExhibition() {
        return priceExhibition;
    }

    public void setPriceExhibition(String priceExhibition) {
        this.priceExhibition = priceExhibition;
    }

    public String getExhibitionDetailInfo() {
        return exhibitionDetailInfo;
    }

    public void setExhibitionDetailInfo(String exhibitionDetailInfo) {
        this.exhibitionDetailInfo = exhibitionDetailInfo;
    }

    public String getExhibitionTicketSite1() {
        return exhibitionTicketSite1;
    }

    public void setExhibitionTicketSite1(String exhibitionTicketSite1) {
        this.exhibitionTicketSite1 = exhibitionTicketSite1;
    }

    public String getExhibitionTicketSite2() {
        return exhibitionTicketSite2;
    }

    public void setExhibitionTicketSite2(String exhibitionTicketSite2) {
        this.exhibitionTicketSite2 = exhibitionTicketSite2;
    }
}

