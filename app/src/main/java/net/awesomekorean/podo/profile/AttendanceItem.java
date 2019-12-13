package net.awesomekorean.podo.profile;

public class AttendanceItem {

    private boolean day1 = false; // 일요일
    private boolean day2 = false;
    private boolean day3 = false;
    private boolean day4 = false;
    private boolean day5 = false;
    private boolean day6 = false;
    private boolean day7 = false;  // 토요일

    public void AttendanceItem() {}


    public boolean isDay1() {
        return day1;
    }

    public void setDay1(boolean day1) {
        this.day1 = day1;
    }

    public boolean isDay2() {
        return day2;
    }

    public void setDay2(boolean day2) {
        this.day2 = day2;
    }

    public boolean isDay3() {
        return day3;
    }

    public void setDay3(boolean day3) {
        this.day3 = day3;
    }

    public boolean isDay4() {
        return day4;
    }

    public void setDay4(boolean day4) {
        this.day4 = day4;
    }

    public boolean isDay5() {
        return day5;
    }

    public void setDay5(boolean day5) {
        this.day5 = day5;
    }

    public boolean isDay6() {
        return day6;
    }

    public void setDay6(boolean day6) {
        this.day6 = day6;
    }

    public boolean isDay7() {
        return day7;
    }

    public void setDay7(boolean day7) {
        this.day7 = day7;
    }

    public boolean getDay(String day) {
        boolean result = false;
        switch (day) {
            case "day1" :
                result = this.day1;
                break;
            case "day2" :
                result = this.day2;
                break;
            case "day3" :
                result = this.day3;
                break;
            case "day4" :
                result = this.day4;
                break;
            case "day5" :
                result = this.day5;
                break;
            case "day6" :
                result = this.day6;
                break;
            case "day7" :
                result = this.day7;
                break;
        }
        return result;
    }

    public void setDay(String day, boolean b) {
        switch (day) {
            case "day1" :
                this.day1 = b;
                break;
            case "day2" :
                this.day2 = b;
                break;
            case "day3" :
                this.day3 = b;
                break;
            case "day4" :
                this.day4 = b;
                break;
            case "day5" :
                this.day5 = b;
                break;
            case "day6" :
                this.day6 = b;
                break;
            case "day7" :
                this.day7 = b;
                break;
        }
    }


    public void resetDays(String day) {
        for(int i=1; i<8; i++) {
            String resetDay = "day"+i;
            setDay(resetDay, false);
        }
        setDay(day, true);
    }
}
