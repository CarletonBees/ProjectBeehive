package com.example.benjamin.beehiveapp;

import android.content.Context;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static java.lang.System.exit;

/**
 * Created by Benjamin on 2017-10-24.
 */



class UDPListener extends Thread {

    public Context context;

    public static final int listenPort = 1000;
    public static final byte packetDelimiter = '?';

    private DatagramSocket sock;
    private float tempTemperature;
    private float tempHumidity;
    private int tempHiveNumber;

    /*
    Packet conventions:
    Start with opcode:(O)
        1:
        2:
    Delimiter
    Next Hive number: 8 bytes(N)
    Delimiter
    Next temperature: 8 bytes(T)
    Delimiter
    Next Humidity:    8 bytes(H)
    End delimiter

    Opcode form: O ? TTTT TTTT ? HHHH HHHH ?
     */



    public UDPListener(Context context){
        this.context = context;
        try{
            sock = new DatagramSocket(listenPort);
        }catch(SocketException e){
            System.out.println("Socket cannot bind to Listener Port");
            exit(0);
        }
    }

    public void run(){
        DatagramPacket p = new DatagramPacket(new byte[100],100);
        while(true){
            try{
                sock.receive(p);
            }catch (IOException e){
                System.out.println("Failed to receive Packet");
            }
            byte[] buf = p.getData();
            switch(buf[0]){
                case 0:
                    parsePacket(buf);
                    break;
                default:
                    break;
            }

        }
    }
    public void parsePacket(byte[] buf){
        //byte[2] - byte[9] = temperature;
        //byte[11] - byte [18] = humidity;
        //get sending port location.
    }
}
