package lang;

import java.util.HashMap;

public class Lang {
    private HashMap<String,HashMap<String,String>> lang = new HashMap<>();
    private HashMap<String,String> ru = new HashMap<>();
    private HashMap<String,String> uz = new HashMap<>();
    public Lang(){
        lang.put("uz",uz);
        lang.put("ru",ru);
        ru.put("Выберите язык","Выберите язык");
        ru.put("Выберите пункт меню","Выберите пункт меню");
        ru.put("Как стать агентом","Как стать агентом");
        ru.put("Вы уже зарегистрированны","Вы уже зарегистрированны");
        ru.put("Какой у Вас номер ? Отправьте ваш номер телефона. Что бы отправить номер нажмите на кнопку","Какой у Вас номер ? Отправьте ваш номер телефона. Что бы отправить номер нажмите на кнопку");
        ru.put("Отправить мой номер","Отправить мой номер");
        ru.put("Отмена","Отмена");
        ru.put("Начать занаво","Начать заново");
        ru.put("Главное меню","Главное меню");
        ru.put("Настройки","Настройки");
        ru.put("Выбор языка","Выбор языка");
        ru.put("Выберите регион","Выберите регион");
        ru.put("Текущий регион - ","Текущий регион - ");
        ru.put("Информация","Информация");
        ru.put("Акции","Акции");
        ru.put("Изменить номер","Изменить номер");
        ru.put("Введите номер телефона в формате (99899 999 99 99)","Введите номер телефона в формате (99899 999 99 99)");
        ru.put("Текущий номер - ","Текущий номер - ");
        ru.put("Агентам Пайнет","Агентам Пайнет");
        ru.put("Оставить отзыв","Оставить отзыв");
        ru.put("Вы можите отправить текстовое сообщение до 1000 знаков","Вы можите отправить текстовое сообщение до 1000 знаков");
        ru.put("Введенный код не верен. Попробуйте еще раз либо начните заново.","Введенный код не верен. Попробуйте еще раз либо начните заново.");
        ru.put("Прикрепите паспорт и удостоверение","Прикрепите паспорт и удостоверение");
        ru.put("Ваше сообщение отправлено","Ваше сообщение отправлено");
        ru.put("Вы изменили номер телефона на:","Вы изменили номер телефона на:");
        ru.put("Введите смс","Введите смс");
        ru.put("Вам необходимо отправить фото пасспорта в формате .jpg или .png","Вам необходимо отправить фото пасспорта в формате .jpg или .png");
        ru.put("Прикрепите удостоверение","А теперь можите прикрепить удостоверение");
        ru.put("Прикрепите паспорт и удостоверение либо перейдите в предыдущий раздел меню","Прикрепите паспорт и удостоверение либо перейдите в предыдущий раздел меню");
        ru.put("Регистрация успешна мы в скором времени свяжемся с Вами","Регистрация успешна мы в скором времени свяжемся с Вами");
        ru.put("Введите название агента","Введите название агента");
        ru.put("Шаблон письма по переходу на уплату НДС","Шаблон письма по переходу на уплату НДС");
        ru.put("Что бы зарегистрироваться как агент выберите пункт - Как стать агентом","Что бы зарегистрироваться как агент выберите пункт - Как стать агентом \uD83E\uDDD1\uD83C\uDFFF\u200D\uD83D\uDCBB");
        ru.put("Номер телефона должен быть в формате 998XX XXX XX XX","Номер телефона должен быть в формате 998XX XXX XX XX");
        ru.put("Вы выбрали - ","Вы выбрали - ");//
        ru.put("Перед тем как изменить номер нужно зарегистрироваться","Перед тем как изменить номер нужно зарегистрироваться");

        uz.put("Выберите язык","Tilni tanlash");
        uz.put("Выберите пункт меню","Menyu bo`limini tanlang");
        uz.put("Как стать агентом","Agent bo`lish");
        uz.put("Вы уже зарегистрированны","Siz ro'yxatdan o'tgansiz");
        uz.put("Какой у Вас номер ? Отправьте ваш номер телефона. Что бы отправить номер нажмите на кнопку","Telefon raqamingiz ? Telefon raqamingizni yuboring. Raqamni yuborish uchun tugmani bosing.");
        uz.put("Отправить мой номер","Raqamimni yuboring");
        uz.put("Отмена","Bekor qilish");
        uz.put("Начать занаво","Boshidan boshlash");
        uz.put("Главное меню","Bosh menyu");
        uz.put("Настройки","Sozlamalar");
        uz.put("Выбор языка","Tilni tanlash");
        uz.put("Выберите регион","Hududni tanlash");
        uz.put("Текущий регион - ","Hozirgi mintaqa - ");
        uz.put("Информация","PAYNET Kompaniyasi haqida ma`lumot");
        uz.put("Акции","Aksiyalar");
        uz.put("Изменить номер","Raqamni o`zgartirish");
        uz.put("Введите номер телефона в формате (99899 999 99 99)","Telefon raqamingizni formatda kiriting (99899 999 99 99)");
        uz.put("Текущий номер - ","Joriy raqam - ");
        uz.put("Агентам Пайнет","PAYNET Agentlari uchun");
        uz.put("Оставить отзыв","Izoh qoldirish");
        uz.put("Вы можите отправить текстовое сообщение до 1000 знаков","1000 ta belgigacha bo`lgan matnli xabar yuborishingiz mumkin");
        uz.put("Введенный код не верен. Попробуйте еще раз либо начните заново.","Siz kiritgan kod noto`g`ri. Qaytadan urinib ko'ring yoki boshidan boshlang.");
        uz.put("Прикрепите паспорт и удостоверение","Pasportingiz va shaxsingizni tasdiqlovchi guvohnoma nusxasini ilova qiling");
        uz.put("Ваше сообщение отправлено","Yuqoridagi xabar yuborildi");
        uz.put("Вы изменили номер телефона на:","Siz telefon raqamingizni quyidagiga o'zgartirdingiz:");
        uz.put("Введите смс","SMS-ni kiriting");
        uz.put("Вам необходимо отправить фото пасспорта в формате .jpg или .png","Pasportingiz nusxasini .jpg yoki .png formatida yuborishingiz kerak");
        uz.put("Прикрепите удостоверение","Endi guvohnomani biriktirishingiz mumkin");
        uz.put("Прикрепите паспорт и удостоверение либо перейдите в предыдущий раздел меню","Pasport va shaxsingizni tasdiqlovchi hujjat nusxasini ilova qiling yoki oldingi menyu bo`limiga o`ting");
        uz.put("Регистрация успешна мы в скором времени свяжемся с Вами","Ro`yxatdan o'tish muvaffaqiyatli yakunlandi, tez orada biz siz bilan bog`lanamiz");
        uz.put("Введите название агента","Agent nomini kiriting");
        uz.put("Шаблон письма по переходу на уплату НДС","QQSni yuborish bo'yicha xat shablonlari");
        uz.put("Что бы зарегистрироваться как агент выберите пункт - Как стать агентом","Agent sifatida ro'yxatdan o'tish uchun -ni tanlang  - Agent bo`lish \uD83E\uDDD1\uD83C\uDFFF\u200D\uD83D\uDCBB");
        uz.put("Номер телефона должен быть в формате 998XX XXX XX XX","Telefon raqami 998XX XXX XX XX formatda kiritilishi kerak");
        uz.put("Вы выбрали - ","Siz tanlagan - ");
        uz.put("Перед тем как изменить номер нужно зарегистрироваться","Raqamni o'zgartirishdan oldin ro'yxatdan o'tish kerak");
    }


    public HashMap getLang() {
        return lang;
    }

    public void setLang(HashMap lang) {
        this.lang = lang;
    }



}
