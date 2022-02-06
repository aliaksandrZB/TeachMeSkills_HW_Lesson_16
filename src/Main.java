import directory.my_directory.Directory;
import dokument.Document;
import string_validator.StringValidator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("enter the path to the directory:");
        Scanner scanner = new Scanner(System.in);
        Directory myDirectory = new Directory(scanner.nextLine());

        if (!myDirectory.validation()) {
            return;
        }

        System.out.println("enter the number of documents to process:");
        scanner = new Scanner(System.in);
        int numOfDoc = scanner.nextInt();
        scanner.close();
        File[] txtFiles = myDirectory.getTxtFiles();
        if (txtFiles.length < numOfDoc) {
            numOfDoc = txtFiles.length;
        }

        Map<String, Document> processedDocuments = new HashMap<>(numOfDoc);

        for (int i = 0; i < numOfDoc; i++) {
            Document doc = new Document();
            StringValidator.validate(doc, txtFiles[i]);
            processedDocuments.put(txtFiles[i].getName(), doc);
        }

        System.out.println(numOfDoc + "documents were processed. "
                           + (myDirectory.getFiles().length - myDirectory.getTxtFiles().length)
                           + "documents were not in a valid format");

    }
}
