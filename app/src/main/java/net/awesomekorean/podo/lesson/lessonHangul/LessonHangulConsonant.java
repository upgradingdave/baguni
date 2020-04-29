package net.awesomekorean.podo.lesson.lessonHangul;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.S_LessonInit_Unlock;

public class LessonHangulConsonant extends S_LessonInit_Unlock implements Hangul, LessonItem {

    private String lessonId = "H_consonant";

    private String lessonTitle = "consonant";

    private String lessonSubTitle = "ㄱ, ㄴ, ㄷ...";

    private int lessonImage = R.drawable.hangul_menu_consonant;

    private String[] hangul = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};

    private String[] hangulExplain = {"기역", "니은", "디귿", "리을", "미음", "비읍", "시옷", "이응, No sound", "지읒", "치읓", "키읔", "티읕", "피읖", "히읗", "쌍기역 \n '쌍' means double.", "쌍디귿", "쌍비읍", "쌍시옷", "쌍지읒"};

    private String hangulIntro =
            "CAUTION) \nIf you want to speak to Korean as like a native speaker, it is not recommended to match the pronunciation of Hangul with the English alphabet. " +
            "However, this lesson provides hints with pictures and alphabet pronunciation to make it easier to remember Hangul but that doesn't mean it's the same pronunciation.\n\n" +
            "Korean only has 14 consonants and 5 double consonants.\n" +
            "We can make the sound of each consonant by using articulators like the lips, tongue, mouth, throat and nose.\n" +
            "That's why we should think about the correct position of each articulator.\n" +
            "This will make you pronounce Korean like a native Korean.\n" +
            "Some of consonants could be similar to sounds in your language.\n" +
            "But some of the others could be difficult to pronounce exactly right because you have never made that sound in your life.\n" +
            "Your muscles in your mouth may not be strong enough to make that new sound yet.\n" +
            "Fortunately, we can train our muscles with practice.\nLet's warm up your mouth before we start.";

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
        String consonant = "consonant_" + index;
        return consonant;
    }
}
