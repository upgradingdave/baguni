package net.awesomekorean.baguni.lesson.lessonHangul;

class LessonHangulBatchim implements Hangul{

    String[] hangul = { "ㄱ", "역", "박", "ㄲ", "밖", "ㅋ", "읔", "밬", "ㄴ", "은", "안", "ㄷ", "귿", "낟", "ㅌ", "읕", "낱", "ㅅ", "옷", "낫", "ㅆ", "났", "ㅈ", "읒", "낮", "ㅊ", "읓", "낯", "ㅎ", "읗", "낳", "ㄹ", "을", "달", "ㅁ", "음", "잠", "ㅂ", "읍", "밥", "ㅍ", "읖", "앞", "ㅇ", "응", "강"};

    String[] hangulExplain = {
            "We can find how our articulator moves when we read each name of consonants slowly.",
            "Can you feel your throat closed when you pronounce Batchim 'ㄱ'? (I'll leave 'Batchim' out from now.)",
            "Let's practice another character with 'ㄱ'.",
            "'ㄲ' is same with 'ㄱ'.",
            "Let's see an example with 'ㄲ'. We can see it's totally same with 'ㄱ'.",
            "How about 'ㅋ'?",
            "We can still feel our throat closed like 'ㄱ' and 'ㄲ'.",
            "It means 'ㄱ', 'ㄲ', and 'ㅋ' have totally same movement.",
            "Let's see 'ㄴ'. We can feel another movement.",
            "The tip of our tongue is moving to the top of mouth and behind of upper teeth. And the air is getting out through our nose.",
            "Let's practice another character with 'ㄴ'.",
            "Let's see the 'ㄷ'.",
            "The tongue movement seems like similar with 'ㄴ'.",
            "But the air doesn't out through our nose.",
            "From now, we will see a lot of other same movements with 'ㄷ'.",
            "Isn't it same?",
            "Let's practice another character.",
            "Here's other same movement.",
            "Same.",
            "Yes, it's same.",
            "The movement of double consonant is same with its single consonant.",
            "Same.",
            "Same again.",
            "Isn't it?",
            "Good.",
            "Same again and again.",
            "Awesome!",
            "Great!",
            "Same one more.",
            "Now we can have a question. Why we should use different character even there are lots of same movement? It seems more simple if we use only one Batchim.",
            "However, every languages have some words that have same pronunciation but different meaning. It will help flag_america to reduce the number of sound that we should make. Even though it has same sound, we can know enough its different meanings with spelling and the situation.",
            "After a long time, it's different movement.",
            "It has also similar tongue position with 'ㄴ'. But the air is getting out through mouth.",
            "Some Korean learners who are native English speaker, tends to roll up their tongue like [r] sound. But the correct position is behind of upper teeth.",
            "Your lips closed.",
            "And you can feel the sound trembled in your mouth.",
            "And the air is getting out through your nose.",
            "Your lips closed like 'ㅁ'.",
            "But the sound not trembled.",
            "It's easy.",
            "This is same with 'ㅂ'.",
            "Totally same.",
            "That's right.",
            "Finally, this is the last one.",
            "Your mouth opened and the air getting out through your nose.",
            "Perfect! Now you can read all of Hangul. Repeat the lesson several times and practice in the next stage."
    };

    String hangulIntro =
            "We call last consonant to 'Batchim'." +
            "It's the consonant that is placed in under part of Hangul." +
            "When I teach Korean I could find a lot of students who feel difficult in Batchim." +
            "Especially the students who studied Batchim as a sound like" +
            "'ㄱ' = [k], 'ㄴ' = [n]..." +
            "It because they think the sound of consonant is different when they are Batchim." +
            "I want to say the Batchim is not a sound but the movement of our articulator like mouth, tongue, throat etc..." +
            "And I can also say we already finished learning Batchim when we learn consonants." +
            "Can you believe this?";

    @Override
    public String[] getHangul() {
        return hangul;
    }

    @Override
    public String[] getHangulExplain() {
        return hangulExplain;
    }

    @Override
    public String getHangulIntro() {
        return hangulIntro;
    }

}
