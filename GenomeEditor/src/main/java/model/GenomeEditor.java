package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		g2.setColor(Color.red);
		
		ReturnBlock b = new ReturnBlock();
		b.setBase(new Vector2D(20f,80f));
		b.paint(g2);
	}
	
}
