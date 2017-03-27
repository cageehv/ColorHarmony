/*************************************************************************************
 *
 *	Colorharmony - a library for Processing
 *
 *	Generate color palettes with colors that are 'harmonized'
 *	(meaning: 'look good together')
 *
 *	Compiled with jdk1.6.0_45 and the Processing 2.2.1 core
 *	(for keeping it Proccessing v2+ compatible)
 *
 *	Tested with Processing v2.2.1 up to v3.3 
 *
 *************************************************************************************/
package com.cage.colorharmony;

import java.awt.Color;
import java.lang.String;
import java.util.Random;

import processing.core.*;


/*************************************************************************************
 *
 *	<b>ColorHarmony class</b><br><br>
 *	A class for generating harmonized color palettes (types: monochromatic, analogous, 
 *	complementary and triads)<br><br>
 *	Author: Rolf van Gelder | CAGE Web Design<br>
 *	http://cagewebdev.com/colorharmony-processing-library/<br>
 *	info@cagewebdev.com
 *
 *************************************************************************************/
public class ColorHarmony extends ColorConvertor {

	/*********************************************************************************
	 *
	 *	Properties
	 *
	 *********************************************************************************/
	String thisVersion     = "1.0";
	String thisReleaseDate = "03/17/2017";
	String thisCopyright   = "(c) 2017 Rolf van Gelder | CAGE Web Design (rvg.cage.nl | cagewebdev.com)";

	// Values can be overruled in the second constructor
	int minSaturation      = 10;
	int maxSaturation      = 60;
	int minLuminosity      = 20;
	int maxLuminosity      = 60;

	String[] paletteTypes  = { "Monochromatic", "Analogous", "Complementary", "Triads" };

	// Current base color
	String baseColorHex;

	// Current palette type
	int currentType;

	
	/*********************************************************************************
	 *
	 *	Constructor for the ColorHarmony class (default constructor)<br>
	 *	Example: ColorHarmony colorHarmony = new ColorHarmony(this);
	 *
	 *********************************************************************************/
	public ColorHarmony(PApplet _parent) {
		this.parent = _parent;
	} // ColorHarmony()


	/*********************************************************************************
	 *
	 *	Constructor for the ColorHarmony class (with minimum and maximum saturation 
	 *	and luminosiy values)<br>
	 *	Example: ColorHarmony colorHarmony = new ColorHarmony(this, 15, 55, 20, 65);<br>
	 *	Default  min/max values: 10, 60, 20, 60<br>
	 *	Range for the min/max values: 0..100
	 *
	 *********************************************************************************/
	public ColorHarmony(PApplet _parent, int _minSaturation, int _maxSaturation, int _minLuminosity, int _maxLuminosity) {
		this.parent   = _parent;
		minSaturation = _minSaturation;
		maxSaturation = _maxSaturation;
		minLuminosity = _minLuminosity;
		maxLuminosity = _maxLuminosity;
	} // ColorHarmony()	
	

	/*********************************************************************************
	 *
	 *	Monochromatic color palette generator with a random base color<br>
	 *	Example: int[] colors = colorHarmony.Monochromatic();
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Monochromatic() {
		return Monochromatic(RandomHexColor());
	} // Monochromatic()


	/*********************************************************************************
	 *
	 *	Monochromatic color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Monochromatic(color(23, 234, 80));
	 *
	 *	@param P5color 	Processing color value for the base color
	 *
	 *	@return			The generated palette: a Processing color array with
	 *					8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Monochromatic(int P5color) {
		return Monochromatic(P52Hex(P5color));
	} // Monochromatic()


	/*********************************************************************************
	 *
	 *	Monochromatic color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Monochromatic("#FF00FF");
	 *
	 *	@param hex	Hexadecimal color value for the base color
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Monochromatic(String hex) {
		currentType = 0;

		String[] paletteHex = new String[8];
		int[] P5Colors      = new int[8];

		int Rtemp, Gtemp, Btemp;

		hex.toUpperCase().replace("#", "");

		baseColorHex = correctColorHex(hex);

		// Color 1 (Corrected base color)
		paletteHex[0] = baseColorHex;
		P5Colors[0]   = Hex2P5(baseColorHex);

		int[] RGB = Hex2RGB(paletteHex[0]);
		int R = RGB[0];
		int G = RGB[1];
		int B = RGB[2];

		// Color2
		Rtemp = MonoColor(R, 2);
		Gtemp = MonoColor(G, 2);
		Btemp = MonoColor(B, 2);
		paletteHex[1] = correctColorHex(RGB2Hex(Rtemp, Gtemp, Btemp));
		P5Colors[1]   = Hex2P5(correctColorHex(RGB2Hex(Rtemp, Gtemp, Btemp)));

		// Color 3
		Rtemp = MonoColor(R, 3);
		Gtemp = MonoColor(G, 3);
		Btemp = MonoColor(B, 3);
		paletteHex[2] = correctColorHex(RGB2Hex(Rtemp, Gtemp, Btemp));
		P5Colors[2]   = Hex2P5(correctColorHex(RGB2Hex(Rtemp, Gtemp, Btemp)));

		// Color 4
		Rtemp = MonoColor(R, 4);
		Gtemp = MonoColor(G, 4);
		Btemp = MonoColor(B, 4);
		paletteHex[3] = correctColorHex(RGB2Hex(Rtemp, Gtemp, Btemp));
		P5Colors[3]   = Hex2P5(correctColorHex(RGB2Hex(Rtemp, Gtemp, Btemp)));

		// Color 5
		P5Colors[4]  = Hex2P5(correctColorHex(SecondColor(paletteHex[0])));
		//Color 6
		P5Colors[5]  = Hex2P5(correctColorHex(SecondColor(paletteHex[1])));
		// Color 7
		P5Colors[6]  = Hex2P5(correctColorHex(SecondColor(paletteHex[2])));
		// Color 8
		P5Colors[7]  = Hex2P5(correctColorHex(SecondColor(paletteHex[3])));

		// Return array with Processing colors
		return P5Colors;
	} // Monochromatic()


	/*********************************************************************************
	 *
	 *	Analogous color palette generator with a random base color<br>
	 *	Example: int[] colors = colorHarmony.Analogous();
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Analogous() {
		return Analogous(RandomHexColor());
	} // Analogous()


	/*********************************************************************************
	 *
	 *	Analogous color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Analogous(color(23, 234, 80));
	 *
	 *	@param P5color 	Processing color value for the base color
	 *
	 *	@return			The generated palette: a Processing color array with
	 *					8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Analogous(int P5color) {
		return Analogous(P52Hex(P5color));
	} // Analogous()


	/*********************************************************************************
	 *
	 *	Analogous color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Analogous("#FF00FF");
	 *
	 *	@param hex	Hexadecimal color value for the base color
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Analogous(String hex) {
		currentType = 1;

		String[] paletteHex = new String[8];
		int[] P5Colors      = new int[8];

		hex.toUpperCase().replace("#", "");
		baseColorHex = correctColorHex(hex);

		// Color 1 (Corrected base color)
		paletteHex[0] = baseColorHex;
		P5Colors[0]   = Hex2P5(baseColorHex);

		float[] hsl = Hex2HSL(paletteHex[0]);

		float H = hsl[0];
		float S = hsl[1];
		float L = hsl[2];

		float H1 = FixHue(H + 30);
		float H2 = FixHue(H - 30);

		// Color 2
		paletteHex[1] =HSL2Hex(H1, S, L);
		P5Colors[1]   = Hex2P5(paletteHex[1]);

		// Color 3
		paletteHex[2] = "FFFFFF";
		P5Colors[2]   = Hex2P5(paletteHex[2]);

		// Color 4
		paletteHex[3] = HSL2Hex(H2, S, L);
		P5Colors[3]   = Hex2P5(paletteHex[3]);

		// Color 5
		P5Colors[4]  = Hex2P5(SecondColor(paletteHex[0]));
		// Color 6
		P5Colors[5]  = Hex2P5(SecondColor(paletteHex[1]));
		// Color 7
		P5Colors[6]  = Hex2P5(SecondColor(paletteHex[2]));
		// Color 8
		P5Colors[7]  = Hex2P5(SecondColor(paletteHex[3]));

		// Return array with Processing colors
		return P5Colors;
	} // Analogous()


	/*********************************************************************************
	 *
	 *	Complementary color palette generator with a random base color<br>
	 *	Example: int[] colors = colorHarmony.Complementary();
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Complementary() {
		return Complementary(RandomHexColor());
	} // Complementary()


	/*********************************************************************************
	 *
	 *	Complementary color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Complementary(color(23, 234, 80));
	 *
	 *	@param P5color 	Processing color value for the base color
	 *
	 *	@return			The generated palette: a Processing color array with
	 *					8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Complementary(int P5color) {
		return Complementary(P52Hex(P5color));
	} // Complementary()


	/*********************************************************************************
	 *
	 *	Complementary color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Complementary("#FF00FF");
	 *
	 *	@param hex	Hexadecimal color value for the base color
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors 
	 *
	 *********************************************************************************/
	public int[] Complementary(String hex) {
		currentType = 2;

		String[] paletteHex = new String[8];
		int[] P5Colors      = new int[8];

		hex.toLowerCase().replace("#", "");
		baseColorHex = correctColorHex(hex);

		int[] RGB = new int[3];

		int R, G, B;

		// Color 1 (Corrected base color)
		paletteHex[0] = baseColorHex;
		P5Colors[0]   = Hex2P5(baseColorHex);

		RGB = Hex2RGB(paletteHex[0]);
		R = RGB[0];
		G = RGB[1];
		B = RGB[2];
		R = MonoColor(R, 2);
		G = MonoColor(G, 2);
		B = MonoColor(B, 2);

		// Color 2
		float[] hsl = Hex2HSL(baseColorHex);
		float H = hsl[0];
		H       = FixHue(H + 180);
		float S = hsl[1];
		float L = hsl[2];
		paletteHex[1] = HSL2Hex(H, S, L);
		P5Colors[1]   = Hex2P5(paletteHex[1]);

		// Color 3
		int[] RGB2 = Hex2RGB(paletteHex[1]);
		int R2 = RGB2[0];
		int G2 = RGB2[1];
		int B2 = RGB2[2];

		R = MonoColor(R, 2);
		G = MonoColor(G, 2);
		B = MonoColor(B, 2);
		paletteHex[2] = RGB2Hex(R, G, B);
		P5Colors[2]   = Hex2P5(paletteHex[2]);

		// Color 4
		R2 = MonoColor(R2, 2);
		G2 = MonoColor(G2, 2);
		B2 = MonoColor(B2, 2);		
		paletteHex[3] = RGB2Hex(R2, G2, B2);
		P5Colors[3]   = Hex2P5(paletteHex[3]);

		// Color 5
		P5Colors[4]  = Hex2P5(SecondColor(paletteHex[0]));
		// Color 6
		P5Colors[5]  = Hex2P5(SecondColor(paletteHex[1]));
		// Color 7
		P5Colors[6]  = Hex2P5(SecondColor(paletteHex[2]));
		// Color 8
		P5Colors[7]  = Hex2P5(SecondColor(paletteHex[3]));

		// Return array with Processing colors
		return P5Colors;
	} // Complementary()


	/*********************************************************************************
	 *
	 *	Triads color palette generator with a random base color<br>
	 *	Example: int[] colors = colorHarmony.Triads();
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Triads() {
		return Triads(RandomHexColor());
	} // Triads()


	/*********************************************************************************
	 *
	 *	Triads color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Triads(color(23, 234, 80));
	 *
	 *	@param P5color 	Processing color value for the base color
	 *
	 *	@return			The generated palette: a Processing color array with
	 *					8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Triads(int P5color) {
		return Triads(P52Hex(P5color));
	} // Triads()


	/*********************************************************************************
	 *
	 *	Triads color palette generator for a specific base color<br>
	 *	Example: int[] colors = colorHarmony.Triads("#FF00FF");
	 *
	 *	@param hex	Hexadecimal color value for the base color
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] Triads(String hex) {
		currentType = 3;

		String[] paletteHex = new String[8];
		int[]    P5Colors   = new int[8];

		hex.toUpperCase().replace("#", "");
		baseColorHex = correctColorHex(hex);

		float[] hsl = Hex2HSL(baseColorHex);
		float H = hsl[0];
		float S = hsl[1];
		float L = hsl[2];

		float H1 = FixHue(H + 120);
		float H2 = FixHue(H - 120);

		// Color 1 (Corrected base color)
		paletteHex[0] = baseColorHex;
		P5Colors[0]   = Hex2P5(paletteHex[0]);
		// Color 2
		paletteHex[1] = HSL2Hex(H1, S, L);
		P5Colors[1]   = Hex2P5(paletteHex[1]);
		// Color 3
		paletteHex[2] = "FFFFFF";
		P5Colors[2]   = Hex2P5(paletteHex[2]);

		// Color 4
		paletteHex[3] = HSL2Hex(H2, S, L);
		P5Colors[3]   = Hex2P5(HSL2Hex(H2, S, L));

		// Color 5
		P5Colors[4]  = Hex2P5(SecondColor(paletteHex[0]));
		// Color 6
		P5Colors[5]  = Hex2P5(SecondColor(paletteHex[1]));
		// Color 7
		P5Colors[6]  = Hex2P5(SecondColor(paletteHex[2]));
		// Color 8
		P5Colors[7]  = Hex2P5(SecondColor(paletteHex[3]));

		// Return array with Processing colors
		return P5Colors;
	} // Triads()


	/*********************************************************************************
	 *
	 *	Generates a random palette with random palette type and random base color<br>
	 *	Example: int[] colors = colorHarmony.GetRandomPalette();
	 *
	 *	@return		The generated palette: a Processing color array with
	 *				8 harmonized colors
	 *
	 *********************************************************************************/
	public int[] GetRandomPalette() {
		Random rand = new Random();
		currentType = rand.nextInt(4);
		if (currentType == 0) {
			return Monochromatic();
		} else if (currentType == 1) {
			return Analogous();
		} else if (currentType == 2) {
			return Complementary();
		} else {
			return Triads();		
		}

	} // GetRandomPalette()


	/*********************************************************************************
	 *
	 *	Generates a random color as an hexadecimal string<br>
	 *	Example: String hexColor = colorHarmony.RandomHexColor();
	 *
	 *	@return		Random color in hexadecimal format
	 *
	 *********************************************************************************/
	public String RandomHexColor() {
		String rndString = "";
		Random rand = new Random();
		int ndx;
		for (int i=0; i<6; i++) {
			ndx = rand.nextInt(16);
			char c = hexadecimalChars.charAt(ndx);
			rndString += c;
		} // for (int i=0; i<8; i++)
		return rndString;
	} // RandomHexColor() 


	/*********************************************************************************
	 *
	 *	Return the hexadecimal value of the current base color<br>
	 *	Example: String currentHexColor = colorHarmony.GetBaseColor();
	 *
	 *	@return		Current base color (hexadecimal string)
	 *
	 *********************************************************************************/
	public String GetBaseColor() {
		return baseColorHex;
	} // GetBaseColor()


	/*********************************************************************************
	 *
	 *	Return the current palette type [int 0..3 = monochromatic, analogous, complementary, triads]<br>
	 *	Example: int currentType = colorHarmony.GetCurrentType();
	 *
	 *	@return		Current palette type
	 *
	 *********************************************************************************/
	public int GetCurrentType() {
		return currentType;
	} // GetCurrentType()


	/*********************************************************************************
	 *
	 *	Return the current palette type description<br>
	 *	Example: String typeDescription = colorHarmony.GetCurrentTypeText();
	 *
	 *	@return		Current palette type description ("Monochromatic", "Analogous", "Complementary" or "Triads")
	 *
	 *********************************************************************************/
	public String GetCurrentTypeText() {
		return paletteTypes[currentType];
	} // GetCurrentTypeText()	


	/*********************************************************************************
	 *
	 *	Displays version information of this library in the console<br>
	 *	Example: colorHarmony.printVersion();
	 *
	 *********************************************************************************/
	public void printVersion() {
		System.out.println("ColorHarmony v"+thisVersion+" ("+thisReleaseDate+")");
		System.out.println(thisCopyright + "\n");
	} // printVersion()	


	/*********************************************************************************
	 *
	 *	INTERNAL METHODS
	 *
	 *********************************************************************************/	


	/*********************************************************************************
	 *
	 *	INTERNAL: Fix the hue (if needed)
	 *
	 *	@param hue 	Hue value to check and fix, if needed
	 *
	 *	@return		Fixed hue value
	 *
	 *********************************************************************************/	
	float FixHue(float hue) {
		if (hue < 0) {
			return hue + 360;
		} else if (hue > 360) {
			return hue - 360;
		} else {
			return hue;
		} // if (hue < 0)
	} // FixHue()
	

	/*********************************************************************************
	 *
	 *	INTERNAL: Generate a second color, based on the base color
	 *
	 *	@param hex 	Hexadecimal color value for the base color
	 *
	 *	@return		Hexadecimal color value of the generated second color
	 *
	 *********************************************************************************/		
	String SecondColor(String hex) {

		int[] RGB = Hex2RGB(hex);
		int R = RGB[0];
		int G = RGB[1];
		int B = RGB[2];

		float par = 0.75f;

		int R2 = (int)Math.floor(par * R);
		int G2 = (int)Math.floor(par * G);
		int B2 = (int)Math.floor(par * B);

		return RGB2Hex(R2, G2, B2);
	} // SecondColor()


	/*********************************************************************************
	 *
	 *	INTERNAL: Mono color
	 *
	 *	@param color 	Red, green of blue value of a color
	 *	@param number	Sequence number of the color
	 *
	 *	@return			Value
	 *
	 *********************************************************************************/	
	int MonoColor(int color, int number) {

		int par1 = 128;
		int par2 = 192;
		int par3 = 90; // 64
		int par4 = 215; // 223

		int diffC = color - par1;
		int diff  = Math.abs(diffC);

		// Color 2
		if (number == 2) {
			if (diffC >= 1) {
				return par2 + (int)Math.floor(0.5f * diff);
			} else if (diffC < 0) {
				return par2 - (int)Math.floor(0.5f * diff);
			} else {
				return par2;
			} // if (diffC >= 1)
		} // if (number == 2)

		// Color 3		
		if (number == 3) {
			if (diffC >= 1) {
				return par3 + (int)Math.floor(0.5f * diff);
			} else if (diffC < 0) {
				return par3 - (int)Math.floor(0.5f * diff);
			} else {
				return par3;
			} // if (diffC >= 1)
		} // if (number == 3)
		
		// Color 4
		if (number == 4) {
			if (diffC >= 1) {
				return par4 + (int)Math.floor(0.25f * diff);
			} else if (diffC < 0) {
				return par4 - (int)Math.floor(0.25f * diff);
			} else {
				return par4;
			} // if (diffC >= 1)
		} // if (number == 4)

		return -1;
	} // MonoColor()
	

	/*********************************************************************************
	 *
	 *	INTERNAL: Correct a hex color (based of max values)
	 *
	 *	@param hex 	Hex value of the color to correct
	 *
	 *	@return		The corrected hex value
	 *
	 *********************************************************************************/	
	String correctColorHex(String hex) {

		float[] hsl = Hex2HSL(hex);

		float hBase = hsl[0];
		float sBase = hsl[1];
		float lBase = hsl[2];
		
		if (sBase > maxSaturation) {
			hex = HSL2Hex(hBase, maxSaturation, lBase);
		} else if (sBase < minSaturation) {
			hex = HSL2Hex(hBase, minSaturation, lBase);
		} // if (sBase > s_max)
		
		if (lBase > maxLuminosity) {
			hex = HSL2Hex(hBase, sBase, maxLuminosity);
		} else if (lBase < minLuminosity) {
			hex = HSL2Hex(hBase, sBase, minLuminosity);
		} // if (lBase > l_max)
		
		return hex;
	} // correctColorHex()	
} // ColorHarmony