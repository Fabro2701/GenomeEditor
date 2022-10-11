package genome_editing.model.editor.block;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import genome_editing.model.editor.block.Block.Alignment;
import genome_editing.model.editor.block.DrawElement.Rectangle;
import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.block.DrawElement.StringShape;

public class StringBlock extends Block{
	String value;
	int w;
	public StringBlock(Graphics2D g2, String value) {
		this(new Color(40,179,50,200));
		this.value = value;
		w = g2.getFontMetrics().stringWidth(value);
	}
	public StringBlock(Color defaultColor) {
		super(defaultColor);
		alignment = Alignment.Horizontal;
		components = new Block[2][2];
	}
	@Override
	public void paint(List<Shape> shapes) {
		float width = this.getWidth();
		shapes.add(new Rectangle(base.x, base.y, width, smallBlockHeadery, defaultColor));
		shapes.add(new StringShape(value, this.base.x + nameShiftx, this.base.y+nameShifty+smallBlockHeadery/2f, Color.white));

	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return smallBlockHeadery;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return w+nameShiftx+3f;
	}

}
