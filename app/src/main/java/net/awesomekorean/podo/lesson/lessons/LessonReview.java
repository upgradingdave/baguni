package net.awesomekorean.podo.lesson.lessons;

import java.util.List;

public interface LessonReview {

    List<String> getFront();

    List<String> getBack();

    List<String> getImageString();

    List<String> getAudioString();

    List<String> getAudioFolder();
}
