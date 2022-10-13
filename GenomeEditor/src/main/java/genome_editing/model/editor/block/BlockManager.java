package genome_editing.model.editor.block;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.parsing.BlockParser;
import genome_editing.model.elements.Vector2D;

public class BlockManager{
	Block root;
	Vector2D base;
	List<DrawElement.Shape>shapes;
	static List<Integer>decisions = List.of(1, 1, 5, 0, 0, 1, 0, 5, 0, 3, 0, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	static int cursor=0;
	static HashMap<String, JSONArray>blockDescs;
	static Graphics2D g2;
	
	static HashMap<String, Color>blockColors;
	static {
		blockColors = new HashMap<String, Color>();
		blockColors.put("CODE", new Color(255,0,0,100));
		blockColors.put("LINE", new Color(0,255,0,100));
		blockColors.put("IF", new Color(0,0,255,100));
		blockColors.put("COND", new Color(255,255,0,100));
		blockColors.put("OBS", new Color(255,0,255,100));
	}
	
	public BlockManager(Vector2D base) {
		this.base = base;
		BlockParser parser = new BlockParser();
		JSONObject program = parser.parseFile("test");

		blockDescs = BlockCreator.loadBlocks(program);
		
	}
	public void paint(Graphics2D g) {
		g2 = g;
		shapes = new ArrayList<DrawElement.Shape>();
		cursor=0;
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
	public static int getNext() {
		return decisions.get(cursor++);
	}
	public void setRoot(Block root) {
		this.root = root;
	}
}
