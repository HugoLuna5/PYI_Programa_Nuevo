/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyi_programa_ana_maritza.utils;

/**
 *
 * @author Dell
 */
import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

/**
 * Utilieria para agregar un placeholder 
 * a un JTextField.
 * Fuente: 
 * https://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder
 * @author Dell
 */


/**
 * Esta clase extiende las propiedades de 
 * JTextField
 * @author Dell
 */
@SuppressWarnings("serial")
public class CustomPlaceholderTextField extends JTextField {

   /**
    * Declaracion de variables
    */
    private String placeholder;

    
    /**
     * Constructor de la clase vacio
     */
    public CustomPlaceholderTextField() {
    }

    
    /**
     * Constructor de la clase
     * @param pDoc
     * @param pText
     * @param pColumns 
     */
    public CustomPlaceholderTextField(
        final Document pDoc,
        final String pText,
        final int pColumns)
    {
        super(pDoc, pText, pColumns);
    }

    public CustomPlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    public CustomPlaceholderTextField(final String pText) {
        super(pText);
    }

    public CustomPlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    /**
     * se obtiene el placeholder
     * @return 
     */
    public String getPlaceholder() {
        return placeholder;
    }

    
    /**
     * Se dibuja en el componente
     * @param pG 
     */
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top);
    }

    
    /**
     * Se establece del placeholder 
     * @param s 
     */
    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}
