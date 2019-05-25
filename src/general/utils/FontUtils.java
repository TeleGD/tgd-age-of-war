package general.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FontUtils {

	public static final String FONT_DIRECTORY_NAME="fonts";
	public static final String FONT_PATH=FONT_DIRECTORY_NAME+File.separator;
	public static final boolean ENABLE_LOG=false;

	/**
	 * Charger une police système : Arial, Kalinga, ...
	 * @param name :  nom de la police
	 * @param type : PLAIN, BOLD   -> Font.BOLD, Font.PLAIN
	 * @param size : taille de la police
	 * @return un objet TrueTypeFont
	 */
	public static TrueTypeFont loadSystemFont(String name, int type, int size) {
		if(ENABLE_LOG)System.out.println("FontUtils >> loadSystemFont >> name="+name+"  size="+size+ "  type="+type);

		Font fontTemp = new Font(FONT_PATH+name, type, size);
		return new TrueTypeFont(fontTemp, true);
	}


	/**
	 * Charger une police personnalisé qui se trouve dans le répertoire font
	 * @param name :  nom de la police
	 * @param type : PLAIN, BOLD   -> Font.BOLD, Font.PLAIN
	 * @param size : taille de la police
	 * @return un objet TrueTypeFont
	 */
	public static TrueTypeFont loadCustomFont(String name, int type, int size) {
		if(ENABLE_LOG)System.out.println("FontUtils >> loadCustomFont >> name="+name+"  size="+size+ "  type="+type+" in path="+FONT_PATH);

		Font fontTemp = null;
		try {
			fontTemp = Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream(FONT_PATH+name));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return new TrueTypeFont(fontTemp.deriveFont(java.awt.Font.PLAIN, size),true);
	}

	/**
	 * Charger une police
	 * @param name :  nom de la police
	 * @param type : PLAIN, BOLD   -> Font.BOLD, Font.PLAIN
	 * @param size : taille de la police
	 * @return un objet TrueTypeFont
	 */
	public static TrueTypeFont loadFont(String name, int type, int size, boolean isSystemFont) {

		if(isSystemFont){
			return loadSystemFont(name, type, size);
		}else{
			return loadCustomFont(name, type, size);
		}
	}

}
