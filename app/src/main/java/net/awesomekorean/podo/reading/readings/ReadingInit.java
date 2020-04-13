package net.awesomekorean.podo.reading.readings;

public class ReadingInit {

    private int readingProgress = 0;
    private boolean isCompleted = false;
    private boolean isLock = true;

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLocked(boolean b) {
        this.isLock = b;
    }

    public int getReadingProgress() { return readingProgress; }

    public void setReadingProgress(int readingProgress) { this.readingProgress = readingProgress; }
}
