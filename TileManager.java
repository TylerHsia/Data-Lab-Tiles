//Tyler Hsia
//Data structures D block
//TileManager assists provides logic for the other three files to make a gui
//and do the required actions with drawn tiles 

import java.util.*;
import java.awt.*;

public class TileManager {
    private ArrayList<Tile> myTiles = new ArrayList<Tile>();
    // This constructor is called every time a new tile manager object is created.
    // Initially your manager is not storing any tiles.
    public TileManager() {
        // implement
    }

    // In this method you should add the given tile to the end of your tile
    // manager's list of tiles.
    public void addTile(Tile rect) {
        myTiles.add(rect);
    }

    // This method should cause all of the tiles in the tile manager to draw
    // themselves on the screen using the given graphical pen. You do not need to do
    // this yourself directly by calling methods on the Graphics object; each Tile
    // object already has a draw method that it can use to draw itself. Draw the
    // tiles from bottom (start) to top (end) of your manager's list.
    // Recall that in order to refer to type Graphics, you must import java.awt.*;
    // in your code.
    public void drawAll(Graphics g) {
        for(int i = 0; i < myTiles.size(); i++){
            myTiles.get(i).draw(g);
        }
    }

    // Called when the user left-clicks. It passes you the x/y coordinates the user
    // clicked. If these coordinates touch any tiles, you should move the topmost of
    // these tiles to the very top (end) of the list.
    public void raise(int x, int y) {
        int index = TopTouchIndex(x, y);
        if(index != -1){
            Tile tileAtIndex = myTiles.get(index);
            myTiles.add(tileAtIndex);
            myTiles.remove(index);
        } 
    }

    // Called when the user Shift-left-clicks. If these coordinates touch any tiles,
    // you should move the topmost of these tiles to the very bottom (beginning) of
    // the list.
    public void lower(int x, int y) {
        int index = TopTouchIndex(x, y);
        if(index != -1){
            Tile tileAtIndex = myTiles.get(index);
            myTiles.add(0, tileAtIndex);
            myTiles.remove(index + 1);
        } 
    }

    // Called when the user right-clicks. If these coordinates touch any tiles, you
    // should delete the topmost of these tiles from the list.
    public void delete(int x, int y) {
        int index = TopTouchIndex(x, y);
        if(index != -1){
            myTiles.remove(index);
        } 
    }

    // Called when the user Shift-right-clicks. If these coordinates touch any
    // tiles, you should delete all such tiles from the list.
    public void deleteAll(int x, int y) {
        int index = TopTouchIndex(x, y);
        while(index != -1){
            myTiles.remove(index);
            index = TopTouchIndex(x, y);
            
        }
    }

    // Called when the user types S. This method should perform two actions: (1)
    // reordering the tiles in the list into a random order, and (2) moving every
    // tile on the screen to a new random x/y pixel position. The random position
    // should be such that the square's top-left x/y position is non-negative and
    // also such that every pixel of the tile is within the passed width and height.
    // For example, if the width passed is 300 and the height is 200, a tile of size
    // 20x10 must be moved to a random position such that its top-left x/y position
    // is between (0, 0) and (280, 190).
    // You can use the built-in Java method Collections.shuffle to randomly
    // rearrange the elements of your list (step 1).
    public void shuffle(int width, int height) {
        //shuffle order of array
        Collections.shuffle(myTiles);

        //shuffle position of tiles 
        int windowWidth = 300;
        int windowHeight = 300;
        for(int i = 0; i < myTiles.size(); i++){
            Tile current = myTiles.get(i);
            //want x between 0 and window width - tile width 
            current.setX((int) (Math.random() * (windowWidth - current.getWidth() + 1)));
            current.setY((int) (Math.random() * (windowHeight - current.getHeight() + 1)));
            
        }
    }

    //returns the index of the top tile for a given coordinate 
    private int TopTouchIndex(int x, int y){
        // implement 
        for(int i = myTiles.size() - 1; i >= 0; i--){
            Tile current = myTiles.get(i);
            //x bounds
            if(current.getX() < x && x < current.getX() + current.getWidth()){
                //y bounds 
                if(current.getY() < y && y < current.getY() + current.getHeight()){
                    return i;                        
                }
            }
            
        }
        return -1;
    }
}
