package net.awesomekorean.baguni.collection;

public class CollectionDb {


    private static String[] oldCollectionKorean = {"사과", "바나나", "망고", "ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ","사과", "바나나", "망고", "ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    private static String[] oldCollectionEnglish = {"apple", "banana", "mango", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n","apple", "banana", "mango", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};

    private static String[] newCollectionKorean;
    private static String[] newCollectionEnglish;


    public void addCollection(String front, String back) {

        int length = oldCollectionKorean.length;

        newCollectionKorean = new String[length+1];
        newCollectionEnglish = new String[length+1];

        for(int i=0; i<length; i++) {

            newCollectionKorean[i] = oldCollectionKorean[i];
            newCollectionEnglish[i] = oldCollectionEnglish[i];
        }

        newCollectionKorean[length] = front;
        newCollectionEnglish[length] = back;

    }

    public void editCollection(int index, String front, String back) {

        int length = oldCollectionKorean.length;

        newCollectionKorean = new String[length];
        newCollectionEnglish = new String[length];

        for(int i=0; i<length; i++) {
            newCollectionKorean[i] = oldCollectionKorean[i];
            newCollectionEnglish[i] = oldCollectionEnglish[i];
        }

        newCollectionKorean[index] = front;
        newCollectionEnglish[index] = back;
    }

    public String[] getCollectionKorean() {

        if(newCollectionKorean != null) {
            return newCollectionKorean;
        } else {
            return oldCollectionKorean;
        }
    }

    public String[] getCollectionEnglish() {

        if(newCollectionEnglish != null) {
            return newCollectionEnglish;
        } else {
            return oldCollectionEnglish;
        }
    }
}
