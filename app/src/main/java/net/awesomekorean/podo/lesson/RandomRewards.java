package net.awesomekorean.podo.lesson;

import java.util.Random;

public class RandomRewards {


    public static int randomRewards() {

        Random random = new Random();

        int reward1 = 4;    // 60%
        int reward2 = 5;   // 25%
        int reward3 = 7;   // 10%
        int reward4 = 10;   // 5%

        int reward = 0;

        int select = random.nextInt(100);
        System.out.println("select: " + select);

        if (select >= 0 && select <= 4) {
            reward = reward4;

        } else if (select >= 5 && select <= 14) {
            reward = reward3;

        } else if (select >= 15 && select <= 39) {
            reward = reward2;

        } else if (select >= 40 && select <= 99) {
            reward = reward1;

        }
        return reward;
    }
}
