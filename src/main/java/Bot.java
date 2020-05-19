import db.agent.info.AgentInfoDTO;
import db.agent.info.AgentIntoDAOImpl;
import db.event.EventDAOImpl;
import db.event.EventDTO;
import db.feedback.FeedbackDAOImpl;
import db.feedback.FeedbackDTO;
import db.info.InfoDAOImpl;
import db.info.InfoDTO;
import db.region.RegionDAOImpl;
import db.region.RegionDTO;
import db.user.UserDAOImpl;
import db.user.UserDTO;
import helpers.CodeGenerator;
import lang.Lang;
import menu.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Bot extends TelegramLongPollingBot {
    //------------------------
    //private int state;
    //UserDAOImpl userDAO;
    //RegionDAOImpl regions ;
    //CodeGenerator generator;
    //------------------------
    private HashMap<String,String> langSet;
    int state;
    String lang;
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi tgbotApi = new TelegramBotsApi();
        try {
            tgbotApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
    public void onUpdateReceived(Update update) {
        System.out.println("-------===----===-------");
        System.out.println("state : "+state);
        System.out.println("update : "+update.toString());
        //int state;
        UserDTO user;
        Message message=null;
        UserDAOImpl userDAO = new UserDAOImpl();
        //RegionDAOImpl regions = new RegionDAOImpl();
        CodeGenerator generator = new CodeGenerator();
        if (update.getMessage()!=null){
            message = update.getMessage();
            user = userDAO.selectById(message.getFrom().getId());
        }else{
            user = userDAO.selectById(update.getCallbackQuery().getFrom().getId());
        }

        getLang(user.getLang());

        if(message!=null ){
            if (message.hasText()){
                String textMsg=message.getText().toString();
                if (textMsg.equals("/start")){
                    if(user.getUser_id()==null){
                        userDAO.insertUser(new UserDTO(message.getFrom().getId(),null,null,null,null,null,null,null,null,null));
                        this.state = BotState.LANG.getState();
                        sendMesg(message,"Выберите язык",new LangMenu());
                    }else{
                        if(user.getLang()==null){
                            this.state = BotState.LANG.getState();
                            sendMesg(message,"Выберите язык",new LangMenu());
                        }else{
                            this.state = BotState.COMMON.getState();
                            sendMesg(message,langSet.get("Что бы зарегистрироваться как агент выберите пункт - Как стать агентом"), new MainMenu());
                        }
                    }
                }else if((textMsg.equals("Русский \uD83C\uDDF7\uD83C\uDDFA") || textMsg.equals("O‘zbekcha \uD83C\uDDFA\uD83C\uDDFF")) && this.state == BotState.LANG.getState()){
                    //String lang;
                    if (textMsg.equals("Русский \uD83C\uDDF7\uD83C\uDDFA")){
                        lang = "ru";
                    }else{
                        lang = "uz";
                    }
                    userDAO.updateUser(new UserDTO(message.getFrom().getId(),lang,null,null,null,null,null,null,null,null),user.getUser_id(),"lang");
                    this.state = BotState.COMMON.getState();
                    getLang(lang);
                    String text=langSet.get("Выберите пункт меню");
                    if(user.getStatus()!=2){
                        text = langSet.get("Что бы зарегистрироваться как агент выберите пункт - Как стать агентом");
                    }
                    sendMesg(message,text, new MainMenu());
                }else if (textMsg.equals(langSet.get("Как стать агентом")+" \uD83E\uDDD1\uD83C\uDFFF\u200D\uD83D\uDCBB")){
                    if(user.getStatus()==2){
                        this.state = BotState.COMMON.getState();
                        sendMesg(message,langSet.get("Вы уже зарегистрированны"), new MainMenu());
                    }else {
                        this.state = BotState.PHONE.getState();
                        sendMesg(message,"\uD83D\uDCF1 "+langSet.get("Какой у Вас номер ? Отправьте ваш номер телефона. Что бы отправить номер нажмите на кнопку") +"'\uD83D\uDCF1 "+langSet.get("Отправить мой номер")+"'", new PhoneSendMenu());
                    }
                }else if (textMsg.equals(langSet.get("Отмена"))){
                    this.state = BotState.COMMON.getState();
                    sendMesg(message,langSet.get("Выберите пункт меню"), new MainMenu());
                }else if(textMsg.equals(langSet.get("Начать занаво")+" \uD83D\uDCCB")){
                    this.state = BotState.COMMON.getState();
                    sendMesg(message,langSet.get("Выберите пункт меню"), new MainMenu());
                }else if (textMsg.equals(langSet.get("Главное меню")+" \uD83D\uDCCB")){
                    this.state = BotState.COMMON.getState();
                    sendMesg(message,langSet.get("Выберите пункт меню"), new MainMenu());
                }else if (textMsg.equals(langSet.get("Настройки")+" \uD83D\uDEE0")){
                    this.state = BotState.SETTINGS.getState();
                    sendMesg(message,langSet.get("Выберите пункт меню"), new SetingMenu());
                }else if (textMsg.equals(langSet.get("Выбор языка")+" \uD83C\uDDF7\uD83C\uDDFA\uD83C\uDDFA\uD83C\uDDFF")){//
                    this.state = BotState.SETTING_LANG.getState();
                    sendMesg(message,langSet.get("Выберите язык"),new LangMenu());
                }else if(textMsg.equals(langSet.get("Выберите регион")+" \uD83C\uDF0F")){
                    this.state = BotState.SETTING_REGION.getState();
                    RegionDAOImpl region = new RegionDAOImpl();
                    RegionDTO regionById =region.selectById(user.getRegion_id());
                    sendTextMesg(message,langSet.get("Текущий регион - ")+regionById.getReg(lang));
                    sendInlineKeybordWithRegions(message);
                }else if(textMsg.equals(langSet.get("Информация")+" \uD83D\uDCBE")){
                    InfoDAOImpl info = new InfoDAOImpl();
                    InfoDTO infoDto = info.selectById(1);
                    sendMesg(message,infoDto.getInfoText(lang), new MainMenu());
                }else if(textMsg.equals(langSet.get("Акции")+" \uD83D\uDCC4")){
                    this.state = BotState.EVENTS.getState();
                    sendInlineKeybordWithEvents(message);
                }else if(textMsg.equals(langSet.get("Изменить номер")+" \uD83D\uDCF1")){
                    if(user.getMsisdn()!=null){
                        this.state = BotState.SETTING_PHONE.getState();
                        sendMesg(message,langSet.get("Введите номер телефона в формате (99899 999 99 99)"), new SetingMenu());
                        sendTextMesg(message,langSet.get("Текущий номер - ")+user.getMsisdn());
                    }else{
                        sendMesg(message,langSet.get("Перед тем как изменить номер нужно зарегистрироваться"), new SetingMenu());
                    }
                }else if(textMsg.equals(langSet.get("Агентам Пайнет")+" \uD83D\uDDA5")){
                    this.state = BotState.AGENTINFO.getState();
                    sendInlineKeybordWithinfo(message);
                }else if(textMsg.equals(langSet.get("Оставить отзыв")+" \uD83D\uDCDE")){
                    this.state = BotState.FEEDBACK.getState();
                    sendMesg(message,langSet.get("Вы можите отправить текстовое сообщение до 1000 знаков"), new MainMenu());
                }else if(this.state == BotState.CODE.getState()) {
                    if(user.getActivation_code().equals(textMsg)){
                        userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,1,null,null,null,null,null,null,null),user.getUser_id(),"status");
                        this.state = BotState.REGION.getState();
                        sendInlineKeybordWithRegions(message);
                    }else {
                        sendMesg(message,langSet.get("Введенный код не верен. Попробуйте еще раз либо начните заново."), new ToMainMenu());
                        this.state = BotState.CODE.getState();
                    }
                }else if(this.state == BotState.NAME.getState()){
                    this.state = BotState.PASSPORT.getState();
                    userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,null,null,null,null,textMsg,null,null,null),user.getUser_id(),"agent_name");
                    sendMesg(message,langSet.get("Прикрепите паспорт и удостоверение"), new ToMainMenu());
                }else if(this.state == BotState.FEEDBACK.getState()){
                    FeedbackDAOImpl feed = new FeedbackDAOImpl();
                    feed.insertFeedback(new FeedbackDTO(textMsg,message.getFrom().getId(),message.getFrom().getFirstName()));
                    this.state = BotState.COMMON.getState();
                    sendMesg(message,langSet.get("Ваше сообщение отправлено"), new MainMenu());
                }else if(this.state == BotState.SETTING_PHONE.getState()){
                    if(textMsg.replaceAll("[^\\d.]", "").matches("998\\d{9,9}")){
                        userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,null,null,null,textMsg.replaceAll("[^\\d.]", ""),null,null,null,null),user.getUser_id(),"phone");
                        this.state = BotState.SETTINGS.getState();
                        sendMesg(message,langSet.get("Вы изменили номер телефона на:")+textMsg, new SetingMenu());
                    }else{
                        sendMesg(message,langSet.get("Номер телефона должен быть в формате 998XX XXX XX XX"), new SetingMenu());
                    }
                }else if((textMsg.equals("Русский \uD83C\uDDF7\uD83C\uDDFA") || textMsg.equals("O‘zbekcha \uD83C\uDDFA\uD83C\uDDFF")) && this.state == BotState.SETTING_LANG.getState()){
                    if (textMsg.equals("Русский \uD83C\uDDF7\uD83C\uDDFA")){
                        lang = "ru";
                    }else{
                        lang = "uz";
                    }
                    userDAO.updateUser(new UserDTO(message.getFrom().getId(),lang,null,null,null,null,null,null,null,null),user.getUser_id(),"lang");
                    this.state = BotState.SETTINGS.getState();
                    getLang(lang);
                    sendMesg(message,langSet.get("Выберите пункт меню"), new SetingMenu());
                }
            }else{
                if(this.state == BotState.PHONE.getState()){
                    message.getContact().getPhoneNumber();
                    String code = generator.generateRandomString(6).toUpperCase();
                    String msisdn = message.getContact().getPhoneNumber().replaceAll("[^\\d.]", "");
                    userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,null,null,null,message.getContact().getPhoneNumber().replaceAll("[^\\d.]", ""),null,null,null,null),user.getUser_id(),"phone");
                    userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,null,null,null,null,null,code,null,null),user.getUser_id(),"activation_code");
                    userDAO.notificationUser("1", msisdn, "2", "2", "0", "1771",code);
                    sendMesg(message,langSet.get("Введите смс"), new ToMainMenu());
                    this.state = BotState.CODE.getState();
                }else if(this.state == BotState.PASSPORT.getState()){
                    InputStream my_blob= null;
                    if(message.hasPhoto() || message.hasDocument()){
                        this.state = BotState.CERTIFICATE.getState();
                        List<PhotoSize> photos=new ArrayList<>();
                        if (message.hasPhoto()){
                            photos.addAll(update.getMessage().getPhoto());
                        }else{
                            if (update.getMessage().getDocument().getThumb()==null || (!update.getMessage().getDocument().getMimeType().equals("image/png") && !update.getMessage().getDocument().getMimeType().equals("image/jpeg"))){
                                this.state = BotState.PASSPORT.getState();
                                sendMesg(message,langSet.get("Вам необходимо отправить фото пасспорта в формате .jpg или .png"), new ToMainMenu());
                            }
                            photos.add(update.getMessage().getDocument().getThumb()) ;
                        }
                        try {
                            my_blob = (convertFileContentToBlob(downloadFile(getFilePath(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize)).orElse(null)))));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,null,null,null,null,null,null,my_blob,null),user.getUser_id(),"passport");
                        sendMesg(message,langSet.get("Прикрепите удостоверение"), new ToMainMenu());

                    }else{
                        this.state = BotState.PASSPORT.getState();
                        sendMesg(message,langSet.get("Прикрепите паспорт и удостоверение либо перейдите в предыдущий раздел меню"), new ToMainMenu());
                    }
                }else if(this.state == BotState.CERTIFICATE.getState()){
                    InputStream my_blob= null;
                    if(message.hasPhoto() || message.hasDocument()){
                        this.state = BotState.COMMON.getState();
                        List<PhotoSize> photos=new ArrayList<>();
                        if (message.hasPhoto()){
                            photos.addAll(update.getMessage().getPhoto());
                        }else{
                            if (update.getMessage().getDocument().getThumb()==null || (!update.getMessage().getDocument().getMimeType().equals("image/png") && !update.getMessage().getDocument().getMimeType().equals("image/jpeg"))){
                                this.state = BotState.CERTIFICATE.getState();
                                sendMesg(message,langSet.get("Вам необходимо отправить фото удостоверения  в формате .jpg или .png"), new ToMainMenu());
                            }
                            photos.add(update.getMessage().getDocument().getThumb());
                        }
                        try {
                            my_blob = (convertFileContentToBlob(downloadFile(getFilePath(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize)).orElse(null)))));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Date myDate = new Date();
                        java.sql.Date jsqlD =new java.sql.Date(myDate.getTime());
                        userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,null,null,null,null,null,null,null,my_blob),user.getUser_id(),"certificate");
                        userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,2,null,null,null,null,null,null,null),user.getUser_id(),"status");
                        userDAO.updateUser(new UserDTO(message.getFrom().getId(),null,null,jsqlD,null,null,null,null,null,null),user.getUser_id(),"date_of_change");
                        sendMesg(message,langSet.get("Регистрация успешна мы в скором времени свяжемся с Вами"), new MainMenu());
                    }else{
                        this.state = BotState.CERTIFICATE.getState();
                        sendMesg(message,langSet.get("Прикрепите паспорт и удостоверение либо перейдите в предыдущий раздел меню"), new ToMainMenu());
                    }
                }
            }
        }else if(update.hasCallbackQuery() && this.state == BotState.REGION.getState()){
            userDAO.updateUser(new UserDTO(user.getUser_id(),null,null,null,Integer.valueOf(update.getCallbackQuery().getData()),null,null,null,null,null),user.getUser_id(),"region");
            Message msg = update.getCallbackQuery().getMessage();
            this.state = BotState.NAME.getState();

            sendMesg(msg,langSet.get("Введите название агента"), new ToMainMenu());
        }else if(update.hasCallbackQuery() && this.state == BotState.SETTING_REGION.getState()){
            userDAO.updateUser(new UserDTO(user.getUser_id(),null,null,null,Integer.valueOf(update.getCallbackQuery().getData()),null,null,null,null,null),user.getUser_id(),"region");
            Message msg = update.getCallbackQuery().getMessage();
            RegionDAOImpl region = new RegionDAOImpl();
            RegionDTO regionById =region.selectById(Integer.valueOf(update.getCallbackQuery().getData()));
            this.state = BotState.SETTINGS.getState();
            sendMesg(msg,langSet.get("Вы выбрали - ")+regionById.getReg(lang), new SetingMenu());
        }else if(update.hasCallbackQuery() && this.state == BotState.EVENTS.getState()){
            EventDAOImpl eventDto = new EventDAOImpl();
            EventDTO event = eventDto.selectById(Integer.valueOf(update.getCallbackQuery().getData()));
            Message msg = update.getCallbackQuery().getMessage();
            sendMesg(msg,""+event.getText(lang)+"", new EventMenu());
        }else if(update.hasCallbackQuery() && this.state == BotState.AGENTINFO.getState()){
            AgentIntoDAOImpl information = new AgentIntoDAOImpl();
            AgentInfoDTO info = information.getInfoById(Integer.valueOf(update.getCallbackQuery().getData()));
            Message msg = update.getCallbackQuery().getMessage();
            if(Integer.valueOf(update.getCallbackQuery().getData())==3){
                sendImageUploadingAFile(msg.getChatId().toString(),lang);
                sendMesg(msg,langSet.get("Шаблон письма по переходу на уплату НДС"), new InformationMenu());
            }else{
                sendMesg(msg,""+info.getInfoText(lang)+"", new InformationMenu());
            }
        }
    }

    private void sendMesg(Message message, String text, Menu menu){
        SendMessage sendMessage=new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            menu.SetButtons(sendMessage,this.langSet);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendTextMesg(Message message, String text){
        SendMessage sendMessage=new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void sendInlineKeybordWithRegions(Message message){
        RegionDAOImpl regions = new RegionDAOImpl();
        List<RegionDTO> regionList = regions.selectRegions();
        SendMessage sendMessage;
        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList();
        Iterator iterator=regionList.iterator();
        while(iterator.hasNext()){
            RegionDTO region =(RegionDTO) iterator.next();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(region.getReg(lang));
            inlineKeyboardButton.setCallbackData(region.getRegion_id().toString());
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
            keyboardButtonsRow1.add(inlineKeyboardButton);
            rowList.add(keyboardButtonsRow1);
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage = new SendMessage().setChatId(message.getChatId().toString()).setText(langSet.get("Выберите регион")).setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void sendInlineKeybordWithEvents(Message message){
        EventDAOImpl events = new EventDAOImpl();
        List<EventDTO> eventList = events.selectAll();
        SendMessage sendMessage;
        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList();
        Iterator iterator=eventList.iterator();
        while(iterator.hasNext()){
            EventDTO event =(EventDTO) iterator.next();
            try{
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(event.getName(lang));
                inlineKeyboardButton.setCallbackData(event.getEvent_id().toString());
                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
                keyboardButtonsRow1.add(inlineKeyboardButton);
                rowList.add(keyboardButtonsRow1);
            }catch(Exception e){
                e.printStackTrace();
            }


        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage = new SendMessage().setChatId(message.getChatId().toString()).setText(langSet.get("Акции")).setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void sendInlineKeybordWithinfo(Message message){
        AgentIntoDAOImpl information = new AgentIntoDAOImpl();
        List<AgentInfoDTO> informationList = information.getAll();
        SendMessage sendMessage;
        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList();
        Iterator iterator=informationList.iterator();
        while(iterator.hasNext()){
            AgentInfoDTO info =(AgentInfoDTO) iterator.next();
            try{
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(info.getInfo(lang));
                inlineKeyboardButton.setCallbackData(info.getInfo_id().toString());
                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList();
                keyboardButtonsRow1.add(inlineKeyboardButton);
                rowList.add(keyboardButtonsRow1);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage = new SendMessage().setChatId(message.getChatId().toString()).setText(langSet.get("Информация")).setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public static InputStream  convertFileContentToBlob(java.io.File filepath) throws IOException {
        String path = filepath.toString();
        java.io.File file = new java.io.File(path);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        }
        return inputStream;
    }
    public String getFilePath(PhotoSize photo) {
        Objects.requireNonNull(photo);
        if (photo.hasFilePath()) {
            return photo.getFilePath();
        } else {
            GetFile getFileMethod = new GetFile();
            getFileMethod.setFileId(photo.getFileId());
            try {
                File file = execute(getFileMethod);
                return file.getFilePath();
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private void getLang(String l){
        Lang langObj = new Lang();
        HashMap<String,HashMap<String,String>> Langset=langObj.getLang();
        this.langSet=Langset.get(l);
        this.lang = l;
    }
    public void sendImageUploadingAFile(String chatId,String lang) {
        String filename = "nds_"+lang+".docx";
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filename);
        SendDocument sendDocRequest = new SendDocument();
        sendDocRequest.setChatId(chatId);
        sendDocRequest.setDocument(new java.io.File(resource.getFile()));
        try {
            execute(sendDocRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public String getBotUsername() {
        return "paynet_uz_info_bot";
    }

    public String getBotToken() {
        return "1163274674:AAEHDAYDdz1U1zHaVW2AXBnTeRhEsIDiokI";
    }
}