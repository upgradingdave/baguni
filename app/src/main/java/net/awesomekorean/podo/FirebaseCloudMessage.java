package net.awesomekorean.podo;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseCloudMessage extends FirebaseMessagingService {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();


    public void getId() {

        firebaseInstanceId.getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {

                if(!task.isSuccessful()) {

                    System.out.println("getInstanceId failed");
                }

                String token = "토큰 아이디 : " + task.getResult().getToken();

                System.out.println(token);
            }
        });
    }


    @Override
    public void onNewToken(@NonNull String token) {

        System.out.println("Refreshed token : " + token);

        UserInformation userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());

        userInformation.setCloudMessageToken(token);

        SharedPreferencesInfo.setUserInfo(this, userInformation);

        String userEmail = SharedPreferencesInfo.getUserEmail(this);

        if(userEmail != null) {

            // DB 에 새로운 토큰 업데이트 하기
            DocumentReference reference = db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));

            reference.update("cloudMessageToken", userInformation.getCloudMessageToken()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    System.out.println("DB에 새로운 cloudMessageToken 을 업데이트 했습니다");
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("새로운 cloudMessageToken 업데이트를 실패했습니다:" + e);
                }
            });
        }
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


