package net.awesomekorean.podo;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseCloudMessage extends FirebaseMessagingService {

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
}


