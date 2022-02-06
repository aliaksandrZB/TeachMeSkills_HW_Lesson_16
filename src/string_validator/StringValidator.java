package string_validator;

import dokument.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

    private static final String DOC_NUMBER_REGEX = "^((\\d{4})-([a-zа-я]{3})-){2}(\\d[a-zа-я]){2}$";
    private static final String EMAIL_REGEX = "^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$";
    private static final String PHONE_NUMBER_REGEX = "\\+\\(\\d{2}\\)\\d{7}";

    private static final Pattern docNumberPattern = Pattern.compile(DOC_NUMBER_REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private static final Pattern eMailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private static final Pattern phoneNumberPattern = Pattern.compile(PHONE_NUMBER_REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    private static Matcher docNumberMatcher;
    private static Matcher eMailMatcher;
    private static Matcher phoneNumberMatcher;

    private static String input;

    public static void validate(Document doc, File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        List<String> docNumbers= new ArrayList<>();

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            docNumberMatcher = docNumberPattern.matcher(input);
            eMailMatcher = eMailPattern.matcher(input);
            phoneNumberMatcher = phoneNumberPattern.matcher(input);

            if (docNumberMatcher.matches()) {
                docNumbers.add(input);
            } else if (eMailMatcher.matches()) {
                doc.setEMail(input);
            } else if (phoneNumberMatcher.matches()) {
                doc.setPhoneNumber(input);
            }
        }

        doc.setDocNumbers(docNumbers.toArray(new String[docNumbers.size()]));

    }
}
