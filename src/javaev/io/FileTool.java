package javaev.io;

import java.io.*;

public class FileTool {
    private int type_Prefix = -1;
    private int type_Suffix = 1;

    private String line_separator = System.lineSeparator();

    public String readString(File file) {
        if (null != file) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String string;
                StringBuilder stringBuilder = new StringBuilder();
                while ((string = reader.readLine()) != null) {
                    stringBuilder.append(string);
                    stringBuilder.append(line_separator);
                }
                reader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void writeString(File file, String string) {
        if (null != string) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(string);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public File newFile(File file, Object name, String suffix, boolean isSuffix) {
        if (null != file && null != name && null != suffix) {
            File dirFile = file.getParentFile();
            if (null != dirFile) {
                String fileName = file.getName();
                if (isSuffix) {
                    fileName = getPrefix(fileName) + name + suffix;
                } else {
                    fileName = getPrefix(name.toString()) + suffix;
                }
                return new File(dirFile, fileName);
            }
        }
        return null;
    }

    public String getPrefix(String string) {
        return newString(string, type_Prefix);
    }

    public String getSuffix(String string) {
        return newString(string, type_Suffix);
    }

    private String newString(String string, int suffixType) {
        if (null != string) {
            String prefix;
            String suffix;
            int index = string.lastIndexOf(".");
            if (index != -1) {
                prefix = string.substring(0, index);
                suffix = string.substring(index);
            } else {
                prefix = string;
                suffix = "";
            }
            if (suffixType == type_Prefix) {
                return prefix;
            } else if (suffixType == type_Suffix) {
                return suffix;
            }
        }
        return string;
    }
}
