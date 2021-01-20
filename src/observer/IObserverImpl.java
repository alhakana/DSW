package observer;

import java.util.ArrayList;
import java.util.List;

import notification.Notification;

/**
 * VAZNO: Oni koji nasledjuju ovu klasu ako postoji potreba da naslede jos neku klasu sem ovo
 * 		  moguce je da jednostavno umesto nasledjivanja ove klase implementiraju interfejs IObserver
 * 		  i redefinisu ove tri metode
 * */
public class IObserverImpl implements IObserver {
	
	private List<IListener> listeners;
	@Override
	public void addObserver(IListener listener) {
		if(listener == null)
			return;
		if(listeners == null)
			listeners= new ArrayList<IListener>();
		if(!listeners.contains(listener))
			listeners.add(listener);
		 return;
		 
	}
	
	public List<IListener> getListeners() {
		return listeners;
	}
//	@Override
//	public void removeObserver(IListener listener) {
//		if(listener == null || listeners == null || !listeners.contains(listener))
//			return;
//		listeners.remove(listener);
//	}

	@Override
	public void notifyObservers(Notification notification) {
		if(listeners == null || listeners.isEmpty() || notification == null)
			return;
		
		for(IListener lis : listeners)
			lis.update(notification);
		
		return;
	}

	@Override
	public int countObservers() {
		if(listeners == null || listeners.size() < 1)
			return 0;
		return listeners.size();
	}

}
