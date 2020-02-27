
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vladimir
 */
public class Instanta implements Comparable<Instanta> {

    LinkedHashMap<String, Atribute> instantaAtribute;//ne intereseaza ordinea
    Atribute cheiePrimara;
    String nume;
    long timeStamp;

    public Instanta(String paramString) {
        nume = paramString;
        instantaAtribute = new LinkedHashMap<>();
        timeStamp = System.nanoTime();

    }

    public void adaugareAtribute(String numeEntitate, String tipAtribut, String valoare) {

        Atribute atribut = new Atribute();
        switch (tipAtribut) {
            case "Integer":
                atribut.intAtribute(tipAtribut, valoare);
                break;
            case "String":
                atribut.stringAtribute(tipAtribut, valoare);
                break;
            case "Float":
                atribut.floatAtribute(tipAtribut, valoare);
                break;
            default:
                break;
        }

        if (cheiePrimara == null) {
            cheiePrimara = atribut;
        }

        instantaAtribute.put(numeEntitate, atribut);
    }

    public void print(PrintWriter printWriter) {
        String str = nume;

        for (Map.Entry<String, Atribute> local : instantaAtribute.entrySet()) {

            str = str + " " + local.getKey() + ":" + local.getValue().toString();
        }

        printWriter.println(str);
    }

    public void actualizare(String atribut, String atributActualizat) {
        Atribute atribut1 = (Atribute) instantaAtribute.get(atribut);
        timeStamp = System.nanoTime();

        atribut1.actualizareAtribut(atributActualizat);
    }

    @Override
    public int compareTo(Instanta instanta) {

        int timpstap = (int) ((Instanta) instanta).timeStamp;
        return (int) (timpstap - this.timeStamp);

    }
}
