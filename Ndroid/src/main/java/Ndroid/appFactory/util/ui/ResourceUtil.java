package Ndroid.appFactory.util.ui;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

/**
 * Background 처리에 대한 유틸 정의.
 *
 * Created by Doohyun on 2017. 3. 28..
 */

public class ResourceUtil {

    /**
     * Drawable 에 대한 리소스 처리.
     *
     * @param view
     * @param drawable
     */
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void SetBackground(@NonNull View view, @NonNull Drawable drawable) {
        int sdk = android.os.Build.VERSION.SDK_INT;

        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    /**
     * 컬러값 세팅 시, View 에 대한 백그라운드 처리.
     *
     * @param view
     * @param backgroundColor
     */
    public static void SetBackground(@NonNull View view, @NonNull @ColorInt int backgroundColor) {
        if (view instanceof ImageView) {
            // 적용하는 View 가 ImageView 라면, 리소스와 bitmap 모두 제거해줘야함.
            ImageView imageView = (ImageView) view;
            imageView.setImageResource(0);
            imageView.setImageBitmap(null);
        }

        view.setBackgroundColor(backgroundColor);
    }
}
