/*************************************************************************************
 *
 *	Colorharmony
 *
 *	Helper class for converting colors
 *
 *************************************************************************************/
package com.cage.colorharmony;

import java.awt.Color;
import java.util.*;

import processing.core.*;


/*************************************************************************************
 *
 *	<b>ColorConvertor class</b><br><br>
 *	A helper class for the ColorHarmony class. Takes care of all kinds of conversions.
 *	<br><br>
 *	Author: Rolf van Gelder | CAGE Web Design<br>
 *	http://cagewebdev.com/colorharmony-processing-library/<br>
 *	info@cagewebdev.com
 *
 *************************************************************************************/
public class ColorConvertor {

	/*********************************************************************************
	 *
	 *	Properties
	 *
	 *********************************************************************************/
	// All hexadecimal characters
	String hexadecimalChars  = "0123456789ABCDEF";

	// Processing applet
	PApplet parent;
	

	/*********************************************************************************
	 *
	 *	Constructor for the ColorConvertor class
	 *
	 *********************************************************************************/
	public ColorConvertor() {
	} // ColorConvertor()

	
	/*********************************************************************************
	 *
	 *	Convert a decimal integer value to a hexadecimal string<br>
	 *	Example: String hexValue = colorHarmony.Decimal2Hex(255);<br>
	 *	Result: "FF"
	 *
	 *	@param decimal 	Decimal integer value
	 *
	 *	@return			Hexadecimal string
	 *
	 *********************************************************************************/	
    public String Decimal2Hex(int decimal) {
        if (decimal == 0) return "0";
        String hex = "";
        while (decimal > 0) {
            int digit = decimal % 16;         // rightmost digit
            hex = hexadecimalChars.charAt(digit) + hex; // string concatenation
            decimal = decimal / 16;
        }
        return hex;
    } // Decimal2Hex()


	/*********************************************************************************
	 *
	 *	Convert a hexadecimal string to a decimal integer value<br>
	 *	Example: int decValue = colorHarmony.Hex2Decimal("FF");<br>
	 *	Result: 255
	 *
	 *	@param hex 		Hexadecimal color string (6 characters)
	 *
	 *	@return			Decimal integer value
	 *
	 *********************************************************************************/	
    public int Hex2Decimal(String hex) {
        hex = hex.toUpperCase().replace("#", "");
        int decimal = 0;
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int d  = hexadecimalChars.indexOf(c);
            decimal = 16 * decimal + d;
        }
        return decimal;
    } // Hex2Decimal()


	/*********************************************************************************
	 *
	 *	Convert a hexadecimal color string to HSL values<br>
	 *	Example: float[] hsl = colorHarmony.Hex2HSL("FFFFFF");<br>
	 *	Result: (0.0, 0.0, 100.0)<br>
	 * 	Note: the hex color string has to be 6 characters long!
	 *
	 *	@param hex 	Hexadecimal color string (6 characters)
	 *
	 *	@return		float[3] array with h, s and l values
	 *
	 *********************************************************************************/		
	public float[] Hex2HSL(String hex) {
		hex.toUpperCase().replace("#", "");
		if (hex.length() == 6) {
			int[] rgb = Hex2RGB(hex);
			return RGB2HSL(rgb[0], rgb[1], rgb[2]);
		} else {
			System.out.println("Hex2HSL(): hex color parameter should be 6 characters long");
		}
		return null;
	} // Hex2HSL()


	/*********************************************************************************
	 *
	 *	Convert a hexadecimal color string to a Processing color (int)<br>
	 *	Example:  color P5color = colorHarmony.Hex2P5("FFFFFF");<br>
	 *	Result: -1 (= color(255, 255, 255))<br>
	 *	Note: the hex color string has to be 6 characters long!
	 *
	 *	@param hex 		Hexadecimal color string (6 characters)
	 *
	 *	@return			Processing color (=int)
	 *
	 *********************************************************************************/		
	public int Hex2P5(String hex) {
		if (hex.length() == 6) {
			int[] rgb = Hex2RGB(hex);
			return parent.color(rgb[0], rgb[1], rgb[2]);			
		} else {
			System.out.println("Hex2P5(): hex color parameter should be 6 characters long");
		}
		return -1;
	} // Hex2P5()


	/*********************************************************************************
	 *
	 *	Convert a hexadecimal color string to RGB values<br>
	 *	Example: int[] rgb = colorHarmony.Hex2RGB("FFFFFF");<br>
	 *	Result: (255, 255, 255)<br>
	 *	Note: the hex color string has to be 6 characters long!
	 *
	 *	@param hex 		Hexadecimal color string (6 characters)
	 *
	 *	@return			int[3] array with r, g and b values
	 *
	 *********************************************************************************/			
	public int[] Hex2RGB(String hex) {
		int[] rgb = new int[3];
		hex = hex.toUpperCase().replace("#", "");

		 if (hex.length() == 6) {
			// FFFFFF
			rgb[0] = Hex2Decimal(hex.substring(0, 2));	// R
			rgb[1] = Hex2Decimal(hex.substring(2, 4));	// G
			rgb[2] = Hex2Decimal(hex.substring(4, 6));	// B
		} else {
			System.out.println("Hex2RGB(): hex color parameter should be 6 characters long");
		}

		return rgb;
	} // Hex2RGB()



	/*********************************************************************************
	 *
	 *	Convert HSL values to a hexadecimal solor string<br>
	 *	Example: String hexValue = colorHarmony.HSL2Hex(0.0, 0.0, 100.0);<br>
	 *	Result: "FFFFFF"
	 *
	 *	@param h 	Hue value (0..360)
	 *	@param s 	Saturation value (0..100)
	 *	@param l 	Luminosity value (0..100)
	 *
	 *	@return		Hexadecimal color string (6 characters)
	 *
	 *********************************************************************************/		
	public String HSL2Hex(float h, float s, float l) {
		int[] rgb = HSL2RGB(h, s, l);
		return RGB2Hex(rgb[0], rgb[1], rgb[2]);
	} // HSL2Hex()


	/*********************************************************************************
	 *
	 *	Convert HSL color values to RGB values<br>
	 *	Example: int[] rgb = colorHarmony.HSL2RGB(0.0, 0.0, 100.0);<br>
	 *	Result: (255, 255, 255)
	 *
	 *	@param h 	Hue value (0..360)
	 *	@param s 	Saturation value (0..100)
	 *	@param l 	Luminosity value (also called lightness) (0..100)
	 *
	 *	@return		int[3] array with r, g and b values
	 *
	 *********************************************************************************/		
	public int[] HSL2RGB(float h, float s, float l) {
		int[] rgb = new int[3];

		float R, G, B;

		float var_1, var_2;
		
		h = h / 360.0f;
		s = s / 100.0f;
		l = l / 100.0f;
		
		if ( s == 0 ) {
			// HSL from 0 to 1
			R = l * 255;
			G = l * 255;
			B = l * 255;
		} else {
			if ( l < 0.5f ) {
				var_2 = (l * ( 1.0f + s ));
			} else {
				float a = l + s;
				float b = s * l;
				var_2 = (float)(( l + s ) - ( s * l ));
			}

			var_1 = 2.0f * l - var_2;

			R = 255 * HUE2RGB( var_1, var_2, h + ( 1.0f / 3.0f ) );
			G = 255 * HUE2RGB( var_1, var_2, h );
			B = 255 * HUE2RGB( var_1, var_2, h - ( 1.0f / 3.0f ) );
		
		}	

		rgb[0] = (int)R;
		rgb[1] = (int)G;
		rgb[2] = (int)B;

		return rgb;
	} // HSL2RGB()


	/*********************************************************************************
	 *
	 *	Convert a Processing RGB color to a hexadecimal color string<br>
	 *	Example: String hexValue = colorHarmony.P52Hex(color(255, 255, 255));<br>
	 *	Result: "FFFFFF"
	 *
	 *	@param P5color	Processing color
	 *
	 *	@return			Hexadecimal color string (6 characters)
	 *
	 *********************************************************************************/		
	public String P52Hex(int P5color) {
		int r = (int)parent.red(P5color);
		int g = (int)parent.green(P5color);
		int b = (int)parent.blue(P5color);
		return String.format("%02X%02X%02X", r, g, b);
	} // P52Hex()	


	/*********************************************************************************
	 *
	 *	Convert a RGB color to a hexadecimal color string<br>
	 *	Example: String hexColor = colorHarmony.RBG2Hex(255, 255, 255);<br>
	 *	Result: "FFFFFF"
	 *
	 *	@param r 	Red value   (0..255)
	 *	@param g 	Green value (0..255)
	 *	@param b 	Blue value  (0..255)
	 *
	 *	@return		Hexadecimal color string (6 characters)
	 *
	 *********************************************************************************/		
	public String RGB2Hex(int r, int g, int b) {
		return String.format("%02X%02X%02X", r, g, b);
	} // RGB2Hex()
	

	/*********************************************************************************
	 *
	 *	Convert RGB color values to HSL values<br>
	 *	Example: float hsl = colorHarmony.RGB2HSL(255, 255, 255);<br>
	 *	Result: (0.0, 0.0, 100.0)
	 *
	 *	@param r 	Red value   (0..255)
	 *	@param g 	Green value (0..255)
	 *	@param b 	Blue value  (0..255)
	 *
	 *	@return		float[3] array with h, s and l values
	 *
	 *********************************************************************************/		
	public float[] RGB2HSL(int r, int g, int b) {

		float[] hsl     = new float[3];
		float[] rgb     = new float[3];
		float[] rgbSort = new float[3];
		
		// RGB from 0-255 TO 0-1
		float fR = r / 255f;
		float fG = g / 255f;
		float fB = b / 255f;
		
		// Sort the array for finding min and max values
		rgbSort[0] = fR;
		rgbSort[1] = fG;
		rgbSort[2] = fB;
		Arrays.sort(rgbSort);

		// Min value of RGB
		float varMin = rgbSort[0];
		// Max value of RGB
		float varMax = rgbSort[2];
		// Delta RGB value
		float deltaMax = varMax - varMin;
		
		hsl[0] = 0;
		hsl[1] = 0;
		hsl[2] = ( varMax + varMin ) / 2;
		
		if ( deltaMax == 0 ) {
			// This is a gray, no chroma
		   hsl[0] = 0;
		   hsl[1] = 0;
		} else {
			// Chomatic data
			if ( hsl[2] < 0.5f )
				hsl[1] = deltaMax / ( varMax + varMin );
			else
				hsl[1] = deltaMax / ( 2f - varMax - varMin );

		   float del_R = ( ( ( varMax - fR ) / 6f ) + ( deltaMax / 2f ) ) / deltaMax;
		   float del_G = ( ( ( varMax - fG ) / 6f ) + ( deltaMax / 2f ) ) / deltaMax;
		   float del_B = ( ( ( varMax - fB ) / 6f ) + ( deltaMax / 2f ) ) / deltaMax;

			if ( fR == varMax ) hsl[0] = del_B - del_G;

			if ( fG == varMax ) hsl[0] = ( 1.0f / 3.0f ) + del_R - del_B;

			if ( fB == varMax ) hsl[0] = ( 2.0f / 3.0f ) + del_G - del_R;

			if ( hsl[0] < 0 ) hsl[0] += 1;
			if ( hsl[0] > 1 ) hsl[0] -= 1;
		}
		
		hsl[0] = hsl[0] * 360;
		hsl[1] = hsl[1] * 100;
		hsl[2] = hsl[2] * 100;
		
		return hsl;
	} // RGB2HSL()


	/*********************************************************************************
	 *
	 *	INTERNAL: used by HSL2RGB
	 *
	 *********************************************************************************/			
	float HUE2RGB(float temp1, float temp2, float temp3) {
		if ( temp3 < 0.0f ) temp3 += 1;
		if ( temp3 > 1.0f ) temp3 -= 1;
		if ( ( 6.0f * temp3 ) < 1.0f ) return ( temp1 + ( temp2 - temp1 ) * 6.0f * temp3 );
		if ( ( 2.0f * temp3 ) < 1.0f ) return ( temp2 );
		if ( ( 3.0f * temp3 ) < 2.0f ) return ( temp1 + ( temp2 - temp1 ) * ( ( 2.0f / 3.0f ) - temp3 ) * 6.0f );
		return ( temp1 );
	} // HUE2RGB()
} // ColorConvertor