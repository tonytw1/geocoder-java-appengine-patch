package com.google.code.geocoder.model;


/**
 * @author <a href="mailto:panchmp@gmail.com">Michael Panchenko</a>
 */
public class GeocoderRequest {
    private String address;         //Address. Optional.
    private String language;        //Preferred language for results. Optional.
    private String region;          //Country code top-level domain within which to search. Optional.
    private LatLngBounds bounds;    //LatLngBounds within which to search. Optional.
    private LatLng location;        //LatLng about which to search. Optional.

    public GeocoderRequest() {
    }

    public GeocoderRequest(String address, String language) {
        this.address = address;
        this.language = language;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLngBounds getBounds() {
        return bounds;
    }

    public void setBounds(LatLngBounds bounds) {
        this.bounds = bounds;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GeocoderRequest");
        sb.append("{address='").append(address).append('\'');
        sb.append(", bounds=").append(bounds);
        sb.append(", language='").append(language).append('\'');
        sb.append(", location=").append(location);
        sb.append(", region='").append(region).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeocoderRequest that = (GeocoderRequest) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (bounds != null ? !bounds.equals(that.bounds) : that.bounds != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (bounds != null ? bounds.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }
}