package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CarManager;
import ba.unsa.etf.rpr.business.RentManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Car;
import ba.unsa.etf.rpr.domain.Rent;
import ba.unsa.etf.rpr.domain.User;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

/**
 * class for CLI implementation
 *
 */
public class App{

    private static final Option addCar = new Option("aC","add-car",false, "Adding new car to database");
    private static final Option addUser = new Option("aU","add-user",false, "Adding new user to database");
    private static final Option addRent = new Option("aR","add-rent",false, "Adding new rent to database");
    private static final Option getCars = new Option("getC", "get-cars",false, "Printing all cars from database");
    private static final Option getUsers = new Option("getU", "get-users",false, "Printing all users from database");
    private static final Option getRents = new Option("getR","get-rents",false, "Printing all rents from database");
    private static final Option returnCar = new Option("ret","return-car", false, "Returns a car from selected rent");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 100, "java -jar rpr-Projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 100, options, 5, 5);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addCar);
        options.addOption(addUser);
        options.addOption(addRent);
        options.addOption(getCars);
        options.addOption(getUsers);
        options.addOption(getRents);
        options.addOption(returnCar);
        return options;
    }
    public static void main( String[] args ){
        try{
            CarManager carManager = new CarManager();
            UserManager userManager = new UserManager();
            RentManager rentManager = new RentManager();
            Options options = addOptions();
            CommandLineParser clp = new DefaultParser();
            CommandLine cl = clp.parse(options, args);


            if ((cl.hasOption(addCar.getOpt()) || cl.hasOption(addCar.getLongOpt()))) {
                Car car = new Car();
                car.setMake(cl.getArgList().get(0));
                car.setModel(cl.getArgList().get(1));
                car.setColor(cl.getArgList().get(2));
                car.setRegistration(cl.getArgList().get(3));
                car.setPrice(Integer.parseInt(cl.getArgList().get(4)));
                carManager.add(car);
                System.out.println("You successfully added a car to database!");
            } else if ((cl.hasOption(addUser.getOpt()) || cl.hasOption(addUser.getLongOpt()))) {
                User user = new User();
                user.setFirstName(cl.getArgList().get(0));
                user.setLastName(cl.getArgList().get(1));
                user.setLicense(cl.getArgList().get(2));
                user.setBirthdate(new Date(Integer.parseInt(cl.getArgList().get(5)), Integer.parseInt(cl.getArgList().get(4)), Integer.parseInt(cl.getArgList().get(3))));
                userManager.add(user);
                System.out.println("You successfully added a user to database!");
            } else if ((cl.hasOption(addRent.getOpt()) || cl.hasOption(addRent.getLongOpt()))) {
                Rent rent = new Rent();
                rent.setCar(carManager.getById(Integer.parseInt(cl.getArgList().get(0))));
                rent.setUser(userManager.getById(Integer.parseInt(cl.getArgList().get(1))));
                if (cl.getArgList().get(2).equals("current")) {
                    rent.setStartDate(new Date(System.currentTimeMillis()));
                    rent.setEndDate(new Date(Integer.parseInt(cl.getArgList().get(5)), Integer.parseInt(cl.getArgList().get(4)), Integer.parseInt(cl.getArgList().get(3))));
                } else {
                    rent.setStartDate(new Date(Integer.parseInt(cl.getArgList().get(4)), Integer.parseInt(cl.getArgList().get(3)), Integer.parseInt(cl.getArgList().get(2))));
                    rent.setEndDate(new Date(Integer.parseInt(cl.getArgList().get(7)), Integer.parseInt(cl.getArgList().get(6)), Integer.parseInt(cl.getArgList().get(5))));
                }
                rentManager.add(rent);
                System.out.println("You successfully added a rent to database!");
            } else if ((cl.hasOption(returnCar.getOpt()) || cl.hasOption(returnCar.getLongOpt()))) {
                Rent rent = rentManager.getById(Integer.parseInt(cl.getArgList().get(0)));
                int previousRent = rent.getRentPrice();
                rent.setEndDate(new Date(System.currentTimeMillis()));
                rentManager.update(rent);
                System.out.println("You successfully returned a car");
                System.out.println("You should return " + (previousRent - rent.getRentPrice()) + " to the customer!");
            } else if (cl.hasOption(getRents.getOpt()) || cl.hasOption(getRents.getLongOpt())) {
                List<Rent> rents = rentManager.getAll();
                for (Rent r : rents) {
                    System.out.println(r.getId() + " " + r + " " + r.getStartDate() + " " + r.getEndDate());
                }
            } else if (cl.hasOption(getCars.getOpt()) || cl.hasOption(getCars.getLongOpt())) {
                List<Car> cars = carManager.getAll();
                for (Car c : cars) {
                    System.out.println(c.getId() + ". " + c);
                }
            } else if (cl.hasOption(getUsers.getOpt()) || cl.hasOption(getUsers.getLongOpt())) {
                List<User> users = userManager.getAll();
                for (User u : users) {
                    System.out.println(u.getId() + ". " + u);
                }
            } else {
                printFormattedOptions(options);
                System.exit(-1);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}
