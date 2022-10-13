package genome_editing.model.editor.block;

import java.awt.Color;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class PredefinedBlock extends Block{
	Color color;
	public PredefinedBlock(JSONArray parameters) {
		config();
		for(int i=0;i<parameters.length();i++) {
			JSONObject param = parameters.getJSONObject(i);
			this.setParameter(param.getString("name"),param.getJSONObject("value"));
		}
	}
	protected void setColor(Color color) {
		this.color = color;
	}
	protected abstract void config();
	protected abstract void setParameter(String id, JSONObject value);
}
