package hu.gab.wiki.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.place.shared.Place;

/**
 * @author PG
 * @since 2016-04-30
 */
public class BeforeActivityChanged extends GwtEvent<BeforeActivityChanged.BeforeActivityChangedEventHandler> {
    public static Type<BeforeActivityChangedEventHandler> TYPE = new Type<BeforeActivityChangedEventHandler>();

    public Type<BeforeActivityChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(BeforeActivityChangedEventHandler handler) {
        handler.onMenuChanged(this);
    }

    public interface BeforeActivityChangedEventHandler extends EventHandler {
        void onMenuChanged(BeforeActivityChanged event);
    }

    private Place newPlace;

    public BeforeActivityChanged(Place newPlace) {
        this.newPlace = newPlace;
    }

    public Place getNewPlace() {
        return newPlace;
    }
}
