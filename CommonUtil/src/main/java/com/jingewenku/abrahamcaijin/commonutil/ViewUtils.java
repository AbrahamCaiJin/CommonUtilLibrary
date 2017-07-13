package com.jingewenku.abrahamcaijin.commonutil;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.view.View.MeasureSpec;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 15:42
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class ViewUtils {

    private ViewUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 把自身从父View中移除
     */
    public static void removeSelfFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(view);
            }
        }
    }

    /**
     * 判断触点是否落在该View上
     */
    public static boolean isTouchInView(MotionEvent ev, View v) {
        int[] vLoc = new int[2];
        v.getLocationOnScreen(vLoc);
        float motionX = ev.getRawX();
        float motionY = ev.getRawY();
        return motionX >= vLoc[0] && motionX <= (vLoc[0] + v.getWidth())
            && motionY >= vLoc[1] && motionY <= (vLoc[1] + v.getHeight());
    }

    /**
     *
     * @param view
     * @param isAll
     */
    public static void requestLayoutParent(View view, boolean isAll) {
        ViewParent parent = view.getParent();
        while (parent != null && parent instanceof View) {
            if (!parent.isLayoutRequested()) {
                parent.requestLayout();
                if (!isAll) {
                    break;
                }
            }
            parent = parent.getParent();
        }
    }

    /**
     *
     * @param bmp
     * @param big
     * @return
     */
    public static Bitmap bigImage(Bitmap bmp, float big) {
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(big, big);
        return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
    }

    /**
     * 给TextView设置下划线
     * @param textView
     */
    public static void setTVUnderLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.getPaint().setAntiAlias(true);
    }


    static PopupWindow popupWindow;

    /**
     * 显示PopupWindow
     * @param context
     * @param resId
     * @param root
     * @param paramsType
     * @return
     */
    public static View showPopupWindow(Context context, int resId, View root, int paramsType) {
        View popupView;
        popupView = LayoutInflater.from(context).inflate(resId, null);

        switch (paramsType) {
            case 1:
                popupWindow = new PopupWindow(popupView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                break;
            case 2:
                popupWindow = new PopupWindow(popupView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                break;
            case 3:
                popupWindow = new PopupWindow(popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                break;
            case 4:
                popupWindow = new PopupWindow(popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                break;
            default:
                popupWindow = new PopupWindow(popupView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                break;
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(root);
        return popupView;
    }

    /**
     * 关闭PopupWindow
     */
    public static void dismissPopup() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     *截图
     * @param v
     * @return
     */
    public static Bitmap captureView(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        return v.getDrawingCache();
    }

    /**
     *截图
     * @param v
     * @return
     */
    public static Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    /**
     * 截图
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    /**
     * 获取Activity的截图
     * @param activity
     * @return
     */
    public static Bitmap getActivityBitmap(Activity activity) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache();
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取工具栏高度
     * @param context
     * @return
     */
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return toolbarHeight;
    }

    /**
     * 获取导航栏高度
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 测量view
     * @param view
     */
    public static void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 获取view的宽度
     * @param view
     * @return
     */
    public static int getViewWidth(View view) {
        measureView(view);
        return view.getMeasuredWidth();
    }

    /**
     * 获取view的高度
     * @param view
     * @return
     */
    public static int getViewHeight(View view) {
        measureView(view);
        return view.getMeasuredHeight();
    }

    /**
     * 获取view的上下文
     * @param view
     * @return
     */
    public static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        throw new IllegalStateException("View " + view + " is not attached to an Activity");
    }


}
