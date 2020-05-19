package db.info;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.OracleConnection.getOracleConnection;

public class InfoDAOImpl implements InfoDAO {
    @Override
    public InfoDTO selectById(int id) {
        InfoDTO region = new InfoDTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try{
            connection=getOracleConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM bot_info WHERE INFO_ID=?");
            preparedStatement.setInt(1,id);
            resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                region= new InfoDTO(resultset.getString("INFO_TEXT"),resultset.getString("INFO_TEXT_UZ"));
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
    public List<InfoDTO> selectAll() {
        Connection connection = null;
        List<InfoDTO> regionList = new ArrayList();
        String selectTableSQL = "SELECT * from bot_info  order by 1";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection= getOracleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(selectTableSQL);
            while (resultset.next()) {
                regionList.add(new InfoDTO(resultset.getString("INFO_TEXT"),resultset.getString("INFO_TEXT_UZ")));
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
