package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	
	GamePanel gp;
	Tile[] tile;
	public static final String filePath = "/maps/map02.txt";
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		
		loadMap(filePath);
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/white.png"));
			
			
		}catch(IOException e){
			
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath) {

        int row = 0;
        int col = 0;

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
           
            while ((line = br.readLine()) != null && row < gp.maxScreenRow) {

                String numbers[] = line.split(" ");
                col = 0;

                while(col < gp.maxScreenCol && col < numbers.length) {
                    int num = Integer.parseInt(numbers[col]);
                    System.out.println(num);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxScreenCol) {
                    row++;
                }

            }
            
            br.close();
            
        } catch (Exception e) {
            System.out.println("BIG BUG OVER HERE: " + e);
        }

    }

	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
		
		
		
	}
}
