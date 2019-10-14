package com.example.lab4v01;

public class Restaurant {
    private String name="";
    private String address="";
    private String type="";

    public Restaurant() {
    }
    public Restaurant(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return (name);
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return (address);
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return (type);
    }
    @Override
    public String toString(){
        return (getName());//tra ve ten nha hang
    }

}
