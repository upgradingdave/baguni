package net.awesomekorean.podo.lesson;

import java.util.Random;

public class RandomRewards {


    public static int randomRewards() {

        Random random = new Random();

        int reward1 = 1;    // 50%
        int reward2 = 5;    // 20%
        int reward3 = 10;   // 15%
        int reward4 = 20;   // 10%
        int reward5 = 30;   // 5%

        int reward = 0;

        int select = random.nextInt(100);
        System.out.println("select: " + select);

        if (select >= 0 && select <= 4) {
            reward = reward5;

        } else if (select >= 5 && select <= 14) {
            reward = reward4;

        } else if (select >= 15 && select <= 29) {
            reward = reward3;

        } else if (select >= 30 && select <= 49) {
            reward = reward2;

        } else if (select >= 50 && select <= 99) {
            reward = reward1;
        }
        return reward;
    }
}
