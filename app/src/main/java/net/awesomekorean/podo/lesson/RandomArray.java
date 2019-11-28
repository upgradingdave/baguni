package net.awesomekorean.podo.lesson;

import java.util.Random;

public class RandomArray {

    // 입력받은 array를 랜덤으로 섞어주는 함수
    public static void randomArray(String[] strings) {

        for (int i = 0; i < strings.length; i++) {

            Random random = new Random();
            int rnum = random.nextInt(strings.length);

            String temp = strings[i];
            strings[i] = strings[rnum];
            strings[rnum] = temp;
        }
    }
}
