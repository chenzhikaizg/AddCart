package com.example.a.addcartdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by gaoweiwei on 15/6/4.
 */
public class GouWuCheAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ShoppingCarBean.Product> values;
    private View.OnClickListener clickListener;

    private  String[] list;
    private  List<String> ss;
    public GouWuCheAdapter(Context context, View.OnClickListener clickListener, List<ShoppingCarBean.Product> lists) {
        this.context = context;
        this.clickListener = clickListener;
        layoutInflater = LayoutInflater.from(context);
        values = new ArrayList<ShoppingCarBean.Product>();
        ss = new ArrayList<String>();





    }

    public void addAddressBeans(List<ShoppingCarBean.Product> values) {
        this.values.addAll(values);

        notifyDataSetChanged();
    }

    public void clear() {
        values.clear();
    }

    public List<ShoppingCarBean.Product> getList() {
        return values;
    }

    public void setDefaultAddress(int position) {
        for (int i = 0; i < values.size(); i++) {

        }
        notifyDataSetChanged();
    }

    public void addAddressBean(ShoppingCarBean.Product value) {

        values.add(value);
        notifyDataSetChanged();
    }

    public void editAddressBean(int position, ShoppingCarBean.Product value) {

        values.set(position, value);
        notifyDataSetChanged();
    }

    public void deleteAddress(int position) {
        values.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public ShoppingCarBean.Product getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).cartItem.productId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_gouwuche_item, null);
            viewHolder = new ViewHolder();
            viewHolder.ivProduct = (ImageView) convertView.findViewById(R.id.iv_product);
            viewHolder.tvProduct = (TextView) convertView.findViewById(R.id.tv_product);
            viewHolder.tvPriceNum = (TextView) convertView.findViewById(R.id.tv_price_num);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.tvDelete = (TextView) convertView.findViewById(R.id.tv_delete);

            viewHolder.tvCheckNum = (TextView) convertView.findViewById(R.id.tv_check_num);
            viewHolder.tvPosition = (TextView) convertView.findViewById(R.id.tv_position);
            viewHolder.ivProductJian = (ImageView) convertView.findViewById(R.id.iv_product_jian);
            viewHolder.ivProductJia = (ImageView) convertView.findViewById(R.id.iv_product_jia);
            viewHolder.ivCheck = (ImageView) convertView.findViewById(R.id.iv_check);
            viewHolder.lvAllType = (ListView)convertView.findViewById(R.id.lv_all_type);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShoppingCarBean.Product value = getItem(position);

        viewHolder.tvPosition.setText(position + "");
        viewHolder.ivCheck.setOnClickListener(clickListener);
        viewHolder.ivProductJian.setOnClickListener(clickListener);
        viewHolder.ivProductJia.setOnClickListener(clickListener);
        viewHolder.tvDelete.setOnClickListener(clickListener);

        Glide.with(context).load(R.mipmap.weixianzhong_icon_normal_).into(viewHolder.ivCheck);
        if (value.status == 1) {
            Glide.with(context).load(R.mipmap.quanzhong_icon_normal_).into(viewHolder.ivCheck);
        } else {
            Glide.with(context).load(R.mipmap.weixianzhong_icon_normal_).into(viewHolder.ivCheck);
        }
        viewHolder.tvCheckNum.setText(value.cartItem.number + "");
        viewHolder.tvPriceNum.setText(value.cartItem.productPrice+"");

        String type = "";
        String typeValue = "";
        ss.clear();


        for (int a = 0 ;a<values.get(position).specs.size() ;a++){
            type = values.get(position).specs.get(a).specKey;

                typeValue  = values.get(position).specs.get(a).specValue;

            ss.add(type+typeValue);
        }


        ColorAndSizeAdapter adapter = new ColorAndSizeAdapter(context);
        adapter.addData(ss);
        viewHolder.lvAllType.setAdapter(adapter);
        ListViewUtil.adaptiveHight(context,viewHolder.lvAllType,0f);
        return convertView;
    }

    private class ViewHolder {
        private ImageView ivProduct;
        private TextView tvProduct;
        private TextView tvPriceNum;
        private TextView tvDelete;

        private TextView tvSize;
        private TextView tvCheckNum;
        private TextView tvPosition;
        private ImageView ivProductJian;
        private ImageView ivProductJia;
        private ImageView ivCheck;
        private TextView tvPrice;
        private ListView lvAllType;
    }
}
