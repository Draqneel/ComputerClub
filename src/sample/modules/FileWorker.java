package sample.modules;

import java.io.*;
import java.util.ArrayList;

public class FileWorker {
    public static ArrayList<String> loadData(String fileName) {
        ArrayList<String> strings = new ArrayList<>();
        File file = new File("resources" );
        if(!file.exists()){
            file.mkdir();

        }
        file = new File("resources",fileName);
        StringBuilder sb = new StringBuilder();
        if (!file.exists()) {
            try {

                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e + "\n" + fileName + " " + file);
            }
            return strings;
        }

        if (file.canRead()) {
            try {
                BufferedReader input = new BufferedReader(new FileReader(file));

                try {
                    String s;
                    while ((s = input.readLine()) != null) {
                        strings.add(s);
                    }
                } finally {
                    input.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return strings;
    }

    public static void saveData(String str,String fileName){
        File file = new File("./resources/" + fileName);
        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        String id = str.split(" ")[0];
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(!file.canWrite() && !file.canRead()){
            throw new RuntimeException();
        }
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            try{
                String s;
                while ((s = input.readLine())!=null){
                    if(s.split(" ")[0].equals(id)){
                        sb.append(str);
                        flag = false;
                    }
                    else{
                        sb.append(s);
                    }
                    sb.append("\n");
                }
            }
            finally {
                if(flag){
                    sb.append(str+"\n");
                }
                input.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        write(sb,file);

    }


    public static String findById(long id,String fileName){
        File file = new File("./resources/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(!file.canWrite() && !file.canRead()){
            throw new RuntimeException();
        }
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String s;
                while ((s = input.readLine())!=null){
                    if(id == Long.parseLong(s.split(" ")[0])){
                        return s;
                    }
                }
            }finally {
                input.close();
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return "";
    }

    public static boolean delete(long id,String fileName){
        boolean flag = false;
        File file = new File("./resources/" , fileName);
        StringBuilder sb = new StringBuilder();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(!file.canWrite() && !file.canRead()){
            throw new RuntimeException();
        }
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            try{
                String s;
                while ((s = input.readLine())!=null){
                    if(s.split(" ")[0].equals(String.valueOf(id))){
                        flag = true;
                        continue;
                    }
                    else{
                        sb.append(s);
                    }
                    sb.append("\n");
                }
            }
            finally {
                input.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        write(sb,file);
        return true;
    }

    private static void write(StringBuilder sb,File file){
        try {
            try (PrintWriter out = new PrintWriter(file)) {
                out.print(sb);
                out.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
