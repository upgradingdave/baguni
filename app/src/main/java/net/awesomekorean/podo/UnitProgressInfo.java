package net.awesomekorean.podo;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.SharedPreferencesInfo;

import java.util.Arrays;
import java.util.List;

public class UnitProgressInfo {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Context context;

    private List<String> completeInfo;

    private String[] unitIdArray;

    private String[] unitProgressArray;

    private UserInformation userInformation;


    public UnitProgressInfo(Context context, boolean isReading) {

        this.context = context;

        this.userInformation = SharedPreferencesInfo.getUserInfo(context);

        if(isReading) {

            this.completeInfo = userInformation.getReadingComplete();

        } else {

            this.completeInfo = userInformation.getLessonComplete();
        }

        setCompleteInfoToNewMark(isReading);

        this.unitIdArray = new String[completeInfo.size()];

        this.unitProgressArray = new String[completeInfo.size()];

        if(completeInfo != null) {

            for(int i=0; i<completeInfo.size(); i++) {

                String[] idAndProgress = completeInfo.get(i).split("%");

                unitIdArray[i] = idAndProgress[0];

                unitProgressArray[i] = idAndProgress[1];
            }
        }
    }


    public String[] getUnitIdArray() {
        return unitIdArray;
    }


    // 해당 유닛의 진행률을 반환 (완료리스트에 없으면 -1 반환)
    public int getProgress(String unitId) {

        if(Arrays.asList(unitIdArray).contains(unitId)) {

            int progress = Integer.parseInt(unitProgressArray[getIndex(unitId)]);

            return progress;

        } else {

            return -1;
        }
    }


    // 완료리스트의 인덱스를 반환
    public int getIndex(String lessonId) {

        int index = Arrays.asList(unitIdArray).indexOf(lessonId);

        return index;
    }


    // 완료아이템 표기 변경 (L_00 -> L_00%100)
    private void setCompleteInfoToNewMark(boolean isReading) {

        if(completeInfo != null) {

            if(!completeInfo.get(0).contains("%")) {

                for(int i=0; i<completeInfo.size(); i++) {

                    String newCompleteInfo = completeInfo.get(i) + "%100";

                    completeInfo.set(i, newCompleteInfo);
                }

                if(isReading) {

                    userInformation.setReadingComplete(completeInfo);

                }else {

                    userInformation.setLessonComplete(completeInfo);
                }

                SharedPreferencesInfo.setUserInfo(context, userInformation);

                db.collection(context.getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(context.getString(R.string.DB_INFORMATION)).document(context.getString(R.string.DB_INFORMATION))
                        .set(userInformation)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.println("DB에 완료리스트를 신규 번호로 변경했습니다.");
                            }
                        });
            }
        }
    }
}


