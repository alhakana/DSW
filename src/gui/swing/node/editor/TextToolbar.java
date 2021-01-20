package gui.swing.node.editor;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.text.StyledEditorKit;



@SuppressWarnings("serial")
public class TextToolbar extends JToolBar{
	
	public TextToolbar() {
		setFloatable(false);
		setCursor(getCursor());

		JButton italic = new JButton();
//		italic.setIcon(new ImageIcon(getClass().getResource("images/edit-italic.png")));
		italic.setText("Italic");
		italic.setToolTipText("Italic");
		italic.addActionListener(new StyledEditorKit.ItalicAction());
		add(italic);
		
		
		JButton bold = new JButton();
//		bold.setIcon(new ImageIcon(getClass().getResource("images/edit-bold.png")));
		bold.setText("Bold");
		bold.setToolTipText("Bold");
		bold.addActionListener(new StyledEditorKit.BoldAction());
		add(bold);

		JButton underline = new JButton();
//		underline.setIcon(new ImageIcon(getClass().getResource("images/edit-underline.png")));
		underline.setText("Underline");
		underline.setToolTipText("Underline");
		underline.addActionListener(new StyledEditorKit.UnderlineAction());
		add(underline);
	}

}
