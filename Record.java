// On my honor:
//
// - I have not used code obtained from another student,
// or any other unauthorized source, either modified or unmodified.
//
// - All code and documentation used in my program is either my original
// work, or was derived, by me, from the source code provided by the assignment.
//
// - I have not discussed coding details about this project with anyone
// other than my instructor, teaching assistants assigned to this
// course, except via the message board for the course. I understand that I
// may discuss the concepts of this program with other students, and that
// another student may help me debug my program so long as neither of us
// writes anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor letter of this restriction.

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