/* lab0 实现一个简易的cat命令 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main
{    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage:\n\tjava Main file_path -> miss file_path");
        }

        String file_name = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException exception) {
            System.out.println("Error reading: " + exception.getMessage());
        }
    }
}