package menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;

public interface Menu {
    public void SetButtons(SendMessage msg,HashMap<String,String> langSet);
}
