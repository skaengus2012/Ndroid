package Ndroid.appFactory.util.business;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;

import Ndroid.appFactory.util.weakRef.NxWeakReference;
import Njava.function.IConsumer;
import Njava.util.business.CheckUtil;
import Njava.util.function.MaybeUtil;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * Define android view business util.
 *
 * Created by Doohyun on 2017. 8. 9..
 */
public class ViewUtil {

    protected ViewUtil() {}

    /**
     * Request create View by layout id
     *
     * @param context
     * @param layoutId
     * @return
     */
    public static View GetViewInflateResult(@NonNull Context context, @NonNull @LayoutRes int layoutId) {
        LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return lInflater.inflate(layoutId, null);
    }

    /**
     * Hide keyboard
     *
     * @param activity (Null 허용)
     */
    public static void HideKeyBoard(@Nullable Activity activity) {

        MaybeUtil.JustNullable(activity).flatMap(ac -> MaybeUtil.JustNullable(ac.getCurrentFocus())).subscribe(v -> {
            // inputManager 호출.
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            // 키보드 가리기.
            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }

    /**
     * Scroll To childView
     *
     * <pre>
     *     기본 y 값이 0
     * </pre>
     *
     * @param scrollView
     * @param childView
     */
    public static void ScrollTo(
            @NonNull ScrollView scrollView
            , @NonNull View childView) {
        ScrollTo(scrollView, childView, 0);
    }
    /**
     * Scroll To childView
     *
     * @param scrollView
     * @param childView
     * @param y
     */
    public static void ScrollTo(
            @NonNull ScrollView scrollView
            , @NonNull View childView
            , @NonNull int y) {

        CheckUtil.NullCheck(scrollView, "ScrollView will be required!!!!", RuntimeException.class);
        CheckUtil.NullCheck(childView, "ChildView will be required!!!!", RuntimeException.class);
        CheckUtil.NullCheck(y, "target Y will be non Null", RuntimeException.class);

        // 이동할 타겟 Y 를 구한다.
        int targetY = 0;
        {
            View view = childView;

            while (scrollView != view) {
                // 스크롤뷰도 아닐 때까지 반복.

                targetY += view.getTop();
                view = (View) view.getParent();
            }
        }

        // 스크롤뷰를 스크롤링한다.
        {
            final int finalTargetYn = targetY + y;

            scrollView.smoothScrollTo(0, finalTargetYn);
        }
    }

    /**
     * Post behavior by view.
     *
     * @param view
     * @param consumer
     * @param <T>
     */
    public static <T extends View>void Post(@NonNull T view, @NonNull IConsumer<T> consumer) {

        CheckUtil.NullCheck(view);
        CheckUtil.NullCheck(consumer);

        NxWeakReference<T> weakReference = new NxWeakReference<>(view);
        view.post(() -> weakReference.run(consumer));
    }

    /**
     * Post behavior by view. (With delayed)
     *
     * @param view
     * @param consumer
     * @param delayTime
     * @param <T>
     */
    public static <T extends View>void Post(@NonNull T view, @NonNull IConsumer<T> consumer, @NonNull int delayTime) {
        CheckUtil.NullCheck(view);
        CheckUtil.NullCheck(consumer);

        NxWeakReference<T> weakReference = new NxWeakReference<>(view);
        view.postDelayed(() -> weakReference.run(consumer), delayTime);
    }
}
