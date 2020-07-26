package encryptdecrypt;//package encryptdecrypt;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static <outputFlag> void main(String[] args) throws FileNotFoundException {
        int key = 0;
        String s = "", t = "-";
        File input;
        input = null;
        File output = null;
        boolean outputFlag = false;
        boolean shift = true;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-key")) {
                key = Integer.parseInt(args[i + 1]);
            }
            if (args[i].equals("-data")) {
                s = args[i + 1];
            }
            if (args[i].equals("-mode")) {
                t = args[i + 1];
            }

            if (args[i].equals("-alg")) {
                shift = args[i + 1].equals("shift");
            }


            if (args[i].equals("-in")) input = new File(args[i + 1]);
            if (args[i].equals("-out")) {
                output = new File(args[i + 1]);
                outputFlag = true;
            }
        }
        StringBuilder res = new StringBuilder();
        if (input != null) {
            Scanner scanner = new Scanner(input);
            s = scanner.nextLine();
        }

        if (shift) {
            StringBuilder result = new StringBuilder();
            for (char character : s.toString().toCharArray()) {
                System.out.println("char = " + character);

                if ('A' <= character && character <= 'Z') {
                    int originalAlphabetPosition = character - 'A';
                    int newAlphabetPosition;
                    if (t.equals("enc")) {
                         newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                    } else {
                         newAlphabetPosition = ( 26 - key + originalAlphabetPosition ) % 26;
                    }
                    char newCharacter = (char) ('A' + newAlphabetPosition);
                    result.append(newCharacter);
                } else if ('a' <= character && character <= 'z') {
                    int originalAlphabetPosition = character - 'a';
                    int newAlphabetPosition;
                    if (t.equals("enc")) {
                        newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                    } else {
                        newAlphabetPosition = ( 26 - key + originalAlphabetPosition ) % 26;
                    }
                    char newCharacter = (char) ('a' + newAlphabetPosition);
                    result.append(newCharacter);
                } else {
                    result.append(character);
                }
            }
            System.out.println("&&&" + result);
            res = result;
        } else {
            switch (t) {
                case "enc":
                    for (int i = 0; i < s.length(); i++) {
                        res.append((char) ((s.charAt(i) + key)));
                    }

                    break;
                case "dec":
                    for (int i = 0; i < s.length(); i++) {
                        res.append((char) ((s.charAt(i) - key)));
                    }
                    break;
            }
        }

        if (outputFlag) {
            try (FileWriter writer = new FileWriter(output)) {
                writer.write(res.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(res);
        }
    }
}
