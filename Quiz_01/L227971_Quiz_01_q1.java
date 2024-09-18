import java.util.ArrayList;

class Contact {
    public String name;
    public String phone;
    public String address;

    public Contact(String name, String phone, String address) {
        this.address = address;
        this.phone = phone;
        this.name = name;
    }
}

class UserContactBook {
    public ArrayList<Contact> contacts;

    public UserContactBook() {
        contacts = new ArrayList<Contact>();
    }

    public boolean add(Contact contact) {
        for (Contact contact_search : this.contacts) {
            if (contact_search.address.equals(contact.address) && contact_search.name.equals(contact.name)
                    && contact_search.phone.equals(contact.phone)) {
                System.out.println("Contact Already exists");
                return false;
            }
        }
        contacts.add(contact);
        return true;
    }

    public Contact search_by_name(String name) {
        for (Contact contact : this.contacts) {
            if (contact.name.equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public Contact search_by_phone(String phone) {
        for (Contact contact : this.contacts) {
            if (contact.phone.equals(phone)) {
                return contact;
            }
        }
        return null;
    }

    public boolean remove(String name) {
        int index = -1;
        for (int i = 0; i < this.contacts.size(); i++) {
            if (this.contacts.get(i).name.equals(name)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            this.contacts.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public void show_all() {
        if (this.contacts.size() == 0) {
            System.out.println("Error: No contacts present");
            return;
        }

        for (Contact contact : this.contacts) {
            System.out.println(contact.name + ", " + contact.phone + ", " + contact.address);
        }
    }

    public void print(Contact c) {
        System.out.println(c.name + ", " + c.phone + ", " + c.address);
    }
}

public class L227971_Quiz_01_q1 {
    public static void main(String[] args) {
        UserContactBook book = new UserContactBook();
        Contact c1 = new Contact("Hasan", "0101020992902", "Address 1");
        Contact c2 = new Contact("Hasan 2", "042828482848", "Address 2");
        Contact c3 = new Contact("Hasan 3", "193888383883", "Address 3");

        book.add(c1);
        book.add(c2);
        book.add(c3);

        Contact temp1 = book.search_by_name("Hasan");
        Contact temp2 = book.search_by_phone("042828482848");

        book.print(temp1);
        book.print(temp2);
        book.remove("Hasan 3");
        System.out.println("\n");
        book.show_all();
    }
}