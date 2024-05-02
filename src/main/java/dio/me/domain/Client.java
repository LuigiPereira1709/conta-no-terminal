package main.java.dio.me.domain;

public class Client {
    private String firstName;
    private String lastName;

    /**
     * Constructs a new Client instance with the specified first name and last name.
     *
     * @param firstName The first name of the client.
     * @param lastName  The last name of the client.
     */
    private Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static final class ClientBuilder {
        private String firstName;
        private String lastName;

        private ClientBuilder() {
        }

        public static ClientBuilder aClient() {
            return new ClientBuilder();
        }

        public ClientBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ClientBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Builds a new Client instance with the provided first name and last name.
         *
         * @return A new Client instance.
         */
        public Client build() {
            return new Client(firstName, lastName);
        }
    }
}
