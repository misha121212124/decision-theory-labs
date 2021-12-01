import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class Main {

    public static int n = 6;
    public static String[] names = {"Acer", "Asus", "Apple", "HP", "Lenovo"};
    public static String[] param = {"Екран", "Процесор", "RAM", "Memory", "Відеокарта", "Ціна"};

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("data.txt")));

        double[][] data = new double[n][n];
        while (sc.hasNextLine()) {
            for (int i = 0; i < data.length; i++) {
                String[] line = sc.nextLine().trim().split("\t");
                for (int j = 0; j < line.length; j++) {
                    data[i][j] = Double.parseDouble(line[j]);
                }
            }
        }


        // виведення вхідних даних
        System.out.printf("%11s %-11s %-11s %-11s %-11s %-11s %-11s\n",
                "", "Ваги", names[0], names[1], names[2], names[3], names[4]);
        for (int i = 0; i < n; i++) {
            System.out.printf("%-10s", param[i]);
            for (int j = 0; j < n; j++) {
                System.out.printf("\t%.2f \t", data[i][j]);
            }
            System.out.println();
        }

        // обрахунок суми критеріїв, вомножених на відповідний ваговий коефіцієнт
        double[] value = new double[data.length];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                value[j] += data[i][0] * data[i][j];
            }
        }
        System.out.printf("%20s", "");
        for (int i = 1; i < value.length; i++) {
            System.out.printf("\t%.3f \t", value[i]);
        }

        // пошук оптимального
        System.out.println();
        double max = 0;
        int max_i = 0;
        for (int i = 1; i < value.length; i++) {
            if (value[i] > max) {
                max = value[i];
                max_i = i;
            }
        }
        System.out.println("Найкращу оцінку отримав ноутбук " + names[max_i - 1] + ": " + max);
    }
}