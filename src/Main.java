import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //..... -> .=Leer
        String input = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bereits gefundene Buchstaben: .....");
        input = scanner.nextLine().toLowerCase();

        ArrayList<String> orangeAndGreen = new ArrayList<>();
        System.out.println("Orange und Grüne Buchstaben(BSP:AHED: ");
        String oAGInput = scanner.nextLine().toLowerCase();
        String[] oAInputArr = oAGInput.split("");
        for (String buchstabe : oAInputArr) {
            if (!orangeAndGreen.contains(buchstabe)) {
                orangeAndGreen.add(buchstabe);
            }
        }
        System.out.println(orangeAndGreen);
        ArrayList<String> grau = new ArrayList<>();
        System.out.println("Graue Buchstaben: ");
        String grauInput = scanner.nextLine().toLowerCase();
        String[] grauuInputArr = grauInput.split("");
        for (String buchstabe : grauuInputArr) {
            if (!grau.contains(buchstabe)) {
                grau.add(buchstabe);
            }
        }
        System.out.println(grau);
        try {
            ArrayList<String> five = new ArrayList<>();
            ArrayList<String> finished = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\leoli\\wordleSolving\\src\\words.txt"));
            lines = lines.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            outerloop:
            for( String wort: lines){
                //länge 5
                if (wort.length()==5){
                    five.add(wort);
                    System.out.println(five.size());
                    //graucheck
                    for (String buchstabe: grau){
                        //grauer buchstabe enthalten
                        if (wort.contains(buchstabe)){
                            continue outerloop;
                        }
                    }
                    //ab hier kein grauer buchstabe drin
                    //orangeAndGreenCheck enthalten
                    for (String buchstabe: orangeAndGreen){
                        //falls Buchstabe nicht enthalten
                        if (!wort.contains(buchstabe)){
                            continue outerloop;
                        }
                    }
                    //ab hier auch orangene und Grüne enthalten
                    //PlacementCheck
                    String[] inputArr = input.split("");
                    String[] wortArr = wort.split("");
                    boolean check = true;
                    for (int i = 0; i< inputArr.length; i++){
                        if (!inputArr[i].equals(".")){
                            if (!inputArr[i].equals(wortArr[i])){
                                check = false;
                                break;
                            }
                        }
                    }
                    //placement auch richtig
                    if (check){
                        finished.add(wort);
                    }
                }
            }
            //output = finished ohne doppelte!
            ArrayList<String> output = new ArrayList<>();
            for (String item: finished){
                if (!output.contains(item)){
                    output.add(item);
                }
            }
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}