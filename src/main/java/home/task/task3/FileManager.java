package home.task.task3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileManager {
    private final File file;

    public FileManager(File file) {
        this.file = file;
    }

    public void save(List<String> lines, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, append))) {
            if (!lines.isEmpty()) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (FileNotFoundException e) {
            throw new FIleException("Can't find file: " + file.getName(), e);
        } catch (IOException e) {
            throw new FIleException("Can't write in file: " + file.getName(), e);
        }
    }

    public void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new FIleException("Can't find file: " + file.getName(), e);
        } catch (IOException e) {
            throw new FIleException("Can't read from file: " + file.getName(), e);
        }
    }
}
