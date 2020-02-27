
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vladimir
 */
public class Noduri {

    ArrayList<Instanta> instanta;

    int index;
    int maxCapacitate;
    int dimensiune;

    public Noduri(int index, int maxCapacitate) {
        this.index = index;
        this.maxCapacitate = maxCapacitate;
        instanta = new ArrayList<>();
    }

    public void inserareInstanta(Instanta instanta1) {
        instanta.add(instanta1);
        Collections.sort(instanta);//sortam arraylist instanta
        dimensiune = instanta.size();
    }

    public void print(PrintWriter printWriter) {
        printWriter.println("Nod" + index);
        //parcurgem arraylist de instante si printam
        instanta.forEach((instanta1) -> {
            instanta1.print(printWriter);
        });
    }

    Instanta obtinereInstanta(String numeInstanta, String numeAtribut) {
        //parcurgem arraylist de instante si vedem daca gasim numele si tipul
        for (Instanta instanta1 : instanta) {
            if ((instanta1.nume.equals(numeInstanta))) {
                if ((instanta1.cheiePrimara.toString().equals(numeAtribut))) {
                    return instanta1;
                }
            }
        }
        return null;
    }

    public void actualizare(String numeEntitate, String cheiePrimara,
            ArrayList<String> atribut, ArrayList<String> atributUpdate) {

        for (Instanta instanta1 : instanta) {
            if ((instanta1.nume.equals(numeEntitate))) {
                if ((instanta1.cheiePrimara.toString().equals(cheiePrimara))) {

                    for (int i = 0; i < atribut.size(); i += 2) {

                        instanta1.timeStamp = System.nanoTime();
                        instanta1.actualizare(atribut.get(i),
                                atributUpdate.get(i));
                    }
                    //sortam din nou 
                    Collections.sort(instanta);
                    return;
                }
            }
        }
    }

    public int delete(String numeEntitate, String cheiePrimara) {

        for (Instanta instanta1 : instanta) {
            if ((instanta1.nume.equals(numeEntitate))) {

                if ((instanta1.cheiePrimara.toString().equals(cheiePrimara))) {

                    instanta.remove(instanta1);
                    return 1;
                }
            }
        }
        return 0;
    }
}
