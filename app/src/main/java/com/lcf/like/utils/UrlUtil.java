package com.lcf.like.utils;

import android.text.TextUtils;

/**
 * @author lcf
 * @version 1.0
 * @description url utils
 * @time 2016/6/25 14:24
 */
public class UrlUtil {
    /**
     * whether the url is a image url
     * @param url url
     * @return is image url:true,else:false
     */
    public static boolean isImageUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        // if the url is endsWith ".jpg/.png",it is a image url.
        return url.endsWith(".jpg") || url.endsWith(".png");
    }
}
