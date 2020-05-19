package db.info;

public class InfoDTO {
    private String INFO_TEXT;
    private String INFO_TEXT_UZ;

    InfoDTO() {

    }

    public InfoDTO(String INFO_TEXT, String INFO_TEXT_UZ) {
        this.INFO_TEXT = INFO_TEXT;
        this.INFO_TEXT_UZ = INFO_TEXT_UZ;
    }

    public String getINFO_TEXT() {
        return INFO_TEXT;
    }

    public void setINFO_TEXT(String INFO_TEXT) {
        this.INFO_TEXT = INFO_TEXT;
    }

    public String getINFO_TEXT_UZ() {
        return INFO_TEXT_UZ;
    }

    public void setINFO_TEXT_UZ(String INFO_TEXT_UZ) {
        this.INFO_TEXT_UZ = INFO_TEXT_UZ;
    }

    public String getInfoText(String lang){
        if(lang.equals("ru")){
            return this.INFO_TEXT;
        }else{
            return this.INFO_TEXT_UZ;
        }
    }

    @Override
    public String toString() {
        return "InfoDTO{" +
                "INFO_TEXT='" + INFO_TEXT + '\'' +
                ", INFO_TEXT_UZ='" + INFO_TEXT_UZ + '\'' +
                '}';
    }
}
