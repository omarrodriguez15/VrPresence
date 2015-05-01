/*
*/

const int buttonOne = 2;     // the number of the pushbutton pin
const int buttonTwo = 7;
const int buttonThree = 9;

const int leftMotor =  5;     
const int rightMotor = 6;   

int buttonState = 0;         // variable for reading the pushbutton status

void setup() {
  Serial.begin(9600);
  
  pinMode(leftMotor, OUTPUT);
  pinMode(rightMotor, OUTPUT);

  pinMode(buttonOne, INPUT_PULLUP);
  pinMode(buttonTwo, INPUT_PULLUP);
  pinMode(buttonThree, INPUT_PULLUP);
}

void loop() {
  // read the state of the pushbutton value:
  buttonState += digitalRead(buttonOne);
  buttonState += digitalRead(buttonTwo);
  //buttonState += digitalRead(buttonThree);
  Serial.println(buttonState);
  int dynamicSpeed = 200;
  
  switch(buttonState){
    case 2://nothing
    digitalWrite(leftMotor, LOW);
    digitalWrite(rightMotor, LOW);
    break;
    case 1://turn left or right
    if (digitalRead(buttonOne)){
      digitalWrite(leftMotor, LOW);
      analogWrite(rightMotor, dynamicSpeed);
      //digitalWrite(leftMotor, LOW);
      //digitalWrite(rightMotor, HIGH);
    }
    else if (digitalRead(buttonTwo)){
      analogWrite(leftMotor, dynamicSpeed);
      digitalWrite(rightMotor, LOW);
      //digitalWrite(leftMotor, HIGH);
      //digitalWrite(rightMotor, LOW);
    }
    break;
    case 0://forward
    analogWrite(leftMotor, dynamicSpeed);
    analogWrite(rightMotor, dynamicSpeed);
    //digitalWrite(leftMotor, HIGH);
    //digitalWrite(rightMotor, HIGH);
    break;
    case 4://backward
    //TODO build HBRIDGE
    break;
    default://error stop anyways
    digitalWrite(leftMotor, LOW);
    digitalWrite(rightMotor, LOW);
    break;
  }
  //reset buttonState
  buttonState = 0;
}
