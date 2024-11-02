package com.examples.planit.internals;

/**
 * Represents a Vendor with a unique identifier, name, service type, and contact information.
 * Provides methods to manage vendor details.
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * @see UniqueIDProvider
 * @see Event
 */
public class Vendor extends UniqueIDProvider<Vendor> {
    private String name;
    private String serviceType;
    private String contactInfo;

    /**
     * Constructs a Vendor with the specified name, service type, and contact information.
     *
     * @param name        the vendor's name
     * @param serviceType the type of service the vendor provides
     * @param contactInfo the contact information of the vendor
     */
    public Vendor(String name, String serviceType, String contactInfo) {
        super();
        this.name = name;
        this.serviceType = serviceType;
        this.contactInfo = contactInfo;
    }

    /**
     * Constructs a Vendor with a unique identifier, name, service type, and contact information.
     *
     * @param uid         the unique identifier for the vendor
     * @param name        the vendor's name
     * @param serviceType the type of service the vendor provides
     * @param contactInfo the contact information of the vendor
     */
    public Vendor(String uid, String name, String serviceType, String contactInfo) {
        super(uid);
        this.name = name;
        this.serviceType = serviceType;
        this.contactInfo = contactInfo;
    }

    /**
     * Gets the vendor's name.
     *
     * @return the vendor's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the vendor's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of service the vendor provides.
     *
     * @return the service type
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the type of service the vendor provides.
     *
     * @param serviceType the service type to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Gets the contact information of the vendor.
     *
     * @return the contact information
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact information of the vendor.
     *
     * @param contactInfo the contact information to set
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
