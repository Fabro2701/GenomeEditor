package genome_editing.model;

import static genome_editing.model.Constants.cosf;
import static genome_editing.model.Constants.sinf;

import java.util.ArrayList;
import java.util.List;

import genome_editing.model.elements.Triangle;
import genome_editing.model.elements.Vector3D;

public class Triangulate {
	public static List<Vector3D>createStrand(float shift) {
		List<Vector3D>points = new ArrayList<Vector3D>();
		float t=0f,step=0.2f;
		float domain=5f;
		float a = 1f;
		
		t=-domain;
		
		float x,z;
		while(t<=domain) {
			x = a*sinf(t+shift);
			z = a*cosf(t+shift);
			points.add(new Vector3D(x,t,z));			
			t += step;
		}
		return points;
	}
}
