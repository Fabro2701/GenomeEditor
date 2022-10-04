package model.editor.block;

import model.elements.Vector2D;
import model.elements.Vector3D;

public abstract class Block implements BlockRenderer{

	protected Vector2D base;
	protected static float xleftupMargin = 4f, yleftupMargin = 4f;
	protected static float clipShift = 12f, clipHeight = 15f, clipWidth = 10f;
	
	public Vector2D getBase() {
		return base;
	}

	public void setBase(Vector2D base) {
		this.base = base;
	}
}
