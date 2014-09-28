package com.amigos.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amigos.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTutorial#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentTutorial extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private TextView textView;
    // TODO: Rename and change types of parameters


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTutorial.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTutorial newInstance(String param1) {
        FragmentTutorial fragment = new FragmentTutorial();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    public FragmentTutorial() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         textView = (TextView) inflater.inflate(R.layout.fragment_tutorial, container, false);
        return textView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView.setText(getArguments().getString(ARG_PARAM1));
    }
}
