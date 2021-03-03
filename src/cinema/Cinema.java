package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();

        String[][] cinema = forShow(rows, seats);
        int soldTickets = 0;
        int cur_inc = 0;

        while (true) {
            System.out.println();
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            System.out.println();

            if (choice == 0) {
                return;
            } else if (choice == 3) {
                statistics(soldTickets, rows, seats, cur_inc);
            } else {
                switch (choice) {
                    case 1:
                        showSeats(cinema);
                        break;
                    case 2:
                        int taken = 0;
                        while (taken == 0) {
                            System.out.println("Enter a row number:");
                            int rowClient = scanner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            int seatClient = scanner.nextInt();

                            if (rowClient > rows || seatClient > seats){
                                System.out.println();
                                System.out.println("Wrong input!");
                                System.out.println();
                                continue;
                            } else if (cinema[rowClient - 1][seatClient - 1] == "B") {
                                System.out.println();
                                System.out.println("That ticket has already been purchased!");
                                System.out.println();
                                continue;

                            } else {
                                System.out.println();
                                System.out.println("Ticket price: $" +
                                        priceForTicket(cinema, rows, seats, rowClient));
                                buyTicket(cinema, rowClient, seatClient);
                                cur_inc += priceForTicket(cinema, rows, seats, rowClient);
                                soldTickets++;
                                taken = 1;
                            }
                        }

                }
            }
        }

    }
    public static void showSeats(String[][] myAr) {
        String[][] newArray = myAr;
        int[] seats = new int[newArray[0].length];
        int count = 1;
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int x : seats) {
            x = count;
            System.out.print(" " + x);
            count++;
        }
        System.out.println();
        for (int i = 0; i < myAr.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < newArray[0].length; j++) {
                System.out.print(" " + myAr[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] forShow(int rows, int seats) {
        String[][] newCinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                newCinema[i][j] = "S";
            }
        }
        return newCinema;
    }

    public static void buyTicket(String[][] cinema, int rows, int seats) {
        cinema[rows - 1][seats - 1] = "B";
    }

    public static int priceForTicket(String[][] cinema, int rows, int seats, int rowClient) {
        int price = 0;

        if (rows * seats <= 60) {
            price = 10;
        } else {
            if (rowClient == 1){
                price = 10;
            } else if (rowClient <= rows / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return price;
    }

    public static void statistics(int soldTickets, int rows, int seats, int cur_inc) {
        System.out.printf("Number of purchased tickets: %d", soldTickets);
        System.out.println();
        double sold1 = soldTickets;
        double rows1 = rows;
        double seats1 = seats;
        double perc = (sold1 / (rows1 * seats1)) * 100;
        System.out.printf("Percentage: %.2f%s", perc, "%");
        System.out.println();
        System.out.printf("Current income: $%d", cur_inc);
        System.out.println();

        int income = 0;
        if (rows * seats <= 60) {
            income = rows * seats * 10;
        } else {
            income =  (rows / 2)* seats * 10 + (rows - rows / 2) * seats * 8;
        }
        System.out.printf("Total income: $%d", income);
        System.out.println();
    }
}
