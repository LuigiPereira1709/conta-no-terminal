package main.java.dio.me.domain;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Account {
    private Long id;
    private final Set<String> agencies = Set.of("001", "123-4", "9876", "555-0");
    private String agency;
    private Client client;
    private double balance;

    private Account(Client client, String agency, double balance) {
        this.id = ThreadLocalRandom.current().nextLong(1, 9999);
        this.client = client;
        this.agency = agency;
        this.balance = balance;
    }

    private Account(Long id, Client client, String agency, double balance) {
        this.id = id;
        this.client = client;
        this.agency = agency;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", agency=" + agencies +
                ", client=" + client +
                ", balance=" + balance +
                '}';
    }

    // id:agency:clientName:clientLastName:balance
    public String getAccount() {
        return String.format("%d:%s:%s:%s:%f",
                id,
                agency,
                client.getFirstName(),
                client.getLastName(),
                balance);
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

        public AccountBuilder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public Account buildUp() {
            Account account = new Account(client, agency, balance);
            return account;
        }

        public Account buildIn() {
            Account account = new Account(id, client, agency, balance);
            return account;
        }
    }
}
