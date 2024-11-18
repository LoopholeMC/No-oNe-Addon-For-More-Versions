package net.i_no_am.clickcrystals.addon.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.i_no_am.clickcrystals.addon.utils.OsUtils.SYSTEM.*;

public class OsUtils {

    public static SYSTEM getOs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return WINDOW;
        if (os.contains("nix") || os.contains("nux"))
            return LINUX;
        if (os.contains("mac"))
            return MAC;
        return null;
    }

    public static String getHWID() {
        SYSTEM os = getOs();
        String hwid = "";

        try {
            if (os == WINDOW) {
                // Windows: fetch HWID using WMIC
                String command = "wmic csproduct get uuid";
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
            } else if (os == MAC) {
                // macOS: fetch HWID using IOPlatformUUID
                String command = "ioreg -l | grep IOPlatformUUID";
                Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command });
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("IOPlatformUUID.*= (.*)");
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.find()) {
                        hwid = matcher.group(1).trim();
                        break;
                    }
                }
            } else if (os == LINUX) {
                // Linux: fetch HWID using /etc/machine-id
                String command = "cat /etc/machine-id";
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    hwid = line.trim();
                    break;
                }
            }
        } catch (Exception ignore) {}
        return hwid;
    }

    public static void copyHwid() {
        copy(getHWID());
    }

    enum SYSTEM {
        WINDOW, LINUX, MAC
    }

    public static void copy(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
}
