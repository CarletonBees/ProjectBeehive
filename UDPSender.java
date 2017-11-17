package com.example.benjamin.beehiveapp;

import android.provider.ContactsContract;

import junit.framework.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Benjamin on 2017-10-31.
 */

public class UDPSender {
    DatagramSocket sendSocket;
    DatagramPacket sendPacket;
    private InetAddress computerInetAddress;
    public static final int computerPort = 1111;
    public static final String computerInetAddressString = "127.0.0.1";
    static final char delimiter = '?';
    byte[] sendMessage;
    String sendMessageString;

    public UDPSender(){
        try{
            sendSocket = new DatagramSocket(null);
        }catch (SocketException e){
            System.out.print("Socket creation Failed");
        }
        try{
            computerInetAddress = InetAddress.getByName(computerInetAddressString);
        }catch (UnknownHostException e){
            System.out.print("Unknown Host");
        }
        sendMessage = new byte[100];
    }

    //Sends all of the test packets one by one onClick.
    public void sendTestPackets(TestPackets tp){
        for(int i=0; i<tp.getSize(); i++){
            sendTestPacket(tp, i);
        }
    }
    public void sendTestPacket(TestPackets tp, int testNumber){
        BeePacket bp = tp.getPacket(testNumber);
        sendMessageString = "1";
        sendMessageString += delimiter;
        sendMessageString += bp.getHumidity();
        sendMessageString += delimiter;
        sendMessageString += bp.getTemperature();
        sendMessageString += delimiter;
        sendMessageString += bp.getHiveNumber();
        sendMessageString += delimiter;

        sendMessage = sendMessageString.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, computerInetAddress, computerPort);

        System.out.print(sendMessageString);
    }
}