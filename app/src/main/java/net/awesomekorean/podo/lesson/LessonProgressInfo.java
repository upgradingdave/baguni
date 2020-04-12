package net.awesomekorean.podo.lesson;

import android.content.Context;

import net.awesomekorean.podo.SharedPreferencesInfo;

import java.util.Arrays;
import java.util.List;

public class LessonProgressInfo {

    private Context context;

    private List<String> lessonCompleteInfo;

    private String[] lessonIdArray;

    private String[] lessonProgressArray;


    public LessonProgressInfo(Context context) {

        this.context = context;

        this.lessonCompleteInfo = SharedPreferencesInfo.getUserInfo(context).getLessonComplete();

        this.lessonIdArray = new String[lessonCompleteInfo.size()];

        this.lessonProgressArray = new String[lessonCompleteInfo.size()];

        if(lessonCompleteInfo != null) {

            for(int i=0; i<lessonCompleteInfo.size(); i++) {

                String[] idAndProgress = lessonCompleteInfo.get(i).split("%");

                lessonIdArray[i] = idAndProgress[0];

                lessonProgressArray[i] = idAndProgress[1];
            }
        }
    }


    // 레슨완료리스트의 레슨들을 반환
    public String[] getLessonIdArray() {

        return lessonIdArray;
    }


    // 레슨완료리스트의 진행률들을 반환
    public String[] getLessonProgressArray() {

        return lessonProgressArray;
    }


    // 레슨의 진행률을 반환 (레슨완료리스트에 없으면 -1 반환)
    public int getProgress(String lessonId) {

        if(Arrays.asList(lessonIdArray).contains(lessonId)) {

            int progress = Integer.parseInt(lessonProgressArray[getIndex(lessonId)]);

            return progress;

        } else {

            return -1;
        }
    }


    // 레슨완료리스트의 인덱스를 반환
    public int getIndex(String lessonId) {

        int index = Arrays.asList(lessonIdArray).indexOf(lessonId);

        return index;
    }
}


