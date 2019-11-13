package net.awesomekorean.baguni.lesson.lessonHangul;

public class LessonHangulConsonant implements Hangul {

    String[] hangul = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
    String[] hangulExplain = {"기역", "니은", "디귿", "리을", "미음", "비읍", "시옷", "이응, No audio_small", "지읒", "치읓", "키읔", "티읕", "피읖", "히읗", "쌍기역", "쌍디귿", "쌍비읍", "쌍시옷", "쌍지읒"};

    String hangulIntro = "Korean only has 14 consonants and 5 double consonants.\n"+
            "We can make the audio_small of each consonant by using articulators like the lips, tongue, mouth, throat and nose.\n"+
            "<b>That's why we should think about the correct position of each articulator.</b>\n"+
            "This will make you pronounce Korean like a native Korean.\n" +
            "Some of consonants could be similar to sounds in your language.\n" +
            "But some of the others could be difficult to pronounce exactly right because you have never made that audio_small in your life.\n" +
            "Your muscles in your mouth may not be strong enough to make that new audio_small yet.\n" +
            "<b>Fortunately, we can train our muscles with practice.</b> Let's warm up your mouth before we start.";

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
}
