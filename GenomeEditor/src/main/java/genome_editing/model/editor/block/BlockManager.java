package genome_editing.model.editor.block;

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
	static List<Integer>decisions = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	static int cursor=0;
	static HashMap<String, JSONArray>blockDescs;
	static Graphics2D g2;
	
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
	public void setRoot(Block root) {
		this.root = root;
	}
}
