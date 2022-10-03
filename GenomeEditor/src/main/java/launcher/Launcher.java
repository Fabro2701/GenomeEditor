package launcher;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Constants;
import model.GenomeVisualizer;
import model.genome.Chromosome;
import model.genome.Genotype;

public class Launcher extends JFrame{
	public Launcher() {
		Dimension dim = new Dimension(800,800);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);

		Genotype geno = new Genotype();
		geno.add(new Chromosome(Constants.CHROMOSOME_LENGTH));
		geno.add(new Chromosome(Constants.CHROMOSOME_LENGTH));
		JPanel mainP = new GenomeVisualizer(800,800,geno);
		this.setContentPane(mainP);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]) {
		SwingUtilities.invokeLater(()->new Launcher().setVisible(true));
	}

}
