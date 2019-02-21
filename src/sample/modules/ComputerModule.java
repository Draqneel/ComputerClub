package sample.modules;

import sample.models.Computer;
import sample.models.User;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class ComputerModule {

    private static String fileName = "computers.txt";

    public static void saveData(ArrayList<Computer> computers){
        for (Computer computer:
             computers) {
            saveData(computer);
        }
    }

    public static void saveData(Computer computer){
        FileWorker.saveData(computer.toString(),fileName);
    }

    public static ArrayList<Computer> loadData(){
        ArrayList<Computer> computers = new ArrayList<>();
        ArrayList<String> strings = FileWorker.loadData(fileName);
        for(String string: strings){
            computers.add(new Computer(
                    Integer.parseInt(string.split(" ")[0]),
                    string.split(" ")[1],
                    string.split(" ")[2],
                    Integer.parseInt(string.split(" ")[3]),
                    Integer.parseInt(string.split(" ")[4]),
                    string.split(" ")[5]
            ));
        }
        return computers;
    }

    public static ArrayList<String> loadDescriptions(ArrayList<Computer> computers){
        ArrayList<String> descriptions = new ArrayList<>();
        computers.forEach(computer -> descriptions.add(computer.getDescription()));
        return descriptions;
    }

    public static boolean delete(int id){
        return FileWorker.delete(id,fileName);
    }
}
