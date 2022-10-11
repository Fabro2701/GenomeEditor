package genome_editing.model.editor.block;

import java.awt.Color;
import java.awt.Font;

import genome_editing.model.editor.block.DrawElement.Rectangle;
import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.block.DrawElement.StringShape;
import genome_editing.model.editor.block.DrawElement.Triangle;
import genome_editing.model.elements.Vector2D;

public abstract class Block implements BlockRenderer{
	protected Block[][] components;
	protected Vector2D base;
	protected int choice;
	static Alignment alignment;
	
	
	protected static float clipShift = 15f, clipHeight = 7f, clipWidth = 15f;
	protected static float emptyShift = 16f;
	protected static float inblockShift = 8f, blockHeaderx=120f, blockHeadery=30f;
	protected static float smallBlockHeaderx=120f, smallBlockHeadery=23f;
	protected static float nameShiftx = 5f, nameShifty=5;
	protected static float blockfloor = 15f;
	protected static float inblocksSpace = 0f;
	protected static Font font = new Font("Courier New", 1, 13);
	protected Color defaultColor = new Color(255,179,0,200);
	public static Color white = new Color(255,255,255,255);
	
	public Block() {
	}
	public Block(Color defaultColor) {
		this();
		this.defaultColor = defaultColor;
	}
	public enum Alignment{
		Vertical, Horizontal
	}
	public Vector2D getBase() {
		return base;
	}
	public void setBase(Vector2D base) {
		this.base = base;
	}
	public void addComponent(Block block, int production, int index) {
		this.components[production][index] = block;
	}
	protected Shape generateHeader() {
		return new Rectangle(base.x, base.y, blockHeaderx, blockHeadery, defaultColor);
	}
	protected Shape generateUpperClip() {
		return new Triangle(base.x+clipShift,             base.y, 
							base.x+clipShift+clipWidth/2, base.y+clipHeight, 
							base.x+clipShift+clipWidth,   base.y, 
							white);
	}
	protected Shape generateLowerClip() {
		return new Triangle(base.x+clipShift,             base.y+blockHeadery, 
			    			base.x+clipShift+clipWidth/2, base.y+blockHeadery+clipHeight, 
			    			base.x+clipShift+clipWidth,   base.y+blockHeadery, 
			    			defaultColor);
	}
	protected Shape generateBottomLowerClip(float inblocksHeight) {
		return new Triangle(base.x+clipShift,             base.y+blockHeadery+inblocksHeight+blockfloor, 
			    			base.x+clipShift+clipWidth/2, base.y+blockHeadery+inblocksHeight+blockfloor+clipHeight, 
			    			base.x+clipShift+clipWidth,   base.y+blockHeadery+inblocksHeight+blockfloor, 
			    			defaultColor);
	}
	protected Shape generateLeftMargin(float inblocksHeight) {
		return new Rectangle(base.x, base.y+blockHeadery, inblockShift, inblocksHeight, defaultColor);
	}
	protected Shape generateBottomFloor(float inblocksHeight) {
		return new Rectangle(base.x, base.y+blockHeadery+inblocksHeight, blockHeaderx, blockfloor, defaultColor);
	}
	protected Shape generateInLowerClip(float inblocksHeight) {
		return new Triangle(base.x+inblockShift+clipShift,             base.y+blockHeadery+inblocksHeight, 
			    			base.x+inblockShift+clipShift+clipWidth/2, base.y+blockHeadery+inblocksHeight+clipHeight, 
			    			base.x+inblockShift+clipShift+clipWidth,   base.y+blockHeadery+inblocksHeight, 
			    			white);
	}
	protected Shape generateInUpperClip(float inblocksHeight) {
		return new Triangle(base.x+inblockShift+clipShift,             base.y+blockHeadery, 
			    			base.x+inblockShift+clipShift+clipWidth/2, base.y+blockHeadery+clipHeight, 
			    			base.x+inblockShift+clipShift+clipWidth,   base.y+blockHeadery, 
			    			defaultColor);
	}
	protected Shape generateHeaderName(String name) {
		return new StringShape(name, this.base.x + nameShiftx, this.base.y+nameShifty+blockHeadery/2f, Color.white);
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
}
