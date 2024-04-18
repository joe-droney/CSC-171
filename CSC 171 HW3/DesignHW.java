//Joe Droney, HW3, jdroney2@gmail.com

public class DesignHW {
    public static void main(String[] args) {
        // Create an instance of a Person
        Person person1 = new Person("Taylor Swift", 1989);
        person1.setOccupation("Singer");
        Person person2 = new Person("Josh Allen", 1996);
        person2.setOccupation("Football Player");
       
        //Create an instance for Penguin
        Penguin animal1 = new Penguin("Pablo", 7);
        Penguin animal2 = new Penguin("Perry", 3);
     
        
        
        // Create an instance of Smartphone with a battery capacity of 2200 mAH
        Smartphone myPhone = new Smartphone(2200);

        // Display information about the Person, Animal, and Smartphone objects
        System.out.println("Person 1: " + person1.toString());
        System.out.println("Person 2: " + person2.toString());

        System.out.println("Animal 1: " + animal1.toString());
        animal2.swim();
        System.out.println("Animal 2: " + animal2.toString());

        // Display battery life with all features off
        System.out.println("Battery life with all features off: " + myPhone.getBatteryLife() + " hours");

        // Turn on some features
        myPhone.turnOnScreen();
        myPhone.turnOnVoiceCalling();
        myPhone.turnOffWifi();

        // Display battery life with these features on
        System.out.println("Battery life with screen, voice calling, and WiFi off: " + myPhone.getBatteryLife() + " hours");

        // Turn off voice calling and put the phone in standby
        myPhone.turnOffVoiceCalling();
        myPhone.putInStandby();

        // Display battery life with modified settings
        System.out.println("Battery life with screen on, voice calling off, and standby mode: " + myPhone.getBatteryLife() + " hours");
    }
}
