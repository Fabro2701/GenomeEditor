package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.elements.Matrix;
import model.elements.Mesh;
import model.elements.Vector3D;
import model.genome.Genotype;

public class GenomeVisualizer extends JPanel{
	Mesh mesh;
	Matrix projectionMatrix;
	int WIDTH=800, HEIGHT=800;
	float zfar=1000f,znear=0.1f;
	float a=(float)WIDTH/(float)HEIGHT,f=90f,q=zfar/(zfar-znear);
	float frad = (float) (1f/ (float)Math.tan(f*0.5f/180f*3.14159f));
	float offset = 8f;
	float time = 0.0f;
	Vector3D camera;
	Vector3D lookDir;
	float fYaw,fXaw;
	public GenomeVisualizer(int width, int height, Genotype geno) {
		this.WIDTH = width;
		this.HEIGHT = height;
		mesh = new Mesh();
		
	    //mesh.setTriangles(Triangulate.convert("elevation", 5f));
		
		//projectionMatrix = Util.createProjectionMatrix(zfar, znear, a, f, q, frad);
		projectionMatrix = Matrix.Matrix_MakeProjection(f, a, znear, zfar);
		
		camera = new Vector3D(0f,0f,0f);
		lookDir = new Vector3D(0f,0f,1f);
		
		MouseAdapter mouseA = new MouseAdapter() {
			
			boolean pressed = false;
    		Point current = null;
    		@Override
			public void mousePressed(MouseEvent e) {
    			//advance(0.0005f);
    			if(!pressed) {
    				pressed = true;
        			current = e.getPoint();
    			}
			}
    		@Override
			public void mouseReleased(MouseEvent e) {pressed = false;}
			@Override
			public void mouseDragged(MouseEvent e) {
				if(pressed) {
					Point dir = e.getPoint();
					if(dir.equals(current))return;
					int dx = dir.x-current.x;
					int dy = dir.y-current.y;
					
					float decreaseFactor = 10000.0f;
					if(Math.abs(dx) >10)GenomeVisualizer.this.fYaw +=(float)dx/decreaseFactor;
					if(Math.abs(dy) >10)GenomeVisualizer.this.fXaw +=(float)dy/decreaseFactor;
							
					repaint();
				}
			}
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println(GenomeVisualizer.this.lookDir);
				Vector3D vForward = Vector3D.mul(GenomeVisualizer.this.lookDir, -((float)e.getWheelRotation())*0.3f);
				GenomeVisualizer.this.camera = Vector3D.add(GenomeVisualizer.this.camera, vForward);
				repaint();
			}
		};
		this.addMouseListener(mouseA);
		this.addMouseMotionListener(mouseA);;
		this.addMouseWheelListener(mouseA);

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent ke) {
				int c = ke.getKeyCode();
				switch(c) {
				  case KeyEvent.VK_S:
					  GenomeVisualizer.this.camera.y-=0.8f;
				    break;
				  case KeyEvent.VK_W:
					  GenomeVisualizer.this.camera.y+=0.8f;
				    break;
				  case KeyEvent.VK_D:
					  GenomeVisualizer.this.camera.x-=0.8f;
				    break;
				  case KeyEvent.VK_A:
					  GenomeVisualizer.this.camera.x+=0.8f;
				    break;
				  case KeyEvent.VK_R:
					  GenomeVisualizer.this.camera.z-=0.8f;
				    break;
				  case KeyEvent.VK_F:
					  GenomeVisualizer.this.camera.z+=0.8f;
				    break;

				  default:				    
				}
				repaint();
			}
			@Override
			public void keyReleased(KeyEvent ke) {}
			@Override
			public void keyTyped(KeyEvent ke) {}
		};
		this.addKeyListener(keyListener);
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("look: "+lookDir);
		System.out.println("camera: "+camera);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(0, 0, this.WIDTH, this.HEIGHT);
		
		//time+=0.1f;
		Matrix zrotation = Matrix.Matrix_MakeRotationZ(time);
		Matrix xrotation = Matrix.Matrix_MakeRotationX(time);
		
		Matrix matTrans = Matrix.translate(0f, 0f, offset);
		
		Matrix matWorld = Matrix.identity();
		matWorld = zrotation.multiply(xrotation);
		matWorld = matWorld.multiply(matTrans);
		
		Vector3D vUp = new Vector3D(0f,1f,0f);
		//Vector3D vTarget = camera.add(lookDir);
		Vector3D vTarget = new Vector3D(0f,0f,1f);
		Matrix matCameraRot = Matrix.Matrix_MakeRotationY(fYaw);
		matCameraRot = matCameraRot.multiply(Matrix.Matrix_MakeRotationX(fXaw));
		lookDir = Vector3D.multiplyMatrix(vTarget, matCameraRot);
		vTarget = Vector3D.add(camera, lookDir);
		
		Matrix matCamera = Matrix.Matrix_PointAt(camera, vTarget, vUp);
		Matrix matView = Matrix.Matrix_QuickInverse(matCamera);
		
		//strands
		List<Vector3D>strand1 = Triangulate.createStrand(0f);
		List<Vector3D>strand2 = Triangulate.createStrand(3.14159f);
		
		int alpha = 200;
		g2.setStroke(new BasicStroke(20f));
		g2.setColor(new Color(70,70,70,alpha));
		this.drawStrands(g2, matWorld, matView, strand1);
		g2.setColor(new Color(70,70,70,alpha));
		this.drawStrands(g2, matWorld, matView, strand2);
		
		
	}
	protected void drawStrands(Graphics2D g2, Matrix matWorld, Matrix matView, List<Vector3D>points) {
		List<Vector3D>todraw = new ArrayList<Vector3D>();
		for(Vector3D tri:points) {
			Vector3D triTransformed = Vector3D.multiplyMatrix(tri, matWorld);
			Vector3D triViewed = Vector3D.multiplyMatrix(triTransformed, matView);
			Vector3D triProjected = Vector3D.multiplyMatrix(triViewed, projectionMatrix);
			
			triProjected = Vector3D.div(triProjected, triProjected.w);

			//System.out.println(triProjected);
			triProjected.x *= -1.0f;
			triProjected.y *= -1.0f;
			Vector3D offsetView = new Vector3D(1f,1f,0f);
			triProjected = Vector3D.add(triProjected, offsetView);
			triProjected.x *= 0.5f * (float)this.WIDTH;
			triProjected.y *= 0.5f * (float)this.HEIGHT;
			todraw.add(triProjected);
			//System.out.println(triProjected);
		}
		for(int i=0;i<todraw.size();i++) {
			//g2.fillOval((int)todraw.get(i).x, (int)todraw.get(i).y, 5, 5);
			if(i!=0) {
				g2.drawLine((int)todraw.get(i).x, (int)todraw.get(i).y, (int)todraw.get(i-1).x, (int)todraw.get(i-1).y);
			}
		}
	}
}
