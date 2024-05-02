package main.java.dio.me.services.persistence;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public interface PersistencePerform<T> {
    T execute(String accountData);
}

class SavePerform implements PersistencePerform<Void> {
    @Override
    public Void execute(String accountData) {
        FilePersistenceService.saveData(accountData);
        return null;
    }
}

class UpdatePerform implements PersistencePerform<Void> {
    @Override
    public Void execute(String accountData) {
        FilePersistenceService.updateData(accountData);
        return null;
    }
}

class LoadPerform implements PersistencePerform<Map<String, Object>> {
    @Override
    public Map<String, Object> execute(String accountData) {
        Map<String, Object> mapData = FilePersistenceService.loadData();
        return mapData;
    }
}

class DeletePerform implements PersistencePerform<Void> {
    @Override
    public Void execute(String accountData) {
        FilePersistenceService.deleteData(accountData);
        return null;
    }
}

