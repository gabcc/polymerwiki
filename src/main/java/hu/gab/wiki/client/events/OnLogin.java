package hu.gab.wiki.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author PG
 * @since 2016-05-15
 */
public class OnLogin extends GwtEvent<OnLogin.OnLoginHandler> {
    public static Type<OnLoginHandler> TYPE = new Type<OnLoginHandler>();

    public Type<OnLoginHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(OnLoginHandler handler) {
        handler.onOnLogin(this);
    }

    public interface OnLoginHandler extends EventHandler {
        void onOnLogin(OnLogin event);
    }
}
