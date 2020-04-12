package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class HangulAssembly extends S_LessonInit implements LessonItem {

    private String lessonId = "H_assembly";

    private String title = "assembly";

    private String subTitle = "";

    private int lessonImage = R.drawable.hangul_menu_assembly;

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

}
