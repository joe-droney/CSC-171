public class Smartphone {
    private int batteryCapacity; // in mAH (milliamp-hours)
    private boolean screenOn;
    private boolean voiceCalling;
    private boolean wifiOn;
    private boolean bluetoothOn;
    private boolean standby;

    public Smartphone(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        this.screenOn = false;
        this.voiceCalling = false;
        this.wifiOn = false;
        this.bluetoothOn = false;
        this.standby = false;
    }

    // Method to compute and return the battery life (in hours)
    public double getBatteryLife() {
        int totalPowerConsumption = 0;

        if (screenOn) totalPowerConsumption += 500;
        if (voiceCalling) totalPowerConsumption += 300;
        if (wifiOn) totalPowerConsumption += 200;
        if (bluetoothOn) totalPowerConsumption += 100;
        if (standby) totalPowerConsumption += 5;

        // Calculate battery life (hours) based on capacity and power consumption
        return (double) batteryCapacity / totalPowerConsumption;
    }

    // Method to turn on the screen
    public void turnOnScreen() {
        screenOn = true;
    }

    // Method to turn off the screen
    public void turnOffScreen() {
        screenOn = false;
    }

    // Method to turn on voice calling
    public void turnOnVoiceCalling() {
        voiceCalling = true;
    }

    // Method to turn off voice calling
    public void turnOffVoiceCalling() {
        voiceCalling = false;
    }

    // Method to turn on WiFi
    public void turnOnWifi() {
        wifiOn = true;
    }

    // Method to turn off WiFi
    public void turnOffWifi() {
        wifiOn = false;
    }

    // Method to turn on Bluetooth
    public void turnOnBluetooth() {
        bluetoothOn = true;
    }

    // Method to turn off Bluetooth
    public void turnOffBluetooth() {
        bluetoothOn = false;
    }

    // Method to put the phone in standby mode
    public void putInStandby() {
        standby = true;
    }

    // Method to take the phone out of standby mode
    public void takeOutOfStandby() {
        standby = false;
    }

    public static void main(String[] args) {
        
        Smartphone myPhone = new Smartphone(2200);

        // Display battery life with all features off
        System.out.println("Battery life with all features off: " + myPhone.getBatteryLife() + " hours");

        // Turn on some features
        myPhone.turnOnScreen();
        myPhone.turnOnVoiceCalling();
        myPhone.turnOnWifi();

        // Display battery life with these features on
        System.out.println("Battery life with screen, voice calling, and WiFi on: " + myPhone.getBatteryLife() + " hours");

        // Turn off voice calling and put the phone in standby
        myPhone.turnOffVoiceCalling();
        myPhone.putInStandby();

        // Display battery life with modified settings
        System.out.println("Battery life with screen on, voice calling off, and standby mode: " + myPhone.getBatteryLife() + " hours");
    }
}
