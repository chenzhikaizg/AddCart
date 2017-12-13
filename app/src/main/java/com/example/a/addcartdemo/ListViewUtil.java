 package com.example.a.addcartdemo;

 import android.content.Context;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ListAdapter;
 import android.widget.ListView;

 public class ListViewUtil {

    public static void adaptiveHight(Context context, ListView listView, float dividerHeight) {
        try {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            if (dividerHeight != -1) {
                totalHeight += UIHelper.dip2px(context, dividerHeight) * (listAdapter.getCount() - 1);
            }
            params.height = totalHeight;

            listView.setLayoutParams(params);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static int getItemsHight(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
         if (listAdapter == null) {
             return 0;
         }

         int totalHeight = 0;
         for (int i = 0; i < listAdapter.getCount(); i++) {
             View listItem = listAdapter.getView(i, null, listView);
             listItem.measure(0, 0);
             totalHeight += listItem.getMeasuredHeight();
         }

        return totalHeight;
    }



    public static int getItemHight(ListView listView)
    {
         ListAdapter listAdapter = listView.getAdapter();
         if (listAdapter == null) {
             return 0;
         }

         int itemHeight = 0;
         if(listAdapter.getCount()>0)
         {
              View listItem = listAdapter.getView(0, null, listView);
              listItem.measure(0, 0);
              itemHeight= listItem.getMeasuredHeight();
         }

        return itemHeight;
    }


 }
