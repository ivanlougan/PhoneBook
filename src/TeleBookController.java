import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TeleBookController {
    private TeleBook teleBook = new TeleBook();
    private Scanner input = new Scanner(System.in);

    public TeleBookController(){
        teleBook = FileUtils.read();
    }

    public void loop() {
        OPTIONS option = null;
        do {
            showOptions();
            try {
                option = chooseOption();
                executeOption(option);
            } catch (NoSuchElementException e) {
                System.out.println("Illegal input");
            }
        } while (option != OPTIONS.CLOSE);
    }

    private void showOptions() {
        System.out.println(">>> Options: ");
        for (OPTIONS option : OPTIONS.values())
            System.out.println(option);
    }

    private OPTIONS chooseOption() {
        int option = input.nextInt();
        input.nextLine();
        return OPTIONS.convertToOptions(option);
    }

    private void executeOption(OPTIONS options) {

        switch (options) {

            case ADD_CONTACT:
                addContact();
                break;
            case SEARCH_BY_NAME:
                searchByName();
                break;
            case SEARCH_BY_PHONE:
                searchByTelephone();
                break;
            case DELETE:
                delete();
                break;
            case CLOSE:
                close();
                break;
        }
    }

    private void delete() {
        System.out.println("Enter the name of the contact to be deleted: ");
        String name = input.nextLine();
        boolean remove = teleBook.remove(name);
        if (remove)
            System.out.println("Your contact has been deleted");
        else
            System.out.println("There's no such a name in phone book");
    }

    private void searchByTelephone() {
        System.out.println("Enter phone number or part of it");
        String phone = input.nextLine();
        List<Contact> telephones = teleBook.findByTelephone(phone);
        if (telephones.isEmpty())
            System.out.println("Not found");
        else
            System.out.println("Contacts found: ");
        telephones.forEach(System.out::println);
    }

    private void searchByName() {
        System.out.println("Enter name or part of it");
        String name = input.nextLine();
        List<Contact> byName = teleBook.findByName(name);
        if (byName.isEmpty())
            System.out.println("Not found");
        else
            System.out.println("Contacts found: ");
        byName.forEach(System.out::println);
    }

    private void addContact() {
        System.out.println("Input name: ");
        String name = input.nextLine();
        System.out.println("Input phone number");
        String telephone = input.nextLine();
        try {
            boolean add = teleBook.add(name, telephone);
            if (add)
                System.out.println("Record added to the list");
            else
                System.out.println("Cannot add the record. Record already exist");
        } catch (IllegalArgumentException e) {
            System.err.println("Name or phone cannot be empty");
        }

    }

    private void close() {
        input.close();
        try {
            FileUtils.save(teleBook);
            System.out.println("Changes saved");
        } catch (IOException e) {
            System.err.println("Save Mission aborted");
        }
        System.out.println("Bye bye!");
    }

}
