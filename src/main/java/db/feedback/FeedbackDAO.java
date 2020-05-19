package db.feedback;

public interface FeedbackDAO {
    public void insertUser(FeedbackDTO feed);
    public void deleteUser(Integer id);
    public FeedbackDTO selectById(Integer id);
}
