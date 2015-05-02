/* 
 Controlling a servo position using a potentiometer (variable resistor) 
 by Michal Rinott <http://people.interaction-ivrea.it/m.rinott> 

 modified on 8 Nov 2013
 by Scott Fitzgerald
 http://arduino.cc/en/Tutorial/Knob
*/

#include <Servo.h>
#include <SPI.h>
#include <Ethernet.h>
#include <EthernetUdp.h>


// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = {  
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192, 168, 1, 105);

unsigned int localPort = 8888;      // local port to listen on

// An EthernetUDP instance to let us send and receive packets over UDP
EthernetUDP Udp;

char packetBuffer[UDP_TX_PACKET_MAX_SIZE]; //buffer to hold incoming packet,

Servo myservo_x,myservo_y;  // create servo object to control a servo
const int leftMotor =  5;     
const int rightMotor = 6; 
int dynamicSpeed = 200;

int potpin_x = 0;  // analog pin used to connect the potentiometer
int potpin_y = 1;
int val,val_l,val_r;    // variable to read the value from the analog pin

void setup() {
   // start the Ethernet and UDP:
  Ethernet.begin(mac,ip);
  Udp.begin(localPort);
  pinMode(leftMotor, OUTPUT);
  pinMode(rightMotor, OUTPUT);
  
   Serial.begin(9600);
   Serial1.begin(9600);
  
  myservo_x.attach(9);  // attaches the servo on pin 9 to the servo object
  myservo_y.attach(10);
}

void loop() { 
  Serial1.print("Received packet of size ");
  int packetSize = Udp.parsePacket();
  if(packetSize) {
    Serial1.print("Received packet of size ");
    Serial1.println(packetSize);
    Serial.print("From ");
    
    IPAddress remote = Udp.remoteIP();
    
    for (int i =0; i < 4; i++) {//display the ip adress with format
      Serial.print(remote[i], DEC);
      if (i < 3)
      {
        Serial.print(".");
      }
    }
    
    Serial.print(", port ");
    Serial.println(Udp.remotePort());

    // read the packet into packetBufffer
    Udp.read(packetBuffer,UDP_TX_PACKET_MAX_SIZE);
    Serial.println("Contents:");
    Serial.println(packetBuffer);
    switch (packetBuffer[0]){
          case '1'://forward
            analogWrite(leftMotor, dynamicSpeed);
            analogWrite(rightMotor, dynamicSpeed);
            //digitalWrite(leftMotor, HIGH);
            //digitalWrite(rightMotor, HIGH);
            break;
          case '2'://turn left or right
            analogWrite(leftMotor, dynamicSpeed);
            digitalWrite(rightMotor, LOW);
            break;
          case '3'://forward
            digitalWrite(leftMotor, LOW);
            analogWrite(rightMotor, dynamicSpeed);
            break;
          case '4'://backward
            //TODO build HBRIDGE
            break;
          default://error stop anyways
            digitalWrite(leftMotor, LOW);
            digitalWrite(rightMotor, LOW);
            break;
     }
    delay(100);
    analogWrite(leftMotor, LOW);
      analogWrite(rightMotor, LOW);
    
  }
  /*if(sizeof(packetBuffer) >= 5){

    val_l = (int)packetBuffer[0];//left value
    val_r = (int)packetBuffer[1];//right value
    
    val = analogRead(potpin_x);            // reads the value of the potentiometer (value between 0 and 1023) 
    val_l = map(val_l, 0, 1023, 0, 160);     // scale it to use it with the servo (value between 0 and 180) 
    myservo_x.write(val_l);                  // sets the servo position according to the scaled value 
    
    val = analogRead(potpin_y);            // reads the value of the potentiometer (value between 0 and 1023) 
    val_r = map(val_r, 0, 1023, 0, 180); 
    myservo_y.write(val_r);
    delay(15);                           // waits for the servo to get there 
  }*/
} 

