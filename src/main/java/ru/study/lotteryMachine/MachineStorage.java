package ru.study.lotteryMachine;

import ru.study.prize.Prize;

import java.util.ArrayList;
import java.util.List;

public class MachineStorage {
    private ArrayList<LotteryElement> elements;
    public MachineStorage() {
        this.elements = new ArrayList<>();
    }

    private Integer findPrizeNumber(Prize prize) {
        for (int i=0; i<elements.size(); i++) {
            if (elements.get(i).getPrize().equals(prize)) return i;
        }
        return null;
    }

    /**
     * load Prizes in storage <br>
     * If prize already exist in Storage: <br>
     *   1. Amount will be increased <br>
     *   2. Probability will be set to requested
     */
    public void loadPrize(Prize prize, int probability, int amount) {
        Integer prizeNumber = findPrizeNumber(prize);
        if (prizeNumber != null) {
            LotteryElement el = elements.get(prizeNumber);
            el.setAmount(el.getAmount()+amount);
        } else {
            LotteryElement el = new LotteryElement(prize, probability, amount);
            elements.add(el);
        }
    }

    public List<Integer> getElementsProbability() {
        List<Integer> result = new ArrayList<>();
        for (LotteryElement el : elements) {
            result.add(el.getProbability());
        }
        return result;
    }

    /**
     * retrieve Prize from storage by its storageID <br>
     * also remove lotteryElement from storage if amount == 0
     */
    public Prize getPrize(int elementID) {
        if (elementID < elements.size()) {
            LotteryElement el = elements.get(elementID);
            el.setAmount(el.getAmount()-1);
            if (el.getAmount() == 0) elements.remove(el);
            return el.getPrize();
        }
        return null;
    }

    public Prize listElementPrize(int elementID) {
        if (elementID < elements.size()) return elements.get(elementID).getPrize();
        return null;
    }

    public int listElementAmount(int elementID) {
        if (elementID < elements.size()) return elements.get(elementID).getAmount();
        return 0;
    }

    public int listElementProbability(int elementID) {
        if (elementID < elements.size()) return elements.get(elementID).getProbability();
        return 0;
    }

    /**
     * return number on LotteryElement in object <br>
     * This is not total number of prizes
     * TODO: find more appropriate name for this method
     */
    public int getElementsSize() {
        return elements.size();
    }
}
