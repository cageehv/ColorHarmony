/*****************************************************************************
 *
 *  PALETTES - an example for the ColorHarmony Processing library
 * 
 *  This example will show the generated harmonized palettes for the four
 *  different harmonizing types:
 *  - MONOCHROMATIC
 *  - ANALOGOUS
 *  - COMPLEMENTARY
 *  - TRIADS
 *
 *  Used methods:
 *  - ColorHarmony.Analogous()
 *  - ColorHarmony.Complementary()
 *  - ColorHarmony.Monochromatic()
 *  - ColorHarmony.P52Hex()
 *  - ColorHarmony.printVersion() 
 *  - ColorHarmony.RandomHexColor()
 *  - ColorHarmony.Triads()
 *
 *  Interaction:
 *  Click with your mouse on the sketch to select a new random base color
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

// CREATE COLORHARMONY INSTANCE (DEFAULT CONSTRUCTOR)
ColorHarmony colorHarmony = new ColorHarmony(this);

// CREATE COLORHARMONY INSTANCE
// WITH min AND max VALUES FOR SATURATION AND LUMINOSITY
// DEFAULTS: minS = 10, maxS = 60, minL = 20, maxL = 60
// SATURATION:  0..100
// LUMOSITY:    0..100
//ColorHarmony colorHarmony = new ColorHarmony(this, 10, 60, 20, 60);

// HARMONIZING TYPES
final static int TYPE_MONOCHROMATIC = 0;
final static int TYPE_ANALOGOUS     = 1;
final static int TYPE_COMPLEMENTARY = 2;
final static int TYPE_TRIADS        = 3;

// LAYOUT FOR SHOWING THE PALETTES
int swatchSize     = 80;
int swatchSpacing  = 10;
int headerHeight   = 45;
int titleHeight    = 40;
int paletteSpacing = 30;
int paletteWidth   = 4*swatchSize + 3*swatchSpacing;
int paletteHeight  = titleHeight  + 2*swatchSize + swatchSpacing; 

// COLOR VALUES FOR THE DIFFERENT HARMONIZING TYPES
String[] paletteMonochromatic = new String[8];
String[] paletteAnalogous     = new String[8];
String[] paletteComplementary = new String[8];
String[] paletteTriads        = new String[8];

// BASE COLOR FOR THE GENERATED PALETTES
String baseColorHex = "FF00FF";


/*****************************************************************************
 *
 *  SETUP
 *
 *****************************************************************************/
void setup() {
  // CANVAS WIDTH AND HEIGHT
  int w = 3*paletteSpacing + 2 * paletteWidth;
  int h = headerHeight + 2*paletteHeight + paletteSpacing;

  // FOR PROCESSING v2.x
  //size(w, h);

  // FOR PROCESSING v3.x
  surface.setSize(w, h);

  // WRITE THE VERSION OF THE LIBRARY TO THE CONSOLE
  colorHarmony.printVersion();

  if (baseColorHex.equals("")) {
    // GENERATE A RANDOM BASE COLOR
    baseColorHex = colorHarmony.RandomHexColor();
  }

  // CREATE THE FOUR PALETTES
  palettes.add(new Palette(paletteSpacing, headerHeight, TYPE_MONOCHROMATIC, "MONOCHROMATIC"));  
  palettes.add(new Palette(2*paletteSpacing + paletteWidth, headerHeight, TYPE_ANALOGOUS, "ANALOGOUS"));  
  palettes.add(new Palette(paletteSpacing, headerHeight + paletteHeight, TYPE_COMPLEMENTARY, "COMPLEMENTARY"));  
  palettes.add(new Palette(2*paletteSpacing + paletteWidth, headerHeight + paletteHeight, TYPE_TRIADS, "TRIADS"));

  // DEFAULT LAYOUT SETTINGS
  textAlign(CENTER);
  textSize(20);
  fill(0);
} // setup()


/*****************************************************************************
 *
 *  DRAW
 *
 *****************************************************************************/
void draw() {
  background(255);

  // DISPLAY THE HEADER
  text("ColorHarmony - Processing Library (base color: #" + baseColorHex + ")", width>>1, 30);

  // DISPLAY THE FOUR PALETTES
  for (int i = 0; i < palettes.size(); i++) palettes.get(i).display();
} // draw()


/*****************************************************************************
 *
 *  UPDATE THE PALETTES BASED ON A NEW (RANDOM) BASE COLOR
 *
 *****************************************************************************/
void updatePalettes() {
  // GET A RANDOM BASE COLOR
  baseColorHex = colorHarmony.RandomHexColor();
  // CHANGE THE PALETTES BASED ON THE NEW BASE COLOR
  for (int i = 0; i < palettes.size(); i++) palettes.get(i).loadColors();
} // updatePalettes()


/*****************************************************************************
 *
 *  MOUSE HANDLER
 *
 *****************************************************************************/
void mousePressed() {
  // UPDATE THE PALETTES BASED ON A NEW (RANDOM) BASE COLOR
  updatePalettes();
} // mousePressed()