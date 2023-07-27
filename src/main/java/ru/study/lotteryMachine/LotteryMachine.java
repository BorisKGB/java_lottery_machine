package ru.study.lotteryMachine;

import ru.study.prize.Prize;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class LotteryMachine {
    private MachineStorage storage;
    private Queue<Prize> prizeDistribution;
    public LotteryMachine() {
        this.storage = new MachineStorage();
        this.prizeDistribution = new LinkedList<>();
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
            prizeDistribution.add(storage.getPrize(storageID));
        }
    }

    /**
     * get one prize from distribution
     */
    public Prize getPrize() {
        return prizeDistribution.poll();
    }

    /**
     * get N prizes from distribution <br>
     * will return as many prizes as requested or as available in distribution
     * @param number if prizes to get
     */
    public List<Prize> getPrizes(int number) {
        List<Prize> result = new ArrayList<>();
        Prize prize = getPrize();
        while (prize != null && number > 0) {
            result.add(prize);
            prize = getPrize();
            number--;
        }
        return result;
    }

    /**
     * get all prizes from distribution
     */
    public List<Prize> getPrizes() {
        return getPrizes(prizeDistribution.size());
    }

    private void logAction() {
        // callback to any external logging mechanism
        // to be described later
    }
}
