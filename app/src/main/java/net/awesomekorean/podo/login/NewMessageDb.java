package net.awesomekorean.podo.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.message.MessageItems;

import static android.provider.Settings.System.getString;
import static com.facebook.FacebookSdk.getApplicationContext;

public class NewMessageDb {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void makeNewMessageDb(final Activity activity, final Context context, final String userEmail) {
        final MessageItems messageItems = new MessageItems();
        messageItems.setMessage("welcom to podo blabla");
        CollectionReference messageRef = db.collection(activity.getString(R.string.DB_MESSAGES));
        messageRef.document(userEmail).collection("message").add(messageItems).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                System.out.println("메시지 DB를 만들었습니다");
                Toast.makeText(context, activity.getString(R.string.WELCOME), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                MainActivity.userEmail = userEmail;
                activity.finish();
                activity.startActivity(intent);
            }
        });

    }
}
