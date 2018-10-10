package com.mhky.dianhuotong.shop.precenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderBottomInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.bean.OrderTopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class OrderDataPresenter {

    private static final String TAG = "OrderDataPresenter";

    /**
     * 获取订单List
     * @param orderBaseInfo
     * @return
     */
    public List<OrderInfo> getOrderList(OrderBaseInfo orderBaseInfo) {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (int a = 0; a < orderBaseInfo.getContent().size(); a++) {
            BaseTool.logPrint(TAG, "getOrderList: ---a" + a);
            OrderInfo orderInfoTop = new OrderInfo(1);
            OrderTopInfo orderTopInfo = new OrderTopInfo();
            orderTopInfo.setName(orderBaseInfo.getContent().get(a).getSellerInfo().getName());
            orderTopInfo.setShopID(orderBaseInfo.getContent().get(a).getSellerInfo().getId());
            orderInfoTop.setOrderTopInfo(orderTopInfo);
            orderInfoList.add(orderInfoTop);

            int goodsNumber = 0;
            for (int b = 0; b < orderBaseInfo.getContent().get(a).getItems().size(); b++) {
                BaseTool.logPrint(TAG, "getOrderList: ------b" + b);
                goodsNumber = goodsNumber + orderBaseInfo.getContent().get(a).getItems().get(b).getQuantity();
                OrderInfo orderInfoBody = new OrderInfo(2);
                orderInfoBody.setOrderBodyInfo(orderBaseInfo.getContent().get(a).getItems().get(b));
                orderInfoBody.setParentNumber(orderBaseInfo.getContent().get(a).getId());
                orderInfoList.add(orderInfoBody);
//                orderInfoBody.setParentNumber(a);
            }

            OrderInfo orderInfoBottom = new OrderInfo(3);
            OrderBottomInfo orderBottomInfo = new OrderBottomInfo();
            orderBottomInfo.setAllGoodsNumber(goodsNumber);
            orderBottomInfo.setChildNumbers(orderBaseInfo.getContent().get(a).getItems().size());
            orderBottomInfo.setFreightInfoBean(orderBaseInfo.getContent().get(a).getFreightInfo());
            orderBottomInfo.setMoney(orderBaseInfo.getContent().get(a).getPayment());
            orderBottomInfo.setOrderStatus(orderBaseInfo.getContent().get(a).getStatus());
            orderBottomInfo.setContentBean(orderBaseInfo.getContent().get(a));
            orderInfoBottom.setOrderBottomInfo(orderBottomInfo);
            orderInfoList.add(orderInfoBottom);
        }
        return orderInfoList;
    }


    public List<OrderInfo> getOrderListFragment2(OrderBaseInfo orderBaseInfo) {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (int a = 0; a < orderBaseInfo.getContent().size(); a++) {
            if ("ORDERED".equals(orderBaseInfo.getContent().get(a).getStatus())){
                BaseTool.logPrint(TAG, "getOrderList: ---a2" + a);
                OrderInfo orderInfoTop = new OrderInfo(1);
                OrderTopInfo orderTopInfo = new OrderTopInfo();
                orderTopInfo.setName(orderBaseInfo.getContent().get(a).getSellerInfo().getName());
                orderTopInfo.setShopID(orderBaseInfo.getContent().get(a).getSellerInfo().getId());
                orderInfoTop.setOrderTopInfo(orderTopInfo);
                orderInfoList.add(orderInfoTop);

                int goodsNumber = 0;
                for (int b = 0; b < orderBaseInfo.getContent().get(a).getItems().size(); b++) {
                    BaseTool.logPrint(TAG, "getOrderList: ------b2" + b);
                    goodsNumber = goodsNumber + orderBaseInfo.getContent().get(a).getItems().get(b).getQuantity();
                    OrderInfo orderInfoBody = new OrderInfo(2);
                    orderInfoBody.setOrderBodyInfo(orderBaseInfo.getContent().get(a).getItems().get(b));
                    orderInfoBody.setParentNumber(orderBaseInfo.getContent().get(a).getId());
                    orderInfoList.add(orderInfoBody);
//                    orderInfoBody.setParentNumber(a + "");
                }

                OrderInfo orderInfoBottom = new OrderInfo(3);
                OrderBottomInfo orderBottomInfo = new OrderBottomInfo();
                orderBottomInfo.setAllGoodsNumber(goodsNumber);
                orderBottomInfo.setChildNumbers(orderBaseInfo.getContent().get(a).getItems().size());
                orderBottomInfo.setFreightInfoBean(orderBaseInfo.getContent().get(a).getFreightInfo());
                orderBottomInfo.setMoney(orderBaseInfo.getContent().get(a).getPayment());
                orderBottomInfo.setOrderStatus(orderBaseInfo.getContent().get(a).getStatus());
                orderBottomInfo.setContentBean(orderBaseInfo.getContent().get(a));
                orderInfoBottom.setOrderBottomInfo(orderBottomInfo);
                orderInfoList.add(orderInfoBottom);
            }

        }
        return orderInfoList;
    }


    public List<OrderInfo> getOrderListFragment3(OrderBaseInfo orderBaseInfo) {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (int a = 0; a < orderBaseInfo.getContent().size(); a++) {
            if ("PAID".equals(orderBaseInfo.getContent().get(a).getStatus())){
                BaseTool.logPrint(TAG, "getOrderList: ---a" + a);
                OrderInfo orderInfoTop = new OrderInfo(1);
                OrderTopInfo orderTopInfo = new OrderTopInfo();
                orderTopInfo.setName(orderBaseInfo.getContent().get(a).getSellerInfo().getName());
                orderTopInfo.setShopID(orderBaseInfo.getContent().get(a).getSellerInfo().getId());
                orderInfoTop.setOrderTopInfo(orderTopInfo);
                orderInfoList.add(orderInfoTop);
                int goodsNumber = 0;
                for (int b = 0; b < orderBaseInfo.getContent().get(a).getItems().size(); b++) {
                    BaseTool.logPrint(TAG, "getOrderList: ------b" + b);
                    goodsNumber = goodsNumber + orderBaseInfo.getContent().get(a).getItems().get(b).getQuantity();
                    OrderInfo orderInfoBody = new OrderInfo(2);
                    orderInfoBody.setOrderBodyInfo(orderBaseInfo.getContent().get(a).getItems().get(b));
                    orderInfoBody.setParentNumber(orderBaseInfo.getContent().get(a).getId());
                    orderInfoList.add(orderInfoBody);
//                    orderInfoBody.setParentNumber(a);
                }
                OrderInfo orderInfoBottom = new OrderInfo(3);
                OrderBottomInfo orderBottomInfo = new OrderBottomInfo();
                orderBottomInfo.setAllGoodsNumber(goodsNumber);
                orderBottomInfo.setChildNumbers(orderBaseInfo.getContent().get(a).getItems().size());
                orderBottomInfo.setFreightInfoBean(orderBaseInfo.getContent().get(a).getFreightInfo());
                orderBottomInfo.setMoney(orderBaseInfo.getContent().get(a).getPayment());
                orderBottomInfo.setOrderStatus(orderBaseInfo.getContent().get(a).getStatus());
                orderBottomInfo.setContentBean(orderBaseInfo.getContent().get(a));
                orderInfoBottom.setOrderBottomInfo(orderBottomInfo);
                orderInfoList.add(orderInfoBottom);
            }
        }
        return orderInfoList;
    }

    public List<OrderInfo> getOrderListFragment4(OrderBaseInfo orderBaseInfo) {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (int a = 0; a < orderBaseInfo.getContent().size(); a++) {
            if ("COMPLETED".equals(orderBaseInfo.getContent().get(a).getStatus())) {
                BaseTool.logPrint(TAG, "getOrderList: ---a" + a);
                OrderInfo orderInfoTop = new OrderInfo(1);
                OrderTopInfo orderTopInfo = new OrderTopInfo();
                orderTopInfo.setName(orderBaseInfo.getContent().get(a).getSellerInfo().getName());
                orderTopInfo.setShopID(orderBaseInfo.getContent().get(a).getSellerInfo().getId());
                orderInfoTop.setOrderTopInfo(orderTopInfo);
                orderInfoList.add(orderInfoTop);
                int goodsNumber = 0;
                for (int b = 0; b < orderBaseInfo.getContent().get(a).getItems().size(); b++) {
                    BaseTool.logPrint(TAG, "getOrderList: ------b" + b);
                    goodsNumber = goodsNumber + orderBaseInfo.getContent().get(a).getItems().get(b).getQuantity();
                    OrderInfo orderInfoBody = new OrderInfo(2);
                    orderInfoBody.setOrderBodyInfo(orderBaseInfo.getContent().get(a).getItems().get(b));
                    orderInfoBody.setParentNumber(orderBaseInfo.getContent().get(a).getId());
                    orderInfoList.add(orderInfoBody);
                }
                OrderInfo orderInfoBottom = new OrderInfo(3);
                OrderBottomInfo orderBottomInfo = new OrderBottomInfo();
                orderBottomInfo.setAllGoodsNumber(goodsNumber);
                orderBottomInfo.setChildNumbers(orderBaseInfo.getContent().get(a).getItems().size());
                orderBottomInfo.setFreightInfoBean(orderBaseInfo.getContent().get(a).getFreightInfo());
                orderBottomInfo.setMoney(orderBaseInfo.getContent().get(a).getPayment());
                orderBottomInfo.setOrderStatus(orderBaseInfo.getContent().get(a).getStatus());
                orderInfoBottom.setOrderBottomInfo(orderBottomInfo);
                orderInfoList.add(orderInfoBottom);
            }
        }
        return orderInfoList;
    }
}
