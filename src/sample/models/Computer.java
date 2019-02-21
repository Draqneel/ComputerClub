package sample.models;

import java.util.Date;

/**
 * Created by mezkresh on 16.02.2019.
 */
public class Computer {
    private int id;
    private String description;
    private String proccessor;
    private int ram;
    private int rom;
    private String videoCart;

    public Computer(String description, String proccessor, int ram, int rom, String videoCart) {
        this.description = description;
        this.proccessor = proccessor;
        this.ram = ram;
        this.rom = rom;
        this.videoCart = videoCart;
        this.id = String.valueOf(new Date().getTime()).hashCode();
    }

    public Computer(int id, String description, String proccessor, int ram, int rom, String videoCart) {
        this.id = id;
        this.description = description;
        this.proccessor = proccessor;
        this.ram = ram;
        this.rom = rom;
        this.videoCart = videoCart;
    }

    @Override
    public String toString() {
        return id + " " + description + " " + proccessor + " " + ram + " " + rom + " " + videoCart;
    }

    public String toString(boolean flag){
        return "Процессор: " + proccessor + "\n"+
                "ОЗУ: " + ram  +"ГБ\n"+
                "Память: " +rom +"ГБ\n" +
                "Видеокарта: " + videoCart;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProccessor(String proccessor) {
        this.proccessor = proccessor;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public void setVideoCart(String videoCart) {
        this.videoCart = videoCart;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getProccessor() {
        return proccessor;
    }

    public int getRam() {
        return ram;
    }

    public int getRom() {
        return rom;
    }

    public String getVideoCart() {
        return videoCart;
    }
}
