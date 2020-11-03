//Tyler Hsia
//Data structures D block
//TileManager assists provides logic for the other three files to make a gui
//and do the required actions with drawn tiles 

import java.util.*;
import java.awt.*;

public class TileManager {
    private ArrayList<Tile> myTiles; 
    // TileManager constructor 
    public TileManager() {
        myTiles = new ArrayList<Tile>();
    }

    // adds a new tile to the list of tiles at the end 
    public void addTile(Tile rect) {
        myTiles.add(rect);
    }

    // draws all tiles from beginning of index to end 
    public void drawAll(Graphics g) {
        for(int i = 0; i < myTiles.size(); i++){
            myTiles.get(i).draw(g);
        }
    }

    // puts a clicked tile to the end of the list, raising it to the top
    //called on left click 
    public void raise(int x, int y) {
        int index = TopTouchIndex(x, y);
        if(index != -1){
            Tile tileAtIndex = myTiles.get(index);
            myTiles.add(tileAtIndex);
            myTiles.remove(index);
        } 
    }

    // puts the tile to the beginning of the list, lowering it to the bottom  
    //Called on shift-left click  
    public void lower(int x, int y) {
        int index = TopTouchIndex(x, y);
        if(index != -1){
            Tile tileAtIndex = myTiles.get(index);
            myTiles.add(0, tileAtIndex);
            myTiles.remove(index + 1);
        } 
    }

    // deletes right - clicked tile 
    public void delete(int x, int y) {
        int index = TopTouchIndex(x, y);
        if(index != -1){
            myTiles.remove(index);
        } 
    }

    // Called on Shift-right-clicks
    // all tiles that are on the point that was selected are removed 
    public void deleteAll(int x, int y) {
        int index = TopTouchIndex(x, y);
        while(index != -1){
            myTiles.remove(index);
            index = TopTouchIndex(x, y);
        }
    }

    // Called when the user types S. 
    // reorders tiles in random order in the list
    // this effectively randomizes the z - layer 
    // also randomizes every tile's position 
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
        for(int i = 0; i < myTiles.size(); i++){
            Tile current = myTiles.get(i);
            //random x where tile is fully on screen
            current.setX((int) (Math.random() * (width - current.getWidth() + 1)));
            //random y where tile is fully on screen
            current.setY((int) (Math.random() * (height - current.getHeight() + 1)));
            
        }
    }

    //returns the index of the top tile for a given coordinate 
    private int TopTouchIndex(int x, int y){
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
