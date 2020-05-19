package menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenu implements Menu  {
    public void SetButtons(SendMessage sendMessage,HashMap<String,String> langSet) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyborwRowList = new ArrayList();
        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add(new KeyboardButton(langSet.get("Как стать агентом")+" \uD83E\uDDD1\uD83C\uDFFF\u200D\uD83D\uDCBB"));
        firstRow.add(new KeyboardButton(langSet.get("Агентам Пайнет")+" \uD83D\uDDA5"));
        firstRow.add(new KeyboardButton(langSet.get("Акции")+" \uD83D\uDCC4"));
        secondRow.add(new KeyboardButton(langSet.get("Информация")+" \uD83D\uDCBE"));
        secondRow.add(new KeyboardButton(langSet.get("Оставить отзыв")+" \uD83D\uDCDE"));
        secondRow.add(new KeyboardButton(langSet.get("Настройки")+" \uD83D\uDEE0"));
        keyborwRowList.add(firstRow);
        keyborwRowList.add(secondRow);
        replyKeyboardMarkup.setKeyboard(keyborwRowList);
    }
}
