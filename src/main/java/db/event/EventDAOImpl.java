package db.event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.OracleConnection.getOracleConnection;

public class EventDAOImpl implements EventDAO {
    @Override
    public EventDTO selectById(int id) {
        EventDTO region = new EventDTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try{
            connection=getOracleConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM event WHERE EVENT_ID=?");
            preparedStatement.setInt(1,id);
            resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                region= new EventDTO(resultset.getInt("EVENT_ID"),resultset.getString("EVENT_NAME"),resultset.getString("EVENT_TEXT"),resultset.getString("EVENT_NAME_UZ"),resultset.getString("EVENT_TEXT_UZ"));
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
        return region;
    }

    @Override
    public List<EventDTO> selectAll() {
        Connection connection = null;
        List<EventDTO> regionList = new ArrayList();
        String selectTableSQL = "SELECT * from event  order by 1";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection= getOracleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(selectTableSQL);
            while (resultset.next()) {
                regionList.add(new EventDTO(resultset.getInt("EVENT_ID"),resultset.getString("EVENT_NAME"),resultset.getString("EVENT_TEXT"),resultset.getString("EVENT_NAME_UZ"),resultset.getString("EVENT_TEXT_UZ")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
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
        return regionList;
    }
}
