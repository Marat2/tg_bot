package db.agent.info;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.OracleConnection.getOracleConnection;

public class AgentIntoDAOImpl implements AgentIntoDAO {
    @Override
    public AgentInfoDTO getInfoById(Integer id) {
        AgentInfoDTO region = new AgentInfoDTO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try{
            connection=getOracleConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM agent_info WHERE info_id=?");
            preparedStatement.setInt(1,id);
            resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                region= new AgentInfoDTO(resultset.getInt("info_id"),resultset.getString("info_name"),resultset.getString("info_text"),resultset.getString("info_name_uz"),resultset.getString("info_text_uz"));
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
    public List<AgentInfoDTO> getAll() {
        Connection connection = null;
        List<AgentInfoDTO> regionList = new ArrayList();
        String selectTableSQL = "SELECT * from agent_info  order by 1";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            connection= getOracleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(selectTableSQL);
            while (resultset.next()) {
                regionList.add(new AgentInfoDTO(resultset.getInt("info_id"),resultset.getString("info_name"),resultset.getString("info_text"),resultset.getString("info_name_uz"),resultset.getString("info_text_uz")));
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
        //System.out.println(regionList.toString());
        return regionList;
    }
}
