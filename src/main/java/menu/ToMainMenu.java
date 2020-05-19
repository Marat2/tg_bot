package menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToMainMenu implements Menu {
    public void SetButtons(SendMessage sendMessage,HashMap<String,String> langSet) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyborwRowList = new ArrayList();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton(langSet.get("Начать занаво")+" \uD83D\uDCCB"));
        keyborwRowList.add(firstRow);
        replyKeyboardMarkup.setKeyboard(keyborwRowList);
    }
}
