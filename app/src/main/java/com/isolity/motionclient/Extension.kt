package com.isolity.motionclient

import android.support.annotation.IdRes
import android.view.View

/**
 * Created by shohei52a on 2018/12/28.
 */
fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}