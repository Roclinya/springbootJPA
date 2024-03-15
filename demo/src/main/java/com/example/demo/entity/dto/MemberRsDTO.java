package com.example.demo.entity.dto;

public class MemberRsDTO {
    private String usrName;
    private String usrPwd;

    public MemberRsDTO(String usrName, String usrPwd) {
        this.usrName = usrName;
        this.usrPwd = usrPwd;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }
}
