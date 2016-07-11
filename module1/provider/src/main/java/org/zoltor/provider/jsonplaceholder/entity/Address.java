package org.zoltor.provider.jsonplaceholder.entity;

import org.zoltor.communicator.annotation.JsonItem;

/**
 * Created by zoltor on 11/07/16.
 */
public class Address extends BaseEntity {

    public static class GeoLocation extends BaseEntity {

        @JsonItem("lat")
        private String latitude;

        @JsonItem("lng")
        private String longitude;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

    @JsonItem("street")
    private String street;

    @JsonItem("suite")
    private String suite;

    @JsonItem("city")
    private String city;

    @JsonItem("zipcode")
    private String zipCode;

    @JsonItem("geo")
    private GeoLocation geoLocation;

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
