package com.mo.po;

public class Position {
    public Position() {
        super();
    }

    public Position(String posid, String posname, String longitude, String latitude) {
        this.posid = posid;
        this.posname = posname;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private String posid;
    private String posname;
    private String longitude;
    private String latitude;

    public String getPosid() {
        return posid;
    }

    public String getPosname() {
        return posname;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public void setPosname(String posname) {
        this.posname = posname;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "posid='" + posid + '\'' +
                ", posname='" + posname + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
