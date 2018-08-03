package br.gov.mme.web.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

public final class FilesUtil {

    private FilesUtil() {
    }

    public static ByteArrayOutputStream fileToOutputStream(File file) throws IOException {
        InputStream input = createInput(file);
        ByteArrayOutputStream output = null;
        try {
            output = new ByteArrayOutputStream();
            output.write(IOUtils.toByteArray(input));
        } finally {
            if (output != null) {
                output.close();
            }
        }
        return output;
    }

    private static FileInputStream createInput(File file) throws IOException {
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return (FileInputStream) input;
    }

}
