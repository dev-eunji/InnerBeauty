package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eunjilee on 06/02/2017.
 */


public class ExhibitionModel implements Serializable {
    @SerializedName("exhibition_id")
    int exhibitionId;
    @SerializedName("exhibition_code")
    int exhibitionCode;
    @SerializedName("exhibition_title")
    String exhibitionTitle;
    @SerializedName("exhibitiion_artist")
    String exhibitionArtist;
    @SerializedName("start_date")
    String startDate;
    @SerializedName("end_date")
    String endDate;
    @SerializedName("close_date")
    String closeDate;
    @SerializedName("open_time")
    String openTime;
    @SerializedName("close_time")
    String closeTime;
    @SerializedName("exhibition_place")
    String exhibitionPlace;
    @SerializedName("exhibition_address")
    String exhibitionAddress;
    @SerializedName("exhibition_picture")
    String exhibitionPicture;
    @SerializedName("exhibition_price_adult")
    int priceAdult;
    @SerializedName("exhibition_price_student")
    int priceStudent;
    @SerializedName("exhibition_price_children")
    int priceChildren;
    @SerializedName("exhibition_price_old_infirm")
    int priceOldInfirm;
    @SerializedName("play_price_old_infirm")
    int priceSpecial;
    @SerializedName("exhibition_call")
    String exhibitionCall;
    @SerializedName("exhibition_site")
    String exhibitionSite;

    int exhibitionclicked;
    int exhibitionLiked;
    int exhibitionShared;
    String exhibitionComments;


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

    public int getPriceSpecial() {
        return priceSpecial;
    }

    public void setPriceSpecial(int priceSpecial) {
        this.priceSpecial = priceSpecial;
    }
}

