package BMK_Spring;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            WeatherDAO weatherDAO = (WeatherDAO) context.getBean("WeatherDAO"); // Загрузка бина доступа к таблице прогноза погоды 

            weatherDAO.deleteAll(); // Удаление всех записей

            weather weather = new weather("Понедельник", "Пасмурно, небольшой дождь", 26); // Создание нового объекта таблицы прогноза погоды 
            weatherDAO.insert(weather); // Вставить новый объект (запись) в таблицу прогноза погоды

            // Вставить новый объект (запись) в таблицу прогноза погоды
            weatherDAO.insert(new weather("Вторник", "Переменная облачность, небольшой дождь", 16)); // Вставить новый объект (запись) в таблицу прогноза погоды
            weatherDAO.insert(new weather("Среда", "Пасмурно, небольшой дождь", 13));
            weatherDAO.insert(new weather("Четверг", "Пасмурно, дождь", 11));
            weatherDAO.insert(new weather("Пятница", "Облачно, небольшой дождь", 10));
            weatherDAO.insert(new weather("Суббота", "Переменная облачность", 10));
            weatherDAO.insert(new weather("Воскресенье", "Пасмурно", 11));

            System.out.println("\nИзначальное состояние БД:");

            List<weather> list = weatherDAO.selectAll();
            for (weather myWeather : list) {
                System.out.println(myWeather.getDay() + " " + myWeather.getForecast() + " " + myWeather.getTemperature() + "°C");
            }

            System.out.println("\nВывод записи, в которой температура равна 16");
            weather weather1 = weatherDAO.findByTemperature(16); // Поиск записи по температуре
            System.out.println(weather1 != null ? weather1 : "Нет данных"); // Вывод на экран найденной записи

            weatherDAO.deleteByForecast("Обла");
            weatherDAO.delete("Четверг", "Пасмурно, дождь");
            
            System.out.println("\nВывод записи, содержащей фрагмент дня 'Пон'");
            List<weather> Weather = weatherDAO.findByDay("Пон"); // Поиск записей по фрагменту дня
            System.out.println(Weather != null ? Weather : "Нет данных");

            System.out.println("\nИзменение значения погоды в таблице с 'Пасмурно, небольшой дождь' на 'Переменная облачность'");
            weatherDAO.update("Пасмурно, небольшой дождь", "Переменная облачность");
            System.out.println("\nУдаление записей по фрагменту погоды 'Обла'");
            
            System.out.println("\nУдаление записей по дню 'Четверг' и погоде 'Пасмурно, дождь'");
            
            
            System.out.println("\nКонечное состояние БД:");

            List<weather> list1 = weatherDAO.selectAll();
            for (weather myWeather : list1) {
                System.out.println(myWeather.getDay() + " " + myWeather.getForecast() + " " + myWeather.getTemperature() + "°C");
            }

            System.out.println("\nВывод записей с днём 'Воскресенье' и погодой 'Пасмурно':");
            list = weatherDAO.select("Воскресенье", "Пасмурно");
            for (weather myWeather : list) {
                System.out.println(myWeather.getDay() + " " + myWeather.getForecast());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
}


/*
INSERT INTO weather (`day`, `forecast`, `temperature`) VALUES ("Понедельник", "Ясно", "26");
INSERT INTO weather (`day`, `forecast`, `temperature`) VALUES ("Вторник", "Переменная облачность, небольшой дождь", "16");
INSERT INTO weather (`day`, `forecast`, `temperature`) VALUES ("Среда", "Пасмурно, небольшой дождь", "13");
INSERT INTO weather (`day`, `forecast`, `temperature`) VALUES ("Четверг", "Пасмурно, дождь", "11");
INSERT INTO weather (`day`, `forecast`, `temperature`) VALUES ("Пятница", "Облачно, небольшой дождь", "10");
INSERT INTO weather (`day`, `forecast`, `temperature`) VALUES ("Суббота", "Переменная облачность", "10");
INSERT INTO weather (`day`, `forecast`, `temperature`) VALUES ("Воскресенье", "Пасмурно", "11");
 */
