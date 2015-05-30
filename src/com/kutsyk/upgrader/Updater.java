package com.kutsyk.upgrader;

import com.kutsyk.main.MainForm;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Kutsyk on 27.05.2015.
 */
public class Updater {

    private int currentVersion = -1;
    private int versionOnFTP = -1;

    private FTPClient ftpClient = new FTPClient();
    private String mainPath;
    public Updater(String mainPath) {
        this.mainPath = mainPath;
        currentVersion = getVersionFromFile(mainPath + "/bin/.currVersion");
    }

    public boolean existNewVersion() {
        currentVersion = getVersionFromFile(mainPath + "/bin/.currVersion");
        versionOnFTP = getVersionFromFile(mainPath + "/bin/.version");
        return versionOnFTP>currentVersion;
    }

    private int getVersionFromFile(String fileName) {
        int version = -1;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextInt())
                version = scanner.nextInt();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return version;
    }

    public boolean replaceCurrentVersionWithNew() {
        deleteOldVersion();
        renameNewerVersion();
        updateVersionNumber();
        return true;
    }

    private boolean deleteOldVersion() {
        return new File(mainPath + "/bin/LaTEX.jar").delete();
    }

    private boolean renameNewerVersion() {
        return new File(mainPath + "/bin/program.jar").renameTo(new File(mainPath + "bin/LaTEX.jar"));
    }

    public void updateVersionNumber() {
        try {
            currentVersion = versionOnFTP;
            PrintWriter writer = new PrintWriter(new File(mainPath + "/bin/.currVersion"));
            writer.append("" + currentVersion);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
