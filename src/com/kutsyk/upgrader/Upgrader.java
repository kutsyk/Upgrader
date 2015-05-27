package com.kutsyk.upgrader;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Kutsyk on 27.05.2015.
 */
public class Upgrader {

    private int currentVersion = -1;
    private String server = "ftp.charlesworth-group.com";
    private int port = 21;
    private String user = "guest";
    private String pass = "guest99";
    private FTPClient ftpClient = new FTPClient();
    private String filePath;

    public Upgrader(String mainPath) {
        filePath = mainPath;
        currentVersion = getVersionFromFile(".currVersion");
    }

    public boolean upgrade() {
        if (existNewVersion())
            return replaceCurrentVersionWithNew();
        return false;
    }

    private boolean existNewVersion() {
        int versionOnFTP = -1;
        if (downloadVersionFile())
            versionOnFTP = getVersionFromFile(".version");
        return versionOnFTP > currentVersion;
    }

    public boolean downloadVersionFile() {
        return downloadFileFromFTP("/software/.version", new File(filePath+".version"));
    }

    private int getVersionFromFile(String fileName) {
        int version = -1;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextInt())
                version = scanner.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return version;
    }

    private boolean replaceCurrentVersionWithNew() {
        if(downloadNewerVersion()) {
            deleteOldVersion();
            renameNewerVersion();
        }
        return false;
    }

    private boolean downloadNewerVersion() {
        return downloadFileFromFTP("/software/program.jar", new File(filePath+"program.jar"));
    }

    private boolean deleteOldVersion(){
        return new File(filePath+"LaTEX.jar").delete();
    }

    private boolean renameNewerVersion(){
        return new File(filePath+"program.jar").renameTo(new File(filePath+"LaTEX.jar"));
    }

    private boolean downloadFileFromFTP(String remote, File local) {
        boolean success = false;
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            if (!local.exists())
                local.createNewFile();

            OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(local));
            InputStream inputStream = ftpClient.retrieveFileStream(remote);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }

            success = ftpClient.completePendingCommand();
            outputStream2.close();
            inputStream.close();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return success;
    }
}