package BrickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
public int map[][];
public int brickwidth;
public int brickheight;
public MapGenerator(int rows,int col) {
     map = new int[rows][col];
     for(int i = 0; i<map.length;i++) {
         for(int j = 0;j<map[0].length;j++) {
        	 map[i][j]=1;
         }
      }
   
     brickheight = 150/rows;
     brickwidth = 540/col;
}
public void draw(Graphics2D g) {
	for(int i = 0; i<map.length;i++) {
        for(int j = 0;j<map[0].length;j++) {
        	if(map[i][j]>0) {
        		g.setColor(Color.black);
        		g.fillRect(j*brickwidth + 80, i*brickheight+50, brickwidth, brickheight);
        		
        		g.setStroke(new BasicStroke(3));
        		g.setColor(Color.white);
        		g.drawRect(j*brickwidth + 80, i*brickheight+50, brickwidth, brickheight);
        	}
        }
        }
	}
public void setBrickValue(int value,int row,int col) {
	map[row][col] =value;
}
}