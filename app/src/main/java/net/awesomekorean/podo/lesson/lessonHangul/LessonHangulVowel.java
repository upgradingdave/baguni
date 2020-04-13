package net.awesomekorean.podo.lesson.lessonHangul;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.S_LessonInit_Unlock;

public class LessonHangulVowel extends S_LessonInit_Unlock implements Hangul, LessonItem {

    private String lessonId = "H_vowel";

    private String lessonTitle = "vowel";

    private int lessonImage = R.drawable.hangul_menu_vowel;

    private String[] hangul = {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ", "ㅘ", "ㅝ", "ㅙ", "ㅞ", "ㅚ", "ㅟ", "ㅢ"};

    private String[] hangulExplain = {"Make your mouth bigger.", "", "Say 'Uh-oh'. vowel 'ㅓ' has a similar mouth shape with 'uh'." , "", "", "", "make your lips like an octopus.", "", "Say 'cheese'. The mouth shape is similar when you sound [z] in 'cheese'.", "Say 'smile' imagine you are taking a picture. That mouth shape is 'ㅣ'.", "", "", "almost same with [ㅐ]", "almost same with [ㅒ]", "ㅗ + ㅏ", "ㅜ + ㅓ", "ㅗ + ㅐ", "ㅜ + ㅔ\nalmost same with [ㅙ]", "ㅗ + ㅣ\n[ㅙ] also available (recommend)", "ㅜ + ㅣ", "ㅡ + ㅣ"};

    private String hangulIntro = "Korean has a total 21 vowels. \n" +
            "But it's easy because half of them are combined vowels. Main vowels are only 10!\n" +
            "Different from consonants, every vowel uses the throat.\n" +
            "So it's easier to make the sound compared to consonants.\n" +
            "I'll give you a tip. While you practice reading vowels, it would be helpful to think about the mouth shape and size.";


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
        String vowel = "vowel_" + index;
        return vowel;
    }


}
