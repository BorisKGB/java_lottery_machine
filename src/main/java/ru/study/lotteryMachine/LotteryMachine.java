package ru.study.lotteryMachine;

import ru.study.prize.Prize;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

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

    private static int randNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min)+1) + min;
    }

    /**
     * get element probabilities from storage <br>
     * choose prizes that satisfy chosen number <br>
     * if there is more than one prize, pick random prize from chosen
     * if Prize was chosen move it to prizeDistribution Queue
     */
    public void Gamble() {
        List<Integer> gambleData = storage.getElementsProbability();
        int gambleChoice = randNumber(1, 100);
        List<Integer> gambleResult = new ArrayList<>();
        for (int i=0; i<gambleData.size(); i++) {
            if (gambleData.get(i) >= gambleChoice) {
                gambleResult.add(i);
            }
        }
        Integer storageID = null;
        if (gambleResult.size() > 1) {
            storageID = gambleResult.get(randNumber(0, gambleResult.size()-1));
        } else if (gambleResult.size() == 1) {
            storageID = gambleResult.get(0);
        }
        if (storageID != null) {
            Prize prize = storage.getPrize(storageID);
            // no null check, if we end up here, then prize obj must exist in storage and this was checked earlier
            // and put it in distribution queue
        }
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
