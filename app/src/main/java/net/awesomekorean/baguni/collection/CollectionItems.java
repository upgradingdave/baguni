package net.awesomekorean.baguni.collection;

public class CollectionItems {

    private int id;

    private String collectionFront;
    private String collectionBack;

    private boolean isChecked = false;

    private boolean isVisible = false;

    public void setCollectionFront(String collection) {
        this.collectionFront = collection;
    }

    public void setCollectionBack(String collection) {
        this.collectionBack = collection;
    }

    public String getCollectionFront() {
        return this.collectionFront;
    }

    public String getCollectionBack() {
        return this.collectionBack;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    // 검색 기능을 위해 equals 재정의
    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if(this.collectionFront.contains((CharSequence) obj) || this.collectionBack.contains((CharSequence) obj)) {
            result = true;
        }

        return result;
    }
}
