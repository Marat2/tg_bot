package db.feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static db.OracleConnection.getOracleConnection;

public class FeedbackDAOImpl {
        public void insertFeedback(FeedbackDTO user) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try{
                connection = getOracleConnection();
                preparedStatement = connection.prepareStatement("INSERT INTO feedback (text,user_id,user_name) VALUES (?,?,?)");
                preparedStatement.setString(1,user.getText());
                preparedStatement.setInt(2,user.getUser_id());
                preparedStatement.setString(3,user.getUser_name());
                preparedStatement.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if (preparedStatement!=null){
                    try{
                        preparedStatement.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                if (connection!=null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}


