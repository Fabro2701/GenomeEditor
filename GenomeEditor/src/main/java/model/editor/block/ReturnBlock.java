package model.editor.block;

import java.awt.Color;
import java.awt.Graphics2D;

import model.elements.Vector2D;

public class ReturnBlock extends Block{

	@Override
	public void paint(Graphics2D g2) {
		
		
		Vector2D leftup = new Vector2D(base.x-xleftupMargin, base.y-yleftupMargin);
		
		float w=100f,h=25f;

		
	
		g2.fillRoundRect((int)leftup.x, (int)leftup.y, (int)w, (int)h, 10, 10);
		
		g2.fillPolygon(new int[] {(int)(leftup.x+clipShift), (int)(leftup.x+clipShift+clipWidth/2), (int)(leftup.x+clipShift+clipWidth)}, 
				       new int[] {(int)(leftup.y), (int)(leftup.y-clipHeight/2), (int)(leftup.y)}, 3);
	}

}
