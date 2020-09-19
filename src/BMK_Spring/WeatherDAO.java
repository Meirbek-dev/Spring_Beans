package BMK_Spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.util.List;

public class WeatherDAO implements IWeatherDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(weather customer) { // Реализация вставки новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO WEATHER (DAY, FORECAST, TEMPERATURE) VALUES(?,?,?)",
                new Object[]{customer.getDay(), customer.getForecast(), customer.getTemperature()});
    }

    @Override
    public void append(String day, String forecast, int temperature) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO WEATHER (DAY, FORECAST, TEMPERATURE) VALUES(?,?,?)", new Object[]{day, forecast, temperature});
    }

    @Override
    public void deleteByForecast(String forecast) {  // Реализация удаления записей по прогнозу
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM WEATHER WHERE FORECAST LIKE ?", new Object[]{'%' + forecast + '%'});
    }

    @Override

    public void delete(final String day, final String forecast) {  // Реализация удаления записей с указанным днём и прогнозом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from WEATHER where DAY = ? AND FORECAST = ?", new Object[]{day, forecast});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from WEATHER");
    }

    @Override
    public void update(String oldForecast, String newForecast) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE WEATHER SET FORECAST = ? WHERE FORECAST = ?", new Object[]{newForecast, oldForecast});
    }

    @Override
    public weather findByTemperature(int temperature) { // Реализация поиска записи с заданной температурой
        JdbcTemplate select = new JdbcTemplate(dataSource);
        List<weather> weather = select.query("SELECT * FROM WEATHER WHERE TEMPERATURE = ?",
                new Object[]{temperature}, new WeatherRowMapper());
        return weather.size() > 0 ? weather.get(0) : null;
    }

    @Override
    public List<weather> findByDay(String forecast) {  // Реализация поиска записей по дню
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM WEATHER WHERE DAY LIKE ?";
        List<weather> Forecast = select.query(sql, new Object[]{'%' + forecast + '%'}, new WeatherRowMapper());
        return Forecast;
    }

    @Override
    public List<weather> select(String day, String forecast) {  // Реализация получения записей с заданным днём и прогнозом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from WEATHER where DAY = ? AND FORECAST= ?",
                new Object[]{day, forecast}, new WeatherRowMapper());
    }

    @Override
    public List<weather> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from WEATHER", new WeatherRowMapper());
    }
}
