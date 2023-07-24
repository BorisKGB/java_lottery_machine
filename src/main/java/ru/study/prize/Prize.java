package ru.study.prize;

public class Prize {
    static private int lastID = 0;
    private final int id;
    private String name;

    public Prize(String name) {
        this.id = lastID++;
        this.name = name;
    }

    /**
     * peek what id will be assigned next
     * @return next ID
     */
    public static int getLastID() {
        return lastID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
