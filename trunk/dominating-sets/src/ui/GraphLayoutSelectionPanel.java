package ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.graph.layout.GraphLayout;
import ui.graph.layout.GraphLayoutType;

public abstract class GraphLayoutSelectionPanel extends JPanel {
	private final ButtonGroup group;
	public GraphLayoutSelectionPanel() {
		super();
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		add(panel,BorderLayout.CENTER);
		group = new ButtonGroup();
		panel.setBorder(BorderFactory.createTitledBorder("Graph layout"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for(final GraphLayoutType layout:GraphLayoutType.values()){
			final JRadioButton rb = new JRadioButton(layout.name());
			rb.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent arg0) {
					if(rb.isSelected()){
						layoutChanged(layout.clazz());
					}
				}
			});
			group.add(rb);
			panel.add(rb);
		}
		group.getElements().nextElement().setSelected(true);
	}
	public void setFirst(){
		((JRadioButton)group.getElements().nextElement()).setSelected(true);
	}
	public abstract void layoutChanged(Class<? extends GraphLayout> layout);
}
