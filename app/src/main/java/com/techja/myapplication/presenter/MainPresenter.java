package com.techja.myapplication.presenter;

import com.techja.myapplication.callback.OnMainCallBackToView;

public class MainPresenter extends BasePresenter<OnMainCallBackToView> {

    public MainPresenter(OnMainCallBackToView event) {
        super(event);
    }
}
