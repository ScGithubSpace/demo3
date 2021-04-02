package org.example.server;

import org.example.bean.Commit;
import org.example.fabric.FabricNetWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GitListeningServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        System.out.println("waiting...");
        FabricNetWork fabricNetWork = new FabricNetWork();
        //9999端口
        serverSocket = new ServerSocket(9999);

        try {
            while(true) {
                socket = serverSocket.accept();
                is = socket.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                Commit commit = parse(baos.toString());
                fabricNetWork.commitToFabric(commit);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(baos != null) {
                try {
                    baos.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null) {
                try {
                    socket.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static Commit parse(String s){
        String[] strings = s.split("\n");
        String[] hashArr = strings[0].split("/");
        String hash = hashArr[2] + hashArr[3];
        String tree = strings[1].split(" ")[1];
        String parent = strings[2].split(" ")[1];
        String author = strings[3].split(" ")[1];
        String committer = strings[4].split(" ")[1];
        String msg = strings[6];
        Commit commit = new Commit(hash,tree,parent,author,committer,msg);
        return commit;
    }

}
