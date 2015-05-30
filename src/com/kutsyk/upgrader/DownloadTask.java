package com.kutsyk.upgrader;

import com.kutsyk.main.MainForm;
import org.apache.commons.net.ftp.FTP;

import javax.swing.*;
import java.io.*;
import java.net.ConnectException;

/**
 * Created by Kutsyk on 29.05.2015.
 */
public class DownloadTask extends SwingWorker<Void, Void> {

    private static final int BUFFER_SIZE = 4096;

    private String host;
    private int port;
    private String username;
    private String password;

    private String remoteFile;
    private File local;

    private MainForm gui;

    public DownloadTask(String host, int port, String username,
                        String password, MainForm gui) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.gui = gui;
    }

    public File getLocal() {
        return local;
    }

    public void setLocal(File local) {
        this.local = local;
    }

    public String getRemoteFile() {
        return remoteFile;
    }

    public void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
    }

    /**
     * Executed in background thread
     */
    @Override
    protected Void doInBackground() throws Exception {
        FTPUtility util = new FTPUtility(host, port, username, password);
        try {
            util.connect();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            long totalBytesRead = 0;
            int percentCompleted = 0;

            long fileSize = util.getFileSize(remoteFile);
            FileOutputStream outputStream = new FileOutputStream(local);
            util.downloadFile(remoteFile);
            InputStream inputStream = util.getInputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                percentCompleted = (int) (totalBytesRead * 100 / fileSize);
                setProgress(percentCompleted);
            }

            outputStream.close();

            util.finish();
        } catch (ConnectException ex) {
            ex.printStackTrace();
            setProgress(0);
            cancel(true);
        } finally {
            util.disconnect();
        }

        return null;
    }

    /**
     * Executed in Swing's event dispatching thread
     */
    @Override
    protected void done() {
        if (!isCancelled()) {
        }
    }
}