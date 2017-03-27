/*****************************************************************************
 *
 *  SHAPES - an example for the ColorHarmony Processing library
 * 
 *  This example draws shapes filled with a color, picked from a
 *  harmonized palette
 *
 *  Used methods:
 *  - ColorHarmony.GetBaseColor()
 *  - ColorHarmony.GetCurrentTypeText()
 *  - ColorHarmony.GetRandomPalette()
 *  - ColorHarmony.printVersion()
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
  size(600, 600);

  // WRITE THE VERSION OF THE LIBRARY TO THE CONSOLE
  colorHarmony.printVersion();

  // LOAD THE COLOR PALETTE
  colors = loadPalette();

  // SOME SETTINGS
  frameRate(6);
  background(234);
  strokeWeight(1);
  stroke(0);
} // setup()


/*****************************************************************************
 *
 *  DRAW
 *
 *****************************************************************************/
void draw() {
  // PICK A RANDOM COLOR FROM THE PALETTE
  fill(colors[(int)random(8)]);

  // RANDOM SHAPE AT A RANDOM POSITION
  int w = (int)random(10, 100);
  int x = (int)random(0, width - w);
  int y = (int)random(0, height - w);
  int shape = (int)random(2);

  if (shape == 0)
    rect(x, y, w, w);
  else
    ellipse(x, y, w, w);
} // draw()


/*****************************************************************************
 *
 *  LOAD A PALETTE
 *
 *****************************************************************************/
color[] loadPalette() {
  // GENERATE A RANDOM PALETTE
  colors = colorHarmony.GetRandomPalette();
  println("Palette type: " + colorHarmony.GetCurrentTypeText() + ", base color: #" + colorHarmony.GetBaseColor());    
  return colors;
} // loadPalette()


/*****************************************************************************
 *
 *  MOUSE HANDLER
 *
 *****************************************************************************/
void mousePressed() {
  // CLEAR CANVAS
  background(234);

  // LOAD A NEW PALETTE
  loadPalette();
} // mousePressed()