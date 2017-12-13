package com.example.a.addcartdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by 18810 on 2017/11/15.
 */

public class ShoppingCartActivity extends BaseActivity {

    private ListView lvCart;
    private GouWuCheAdapter adapter;
    private int position;
    private TextView tvCheckNum;
    private int num;
    private TextView tvDelete;
    private ImageView ivBack;
    private TextView tvAllCheck;

    private RelativeLayout rlAllCheck;
    private ImageView ivAllCheck;
    private TextView tvTotalPrice;
    private RelativeLayout rl2Pay;

    //表示合计的价格
    private double totalPrice;
    //表示是不是全选
    private int allCheckStatus;

    private List<String> listSelect;

    private List<ShoppingCarBean.Product> spList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
    }
    @Override
    public void initView() {
        TextView tvTtile = (TextView) findViewById(R.id.tv_title);
        tvTtile.setText("购物车");
        lvCart = (ListView) findViewById(R.id.lv_shopping_cart);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvAllCheck = (TextView) findViewById(R.id.tv_quanxuan);
        rlAllCheck = (RelativeLayout) findViewById(R.id.rl_all_check);
        ivAllCheck = (ImageView) findViewById(R.id.iv_quanxuan);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        rl2Pay = (RelativeLayout) findViewById(R.id.rl_to_pay);
    }
    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        tvAllCheck.setOnClickListener(this);
        rlAllCheck.setOnClickListener(this);
        rl2Pay.setOnClickListener(this);
    }
    @Override
    public void initData() {

        getShopplingcarList();

    }

    /**
     * 请求数据
     */
    private void getShopplingcarList() {
               spList = new ArrayList<ShoppingCarBean.Product>();
                    for (int i=0;i<3;i++){
                        ShoppingCarBean.Product spBean = new ShoppingCarBean.Product();
                        ShoppingCarBean.CartItem sc = new ShoppingCarBean.CartItem();
                        sc.number=1;
                        sc.productName="奥野蛮";
                         sc.productPrice = 234;
                        List<ShoppingCarBean.Specs> sslist = new ArrayList<ShoppingCarBean.Specs>();
                        for (int a=0;a<2;a++){

                            ShoppingCarBean.Specs ssBean = new ShoppingCarBean.Specs();
                            if (a==0){
                                ssBean.specKey="颜色";
                                ssBean.specValue="黑色";
                            }else {
                                ssBean.specKey="罩杯";
                                ssBean.specValue="38F";
                            }

                           sslist.add(ssBean);
                        }
                        spBean.specs = sslist;
                        spBean.cartItem= sc;
                        spList.add(spBean);
                    }




                    adapter = new GouWuCheAdapter(ShoppingCartActivity.this, clickListener, spList);
                    adapter.addAddressBeans(spList);
                    lvCart.setAdapter(adapter);
                    ListViewUtil.adaptiveHight(ShoppingCartActivity.this, lvCart, 1f);
                    lvCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rl_all_check:
            case R.id.tv_quanxuan:
                if (adapter.getCount() == 0) {
                    showShortMsg("请先添加商品到购物车");
                } else {
                    if (allCheckStatus == 0) {
                        for (ShoppingCarBean.Product value :spList) {
                            value.status = 1;
                        }
                        allCheckStatus = 1;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.quanzhong_icon_normal_).into(ivAllCheck);
                        computePrice();
                    } else {
                        for (ShoppingCarBean.Product value : spList) {
                            value.status = 0;
                        }
                        allCheckStatus = 0;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivAllCheck);
                        computePrice();
                    }
                    adapter.notifyDataSetChanged();
                }

                break;
            case R.id.rl_to_pay:

                break;
            default:
                super.onClick(v);
                break;
        }
    }

    /**
     * 计算总价
     */
    private void computePrice() {
        totalPrice = 0;
        for (ShoppingCarBean.Product value : spList) {
            if (value.status == 1) {
                totalPrice += value.cartItem.productPrice * value.cartItem.number;
            }

        }

        tvTotalPrice.setText("￥" + totalPrice);


    }

    List mallStatus = new ArrayList();


    public View.OnClickListener clickListener = new View.OnClickListener() {

        private ImageView ivCheck;

        @Override
        public void onClick(View v) {
            position = Integer.valueOf(((TextView) ((ViewGroup) v.getParent()).findViewById(R.id.tv_position)).getText().toString());
            switch (v.getId()) {
                case R.id.iv_product_jian:

                    tvCheckNum = (TextView) ((ViewGroup) v.getParent()).findViewById(R.id.tv_check_num);
                    num = Integer.valueOf(tvCheckNum.getText().toString());
                    if (num == 1) {
                        showShortMsg("数量不能小于1");
                        return;
                    } else {
                        tvCheckNum.setText(num - 1 + "");

                    }
                    adapter.getItem(position).cartItem.number = num - 1;
                    computePrice();

                    break;
                case R.id.iv_product_jia:
                    tvCheckNum = (TextView) ((ViewGroup) v.getParent()).findViewById(R.id.tv_check_num);
                    num = Integer.valueOf(tvCheckNum.getText().toString());
                    tvCheckNum.setText(num + 1 + "");
                    adapter.getItem(position).cartItem.number = num + 1;
                    computePrice();
                    break;
                case R.id.tv_delete:

                    deleteAddress(adapter.getItemId(position));
                    break;
                case R.id.iv_check:
                    mallStatus.clear();


                    ivCheck = (ImageView) ((ViewGroup) v.getParent()).findViewById(R.id.iv_check);

                    if (adapter.getItem(position).status == 0) {
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.quanzhong_icon_normal_).into(ivCheck);
                        adapter.getItem(position).status = 1;
//
                    } else {
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivCheck);
                        adapter.getItem(position).status = 0;

                    }

                    for (ShoppingCarBean.Product value : spList) {
                        mallStatus.add(value.status);

                    }
                    if (mallStatus.contains(0)) {
                        allCheckStatus = 0;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivAllCheck);
                    } else {
                        allCheckStatus = 1;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.quanzhong_icon_normal_).into(ivAllCheck);
                    }
                    computePrice();
                    break;
            }
        }
    };
    /**
     * 删除条目
     *
     * @param itemId
     */
    private void deleteAddress(long itemId) {

                    adapter.deleteAddress(position);
        spList.remove(position);
                    if (spList.size() == 0) {
                        allCheckStatus = 0;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivAllCheck);
                        rl2Pay.setBackgroundResource(R.color.text_color_grey);
                    }
                    computePrice();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 1) {

            }
        }


    }
}
