package hu.gab.wiki.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author PG
 * @since 2016-05-15
 */
public class OnLogout extends GwtEvent<OnLogout.OnLogoutHandler> {
    public static Type<OnLogoutHandler> TYPE = new Type<OnLogoutHandler>();

    public Type<OnLogoutHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(OnLogoutHandler handler) {
        handler.onOnLogout(this);
    }

    public interface OnLogoutHandler extends EventHandler {
        void onOnLogout(OnLogout event);
    }
}
