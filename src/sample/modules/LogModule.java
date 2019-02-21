package sample.modules;

import sample.models.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class LogModule {
    private static String fileName = "logs.txt";

    public static void saveData(ArrayList<Log> logs) {
        for (Log log : logs)
            saveData(log);
    }

    public static void saveData(Log log) {
        FileWorker.saveData(log.toString(),fileName);
    }

    public static ArrayList<String> loadData() {
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);

        ArrayList<String> logs = new ArrayList<>();
        ArrayList<String> strings = FileWorker.loadData(fileName);

        strings.forEach(s -> {
            String str = s.split(" ")[3];
            String str2 = s.split(" ")[4];
            String data = df.format(new Date(Long.parseLong(str)));
            s = s.replace(str,data);
            data = df.format(new Date(Long.parseLong(str2)));

            s = s.replace(str2, data);
            logs.add(s);
        });
        return logs;
    }


}
