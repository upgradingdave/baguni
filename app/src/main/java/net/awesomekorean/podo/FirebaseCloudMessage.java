package net.awesomekorean.podo;

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

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();

    FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("클라우드 메시지 시작!!");
    }


    @Override
    public void onNewToken(String token) {

        System.out.println("새 토큰 : " + token);

        SharedPreferencesInfo.setUserToken(getApplicationContext(), token);
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        System.out.println("MESSAGE FROM : " + remoteMessage.getFrom());

        if(remoteMessage.getData().size() > 0) {

            System.out.println("MESSAGE DATA PAYLOAD : " + remoteMessage.getData());
        }

        if(remoteMessage.getNotification() != null) {

            System.out.println("MESSAGE NOTIFICATION BODY : " + remoteMessage.getNotification().getBody());
        }
    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}


