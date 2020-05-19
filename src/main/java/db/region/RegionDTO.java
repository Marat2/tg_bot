package db.region;

public class RegionDTO {

    private Integer region_id;
    private String region_name;
    private String region_name_uz;
    RegionDTO(){

    }

    RegionDTO(int region_id,String region_name,String region_name_uz){
        this.region_id = region_id;
        this.region_name = region_name;
        this.region_name_uz = region_name_uz;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getRegion_name_uz() {
        return region_name_uz;
    }

    public void setRegion_name_uz(String region_name_uz) {
        this.region_name_uz = region_name_uz;
    }

    public String getReg(String lang){
        if(lang.equals("ru")){
            return this.region_name;
        }else{
            return this.region_name_uz;
        }
    }

    @Override
    public String toString() {
        return "RegionDTO{" +
                "region_id=" + region_id +
                ", region_name='" + region_name + '\'' +
                ", region_name_uz='" + region_name_uz + '\'' +
                '}';
    }
}
