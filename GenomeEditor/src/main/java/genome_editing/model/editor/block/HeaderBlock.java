package genome_editing.model.editor.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.block.DrawElement.StringShape;
import genome_editing.model.elements.Vector2D;

public class HeaderBlock extends PredefinedBlock{
	Block name;
	List<Block>right;
	public HeaderBlock(JSONArray parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void config() {
		// TODO Auto-generated method stub
		right = new ArrayList<>();
	}
	@Override
	protected void setParameter(String id, JSONObject value) {

		switch(id) {
			case "name":
				name = Block.forType(value);
				break;
			case "right":
				right.add(Block.forType(value));
				break;
			default:
				System.err.println("unsupported parameter: "+id);
		}
	}
	@Override
	public void paint(List<Shape> shapes) {
		if(name!=null) {
			name.setBase(this.base);
			name.paint(shapes);
		}
		
		float shiftx = 8f;
		for(Block child:right) {
			child.setBase(new Vector2D(base.x+shiftx, base.y));
			
			child.paint(shapes);
			shiftx+=child.getWidth();
		}
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}



}
