package genome_editing.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import genome_editing.model.editor.block.Block;
import genome_editing.model.editor.block.CodeBlock;
import genome_editing.model.editor.block.ConditionBlock;
import genome_editing.model.editor.block.DrawElement;
import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.block.IfBlock;
import genome_editing.model.editor.block.IniBlock;
import genome_editing.model.editor.block.LineBlock;
import genome_editing.model.editor.block.ReturnBlock;
import genome_editing.model.editor.block.StringBlock;
import genome_editing.model.elements.Vector2D;
import genome_editing.model.genome.Genotype;
import grammar.AbstractGrammar.Rule;
import grammar.AbstractGrammar.Symbol;
import grammar.Grammar;

public class GenomeEditor extends Editor{
	Genotype geno;
	GenomeVisualizer visualizer;
	BlockManager manager;
	List<Block>blocks;
	public GenomeEditor(Genotype geno, GenomeVisualizer visualizer) {
		super();
		this.geno = geno;
		this.visualizer = visualizer;
		this.init();
	} 

	private void init() {
		try {
			blocks = new ArrayList<Block>();
			manager = new BlockManager(new Vector2D(20f,80f));
			Grammar g = new Grammar();
			g.parseBNF("default");
			//System.out.println(g);
			HashMap<Symbol, Rule> rules = g.getRules();
			for(Symbol s:rules.keySet()) {
				Class<?>clazz = Constants.clazzMap.get(s.getName());
				if(clazz==null) {
					System.err.println(s.getName()+" block not found");
					continue;
				}
				System.out.println(s.getName());
				blocks.add((Block) clazz.getConstructor().newInstance());
				 
			}
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(2, 0, this.getWidth(), this.getHeight());
		
		
		

		IniBlock ini = new IniBlock();
		ini.setChoice(0);
		ConditionBlock cond = new ConditionBlock();
		cond.setChoice(0);
		ConditionBlock cond2 = new ConditionBlock();
		cond.setChoice(0);
		StringBlock str = new StringBlock(g2, "wwaaffffwewerwer");
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
		ini.addComponent(cb,0,0);
		cb.addComponent(lb,0,0);
		lb.addComponent(bi4,1,0);
		//bi.addComponent(bi2, 1, 1);
		//bi2.addComponent(bi3, 1, 1);
		//bi3.addComponent(bi4, 1, 1);
		//bi4.addComponent(rb, 1, 1);
		bi4.addComponent(rb2, 1, 0);
		
		bi.setCondition(str);
		

		cond.addComponent(cond2, 0, 0);
		
		manager.root = bi;
		manager.paint(g2);
		
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
					return o1.priority<o2.priority?-1:o1.priority>o2.priority?1:0;
				}
			});
			shapes.stream().forEach(s->s.draw(g2));
		}
	}
	
}
