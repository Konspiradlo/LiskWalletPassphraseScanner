package pl.kons;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class PathProvider {

    public Set<File> providePaths(String rootDirectory) {
        File rootDirectoryFile = new File(rootDirectory);
        Set<File> files = new HashSet<File>();
        if (rootDirectoryFile != null && rootDirectoryFile.isDirectory()) {
            for (File file : rootDirectoryFile.listFiles()) {
                if (file.isFile()) {
                    files.add(file);
                } else {
                    files.addAll(providePaths(file.getAbsolutePath()));
                }
            }

        }
        return files;
    }
}
