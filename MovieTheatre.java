package movietheatreproject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieTheatre {
    public static void main(String[] args) {
        String[][] seats = new String[5][5];
        initializeSeats(seats);
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\nWelcome to the Movie Theatre Reservation System");
            System.out.println("1. View Seating Chart");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a Reserved Seat");
            System.out.println("4. Exit");
            System.out.print("Please choose an option (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    displaySeats(seats);
                    break;
                case 2:
                    System.out.print("Enter the seat to reserve (e.g., A1): ");
                    String reserveSeat = scanner.nextLine().toUpperCase();
                    reserveSeat(seats, reserveSeat);
                    break;
                case 3:
                    System.out.print("Enter the reserved seat to cancel (e.g., A1): ");
                    String cancelSeat = scanner.nextLine().toUpperCase();
                    cancelSeat(seats, cancelSeat);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    // Initialize seats with row letters and column numbers
    public static void initializeSeats(String[][] seats) {
        char rowLetter = 'A';
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                seats[row][col] = rowLetter + String.valueOf(col + 1);
            }
            rowLetter++;
        }
    }
    
    public static void displaySeats(String[][] seats) {
        System.out.println("Current Seating Arrangement:");
        List<String> availableSeats = new ArrayList<>();
        
        // Display seats and collect available ones. i wanted to use the printf to keep the seats in a grid so they are aligned
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                System.out.printf("%-3s", seats[row][col]);
                if (!seats[row][col].equals("X")) {
                    availableSeats.add(seats[row][col]);
                }
            }
            System.out.println();
        }
        
        // Display available seats
        System.out.println("\nAvailable Seats: " + availableSeats);
    }
    
    // Reserve a seat by marking it with an "X" (checks if already reserved)
    public static void reserveSeat(String[][] seats, String seatNumber) {
        boolean seatFound = false;
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if (seats[row][col].equals(seatNumber)) {
                    if (seats[row][col].equals("X")) {
                        System.out.println("Sorry, seat " + seatNumber + " is already reserved.");
                    } else {
                        seats[row][col] = "X"; // Mark reserved seat with an "X"
                        System.out.println("Seat " + seatNumber + " has been reserved.");
                    }
                    seatFound = true;
                    break;
                }
            }
        }
        if (!seatFound) {
            System.out.println("Seat " + seatNumber + " doesn't exist.");
        }
        
        // Display seating arrangement after attempting to reserve
        displaySeats(seats);
    }
    
    // Cancel a reserved seat by restoring its seat number
    public static void cancelSeat(String[][] seats, String seatNumber) {
        boolean seatFound = false;
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if (seats[row][col].equals("X") && getSeatNumber(row, col).equals(seatNumber)) {
                    seats[row][col] = getSeatNumber(row, col); // Restore seat number
                    System.out.println("Seat " + seatNumber + " reservation has been canceled.");
                    seatFound = true;
                    break;
                }
            }
        }
        if (!seatFound) {
            System.out.println("Seat " + seatNumber + " was not reserved or doesn't exist.");
        }
        
        // Display seating arrangement after canceling reservation
        displaySeats(seats);
    }
    
    // Helper method to get seat number in the format "A1", "B2", etc.
    public static String getSeatNumber(int row, int col) {
        return (char)('A' + row) + String.valueOf(col + 1);
    }
}
