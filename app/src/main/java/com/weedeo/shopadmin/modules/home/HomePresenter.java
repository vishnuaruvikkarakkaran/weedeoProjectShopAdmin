package com.weedeo.shopadmin.modules.home;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.MvpView mvpView;

    public HomePresenter(HomeContract.MvpView view) {
        this.mvpView = view;
    }

}
