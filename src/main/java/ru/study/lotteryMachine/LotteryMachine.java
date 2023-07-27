package ru.study.lotteryMachine;

import ru.study.prize.Prize;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LotteryMachine {
    private MachineStorage storage;
    private Queue<Prize> prizeDistribution;
    public LotteryMachine() {
        this.storage = new MachineStorage();
        // init prizes out queue
    }

    public void LoadPrizes(Prize prize, int probability, int amount) {
        if (probability > 0 && probability <= 100) {
            storage.loadPrize(prize, probability, amount);
        } else {
            System.out.printf("WARN: incorrect probability '%d', will be set to '30'\n", probability);
            storage.loadPrize(prize, 30, amount);
        }
    }
    public void Gamble() {
        // before gamble check available prizes
        List<Integer> gambleData = storage.getElementsProbability();
        // on win condition
        int storageID = 0;
        Prize prize = storage.getPrize(storageID);
        // no null check, if we end up here, then prize obj must exist in storage and this was checked earlier
        // and put it in distribution queue
    }
    public Prize getPrize() {
        // get one prize from distribution (or null)
        return null;
    }
    public List<Prize> getPrizes() {
        // get all prizes from distribution queue (can return empty list)
        return new ArrayList<Prize>();
    }
    public List<Prize> getPrizes(int number) {
        // get $number prizes from distribution queue (can return empty list or list with less size that requested)
        return new ArrayList<Prize>();
    }
    private void logAction() {
        // callback to any external logging mechanism
        // to be described later
    }
}
