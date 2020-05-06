package net.awesomekorean.podo;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UserInformation {

    private List<Boolean> attendance = new ArrayList<>(7); // [0]~[6], 일~토
    private int points;
    private List<String> lessonComplete = new ArrayList<>();
    private List<String> readingComplete = new ArrayList<>();
    private List<String> specialLessonUnlock = new ArrayList<>();
    private List<String> readingUnlock = new ArrayList<>();
    private Boolean isPremium;
    private Long datePurchase;
    private Long dateExpire;

    public UserInformation() {
        for(int i=0; i<7; i++) {
            this.attendance.add(false);
        }
        this.points = 20;
        this.isPremium = false;
    }

    public List<Boolean> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Boolean> attendance) {
        this.attendance = attendance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getLessonComplete() {
        return lessonComplete;
    }

    // 신규 완료정보 변경을 위해 만듦 (L_00 --> L_00%100)
    public void setLessonComplete(List<String> lessonComplete) {
        this.lessonComplete = lessonComplete;
    }
    public void setReadingComplete(List<String> readingComplete) {
        this.readingComplete = readingComplete;
    }


    // 완료리스트 업데이트하고 앱이랑 DB도 바로 업데이트 함
    public void updateCompleteList(Context context, String unitId, int thisProgress, boolean isReading) {

        UnitProgressInfo unitProgressInfo = new UnitProgressInfo(context, isReading);

        int previousProgress = unitProgressInfo.getProgress(unitId);

        System.out.println("유닛 아이디 : " + unitId);

        System.out.println("기존 진행률 : " + previousProgress);

        System.out.println("현재 진행률 : " + thisProgress);


        if(thisProgress > previousProgress) {

            if(previousProgress == -1) {

                // 기존진행률이 0일 때 -> 완료리스트에 추가
                if(isReading) {

                    readingComplete.add(unitId + "%" + thisProgress);

                } else {

                    lessonComplete.add(unitId + "%" + thisProgress);
                }

                // 기존진행률이 있을 때 -> 완료리스트 수정
            } else {

                int index = unitProgressInfo.getIndex(unitId);

                if(isReading) {

                    readingComplete.set(index, unitId + "%" + thisProgress);

                } else {

                    lessonComplete.set(index, unitId + "%" + thisProgress);
                }
            }

            SharedPreferencesInfo.setUserInfo(context, this);

            updateDb(context);

        } else {

            System.out.println("기존 진행률이 더 높습니다. DB에 유저 정보를 업데이트하지 않습니다.");
        }
    }


    // DB 에 유저 정보 업데이드 하기
    public void updateDb(final Context context) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(context.getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(context.getString(R.string.DB_INFORMATION)).document(context.getString(R.string.DB_INFORMATION))
                .set(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        System.out.println("DB에 유저 정보를 업데이트 했습니다.");
                    }
                });
    }


    public List<String> getReadingComplete() {
        return readingComplete;
    }

    public void addReadingComplete(String readingId) {
        this.readingComplete.add(readingId);
    }

    public Boolean getIsPremium() {return isPremium;}

    public void setIsPremium(Boolean b) {
        this.isPremium = b;
    }

    public Long getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Long datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Long getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(Long dateExpire) {
        this.dateExpire = dateExpire;
    }

    public List<String> getSpecialLessonUnlock() {
        return specialLessonUnlock;
    }

    public void addSpecialLessonUnlock(String lessonId) {
        this.specialLessonUnlock.add(lessonId);
    }

    public List<String> getReadingUnlock() {
        return readingUnlock;
    }

    public void addReadingUnlock(String readingId) {
        this.readingUnlock.add(readingId);
    }

    public void resetDays(int today) {
        for(int i=0; i<7; i++) {
            this.attendance.set(i, false);
        }
        this.attendance.set(today, true);
    }

    public void setDay(int today) {
        this.attendance.set(today, true);
    }
}
