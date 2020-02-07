package net.awesomekorean.podo.lesson.lessonHangul;


import java.util.Arrays;

public class HangulUniCode {


    private final String[] cho = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ" ,"ㅆ", "ㅇ" ,"ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ" ,"ㅍ", "ㅎ" };
    private final String[] jung = { "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    private final String[] jong = { "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    private final String[] jungException = {"ㅙ", "ㅞ"}; // "ㅚ"
    private final String[] jongException1 = {"ㅋ", "ㄲ"}; // "ㄱ"
    private final String[] jongException2 = {"ㅅ", "ㅈ", "ㅊ", "ㅌ", "ㅎ", "ㅆ"}; // "ㄷ"
    private final String[] jongException3 = {"ㅍ"}; // "ㅂ"

    private int indexCho;
    private int indexJung;
    private int indexJong;

    private int assembledUniCode;
    private String assembledHangul;

    private String audioFile;
    private String audioFileCorrected = null;

    char cv;
    char cvc;

    public HangulUniCode(String a, String b) {


        indexCho = findIndex(a, cho);
        indexJung = findIndex(b, jung);

        assembledUniCode = 44032 + (indexCho*588) + indexJung*28;
        cv = (char) assembledUniCode;
        assembledHangul = Character.toString(cv);

        // 입력한 모음이 예외 모음에 해당되는지 확인
        if(Arrays.asList(jungException).contains(b)) {
            String b2 = "ㅚ";
            int indexJung2 = findIndex(b2, jung);
            assembledUniCode = 44032 + (indexCho*588) + indexJung2*28;
            cv = (char) assembledUniCode;
            audioFileCorrected = cv + ".mp3";

            // 예외 모음에 포함되면 audioFileCorrected 생성
        } else {
            audioFile = assembledHangul + ".mp3";
        }
    }


    public HangulUniCode(String a, String b, String c) {

        indexCho = findIndex(a, cho);
        indexJung = findIndex(b, jung);
        indexJong = findIndex(c, jong);

        assembledUniCode = 44032 + (indexCho*588) + indexJung*28 + indexJong;
        cvc = (char) assembledUniCode;
        assembledHangul = Character.toString(cvc);

        // 입력한 받침이 예외 받침에 해당되는지 확인
        String c2 = null;
        if(Arrays.asList(jongException1).contains(c)) {
            c2 = "ㄱ";
        } else if(Arrays.asList(jongException2).contains(c)) {
            c2 = "ㄷ";
        } else if(Arrays.asList(jongException3).contains(c)) {
            c2 = "ㅂ";
        }

        if(c2 == null) {
            audioFile = assembledHangul + ".mp3";

        // 예외 받침에 포함되면 audioFileCorrected 생성
        } else {
            int indexJong2 = findIndex(c2, jong);
            assembledUniCode = 44032 + (indexCho*588) + indexJung*28 + indexJong2;
            cvc = (char) assembledUniCode;
            audioFileCorrected = cvc + ".mp3";
        }
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

    public String getAudioFile() {
        if(audioFileCorrected != null) {
            return audioFileCorrected;
        } else {
            return audioFile;
        }
    }


    public String getAssembledHangul() {
        return assembledHangul;
    }
}


