package com.techja.myapplication.view.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techja.myapplication.App;
import com.techja.myapplication.R;
import com.techja.myapplication.callback.OnM002ProfileCallBackToView;
import com.techja.myapplication.model.CompanyEntity;
import com.techja.myapplication.presenter.M002ProfileCompanyPresenter;
import com.techja.myapplication.view.base.BaseFragment;
import com.techja.myapplication.view.dialog.M002ChangeCodeDialog;
import com.techja.myapplication.view.event.OnM002ProifleCompanyCallBack;

import java.util.List;

public class M002ProfileCompanyFrg extends BaseFragment<M002ProfileCompanyPresenter, OnM002ProifleCompanyCallBack> implements OnM002ProfileCallBackToView {
    public static final String TAG = M002ProfileCompanyFrg.class.getName();
    private Button btEdit;
    private TextView tvCode, tvLat, tvLong, tvRange;
    private String code, lat, longt, range;

    @Override
    protected M002ProfileCompanyPresenter getPresenter() {
        return new M002ProfileCompanyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.m002_frg_profile_company;
    }

    @Override
    protected void initViews() {
        btEdit = findViewById(R.id.bt_edit_002, this);
        tvCode = findViewById(R.id.tv_code_002, App.getInstance().getRegularFont());
        tvLat = findViewById(R.id.tv_lat, App.getInstance().getRegularFont());
        tvLong = findViewById(R.id.tv_long, App.getInstance().getRegularFont());
        tvRange = findViewById(R.id.tv_range, App.getInstance().getRegularFont());
        mPresenter.getInfoCode(new String[]{"codeDD", "latitude", "longitude", "maxdistance"});


    }

    @Override
    public void showPreviousFrg() {
        mCallBack.showFragment(M003MenuFrg.TAG);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_edit_002) {
            CompanyEntity entity = new CompanyEntity(code, lat, longt, range);
            getStorage().setCompanyEntity(entity);
            M002ChangeCodeDialog dialog = new M002ChangeCodeDialog(mContext);
            dialog.show();
        }
    }

    @Override
    public void sendInfoCode(List<String> listData) {
        if (listData.size() < 0) {
            return;
        }
        this.code = listData.get(0);
        this.lat = listData.get(1);
        this.longt = listData.get(2);
        this.range = listData.get(3);
        tvCode.setText(listData.get(0));
        tvLat.setText(listData.get(1));
        tvLong.setText(listData.get(2));
        tvRange.setText(listData.get(3));
    }
}
