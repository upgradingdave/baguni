package net.awesomekorean.podo.lesson;

import java.util.Random;

public class RandomRewards {


    public static int randomRewards() {

        Random random = new Random();

        int reward1 = 2;    // 77%
        int reward2 = 5;   // 15%
        int reward3 = 7;   // 6%
        int reward4 = 10;   // 2%

        int reward = 0;

        int select = random.nextInt(100);
        System.out.println("select: " + select);

        if (select >= 0 && select <= 1) {
            reward = reward4;

        } else if (select >= 2 && select <= 7) {
            reward = reward3;

        } else if (select >= 8 && select <= 22) {
            reward = reward2;

        } else if (select >= 23 && select <= 99) {
            reward = reward1;

        }
        return reward;
    }
}
