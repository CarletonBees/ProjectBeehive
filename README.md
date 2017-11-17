# Project-Beehive
Fourth Year Project


Files and how they break down:

Project Proposal Draft:
  This is where we write and draft our project proposal. No code here, but the general idea of the project and method is here

Project Schedule:
  This has the rough outline of all the deliverables and deadlines for the project.

Three Lists:
   This has what we want to do in the project, what we hope to do in the project, and what would be amazing, but can't do realistically in    the scope of this project.

Java files:
  These are the files for the Android Application.
  BeePacket:
    This is how the App stores the information from the Packets
  DisplayMessageActivity:
    This is the secondary screen where you view information about a specific beehive
    Name to be Changed.
  MainActivity:
    This is the main menu where you select a beehive from a list and can trigger the polling for new information from the hives/database
  TestPackets:
    This is where the test Packets to check the Application's Packet Processing and limit handling.
  UDPListener:
    This is where the program waits for the packets. Has packet handling and processing.
  UDPSender:
    This is where the program will eventually ping the database for new info.
