package utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
    private static final String IO_EXCEPTION = "IOException={}";

    private FileUtil() {}

    public static String getFileContent(String filePath) {
        final File file = new File(filePath);
        return getFileContent(file);
    }

    public static String getFileContent(File file) {
        String content = "";
        if (file.exists() && file.isFile())
            try {
                content = Files.toString(file, Charsets.UTF_8);
            } catch (IOException e) {
                LOG.error(IO_EXCEPTION, e);
                return null;
            }

        return content;
    }

    public static File generateFile(String filePath, String content) {
        final File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOG.error(IO_EXCEPTION, e);
            }
        }

        if (file.isFile()) {
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            } catch (IOException e) {
                LOG.error(IO_EXCEPTION, e);
            }
            return file;
        } else {
            LOG.error("filePath '{}' is not a file", filePath);
            return null;
        }
    }
}
