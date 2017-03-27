/*****************************************************************************
 *
 *  PALETTE CLASS FOR DISPLAYING THE PALETTES
 *
 *****************************************************************************/

// ARRAY WITH THE FOUR PALETTES
ArrayList<Palette> palettes = new ArrayList<Palette>();


/*****************************************************************************
 *
 *  PALETTE CLASS
 *
 *****************************************************************************/
class Palette {
  int      xPos, yPos;
  int      paletteType;
  String   title;
  int[]    colors = new int[8];
  int[]    rgb    = new int[3];


  /***************************************************************************
   *
   *  CONSTRUCTOR
   *
   ***************************************************************************/
  Palette(int _xPos, int _yPos, int _paletteType, String _title) {
    xPos        = _xPos;
    yPos        = _yPos;
    paletteType = _paletteType;
    title       = _title;
    loadColors();
  } // Palette()


  /***************************************************************************
   *
   *  LOAD THE COLORS FOR THE FOUR PALETTES
   *
   ***************************************************************************/
  void loadColors() {
    switch (paletteType) {
    case TYPE_MONOCHROMATIC:
      colors = colorHarmony.Monochromatic(baseColorHex);
      break;
    case TYPE_ANALOGOUS:
      colors = colorHarmony.Analogous(baseColorHex);
      break;
    case TYPE_COMPLEMENTARY:
      colors = colorHarmony.Complementary(baseColorHex);
      break;
    case TYPE_TRIADS:
      colors = colorHarmony.Triads(baseColorHex);
      break;
    }
  } // loadColors()


  /***************************************************************************
   *
   *  DISPLAY THIS PALETTE
   *
   ***************************************************************************/
  void display() {
    // DISPLAY THE TITLE OF THE PALETTE
    showTitle();
    // DISPLAY THE 8 SWATCHES
    showSwatches();
  } // display()


  /***************************************************************************
   *
   *  DISPLAY THE TITLE OF THE PALETTE
   *
   ***************************************************************************/
  void showTitle() {
    pushStyle();
    textAlign(LEFT);
    textSize(16);
    fill(0);
    text(title, xPos, yPos + 35);
    popStyle();
  } // showTitle()


  /***************************************************************************
   *
   *  DISPLAY THE 8 SWATCHES
   *
   ***************************************************************************/
  void showSwatches() {
    pushStyle();
    int x2 = xPos;
    int y2 = yPos + titleHeight;
    for (int i = 0; i < 8; i++) {
      if (i == 4) { 
        y2 += swatchSize + swatchSpacing; 
        x2 = xPos;
      }
      fill(colors[i]);
      stroke(200);
      rect(x2, y2, swatchSize, swatchSize);
      if (brightness(colors[i]) > 160)
        fill(0);
      else
        fill(255);
      textAlign(LEFT);        
      textSize(13);
      String hexCode = colorHarmony.P52Hex(colors[i]);
      float tw = textWidth("#" + hexCode);
      text("#" + hexCode, x2 + (swatchSize - tw)/2, y2 + titleHeight + 7);
      x2 += swatchSize + swatchSpacing;
    } // for (int i = 0; i < 8; i++)
    popStyle();
  } // showSwatches()
} // Palette