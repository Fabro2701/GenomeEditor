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
		float t=0f,step=0.2f;
		float domain=5f;
		float a = 1f;
		
		t=-domain;
		
		float x,z;
		while(t<=domain) {
			x = a*sinf(t+shift);
			z = a*cosf(t+shift);
			points.add(new Vector3D(x,t,z));
			//System.out.println("point: "+(x*10f)+", "+t);
			
			t += step;
		}
		return points;
	}
}
