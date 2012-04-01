package ui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solver.DominantSetSolver;
import solver.DominantSetSolverType;

public abstract class DominantSetSolverSelectionPanel extends JPanel implements ActionListener{
	
	public DominantSetSolverSelectionPanel() {
		super();
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Dominant set solver"));
		JPanel optionsPane = new JPanel(new GridLayout(0, 1,2,2));
		ButtonGroup group = new ButtonGroup();
		for(final DominantSetSolverType dominantSetFinderType:DominantSetSolverType.values()){
			final JRadioButton rbutton = new JRadioButton(dominantSetFinderType.name());
			group.add(rbutton);
			rbutton.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent ce) {
					if(rbutton.isSelected()){
						selectedSolver=dominantSetFinderType.clazz();
					}
				}
			});
			optionsPane.add(rbutton);
		}
		
		group.getElements().nextElement().setSelected(true);
		
		JPanel buttonPane = new JPanel();
		BoxLayout bl = new BoxLayout(buttonPane, BoxLayout.Y_AXIS);
		
		buttonPane.setLayout(bl);
		
		final JButton b = new JButton("Solve");
		b.addActionListener(this);
		buttonPane.add(b);
		
		add(optionsPane,BorderLayout.NORTH);

		add(buttonPane,BorderLayout.CENTER);
		
	}
	private Class<? extends DominantSetSolver> selectedSolver;
	public Class<? extends DominantSetSolver> getSelectedSolver() {
		return selectedSolver;
	}
}