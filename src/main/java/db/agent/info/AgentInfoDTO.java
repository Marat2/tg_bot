package db.agent.info;

public class AgentInfoDTO {
    private Integer info_id;
    private String info_name;
    private String info_text;
    private String info_name_uz;
    private String info_text_uz;

    AgentInfoDTO(){

    }

    public AgentInfoDTO(Integer info_id, String info_name, String info_text, String info_name_uz, String info_text_uz) {
        this.info_id = info_id;
        this.info_name = info_name;
        this.info_text = info_text;
        this.info_name_uz = info_name_uz;
        this.info_text_uz = info_text_uz;
    }

    public Integer getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Integer info_id) {
        this.info_id = info_id;
    }

    public String getInfo_name() {
        return info_name;
    }

    public void setInfo_name(String info_name) {
        this.info_name = info_name;
    }

    public String getInfo_text() {
        return info_text;
    }

    public void setInfo_text(String info_text) {
        this.info_text = info_text;
    }

    public String getInfo_name_uz() {
        return info_name_uz;
    }

    public void setInfo_name_uz(String info_name_uz) {
        this.info_name_uz = info_name_uz;
    }

    public String getInfo_text_uz() {
        return info_text_uz;
    }

    public void setInfo_text_uz(String info_text_uz) {
        this.info_text_uz = info_text_uz;
    }

    public String getInfo(String lang){
        if(lang.equals("ru")){
            return this.info_name;
        }else{
            return this.info_name_uz;
        }
    }
    public String getInfoText(String lang){
        if(lang.equals("ru")){
            return this.info_text;
        }else{
            return this.info_text_uz;
        }
    }

    @Override
    public String toString() {
        return "AgentInfoDTO{" +
                "info_id=" + info_id +
                ", info_name='" + info_name + '\'' +
                ", info_text='" + info_text + '\'' +
                ", info_name_uz='" + info_name_uz + '\'' +
                ", info_text_uz='" + info_text_uz + '\'' +
                '}';
    }
}
