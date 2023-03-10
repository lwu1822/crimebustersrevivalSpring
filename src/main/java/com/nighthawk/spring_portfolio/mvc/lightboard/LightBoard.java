package com.nighthawk.spring_portfolio.mvc.lightboard;

import lombok.Data;

@Data  // Annotations to simplify writing code (ie constructors, setters)
public class LightBoard {
    private Light[][] lights;

    /* Initialize LightBoard and Lights */
    public LightBoard(int numRows, int numCols) {
        this.lights = new Light[numRows][numCols];
        // 2D array nested loops, used for initialization
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                lights[row][col] = new Light();  // each cell needs to be constructed
            }
        }
    }

    /* Output is intended for API key/values */
    public String toString() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // data
                "{" + 
                "\"row\": " + row + "," +
                "\"column\": " + col + "," +
                "\"light\": " + lights[row][col] +   // extract toString data
                "}," ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 1) + "]";
		return outString;
    }

    /* Output is intended for Terminal, effects added to output */
    public String toTerminal() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // reset
                "\033[m" +
                
                // color
                // IMPORTANT: [38;2 = set foreground RGB color, see
                // https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
                "\033[38;2;" + 
                lights[row][col].getRed() + ";" +  // set color using getters
                lights[row][col].getGreen() + ";" +
                lights[row][col].getBlue() + ";" +
                lights[row][col].getEffect() + "m" +
                // data, extract custom getters
                "{" +
                "\"" + "RGB\": " + "\"" + lights[row][col].getRGB() + "\"" +
                "," +
                "\"" + "Effect\": " + "\"" + lights[row][col].getEffectTitle() + "\"" +
                "," +
                "\"" + "On\": " + "\"" + lights[row][col].getOn() + "\"" +
                "}," +
                // newline
                "\n" ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 2) + "\033[m" + "]";
		return outString;
    }

    /* Output is intended for Terminal, draws color palette */
    public String toColorPalette() {
        // block sizes
        final int ROWS = 5;
        final int COLS = 10;

        // Build large string for entire color palette
        String outString = "";
        // find each row 
        for (int row = 0; row < lights.length; row++) {
            // repeat each row for block size
            for (int i = 0; i < ROWS; i++) {
                // find each column
                for (int col = 0; col < lights[row].length; col++) {
                    // repeat each column for block size
                    for (int j = 0; j < COLS; j++) {
                        // print single character, except at midpoint print color code
                        // IMPORTANT: see how r:
                        // http://twistedoakstudios.com/blog/Post5273_how-to-read-nested-ternary-operators
                        String c = (i == (int) (ROWS / 2) && j == (int) (COLS / 2) && lights[row][col].getOn() == false)
                            ? " ".repeat(lights[row][col].getRGB().length())
                            : (i == (int) (ROWS / 2) && j == (int) (COLS / 2) ) 
                            ? "\033[48;2;255;0;0m" + lights[row][col].getRGB()
                            : (j == (int) (COLS / 2))  // nested ternary
                            // IMPORTANT: .repeat(#): Repeat str (ex: " ") # t
                            ? " ".repeat(lights[row][col].getRGB().length())
                            : " ";

                        if (lights[row][col].getOn() == true) {
                                    outString += 
                                    // reset
                                    "\033[m" +
                                    
                                    // color
                                    "\033[38;2;" + 
                                    lights[row][col].getRed() + ";" +
                                    lights[row][col].getGreen() + ";" +
                                    lights[row][col].getBlue() + ";" +
                                    "7m" +

                                    // color code or blank character
                                    //"\033[48;2;255;255;255;7m" +
                                    c +

                                    // reset
                                    "\033[m";
                                        
                            /* 
                            if (lights[row][col].getRed() + lights[row][col].getGreen() + lights[row][col].getBlue() >= 383) {
                                outString += 
                                // reset
                                "\033[m" +
                                
                                // color
                                "\033[38;2;" + 
                                lights[row][col].getRed() + ";" +
                                lights[row][col].getGreen() + ";" +
                                lights[row][col].getBlue() + ";" +
                                "7m" +

                                // color code or blank character
                                "\033[48;2;0;0;0;7m" +
                                c +

                                // reset
                                "\033[m";
                                } else {
                                    outString += 
                                    // reset
                                    "\033[m" +
                                    
                                    // color
                                    "\033[38;2;" + 
                                    lights[row][col].getRed() + ";" +
                                    lights[row][col].getGreen() + ";" +
                                    lights[row][col].getBlue() + ";" +
                                    "7m" +

                                    // color code or blank character
                                    "\033[48;2;255;255;255;7m" +
                                    c +

                                    // reset
                                    "\033[m";
                                        }
                           */ 
                        } else {
                            outString +=  
                            "\033[m" +
                            // color
                            "\033[38;2;" + 
                            0 + ";" +
                            0 + ";" +
                            0 + ";" +
                            "7m" +

                            // color code or blank character
                            c +

                            // reset
                            "\033[m";
                        }
                        
                    }
                }
                outString += "\n";
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString += "\033[m";
		return outString;
    }
    
    static public void main(String[] args) {
        // create and display LightBoard
        LightBoard lightBoard = new LightBoard(2, 2);
        System.out.println(lightBoard);  // use toString() method
        System.out.println(lightBoard.toTerminal());
        System.out.println(lightBoard.toColorPalette());
    }   

}
