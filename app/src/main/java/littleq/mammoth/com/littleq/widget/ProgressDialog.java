package littleq.mammoth.com.littleq.widget;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import littleq.mammoth.com.littleq.R;

/**
 * 类名: ProgressDialog</br> 
 * 描述: </br>
 * 开发人员： longtaoge</br>
 * 创建时间： 2013-5-3 
 */ 

public class ProgressDialog extends Dialog {
	private Context context = null;
	private Dialog loadingDialog;
	public ProgressDialog(Context context) {
		super(context);
		this.context = context;
	}

	public ProgressDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public static Dialog createDialog(Context context, String tip) {

		// 首先得到整个View
		View view = LayoutInflater.from(context).inflate(
				R.layout.customprogressdialog, null);
		// 获取整个布局
		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.dialog_view);
		// 页面中的Img
		ImageView img = (ImageView) view.findViewById(R.id.loadingImageView);
		// 页面中显示文本
		TextView tipText = (TextView) view.findViewById(R.id.id_tv_loadingmsg);

		// 加载动画，动画用户使img图片不停的旋转
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.progress_round);
		// 显示动画
		img.startAnimation(animation);
		// 显示文本
		tipText.setText(tip);

		// 创建自定义样式的Dialog
		Dialog loadingDialog = new Dialog(context, R.style.CustomProgressDialog);
		// 设置返回键无效
		loadingDialog.setCancelable(false);
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));

		return loadingDialog;
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (loadingDialog == null) {
			return;
		}

		ImageView imageView = (ImageView) loadingDialog
				.findViewById(R.id.loadingImageView);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
				.getBackground();
		animationDrawable.start();
	}

	/**
	 * 
	 * [Summary] setTitile 标题
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public Dialog setTitile(String strTitle) {
		return loadingDialog;
	}

	/**
	 * 
	 * [Summary] setMessage 提示内容
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public Dialog setMessage(String strMessage) {
		TextView tvMsg = (TextView) loadingDialog
				.findViewById(R.id.id_tv_loadingmsg);
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
		return loadingDialog;
	}
}
