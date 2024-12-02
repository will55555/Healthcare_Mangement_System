package org.WTT.entity;

public class Nurses {
    private int userId;
    private String firstN; //first name
    private String lastN;//lastname
    private String nurseLicense;//License type
    private String licenseExpDate;//license expiration date
    private String certification;//certification type
    private String certExpDate;//certification expiration date
    private String email;

    public Nurses() {
    }

    public Nurses(int userId, String firstN, String lastN, String licenseExpDate, String nurseLicense, String certification, String certExpDate, String email) {
        this.userId = userId;
        this.firstN = firstN;
        this.lastN = lastN;
        this.licenseExpDate = licenseExpDate;
        this.nurseLicense = nurseLicense;
        this.certification = certification;
        this.certExpDate = certExpDate;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {

        this.firstN = firstN;
    }

    public String getNurseLicense() {
        return nurseLicense;
    }

    public void setNurseLicense(String nurseLicense) {
        this.nurseLicense = nurseLicense;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getLicenseExpDate() {
        return licenseExpDate;
    }

    public void setLicenseExpDate(String licenseExpDate) {
        this.licenseExpDate = licenseExpDate;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getCertExpDate() {
        return certExpDate;
    }

    public void setCertExpDate(String certExpDate) {
        this.certExpDate = certExpDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nurses{" +
                "userId=" + userId +
                ", firstN='" + firstN + '\'' +
                ", lastN='" + lastN + '\'' +
                ", nurseLicense='" + nurseLicense + '\'' +
                ", licenseExpDate='" + licenseExpDate + '\'' +
                ", certification='" + certification + '\'' +
                ", certExpDate='" + certExpDate + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
