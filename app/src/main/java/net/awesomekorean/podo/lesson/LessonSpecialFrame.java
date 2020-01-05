package net.awesomekorean.podo.lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.S_Lesson1;

public class LessonSpecialFrame extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView title;
    TextView contents;
    Button btnFinish;
    
    LessonSpecial lessonSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_special_frame);

        title = findViewById(R.id.title);
        contents = findViewById(R.id.contents);
        btnFinish = findViewById(R.id.btnFinish);
        contents.setMovementMethod(new ScrollingMovementMethod());
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DocumentReference reference = db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));

                db.runTransaction(new Transaction.Function<Void>() {
                    @Override
                    public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                        DocumentSnapshot snapshot = transaction.get(reference);

                        UserInformation userInformation = snapshot.toObject(UserInformation.class);

                        userInformation.addLessonComplete(MainLesson.lessonUnit);

                        transaction.set(reference, userInformation);

                        return null;
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("레슨완료 정보를 업데이트 했습니다");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("레슨완료 정보를 업데이트를 실패 했습니다: "+e);
                    }
                });

            }
        });

        Intent intent = getIntent();
        int lessonNo = intent.getExtras().getInt("position");
        
        switch(lessonNo) {
            
            case 2:
                lessonSpecial = new S_Lesson1();
                readyForLesson();
                break;
        }

    }

    private void readyForLesson() {
        title.setText(lessonSpecial.getTitle());
        contents.setText(lessonSpecial.getContents());
    }
}
