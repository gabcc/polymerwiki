package hu.gab.wiki.client.helper;

import com.google.gwt.user.client.rpc.AsyncCallback;
import hu.gab.wiki.client.AppUtils;

/**
 * @author PG
 * @since 2016-05-15
 */
abstract public class CommonWikiAsyncHandler<T> implements AsyncCallback<T> {
    @Override
    public void onFailure(Throwable caught) {
        AppUtils.hideLoadingSpinner();
        AppUtils.showToast(0, caught.getMessage(), true);
    }
}
