package directory.my_directory;

import directory.IDirectoryValidator;

import java.io.File;
import java.util.Arrays;

public class Directory implements IDirectoryValidator {

    private String path;
    private File directory;
    private File[] files;
    private File[] txtFiles;

    public Directory(String path) {
        this.path = path;
        this.directory = new File(path);
        this.files = directory.listFiles();
        this.txtFiles = filterTypes("txt");
    }

    public File[] getFiles() {
        return files;
    }

    public File[] getTxtFiles() {
        return txtFiles;
    }

    @Override
    public boolean validation() {
        if (!isOkPath()) {
            System.out.println("There is no directory in the specified path.");
            return false;
        } else if (isEmpty()) {
            System.out.println("This directory is empty");
            return false;
        } else if (!isContainsTxtFiles()) {
            System.out.println("directory.my_directory.Directory does not contains \".txt\" files");
            return false;
        }
        return true;
    }

    @Override
    public boolean isOkPath() {
        return this.directory.isDirectory();
    }

    @Override
    public boolean isEmpty() {
        return this.files.length == 0;
    }

    @Override
    public boolean isContainsTxtFiles() {
        return this.txtFiles.length > 0;
    }

    @Override
    public File[] filterTypes(String type) {
        return Arrays.stream(this.files)
                .filter(x -> x.getName().endsWith("." + type))
                .toArray(File[]::new);
    }
}
