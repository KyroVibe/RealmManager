package main.java.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) { new Server(); }

    public ArrayList<RealmInfo> realms = new ArrayList<RealmInfo>() {
        { new RealmInfo("DEFAULT", "76.105.136.144"); }
    };

    public final int PORT = 7200;
    public final int REALMPORT = 7300;

    private ServerSocket me;

    public Server() {
        me = new ServerSocket(7200);

        while(true) {
            Socket s = me.accept();
            BufferedReader reader = new InputStreamReader(s.getInputStream());
            BufferedWriter writer = new OutputStreamWriter(s.getOutputStream());
            String a = reader.readLine();
            writer.write(HostnameViaName(a));
            writer.flush();
            reader.close();
            writer.close();
            s.close();
        }

        me.close();
    }

    public String HostnameViaName(String name) {
        for (int i = 0; i < realms.size(); i++) {
            if (realms.get(i).realmName.equals(name)) {
                return realms.get(i).hostname;
            }
        }

        return "null";
    }

}