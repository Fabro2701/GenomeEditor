package genome_editing.model.editor.block;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import genome_editing.model.editor.block.DrawElement.Rectangle;
import genome_editing.model.editor.block.DrawElement.Shape;
import genome_editing.model.editor.block.DrawElement.StringShape;
import genome_editing.model.editor.block.DrawElement.Triangle;
import genome_editing.model.elements.Vector2D;

public abstract class  Block implements BlockRenderer{
	protected List<ArrayList<Block>> components;
	protected Vector2D base;
	
	protected static float inblockShift = 8f;
	protected static float stringHeight = 15f;
	protected static float floorblockShift = 4f;
	
	protected static Font font = new Font("Courier New", 1, 13);
	protected Color defaultColor = new Color(255,179,0,150);
	public static Color white = new Color(255,255,255,255);
	
	public Block() {
	}
	public Block(JSONArray elements) {
		components = new ArrayList<ArrayList<Block>>();
		for(int i=0;i<elements.length();i++) {
			JSONArray production = elements.getJSONArray(i);
			ArrayList<Block> arr = new ArrayList<Block>();
			for(int j=0;j<production.length();j++) {
				arr.add(Block.forType(production.getJSONObject(j)));
			}
			components.add(arr);
		}
	}
	protected static Block forType(JSONObject source) {
		String type = source.getString("type");
		try {
			if(type.equals("PredefinedBlock")) {
				String name = source.getString("id")+"Block";
				Class<?>clazz = Class.forName("genome_editing.model.editor.block."+name);
				JSONArray parameters = source.getJSONArray("params");
				//PredefinedBlock block = (PredefinedBlock)clazz.getConstructor(JSONArray.class).newInstance(parameters);
				PredefinedBlock block = (PredefinedBlock)clazz.getConstructors()[0].newInstance(parameters);

				return block;
			}
			else if(type.equals("RecursiveBlock")) {
				return new RecursiveBlock(source.getString("id"));
			}
			else {
				System.err.println("unknown block type");
				return null;
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(((InvocationTargetException)e).getCause());
			e.printStackTrace();
		}
		return null;
	}
	public Block(Color defaultColor) {
		this();
		this.defaultColor = defaultColor;
	}
	public Vector2D getBase() {
		return base;
	}
	public void setBase(Vector2D base) {
		this.base = base;
	}
	
}
