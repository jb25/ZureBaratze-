package jonBilbao.landareak.objects;

import java.io.Serializable;

public class Measure implements Serializable {
    private int id;
    private String plant_name;
    private String measure_date;
    private String humididy ;
    private String temperature;

    public Measure(int id, String plant_name, String measure_date, String humididy, String temperature) {
        this.id = id;
        this.plant_name = plant_name;
        this.measure_date = measure_date;
        this.humididy = humididy;
        this.temperature = temperature;
    }

    public String getPlant_Name() {
        return this.plant_name;
    }

    public void setPlant_Name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getMeasureDate() {
        return this.measure_date;
    }

    public void setMeasureDate(String date) {
        this.measure_date = date;
    }

    public String getHumididy() {
        return this.humididy;
    }

    public void setHumidity(String humididy) {
        this.humididy = humididy;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getId() {
        return this.id;
    }
}
