package genome_editing.model.editor.block;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import genome_editing.model.editor.block.DrawElement.Shape;

public class RecursiveBlock extends Block{
	String rule;
	List<Block>blocks;
	public RecursiveBlock(String ruleReference) {
		this.rule = ruleReference;
		blocks = new ArrayList<Block>();
	}
	@Override
	public void paint(List<Shape> shapes) {
		blocks.clear();
		JSONArray production = BlockManager.blockDescs.get(rule).getJSONArray(BlockManager.decisions.get(BlockManager.cursor++));
		for(int i=0;i<production.length();i++) {
			JSONObject bo = production.getJSONObject(i);
			Block b = Block.forType(bo);
			b.setBase(base);
			blocks.add(b);
			b.paint(shapes);
		}
	}
	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
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
