package littleq.mammoth.com.littleq.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;
import littleq.mammoth.com.littleq.ui.activity.UserResetPwdActivity;

/**
 * Created by Hunter on 2016/11/10.
 */

public class ModifyPwdDialog extends Dialog {
    private Context ctx;
    private View v = null;

    public ModifyPwdDialog(Context context) {
        super(context);
        ctx = context;
    }
    public ModifyPwdDialog(Context context, int theme) {
        super(context, theme);
    }
    public static class Builder {
        private Context context;
        private TextView tvCancel;
        private TextView tvOk;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private TextView tvPwdTip;
        private TextView tvForgetPwd;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPositiveButton(DialogInterface.OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(DialogInterface.OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }

        public ModifyPwdDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ModifyPwdDialog dialog = new ModifyPwdDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_modify_pwd, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tvPwdTip = (TextView) layout.findViewById(R.id.tv_pwd_tip);
            tvForgetPwd = (TextView) layout.findViewById(R.id.tv_forget_pwd);
            tvForgetPwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , UserResetPwdActivity.class);
                    context.startActivity(intent);
                    dialog.dismiss();
                }
            });
            tvCancel = (TextView) layout.findViewById(R.id.tv_cancel);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    negativeButtonClickListener.onClick(dialog,
                            DialogInterface.BUTTON_NEGATIVE);
                }
            });
            tvOk = (TextView) layout.findViewById(R.id.tv_ok);
            tvOk.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    positiveButtonClickListener.onClick(dialog,
                            DialogInterface.BUTTON_POSITIVE);
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }
    }
}