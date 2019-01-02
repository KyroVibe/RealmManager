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

        System.out.println("--- Realm Manager v1.0.0 ---");

        try {

            me = new ServerSocket(7200);

            while(!DIE) {
                Socket s = me.accept();
                NeoReader reader = new NeoReader(s.getInputStream());
                NeoWriter writer = new NeoWriter(s.getOutputStream());
                String a = reader.Read();
                String host = HostnameViaName(a);
                writer.Write(host);
                writer.Flush();
                reader.Close();
                writer.Close();
                s.close();
                System.out.println("Connected Client to Realm '" + a + "' at '"  + host + "'");
            }

            me.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FUCKCHFUBBFKDSKBHDSBGHDSBKHDBSHKBDSHKHKDSBBHLDSBHLLBD");
        }
    }

    public String HostnameViaName(String name) {
        //name = name.replaceAll("\n", "a");
        //System.out.println("'" + name + "'");
        
        //System.out.println(name.length());
        //System.out.println(realms.size());
        for (int i = 0; i < realms.size(); i++) {
            //System.out.println("'" + realms.get(i).realmName + "'");
            if (realms.get(i).realmName.equals(name)) {
                return realms.get(i).hostname;
            } else {
                //System.out.println("'" + realms.get(i).realmName + "' doesn't equal '" + name + "'");
            }
        }

        //System.out.println("Welp Fuck");

        return "null";
    }

}