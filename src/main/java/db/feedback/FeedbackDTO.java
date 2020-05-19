package db.feedback;

public class FeedbackDTO {
    private String text;
    private Integer user_id;
    private String  user_name;

    FeedbackDTO(){

    }
    public FeedbackDTO(String text, Integer user_id, String user_name){
        this.text=text;
        this.user_id=user_id;
        this.user_name=user_name;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "text='" + text + '\'' +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}