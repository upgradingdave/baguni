package net.awesomekorean.baguni.collection;

public class CollectionItems {

    private String collectionKorean;
    private String collectionEnglish;

    private boolean isChecked;

    public void setCollectionKorean(String collection) {
        this.collectionKorean = collection;
    }

    public void setCollectionEnglish(String collection) {
        this.collectionEnglish = collection;
    }

    public String getCollectionKorean() {
        return this.collectionKorean;
    }

    public String getCollectionEnglish() {
        return this.collectionEnglish;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getChecked() {
        return isChecked;
    }

}
