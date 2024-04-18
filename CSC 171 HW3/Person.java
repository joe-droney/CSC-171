public class Person {
    private String name;
    private int birthYear;
    private String occupation;
    private boolean alive;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.alive = true;
    }

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    
    public int getBirthYear() {
        return birthYear;
    }

   
    @Override
    public String toString() {
        String status = alive ? "is" : "was";
        return name + " " + status + " a " + occupation + " born in " + birthYear + ".";
    }

    public static void main(String[] args) {
        // Example usage:
        Person einstein = new Person("Albert Einstein", 1879);
        einstein.setOccupation("physicist");

        Person obama = new Person("Barack Obama", 1961);
        obama.setOccupation("politician");

        Person frida = new Person("Frida Kahlo", 1954);
        frida.setOccupation("artist");
        frida.setAlive(false);

        System.out.println(einstein.toString());
        System.out.println(obama.toString());
        System.out.println(frida.toString());
    }
}
