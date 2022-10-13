package genome_editing.model.editor.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.elements.Vector2D;

public class RecursiveBlock extends Block{
	String rule;
	List<Block>blocks;
	Color color;
	
	public RecursiveBlock(String ruleReference) {
		this.rule = ruleReference;
		blocks = new ArrayList<Block>();
		color = BlockManager.blockColors.get(ruleReference);
	}
	@Override
	public void paint(List<Shape> shapes) {
		blocks.clear();
		int elec = BlockManager.getNext();
		JSONArray r = BlockManager.blockDescs.get(rule);
		elec %= r.length();
		JSONArray production = BlockManager.blockDescs.get(rule).getJSONArray(elec);
		float shifty = 0f;
		for(int i=0;i<production.length();i++) {
			JSONObject bo = production.getJSONObject(i);
			Block b = Block.forType(bo);
			if(bo.getString("type").equals("PredefinedBlock")) {
				((PredefinedBlock)b).setColor(color);
			}
			
			b.setBase(new Vector2D(base.x, base.y+shifty));
			blocks.add(b);
			b.paint(shapes);
			shifty += b.getHeight();
		}
	}
	@Override
	public float getHeight() {
		float sum = 0f;
		for(Block b:blocks) {
			sum += b.getHeight();
		}
		return sum;
	}
	@Override
	public float getWidth() {
		float sum = 0f;
		for(Block b:blocks) {
			sum += b.getWidth();
		}
		return sum;
	}

}
