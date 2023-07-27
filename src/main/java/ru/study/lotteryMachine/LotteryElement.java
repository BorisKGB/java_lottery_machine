package ru.study.lotteryMachine;

import ru.study.prize.Prize;

public class LotteryElement {
    private Prize prize;
    private int probability;
    private int amount;

    public LotteryElement(Prize prize, int probability, int amount) {
        this.prize = prize;
        this.probability = probability;
        this.amount = amount;
    }

    public Prize getPrize() {
        return prize;
    }

    public int getAmount() {
        return amount;
    }

    public int getProbability() {
        return probability;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
