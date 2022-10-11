package launcher;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import genome_editing.model.Constants;
import genome_editing.model.GenomeEditor;
import genome_editing.model.GenomeVisualizer;
import genome_editing.model.genome.Chromosome;
import genome_editing.model.genome.Genotype;

public class Launcher extends JFrame{
	public Launcher() {
		Dimension dim = new Dimension(805*2,850);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);

		Genotype geno = new Genotype();
		for(int i=0;i<Constants.PLOIDY;i++) {
			geno.add(new Chromosome(Constants.CHROMOSOME_LENGTH));
		}
		
		this.setLayout(new GridLayout(1,2));
		
		GenomeVisualizer visualizer = new GenomeVisualizer(800,800,geno);
		this.add(visualizer);
		
		GenomeEditor editor = new GenomeEditor(geno, visualizer);
		this.add(editor);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
	public static void main(String args[]) {
		SwingUtilities.invokeLater(()->new Launcher().setVisible(true));
	}

}
