package com.example.instagram.camera

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView

interface Camera {
    interface Presenter:BasePresenter{

    }
    interface View:BaseView<Presenter>
}