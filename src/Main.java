import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {


    static class Time {

        int min;
        int hour;

        int day;

        public Time(int hour, int min, int day) {
            this.min = min;
            this.hour = hour;
            this.day = day;
        }


    }

    static class Ship {
        String shipName;
        int passengersCount;
        Time timeDeparture;
        Time timeArrival;
        int dayOfDeparture;
        int dayOfArrival;

        public Ship(String shipName, int passengersCount, Time timeDeparture, Time timeArrival) {
            this.shipName = shipName;
            this.passengersCount = passengersCount;
            this.timeDeparture = timeDeparture;
            this.timeArrival = timeArrival;
        }

    }

    static void printShipsToSystemFile(Ship[] ships, String place, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(Integer.toString(ships.length));
        bufferedWriter.newLine();
        for (int i = 0; i < ships.length; i++) {

            bufferedWriter.write(ships[i].shipName);
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(ships[i].passengersCount));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(ships[i].timeDeparture.day));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(ships[i].timeDeparture.hour));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(ships[i].timeDeparture.min));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(ships[i].timeArrival.day));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(ships[i].timeArrival.hour));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(ships[i].timeArrival.min));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
    }


    static void printShipsToUserFile(Ship[] ships,String filename) throws IOException {

        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Number of ships:");
        bufferedWriter.newLine();
        bufferedWriter.write(Integer.toString(ships.length));
        bufferedWriter.newLine();

        for (int i = 0; i < ships.length; i++) {
            bufferedWriter.write("Ship name: ");
            bufferedWriter.write(ships[i].shipName);
            bufferedWriter.newLine();
            bufferedWriter.write("Passengers: ");
            bufferedWriter.write(Integer.toString(ships[i].passengersCount));
            bufferedWriter.newLine();
            bufferedWriter.write("Day of departure: ");
            bufferedWriter.write(Integer.toString(ships[i].timeDeparture.day));
            bufferedWriter.newLine();
            bufferedWriter.write("Hour of departure: ");
            bufferedWriter.write(Integer.toString(ships[i].timeDeparture.hour));
            bufferedWriter.newLine();
            bufferedWriter.write("Minute of departure: ");
            bufferedWriter.write(Integer.toString(ships[i].timeDeparture.min));
            bufferedWriter.newLine();
            bufferedWriter.write("Day of arrival: ");
            bufferedWriter.write(Integer.toString(ships[i].timeArrival.day));
            bufferedWriter.newLine();
            bufferedWriter.write("Hour of arrival: ");
            bufferedWriter.write(Integer.toString(ships[i].timeArrival.hour));
            bufferedWriter.newLine();
            bufferedWriter.write("Minute of arrival: ");
            bufferedWriter.write(Integer.toString(ships[i].timeArrival.min));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
    }
    static Ship[] readShipsFromSystemFile(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int countShips = Integer.parseInt(bufferedReader.readLine());
        Ship[] ships = new Ship[countShips];

        int passengersCount = 0;
        String shipName = " ";

        for (int i = 0; i < ships.length; i++) {
            Time timeDeparture = new Time(0, 0, 0);
            Time timeArrival = new Time(0, 0, 0);
            shipName = bufferedReader.readLine();
            passengersCount = Integer.parseInt(bufferedReader.readLine());
            timeDeparture.day = Integer.parseInt(bufferedReader.readLine());
            timeDeparture.hour = Integer.parseInt(bufferedReader.readLine());
            timeDeparture.min = Integer.parseInt(bufferedReader.readLine());
            timeArrival.day = Integer.parseInt(bufferedReader.readLine());
            timeArrival.hour = Integer.parseInt(bufferedReader.readLine());
            timeArrival.min = Integer.parseInt(bufferedReader.readLine());

            ships[i] = new Ship(shipName, passengersCount, timeDeparture, timeArrival);
        }

        bufferedReader.close();
        fileReader.close();

        return ships;
    }

    /**
     * Create array for tests
     *
     * @param countOfPlanes
     * @param shipName
     * @return
     */
    static Ship[] createShips(int countOfPlanes, String shipName) {
        int passengersCount = 0;
        Ship[] ships = new Ship[countOfPlanes];

        for (int i = 0; i < ships.length; i++) {
            Random random = new Random();
            Time timeDeparture = new Time(random.nextInt(24), random.nextInt(61), random.nextInt(30) + 1);
            Time timeArrival = new Time(random.nextInt(24), random.nextInt(61), random.nextInt(30) + 1);
            passengersCount = random.nextInt(2000) + 1500;
            ships[i] = new Ship(shipName, passengersCount, timeDeparture, timeArrival);
        }
        return ships;
    }

    static Ship[] checkShipsArrivesStatus(Ship[] planes, Time time) {

        Ship[] shipsArrived = new Ship[planes.length];
        for (int i = 0; i < shipsArrived.length; i++) {
            if (planes[i].timeArrival.day >= getTime().day) {
                if (planes[i].timeArrival.hour <= getTime().hour) {
                    if (planes[i].timeArrival.min <= getTime().min) {
                        shipsArrived[i] = planes[i];
                    }
                }
            }
        }
        return shipsArrived;
    }

    static Ship[] checkShipsNotDepartureStatus(Ship[] ships, Time time) {

        Ship[] shipsNotDeparted = new Ship[ships.length];
        for (int i = 0; i < shipsNotDeparted.length; i++) {
            if (ships[i].timeDeparture.day <= getTime().day) {
                if (ships[i].timeDeparture.hour > getTime().hour) {
                    if (ships[i].timeDeparture.min > getTime().min) {
                        shipsNotDeparted[i] = ships[i];
                    }
                }
            }

        }
        return shipsNotDeparted;
    }

    static void sortShipsAboutTimeOfDeparture(Ship[] ships) {

        for (int i = 0; i < ships.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < ships.length; j++) {

                if (ships[j].timeDeparture.day < ships[index].timeDeparture.day) {

                    index = j;

                } else if (ships[j].timeDeparture.day == ships[index].timeDeparture.day) {

                    if (ships[j].timeDeparture.hour == ships[index].timeDeparture.hour) {

                        if (ships[j].timeDeparture.min < ships[index].timeDeparture.min) {

                            index = j;
                        }
                    } else if (ships[j].timeDeparture.hour < ships[index].timeDeparture.hour) {

                        index = j;
                    }
                }

            }

            Ship temp = ships[index];
            ships[index] = ships[i];
            ships[i] = temp;
        }

    }

    static int enteringIntValue(String message, int rightSide, int leftSide) {
        System.out.println(message);

        int value = 0;
        boolean error;

        do {
            error = false;

            try {
                Scanner sc = new Scanner(System.in);
                value = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("It is not a number.");
                error = true;
            }

            if (value > rightSide || value < leftSide) {
                error = true;
                System.out.println("Wrong value");
            }

        } while (error);

        return value;
    }


    static void sortShipsAboutPassengersCount(Ship[] ships) {
        for (int i = 0; i < ships.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < ships.length; j++) {

                if (ships[j].passengersCount < ships[index].passengersCount) {
                    index = j;
                }

                Ship temp = ships[index];
                ships[index] = ships[i];
                ships[i] = temp;
            }
        }
    }


    static void printShips(Ship[] ships) {

        String arrivalTimeHour = "p";
        String arrivalTimeMin = "p";
        String departTimeHour = "p";
        String departTimeMin = "p";
        System.out.println(String.format("%-15s%-15s%20s%20s%s%s%20s%20s%s%s",
                "Ship name:","Passengers:","Dep. day:","Dep. hour",":","Dep. min",
                "Arrival day:","Arr. hour",":","Arr. min"));
        for (int i = 0; i < ships.length; i++) {

            if (ships[i] != null) {
                arrivalTimeHour = Integer.toString(ships[i].timeArrival.hour);
                arrivalTimeMin = Integer.toString(ships[i].timeArrival.min);
                departTimeHour = Integer.toString(ships[i].timeDeparture.hour);
                departTimeMin = Integer.toString(ships[i].timeDeparture.min);
                if (ships[i].timeArrival.hour < 10) {
                    arrivalTimeHour = "0" + Integer.toString(ships[i].timeArrival.hour);
                }
                if (ships[i].timeArrival.min < 10) {
                    arrivalTimeMin = "0" + Integer.toString(ships[i].timeArrival.min);
                }
                if (ships[i].timeDeparture.hour < 10) {
                    departTimeHour = "0" + Integer.toString(ships[i].timeDeparture.hour);
                }
                if (ships[i].timeDeparture.min < 10) {
                    departTimeMin = "0" + Integer.toString(ships[i].timeDeparture.min);
                }

                System.out.println(String.format("%-15s%-15d%20d%20s%s%s%26d%20s%s%s",
                        ships[i].shipName,ships[i].passengersCount,ships[i].timeDeparture.day,departTimeHour,":",departTimeMin,
                        ships[i].timeArrival.day,arrivalTimeHour,":",arrivalTimeMin));

            }

        }
    }

    static Time getTime() {
        Time time = new Time(0, 0, 1);
        Date dateNow = new Date();
        String date = dateNow.toString();
        time.day = Integer.parseInt(date.substring(8, 10));
        time.hour = Integer.parseInt(date.substring(11, 13));
        time.min = Integer.parseInt(date.substring(14, 16));
        return time;
    }


    public static void main(String[] args) throws InterruptedException, IOException {

        Random random = new Random();

        int countOfShips = 10;

        Ship[] ships = readShipsFromSystemFile("ocean.txt");
       //Ship[] ships = createShips(10,"lodka");
       //printShipsToSystemFile(ships,"Ocean","ocean.txt");

        System.out.println("Choose sort way:");
        System.out.println("1:from passengers count");
        System.out.println("2:from departure time");

        int sortWay = enteringIntValue("Enter a number of sort:", 2, 1);

        int sleep = enteringIntValue("Enter a sleep time:", 60000, 5000);

        if (sortWay == 1) {
            sortShipsAboutPassengersCount(ships);
        } else if (sortWay == 2) {
            sortShipsAboutTimeOfDeparture(ships);
        }

        printShipsToUserFile(ships,"user.txt");
        do {

            System.out.println("Date and time now:");
            Date dateNow = new Date();
            String date = dateNow.toString();
            System.out.println(date);

            System.out.println("All ships:");
            printShips(ships);

            System.out.println("Ships that have arrived yet:");
            printShips(checkShipsArrivesStatus(ships, getTime()));

            System.out.println("Ships that haven't departed:");
            printShips(checkShipsNotDepartureStatus(ships, getTime()));

            Thread.sleep(sleep);

        } while (true);


    }
}









