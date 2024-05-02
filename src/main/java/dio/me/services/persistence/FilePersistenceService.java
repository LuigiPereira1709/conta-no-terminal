package main.java.dio.me.services.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public final class FilePersistenceService {
    private static final String SOURCE_PATH = "src\\main\\resources\\data\\accounts.json";
    private static final Gson gson = new Gson();

    public static void saveData(String accountData) {
        String newAccountJson = jsonBuilder(accountData, PersistenceOperation.SAVE);
        String jsonString = loadJsonFile();
        jsonString = addAccountToJson(jsonString, newAccountJson);
        writeJsonToFile(jsonString);
    }

    public static Map<String, Object> loadData() {
        String jsonString = loadJsonFile();
        return gson.fromJson(jsonString, Map.class);
    }


    public static void updateData(String accountData) {
        String modifiedAccountBuilder = jsonBuilder(accountData, PersistenceOperation.UPDATE);
        JsonObject modifiedAccount = JsonParser.parseString(modifiedAccountBuilder).getAsJsonObject();

        String jsonString = loadJsonFile();

        String[] fields = accountData.split(":");
        String accountId = fields[0];

        String modifiedJsonString = updateAccountInJson(jsonString, accountId, modifiedAccount).toString();
        writeJsonToFile(modifiedJsonString);
    }

    public static void deleteData(String accountData) {
        String jsonString = loadJsonFile();

        String[] fields = accountData.split(":");
        String accountId = fields[0];

        String modifiedJsonString = deleteAccountInJson(jsonString, accountId).toString();
        writeJsonToFile(modifiedJsonString);
    }

    private static String deleteAccountInJson(String jsonString, String accountId) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        JsonObject accounts = jsonObject.getAsJsonObject("Accounts");

        if (accounts.has(accountId)) {
            accounts.remove(accountId);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " not found");
        }
        return jsonObject.toString();
    }

    private static String updateAccountInJson(String jsonString, String accountId, JsonObject modifiedAccount) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        JsonObject accounts = jsonObject.getAsJsonObject("Accounts");

        if (accounts.has(accountId)) {
            accounts.add(accountId, modifiedAccount);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " not found");
        }

        return jsonObject.toString();
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

    private static String jsonBuilder(String pattern, PersistenceOperation operation) {
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

        Map<String, Object> accountMap = new HashMap<>();
        switch (operation) {
            case SAVE -> {
                accountMap.put(accountId, account);
                break;
            }
            case UPDATE -> {
                accountMap.putAll(account);
                break;
            }
        }

        return gson.toJson(accountMap);
    }
}
