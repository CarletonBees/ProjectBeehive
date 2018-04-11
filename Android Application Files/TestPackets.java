package com.example.benjamin.beehiveapp;

/**
 * Created by Benjamin on 2017-10-25.
 */

public class TestPackets {
    private float[] testTemperature;
    private float[] testHumidity;
    private int testPacketNumber;
    private BeePacket[] testPackets;

    public TestPackets(){
        testTemperature[0] = 35;
        testTemperature[1] = 25;
        testTemperature[2] = 40;
        testTemperature[3] = -5;
        testTemperature[4] = -40;
        testTemperature[5] = 100;
        testHumidity[0] = 55;
        testHumidity[1] = 45;
        testHumidity[2] = 65;
        testHumidity[3] = 30;
        testHumidity[4] = 0;
        testHumidity[5] = 100;
        generatePackets();
    }

    public void generatePackets(){
        for(int i = 0; i<16; i++){
            float testPacketHumidity = testHumidity[i%4];
            float testPacketTemperature = testTemperature[i/4];
            testPackets[i] = new BeePacket(testPacketHumidity, testPacketTemperature);
        }
        int k = 0;
        for(int i = 4; i<6; i++){
            for(int j = 4; j<6; j++){
                float testPacketHumidity = testHumidity[i];
                float testPacketTemperature = testTemperature[j];
                testPackets[16+k] = new BeePacket(testPacketHumidity, testPacketTemperature);
                k++;
            }
        }
    }

    public BeePacket getPacket(int index){
        return testPackets[index];
    }
    public int getSize(){
        return testPackets.length;
    }
    /*
    20 test packets:
    4 test temperatures:
        35, 25, 40, -5
    4 test humidities:
        55, 45, 65, 30
    all combinations
    4 fringe test cases:
        35 T, 0 H
        35 T, 100 H
        -40 T, 55 H
        100 T, 55 H
     */
}
