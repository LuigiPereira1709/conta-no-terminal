package main.java.dio.me.services.persistence;

import java.util.Map;

/**
 * An interface representing a persistence operation.
 *
 * @param <T> The type of result produced by executing the operation.
 */
public interface PersistencePerform<T> {
    T execute(String accountData);
}

class SavePerform implements PersistencePerform<Void> {
    /**
     * Executes the operation to save account data.
     *
     * @param accountData The account data to be saved.
     * @return null, as the operation does not produce a return value.
     */
    @Override
    public Void execute(String accountData) {
        FilePersistenceService.saveData(accountData);
        return null;
    }
}

class UpdatePerform implements PersistencePerform<Void> {
    /**
     * Executes the operation to update account data.
     *
     * @param accountData The account data to be updated.
     * @return null, as the operation does not produce a return value.
     */
    @Override
    public Void execute(String accountData) {
        FilePersistenceService.updateData(accountData);
        return null;
    }
}

@SuppressWarnings("rawtypes")
class LoadPerform implements PersistencePerform<Map> {
    /**
     * Executes the operation to load account data.
     *
     * @param accountData Unused parameter.
     * @return A map containing the loaded account data.
     */
    @Override
    public Map execute(String accountData) {
        return FilePersistenceService.loadData();
    }
}


class DeletePerform implements PersistencePerform<Void> {
    /**
     * Executes the operation to delete account data.
     *
     * @param accountData The account data to be deleted.
     * @return null, as the operation does not produce a return value.
     */
    @Override
    public Void execute(String accountData) {
        FilePersistenceService.deleteData(accountData);
        return null;
    }
}
