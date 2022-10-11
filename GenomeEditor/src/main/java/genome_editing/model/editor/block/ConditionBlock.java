package genome_editing.model.editor.block;

import java.awt.Color;
import java.util.List;

import genome_editing.model.editor.block.Block.Alignment;
import genome_editing.model.editor.block.DrawElement.Rectangle;
import genome_editing.model.editor.block.DrawElement.Shape;

public class ConditionBlock extends Block{
	public ConditionBlock() {
		this(new Color(40,179,50,200));
	}
	public ConditionBlock(Color defaultColor) {
		super(defaultColor);
		alignment = Alignment.Horizontal;
		components = new Block[2][2];
	}
	@Override
	public void paint(List<Shape> shapes) {
		shapes.add(new Rectangle(base.x, base.y, smallBlockHeaderx, smallBlockHeadery, defaultColor));

	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
