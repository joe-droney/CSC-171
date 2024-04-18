/*Joe Droney
 * jdroney2@u.rochester.edu
 * 
 * 12/4/2023
 */




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    String name;
    double mass;
    double maxCargo;
    double range;
    double speed;
    double hourlyCost;
    double fuelBurn;

    public Vehicle(String name, double mass, double maxCargo, double range, double speed, double hourlyCost, double fuelBurn) {
        this.name = name;
        this.mass = mass;
        this.maxCargo = maxCargo;
        this.range = range;
        this.speed = speed;
        this.hourlyCost = hourlyCost;
        this.fuelBurn = fuelBurn;
    }

    public double calculateCost(double distance, double cargoMass) {
        double travelTime = distance / speed;
        double totalCost = travelTime * hourlyCost; // Use travelTime directly without adding 1 hour
        totalCost += distance * fuelBurn;
        return totalCost;
    }

    public BigDecimal calculateProfit(double distance, double cargoMass, double payment) {
        if (cargoMass > maxCargo || distance > range) {
            // Vehicle cannot handle the contract
            return BigDecimal.ZERO;
        }

        BigDecimal revenue = BigDecimal.valueOf(payment);
        BigDecimal cost = BigDecimal.valueOf(calculateCost(distance, cargoMass));
        BigDecimal profit = revenue.subtract(cost);
        return profit.max(BigDecimal.ZERO); // Ensure profit is non-negative
    }
}

public class SupplyChainHW {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ensure that there is at least one line of input
        if (!scanner.hasNextLine()) {
            System.out.println("No input provided.");
            return;
        }

        // Parse the number of vehicles
        int numVehicles;
        try {
            numVehicles = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of vehicles.");
            return;
        }

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Input fleet configuration
        for (int i = 0; i < numVehicles; i++) {
            String[] data = scanner.nextLine().split(" ");
            vehicles.add(new Vehicle(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]),
                    Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                    Double.parseDouble(data[6])));
        }

        BigDecimal totalProfit = BigDecimal.ZERO;

        // Input and process contracts
        while (scanner.hasNextLine()) {
            String[] contractData = scanner.nextLine().split(" ");

            if (contractData[0].equals("quit")) {
                break;
            }

            // Add a check for empty string before parsing as double
            if (contractData.length != 3 || contractData[0].isEmpty() || contractData[1].isEmpty() || contractData[2].isEmpty()) {
                System.out.println("Invalid contract format.");
                return;
            }

            double cargoMass = Double.parseDouble(contractData[0]);
            double distance = Double.parseDouble(contractData[1]);
            double payment = Double.parseDouble(contractData[2]);

            Vehicle bestOption = findBestOption(vehicles, cargoMass, distance, payment);

            if (bestOption == null) {
                System.out.println("Decline");
            } else {
                BigDecimal profit = bestOption.calculateProfit(distance, cargoMass, payment);
                System.out.printf("%s %.2f\n", bestOption.name, profit);
                totalProfit = totalProfit.add(profit);
            }
        }

        System.out.printf("TotalProfit: %.2f\n", totalProfit);
    }

    public static Vehicle findBestOption(ArrayList<Vehicle> vehicles, double cargoMass, double distance, double payment) {
        BigDecimal maxProfit = BigDecimal.ZERO;
        Vehicle bestOption = null;

        for (Vehicle vehicle : vehicles) {
            BigDecimal profit = vehicle.calculateProfit(distance, cargoMass, payment);
            if (profit.compareTo(maxProfit) > 0) {
                maxProfit = profit;
                bestOption = vehicle;
            }
        }

        return bestOption;
    }
}
