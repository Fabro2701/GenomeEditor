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
	protected int choice;
	
	
	protected static float clipShift = 15f, clipHeight = 7f, clipWidth = 15f;
	protected static float emptyShift = 16f;
	protected static float inblockShift = 8f, blockHeaderx=120f, blockHeadery=30f;
	protected static float smallBlockHeaderx=120f, smallBlockHeadery=23f;
	protected static float nameShiftx = 5f, nameShifty=5;
	protected static float blockfloor = 15f;
	protected static float inblocksSpace = 0f;
	protected static Font font = new Font("Courier New", 1, 13);
	protected Color defaultColor = new Color(255,179,0,200);
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
	protected Shape generateHeader() {
		return new Rectangle(base.x, base.y, blockHeaderx, blockHeadery, defaultColor);
	}
	protected Shape generateUpperClip() {
		return new Triangle(base.x+clipShift,             base.y, 
							base.x+clipShift+clipWidth/2, base.y+clipHeight, 
							base.x+clipShift+clipWidth,   base.y, 
							white);
	}
	protected Shape generateLowerClip() {
		return new Triangle(base.x+clipShift,             base.y+blockHeadery, 
			    			base.x+clipShift+clipWidth/2, base.y+blockHeadery+clipHeight, 
			    			base.x+clipShift+clipWidth,   base.y+blockHeadery, 
			    			defaultColor);
	}
	protected Shape generateBottomLowerClip(float inblocksHeight) {
		return new Triangle(base.x+clipShift,             base.y+blockHeadery+inblocksHeight+blockfloor, 
			    			base.x+clipShift+clipWidth/2, base.y+blockHeadery+inblocksHeight+blockfloor+clipHeight, 
			    			base.x+clipShift+clipWidth,   base.y+blockHeadery+inblocksHeight+blockfloor, 
			    			defaultColor);
	}
	protected Shape generateLeftMargin(float inblocksHeight) {
		return new Rectangle(base.x, base.y+blockHeadery, inblockShift, inblocksHeight, defaultColor);
	}
	protected Shape generateBottomFloor(float inblocksHeight) {
		return new Rectangle(base.x, base.y+blockHeadery+inblocksHeight, blockHeaderx, blockfloor, defaultColor);
	}
	protected Shape generateInLowerClip(float inblocksHeight) {
		return new Triangle(base.x+inblockShift+clipShift,             base.y+blockHeadery+inblocksHeight, 
			    			base.x+inblockShift+clipShift+clipWidth/2, base.y+blockHeadery+inblocksHeight+clipHeight, 
			    			base.x+inblockShift+clipShift+clipWidth,   base.y+blockHeadery+inblocksHeight, 
			    			white);
	}
	protected Shape generateInUpperClip(float inblocksHeight) {
		return new Triangle(base.x+inblockShift+clipShift,             base.y+blockHeadery, 
			    			base.x+inblockShift+clipShift+clipWidth/2, base.y+blockHeadery+clipHeight, 
			    			base.x+inblockShift+clipShift+clipWidth,   base.y+blockHeadery, 
			    			defaultColor);
	}
	protected Shape generateHeaderName(String name) {
		return new StringShape(name, this.base.x + nameShiftx, this.base.y+nameShifty+blockHeadery/2f, Color.white);
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
}
