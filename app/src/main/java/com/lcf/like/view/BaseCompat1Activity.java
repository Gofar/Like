package com.lcf.like.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author LiChaofeng
 * @description
 * @copyright © Medlinker
 * @date 2016/8/5 14:52
 * @since 3.4
 */
public abstract class BaseCompat1Activity extends BaseCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }
}
