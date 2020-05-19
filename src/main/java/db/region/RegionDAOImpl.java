package db.region;

import db.user.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.OracleConnection.getOracleConnection;

public class RegionDAOImpl implements RegionDAO{

    public List<RegionDTO> selectRegions() {
        Connection connection = null;
        List<RegionDTO> regionList = new ArrayList();
        String selectTableSQL = "SELECT * from web_regions  order by 1";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection= getOracleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(selectTableSQL);
            while (resultset.next()) {
                regionList.add(new RegionDTO(resultset.getInt("region_id"),resultset.getString("region_name"),resultset.getString("region_name_uz")));
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
    public RegionDTO selectById(Integer id) {
        RegionDTO region = new RegionDTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try{
            connection=getOracleConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM web_regions WHERE region_id=?");
            preparedStatement.setInt(1,id);
            resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                region= new RegionDTO(resultset.getInt("region_id"),resultset.getString("region_name"),resultset.getString("region_name_uz"));
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
}
