package com.example.asr;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.testapp.R;

/**
 * @ProjectName: TestApp
 * @Package: com.example.asr
 * @ClassName: LoadingDialog
 * @Description:
 * @Author: julius
 * @Email: julius_hy@qq.com
 * @Version: 1.0
 */
public class LoadingDialog {
    private volatile static Dialog loadingDlg;

    private static Dialog createDialog(Context context) {
        Dialog dlg = new Dialog(context, R.style.my_dialog_style);
        dlg.setContentView(R.layout.dialog_loading);

        Window window = dlg.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();

        Resources resource = context.getResources();

        DisplayMetrics dm = resource.getDisplayMetrics();

        param.width = dm.widthPixels;
        param.height = dm.heightPixels;
        param.gravity = Gravity.TOP;
        window.setAttributes(param);
        dlg.setCancelable(false);
        return dlg;
    }

    public static void show(Context context) {
        if (loadingDlg == null) {
            synchronized (Dialog.class) {
                if (loadingDlg == null) {
                    loadingDlg = createDialog(context);
                }
            }
        }
        loadingDlg.show();
    }

    public static void dismiss() {
        if (loadingDlg != null) {
            synchronized (Dialog.class) {
                if (loadingDlg != null) {
                    loadingDlg.dismiss();
                    loadingDlg = null;
                }
            }
        }
    }

}
