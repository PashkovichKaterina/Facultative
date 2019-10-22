package by.trjava.pashkovich.facultative.util;

public class ReplacingEscapedCharacter {
    public static String replace(String text) {
        return text
                .replaceAll("%", "%25")   // Процент
                .replaceAll(" ", "%20")   // Пробел
                .replaceAll("\t", "%20")  // Табуляция (заменяем на пробел)
                .replaceAll("\n", "%20")  // Переход строки (заменяем на пробел)
                .replaceAll("\r", "%20")  // Возврат каретки (заменяем на пробел)
                .replaceAll("!", "%21")   // Восклицательный знак
                .replaceAll("\"", "%22")  // Двойная кавычка
                .replaceAll("#", "%23")   // Октоторп, решетка
                .replaceAll("\\$", "%24") // Знак доллара
                .replaceAll("&", "%26")   // Амперсанд
                .replaceAll("'", "%27")   // Одиночная кавычка
                .replaceAll("\\(", "%28") // Открывающаяся скобка
                .replaceAll("\\)", "%29") // Закрывающаяся скобка
                .replaceAll("\\*", "%2a") // Звездочка
                .replaceAll("\\+", "%2b") // Знак плюс
                .replaceAll(",", "%2c")   // Запятая
                .replaceAll("-", "%2d")   // Дефис
                .replaceAll("\\.", "%2e") // Точка
                .replaceAll("/", "%2f")   // Слеш, косая черта
                .replaceAll(":", "%3a")   // Двоеточие
                .replaceAll(";", "%3b")   // Точка с запятой
                .replaceAll("<", "%3c")   // Открывающаяся угловая скобка
                .replaceAll("=", "%3d")   // Знак равно
                .replaceAll(">", "%3e")   // Закрывающаяся угловая скобка
                .replaceAll("\\?", "%3f") // Вопросительный знак
                .replaceAll("@", "%40")   // At sign, по цене, собачка
                .replaceAll("\\[", "%5b") // Открывающаяся квадратная скобка
                .replaceAll("\\\\", "%5c") // Одиночный обратный слеш '\'
                .replaceAll("\\]", "%5d") // Закрывающаяся квадратная скобка
                .replaceAll("\\^", "%5e") // Циркумфлекс
                .replaceAll("_", "%5f")   // Нижнее подчеркивание
                .replaceAll("`", "%60")   // Гравис
                .replaceAll("\\{", "%7b") // Открывающаяся фигурная скобка
                .replaceAll("\\|", "%7c") // Вертикальная черта
                .replaceAll("\\}", "%7d") // Закрывающаяся фигурная скобка
                .replaceAll("~", "%7e");  // Тильда
    }
}
