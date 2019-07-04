package haiming.co.jp.sample_02.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class sampleDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("サンプル用のDialogFragment");
        builder.setPositiveButton("はい", positive_btn_listener);
        builder.setNegativeButton("いいえ", negative_btn_listener);
        this.setCancelable(false);
        return builder.create();
    }

    private DialogInterface.OnClickListener positive_btn_listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getContext(),"はいがタップされました。",Toast.LENGTH_SHORT).show();
        }
    };

    private DialogInterface.OnClickListener negative_btn_listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getContext(),"いいえがタップされました。",Toast.LENGTH_SHORT).show();
        }
    };
}
