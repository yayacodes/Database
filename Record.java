public class Record{
    private Integer population;
    private Integer economy;
    private String name;

    public Record(Integer population, Integer economy, String name){
        this.population = population;
        this.economy = economy;
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public Integer getEconomy() {
        return economy;
    }

    public String getName() {
        return name;
    }
}
