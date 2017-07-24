package app.com.zolospace.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import app.com.zolospace.App;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;


public class ResourcesUtil {
    private static Context context = App.getContext();
    private static Resources.Theme theme = App.getContext().getTheme();

    public static Drawable getDrawableById(int resId) {
        return SDK_INT >= LOLLIPOP ? context.getResources().getDrawable(resId, theme) :
                context.getResources().getDrawable(resId);
    }

    public static String getString(int resId) {
        return SDK_INT >= LOLLIPOP ? context.getResources().getString(resId) :
                context.getResources().getString(resId);
    }

    public static void showSnackbar(Context context, String message) {
        Snackbar.make((((Activity) context).findViewById(android.R.id.content)), message, Snackbar.LENGTH_LONG).show();
    }

    public static void showAlertBoxForConfirmation(Context context, String msg, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {

        new AlertDialog.Builder(context).setTitle(null).setMessage(msg)
                .setPositiveButton("YES", okListener)
                .setNegativeButton("NO", cancelListener)
                .show().setCancelable(false);
    }

    public static void showAlertBox(Context context, String msg, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context).setTitle(null).setMessage(msg).setPositiveButton("OK", okListener).show().
                setCancelable(false);
    }

    public static void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public static void hideKeyboard(Activity activity) {
        try {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
