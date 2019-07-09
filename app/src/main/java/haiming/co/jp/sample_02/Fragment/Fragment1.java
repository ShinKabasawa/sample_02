package haiming.co.jp.sample_02.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import haiming.co.jp.sample_02.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    private String mParam1;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Fragment1","CreateVIew");
        Bundle bundle = getArguments();
        if (bundle != null) {
            mParam1 = getArguments().getString("test");
            Log.v("Fragment1","mParams = " + mParam1);
        }else{
            mParam1 = "Fragment内のテキストビュー";
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Button button = (Button)getActivity().findViewById(R.id.fragment_btn);
        button.setOnClickListener(frag_btnlistener);
    }

    // Fragment内のボタンクリックリスナー
    private View.OnClickListener frag_btnlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(),"Fragment内のボタンタップ",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.v("Fragment1","onCreateVIew");
        View layoutInflater = inflater.inflate(R.layout.fragment_fragment1,container,false);
        TextView textView = (TextView)layoutInflater.findViewById(R.id.frag_text);
        textView.setText(mParam1);
        mListener.onFragmentInteraction("test");
        return layoutInflater;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v("Fragment1","onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("Fragment1","onDetach");
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String uri);
    }
}
