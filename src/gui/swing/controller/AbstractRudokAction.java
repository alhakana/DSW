package gui.swing.controller;

import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public abstract class AbstractRudokAction extends AbstractAction {

    public Icon loadIcon(String fileName) {
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        else 
            System.err.println("Resource not found: " + fileName);
        
        return icon;
    }


}
