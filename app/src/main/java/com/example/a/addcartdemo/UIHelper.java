package com.example.a.addcartdemo;

import android.content.Context;

public class UIHelper {

	/**
	 * dp2px
	 * 
	 * @param context
	 * @return
	 */
	public static int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}

	/**
	 * px2dp
	 * 
	 * @param context
	 * @return
	 */
	public static int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale + 0.5f);
	}
	
	public static float getDensity(Context context){
		return context.getResources().getDisplayMetrics().density;
	}

	/**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param pxValue
     * @param fontScale
     * DisplayMetrics类中属性scaledDensity）
     * @return
     */
	 public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
     }

     /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param spValue
     * @param fontScale
     * DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
       final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
       return (int) (spValue * fontScale + 0.5f);
    }
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidthDip(Context context){
		int widthPx = UIHelper.getScreenWidth(context);
		int widthDip = UIHelper.px2dip(context, widthPx);
		return widthDip;
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeightDip(Context context){
		int heightPx = UIHelper.getScreenHeight(context);
		int heightDip = UIHelper.px2dip(context, heightPx);
		return heightDip;
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		if (context != null)
		{
			return context.getResources().getDisplayMetrics().widthPixels;
		} else {
			return 0;
		}
	}	

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		if (context != null) {
			return context.getResources().getDisplayMetrics().heightPixels;
		} else {
			return 0;
		}
	}	
}
