import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите пути к файлам как аргументы!");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        try {
            BufferedReader circleReader = new BufferedReader(new FileReader(circleFilePath));
            String[] circleCenter = circleReader.readLine().trim().split(" ");
            BigDecimal circleX = new BigDecimal(circleCenter[0]);
            BigDecimal circleY = new BigDecimal(circleCenter[1]);
            BigDecimal radius = new BigDecimal(circleReader.readLine().trim());
            circleReader.close();

            BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFilePath));
            List<String> results = new ArrayList<>();

            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] pointCoords = line.trim().split(" ");
                BigDecimal pointX = new BigDecimal(pointCoords[0]);
                BigDecimal pointY = new BigDecimal(pointCoords[1]);

                results.add(determinePosition(circleX, circleY, radius, pointX, pointY));
            }
            pointsReader.close();

            results.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файлов: " + e.getMessage());
        }
    }

    private static String determinePosition(BigDecimal circleX, BigDecimal circleY, BigDecimal radius,
                                            BigDecimal pointX, BigDecimal pointY) {
        BigDecimal dx = pointX.subtract(circleX);
        BigDecimal dy = pointY.subtract(circleY);
        BigDecimal distanceSquared = dx.pow(2).add(dy.pow(2));
        BigDecimal radiusSquared = radius.pow(2);

        int comparison = distanceSquared.compareTo(radiusSquared);
        if (comparison == 0) {
            return "0";
        } else if (comparison < 0) {
            return "1";
        } else {
            return "2";
        }
    }
}