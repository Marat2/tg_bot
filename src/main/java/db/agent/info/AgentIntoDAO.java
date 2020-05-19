package db.agent.info;

import java.util.List;

public interface AgentIntoDAO {
    public AgentInfoDTO getInfoById(Integer id);
    public List<AgentInfoDTO> getAll();
}
