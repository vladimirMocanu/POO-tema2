
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vladimir
 */
public class Tema2 {

    static BazaDate bazaDate;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException,
            IOException {

        File intrare = new File(args[0]);
        Scanner in = new Scanner(intrare);
        FileWriter out = new FileWriter(intrare + "_out");

        try (PrintWriter printWriter = new PrintWriter(out)) {
            while (in.hasNext()) {

                switch (in.next()) {

                    case "CREATEDB": {
                        String numeBazaDate = in.next();
                        int nrNodes = in.nextInt();
                        int maxCapacitate = in.nextInt();

                        bazaDate = new BazaDate(numeBazaDate, nrNodes,
                                maxCapacitate);

                        break;
                    }
                    case "CREATE": {
                        ArrayList<String> atribute = new ArrayList<>();

                        String numeEntitate = in.next();

                        int RF = in.nextInt();
                        int nrAtribute = in.nextInt();

                        for (int i = 0; i < 2 * nrAtribute; i++) {

                            atribute.add(i, in.next());

                        }

                        bazaDate.creareEntitate(numeEntitate, RF, nrAtribute,
                                atribute);

                        break;
                    }
                    case "INSERT": {
                        ArrayList<String> instanta = new ArrayList<>();

                        String numeEntitate = in.next();
                        String line = in.nextLine();

                        int i = 0;

                        for (String line1 : line.split("\\s")) {
                            instanta.add(i, line1);
                            i++;
                        }

                        instanta.remove(0);

                        bazaDate.inserareInstanta(numeEntitate, instanta);

                        break;
                    }
                    case "SNAPSHOTDB": {
                        bazaDate.snapshotDB(printWriter);

                        break;
                    }
                    case "GET": {
                        String numeEntitate = in.next();
                        String cheiePrimara = in.next();

                        bazaDate.obtinereInstanta(numeEntitate, cheiePrimara,
                                printWriter);

                        break;
                    }
                    case "UPDATE": {
                        ArrayList<String> atribut = new ArrayList<>();
                        ArrayList<String> atributUpdate = new ArrayList<>();

                        String numeEntitate = in.next();
                        String cheiePrimara = in.next();
                        String linie = in.nextLine();

                        int i = 0;
                        int j = 0;
                        int contor = 1;

                        for (String linie1 : linie.split("\\s")) {

                            if (contor % 2 == 0) {

                                atribut.add(i, linie1);

                                contor++;
                                i++;
                            } else {

                                atributUpdate.add(j, linie1);
                                contor++;
                                j++;
                            }

                        }
                        atributUpdate.remove(0);

                        bazaDate.actualizare(numeEntitate, cheiePrimara,
                                atribut, atributUpdate);
                        break;
                    }
                    case "DELETE": {
                        String numeEntitate = in.next();
                        String cheiePrimara = in.next();

                        bazaDate.stergereInstanta(numeEntitate, cheiePrimara,
                                printWriter);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }
}
