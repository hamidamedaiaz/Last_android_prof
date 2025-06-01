package edu.polytech.td4ex3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;


public class Screen6Fragment extends Fragment {
    private final static int NUM_FRAGMENT = 6;
    private final String TAG = "frallo "+getClass().getSimpleName();
    private List<String> names;
    private Notifiable notifiable;
    public Screen6Fragment() {
        //Log.d(TAG,"screenFragment type 6 created"); // Required empty public constructor
        names = List.of("Cléments", "Sofiane", "Séréna", "Aaron","Valentin", "Raphaël", "Zakary", "Maxime", "Mathis", "Badr", "Ivann");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (requireActivity() instanceof Notifiable) {
            notifiable = (Notifiable) requireActivity();
            //Log.d(TAG, "Class " + requireActivity().getClass().getSimpleName() + " implements Notifiable.");
        } else {
            throw new AssertionError("Classe " + requireActivity().getClass().getName() + " ne met pas en œuvre Notifiable.");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen6, container, false);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_expandable_list_item_1, names);
        ((ListView)view.findViewById(R.id.listview)).setAdapter(adapter);
        return view;
    }
}