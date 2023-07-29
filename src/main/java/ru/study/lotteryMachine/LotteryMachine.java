package ru.study.lotteryMachine;

import ru.study.customLog.CustomLog;
import ru.study.prize.Prize;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import static ru.study.Main.randNumber;

public class LotteryMachine {
    private MachineStorage storage;
    private Queue<Prize> prizeDistribution;
    private CustomLog log;
    public LotteryMachine() {
        this.storage = new MachineStorage();
        this.prizeDistribution = new LinkedList<>();
        this.log = CustomLog.getInstance();
    }

    public void loadPrizes(Prize prize, int probability, int amount) {
        log.info(String.format("Запрошена загрузка %d призов '%s' с вероятностью %d%%", amount, prize.getName(), probability));
        if (probability > 0 && probability <= 100) {
            storage.loadPrize(prize, probability, amount);
        } else {
            log.warn(String.format("Запрошенная вероятность '%d' превышает допустимую, вероятность выставлена в 30%%", probability));
            storage.loadPrize(prize, 30, amount);
        }
    }

    /**
     * get element probabilities from storage <br>
     * choose prizes that satisfy chosen number <br>
     * if there is more than one prize, pick random prize from chosen
     * if Prize was chosen move it to prizeDistribution Queue
     */
    public void Gamble() {
        log.info("Запрошен розыгрыш");
        List<Integer> gambleData = storage.getElementsProbability();
        int gambleChoice = randNumber(1, 100);
        List<Integer> gambleResult = new ArrayList<>();
        for (int i=0; i<gambleData.size(); i++) {
            if (gambleData.get(i) >= gambleChoice) {
                gambleResult.add(i);
            }
        }
        if (gambleData.isEmpty()) log.warn("Нет призов для розыгрыша");
        Integer storageID = null;
        if (gambleResult.size() > 1) {
            storageID = gambleResult.get(randNumber(0, gambleResult.size()-1));
        } else if (gambleResult.size() == 1) {
            storageID = gambleResult.get(0);
        }
        if (storageID != null) {
            Prize prize = storage.getPrize(storageID);
            log.info(String.format("Результат розыгрыша '%s', добавлен в очередь на выдачу", prize.getName()));
            prizeDistribution.add(prize);
        } else {
            log.info("Результат розыгрыша 'ничего'");
        }
    }

    /**
     * get one prize from distribution
     */
    public Prize getPrize() {
        Prize prize = prizeDistribution.poll();
        if (prize != null) {
            log.info(String.format("Выдан приз '%s'", prize.getName()));
        } else {
            log.info("Запрошена выдача приза, нет призов в очереди на раздачу, ничего не выдано");
        }
        return prize;
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
}
