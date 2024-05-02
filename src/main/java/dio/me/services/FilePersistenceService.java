package main.java.dio.me.services;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class FilePersistenceService {
    private static final String SOURCE_PATH = "src\\main\\resources\\data\\accounts.json";
    private static final Gson gson = new Gson();

    public static void saveData(String accountData) {
        String newAccountJson = jsonBuilder(accountData);
        String jsonString = loadJsonFile();
        jsonString = addAccountToJson(jsonString, newAccountJson);
        writeJsonToFile(jsonString);
    }

    public static String loadData() {
        return loadJsonFile();
    }

    // modify

    // delete

    private static String loadJsonFile() {
        StringBuilder jsonFile = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(SOURCE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonFile.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonFile.toString();
    }

    private static String addAccountToJson(String originalJson, String newAccountJson) {
        Map<String, Object> jsonMap = gson.fromJson(originalJson, Map.class);

        Map<String, Object> accounts = (Map<String, Object>) jsonMap.get("Accounts");
        Map<String, Object> newAccountMap = gson.fromJson(newAccountJson, Map.class);

        accounts.putAll(newAccountMap);

        return gson.toJson(jsonMap);
    }


    private static void writeJsonToFile(String jsonString) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SOURCE_PATH))) {
            bw.write(jsonString);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String jsonBuilder(String pattern) {
        String[] parts = pattern.split(":");

        String accountId = parts[0];
        String agency = parts[1];
        String clientFirstName = parts[2];
        String clientLastName = parts[3];
        String balance = parts[4];

        Map<String, Object> account = new HashMap<>();
        account.put("FullName", clientFirstName + " " + clientLastName);
        account.put("Agency", agency);
        account.put("Balance", balance);
        account.put("WithdrawalRegister", new HashMap<>());
        account.put("DepositRegister", new HashMap<>());

        Map<String, Object> newAccountMap = new HashMap<>();
        newAccountMap.put(accountId, account);

        return gson.toJson(newAccountMap);
    }

}
