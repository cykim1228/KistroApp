package com.qsol.kistroapp.data.VO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserVO implements Serializable {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("useToken")
    @Expose
    private Boolean useToken;
    @SerializedName("userToken")
    @Expose
    private String userToken;
    @SerializedName("userPW")
    @Expose
    private String userPW;
    @SerializedName("userAddress1")
    @Expose
    private String userAddress1;
    @SerializedName("userHP")
    @Expose
    private String userHP;
    @SerializedName("userAPICode")
    @Expose
    private String userAPICode;
    @SerializedName("isMaster")
    @Expose
    private Integer isMaster;
    @SerializedName("userAddress2")
    @Expose
    private String userAddress2;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("result")
    @Expose
    private String result;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Boolean getUseToken() {
        return useToken;
    }

    public void setUseToken(Boolean useToken) {
        this.useToken = useToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getUserAddress1() {
        return userAddress1;
    }

    public void setUserAddress1(String userAddress1) {
        this.userAddress1 = userAddress1;
    }

    public String getUserHP() {
        return userHP;
    }

    public void setUserHP(String userHP) {
        this.userHP = userHP;
    }

    public String getUserAPICode() { return userAPICode; }

    public void setUserAPICode(String userAPICode) {
        this.userAPICode = userAPICode;
    }

    public Integer getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Integer isMaster) {
        this.isMaster = isMaster;
    }

    public String getUserAddress2() {
        return userAddress2;
    }

    public void setUserAddress2(String userAddress2) {
        this.userAddress2 = userAddress2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}