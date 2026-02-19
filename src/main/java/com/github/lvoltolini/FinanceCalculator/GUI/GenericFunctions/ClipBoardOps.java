package com.github.lvoltolini.FinanceCalculator.Generic.GenericFunctions;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipBoardOps
{
	private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	// ---------------------------
	// GENERIC 
	// ---------------------------
	private static <T>T getFromClipboard(DataFlavor flavor) 
	{
	    try 
	    {
	        Transferable contents = getContents();
	        if (isItNotNullAndRightDataFlavor(contents, flavor)) 
	        { return (T) contents.getTransferData(flavor); }
	    } 
	    catch (UnsupportedFlavorException | IOException e) { e.printStackTrace(); }
	    return null; // if Error or content is null/not Text
	}
	
	// ---------------------------
	// TEXT
	// ---------------------------
	public static void setText(String text)
	{ clipboard.setContents(new StringSelection(text), null); }
	
	public static String getText() { return getFromClipboard(DataFlavor.stringFlavor); }


//	private static String getTextFromClipboard() { //	    try //	    { //	        Transferable contents = getContents(); //	        if (isItNotNullAndRightDataFlavor(contents, DataFlavor.stringFlavor)) //	        { return (String) contents.getTransferData(DataFlavor.stringFlavor); } //	    } //	    catch (UnsupportedFlavorException | IOException e) { e.printStackTrace(); } //	    return null; // if Error or content is null/not Text //	}

	/**
	 * @param contents
	 * @return
	 */
	public static boolean isItNotNullAndRightDataFlavor(Transferable contents, DataFlavor typeFlavor)
	{ return (contents != null) && (contents.isDataFlavorSupported(DataFlavor.stringFlavor)); }

	// ---------------------------
	// IMAGE
	// ---------------------------
	public static Image getImage() { return getFromClipboard(DataFlavor.imageFlavor); }
//	public static Image getImageFromClipboard() //	{ //	    try //	    { //	        Transferable contents = getContents(); //	        if (isItNotNullAndRightDataFlavor(contents, DataFlavor.imageFlavor)) //	        {  return (Image) contents.getTransferData(DataFlavor.imageFlavor); } //	    } //	    catch (UnsupportedFlavorException | IOException e) {  e.printStackTrace(); } // //	    return null; //	}

	/**
	 * @return
	 */
	public static Transferable getContents()
	{
		return clipboard.getContents(null);
	}

	public static void setImage(Image image) 
	{
	    Transferable transferable = new Transferable() 
	    {

	        @Override
	        public DataFlavor[] getTransferDataFlavors() 
	        { return new DataFlavor[] { DataFlavor.imageFlavor }; }

	        @Override
	        public boolean isDataFlavorSupported(DataFlavor flavor) 
	        { return DataFlavor.imageFlavor.equals(flavor); }

	        @Override
	        public Object getTransferData(DataFlavor flavor)
	                throws UnsupportedFlavorException 
	        {
	            if (!isDataFlavorSupported(flavor)) { throw new UnsupportedFlavorException(flavor); }
	            else {return image;}
	        }
	    };
	    clipboard.setContents(transferable, null);
	}

}
