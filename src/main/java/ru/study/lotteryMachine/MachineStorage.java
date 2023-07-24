package ru.study.lotteryMachine;

import ru.study.prize.Prize;

import java.util.ArrayList;

public class MachineStorage {
    private ArrayList<LotteryElement> elements;
    public MachineStorage() {
        this.elements = new ArrayList<>();
    }
    public void loadPrize(Prize prize, int probability, int amount) {
        //if no prize with such ID in elements
        LotteryElement el = new LotteryElement(prize, probability, amount);
        elements.add(el);
        // else
        // findElementByID().updateAmount()
    }
    public ArrayList<LotteryElement> getElements() {
        return null;
        // return elements as is for gamble logic
        // or return hash<int(probability), Prize> or other easy to parse for gamble logic format
        // also another method for showLeftovers()?
    }
    public Prize getPrize(int prizeID) {
        return null;
    }
}
