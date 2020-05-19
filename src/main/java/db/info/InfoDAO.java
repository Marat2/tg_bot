package db.info;

import java.util.List;

public interface InfoDAO {
    public InfoDTO selectById(int id);
    public List<InfoDTO> selectAll();

}
