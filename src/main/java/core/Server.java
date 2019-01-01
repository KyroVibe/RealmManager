package core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import neospace.*;

public class Server {
    public static void main(String[] args) { new Server(); }

    public ArrayList<RealmInfo> realms = new ArrayList<RealmInfo>();

    public final int PORT = 7200;
    public final int REALMPORT = 7300;

    public boolean DIE = false;

    private ServerSocket me;

    public Server() {

        // Soft Code Varibles
        realms.add(new RealmInfo("DEFAULT", "76.105.136.144"));

        try {

            me = new ServerSocket(7200);

            while(!DIE) {
                Socket s = me.accept();
                NeoReader reader = new NeoReader(s.getInputStream());
                NeoWriter writer = new NeoWriter(s.getOutputStream());
                String a = reader.Read();
                writer.Write(HostnameViaName(a));
                writer.Flush();
                reader.Close();
                writer.Close();
                s.close();
            }

            me.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FUCKCHFUBBFKDSKBHDSBGHDSBKHDBSHKBDSHKHKDSBBHLDSBHLLBD");
        }
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