package com.jingewenku.abrahamcaijin.commonutil;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URL;

/**
 * Created by magic on 2017-3-24.
 * 实现html的图文混排
 */
public class HtmlHttpImageGetter implements Html.ImageGetter {
    TextView container;
    URI baseUri;
    static int urlNum = 0;

    public HtmlHttpImageGetter(TextView textView) {
        this.container = textView;
    }


//    public HtmlHttpImageGetter(TextView textView, String baseUrl) {
//        this.container = textView;
//        if (baseUrl != null) {
//            this.baseUri = URI.create(baseUrl);
//        }
//    }

    public HtmlHttpImageGetter(TextView textView, String baseUrl) {
        urlNum = 0;
        this.container = textView;
        if (baseUrl != null) {
            this.baseUri = URI.create(baseUrl);
        }
    }

    public Drawable getDrawable(String source) {
        UrlDrawable urlDrawable = new UrlDrawable();
        urlNum++;//限制详情页最多显示20张图片
        //    if (urlNum < 20) {
        // get the actual source
        ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable, this, container);
        asyncTask.execute(source);
        //  }
        // return reference to URLDrawable which will asynchronously load the image specified in the src tag
        return urlDrawable;
    }

    /**
     * Static inner {@link AsyncTask} that keeps a {@link WeakReference} to the {@link UrlDrawable}
     * and {@link HtmlHttpImageGetter}.
     * <p/>
     * This way, if the AsyncTask has a longer life span than the UrlDrawable,
     * we won't leak the UrlDrawable or the HtmlRemoteImageGetter.
     */
    private static class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        private final WeakReference<UrlDrawable> drawableReference;
        private final WeakReference<HtmlHttpImageGetter> imageGetterReference;
        private final WeakReference<View> containerReference;
        private String source;
        private boolean matchParentWidth;
        private float scale;


        public ImageGetterAsyncTask(UrlDrawable d, HtmlHttpImageGetter imageGetter, View container) {
            this.drawableReference = new WeakReference<>(d);
            this.imageGetterReference = new WeakReference<>(imageGetter);
            this.containerReference = new WeakReference<>(container);
        }

        @Override
        protected Drawable doInBackground(String... params) {
            //延时0.2秒，防止图片太多加载不出来
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            source = params[0];
            return fetchDrawable(source);
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result == null) {
                return;
            }
            final UrlDrawable urlDrawable = drawableReference.get();
            if (urlDrawable == null) {
                return;
            }
            // set the correct bound according to the result from HTTP call
            urlDrawable.setBounds(0, 0, (int) (result.getIntrinsicWidth() * scale), (int) (result.getIntrinsicHeight() * scale));

            // change the reference of the current drawable to the result from the HTTP call
            urlDrawable.drawable = result;

            final HtmlHttpImageGetter imageGetter = imageGetterReference.get();
            if (imageGetter == null) {
                return;
            }
            // redraw the image by invalidating the container
            imageGetter.container.invalidate();
            // re-set text to fix images overlapping text
            imageGetter.container.setText(imageGetter.container.getText());
        }

        /**
         * Get the Drawable from URL
         */
        public Drawable fetchDrawable(String urlString) {
            try {
                InputStream is = fetch(urlString);
                Drawable drawable = Drawable.createFromStream(is, "src");

                //去掉http://和最后的.gif
                String substring = urlString.substring(7, urlString.length() - 4);
                //获取最后一个字符
                String[] split = substring.split("/");
                String str = split[split.length - 1];
                if (str.contains("SB") || str.contains("XB") || str.contains("SBjianXB") || str.contains("WZ")) {
                    matchParentWidth = false;
                } else {
                    matchParentWidth = true;
                }
                if (str.length() <= 2) {
                    //if (str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")) {
                    matchParentWidth = false;
                    // }
                }

                scale = getScale(drawable);
                drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * scale), (int) (drawable.getIntrinsicHeight() * scale));
                //  drawable.setBounds(0, 0, (int) (DensityUtil.getXScreenpx(activity)), (int) (drawable.getIntrinsicHeight() * scale));
                return drawable;
            } catch (Exception e) {
                return null;
            }
        }

        private float getScale(Drawable drawable) {
            View container = containerReference.get();

            if (!matchParentWidth || container == null) {
                return 1f;
            }

            float maxWidth = container.getWidth();
            float originalDrawableWidth = drawable.getIntrinsicWidth();
            return maxWidth / originalDrawableWidth;
        }

        private InputStream fetch(String urlString) throws IOException {
            URL url;
            final HtmlHttpImageGetter imageGetter = imageGetterReference.get();
            if (imageGetter == null) {
                return null;
            }
            if (imageGetter.baseUri != null) {
                url = imageGetter.baseUri.resolve(urlString).toURL();
            } else {
                url = URI.create(urlString).toURL();
            }

            return (InputStream) url.getContent();
        }
    }

    @SuppressWarnings("deprecation")
    public class UrlDrawable extends BitmapDrawable {
        protected Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }
}