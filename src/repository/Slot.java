package repository;

import java.io.File;

import notification.Notification;
import notification.NotificationCode;
import notification.ShapeType;
import repository.node.RuNode;

@SuppressWarnings("serial")
public class Slot extends RuNode {
	protected double x, y, xCopy, yCopy;
	protected double w, h, wCopy, hCopy;
	protected double coef, angle = 0.0;
	protected ShapeType type;
	protected ShapeType sadrzaj;	//da li je textualni ili je graficki
	protected RuNode parent;
	protected File file;
	
	public Slot(String name, RuNode parent) {
		super(name, parent);
	}
	
	public Slot() {}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}
	
	@Override
	public void deleteMe() {
		sendMessage(new Notification(NotificationCode.DELETE_ME, null));
	}
	
	/**
	 * Koristi se kad kreiramo slot pomocu praznog konstruktora i onda hocemo da mu namestimo x i y kordinate
	 * kao i kordinate centra tog oblika
	 * @param x
	 * @param y
	 */
	public void coordinates(int x, int y) {
		this.x = x;
		this.y = y;
		xCopy = x;
		yCopy = y;
		wCopy = w;
		hCopy = h;
	}

	public void changePosition(double newX, double newY) {
		x = newX;
		y = newY;
		
		sendMessage(new Notification(NotificationCode.RESET, null));
	}

	public void changeOriginalPosition() {
		xCopy = x;
		yCopy = y;
	}

	public void setCoef(double coef) {
		if (w*coef < 50)
			return;
		
		this.coef = coef;
		wCopy = w*coef;
		hCopy = h*coef;

		
		sendMessage(new Notification(NotificationCode.RESET, null));
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setSize() {
		w = wCopy;
		h = hCopy;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		sendMessage(new Notification(NotificationCode.RESET, null));
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public ShapeType getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(ShapeType sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public double getXCopy() {
		return xCopy;
	}

	public double getYCopy() {
		return yCopy;
	}
	
	public double getW() {
		return w;
	}

	public double getH() {
		return h;
	}

	public ShapeType getType() {
		return type;
	}

	public RuNode getParent() {
		return parent;
	}

	public void setParent(RuNode parent) {
		this.parent = parent;
	}

	public double getHCopy() {
		return hCopy;
	}
	
	public double getWCopy() {
		return wCopy;
	}
	
	public double getAngle() {
		return angle;
	}

}
