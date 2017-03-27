/*****************************************************************************
 *
 *  CONVERSIONS - an example for the ColorHarmony Processing library
 * 
 *  This example demonstrates the color conversion functionality of the
 *  library
 *
 *  Used methods:
 *  - ColorHarmony.Decimal2Hex()
 *  - ColorHarmony.Hex2Decimal()
 *  - ColorHarmony.Hex2HSL()
 *  - ColorHarmony.Hex2P5()
 *  - ColorHarmony.Hex2RGB()
 *  - ColorHarmony.HSL2Hex()
 *  - ColorHarmony.HSL2RGB()
 *  - ColorHarmony.P52Hex()
 *  - ColorHarmony.RGB2Hex()
 *  - ColorHarmony.RGB2HSL()
 *
 *  Interaction:
 *  Click with your mouse on the sketch to select a new palette type and
 *  base color
 *
 *  Library page:
 *  http://cagewebdev.com/colorharmony-processing-library/
 *
 *  Author:
 *  Rolf van Gelder
 *  http://rvg.cage.nl, http://cagewebdev.com, info@cagewebdev.com
 *
 *  Release:
 *  03/17/2017
 *
 *  (c) 2017 Rolf van Gelder - CAGE Web Design
 *
 *****************************************************************************/
import com.cage.colorharmony.*;

// CREATE A COLORHARMONY INSTANCE (DEFAULT CONSTRUCTOR)
ColorHarmony colorHarmony = new ColorHarmony(this);

void setup() {
  size(400, 200);
  textAlign(CENTER);
  textSize(18);

  println("Decimal2Hex");
  String hexValue = colorHarmony.Decimal2Hex(4095);
  println("String hexValue = colorHarmony.Decimal2Hex(4095);  => hexValue = " + "\"" + hexValue + "\"");

  println("\nHex2Decimal");
  int decValue = colorHarmony.Hex2Decimal("FFF");
  println("int decValue = colorHarmony.Hex2Decimal(\"FFF\"); => decValue = " + decValue);

  println("\nHex2HSL");
  float[] hsl = colorHarmony.Hex2HSL("FFFFFF");
  //printArray(hsl);
  println("float[] hsl = colorHarmony.Hex2HSL(\"FFFFFF\"); => hsl = (" + hsl[0] + ", " + hsl[1] + ", " +hsl[2] + ")");

  println("\nHex2P5");
  color P5color = colorHarmony.Hex2P5("FFFFFF");
  println("color P5color = colorHarmony.Hex2P5(\"FFFFFF\"); => P5color = " + P5color);

  println("\nHex2RGB");
  int[] rgb = colorHarmony.Hex2RGB("FFFFFF");
  println("int[] rgb = colorHarmony.Hex2RGB(\"FFFFFF\"); => rgb = (" + rgb[0] + ", " + rgb[1] + ", " +rgb[2] + ")");

  println("\nHSL2Hex");
  hexValue = colorHarmony.HSL2Hex(0.0, 0.0, 100.0);
  println("String hexValue = colorHarmony.HSL2Hex(0.0, 0.0, 100.0);  => hexValue = " + "\"" + hexValue + "\"");

  println("\nHSL2RGB");
  rgb = colorHarmony.HSL2RGB(0.0, 0.0, 100.0);
  println("int[] rgb = colorHarmony.HSL2RGB(0.0, 0.0, 100.0); => rgb = (" + rgb[0] + ", " + rgb[1] + ", " +rgb[2] + ")");

  println("\nP52Hex");
  hexValue = colorHarmony.P52Hex(color(255, 255, 255));
  println("String hexValue = colorHarmony.P52Hex(color(255, 255, 255));  => hexValue = " + "\"" + hexValue + "\"");

  println("\nRGB2Hex");
  hexValue = colorHarmony.RGB2Hex(255, 255, 255);
  println("String hexValue = colorHarmony.RGB2Hex(255, 255, 255);  => hexValue = " + "\"" + hexValue + "\"");  

  println("\nRGB2HSL");
  hsl = colorHarmony.RGB2HSL(255, 255, 255);
  println("float[] hsl = colorHarmony.RGB2HSL(255, 255, 255); => hsl = (" + hsl[0] + ", " + hsl[1] + ", " +hsl[2] + ")");
} // setup()

void draw() {
  background(0);
  fill(255);
  text("Look at the console for the results...", width>>1, height>>1);
} // draw()