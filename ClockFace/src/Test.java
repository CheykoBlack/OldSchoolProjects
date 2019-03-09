package martcd08_cs161_project3;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 3: MVC on the Clock
*
* Description:
    Contains the main method and allows for testing of our clock.
*/
public class Test {

    public static void main(String[] args) {
        ClockWork cw = new ClockWork();
        ControlDevices control = new ControlDevices(cw);
        FaceGUI test = new FaceGUI(cw);
    }
    
}
