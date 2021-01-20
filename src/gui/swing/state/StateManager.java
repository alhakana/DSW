package gui.swing.state;

import gui.swing.node.view.PageView;

public class StateManager {
	private State currectState;
	private PageView mediator;
	
	//oblici
	private RectangleState rectangleState;
	private TriangleState triangleState;
	private CircleState circleState;
	
	//operacije nad oblicima
	private SelectState selectState;
	private MoveState moveState;
	private ResizeState resizeState;
	private RotateState rotateState;
	private DeleteState deleteState;
	
	public StateManager(PageView mediator) {
		this.mediator = mediator;
		loadAll();
	}
	
	private void loadAll() {
		rectangleState = new RectangleState(mediator);
		triangleState = new TriangleState(mediator);
		circleState = new CircleState(mediator);
		selectState = new SelectState(mediator);
		moveState = new MoveState(mediator);
		resizeState = new ResizeState(mediator);
		rotateState = new RotateState(mediator);
		deleteState = new DeleteState(mediator);
	}

	public State getCurrectState() {
		return currectState;
	}

	public void setRectangleState() {
		currectState = rectangleState;
	}
	
	public void setTriangleState() {
		currectState = triangleState;
	}
	
	public void setCircleState() {
		currectState = circleState;
	}

	public void setSelectState() {
		currectState = selectState;
	}
	
	public void setMoveState() {
		currectState = moveState;
	}

	public void setResizeState() {
		currectState = resizeState;
	}
	
	public void setRotateState() {
		currectState = rotateState;
	}
	
	public void setDeleteState() {
		currectState = deleteState;
	}
}
