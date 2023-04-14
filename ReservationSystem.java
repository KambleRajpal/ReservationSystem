import java.util.ArrayList;
import java.util.Scanner;

public class ReservationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> reservationList = new ArrayList<>();
    private static ArrayList<String> cancelledReservationList = new ArrayList<>();
    private static boolean isLoggedIn = false;

    public static void main(String[] args) {
        showLoginForm();

        while (isLoggedIn) {
            System.out.println("Welcome to the Reservation System. Please select an option:");
            System.out.println("1. Reserve a seat");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Logout");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume the new line character

            switch (option) {
                case 1:
                    reserveSeat();
                    break;

                case 2:
                    cancelReservation();
                    break;

                case 3:
                    isLoggedIn = false;
                    System.out.println("Logged out successfully.");
                    showLoginForm();
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            System.out.println("Current Reservations:");
            for (String reservation : reservationList) {
                System.out.println(reservation);
            }
            System.out.println();
        }
    }

    private static void showLoginForm() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("password")) {
            isLoggedIn = true;
            System.out.println("Logged in successfully.");
        } else {
            System.out.println("Incorrect username or password. Please try again.");
            showLoginForm();
        }
    }

    private static void reserveSeat() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the number of seats to reserve: ");
        int numSeats = scanner.nextInt();
        scanner.nextLine(); // consume the new line character

        String reservation = String.format("%s reserved %d seats.", name, numSeats);
        reservationList.add(reservation);

        System.out.printf("Reservation successful for %s. Reserved %d seats.\n", name, numSeats);
    }

    private static void cancelReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the number of seats to cancel: ");
        int numSeats = scanner.nextInt();
        scanner.nextLine(); // consume the new line character

        String reservation = String.format("%s cancelled %d seats.", name, numSeats);
        cancelledReservationList.add(reservation);

        for (int i = 0; i < reservationList.size(); i++) {
            String existingReservation = reservationList.get(i);
            if (existingReservation.contains(name)) {
                String[] splitReservation = existingReservation.split(" ");
                int reservedSeats = Integer.parseInt(splitReservation[2]);
                if (reservedSeats == numSeats) {
                    reservationList.remove(i);
                } else {
                    int updatedSeats = reservedSeats - numSeats;
                    String updatedReservation = String.format("%s reserved %d seats.", name, updatedSeats);
                    reservationList.set(i, updatedReservation);
                }
                System.out.printf("Cancellation successful for %s. Cancelled %d seats.\n", name, numSeats);
                return;
            }
        }

        System.out.println("Reservation not found.");
    }
}
