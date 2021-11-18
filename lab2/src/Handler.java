import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Handler {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("data.txt")));
        int dCount = 14;
        double[] datas = new double[dCount];
        String[] line = sc.nextLine().trim().split(" ");
        for (int j = 0; j < line.length; j++) {
            datas[j] = Double.parseDouble(line[j]);
        }


        String[] names = {"M1", "D1", "P1", "D2", "P2", "M2", "D1", "P1", "D2", "P2", "P3", "P4", "P1", "P2"};

        for (int j = 0; j < dCount; j++) {
            if (j % 5 == 0) System.out.println();
            System.out.print(names[j] + " = " + datas[j] + "\t\t\t");
        }

        System.out.println("\n");
        System.out.println("Варіант  Прибуток (тис. доларів)");

        double A = datas[2] * datas[1] * 5 + datas[4] * datas[3] * 5 - datas[0];
        System.out.println("   А\t\t\t" + A);
        double B = datas[7] * datas[6] * 5 + datas[9] * datas[8] * 5 - datas[5];
        System.out.println("   В\t\t\t" + B);
        double C1 = datas[12] * datas[1] * 4 + datas[13] * datas[3] * 4 - datas[0];
        System.out.println("   *d\t\t\t" + C1);
        double C2 = datas[12] * datas[6] * 4 + datas[13] * datas[8] * 4 - datas[5];
        System.out.println("   *e\t\t\t" + C2);
        double C = Math.max(C1, C2);
        C = datas[10] * C + datas[11] * 0;
        System.out.println("   C\t\t\t" + C);

        System.out.println();
        double result = Math.max(Math.max(A,B),C);
        System.out.println("Найефективніший варіант: " + result + " тис. доларів (Варіант "+
                ((result==A)?"A":(result==B)?"B":"C") +")");
    }
}
