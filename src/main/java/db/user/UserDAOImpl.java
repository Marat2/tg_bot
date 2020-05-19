package db.user;
import oracle.sql.BLOB;

import java.io.IOException;
import java.sql.*;

import static db.OracleConnection.getOracleConnection;
public class UserDAOImpl implements UserDAO {

    public void updateUser(UserDTO user, int id,String columnUpdate) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement(UpdateQueryStr(columnUpdate));
            if (columnUpdate.equals("region")){
                preparedStatement.setInt(1,user.getRegion_id());
            }else if(columnUpdate.equals("phone")){
                preparedStatement.setString(1, user.getMsisdn());
            }else if (columnUpdate.equals("lang")){
                preparedStatement.setString(1, user.getLang());
            }else if (columnUpdate.equals("status")){
                preparedStatement.setInt(1,user.getStatus());
            }else if (columnUpdate.equals("activation_code")){
                preparedStatement.setString(1,user.getActivation_code());
            }else if (columnUpdate.equals("agent_name")){
                preparedStatement.setString(1,user.getAgent_name());
            }else if(columnUpdate.equals("date_of_change")){
                preparedStatement.setDate(1,user.getDate_of_change());
            }else if(columnUpdate.equals("passport")){
                preparedStatement.setBlob(1,user.getPassport());
            }else{
                preparedStatement.setBlob(1,user.getCertificate());
            }
            preparedStatement.setInt(2,id);
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

    private String UpdateQueryStr(String column){
        String result;
        if(column.equals("phone")){
            result="UPDATE web_tg_agents SET msisdn=? where user_id=?";
        }else if(column.equals("lang")){
            result="UPDATE web_tg_agents SET lang=? where user_id=?";
        }else if(column.equals("region")){
            result="UPDATE web_tg_agents SET region_id=? where user_id=?";
        }else if(column.equals("status")){
            result="UPDATE web_tg_agents SET status=? where user_id=?";
        }else if(column.equals("activation_code")){
            result="UPDATE web_tg_agents SET activation_code=? where user_id=?";
        }else if(column.equals("agent_name")){
            result="UPDATE web_tg_agents SET agent_name=? where user_id=?";
        }else if(column.equals("date_of_change")){
            result="UPDATE web_tg_agents SET date_of_change=? where user_id=?";
        }else if(column.equals("passport")){
            result="UPDATE web_tg_agents SET passport=? where user_id=?";
        }else{
            result="UPDATE web_tg_agents SET certificate=? where user_id=?";
        }
        return result;
    }

    public void insertUser(UserDTO user) {
        System.out.println(user.toString());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO web_tg_agents (user_id) VALUES (?)");
            preparedStatement.setInt(1,user.getUser_id());
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

    public void deleteUser(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = getOracleConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM web_tg_agents id=?");
            preparedStatement.setInt(1,id);
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

    public UserDTO selectById(Integer id) {
        UserDTO user = new UserDTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try{
            connection=getOracleConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM web_tg_agents WHERE user_id=?");
            preparedStatement.setInt(1,id);
            resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                user= new UserDTO(
                        resultset.getInt("user_id"),
                        resultset.getString("lang"),
                        resultset.getInt("status"),
                        resultset.getDate("date_of_change"),
                        resultset.getInt("region_id"),
                        resultset.getString("msisdn"),
                        resultset.getString("agent_name"),
                        resultset.getString("activation_code"),
                        resultset.getBinaryStream("passport"),
                        resultset.getBinaryStream("certificate")
                );
            }
        }catch(SQLException e){
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
            if (resultset!=null){
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }
    public Integer notificationUser( String p_type_id,String p_recipient,String p_method_id,String p_entity_type,String p_entity_id,String p_user_id,String p_text){
        //notificationUser("1", req.getPHONE(), "2", "2", "0", "1771", req.getCODE());
        Connection connection = null;
        CallableStatement  preparedStatement = null;
        try {
            connection=getOracleConnection();
            preparedStatement=connection.prepareCall("{call paynet.notif_pkg.create_notification(?,?,?,?,?,?,?,?)}");
            preparedStatement.setString(1,p_type_id);
            preparedStatement.setString(2,p_recipient);
            preparedStatement.setString(3,p_method_id);
            preparedStatement.setString(4,p_entity_type);
            preparedStatement.setString(5,p_entity_id);
            preparedStatement.setString(6,p_user_id);
            preparedStatement.setString(7,p_text);
            preparedStatement.registerOutParameter(8, Types.FLOAT);
            preparedStatement.execute();
        } catch (SQLException e) {
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
        return null;
    }
}
