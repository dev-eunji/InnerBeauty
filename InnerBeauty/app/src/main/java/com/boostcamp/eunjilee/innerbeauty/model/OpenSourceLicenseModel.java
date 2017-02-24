package com.boostcamp.eunjilee.innerbeauty.model;

import java.io.Serializable;

/**
 * Created by eunjilee on 24/02/2017.
 */

public class OpenSourceLicenseModel implements Serializable {
    private String libraryName;
    private String libraryLicense;

    public OpenSourceLicenseModel(){}
    public OpenSourceLicenseModel(String libraryName, String libraryLicense){
        this.libraryName = libraryName;
        this.libraryLicense = libraryLicense;
    }
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryLicense() {
        return libraryLicense;
    }

    public void setLibraryLicense(String libraryLicense) {
        this.libraryLicense = libraryLicense;
    }
}
