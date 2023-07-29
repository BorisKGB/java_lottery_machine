package ru.study;

import ru.study.customLog.CustomLog;
import ru.study.lotteryMachine.LotteryMachine;
import ru.study.prize.Prize;

import java.util.Random;

public class Main {
    public static int randNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min)+1) + min;
    }
    public static boolean randBool() {
        Random rand = new Random();
        return rand.nextBoolean();
    }
    public static void main(String[] args) {
        CustomLog log = CustomLog.getInstance();
        log.setTime(8,30);
        log.setWriteToConsole();
        log.setWriteToFile();
        log.info("Запуск приложения");

        LotteryMachine lotMach = new LotteryMachine();

        Prize car = new Prize("Машинка");
        Prize plane = new Prize("Самолётик");
        Prize doll = new Prize("Кукла");

        lotMach.loadPrizes(car, 15, 5);
        lotMach.loadPrizes(plane, 115, 10);
        lotMach.loadPrizes(doll, 20, 3);

        log.addHours(2);
        for (int i = 0; i < randNumber(5, 20); i++) {
            lotMach.Gamble();
            if (randBool()) lotMach.getPrizes(randNumber(1, 3));
            log.addMinutes(randNumber(5, 50));
        }

        log.info("Завершение работы");
    }
}