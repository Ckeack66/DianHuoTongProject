package com.mhky.yaolinwang.order.presenter;

import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.DataModel;
import com.mhky.dianhuotong.shop.bean.OrderBottomInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.bean.OrderTopInfo;
import com.mhky.yaolinwang.order.bean.CustomerOrder;
import com.mhky.yaolinwang.order.bean.CustomerOrderBean;
import com.mhky.yaolinwang.order.bean.CustomerOrderBottomInfo;
import com.mhky.yaolinwang.order.bean.CustomerOrderTopInfo;
import com.mhky.yaolinwang.order.model.CustomerOrdersModel;
import com.mhky.yaolinwang.order.view.CustomerOrdersView;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator  on  2018/10/16
 * Describe：
 */
public class CustomerOrdersPresenter extends BasePresenter<CustomerOrdersView> {
    /**
     * 请求 C 端全部订单列表
     * @param httpParams
     */
    public void getCustomerOrders(HttpParams httpParams){
        try {
            if(!isViewAttached()){
                return;
            }
            DataModel.request(CustomerOrdersModel.class).params(httpParams)
                    .execute(new BaseCallBack<String>() {
                        @Override
                        public void onSuccess(String data) {
                            getView().getCustomerOrdersSuccess(data);
                        }

                        @Override
                        public void onFailure(String msg) {
                            getView().getCustomerOrdersFailed(msg);
                        }

                        @Override
                        public void onError() {

                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(BaseApplication.getContext(),e);
        }
    }


    /**
     * 获取订单List列表
     * @param customerOrderBean
     * @return
     */
    public List<CustomerOrder> getCustomerOrderList(CustomerOrderBean customerOrderBean){
        List<CustomerOrder> orderList = new ArrayList<>();
        for (int a = 0; a < customerOrderBean.getContent().size(); a++) {
            /*   头部   */
            CustomerOrder customerOrder_top = new CustomerOrder(1);
            CustomerOrderTopInfo customerOrderTopInfo = new CustomerOrderTopInfo();
            customerOrderTopInfo.setName(customerOrderBean.getContent().get(a).getSellerInfo().getName());
            customerOrderTopInfo.setShopId(customerOrderBean.getContent().get(a).getSellerInfo().getId());
            customerOrderTopInfo.setState(customerOrderBean.getContent().get(a).getStatus());
            customerOrder_top.setOrderId(customerOrderBean.getContent().get(a).getId());
            customerOrder_top.setCustomerOrderTopInfo(customerOrderTopInfo);
            orderList.add(customerOrder_top);
            /*   商品（body）  */
            int goodsNumber = 0;//订单商品数量
            for (int b = 0; b < customerOrderBean.getContent().get(a).getOrderDetailSet().size(); b++) {
                goodsNumber = goodsNumber + customerOrderBean.getContent().get(a).getOrderDetailSet().get(b).getProductQuantity();
                CustomerOrder customerOrder_body = new CustomerOrder(2);
                customerOrder_body.setOrderDetailSetBean(customerOrderBean.getContent().get(a).getOrderDetailSet().get(b));
                customerOrder_body.setOrderId(customerOrderBean.getContent().get(a).getId());
                orderList.add(customerOrder_body);
            }
            /*   底部   */
            CustomerOrder customerOrder_bottom = new CustomerOrder(3);
            CustomerOrderBottomInfo customerOrderBottomInfo = new CustomerOrderBottomInfo();
            customerOrderBottomInfo.setContentBean(customerOrderBean.getContent().get(a));
            customerOrderBottomInfo.setGoodsNum(goodsNumber);
            customerOrderBottomInfo.setGoodsMoney(customerOrderBean.getContent().get(a).getOrderPayment());
            customerOrderBottomInfo.setOrderState(customerOrderBean.getContent().get(a).getStatus());
            customerOrder_bottom.setCustomerOrderBottomInfo(customerOrderBottomInfo);
            orderList.add(customerOrder_bottom);
        }
        return orderList;
    }
}
