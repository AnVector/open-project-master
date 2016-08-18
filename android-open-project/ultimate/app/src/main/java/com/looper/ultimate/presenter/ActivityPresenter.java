package com.looper.ultimate.presenter;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lemon.android.http.util.LemonDotnetApi;
import com.lemon.android.http.util.LemonDotnetCallback;
import com.lemon.android.http.util.LemonHttpAsyncBean;
import com.looper.ultimate.common.InterfaceType;
import com.looper.ultimate.common.UrlMatch;
import com.looper.ultimate.common.VolleyManager;
import com.looper.ultimate.util.HttpHeaderUtil;
import com.looper.ultimate.util.VolleyRequestWithAuth;
import com.looper.ultimate.view.View;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ActivityPresenter extends PresenterImpl {

    public ActivityPresenter(View view) {
        super(view);
    }

    public void getData(InterfaceType interfaceType){
        mCompositeSubscription.add(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if(isSubscriptionAttached()){
//                    unSubscribe();//线程执行完毕之后，将该事件从线程队列移除
                    mCompositeSubscription.remove(this);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    public void fetchDataByVolley(String url, JSONObject json, final Object tag,final int page, final InterfaceType type) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (getView() != null) {
                    getView().onSuccess(response.toString(), page, type);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getView() != null) {
                    getView().onFailure(error.toString(),page,type);
                }
            }
        });
        jsonObjectRequest.setTag(tag);
        VolleyManager.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void VolleyRequestWithAuth(JSONObject json,final Object tag, final int page,final InterfaceType type) {

        VolleyRequestWithAuth mRequest = new VolleyRequestWithAuth(Request.Method.POST, UrlMatch.getInterfaceUrl(type), json,getContext().getApplicationContext(),new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                if(getView()!=null){
                    getView().onSuccess(response.toString(),page,type);
                }
                Log.d("catalogInfo",""+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(getView()!=null){
                    getView().onFailure(error.toString(),page,type);
                }
                Log.d("catalogInfo",""+error.toString());
            }
        });
        mRequest.setTag(tag);
        VolleyManager.getInstance(getContext()).addToRequestQueue(mRequest);
    }

    public void fetchData(JSONObject json, final int page, final InterfaceType type) {

        LemonHttpAsyncBean asyncBean = new LemonHttpAsyncBean();
        asyncBean.setContext(getContext());
        asyncBean.setUrl(UrlMatch.getInterfaceUrl(type));
        asyncBean.setHeaders(HttpHeaderUtil.getUserLoginHeaders(getContext()));
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        asyncBean.setEntity(entity);
        LemonDotnetApi api = new LemonDotnetApi();
        api.getBeanByPost(asyncBean, new LemonDotnetCallback() {
            @Override
            public void onFetchDataSuccess(String data) {
                if (getView() != null) {
                    getView().onSuccess(data, page, type);
                }
            }

            @Override
            public void onFetchDataError() {
                if (getView() != null) {
                    getView().onFailure("",page,type);
                }
            }
        });
    }
}
