package me.uplan.stillcoder.library.view;

import me.uplan.stillcoder.library.presenter.IPresenter;

/**
 * @descript:MVP模式中View的基类接口
 * @auther:stillcoder
 * @date:2017/12/12
 * @todoDescript:
 */
public interface IView<P extends IPresenter> {

    /**
     * 设置对应的presenter
     * @param presenter 对象
     */
    void setPresenter(P presenter);
}
