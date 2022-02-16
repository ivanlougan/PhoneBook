import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtils {
    private static final String FILE_NAME = "telebook.csv";

    public static void save(TeleBook teleBook) throws IOException {
        var writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Contact contact : teleBook) {
            writer.write(contact.toCSV());
            writer.newLine();
        }
        writer.close();
    }

    public static TeleBook read() {
        TeleBook book = null;
        try {
            var buffReader = new BufferedReader(new FileReader(FILE_NAME));
            Map<String, Contact> contacts = buffReader.lines()
                    .map(line -> line.split(";"))
                    .map(split -> new Contact(split[0], split[1]))
                    .collect(Collectors.toMap(Contact::getName, Function.identity()));
            book = new TeleBook(new TreeMap<>(contacts));
        } catch (FileNotFoundException e ) {

        }
        return book != null ? book : new TeleBook();
    }

}
