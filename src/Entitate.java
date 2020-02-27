
import java.util.LinkedHashMap;

/**
 *
 * @author Vladimir
 */
public class Entitate {
    
    //daca folosim HashMap nu pastreaza ordinea
    //astfel folosim LinkedHashMap
    LinkedHashMap<String, String> atribute;
    String nume;
    
    int RF;//
    int nrAtribute;

    public Entitate(String nume, int RF, int nrAtribute) {
        atribute = new LinkedHashMap<>();
        this.nume = nume;
        this.RF = RF;
        this.nrAtribute = nrAtribute;
    }

    public void creareAtribute(String numeAtribute, String tipAtribute) {
        atribute.put(numeAtribute, tipAtribute);
    }

}
