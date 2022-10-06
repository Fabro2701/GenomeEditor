package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.editor.block.Block;
import model.editor.block.CodeBlock;
import model.editor.block.DrawElement;
import model.editor.block.DrawElement.Shape;
import model.editor.block.IfBlock;
import model.editor.block.LineBlock;
import model.editor.block.ReturnBlock;
import model.elements.Vector2D;
import model.genome.Genotype;

public class GenomeEditor extends Editor{
	Genotype geno;
	GenomeVisualizer visualizer;
	
	public GenomeEditor(Genotype geno, GenomeVisualizer visualizer) {
		super();
		this.geno = geno;
		this.visualizer = visualizer;
		this.init();
	}

	private void init() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(2, 0, this.getWidth(), this.getHeight());
		
		BlockManager manager = new BlockManager(new Vector2D(20f,80f));
		
		CodeBlock cb = new CodeBlock();
		cb.setChoice(0);
		CodeBlock cb2 = new CodeBlock();
		LineBlock lb = new LineBlock();
		lb.setChoice(1);
		ReturnBlock rb = new ReturnBlock();
		ReturnBlock rb2 = new ReturnBlock();
		IfBlock bi = new IfBlock();
		IfBlock bi2 = new IfBlock(new Color(255,0,0,150));
		IfBlock bi3 = new IfBlock(new Color(0,255,0,150));
		bi3.setChoice(1);
		IfBlock bi4 = new IfBlock(new Color(0,0,255,150));
		bi4.setChoice(1);
		cb.addComponent(lb,0,0);
		lb.addComponent(bi4,1,0);
		//bi.addComponent(bi2, 1, 1);
		//bi2.addComponent(bi3, 1, 1);
		//bi3.addComponent(bi4, 1, 1);
		//bi4.addComponent(rb, 1, 1);
		bi4.addComponent(rb2, 1, 0);
		
		manager.root = cb;
		manager.paint(g2);
//		ReturnBlock rb = new ReturnBlock();
//		rb.setBase(new Vector2D(20f,80f));
//		rb.paint(g2);
//		ReturnBlock rb2 = new ReturnBlock();
//		rb2.setBase(new Vector2D(20f,80f+rb.getHeight()+2f));
//		rb2.paint(g2);

		
		/*
		 * Use a draw list to avoid overlapping
		 */
		
		//blockmanager(list) stores all block (includinnf ghost blocks)
		//when click is performed the(x,y) search is delegated to blockmanager
		//who iterates its blocks until finds the corresponded
		
		
		
		
	}
	public class BlockManager{
		Block root;
		Vector2D base;
		List<DrawElement.Shape>shapes;
		public BlockManager(Vector2D base) {
			this.base = base;
			shapes = new ArrayList<DrawElement.Shape>();
		}
		public void paint(Graphics2D g2) {
			root.setBase(base);
			root.paint(shapes);
			shapes.sort(new Comparator<Shape>(){
				@Override
				public int compare(Shape o1, Shape o2) {
					// TODO Auto-generated method stub
					return o1.priority<o2.priority?-1:o1.priority>o2.priority?1:0;
				}
				
			});
			shapes.stream().forEach(s->s.draw(g2));
		}
	}
	
}
