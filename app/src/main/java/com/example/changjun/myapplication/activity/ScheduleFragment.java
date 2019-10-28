package com.example.changjun.myapplication.activity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.util.Schedule;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
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
    private TextView monday[] = new TextView[20];
    private TextView tuesday[] = new TextView[20];
    private TextView wednesday[] = new TextView[20];
    private TextView thursday[] = new TextView[20];
    private TextView friday[] = new TextView[20];
    private Schedule schedule ;
    @Override
    public void onActivityCreated(Bundle b){

        super.onActivityCreated(b);
        schedule = Schedule.getInstance();
        monday[0] = (TextView)getView().findViewById(R.id.monday1);
        monday[1] = (TextView)getView().findViewById(R.id.monday2);
        monday[2] = (TextView)getView().findViewById(R.id.monday3);
        monday[3] = (TextView)getView().findViewById(R.id.monday4);
        monday[4] = (TextView)getView().findViewById(R.id.monday5);
        monday[5] = (TextView)getView().findViewById(R.id.monday6);
        monday[6] = (TextView)getView().findViewById(R.id.monday7);
        monday[7] = (TextView)getView().findViewById(R.id.monday8);
        monday[8] = (TextView)getView().findViewById(R.id.monday9);
        monday[9] = (TextView)getView().findViewById(R.id.monday10);
        monday[10] = (TextView)getView().findViewById(R.id.monday11);
        monday[11] = (TextView)getView().findViewById(R.id.monday12);
        monday[12] = (TextView)getView().findViewById(R.id.monday13);
        monday[13] = (TextView)getView().findViewById(R.id.monday14);
        monday[14] = (TextView)getView().findViewById(R.id.monday15);
        monday[15] = (TextView)getView().findViewById(R.id.monday16);
        monday[16] = (TextView)getView().findViewById(R.id.monday17);
        monday[17] = (TextView)getView().findViewById(R.id.monday18);
        monday[18] = (TextView)getView().findViewById(R.id.monday19);
        monday[19] = (TextView)getView().findViewById(R.id.monday20);

        tuesday[0] = (TextView)getView().findViewById(R.id.tuesday1);
        tuesday[1] = (TextView)getView().findViewById(R.id.tuesday2);
        tuesday[2] = (TextView)getView().findViewById(R.id.tuesday3);
        tuesday[3] = (TextView)getView().findViewById(R.id.tuesday4);
        tuesday[4] = (TextView)getView().findViewById(R.id.tuesday5);
        tuesday[5] = (TextView)getView().findViewById(R.id.tuesday6);
        tuesday[6] = (TextView)getView().findViewById(R.id.tuesday7);
        tuesday[7] = (TextView)getView().findViewById(R.id.tuesday8);
        tuesday[8] = (TextView)getView().findViewById(R.id.tuesday9);
        tuesday[9] = (TextView)getView().findViewById(R.id.tuesday10);
        tuesday[10] = (TextView)getView().findViewById(R.id.tuesday11);
        tuesday[11] = (TextView)getView().findViewById(R.id.tuesday12);
        tuesday[12] = (TextView)getView().findViewById(R.id.tuesday13);
        tuesday[13] = (TextView)getView().findViewById(R.id.tuesday14);
        tuesday[14] = (TextView)getView().findViewById(R.id.tuesday15);
        tuesday[15] = (TextView)getView().findViewById(R.id.tuesday16);
        tuesday[16] = (TextView)getView().findViewById(R.id.tuesday17);
        tuesday[17] = (TextView)getView().findViewById(R.id.tuesday18);
        tuesday[18] = (TextView)getView().findViewById(R.id.tuesday19);
        tuesday[19] = (TextView)getView().findViewById(R.id.tuesday20);

        wednesday[0] = (TextView)getView().findViewById(R.id.wednseday1);
        wednesday[1] = (TextView)getView().findViewById(R.id.wednseday2);
        wednesday[2] = (TextView)getView().findViewById(R.id.wednseday3);
        wednesday[3] = (TextView)getView().findViewById(R.id.wednseday4);
        wednesday[4] = (TextView)getView().findViewById(R.id.wednseday5);
        wednesday[5] = (TextView)getView().findViewById(R.id.wednseday6);
        wednesday[6] = (TextView)getView().findViewById(R.id.wednseday7);
        wednesday[7] = (TextView)getView().findViewById(R.id.wednseday8);
        wednesday[8] = (TextView)getView().findViewById(R.id.wednseday9);
        wednesday[9] = (TextView)getView().findViewById(R.id.wednseday10);
        wednesday[10] = (TextView)getView().findViewById(R.id.wednseday11);
        wednesday[11] = (TextView)getView().findViewById(R.id.wednseday12);
        wednesday[12] = (TextView)getView().findViewById(R.id.wednseday13);
        wednesday[13] = (TextView)getView().findViewById(R.id.wednseday14);
        wednesday[14] = (TextView)getView().findViewById(R.id.wednseday15);
        wednesday[15] = (TextView)getView().findViewById(R.id.wednseday16);
        wednesday[16] = (TextView)getView().findViewById(R.id.wednseday17);
        wednesday[17] = (TextView)getView().findViewById(R.id.wednseday18);
        wednesday[18] = (TextView)getView().findViewById(R.id.wednseday19);
        wednesday[19] = (TextView)getView().findViewById(R.id.wednseday20);

        thursday[0] = (TextView)getView().findViewById(R.id.thursday1);
        thursday[1] = (TextView)getView().findViewById(R.id.thursday2);
        thursday[2] = (TextView)getView().findViewById(R.id.thursday3);
        thursday[3] = (TextView)getView().findViewById(R.id.thursday4);
        thursday[4] = (TextView)getView().findViewById(R.id.thursday5);
        thursday[5] = (TextView)getView().findViewById(R.id.thursday6);
        thursday[6] = (TextView)getView().findViewById(R.id.thursday7);
        thursday[7] = (TextView)getView().findViewById(R.id.thursday8);
        thursday[8] = (TextView)getView().findViewById(R.id.thursday9);
        thursday[9] = (TextView)getView().findViewById(R.id.thursday10);
        thursday[10] = (TextView)getView().findViewById(R.id.thursday11);
        thursday[11] = (TextView)getView().findViewById(R.id.thursday12);
        thursday[12] = (TextView)getView().findViewById(R.id.thursday13);
        thursday[13] = (TextView)getView().findViewById(R.id.thursday14);
        thursday[14] = (TextView)getView().findViewById(R.id.thursday15);
        thursday[15] = (TextView)getView().findViewById(R.id.thursday16);
        thursday[16] = (TextView)getView().findViewById(R.id.thursday17);
        thursday[17] = (TextView)getView().findViewById(R.id.thursday18);
        thursday[18] = (TextView)getView().findViewById(R.id.thursday19);
        thursday[19] = (TextView)getView().findViewById(R.id.thursday20);

        friday[0] = (TextView)getView().findViewById(R.id.friday1);
        friday[1] = (TextView)getView().findViewById(R.id.friday2);
        friday[2] = (TextView)getView().findViewById(R.id.friday3);
        friday[3] = (TextView)getView().findViewById(R.id.friday4);
        friday[4] = (TextView)getView().findViewById(R.id.friday5);
        friday[5] = (TextView)getView().findViewById(R.id.friday6);
        friday[6] = (TextView)getView().findViewById(R.id.friday7);
        friday[7] = (TextView)getView().findViewById(R.id.friday8);
        friday[8] = (TextView)getView().findViewById(R.id.friday9);
        friday[9] = (TextView)getView().findViewById(R.id.friday10);
        friday[10] = (TextView)getView().findViewById(R.id.friday11);
        friday[11] = (TextView)getView().findViewById(R.id.friday12);
        friday[12] = (TextView)getView().findViewById(R.id.friday13);
        friday[13] = (TextView)getView().findViewById(R.id.friday14);
        friday[14] = (TextView)getView().findViewById(R.id.friday15);
        friday[15] = (TextView)getView().findViewById(R.id.friday16);
        friday[16] = (TextView)getView().findViewById(R.id.friday17);
        friday[17] = (TextView)getView().findViewById(R.id.friday18);
        friday[18] = (TextView)getView().findViewById(R.id.friday19);
        friday[19] = (TextView)getView().findViewById(R.id.friday20);
        new ScheduleTask().execute(null, null, null);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
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

    private class ScheduleTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            schedule.setting(monday,tuesday,wednesday,thursday,friday);
            Log.e("시간표테스트","시작");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
