package db.event;

public class EventDTO {

    private Integer event_id;
    private String EVENT_NAME;
    private String EVENT_TEXT;
    private String EVENT_NAME_UZ;
    private String EVENT_TEXT_UZ;

    EventDTO(){

    }
    public EventDTO(Integer event_id,String EVENT_NAME,String EVENT_TEXT,String EVENT_NAME_UZ,String EVENT_TEXT_UZ){
        this.event_id=event_id;
        this.EVENT_NAME=EVENT_NAME;
        this.EVENT_TEXT=EVENT_TEXT;
        this.EVENT_NAME_UZ=EVENT_NAME_UZ;
        this.EVENT_TEXT_UZ=EVENT_TEXT_UZ;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getEVENT_NAME() {
        return EVENT_NAME;
    }

    public void setEVENT_NAME(String EVENT_NAME) {
        this.EVENT_NAME = EVENT_NAME;
    }

    public String getEVENT_TEXT() {
        return EVENT_TEXT;
    }

    public void setEVENT_TEXT(String EVENT_TEXT) {
        this.EVENT_TEXT = EVENT_TEXT;
    }

    public String getEVENT_NAME_UZ() {
        return EVENT_NAME_UZ;
    }

    public void setEVENT_NAME_UZ(String EVENT_NAME_UZ) {
        this.EVENT_NAME_UZ = EVENT_NAME_UZ;
    }

    public String getEVENT_TEXT_UZ() {
        return EVENT_TEXT_UZ;
    }

    public void setEVENT_TEXT_UZ(String EVENT_TEXT_UZ) {
        this.EVENT_TEXT_UZ = EVENT_TEXT_UZ;
    }

    public String getName(String lang){
        if(lang.equals("ru")){
            return this.EVENT_NAME;
        }else{
            return this.EVENT_NAME_UZ;
        }
    }
    public String getText(String lang){
        if(lang.equals("ru")){
            return this.EVENT_TEXT;
        }else{
            return this.EVENT_TEXT_UZ;
        }
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "event_id=" + event_id +
                ", EVENT_NAME='" + EVENT_NAME + '\'' +
                ", EVENT_TEXT='" + EVENT_TEXT + '\'' +
                ", EVENT_NAME_UZ='" + EVENT_NAME_UZ + '\'' +
                ", EVENT_TEXT_UZ='" + EVENT_TEXT_UZ + '\'' +
                '}';
    }
}
