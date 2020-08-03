package net.awesomekorean.podo.lesson;

public class TestGrammarItem {

    private String[] baseForm = {"가다", "먹다", "오다", "사다", "팔다"};

    private String[][] conjugate = {
            {"가요", "갔어요", "갈 거예요", "가고 있어요", "안 가요"},
            {"먹어요", "먹었어요", "먹을 거예요", "먹고 있어요", "안 먹어요"},
            {"와요", "왔어요", "올 거예요", "오고 있어요", "안 와요"},
            {"사요", "샀어요", "살 거예요", "사고 있어요", "안 사요"},
            {"팔아요", "팔았어요", "팔 거예요", "팔고 있어요", "안 팔아요"}
    };

    private String[][] translate = {
            {"go", "went", "will go", "going", "don't go"},
            {"eat", "ate", "will eat", "eating", "don't eat"},
            {"come", "came", "will come", "coming", "don't come"},
            {"buy", "bought", "will buy", "buying", "don't buy"},
            {"sell", "sold", "will sell", "selling", "don't sell"}
    };


    public String[] getBaseForm() {
        return baseForm;
    }

    public String[][] getConjugate() {
        return conjugate;
    }

    public String[][] getTranslate() {
        return translate;
    }
}
