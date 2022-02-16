import java.util.*;

public class TeleBook implements Iterable<Contact> {

    private Map<String, Contact> contacts = new TreeMap<>();

    public TeleBook() {
    }

    public TeleBook(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }

    public boolean add (String name, String telephone) {
        if (name == null || telephone == null) {
            throw new NullPointerException("name and telephone cannot be null");
        }
        if (name.isEmpty() || telephone.isEmpty()) {
            throw new IllegalArgumentException("name and telephone cannot be empty");
        }
        if (!contacts.containsKey(name)) {
            contacts.put(name, new Contact(name, telephone));
            return true;
        }
        return false;
    }

    public boolean remove(String name) {
        return contacts.remove(name) != null;
    }

    public List<Contact> findByName (String name) {
        List<Contact> result = new ArrayList<>();
        for (var entry : contacts.entrySet()) {
            if (entry.getKey().contains(name))
                result.add(entry.getValue());
        }
        return result;
    }

    public List<Contact> findByTelephone (String telephone) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts.values()) {
            if (contact.getTelephone().contains(telephone))
                result.add(contact);
        }
        return result;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.values().iterator();
    }
}
