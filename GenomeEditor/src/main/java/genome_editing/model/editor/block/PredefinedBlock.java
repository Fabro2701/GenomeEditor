package genome_editing.model.editor.block;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class PredefinedBlock extends Block{
	public PredefinedBlock(JSONArray parameters) {
		config();
		for(int i=0;i<parameters.length();i++) {
			JSONObject param = parameters.getJSONObject(i);
			this.setParameter(param.getString("name"),param.getJSONObject("value"));
		}
	}
	protected abstract void config();
	protected abstract void setParameter(String id, JSONObject value);
}
