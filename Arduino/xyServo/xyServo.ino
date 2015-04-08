/* 
 Controlling a servo position using a potentiometer (variable resistor) 
 by Michal Rinott <http://people.interaction-ivrea.it/m.rinott> 

 modified on 8 Nov 2013
 by Scott Fitzgerald
 http://arduino.cc/en/Tutorial/Knob
*/

#include <Servo.h>

Servo myservo_x,myservo_y;  // create servo object to control a servo

int potpin_x = 0;  // analog pin used to connect the potentiometer
int potpin_y = 1;
int val;    // variable to read the value from the analog pin

void setup()
{
  myservo_x.attach(9);  // attaches the servo on pin 9 to the servo object
  myservo_y.attach(10);
}

void loop() 
{ 
  val = analogRead(potpin_x);            // reads the value of the potentiometer (value between 0 and 1023) 
  val = map(val, 0, 1023, 0, 160);     // scale it to use it with the servo (value between 0 and 180) 
  myservo_x.write(val);                  // sets the servo position according to the scaled value 
  
  val = analogRead(potpin_y);            // reads the value of the potentiometer (value between 0 and 1023) 
  val = map(val, 0, 1023, 0, 180); 
  myservo_y.write(val);
  delay(15);                           // waits for the servo to get there 
} 

