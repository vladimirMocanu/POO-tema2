
import java.text.DecimalFormat;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vladimir
 */
public class Atribute {

    String valoareString;
    String tip;
    DecimalFormat df;

    int valoareInt;
    float valoareFloat;

    public Atribute() {

    }

    public void intAtribute(String tipAtribut, String valoareInt) {
        this.tip = tipAtribut;
        this.valoareInt = Integer.parseInt(valoareInt);
    }

    public void stringAtribute(String tipAtribut, String valoareString) {
        this.tip = tipAtribut;
        this.valoareString = valoareString;
    }

    public void floatAtribute(String tipAtribut, String valoareFloat) {
        this.tip = tipAtribut;
        this.valoareFloat = Float.parseFloat(valoareFloat);
        df = new DecimalFormat("#.###");
    }

    public void actualizareAtribut(String atributActualizat) {
        switch (tip) {
            case "Integer":
                valoareInt = Integer.parseInt(atributActualizat);
                break;
            case "String":
                valoareString = atributActualizat;
                break;
            case "Float":
                valoareFloat = Float.parseFloat(atributActualizat);
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {

        switch (tip) {
            case "Integer":
                return String.valueOf(valoareInt);
            case "String":
                return valoareString;
            case "Float":
                return df.format(valoareFloat);
            default:
                break;
        }

        return "BUG";
    }

}
