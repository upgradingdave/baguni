package net.awesomekorean.podo;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class DownloadAudio {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    private Context context;
    private String folder;
    private String audio;

    public DownloadAudio(Context context, String folder, String audio) {
        this.context = context;
        this.folder = folder;
        this.audio = audio;
    }

    public void downloadAudio() {
        // 오디오파일 다운로드 하기
        StorageReference reference = storage.getReference().child(folder).child(audio);

        try {
            final File file = new File(context.getFilesDir(), audio);

            reference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    System.out.println("오디오파일 다운로드를 성공 했습니다.: " + file.getPath());
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("오디오파일 다운로드를 실패 했습니다.: "+e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
