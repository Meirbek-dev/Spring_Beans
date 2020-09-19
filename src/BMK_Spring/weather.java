package BMK_Spring;

public class weather {

    int id;
    String day;
    String forecast;
    int temperature;

    public weather() {
        this.id = 0;
        this.day = "";
        this.forecast = "";
        this.temperature = 0;
    }

    public weather(String day, String forecast, int temperature) {
        this.id = 0;
        this.day = day;
        this.forecast = forecast;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getForecast() {
        return forecast;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return String.format("День=%s, Погода=%s, Температура=%d", day, forecast, temperature);
    }
}
