package com.mo.po;

public class Result {
    public Result() {
        super();
    }
    public Result(String iD, String pOS, String iN, String nowTime) {
        super();
        ID = iD;
        POS = pOS;
        IN = iN;
        NowTime = nowTime;
    }
    private String ID;//RFID号(4字节)
    private String POS;//位置信息（2字节）
    private String IN;//移动信息（1字节）
    private String CRC;//校验码（2字节）
    private String NowTime;//当前时间
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getPOS() {
        return POS;
    }
    public void setPOS(String pOS) {
        POS = pOS;
    }
    public String getIN() {
        return IN;
    }
    public void setIN(String iN) {
        IN = iN;
    }
    public String getCRC() {
        return CRC;
    }
    public void setCRC(String cRC) {
        CRC = cRC;
    }

    @Override
    public String toString() {
        return "Result{" +
                "ID='" + ID + '\'' +
                ", POS='" + POS + '\'' +
                ", IN='" + IN + '\'' +
                ", CRC='" + CRC + '\'' +
                ", NowTime='" + NowTime + '\'' +
                '}';
    }

    public String getNowTime() {
        return NowTime;
    }
    public void setNowTime(String nowTime) {
        NowTime = nowTime;
    }
}
