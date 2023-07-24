package ru.study.lotteryMachine;

import ru.study.prize.Prize;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LotteryMachine {
    private MachineStorage storage;
    private Queue<Prize> prizeDistribution;
    public LotteryMachine() {
        // init storage
        // init prizes out queue
    }

    public void LoadPrizes() {
        // ? proxy action to storage?
    }
    public void Gamble() {}
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
