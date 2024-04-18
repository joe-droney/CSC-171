public class Penguin {
    private String name;
    private int age;
    private int happinessLevel;

    public Penguin(String name, int age) {
        this.name = name;
        this.age = age;
        this.happinessLevel = 50; // Initialize happiness level to a moderate value (between 0 and 100)
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

        public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

   
    public int getHappinessLevel() {
        return happinessLevel;
    }

    // Method to simulate the penguin swimming
    public void swim() {
        // Swimming increases happiness, but it also consumes energy
        int energyConsumed = 10;
        int happinessIncrease = 15;

        // Check if the penguin has enough energy to swim
        if (energyConsumed <= 100 - happinessLevel) {
            happinessLevel += happinessIncrease;
            System.out.println(name + " the penguin is swimming and feeling happier!");
            // Decrease energy level after swimming
            happinessLevel -= energyConsumed;
        } else {
            System.out.println(name + " the penguin is too tired to swim.");
        }
    }

    // Override the toString method to display penguin's happiness
    @Override
    public String toString() {
        if (happinessLevel >= 51) {
            return name + " the penguin is happy!";
        } else {
            return name + " the penguin is not happy.";
        }
    }

    public static void main(String[] args) {
        // Example usage:
        Penguin penguin1 = new Penguin("Waddles", 5);
        Penguin penguin2 = new Penguin("Chilly", 3);

        penguin1.swim(); // Waddles swims
        penguin2.swim(); // Chilly swims

        System.out.println(penguin1);
        System.out.println(penguin2);
    }
}
