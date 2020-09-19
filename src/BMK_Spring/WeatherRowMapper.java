package BMK_Spring;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

public class WeatherRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }
    class PersonResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            weather weather = new weather();
            weather.setId(rs.getInt(1));
            weather.setDay(rs.getString(2));
            weather.setForecast(rs.getString(3));
            weather.setTemperature(rs.getInt(4));
            return weather;
        }
    }
}
