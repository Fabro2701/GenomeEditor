package model.editor.block;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import model.editor.block.DrawElement.Shape;
import model.elements.Vector2D;

public class CodeBlock extends Block{
	public CodeBlock() {
		this(new Color(255,179,0,200));
	}
	public CodeBlock(Color defaultColor) {
		super(defaultColor);
		components = new Block[2][2];
	}
	@Override
	public void paint(List<Shape> shapes) {
		
		
		float inblocksHeight = this.getHeight()-(blockHeadery+blockfloor);

		
		//upper clip
		shapes.add(generateUpperClip());
		
		//header
		shapes.add(generateHeader());

		//left margin
		shapes.add(generateLeftMargin(inblocksHeight));

		//in upper clip
		shapes.add(generateInUpperClip(inblocksHeight));
		
		//in lower clip
		shapes.add(generateInLowerClip(inblocksHeight));
		
		//bottom
		shapes.add(generateBottomFloor(inblocksHeight));
		
		//lower clip
		shapes.add(generateBottomLowerClip(inblocksHeight));

		//name
		shapes.add(generateHeaderName("code"));
		

		float shift = 0f;
		for(int i=components[this.choice].length-1;i>=0;i--) {
			Block b = components[this.choice][i];
			if(b!=null) {
				b.setBase(new Vector2D(base.x+inblockShift, base.y+blockHeadery+inblocksSpace+shift));
				shift += b.getHeight();
				b.paint(shapes);
			}
		}
	}
	
	@Override
	public float getHeight() {
		float inblocksHeight =0f;
		for(int i=0;i<components[this.choice].length;i++) {
			Block b = components[this.choice][i];
			if(b!=null) {
				inblocksHeight += b.getHeight();
			}
		}
		return blockHeadery+(inblocksHeight==0f?emptyShift:inblocksHeight)+blockfloor;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return blockHeaderx;
	}

}
