import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[20];
        Scanner read = new Scanner(System.in);
        char command; // user menu choice

        // counter variable for how many vehicles are in the array right now
        int vehicleCount = 0;
        // Preload the array with 6 vehicles

        // vehicleId,makeModel,color,odometerReading,price
        vehicles[vehicleCount++] = new Vehicle(101121,"Ford Explorer",
                "Red",45000,13500);
        vehicles[vehicleCount++] = new Vehicle(101122,"Toyota Camry",
                "Blue",60000,11000);
        vehicles[vehicleCount++] = new Vehicle(101123,"Chevrolet Malibu",
                "Black",50000,9700);
        vehicles[vehicleCount++] = new Vehicle(101124,"Honda Civic",
                "White",70000,7500);
        vehicles[vehicleCount++] = new Vehicle(101125,"Subaru Outback",
                "Green",55000,14500);
        vehicles[vehicleCount++] = new Vehicle(101126,"Jeep Wrangler",
                "Yellow",30000,16000);

        do {
            System.out.println("What do you want to do?");
            System.out.println(" 1 - List all vehicles");
            System.out.println(" 2 - Search by make/model");
            System.out.println(" 3 - Search by price range");
            System.out.println(" 4 - Search by color");
            System.out.println(" 5 - Add a vehicle");
            System.out.println(" 6 - Quit");
            System.out.print("Enter your command: ");

            command = read.next().charAt(0);
            read.nextLine();

            switch(command) {
                case '1' -> listAllVehicles(vehicles);
                case '2' -> findVehiclesByMakeModel(vehicles, read);
                case '3' -> findVehiclesByPriceRange(vehicles, read);
                case '4' -> findVehiclesByColor(vehicles, read);
                case '5' -> addVehicle(vehicles, vehicleCount, read);
                default -> command = '6';
            }
        } while (command != '6');

        read.close();
    }

    static void listAllVehicles(Vehicle[] vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle == null) {
                break;
            }
            System.out.printf("%d | %s | %s | %d | $%.2f%n", vehicle.getVehicleId(), vehicle.getMakeModel(),
                    vehicle.getColor(), vehicle.getOdometerReading(), vehicle.getPrice());
        }
    }

    static void findVehiclesByMakeModel(Vehicle[] vehicles, Scanner read) {
        System.out.print("Enter Make/Model: ");
        String searchModel = read.nextLine().trim();

        boolean found = false;

        for (Vehicle vehicle : vehicles) {
            // Skip empty array elements
            if (vehicle == null) {
                continue;
            }

            if (vehicle.getMakeModel().equalsIgnoreCase(searchModel)) {
                System.out.printf("%d | %s | %s | %d | $%.2f%n",
                        vehicle.getVehicleId(),
                        vehicle.getMakeModel(),
                        vehicle.getColor(),
                        vehicle.getOdometerReading(),
                        vehicle.getPrice());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles found matching: " + searchModel);
        }
    }

    static void findVehiclesByPriceRange(Vehicle[] vehicles, Scanner read) {
        System.out.print("Enter minimum price: ");
        double minPrice = read.nextDouble();

        System.out.print("Enter maximum price: ");
        double maxPrice = read.nextDouble();

        read.nextLine(); // Consume leftover newline

        boolean found = false;

        for (Vehicle vehicle : vehicles) {
            if (vehicle == null) {
                continue;
            }

            double price = vehicle.getPrice();

            if (price >= minPrice && price <= maxPrice) {
                System.out.printf("%d | %s | %s | %d | $%.2f%n",
                        vehicle.getVehicleId(),
                        vehicle.getMakeModel(),
                        vehicle.getColor(),
                        vehicle.getOdometerReading(),
                        vehicle.getPrice());

                found = true;
            }
        }

        if (!found) {
            System.out.printf("No vehicles found between $%.2f and $%.2f.%n",
                    minPrice, maxPrice);
        }
    }

    static void findVehiclesByColor(Vehicle[] vehicles, Scanner read) {
        System.out.print("Enter color: ");
        String searchColor = read.nextLine().trim();

        boolean found = false;

        for (Vehicle vehicle : vehicles) {
            if (vehicle == null) {
                continue;
            }

            if (vehicle.getColor().equalsIgnoreCase(searchColor)) {
                System.out.printf("%d | %s | %s | %d | $%.2f%n",
                        vehicle.getVehicleId(),
                        vehicle.getMakeModel(),
                        vehicle.getColor(),
                        vehicle.getOdometerReading(),
                        vehicle.getPrice());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles found with color: " + searchColor);
        }
    }

    static void addVehicle(Vehicle[] vehicles, int vehicleCount, Scanner read) {
        // Check whether the array is full
        if (vehicleCount >= vehicles.length) {
            System.out.println("Inventory is full! Cannot add more vehicles.");
            return;
        }

        System.out.print("Enter vehicle ID: ");
        long vehicleId = read.nextLong();
        read.nextLine(); // consume CRLF

        System.out.print("Enter make/model: ");
        String makeModel = read.nextLine().trim();

        System.out.print("Enter color: ");
        String color = read.nextLine().trim();

        System.out.print("Enter odometer reading: ");
        int odometerReading = read.nextInt();

        System.out.print("Enter price: ");
        double price = read.nextDouble();
        read.nextLine(); // consume CRLF

        // Create and store the new vehicle
        vehicles[vehicleCount] = new Vehicle(vehicleId, makeModel, color,
                odometerReading, price);

        vehicleCount++; // updates and keeps track of vehicle count

        System.out.println("Vehicle added successfully!");
    }
}
