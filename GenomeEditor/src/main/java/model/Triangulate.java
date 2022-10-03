package model;

import static model.Constants.cosf;
import static model.Constants.sinf;

import java.util.ArrayList;
import java.util.List;

import model.elements.Triangle;
import model.elements.Vector3D;

public class Triangulate {
	public static List<Vector3D>createStrand(float shift) {
		List<Vector3D>points = new ArrayList<Vector3D>();
		float t=0f,step=1f;
		float domain=10f;
		
		t=-domain;
		
		float x,z;
		while(t<=domain) {
			x = sinf(t+shift);
			z = cosf(t+shift);
			points.add(new Vector3D(x,z,t));
			//System.out.println("point: "+(x*10f)+", "+t);
			
			t += step;
		}
		return points;
	}
}
