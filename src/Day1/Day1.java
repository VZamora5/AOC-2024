package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    File inputData = new File("src/Day1/AOC_Day1_input.txt");
    List<Integer> list1 = new ArrayList<Integer>();
    List<Integer> list2 = new ArrayList<Integer>();
    Scanner scanner = new Scanner(inputData);
    HashMap<Pairs, Integer> map = new HashMap<>();

    public Day1() throws FileNotFoundException {
        intakeData();
        sort();
    }

    public void intakeData() {
        while (scanner.hasNextInt()) {
            int key1 = scanner.nextInt();
            int key2 = scanner.nextInt();
            list1.add(key1);
            list2.add(key2);
            scanner.nextLine();
        }

    }

    public Pairs getMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (Integer key : list) {
            if(key < min) {
                min = key;
                index = list.indexOf(key);}
        }
        return new Pairs(min, index);
    }

    private int getDistance(int key1, int key2) {
        return Math.abs(key1 - key2);
    }

    public void sort() {
        while (!list1.isEmpty() && !list2.isEmpty()) {
            Pairs leftMin = getMin(list1);
            Pairs rightMin = getMin(list2);
            list1.remove(leftMin.getRightInt());
            list2.remove(rightMin.getRightInt());
            Pairs mins = new Pairs(leftMin.getLeftInt(), rightMin.getLeftInt());
            map.put(mins,
                    getDistance(leftMin.getLeftInt(), rightMin.getLeftInt()));
        }
    }

    public int getTotalDistance() {
        int total = 0;
        for (Integer distance : map.values()) {
            total += distance;
        }
        return total;
    }

    public int getSimilarityScore() {
        int score = 0;
        for(Pairs n: map.keySet()){
            int key1 = n.getLeftInt();
            int frequency = 0;
            for(Pairs n2: map.keySet()){
                if(n2.rightInt == key1) {
                    frequency++;
                }
            }
            score += key1 * frequency;
        }
        return score;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day1 day1 = new Day1();
        System.out.println(day1.getTotalDistance());
        System.out.println(day1.getSimilarityScore());
    }
}
