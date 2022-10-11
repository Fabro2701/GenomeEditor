package genome_editing.model.editor.block;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import genome_editing.model.editor.block.DrawElement.Rectangle;
import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.block.DrawElement.StringShape;
import genome_editing.model.editor.block.DrawElement.Triangle;
import genome_editing.model.elements.Vector2D;

public class ReturnBlock extends Block{
	@Override
	public void paint(List<Shape> shapes) {
		
	
		//upper clip
		shapes.add(generateUpperClip());
		
		//header
		shapes.add(generateHeader());
		
		//lower clip
		shapes.add(generateLowerClip());
		
		//name
		shapes.add(generateHeaderName("return"));
	}

	@Override
	public float getHeight() {
		return blockHeadery+inblocksSpace;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return blockHeaderx;
	}

}
