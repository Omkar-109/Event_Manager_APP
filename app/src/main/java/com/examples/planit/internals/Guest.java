package com.examples.planit.internals;

public class Guest extends UniqueIDProvider<Guest> {
    private String name;
    private String email;
    private String phone;
    private RSVPStatus status;

    public Guest(String name, String email, String phone, RSVPStatus status) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public Guest(String uid, String name, String email, String phone, String status) {
        super(uid);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = RSVPStatus.fromString(status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RSVPStatus getStatus() {
        return status;
    }

    public void setStatus(RSVPStatus status) {
        this.status = status;
    }
}