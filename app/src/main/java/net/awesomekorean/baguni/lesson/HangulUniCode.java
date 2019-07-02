package net.awesomekorean.baguni.lesson;

import java.io.UnsupportedEncodingException;

public class HangulUniCode {

    private final String[] cho = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ" ,"ㅆ", "ㅇ" ,"ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ" ,"ㅍ", "ㅎ" };
    private final String[] jung = { "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    private final String[] jong = { "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    private int indexCho;
    private int indexJung;
    private int indexJong;

    private int assembledUniCode;
    private String assembledHangul;


    public HangulUniCode(String a, String b) {

        indexCho = findIndex(a, cho);
        indexJung = findIndex(b, jung);

        assembledUniCode = 44032 + (indexCho*588) + indexJung*28;

        char cv = (char) assembledUniCode;

        assembledHangul = Character.toString(cv);

    }


    public HangulUniCode(String a, String b, String c) {

        indexCho = findIndex(a, cho);
        indexJung = findIndex(b, jung);
        indexJong = findIndex(c, jong);

        assembledUniCode = 44032 + (indexCho*588) + indexJung*28 + indexJong;

        char cvc = (char) assembledUniCode;

        assembledHangul = Character.toString(cvc);
    }


    public int findIndex(String selectedHangul, String[] jamo) {

        int index = 0;

        for (int i=0; i<jamo.length; i++) {
            if(selectedHangul.equals(jamo[i])) {
                index = i;
            }
        }

        return index;
    }


    public String getAssembledHangul() {
        return assembledHangul;
    }
}


