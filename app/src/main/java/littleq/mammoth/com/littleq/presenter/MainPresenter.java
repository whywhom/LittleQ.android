package littleq.mammoth.com.littleq.presenter;

import littleq.mammoth.com.littleq.view.IMainView;

/**
 * Created by Hunter on 2016/9/18.
 */
public class MainPresenter {
    IMainView mainView;

    public MainPresenter(IMainView view){
        mainView = view;
    }
    public void initToolbar(int index){
        mainView.setToolbar(index);
        mainView.setCurrentItem(index);
        mainView.setCurrentTitle(index);
    }

    public void initToolbarListener(int i) {
        mainView.setToolbar(i);
        mainView.setCurrentItem(i);
        mainView.setCurrentTitle(i);
    }

    public void onPageSelected(int position) {
        mainView.setToolbar(position);
        mainView.setCurrentTitle(position);
    }
}
