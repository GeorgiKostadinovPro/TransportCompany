package org.example.util;

import org.example.dto.cargo.CargoDto;

import java.io.*;

public class FileService {
    private static final String CARGO_FILE = "cargoTracker/";

    public FileService() {
        File folder = new File(CARGO_FILE);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void saveCargoAsFile(CargoDto cargo) {
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                String fileName = CARGO_FILE + "cargo_" + cargo.getId() + ".txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    writer.write(cargo.toString());
                }
                System.out.println("Cargo " + cargo.getId() + " saved successfully!");
            } catch (Exception e) {
                System.err.println("Error saving cargo in background: " + e.getMessage());
            }
        }).start();
    }

    public String readCargoFromFile(long id) {
        String fileName = CARGO_FILE + "cargo_" + id + ".txt";
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException(String.format("Error reading cargo from file: %s", e.getMessage()));
        }

        return content.toString();
    }
}
