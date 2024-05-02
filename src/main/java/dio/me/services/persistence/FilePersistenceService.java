package main.java.dio.me.services.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class FilePersistenceService {
    private static final String FILE_PATH = "src\\main\\resources\\data\\accounts.json";
    private static final Gson gson = new Gson();

    /**
     * Saves account data to the JSON file.
     *
     * @param accountData The account data to be saved.
     */
    protected static void saveData(String accountData) {
        String newAccountJson = buildAccountJson(accountData, PersistenceOperation.SAVE);
        String jsonString = loadJsonFile();
        jsonString = addAccountToJson(jsonString, newAccountJson);
        writeJsonToFile(jsonString);
    }

    /**
     * Loads account data from the JSON file.
     *
     * @return A map containing the loaded account data.
     */
    @SuppressWarnings("rawtypes")
    protected static Map loadData() {
        String jsonString = loadJsonFile();
        return gson.fromJson(jsonString, Map.class);
    }

    /**
     * Updates account data in the JSON file.
     *
     * @param accountData The account data to be updated.
     */
    protected static void updateData(String accountData) {
        String modifiedAccountBuilder = buildAccountJson(accountData, PersistenceOperation.UPDATE);
        JsonObject modifiedAccount = JsonParser.parseString(modifiedAccountBuilder).getAsJsonObject();

        String jsonString = loadJsonFile();

        String[] fields = accountData.split(":");
        String accountId = fields[0];

        String modifiedJsonString = updateAccountInJson(jsonString, accountId, modifiedAccount);
        writeJsonToFile(modifiedJsonString);
    }

    /**
     * Deletes account data from the JSON file.
     *
     * @param accountData The account data to be deleted.
     */
    protected static void deleteData(String accountData) {
        String jsonString = loadJsonFile();

        String[] fields = accountData.split(":");
        String accountId = fields[0];

        String modifiedJsonString = deleteAccountInJson(jsonString, accountId);
        writeJsonToFile(modifiedJsonString);
    }


    /**
     * Delete an account from the JSON file.
     *
     * @param jsonString The JSON string representing the file contents.
     * @param accountId  The ID of the account to be deleted.
     * @return The updated JSON string after removing the account.
     * @throws IllegalArgumentException If the account with the given ID is not found.
     */
    private static String deleteAccountInJson(String jsonString, String accountId) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject accounts = jsonObject.getAsJsonObject("Accounts");

        if (!accounts.has(accountId)) {
            throw new IllegalArgumentException("Account with ID " + accountId + " not found");
        }

        accounts.remove(accountId);
        return jsonObject.toString();
    }

    /**
     * Update an account in the JSON file with new account data.
     *
     * @param jsonString      The JSON string representing the file contents.
     * @param accountId       The ID of the account to be updated.
     * @param modifiedAccount The JSON object representing the modified account data.
     * @return The updated JSON string after updating the account.
     * @throws IllegalArgumentException If the account with the given ID is not found.
     */
    private static String updateAccountInJson(String jsonString, String accountId, JsonObject modifiedAccount) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject accounts = jsonObject.getAsJsonObject("Accounts");

        if (!accounts.has(accountId)) {
            throw new IllegalArgumentException("Account with ID " + accountId + " not found");
        }

        accounts.add(accountId, modifiedAccount);
        return jsonObject.toString();
    }

    /**
     * Write a JSON string to the file.
     *
     * @param jsonString The JSON string to be written to the file.
     * @throws RuntimeException If an I/O error occurs.
     */
    private static void writeJsonToFile(String jsonString) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(jsonString);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load the contents of the JSON file as a string.
     *
     * @return The JSON string representing the file contents.
     * @throws RuntimeException If an I/O error occurs.
     */
    private static String loadJsonFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder jsonFile = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonFile.append(line);
            }
            return jsonFile.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add a new account to the existing JSON data.
     *
     * @param originalJson   The original JSON data.
     * @param newAccountJson The JSON string representing the new account data.
     * @return The updated JSON string after adding the new account.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static String addAccountToJson(String originalJson, String newAccountJson) {
        Map jsonMap = gson.fromJson(originalJson, Map.class);
        Map<String, Object> accounts = (Map<String, Object>) jsonMap.computeIfAbsent("Accounts", k -> new JsonObject());
        Map<String, Object> newAccountMap = gson.fromJson(newAccountJson, Map.class);
        accounts.putAll(newAccountMap);
        return gson.toJson(jsonMap);
    }

    /**
     * Build a JSON string representing account data based on the given pattern and operation.
     *
     * @param pattern   The pattern containing account data.
     * @param operation The persistence operation (SAVE or UPDATE).
     * @return The JSON string representing the account data.
     */
    private static String buildAccountJson(String pattern, PersistenceOperation operation) {
        String[] parts = pattern.split(":");

        String accountId = parts[0];
        String agency = parts[1];
        String clientFirstName = parts[2];
        String clientLastName = parts[3];
        String balance = parts[4];

        Map<String, Object> account = new HashMap<>(Map.of(
                "FullName", clientFirstName + " " + clientLastName,
                "Agency", agency,
                "Balance", balance
        ));

        HashMap<String, Object> accountMap = new HashMap<>();

        if (operation == PersistenceOperation.SAVE) {
            accountMap.put(accountId, account);
        } else if (operation == PersistenceOperation.UPDATE) {
            accountMap.putAll(account);
        }

        return gson.toJson(accountMap);
    }
}