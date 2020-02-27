
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Vladimir
 */
public class BazaDate {

    //folosim LinkedHashMap pentru a pastra ordinea
    // daca folosim HashMap nu este nicio diferenta, in acest caz
    LinkedHashMap<String, Entitate> entitate;
    ArrayList<Noduri> noduri;

    String numeBazaDate;

    int nrNoduri;
    int maxCapacitate;

    public BazaDate(String numeBazaDate, int nrNoduri, int maxCapacitate) {
        this.numeBazaDate = numeBazaDate;
        this.nrNoduri = nrNoduri;
        this.maxCapacitate = maxCapacitate;

        noduri = new ArrayList<>();
        entitate = new LinkedHashMap<>();

        for (int i = 1; i <= nrNoduri; i++) {
            noduri.add(new Noduri(i, maxCapacitate));
        }
    }

    public void creareEntitate(String numeEntitate, int RF, int nrAtribute,
            ArrayList<String> atribute) {

        Entitate entitate1 = new Entitate(numeEntitate, RF, nrAtribute);

        for (int i = 0; i < atribute.size(); i = i + 2) {
            entitate1.creareAtribute(atribute.get(i), atribute.get(i + 1));
        }

        entitate.put(numeEntitate, entitate1);

    }

    public void inserareInstanta(String numeEntitate,
            ArrayList<String> instanta) {

        Instanta instanta1 = new Instanta(numeEntitate);
        Entitate entitate1 = entitate.get(numeEntitate);

        int i = 0;

        for (String numeEntitate2 : entitate1.atribute.keySet()) {
            instanta1.adaugareAtribute(numeEntitate2,
                    entitate1.atribute.get(numeEntitate2), instanta.get(i));
            i++;
        }

        int j = entitate.get(numeEntitate).RF;

        for (Noduri noduri1 : noduri) {
            if (j == 0) {
                break;
            }
            if (noduri1.dimensiune < maxCapacitate) {
                noduri1.inserareInstanta(instanta1);
                j--;
            }
        }

        if (j > 0) {
            for (i = 0; i < j; i++) {
                noduri.add(new Noduri(nrNoduri + i + 1, maxCapacitate));
                noduri.get(nrNoduri + i).inserareInstanta(instanta1);
            }
            nrNoduri = nrNoduri + j;
        }
    }

    public void snapshotDB(PrintWriter printWriter) {
        int i = 0;

        for (Noduri noduri1 : noduri) {
            if (noduri1.dimensiune > 0) {
                noduri1.print(printWriter);
            } else {
                i++;
            }
        }

        if (i == noduri.size()) {
            printWriter.println("EMPTY DB");
        }
    }

    void obtinereInstanta(String numeEntitate, String cheiePrimara,
            PrintWriter printWriter) {

        String afisare = new String();

        Instanta instanta1 = null;
        Instanta instanta2 = null;

        for (Noduri noduri1 : noduri) {
            instanta1 = noduri1.obtinereInstanta(numeEntitate, cheiePrimara);
            if (instanta1 != null) {
                instanta2 = instanta1;
                afisare = afisare + "Nod" + noduri1.index + " ";
            }
        }

        if (instanta2 != null) {
            printWriter.print(afisare);
            instanta2.print(printWriter);
        } else {
            printWriter.println("NO INSTANCE FOUND");
        }
    }

    public void actualizare(String numeEntitate, String cheiePrimara,
            ArrayList<String> atribut, ArrayList<String> atributUpdate) {

        for (Noduri noduri1 : noduri) {
            if (noduri1.dimensiune > 0) {
                noduri1.actualizare(numeEntitate, cheiePrimara,
                        atribut, atributUpdate);
            }
        }
    }

    public void stergereInstanta(String numeEntitate, String cheiePrimara,
            PrintWriter printWriter) {
        int contor = 0;

        for (Noduri noduri1 : noduri) {
            if ((noduri1.dimensiune > 0)) {
                if (noduri1.delete(numeEntitate, cheiePrimara) == 1) {
                    contor++;
                }

            }
        }

        if (contor == 0) {
            printWriter.println("NO INSTANCE TO DELETE");
        }
    }

}
