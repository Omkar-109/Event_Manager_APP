package com.examples.planit.internals;

public class Vendor extends UniqueIDProvider<Vendor> {
    private String name;
    private String serviceType;
    private String contactInfo;

    public Vendor(String name, String serviceType, String contactInfo) {
        super();
        this.name = name;
        this.serviceType = serviceType;
        this.contactInfo = contactInfo;
    }

    public Vendor(String uid, String name, String serviceType, String contactInfo) {
        super(uid);
        this.name = name;
        this.serviceType = serviceType;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}

