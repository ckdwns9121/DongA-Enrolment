package com.example.changjun.myapplication.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.util.AddListAdapter;
import com.example.changjun.myapplication.util.Course;
import com.example.changjun.myapplication.util.CustomProgress;
import com.example.changjun.myapplication.util.Department;
import com.example.changjun.myapplication.util.RequsetServer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BasketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BasketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BasketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static ListView addListView;
    public static AddListAdapter addListAdapter;
    public static ArrayList<Course> addList;
    public CustomProgress customProgress;

    private Button s_button,b_button,d_button;
    public static TextView totalCredit;

    private RequsetServer requsetServer;
    private Department information;

    public static int total=0;


    public void onActivityCreated(Bundle b) {

        super.onActivityCreated(b);
        s_button = (Button) getView().findViewById(R.id.startButton);
        d_button=(Button) getView().findViewById(R.id.deleteButton);
        requsetServer = RequsetServer.getInstance();
        information=Department.getInstace();

        addListView = (ListView)getView().findViewById(R.id.addList);
        addListAdapter = new AddListAdapter(getActivity(),information.addList);
        addListView.setAdapter(addListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
