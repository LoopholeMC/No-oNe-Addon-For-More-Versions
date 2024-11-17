package net.i_no_am.clickcrystals.addon.utils;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OsUtils {

    public static String getHWID() {
        String command = "wmic csproduct get uuid";
        String hwid = "";

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\b[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}\\b");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    hwid = matcher.group(0);
                    break;
                }
            }
        } catch (Exception ignore) {}
        return hwid;
    }


    public static void copyHwid(){
        copy(getHWID());
    }

    public static void copy(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
}