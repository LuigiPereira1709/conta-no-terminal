package main.java.dio.me.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Account {
    private final Long id;
    private final Set<String> agencies = Set.of("001", "123-4", "9876", "555-0");
    private String agency;
    private final Client client;
    private double balance;
    private final String dateCreation;

    /**
     * Constructs a new Account instance with a random ID and the specified client, agency, and balance.
     * The creation date is set to the current date and time.
     *
     * @param client  The client associated with the account.
     * @param agency  The agency of the account.
     * @param balance The initial balance of the account.
     */
    private Account(Client client, String agency, double balance) {
        this.id = ThreadLocalRandom.current().nextLong(1, 9999);
        this.client = client;
        this.agency = agency;
        this.balance = balance;
        this.dateCreation = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Constructs a new Account instance with the specified ID, client, agency, balance, and creation date.
     *
     * @param id           The ID of the account.
     * @param client       The client associated with the account.
     * @param agency       The agency of the account.
     * @param balance      The initial balance of the account.
     * @param dateCreation The creation date of the account.
     */
    private Account(Long id, Client client, String agency, double balance, String dateCreation) {
        this.id = id;
        this.client = client;
        this.agency = agency;
        this.balance = balance;
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", agency=" + agency +
                ", client=" + client +
                ", balance=" + balance +
                ", dateCreation=" + dateCreation +
                '}';
    }

    /**
     * Returns a formatted string representation of the Account's details.
     *
     * @return A formatted string representation of the Account's details.
     */
    public String getAccount() {
        return String.format(Locale.US, "%d:%s:%s:%s:%f:%s",
                id,
                agency,
                client.getFirstName(),
                client.getLastName(),
                balance,
                dateCreation);
    }

    public Long getId() {
        return this.id;
    }

    public Set<String> getAgencies() {
        return agencies;
    }

    public String getAgency() {
        return agency;
    }

    public Client getClient() {
        return client;
    }

    public double getBalance() {
        return balance;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static final class AccountBuilder {
        private Long id;
        private Client client;
        private String agency;
        private double balance;
        private String dateCreation;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public AccountBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder agency(String agency) {
            this.agency = agency;
            return this;
        }

        public AccountBuilder dateCreation(String dateCreation) {
            this.dateCreation = dateCreation;
            return this;
        }

        public AccountBuilder balance(double balance) {
            this.balance = balance;
            return this;
        }

        /**
         * Builds a new Account instance with the provided attributes.
         *
         * @return A new Account instance.
         */
        public Account buildWithRandomId() {
            return new Account(client, agency, balance);
        }

        /**
         * Builds a new Account instance with the provided attributes, including the creation date.
         *
         * @return A new Account instance.
         */
        public Account buildWithId() {
            return new Account(id, client, agency, balance, dateCreation);
        }
    }
}
