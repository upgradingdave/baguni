package net.awesomekorean.podo;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;

public class FirebaseCloudMessage extends FirebaseMessagingService {

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("클라우드 메시지 시작!!");
    }


    @Override
    public void onNewToken(String token) {
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Intent intent = new Intent(getApplicationContext(), CloudMessageReceiver.class);

        intent.putExtra("title", remoteMessage.getNotification().getTitle());

        intent.putExtra("contents", remoteMessage.getNotification().getBody());

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        System.out.println("메시지 타이틀 : " + remoteMessage.getNotification().getTitle());

        System.out.println("메시지 내용 :  " + remoteMessage.getNotification().getBody());

        startActivity(intent);
    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}


