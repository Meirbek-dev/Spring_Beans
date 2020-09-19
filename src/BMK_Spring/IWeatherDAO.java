package BMK_Spring;

import javax.sql.DataSource;
import java.util.List;

public interface IWeatherDAO {

    void setDataSource(DataSource ds); // Установка связи с данными

    void insert(weather customer); // Вставка новой записи

    void append(String day, String forecast, int temperature);

    void deleteByForecast(String forecast);

    void delete(String day, String forecast); // Удаление записи с указанным днём

    void deleteAll(); // Удаление всех записей

    void update(String oldForecast, String newForecast);

    weather findByTemperature(int temperature); // Получение записи с заданной температурой

    List<weather> findByDay(String day); // Нахождение записей с заданным днём

    List<weather> select(String day, String forecast); // Выбор записей с заданным днём

    List<weather> selectAll();
}
