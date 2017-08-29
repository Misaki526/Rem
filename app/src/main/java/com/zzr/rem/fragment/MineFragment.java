package com.zzr.rem.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zzr.rem.R;
import com.zzr.rem.RemApplication;
import com.zzr.rem.activity.LoginActivity;
import com.zzr.rem.bean.RLoginResult;
import com.zzr.rem.cons.IdiyMessage;
import com.zzr.rem.controller.UserController;
import com.zzr.rem.util.ActivityUtil;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Created by Misaki on 2017/8/2.
 */

public class MineFragment extends BaseFragment implements OnClickListener {

    private TextView mUserNameTv;
    private TextView mUserLevelTv;
    private TextView mWaitPayTv;
    private TextView mWaitReceiveTv;

    @Override
    protected void handlerMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.CLEAR_USER_ACTION_RESULT:
                ActivityUtil.start(getActivity(), LoginActivity.class, true);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initController();
        initUI();
    }

    @Override
    protected void initUI() {
        getActivity().findViewById(R.id.logout_btn).setOnClickListener(this);

        // 1. 获取控件
        mUserNameTv = (TextView) getActivity().findViewById(R.id.user_name_tv);
        mUserLevelTv = (TextView) getActivity().findViewById(R.id.user_level_tv);
        mWaitPayTv = (TextView) getActivity().findViewById(R.id.wait_pay_tv);
        mWaitReceiveTv = (TextView) getActivity().findViewById(R.id.wait_receive_tv);

        // 2. 后台请求，更新控件
        RemApplication application = (RemApplication) getActivity().getApplication();
        RLoginResult mRLoginResult = application.mRLoginResult;

        mUserNameTv.setText(mRLoginResult.getUserName());
        initUserLevel(mRLoginResult);
        mWaitPayTv.setText(mRLoginResult.getWaitPayCount() + "");
        mWaitReceiveTv.setText(mRLoginResult.getWaitReceiveCount() + "");
    }

    private void initUserLevel(RLoginResult mRLoginResult) {
        String text = "";
        switch (mRLoginResult.getUserLevel()) {
            case 1:
                text = "注册会员";
                break;
            case 2:
                text = "铜牌会员";
                break;
            case 3:
                text = "银牌会员";
                break;
            case 4:
                text = "金牌会员";
                break;
            case 5:
                text = "钻石会员";
                break;
        }
        mUserLevelTv.setText(text);
    }

    @Override
    protected void initController() {
        mController = new UserController(getActivity());
        mController.setIModeChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_btn:
                mController.sendAsyncMessage(IdiyMessage.CLEAR_USER_ACTION, 0);
                break;
        }
    }

}
