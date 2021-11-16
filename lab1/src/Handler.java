import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

class Handler {
    private static final int dim = 3;
    public static void main(String[] args) throws Exception {

        int[][] matrix = getMatrix();
        int[] result_Valda = ruleValda(matrix);
        double[] result_Hurvitsa = ruleHurvitsa(matrix, 0.2);
        double[] result_Laplasa = ruleLaplasa(matrix);
        int[] result_Bayesa_Laplasa = ruleBayesaLaplasa(matrix, new double[]{0.55, 0.3, 0.15});

        System.out.println("------------------------------------------------------------------");
        System.out.println("|   | B1 | B2 | B3 | Вальда | Гурвіца | Лапласа | Байєса-Лапласа |");
        for (int i = 0; i < dim; i++) {
//            System.out.println("------------------------------------------------------------------");
            System.out.print("|A" + (i+1)+" ");
            for (int j = 0; j < dim; j++) {
                System.out.printf("|%3d ",matrix[i][j]);
            }
            System.out.printf("|  %3d   |  %3.1f   |   %3.1f  |     %3d        |",result_Valda[i], result_Hurvitsa[i], result_Laplasa[i], result_Bayesa_Laplasa[i]);
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------");
    }

    private static int[][] getMatrix() throws FileNotFoundException {
        Scanner input = new Scanner(new BufferedReader(new FileReader("matrix.txt")));
        int[][] matrix = new int[dim][dim];
        while (input.hasNextLine()) {
            for (int i = 0; i < matrix.length; i++) {
                String[] line = input.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        return matrix;
    }

    public static int[] ruleValda(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int[] result = new int[dim];
        int max = 0;
        int current;
        int strategy = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                current = matrix[i][j];
                if (current < min) {
                    min = current;
                }
                result[i] = min;
            }
            if (min > max) {
                max = min;

                strategy = i;
            }
            min = Integer.MAX_VALUE;
        }
        System.out.println("Критерій Вальда: " + max + ". Номер оптимальної стратегії: " + (strategy + 1));
        return result;
    }

    public static double[] ruleHurvitsa(int[][] matrix, double k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        double[] result2 = new double[dim];
        int current;
        int strategy = 0;
        double result = Integer.MIN_VALUE;
        double temp;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                current = matrix[i][j];
                if (current > max) {
                    max = current;
                }
                if (current < min) {
                    min = current;
                }
            }
            temp = k * min + (1 - k) * max;
            result2[i] = temp;
            if (temp > result) {
                result = temp;

                strategy = i;
            }
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
        }
        System.out.println("Критерій Гурвіца: " + (int) result + ". Номер оптимальної стратегії: " + (strategy + 1));
        return result2;
    }

    public static double[] ruleLaplasa(int[][] matrix) {
        double max = Double.MIN_VALUE;
        double[] result = new double[dim];
        int strategy = 0;
        int sum = 0;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                sum += matrix[i][j];
            }
            result[i] = sum/3.0;
            if(result[i] > max) {
                max = result[i];
                strategy = i;
            }
            sum = 0;
        }
        System.out.println("Критерій Лапласа: " + max + ". Номер оптимальної стратегії: " + (strategy + 1));
        return result;
    }

    public static int[] ruleBayesaLaplasa(int[][] matrix, double[] p) {
        int max = Integer.MIN_VALUE;
        int[] result = new int[dim];
        int strategy = 0;
        int sum = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                sum += matrix[i][j]*p[j];
            }
            result[i] = sum;
            if(sum > max) {
                max = sum;
                strategy = i;

            }
            sum = 0;
        }
        System.out.println("Критерій Байєса-Лапласа: " + max + ". Номер оптимальної стратегії: " + (strategy + 1));
        return result;
    }
}
