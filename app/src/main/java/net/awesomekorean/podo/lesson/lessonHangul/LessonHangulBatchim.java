package net.awesomekorean.podo.lesson.lessonHangul;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.S_LessonInit_Unlock;

public class LessonHangulBatchim extends S_LessonInit_Unlock implements Hangul, LessonItem {

    private String lessonId = "H_batchim";

    private String lessonTitle = "batchim";

    private String lessonSubTitle = "ㄱ, ㄴ, ㄷ...";

    private int lessonImage = R.drawable.hangul_menu_batchim;

    private String[] hangul = { "ㄱ", "역", "한국", "한국", "ㄲ", "ㅋ", "박, 밖, 밬", "ㄴ", "은", "ㄷ", "귿", "ㅌ", "ㅅ", "ㅆ", "ㅈ", "ㅊ", "ㅎ", "낟, 낱, 낫, 났, 낮, 낯, 낳", "ㄹ", "을", "ㅁ", "음", "ㅂ", "읍", "ㅍ", "압, 앞", "ㅇ", "응"};

    private String[] hangulExplain = {
            "기역. \n We can find how our articulator moves when we reading each name of consonants slowly.",
            "Can you feel your throat closed when you pronounce Batchim 'ㄱ'? (I'll leave 'Batchim' out from now.)",
            "(wrong pronunciation) Can you hear the [k] sound? \n Many students pronounce it like this. As I said in the intro, there is no sound in a Batchim. There is only a movement. \n Listen to the correct pronunciation on the next page.",
            "(correct pronunciation) There is no sound but only the movement of the throat closed",
            "쌍기역. \n has a same movement with 'ㄱ'.",
            "키윽. \n also has a same movement with 'ㄱ'.",
            "Therefore, all of these letters have the totally same pronunciation",
            "니은. \n Let's see 'ㄴ'. We can feel another movement.",
            "The tip of our tongue is moving to the top of mouth and behind of upper teeth. And the air is getting out through our nose.",
            "디귿. \n Let's see the 'ㄷ'.",
            "The tongue movement seems like similar with 'ㄴ'. \n But the air doesn't out through our nose. \n From now, we will see a lot of other same movements with 'ㄷ'.",
            "티읕.",
            "시옷.",
            "쌍시옷.",
            "지읒.",
            "치읒.",
            "히읗.",
            "They are totally same pronunciation. \n Now we can have a question. Why we should use different character even there are lots of same movement? It seems more simple if we use only one Batchim. \n However, it will help you to reduce the number of sound that we should make. We can know enough its different meanings with spelling and the situation.",
            "리을. \n It has also similar tongue position with 'ㄴ'.",
            "But the air is getting out through mouth. \n Some Korean learners who are native English speaker, tends to roll up their tongue like [r] sound. But the correct position is behind of upper teeth.",
            "미음. \n Close your mouth and hum.",
            "And you can feel the sound trembled in your mouth.",
            "비읍. \n Your mouth closed like 'ㅁ'.",
            "But the sound not trembled.",
            "피읖. \n also has a same movement with 'ㅂ'.",
            "Therefore, they are same pronunciation.",
            "이응. \n Finally, this is the last one.",
            "Humming with your mouth opened. \n Now you can reading all of Hangul. Repeat the lesson several times and practice in the next stage."
    };

    private String hangulIntro =
            "We call last consonant a 'Batchim'.\n" +
                    "It's the last consonant that is placed in the under part of the Hangul.\n" +
                    "When I teach Korean, I found a lot of students feel that Batchim is difficult.\n" +
                    "They usually studied Batchim to sound like\n" +
                    "'ㄱ' = [k], 'ㄴ' = [n]...\n" +
                    "And they think the sound of the consonant changes when they are a Batchim.\n" +
                    "I want to say the Batchim is not a sound but the movement of our mouth, tongue and throat.\n" +
                    "The name of each consonant (기역, 니은...) begins with the consonant(sound) and ends with a Batchim(movement).\n" +
                    "Therefore, if you read the name of each consonant slowly, it is easy to see what movement it has as a Batchim.";


    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

    @Override
    public String[] getHangul() {
        return hangul;
    }

    @Override
    public String[] getHangulExplain() {
        return hangulExplain;
    }

    @Override
    public String getHangulIntro() {
        return hangulIntro;
    }

    @Override
    public String getHangulAudio(int index) {
        String batchim = "batchim_" + index;
        return batchim;
    }
}
