package net.awesomekorean.podo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import net.awesomekorean.podo.login.SignUp;
import net.awesomekorean.podo.qna.AskQuestion;
import net.awesomekorean.podo.qna.Faq;
import net.awesomekorean.podo.qna.MyQuestions;

public class MainQna extends Fragment implements View.OnClickListener{

    View view;
    Intent intent;

    TextView faq;
    TextView myQuestions;

    View underBarFaq;
    View underBarMyQ;

    Button btnAsk;

    Fragment fragment;


    public static MainQna newInstance() {
        return new MainQna();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = Faq.newInstance();
        setChildFragment(fragment);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_qna, container, false);

        faq = view.findViewById(R.id.faq);
        faq.setOnClickListener(this);
        myQuestions = view.findViewById(R.id.myQuestions);
        myQuestions.setOnClickListener(this);
        underBarFaq = view.findViewById(R.id.underBarFaq);
        underBarMyQ = view.findViewById(R.id.underBarMyQuestions);
        btnAsk = view.findViewById(R.id.btnAsk);
        btnAsk.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.faq :
                fragment = Faq.newInstance();
                setChildFragment(fragment);
                faq.setTextColor(Color.parseColor("#6A69EF"));
                myQuestions.setTextColor(Color.parseColor("#7E8494"));
                underBarFaq.setVisibility(View.VISIBLE);
                underBarMyQ.setVisibility(View.INVISIBLE);
                break;

            case R.id.myQuestions :
                fragment = MyQuestions.newInstance();
                setChildFragment(fragment);
                faq.setTextColor(Color.parseColor("#7E8494"));
                myQuestions.setTextColor(Color.parseColor("#6A69EF"));
                underBarFaq.setVisibility(View.INVISIBLE);
                underBarMyQ.setVisibility(View.VISIBLE);
                break;

            case R.id.btnAsk :
                intent = new Intent(getContext(), AskQuestion.class);
                startActivity(intent);
                break;
        }
    }

    private void setChildFragment(Fragment fragment) {

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if (!fragment.isAdded()) {
            transaction.replace(R.id.child_fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
