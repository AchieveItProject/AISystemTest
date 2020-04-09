package com.achieveit.systemtest.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.sql.Timestamp;

public class ProjectInfo {
    private String id;
    private String projectName;
    private String leader;
    private String customerInfo;
    private String milepost;
    private String projectFunction;
    private String technology;
    private String businessArea;
    private String scheduleDate;
    private String deliveryDate;
    private String creatorId;
    private String status;

    public ProjectInfo() {
    }

    public ProjectInfo(String projectName, String leader, String customerInfo, String milepost, String projectFunction,
                       String technology, String businessArea, String scheduleDate,
                       String deliveryDate) {
        this.projectName = projectName;
        this.leader = leader;
        this.customerInfo = customerInfo;
        this.milepost = milepost;
        this.projectFunction = projectFunction;
        this.technology = technology;
        this.businessArea = businessArea;
        this.scheduleDate = scheduleDate;

        this.deliveryDate = deliveryDate;

    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getLeader() {
        return leader;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public String getMilepost() {
        return milepost;
    }

    public String getProjectFunction() {
        return projectFunction;
    }

    public String getTechnology() {
        return technology;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }


    public String getDeliveryDate() {
        return deliveryDate;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public void setMilepost(String milepost) {
        this.milepost = milepost;
    }

    public void setProjectFunction(String projectFunction) {
        this.projectFunction = projectFunction;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }


    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectInfo)) return false;

        ProjectInfo that = (ProjectInfo) o;


        if (id != null && that.id!=null && !id.equals(that.id) ) return false;
        if (projectName != null && that.projectName!=null&&!projectName.equals(that.projectName)) return false;
        if (leader != null && that.leader!=null&&!leader.equals(that.leader)) return false;
        if (customerInfo != null && that.customerInfo!=null&&!customerInfo.equals(that.customerInfo)) return false;
        if (milepost != null && that.milepost!=null&&!milepost.equals(that.milepost)) return false;
        if (projectFunction != null && that.projectFunction!=null&&!projectFunction.equals(that.projectFunction))
            return false;
        if (technology != null && that.technology!=null&&!technology.equals(that.technology)) return false;
        if (businessArea != null && that.businessArea!=null&&!businessArea.equals(that.businessArea)) return false;
        if (scheduleDate != null && that.scheduleDate!=null&&!scheduleDate.equals(that.scheduleDate)) return false;
        if (deliveryDate != null && that.deliveryDate!=null&&!deliveryDate.equals(that.deliveryDate)) return false;
        if (creatorId != null && that.creatorId!=null&&!creatorId.equals(that.creatorId)) return false;
        if (status != null && that.status!=null&&!status.equals(that.status)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (leader != null ? leader.hashCode() : 0);
        result = 31 * result + (customerInfo != null ? customerInfo.hashCode() : 0);
        result = 31 * result + (milepost != null ? milepost.hashCode() : 0);
        result = 31 * result + (projectFunction != null ? projectFunction.hashCode() : 0);
        result = 31 * result + (technology != null ? technology.hashCode() : 0);
        result = 31 * result + (businessArea != null ? businessArea.hashCode() : 0);
        result = 31 * result + (scheduleDate != null ? scheduleDate.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
