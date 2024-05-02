package main.java.dio.me.services.persistence;

public final class FilePersistenceOperationFactory {

    /**
     * Creates a persistence operation object based on the specified operation type.
     *
     * @param operation The type of persistence operation.
     * @return An instance of PersistencePerform representing the specified operation.
     * @throws IllegalArgumentException if the specified operation is unsupported.
     */
    @SuppressWarnings("rawtypes")
    public static PersistencePerform createOperation(PersistenceOperation operation) {
        return switch (operation) {
            case SAVE -> new SavePerform();
            case UPDATE -> new UpdatePerform();
            case LOAD -> new LoadPerform();
            case DELETE -> new DeletePerform();
        };
    }
}
