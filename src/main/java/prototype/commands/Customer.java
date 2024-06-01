package prototype.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Customer {
    private static int nextId = 1;
    private int userId;
    private String mail;
    private String phoneNumber;
    private String name;
    private String firstName;
    private boolean paymentStatus;
    public static ArrayList<Customer> customers = new ArrayList<>();
    private static boolean text = false;

    public Customer(String name, String firstName, String mail, String phoneNumber) {
        this.name = name;
        this.firstName = firstName;
        this.userId = nextId++;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        if (!sameCustomer(mail)) {
            customers.add(this);
            if(text){
                System.out.println("The customer was added successfully!");
            }
        }
        this.paymentStatus = false;
    }

    /**
     * This method creates some examples to be able to test.
     */
    public static void creationCustomers() {
        new Customer("Cid", "Miguel", "miguel.cid@tum.de", "0034640882288");
        new Customer("Cornejo", "Urko", "urko.cornejo@tum.de", "0034640932256");
        text = true;
    }

    public static void modifyMail(int userid, String newMail) {

    }

    public static void modifyPhoneNumber(int userid, String newPhoneNumber) {

    }

    public static void importCustomer(String mail, String phoneNumber) {
        // Import a customer file
    }

    /**
     * @param userId it gets from CommandParser the id of the user
     *               Iterator is used to be able to delete the customer once we know it exists and does not have any borrowed books.
     */
    public static void delete(int userId) {
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.userId == userId) {
                for (Map.Entry<Integer, Integer> entry : BookCopy.getCopyBorrowers().entrySet()) {
                    if (entry.getValue() == userId) {
                        System.out.println("Cannot delete customer because they have borrowed books.");
                        return;
                    }
                }
                iterator.remove();
                System.out.println("Customer removed successfully.");
                return;
            }
        }
        System.out.println("Customer with that ID was not found.");
    }

    /**
     * This method only is helpful to not have to search if a customer exists or not in other classes.
     *
     * @param userId
     * @return Depends on if the user exists or not
     */
    public static boolean customerExists(int userId) {
        for (Customer customer : customers) {
            if (customer.userId == userId) {
                return true;
            }
        }
        return false;
    }
    public static boolean sameCustomer(String mail) {
        for (Customer customer : customers) {
            if (customer.getMail().equals(mail)) {
                return true;
            }
        }
        return false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        Customer.customers = customers;
    }

    public static boolean isText() {
        return text;
    }

    public static void setText(boolean text) {
        Customer.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}