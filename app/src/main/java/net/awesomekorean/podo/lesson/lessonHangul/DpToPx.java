package net.awesomekorean.podo.lesson.lessonHangul;

import android.content.res.Resources;

public class DpToPx {

    public static int getDpToPx(Resources resources, int dp) {

        final float scale = resources.getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);

        return pixels;
    }
}
