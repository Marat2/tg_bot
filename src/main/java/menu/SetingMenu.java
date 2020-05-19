package menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetingMenu implements Menu  {
    public void SetButtons(SendMessage sendMessage,HashMap<String,String> langSet) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyborwRowList = new ArrayList();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add(new KeyboardButton(langSet.get("Выбор языка")+" \uD83C\uDDF7\uD83C\uDDFA\uD83C\uDDFA\uD83C\uDDFF"));
        firstRow.add(new KeyboardButton(langSet.get("Изменить номер")+" \uD83D\uDCF1"));
        firstRow.add(new KeyboardButton(langSet.get("Выберите регион")+" \uD83C\uDF0F"));
        secondRow.add(new KeyboardButton(langSet.get("Отмена")));
        keyborwRowList.add(firstRow);
        keyborwRowList.add(secondRow);
        replyKeyboardMarkup.setKeyboard(keyborwRowList);
    }
}
