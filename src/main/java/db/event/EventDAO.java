package db.event;

import java.util.List;

public interface EventDAO {
    public EventDTO selectById(int id);
    public List<EventDTO> selectAll();
}
