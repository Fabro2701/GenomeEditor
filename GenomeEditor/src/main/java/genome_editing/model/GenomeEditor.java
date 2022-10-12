package genome_editing.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import genome_editing.model.editor.block.Block;
import genome_editing.model.editor.block.BlockCreator;
import genome_editing.model.editor.block.BlockManager;
import genome_editing.model.editor.block.DrawElement;
import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.block.RecursiveBlock;
import genome_editing.model.editor.parsing.BlockParser;
import genome_editing.model.elements.Vector2D;
import genome_editing.model.genome.Genotype;

public class GenomeEditor extends Editor{
	Genotype geno;
	GenomeVisualizer visualizer;
	BlockManager manager;
	public GenomeEditor(Genotype geno, GenomeVisualizer visualizer) {
		super();
		this.geno = geno;
		this.visualizer = visualizer;
		this.init();
	} 

	private void init() {
		manager = new BlockManager(new Vector2D(20f,80f));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(2, 0, this.getWidth(), this.getHeight());
		
		
		
		RecursiveBlock block1 = new RecursiveBlock("CODE");

		
		manager.setRoot(block1);
		manager.paint(g2);
		
		//blockmanager(list) stores all block (includinnf ghost blocks)
		//when click is performed the(x,y) search is delegated to blockmanager
		//who iterates its blocks until finds the corresponded

	}
	
	
}
