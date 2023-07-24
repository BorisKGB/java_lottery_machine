package ru.study.lotteryMachine;

import ru.study.prize.Prize;

public class LotteryElement {
    private Prize element;
    private int probability;
    private int elementsAmount;

    public LotteryElement(Prize element, int probability, int amount) {
        this.element = element;
        this.probability = probability;
        this.elementsAmount = amount;
    }

    public Prize getElement() {
        return element;
    }

    public int getElementsAmount() {
        return elementsAmount;
    }

    public int getProbability() {
        return probability;
    }

    public void setElementsAmount(int elementsAmount) {
        this.elementsAmount = elementsAmount;
    }
}
