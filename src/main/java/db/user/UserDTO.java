package db.user;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
public class UserDTO {

    private Integer user_id;
    private String lang;
    private Integer status;
    private String activation_code;
    private Date date_of_change;
    private Integer region_id;
    private String msisdn;
    private String agent_name;
    private InputStream passport;
    private InputStream certificate;

    public UserDTO(){}

    public UserDTO(Integer user_id,String lang, Integer status, Date date_of_change, Integer region_id, String msisdn, String agent_name,String activation_code,InputStream passport,InputStream certificate) {
        this.lang = lang;
        this.status = status;
        this.date_of_change = date_of_change;
        this.region_id = region_id;
        this.msisdn = msisdn;
        this.agent_name = agent_name;
        this.user_id=user_id;
        this.activation_code=activation_code;
        this.passport=passport;
        this.certificate=certificate;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public Date getDate_of_change() {
        return date_of_change;
    }

    public void setDate_of_change(Date date_of_change) {
        this.date_of_change = date_of_change;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public InputStream getPassport() {
        return passport;
    }

    public void setPassport(InputStream passport) {
        this.passport = passport;
    }

    public InputStream getCertificate() {
        return certificate;
    }

    public void setCertificate(InputStream certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_id=" + user_id +
                ", lang='" + lang + '\'' +
                ", status=" + status +
                ", activation_code='" + activation_code + '\'' +
                ", date_of_change='" + date_of_change + '\'' +
                ", region_id=" + region_id +
                ", msisdn='" + msisdn + '\'' +
                ", agent_name='" + agent_name + '\'' +
                ", passport=" + passport +
                ", certificate=" + certificate +
                '}';
    }
}
