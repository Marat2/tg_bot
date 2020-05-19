package db.user;

public interface UserDAO {
    public void updateUser(UserDTO user,int id,String columnUpdate);
    public void insertUser(UserDTO user);
    public void deleteUser(Integer id);
    public UserDTO selectById(Integer id);
}
