import java.util.*;

class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        }

        balance -= amount;
        System.out.println("Amount withdrawn successfully.");
        return true;
    }

    public void displayAccount() {
        System.out.println("----------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + holderName);
        System.out.printf("Balance        : ₹%.2f%n", balance);
        System.out.println("----------------------------");
    }
}

public class BankAccountManagement {

    // HashMap provides fast account lookup using account number
    private static Map<String, BankAccount> accounts = new HashMap<>();

    // Create a new account
    public static void createAccount(String accountNumber,
                                     String holderName,
                                     double initialDeposit) {

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists.");
            return;
        }

        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        BankAccount account =
                new BankAccount(accountNumber, holderName, initialDeposit);

        accounts.put(accountNumber, account);

        System.out.println("Account created successfully.");
    }

    // Deposit money
    public static void deposit(String accountNumber, double amount) {

        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.deposit(amount);
    }

    // Withdraw money
    public static void withdraw(String accountNumber, double amount) {

        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.withdraw(amount);
    }

    // View balance
    public static void viewBalance(String accountNumber) {

        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.printf(
                "Account Holder: %s%nBalance: ₹%.2f%n",
                account.getHolderName(),
                account.getBalance()
        );
    }

    // Display all accounts
    public static void displayAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n===== All Bank Accounts =====");

        for (BankAccount account : accounts.values()) {
            account.displayAccount();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Bank Account Management =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = sc.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String holderName = sc.nextLine();

                    System.out.print("Enter Initial Deposit: ₹");
                    double initialDeposit = sc.nextDouble();

                    createAccount(
                            accountNumber,
                            holderName,
                            initialDeposit
                    );
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    String depositAccount = sc.nextLine();

                    System.out.print("Enter Deposit Amount: ₹");
                    double depositAmount = sc.nextDouble();

                    deposit(depositAccount, depositAmount);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    String withdrawAccount = sc.nextLine();

                    System.out.print("Enter Withdrawal Amount: ₹");
                    double withdrawAmount = sc.nextDouble();

                    withdraw(withdrawAccount, withdrawAmount);
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    String balanceAccount = sc.nextLine();

                    viewBalance(balanceAccount);
                    break;

                case 5:
                    displayAllAccounts();
                    break;

                case 6:
                    System.out.println("Thank you for using the bank system.");
                    sc.close();
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please enter 1-6."
                    );
            }
        }
    }
}