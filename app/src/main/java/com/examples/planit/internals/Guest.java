package com.examples.planit.internals;

/**
 * Represents a Guest with a unique identifier, name, email, phone number, and RSVP status.
 * Provides methods to manage guest details and RSVP status.
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * @see UniqueIDProvider
 * @see RSVPStatus
 * @see Event
 */
public class Guest extends UniqueIDProvider<Guest> {
    private String name;
    private String email;
    private String phone;
    private RSVPStatus status;

    /**
     * Constructs a Guest with the specified name, email, phone number, and RSVP status.
     *
     * @param name   the guest's name
     * @param email  the guest's email address
     * @param phone  the guest's phone number
     * @param status the RSVP status of the guest
     */
    public Guest(String name, String email, String phone, RSVPStatus status) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    /**
     * Constructs a Guest with a unique identifier, name, email, phone number, and RSVP status as a string.
     *
     * @param uid    the unique identifier for the guest
     * @param name   the guest's name
     * @param email  the guest's email address
     * @param phone  the guest's phone number
     * @param status the RSVP status as a string
     */
    public Guest(String uid, String name, String email, String phone, String status) {
        super(uid);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = RSVPStatus.fromString(status);
    }

    /**
     * Gets the guest's name.
     *
     * @return the guest's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the guest's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the guest's email address.
     *
     * @return the guest's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the guest's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the guest's phone number.
     *
     * @return the guest's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the guest's phone number.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the RSVP status of the guest.
     *
     * @return the RSVP status
     */
    public RSVPStatus getStatus() {
        return status;
    }

    /**
     * Sets the RSVP status of the guest.
     *
     * @param status the RSVP status to set
     */
    public void setStatus(RSVPStatus status) {
        this.status = status;
    }
}
