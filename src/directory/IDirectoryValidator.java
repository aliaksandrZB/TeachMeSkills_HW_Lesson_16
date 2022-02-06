package directory;

import java.io.File;

public interface IDirectoryValidator {

    public boolean validation();

    public boolean isOkPath();

    public boolean isEmpty();

    public boolean isContainsTxtFiles();

    public File[] filterTypes(String type);

}
