package com.jingewenku.abrahamcaijin.commonutil;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * @Description:主要功能: Drawable 着色工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年07月20日 17:05
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class TintUtil {
    /**
     * 给Drawable着色
     * @param drawable 待着色的drawable
     * @param colorStateList  ColorStateList,如 ColorStateList.valueOf(Color.RED)
     * @return 完成着色的 Drawable
     */
    public static Drawable tintDrawable(Drawable drawable, ColorStateList colorStateList) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(wrappedDrawable, colorStateList);
        return wrappedDrawable;
    }

    /**
     *  给EditText光标着色
     * @param editText EditText对象
     * @param color Color,如Color.RED
     */
    public static void tintCursorDrawable(EditText editText, int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            if (mCursorDrawableRes <= 0) {
                return;
            }

            Drawable cursorDrawable = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            if (cursorDrawable == null) {
                return;
            }

            Drawable tintDrawable  = tintDrawable(cursorDrawable, ColorStateList.valueOf(color));
            Drawable[] drawables = new Drawable[] {tintDrawable, tintDrawable};
            fCursorDrawable.set(editor, drawables);
        } catch (Throwable ignored) {
        }
    }
}
