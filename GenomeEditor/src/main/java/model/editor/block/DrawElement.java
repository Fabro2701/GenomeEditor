package model.editor.block;

import java.awt.Color;
import java.awt.Graphics2D;

public class DrawElement {
	public static abstract class Shape{
		protected Color color;
		public float priority;
		public abstract void draw(Graphics2D g2);
		public Shape(Color color) {
			this.color = color;
		}
	}
	public static class Rectangle extends Shape{
		float x, y, width, height;
		Color color;
		public Rectangle(float x, float y, float width, float height, Color color) {
			super(color);
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.color = color;
			priority = 0f;
		}
		@Override
		public void draw(Graphics2D g2) {
			g2.setColor(color);
			g2.fillRect((int)x, (int)y, (int)width, (int)height);
		}
	}
	public static class Triangle extends Shape{
		float x1,x2,x3,y1,y2,y3;
		Color color;
		public Triangle(float x1, float y1, float x2, float y2, float x3, float y3, Color color) {
			super(color);
			this.x1 = x1;
			this.x2 = x2;
			this.x3 = x3;
			this.y1 = y1;
			this.y2 = y2;
			this.y3 = y3;
			this.color = color;
			priority = 1f + (color==Block.white?0f:0.1f);
		}
		@Override
		public void draw(Graphics2D g2) {
			g2.setColor(color);
			g2.fillPolygon(new int[] {(int)x1,(int)x2,(int)x3}, new int[] {(int)y1,(int)y2,(int)y3}, 3);
		}
	}
	public static class StringShape extends Shape{
		String s;
		float x, y;
		Color color;
		public StringShape(String s, float x, float y, Color color) {
			super(color);
			this.x = x;
			this.y = y;
			this.s = s;
			this.color = color;
			priority = 2f;
		}
		@Override
		public void draw(Graphics2D g2) {
			g2.setColor(color);
			g2.drawString(s, x, y);
		}
	}
}
