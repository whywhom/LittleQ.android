package littleq.mammoth.com.littleq.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;

/**
 * Created by Hunter on 2016/11/10.
 */

public class ConfirmPwdDialog extends Dialog {
    private Context ctx;
    private View v = null;

    public ConfirmPwdDialog(Context context) {
        super(context);
        ctx = context;
    }
    public ConfirmPwdDialog(Context context, int theme) {
        super(context, theme);
    }
    public static class Builder {
        private Context context;
        private TextView tvCancel;
        private TextView tvOk;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private TextView tvErrPwd;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPositiveButton(OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }

        public ConfirmPwdDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ConfirmPwdDialog dialog = new ConfirmPwdDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_confirm_pwd, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
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