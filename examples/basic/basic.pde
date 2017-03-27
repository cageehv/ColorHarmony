/*****************************************************************************
 *
 *  BASIC - a basic example for the ColorHarmony Processing library
 * 
 *  This example colors the background with colors, picked from a
 *  harmonized palette
 *
 *  Used methods:
 *  - ColorHarmony.GetBaseColor()
 *  - ColorHarmony.GetCurrentTypeText()
 *  - ColorHarmony.GetRandomPalette()
 *  - ColorHarmony.P52Hex()
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

// THE HARMONIZED PALETTE
color[] colors = new color[8];


/*****************************************************************************
 *
 *  SETUP
 *
 *****************************************************************************/
void setup() {
  frameRate(2);  
  size(400, 400);

  // GET A MONOCHROMATIC PALETTE, BASED ON A RANDOM BASE COLOR
  colors = colorHarmony.Monochromatic();
  println("Palette type: " + colorHarmony.GetCurrentTypeText() + ", base color: #" + colorHarmony.GetBaseColor());

  textAlign(CENTER);
  textSize(30);
} // setup()


/*****************************************************************************
 *
 *  DRAW
 *
 *****************************************************************************/
void draw() { 
  // PICK A COLOR FROM THE HARMONIZED PALETTE
  color c = colors[(int)random(8)];

  background(c);
  if (brightness(c) > 127) fill(0); 
  else fill(255);
  String hex = colorHarmony.P52Hex(c);  
  text("#" + hex, width>>1, height>>1);
} // draw()


/*****************************************************************************
 *
 *  MOUSE HANDLER
 *
 *****************************************************************************/
void mousePressed() {
  colors = colorHarmony.GetRandomPalette();
  println("Palette type: " + colorHarmony.GetCurrentTypeText() + ", base color: #" + colorHarmony.GetBaseColor());
} // mousePressed()