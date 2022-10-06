package model.editor.block;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import model.editor.block.DrawElement.Rectangle;
import model.editor.block.DrawElement.Shape;
import model.editor.block.DrawElement.StringShape;
import model.editor.block.DrawElement.Triangle;
import model.elements.Vector2D;

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
