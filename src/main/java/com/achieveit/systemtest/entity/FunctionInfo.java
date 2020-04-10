package com.achieveit.systemtest.entity;

public class FunctionInfo {
    private String functionId;
    private String name;
    private String people;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionInfo)) return false;

        FunctionInfo that = (FunctionInfo) o;

        if (functionId != null && that.functionId!=null && !functionId.equals(that.functionId)) return false;
        if (name != null && that.name!=null && !name.equals(that.name)) return false;
        if(people != null && that.people!=null && !people.equals(that.people))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = functionId != null ? functionId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        return result;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
