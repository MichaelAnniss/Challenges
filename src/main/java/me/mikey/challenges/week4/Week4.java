package me.mikey.challenges.week4;

import me.mikey.challenges.week4.interpreter.BareBones;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Michael on 25/10/16.
 */
public class Week4 {
    public static void main(String[] args) {
        //warm up vm for more realistic benchmarks
        runResource("multiply.bb", true);
        runResource("multiply.bb", true);

        if(args.length == 0) {
            runResource("multiply.bb", false);
        } else {
            run(args[0], false);
        }
    }

    public static void run(String fileName, boolean silent) {
        try {
            File file = new File(fileName);

            Scanner scanner = new Scanner(file);
            StringBuilder code = new StringBuilder();

            while (scanner.hasNext())
                code.append(scanner.nextLine()).append("\n");

            BareBones.evaluate(code.toString(), silent);

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runResource(String fileName, boolean silent) {
        try(InputStream is = Week4.class.getClassLoader().getResourceAsStream(fileName)) {
            Scanner scanner = new Scanner(is);
            StringBuilder code = new StringBuilder();

            while(scanner.hasNext())
                code.append(scanner.nextLine()).append("\n");

            BareBones.evaluate(code.toString(), silent);
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while trying to open file!");
        }
    }
}
