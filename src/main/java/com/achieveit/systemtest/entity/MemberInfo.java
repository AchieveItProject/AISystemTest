package com.achieveit.systemtest.entity;

public class MemberInfo {
    private String memberId;
    private String memberRole;

    private String memberName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberInfo)) return false;

        MemberInfo that = (MemberInfo) o;

        if (memberId != null && that.memberId !=null && !memberId.equals(that.memberId)) return false;
        if (memberRole != null && that.memberRole !=null && !memberRole.equals(that.memberRole)) return false;
        if(memberName != null && that.memberName !=null && !memberName.equals(that.memberName))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId != null ? memberId.hashCode() : 0;
        result = 31 * result + (memberRole != null ? memberRole.hashCode() : 0);
        result = 31 * result + (memberName != null ? memberName.hashCode() : 0);
        return result;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        switch(memberRole){
            case "1":
                this.memberRole ="项目上级";
                break;
            case "2":
                this.memberRole ="组织级配置管理员";
                break;
            case "3":
                this.memberRole ="项目改进小组";
                break;
            case "4":
                this.memberRole ="质量监控";
                break;
            case "5":
                this.memberRole ="项目经理";
                break;
            case "6":
                this.memberRole ="项目资产管理员";
                break;
            case "7":
                this.memberRole ="项目成员";
                break;
        }
//        this.memberRole = memberRole;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
