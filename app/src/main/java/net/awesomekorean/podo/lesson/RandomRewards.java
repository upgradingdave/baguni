package net.awesomekorean.podo.lesson;

import java.util.Random;

public class RandomRewards {


    public static int randomRewards() {

        Random random = new Random();

        int reward1 = 1;    // 55%
        int reward2 = 5;    // 30%
        int reward3 = 10;   // 10%
        int reward4 = 20;   // 3%
        int reward5 = 30;   // 2%

        int reward = 0;

        int select = random.nextInt(100);
        System.out.println("select: " + select);

        if (select >= 0 && select <= 1) {
            reward = reward5;

        } else if (select >= 2 && select <= 4) {
            reward = reward4;

        } else if (select >= 5 && select <= 14) {
            reward = reward3;

        } else if (select >= 15 && select <= 44) {
            reward = reward2;

        } else if (select >= 45 && select <= 99) {
            reward = reward1;
        }
        return reward;
    }
}
