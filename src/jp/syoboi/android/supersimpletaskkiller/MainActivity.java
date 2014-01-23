package jp.syoboi.android.supersimpletaskkiller;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		taskKill();
		finish();
	}

	void taskKill() {

		ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

		int count = 0;
		for (RunningAppProcessInfo rpi: am.getRunningAppProcesses()) {
			for (String pkg: rpi.pkgList) {
				am.killBackgroundProcesses(pkg);
				count++;
			}
		}

		int padding = (int) (getResources().getDisplayMetrics().density * 4);

		final Toast tst = new Toast(this);
		TextView tv = new TextView(this);
		tv.setPadding(padding * 2, padding, padding * 2, padding);
		tv.setText("× " + count);
		tv.setTextSize(32);
		tv.setTypeface(Typeface.DEFAULT_BOLD);
		tv.setBackgroundColor(0xaa000000);
		tv.setTextColor(0xffffffff);
		tst.setView(tv);
		tst.setGravity(Gravity.CENTER, 0, 0);
		tst.show();

		tv.postDelayed(new Runnable() {

			@Override
			public void run() {
				tst.cancel();
			}
		}, 500);
	}

}
