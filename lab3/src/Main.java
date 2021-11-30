import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("data.txt")));
        int n = 6;
        String[][] datas = new String[n][2];
        while (sc.hasNextLine()) {
            for (int i = 0; i < datas.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    datas[i][j] = line[j];
                }
            }
        }

        int[] voices = new int[6];
        String[] chain = new String[6];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                if (j != 1)
                    voices[i] = Integer.parseInt(datas[i][j]);
                else {
                    chain[i] = datas[i][j].trim();
                }
            }
        }
        System.out.println("Число виборців: ");
        for (int i = 0; i < voices.length; i++) {
            System.out.print(voices[i] + " ");
        }
        System.out.println();

        System.out.println("Переваги: ");
        for (int i = 0; i < n; i++) {
            System.out.print(chain[i] + " ");
        }
        System.out.println();

        Condorse(voices, chain);
        Borde(voices, chain);
    }

    static void Condorse(int[] count, String[] candidate) {
        int[] sums = new int[count.length];
        for (int i = 0; i < candidate.length; i++) {

            char[] candidatePriority = candidate[i].toCharArray();
            char first = candidatePriority[0];
            char second = candidatePriority[1];
//            System.out.println(first + " " + second);
            for (int j = 0; j < candidate.length; j++) {
                if (candidate[j].indexOf(""+first)<candidate[j].indexOf(""+second)) sums[i] += count[j];
            }
        }

        for (int i = 0; i < sums.length; i++) {
            System.out.print(sums[i] + "  ");
        }
        int maxCount = 0;
        int ind = 0;

        for (int i = 0; i < sums.length; i++) {
            if (maxCount <= sums[i]) {
                maxCount = sums[i];
                ind = i;
            }
        }
        System.out.println();
        System.out.println("Переміг кандитат з перевагами " + candidate[ind] + " із кількістю голосів: " + maxCount);
    }

    static void Borde(int[] count, String[] candidate) {
        Map<Character, Integer> sums = new HashMap<>();
        candidate[0].chars().mapToObj(c -> (Character) (char)c).forEach(v -> sums.put(v, 0));
        for (int i = 0; i < candidate.length; i++){
            char[] candidatePriority = candidate[i].toCharArray();
            for (int j = 0; j < candidatePriority.length; j++){
                sums.put(candidatePriority[j],sums.get(candidatePriority[j])+count[i] * (3-j));
            }
        }
        System.out.println("Голоси кандидати A: " + sums.get('A') );
        System.out.println("Голоси кандидати B: " + sums.get('B') );
        System.out.println("Голоси кандидати C: " + sums.get('C') );

        Map.Entry<Character, Integer> winner = Collections.max(sums.entrySet(), Map.Entry.comparingByValue());

        System.out.println("Переміг кандитат "+winner.getKey()+" з кількістю голосів: " + winner.getValue());

    }
}




