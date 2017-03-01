import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class assignlatest extends GameEngine {
	public static void main(String args[]) {
		createGame(new assignlatest());
	}
	/* FIX NOTES!
		1. incorperate height() and width() to collisions and spawns	
		2. Collisions need better height and width 
		3. new laser Image
		4. Enemy width and height
	*/
	
	//--------------------------------------------------
	//Difficulty
	//--------------------------------------------------	
	double difficultyLOS=150;
	public void easyMode(){
		difficultyLOS = 150;
		for(int i=0;i<enemyNumber; i++){
			enemyLOS[i] = difficultyLOS;
		}
		
		//lives = 3;
	}
	
	public void hardMode(){
		difficultyLOS = 250;
		for(int i=0;i<enemyNumber; i++){
			enemyLOS[i] = difficultyLOS;
		}
		
		//lives = 1;
	}

	//--------------------------------------------------
	//Characters
	//--------------------------------------------------
	//characterMode = 0 wintersoldier 1 starload 2 deadpool
	int characterMode = 2;
	//--------------------------------------------------
	//Room 
	//--------------------------------------------------
	
	Image tileset1, tileset2;
	Image floor1, shade, floorShade, wall, food;
	
	
	int roomNumber;
	int wallAmount, maxWalls;
	
	double[] wallPositionX, wallPositionY, wallWidth, wallHeight;	
	
	double wallCenterX, wallCenterY;
	
	public void createWall(int x, double wallX, double wallY, double wallW, double wallH){
		wallPositionX[x] = wallX;
		wallPositionY[x] = wallY;
		wallWidth[x] = wallW;
		wallHeight[x] = wallH;
	}
	
	public void initRoom(){
		tileset1 = loadImage("dg_grounds32.gif");
		tileset2 = loadImage("JapaneseVillage02.png");
		shade = loadImage("shade.png");
		floor1 = subImage(tileset1, 96, 0, 32, 32);
		floorShade = subImage(shade,0,0,32,32);
		wall = subImage(tileset2, 128, 704, 32, 32);
		food = subImage(tileset2,192, 672, 32, 32);
		maxWalls = 20;
		roomNumber = 16;
		wallPositionX = new double[maxWalls];
		wallPositionY = new double[maxWalls];
		wallWidth = new double[maxWalls];
		wallHeight = new double[maxWalls];
		newRoom();
	}
	
	
	//createLaser(ID num, positionX, positionY, laserlength, laserdirection, time on, time off)
	//Stationary Enemy: createEnemy(ID num, posX, posY, width, height, direction(dont face, 4 if blank), direction(dont face, 4 if blank), starting direction, enemy type(1 for startionary))
	//Moving Enemy: createEnemy(ID num, posX, posY, width, height, boundy(X or Y depending on start direction), boundy(X or Y depending on start direction), starting direction, enemy type(0 for moving))
	
	
	public void newRoom(){
		if(roomNumber==0){
			wallAmount = 5;
			enemyNumber=1;
			laserAmount = 1;
			ghostAmount = 0;
			createWall(0, 150, 175, 700,250);
			createWall(1, 150, 175, 300, 450);
			createWall(2, 550, 175, 350, 450);
			createWall(3, 100, -35, 350, 110);
			createWall(4, 550, -35, 350, 110);
			createEnemy(0,925, 75, enemyW, enemyH, 75, 650, 2,0);
			createLaser(0, -100, 500, 250,1 ,3,1);
			
		} else if(roomNumber==1){
			wallAmount = 4;
			enemyNumber=0;
			laserAmount = 1;
			ghostAmount = 1;
			createWall(0, 0, 150, 400, 500);
			createWall(1, 400, 150, 50, 700);
			createWall(2, 550, 150, 50, 700);
			createWall(3, 700, 0, 300, 550);
			createLaser(0, 200, -75, 225, 0, 3, 1);
			createGhost(0, 50, 650, 500);
			
		}else if(roomNumber==2){
			wallAmount = 9;
			enemyNumber= 4;
			laserAmount = 0;
			ghostAmount = 0;
			createWall(0, 400, 415, 200, 10);
			createWall(1, 200, 125, 200, 100);
			createWall(2, 200, 225, 100, 100);
			createWall(3, 200, 425, 100, 100);
			createWall(4, 200, 525, 200, 100);
			createWall(5, 600, 125, 200, 100);
			createWall(6, 700, 225, 100, 100);
			createWall(7, 700, 425, 100, 100);
			createWall(8, 600, 525, 200, 100);
			createEnemy(0, 75, 25, enemyW, enemyH, 75, 925, 1, 0);
			createEnemy(1, 600, 670, enemyW, enemyH, 75, 925, 1, 0);
			createEnemy(2, 75, 725, enemyW, enemyH,125,725, 0,0);
			createEnemy(3, 925, 725, enemyW, enemyH,125,725, 0,0);


		}else if(roomNumber==3){
			wallAmount = 4;
			enemyNumber= 3;
			laserAmount = 4;
			ghostAmount = 0;
			createWall(0, 100, 300,650,200);
			createWall(1, 850, 0, 150,550);
			createWall(2, 0, 700, 1000, 50);
			createWall(3, 0, 490, 100, 10);
			createLaser(0, 150, 500, 200, 0, 3, 1);
			createLaser(1, 350, 500, 200, 0, 3, 1);
			createLaser(2, 550, 500, 200, 0, 3, 1);
			createLaser(3, 750, 500, 200, 0, 3, 1);
			createEnemy(0, 25, 25, enemyW, enemyH,25,700, 1,0);
			createEnemy(1, 300, 125, enemyW, enemyH,25,700, 1,0);
			createEnemy(2, 700, 225, enemyW, enemyH,25,700, 3,0);

		}else if(roomNumber==4){
			wallAmount = 8;
			enemyNumber = 4;
			laserAmount = 5;
			ghostAmount = 0;
			createWall(5, 850, 0, 150, 335);
			createWall(1, 850, 415, 150, 350);
			createWall(2, 550, 75, 200, 600);
			createWall(3, 550, 543, 100, 207);
			createWall(4, 300, 75, 175, 400);
			createWall(0, 0, -50, 350, 385);
			createWall(6, 0, 415, 200, 600);
			createWall(7, 300, 600, 175, 150);
			createLaser(0, 860, 335, 80, 0, 2, 3);
			createLaser(1, 740, 675, 207, 0, 1, 1);
			createLaser(2, 470, 0, 75, 0, 2, 4);
			createLaser(3, 370, 475, 125, 0, 2, 2);
			createLaser(4, 200, 415, 100, 1, 1, 2);
			createEnemy(0, 770, 550, enemyW, enemyH, 0, 750, 0,0);
			createEnemy(1, 600, 0, enemyW, enemyH, 350, 630, 1,0);
			createEnemy(2, 490, 375, enemyW, enemyH, 400,750, 2,0);
			createEnemy(3, 220, 600, enemyW, enemyH, 335,750, 2,0);
			
		}else if(roomNumber == 5){//arthur
			wallAmount = 9;
			enemyNumber = 3;
			laserAmount = 2;
			ghostAmount = 0;
			createWall(0, 275, 325, 450, 100);
			createWall(1, 200, 0, 10, 175);
			createWall(2, 400, 100, 10, 75);
			createWall(3, 600, 0, 10, 175);
			createWall(7, 800, 0, 10, 175);
			createWall(4, 200, 575, 10, 175);
			createWall(5, 400, 575, 10, 75);
			createWall(6, 600, 575, 10, 175);
			createWall(8, 800, 575, 10, 175);
			createEnemy(0, 400-19, 175+5, enemyW, enemyH, 4, 0, 2,1);
			createEnemy(1, 400-19, 575-5-72, enemyW, enemyH, 4, 2, 0,1);
			createEnemy(2, 730, 325, enemyW, enemyH, 180, 570, 0,0);
			createLaser(0, 210, 175, 590, 1, 4, 2);
			createLaser(1, 210, 575, 590, 1, 4, 2);

		}else if(roomNumber == 6){//arthur
			wallAmount = 0;
			enemyNumber = 14;
			laserAmount = 2;
			ghostAmount = 0;
			createLaser(0, 0, 325, 1000, 1, 10, 0);
			createLaser(1, 0, 425, 1000, 1, 10, 0);
			
			createEnemy(0, 114, 240, enemyW, enemyH, 0, 300, 2, 0 );
			createEnemy(1, 214, 210, enemyW, enemyH, 0, 300, 2, 0 );
			createEnemy(2, 314, 180, enemyW, enemyH, 0, 300, 2, 0 );
			createEnemy(3, 414, 150, enemyW, enemyH, 0, 300, 2, 0 );
			
			
			createEnemy(4, 614, 90, enemyW, enemyH, 0, 300, 2, 0 );
			createEnemy(5, 714, 60, enemyW, enemyH, 0, 300, 2, 0 );
			createEnemy(6, 814, 30, enemyW, enemyH, 0, 300, 2, 0 );
		
			createEnemy(7, 114, 460, enemyW, enemyH, 400, 750, 0, 0 );
			createEnemy(8, 214, 490, enemyW, enemyH, 400, 750, 0, 0 );
			createEnemy(9, 314, 520, enemyW, enemyH, 400, 750, 0, 0 );
			createEnemy(10, 414, 550, enemyW, enemyH, 400, 750, 0, 0 );
			
			createEnemy(11, 614, 610, enemyW, enemyH, 400, 750, 0, 0 );
			createEnemy(12, 714, 640, enemyW, enemyH, 400, 750, 0, 0 );
			createEnemy(13, 814, 670, enemyW, enemyH, 400, 750, 0, 0 );

			
		} else if(roomNumber == 7){//arthur
			//EMPTY ROOM 
			wallAmount = 4;
			enemyNumber= 2;
			laserAmount = 20;
			ghostAmount = 0;
			createWall(0, 100, 500, 466, 20);
			createWall(1, 313, 100, 30, 400);
			createWall(2, 343, 240, 566, 20);
			createWall(3, 666, 250, 30, 400);
			
			createEnemy(0, 914, 50, enemyW, enemyH, 50, 700, 2, 0);
			createEnemy(1, 14, 700, enemyW, enemyH, 50, 700, 0, 0);

			createLaser(0, 343+40, 0, 240, 0, 10, 1);
			createLaser(1, 343+40+40, 0, 240, 0, 9, 2);
			createLaser(2, 343+40+40+40, 0, 240, 0, 8, 3);
			createLaser(3, 343+40+40+40+40, 0, 240, 0, 7, 4);
			createLaser(4, 343+40+40+40+40+40, 0, 240, 0, 6, 5);
			createLaser(5, 343+40+40+40+40+40+40, 0, 240, 0, 5, 6);
			createLaser(6, 343+40+40+40+40+40+40+40, 0, 240, 0, 4, 7);
			createLaser(7, 343+40+40+40+40+40+40+40+40, 0, 240, 0, 3, 8);
			createLaser(8, 343+40+40+40+40+40+40+40+40+40, 0, 240, 0, 2, 9);
			createLaser(9, 343+40+40+40+40+40+40+40+40+40+40, 0, 240, 0, 1, 10);

			createLaser(10, 100+40, 520, 240, 0, 1, 10);
			createLaser(11, 100+40+40, 520, 240, 0, 2, 9);
			createLaser(12, 100+40+40+40, 520, 240, 0, 3, 8);
			createLaser(13, 100+40+40+40+40, 520, 240, 0, 4, 7);
			createLaser(14, 100+40+40+40+40+40, 520, 240, 0, 5, 6);
			createLaser(15, 100+40+40+40+40+40+40, 520, 240, 0, 6, 5);
			createLaser(16, 100+40+40+40+40+40+40+40, 520, 240, 0, 7, 4);
			createLaser(17, 100+40+40+40+40+40+40+40+40, 520, 240, 0, 8, 3);
			createLaser(18, 100+40+40+40+40+40+40+40+40+40, 520, 240, 0, 9, 2);
			createLaser(19, 100+40+40+40+40+40+40+40+40+40+40, 520, 240, 0, 10, 1);
		} else if(roomNumber == 8){
			wallAmount = 5;
			enemyNumber = 3;
			laserAmount = 3;
			ghostAmount = 2;
			
			createWall(0, 705, 240, 295, 95);
			createWall(1, 620, 240, 85, 270);
			createWall(2, 530, 440, 90, 70);
			createWall(3, 445, 240, 85, 270);
			createWall(4, 0, 240, 250, 270);
			
			createLaser(0, 445, 0, 240, 0, 1, 3);
			createLaser(1, 750, 0, 240, 0, 1, 2);
			createLaser(2, 250, 240, 195, 1, 1, 4);
			
			createEnemy(0, 60, 545, enemyW, enemyH, 60, 940, 1, 0);
			createEnemy(1, 60, 650, enemyW, enemyH, 60, 940, 1, 0);
			createEnemy(2, 550, 75, enemyW, enemyH, 0, 4, 2, 1);			
			
			createGhost(0, 25, 10, 240);
			createGhost(1, 925, 10, 175);
			
		} else if(roomNumber == 9){
			wallAmount = 5;
			enemyNumber = 3;
			laserAmount = 2;
			ghostAmount = 1;
			
			createWall(0, 170, 0,830,250);
			createWall(1, 170, 250,140,400);
			createWall(2, 440, 350,90,300);
			createWall(3, 530, 350,90,75);
			createWall(4, 620, 350,100,250);
			
			createLaser(0, 525, 250, 100, 0, 1, 1);
			createLaser(1, 0, 100, 170, 1, 1, 2);
			
			createEnemy(0, 110, 650, enemyW, enemyH, 400, 700, 0, 0);
			createEnemy(1, 830, 280, enemyW, enemyH, 0, 4, 1, 1);
			createEnemy(2, 40, 100, enemyW, enemyH, 100, 400, 2, 0);
			
			createGhost(0, 915, 660, 150);
			
			
			
		} else if(roomNumber == 10){// Imran
			wallAmount = 8;
			enemyNumber = 4;
			laserAmount = 2;
			ghostAmount = 0;
			createWall(0, 0, -100, 50, 350);
			createWall(1, 950, -100, 50, 350);
			createWall(2, 0, 150, 250, 150);
			createWall(3, 750, 150, 250, 150);
			createWall(4, 250, 150, 100, 300);
			createWall(5, 650, 150, 100, 300);
			createWall(6, 0, 650, 300, 300);
			createWall(7, 400, 650, 600, 300);
			
			createEnemy(0, 60, 75, enemyW, enemyH, 60, 940, 1, 0);
			createEnemy(1, 940, 550, enemyW, enemyH, 60, 940, 3, 0);
			createEnemy(2, 940, 450, enemyW, enemyH, 60, 940, 3, 0);
			createEnemy(3, 750, 350, enemyW, enemyH, 3, 0, 2, 1);
			createLaser(0, 350, 448, 300, 1, 1, 2);
			createLaser(1, 350, 352, 300, 1, 1, 2);
		}else if(roomNumber == 11){//Imran
			wallAmount = 8;
			enemyNumber = 2;
			laserAmount = 3;
			ghostAmount = 0;
			createWall(2, 550, 100, 150, 550);
			createWall(1, 700, 100, 150, 200);
			createWall(0, 700, 450, 150, 400);
			createWall(4, 200, 450, 250, 200);			
			createWall(3, 400, -100, 50, 650);
			createWall(5, 0, -100, 300, 210);
			createWall(6, 0, -50, 10, 1000);
			createWall(7, 980, -50, 20, 1000);
		
			createEnemy(0, 800, 325, enemyW, enemyH, 0, 2, 3, 1);
			createEnemy(1, 180, 250, enemyW, enemyH, 4, 4, 0, 1);
			createLaser(0, 0, 551, 200, 1, 2, 2);
			createLaser(1, 0, 646, 200, 1, 2, 2);
			createLaser(2, 300, 109, 100, 1, 2, 2);		
		}else if(roomNumber == 12){
			wallAmount = 5;
			enemyNumber = 2;
			laserAmount = 4;
			ghostAmount = 1;
			createWall(0, 750, 0, 150, 650);
			createWall(2, 100, 500, 800, 150);
			createWall(1, 100, 100, 150, 550);
			createWall(3, 350, 0, 300, 150);
			createWall(4, 350, 250, 300, 150);
			createEnemy(0, 275, 425, enemyW, enemyH, 2, 3, 0, 1);
			createGhost(0, 925, 0, 400);
			createLaser(0, 800, 650, 100, 0, 4, 3);
			createLaser(1, 700, 650, 100, 0, 4, 3);
			createLaser(2, 600, 650, 100, 0, 4, 3);
			createLaser(3, 500, 650, 100, 0, 4, 3);
			//createLaser(4, 0, 250, 100, 1, 4, 3);
			//createLaser(5, 0, 350, 100, 1, 4, 3);
			//createLaser(3, 0, 150, 100, 1, 4, 3);
			//createLaser(7, 0, 550, 100, 1, 4, 3);*/
		}else if(roomNumber == 13){
			wallAmount = 6;
			enemyNumber = 3;
			laserAmount = 0;
			ghostAmount = 1;
			createWall(0, 0, 100, 450 ,225);
			createWall(1, 150, 425, 100, 225); 
			createWall(2, 350, 425, 100, 225);
			createWall(3, 550, 100, 100, 225);
			createWall(4, 750, 100, 100, 225);
			createWall(5, 550, 425, 300, 225);
			createEnemy(0, 275, 525, enemyW, enemyH, 1, 3, 0, 1);
			createEnemy(2, 675, 175, enemyW, enemyH, 1, 3, 2, 1);
			createEnemy(1, 800, 340, enemyW, enemyH, 250, 800, 3, 0);
			createGhost(0, 100, 25, 200);
		}else if(roomNumber == 14){
			wallAmount = 6;
			enemyNumber = 3;
			laserAmount = 5;
			ghostAmount = 1;
			createWall(0, 200, 100, 300, 450);
			createWall(1, 0, 650, 1000, 100); 
			createWall(2, 0, 0, 100, 300);
			createWall(3, 500, 100, 400, 150);
			createWall(4, 600, 100, 50, 350);
			createWall(5, 500, 300, 150, 250);
			createLaser(0, 250, 550, 100, 0, 2, 3);
			createLaser(1, 300, 550, 100, 0, 2, 3);
			createLaser(2, 350, 550, 100, 0, 2, 3);
			createLaser(3, 400, 550, 100, 0, 2, 3);
			createLaser(4, 450, 550, 100, 0, 2, 3);
			createEnemy(0, 125, 20, enemyW, enemyH, 0, 3, 2, 1);
			createEnemy(1, 925, 20, enemyW, enemyH, 20, 600, 2, 0);
			createEnemy(2, 850, 550, enemyW, enemyH, 1, 2, 0, 1);
			createGhost(0, 500, 200, 200);
		} else if(roomNumber == 16){
			
			wallAmount = 0;
			enemyNumber = 0;
			laserAmount = 34;
			ghostAmount = 0;
			if (lastroomTimer >= 1) {
			createLaser(0, 0, 75, 1000, 1, 2, 2);
			createLaser(1, 0, 675, 1000, 1, 2, 2);
			createLaser(2, 100, 0, 750, 0, 2, 2);
			createLaser(3, 900, 0, 750, 0, 2, 2);
			} 
			if (lastroomTimer >= 2) {
			createLaser(4, 0, 150, 1000, 1, 2, 2);
			createLaser(5, 0, 600, 1000, 1, 2, 2);
			createLaser(6, 200, 0, 750, 0, 2, 2);
			createLaser(7, 800, 0, 750, 0, 2, 2);
			}
			if (lastroomTimer >= 3) {
			createLaser(8, 0, 225, 1000, 1, 2, 2);
			createLaser(9, 0, 525, 1000, 1, 2, 2);
			createLaser(10, 300, 0, 750, 0, 2, 2);
			createLaser(11, 700, 0, 750, 0, 2, 2);
			}
			if (lastroomTimer >= 4) {
			createLaser(12, 0, 300, 1000, 1, 2, 2);
			createLaser(13, 0, 450, 1000, 1, 2, 2);
			createLaser(14, 400, 0, 750, 0, 2, 2);
			createLaser(15, 600, 0, 750, 0, 2, 2);
			}
			if (lastroomTimer >= 6) {
			createLaser(16, 50, 0, 750, 0, 2, 2);
			createLaser(17, 950, 0, 750, 0, 2, 2);
			createLaser(18, 0, 62.5, 1000, 1, 2, 2);
			createLaser(19, 0, 187.5, 1000, 1, 2, 2);

			createLaser(20, 150, 0, 750, 0, 2, 2);
			createLaser(21, 850, 0, 750, 0, 2, 2);
			createLaser(22, 0, 312.5, 1000, 1, 2, 2);
			createLaser(23, 0, 437.5, 1000, 1, 2, 2);

			createLaser(24, 250, 0, 750, 0, 2, 2);
			createLaser(25, 750, 0, 750, 0, 2, 2);	
			createLaser(26, 0, 562.5, 1000, 1, 2, 2);
			createLaser(27, 0, 687.5, 1000, 1, 2, 2);

			createLaser(28, 350, 0, 750, 0, 2, 2);
			createLaser(29, 650, 0, 750, 0, 2, 2);
			createLaser(30, 0, 375, 1000, 1, 2, 2);

			createLaser(31, 450, 0, 750, 0, 2, 2);
			createLaser(32, 550, 0, 750, 0, 2, 2);
			createLaser(33, 500, 0, 750, 0, 2, 2);
			}

			

		} else {
			wallAmount = 0;
			enemyNumber = 0;
			laserAmount = 0;
			ghostAmount = 0;
		}
	}
	// playerTopEdge, playerBottomEdge, playerRightEdge, playerLeftEdge;
	public void updateRoom(double dt){
		
		for(int i = 0; i<wallAmount; i++){
			double wallLeftEdge = wallPositionX[i];
			double wallRightEdge = wallPositionX[i] + wallWidth[i];
			double wallTopEdge = wallPositionY[i];
			double wallBottomEdge = wallPositionY[i] + wallHeight[i];
			
			if(playerLeftEdge<wallRightEdge && playerRightEdge>wallLeftEdge && playerTopEdge<wallBottomEdge && playerBottomEdge>wallTopEdge){
				double tx = 9999999;
				double ty = 9999999;
				
				if(playerVelocityX<0){
					tx = (playerLeftEdge - wallRightEdge)/playerVelocityX;
				} else if(playerVelocityX>0){
					tx = (playerRightEdge - wallLeftEdge)/playerVelocityX;
				}
				
				if(playerVelocityY<0){
					ty = (playerTopEdge - wallBottomEdge)/playerVelocityY;
				}else if(playerVelocityY>0){
					ty = (playerBottomEdge - wallTopEdge)/playerVelocityY;
				}
				
				if(tx<ty){
					if(playerVelocityX<0){
						playerPositionX = wallRightEdge;//
					} else {
						//Collide with left wall
						playerPositionX = wallLeftEdge - playerWidth;
					}
				} else {
					if(playerVelocityY<0){
						playerPositionY = wallBottomEdge;//
					} else{
						//collide with top wall
						playerPositionY = wallTopEdge-playerHeight;
					}
				}
			}
		}
	}
	
	public void drawRoom(){
		
		int w = width();
		int h = height();
		
	
		//drawWalls
		for(int i = 0; i<wallAmount; i++){
			
			changeColor(black);
			drawSolidRectangle(wallPositionX[i], wallPositionY[i], wallWidth[i], wallHeight[i]);
			
			if (wallHeight[i] > 100) {
				for( int x1 = 0; x1 < wallWidth[i]; x1+=5) {
					drawImage(wall, wallPositionX[i] +x1, wallPositionY[i] + wallHeight[i] -100, 5, 100);
				}
			}
			for(int y = 0; y < wallHeight[i] - 32; y+= 1) {
				drawImage(floorShade, wallPositionX[i]+wallWidth[i] , y + wallPositionY[i] +32, 20, 1);
			}
			
		}

		//display room number
		changeColor(purple);
		 for ( int i = 0; i  < 16; i++) {
		 	if (roomNumber == i) {
		 		drawText( 100, 50, "ROOM" + " " + i, "Chiller", 60);
		 	}
		 }
		 changeColor(red);
		 if (roomNumber == 16) {
		 	drawText( 100, 50, "ROOM 666", "Chiller", 60);
		 }
		
	}

	public void drawBackground(){
		int w = width();
		int h = height();
		int x;
		for (x = 0; x < w; x += 32) {
			for( int y = 0; y < h; y += 32) {
				drawImage(floor1, x, y);
			}
		}
		
	}
	
	//--------------------------------------------------
	//Doors!
	//--------------------------------------------------
	
	double[] doorPositionX, doorPositionY, doorWidth, doorHeight;
	int[] doorRoomNumber, doorOrientation, doorStatus, doorRoomConnection;
	
	int maxDoorNumber;
	
	Image door0open, door1open;
	Image door0closed, door1closed, door2closed, door3closed;
	
	
	public void initDoors(){
		
		door0open = loadImage("door0open.png");
		door0closed = loadImage("door0closed.png");
		door1open = loadImage("door1open.png");
		door1closed = loadImage("door1closed.png");
		door2closed = loadImage("door2closed.png");
		door3closed = loadImage("door3closed.png");
		
		maxDoorNumber = 30;
		//Door
		doorPositionX = new double[maxDoorNumber];
		doorPositionY = new double[maxDoorNumber];
		doorWidth = new double[maxDoorNumber];
		doorHeight = new double[maxDoorNumber];
		doorRoomNumber = new int[maxDoorNumber];
		doorOrientation = new int[maxDoorNumber]; //0 = Top of room; 1 = Right of room; 2 = Bottom of room; 3 = Left of room
		doorStatus = new int[maxDoorNumber]; // 0 = open; 1 = locked
		doorRoomConnection = new int[maxDoorNumber]; //Room number door connects too
		
		
		//createDoor(ID num, room Number of door, positionX, PositionY, width, height, orientation, door status, connected room number)
		//room0
		createDoor(0, 0, 460, 0,85,15,0,1,1);
		//room1
		createDoor(1, 1, 460, 0,85,15,0,1,2);
		createDoor(2, 1, 460, 735,85,15,2,0,0);
		//room2
		createDoor(3, 2, 460,735,85,15,2,0,1);
		createDoor(4, 2, 0, 325, 15, 100, 3, 1, 4);
		createDoor(5, 2, 985, 325, 15, 100, 1, 0, 3);
		//room3
		createDoor(6, 3, 0, 315, 15, 100, 3, 0, 2);
		
		//TAE
		//room4 to 2
		createDoor(7, 4, 985, 325, 15, 100, 1, 0, 2);
		//room4 to 8
		createDoor(8, 4, 0, 325, 15, 100, 3, 0, 8);
		
		
		//room3 to room5
		createDoor(9, 3, 985, 590, 15, 100, 1, 0, 5);//arthur
		//room5 to room3
		createDoor(10, 5, 0, 590, 15, 100, 3, 0, 3);//arthur
		//room5 to room6
		createDoor(11, 5, 985, 325, 15, 100, 1, 0, 6);//arthur
		//room6 to room5
		createDoor(12, 6, 0, 325, 15, 100, 3, 0, 5);//arthur
		//room6 to room7
		createDoor(13, 6, 985, 325, 15, 100, 1, 0, 7);//arthur
		//room7 to room6
		createDoor(14, 7, 0, 325, 15, 100, 3, 0, 6);//arthur
		
		//room2 to room11
		createDoor(15, 2, 875, 0, 85, 15, 0, 0, 11);
		
		//room11
		createDoor(27,11, 875, 735, 85, 15, 2, 0, 2); 
		createDoor(28, 11, 310, 0, 85, 15, 0, 0, 10);
		
		//room10
		createDoor(16, 10, 0, 325, 15,100, 3, 0, 12);
		createDoor(17 ,10, 985, 325,15,100,1,0, 13);
		createDoor(18 ,10, 460, 0,85,15,0,0, 15);
		createDoor(19 ,10, 450, 985, 100, 15, 2, 0, 2);
		createDoor(12, 10, 310, 735, 85, 15, 2, 0, 11); 
		//room 12
		createDoor(20, 12, 985, 325, 15, 100, 1, 0, 10);
		
		//room13
		createDoor(21, 13, 0, 325, 15, 100, 3, 0, 10); 
		createDoor(22, 13, 985, 325, 15, 100, 1, 0, 14);
		
		//room14
		createDoor(23, 14, 0, 325, 15, 100, 3, 0, 13);
		
		//room8 to 4
		createDoor(24, 8, 985, 325, 15, 100, 1, 0, 4);
		//room8 to 9
		createDoor(25, 8, 530, 425, 90, 15, 2, 0, 9);
		//room9 to 8
		createDoor(26, 9, 530, 425, 90, 15, 0, 0, 8);
		
		//room 15
		createDoor(29, 15, 460, 735, 85, 15, 2, 0, 10);
	}
	
	public void updateDoors(double dt){
		//function checks if player has moved through the door 
		for(int i=0; i < maxDoorNumber; i++){
			if(doorRoomNumber[i]==roomNumber){	
				if(doorOrientation[i] == 2){
					if(playerPositionX>doorPositionX[i] && playerPositionX+playerWidth<doorPositionX[i]+doorWidth[i] && playerPositionY+playerHeight > doorPositionY[i] && playerPositionY < doorPositionY[i] - doorHeight[i]){
						if(doorStatus[i] == 0){
							if(doorPositionY[i] > 700){
								playerPositionY = doorHeight[i] + 1;
							} else {
								playerPositionY = doorPositionY[i] + doorHeight[i]+1;
							}
							roomNumber = doorRoomConnection[i];
							roomspawnX[roomNumber] = playerPositionX;
							roomspawnY[roomNumber] = playerPositionY;
							newRoom();
						}
					}
				}
			
				if(doorOrientation[i]==0){
						//Door on Top of screen
					if(playerPositionX>doorPositionX[i] && playerPositionX+playerWidth<doorPositionX[i]+doorWidth[i] && playerPositionY<doorPositionY[i]+doorHeight[i] && playerPositionY + playerHeight > doorPositionY[i]){
						if(doorStatus[i] == 0){
							if(doorPositionY[i] + doorHeight[i]< 50){
								playerPositionY = 750 - playerHeight - doorHeight[i] -1;
							} else {
								playerPositionY = doorPositionY[i]-playerHeight-1;
							}
							roomNumber = doorRoomConnection[i];
							roomspawnX[roomNumber] = playerPositionX;
							roomspawnY[roomNumber] = playerPositionY;
							newRoom();
						}
					}
				}
			
				if(doorOrientation[i] == 3){
					if(playerPositionX<doorPositionX[i]+doorWidth[i] && playerPositionY>doorPositionY[i] && playerPositionY+playerHeight<doorPositionY[i]+doorHeight[i]){
						if(doorStatus[i] == 0){
							roomNumber = doorRoomConnection[i];
							playerPositionX = 1000-doorWidth[i]-playerWidth-1;
							roomspawnX[roomNumber] = playerPositionX;
							roomspawnY[roomNumber] = playerPositionY;
							newRoom();
						}
					}
				}
			
				if(doorOrientation[i] == 1){
					if(playerPositionX+playerWidth>doorPositionX[i] && playerPositionY>doorPositionY[i] && playerPositionY+playerHeight<doorPositionY[i]+doorHeight[i]){
						if(doorStatus[i] == 0){
							roomNumber = doorRoomConnection[i];
							playerPositionX = doorWidth[i]+1;
							roomspawnX[roomNumber] = playerPositionX;
							roomspawnY[roomNumber] = playerPositionY;
							newRoom();
						}
					}
				}
			}
		}		
	}
	
	public void drawDoors(){
		for (int i = 0; i< maxDoorNumber; i++){
			if(doorRoomNumber[i] == roomNumber){
				if(doorStatus[i]==0){
					//changeColor(green);
					if (doorOrientation[i] == 0){
						drawImage(door0open,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
					
					if (doorOrientation[i] == 1){
						drawImage(door1open,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
					
					if (doorOrientation[i] == 2){					
						drawImage(door0open,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
					
					if (doorOrientation[i] == 3){					
						drawImage(door1open,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
					
				}
				
				
				if(doorStatus[i] == 1){
					//changeColor(red);
					if (doorOrientation[i] == 0){
						drawImage(door0closed,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
					
					if (doorOrientation[i] == 1){					
						drawImage(door1closed,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
					
					if( doorOrientation[i] == 2){
						drawImage(door2closed,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
					
					if (doorOrientation[i] == 3){
						drawImage(door3closed,doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
					}
				}
				
				//drawSolidRectangle(doorPositionX[i], doorPositionY[i], doorWidth[i], doorHeight[i]);
				changeColor(black);
			}
		}
	}
	
	public void createDoor(int x, int doorR, double doorX, double doorY, double doorW, double doorH, int doorO, int doorS, int doorRC){
		doorRoomNumber[x] = doorR;
		doorPositionX[x] = doorX;
		doorPositionY[x] = doorY;
		doorWidth[x] = doorW;
		doorHeight[x] = doorH;
		doorOrientation[x] = doorO;
		doorStatus[x] =  doorS;
		doorRoomConnection[x] = doorRC;
	}
	
	//--------------------------------------------------
	//collectibles
	//--------------------------------------------------
	
	int maxCollectNumber;
	double[] collectPositionX, collectPositionY, collectRadius;
	int[] collectRoom, collectStatus, collectType, collectEffect;
	
	Image key, coin;
	AudioClip keyPickup, coinPickup;
	
	public void initCollect(){
		key = loadImage("key.png");
		coin = loadImage("coin7.png");
		keyPickup = loadAudio("key2 pickup.wav");
		coinPickup  = loadAudio("coinsound.wav");
		maxCollectNumber = 37;
		collectPositionX = new double[maxCollectNumber];
		collectPositionY = new double[maxCollectNumber];
		collectRadius = new double[maxCollectNumber];
		collectRoom = new int[maxCollectNumber];
		collectStatus = new int[maxCollectNumber]; //0 = Active 1 = inactive
		collectType = new int[maxCollectNumber]; //0 = Point scorer; 1 = key;2 = food
		collectEffect = new int[maxCollectNumber]; //if collectType is Point score collectEffect point worth//if door unlock number = door that will unlock
		
		//createCollect(ID Num, Room Number, PositionX, PositionY, Radius, Type of collectable, Type of effect)
		
		//Room 0 
		createCollect(0, 0, 500, 550, 30, 0, 5);
		createCollect(1, 0, 50, 30, 30, 0, 5);
		createCollect(2, 0, 950, 30, 30, 1, 0);
		createCollect(36, 0, 500, 600, 30, 2, 0);
		
		//Room 1
		createCollect(3, 1, 850, 550, 20, 0, 5);
		createCollect(4, 1, 950, 730, 20, 1, 1);
		createCollect(5, 1, 20, 30, 20, 0, 20);
		
		//Room 2
		createCollect(6, 2, 500, 370, 35, 0, 1000);
		
		//Room 3
		createCollect(7, 3, 75, 600, 90, 0, 500);
		createCollect(8, 3, 20, 700, 20, 1, 4);
		
		//Room 4
		createCollect(9, 4, 680, 710, 30, 0, 500);

		//Room5 arthur
		createCollect(10, 5, 300, 40, 30, 0, 15);
		createCollect(11, 5, 300, 710, 30, 0, 15);
		createCollect(12, 5, 500, 40, 30, 0, 15);
		createCollect(13, 5, 500, 710, 30, 0, 15);
		createCollect(14, 5, 700, 40, 30, 0, 15);
		createCollect(15, 5, 700, 710, 30, 0, 15);

		//Room6 arthur
		createCollect(16, 6, 900, 375, 40, 0, 50);
		
		//Room7 arthur
		createCollect(17, 7, 500, 375, 40, 0, 50);
		
		
		
		//room13
		createCollect(18, 13, 25, 50, 20, 1, 14);
		createCollect(19, 13, 500, 200, 20, 0, 5);
		createCollect(20, 13, 500, 300, 20, 0, 5);
		createCollect(21, 13, 500, 400, 20, 0, 5);
		createCollect(22, 13, 500, 500, 20, 0, 5);
		
		//room 14
		createCollect(23, 14, 200, 50, 20, 1, 0);
		
		//room 12
		createCollect(24, 12, 425, 450, 40, 0, 20);
		createCollect(25, 12, 425, 200, 20 , 0, 5);
		createCollect(26, 12, 500, 200, 20 , 0, 5);
		createCollect(27, 12, 725, 50, 20, 1, 0);
		createCollect(28, 12, 575, 200, 20, 0, 5);
		
		//room 9 TAE
		createCollect(29, 9, 50, 50, 20, 1, 10); 
		//room 4
		createCollect(30, 4, 250, 700, 30, 0, 300);
		//room 8
		createCollect(31, 8, 930, 200, 30, 0, 350);
		createCollect(32, 8, 30, 200, 30, 0, 350);
		createCollect(33, 8, 540, 660, 30, 0, 350);
		//room 9
		createCollect(34, 9, 910, 540, 30, 0, 300);
		createCollect(35, 9, 940, 280, 30, 0, 500);
		
		//room10
		createCollect(36, 11, 650, 700, 30, 0, 500);

	}
	
	
	public void createCollect(int x, int colRoom, double colX, double colY, double colR, int colT, int colE){
		collectPositionX[x] = colX;
		collectPositionY[x] = colY;
		collectRadius[x] = colR;
		collectRoom[x] = colRoom;
		collectStatus[x] = 0;
		collectType[x] = colT;
		collectEffect[x] = colE;
	}
	
	public void updateCollect(double dt){
		for(int i = 0; i<maxCollectNumber; i++){
			if(collectRoom[i]==roomNumber && collectStatus[i]==0){
				if(distance(playerPositionX+(playerWidth/2), playerPositionY+(playerHeight/2), collectPositionX[i], collectPositionY[i])<collectRadius[i]*2){
					
					if(collectType[i] == 0){
						//collectable is a point scoring collectable
						score+=collectEffect[i];
						if(soundEffectOn == true) {
							playAudio(coinPickup);
						}
					} else if(collectType[i] ==1){
						//collectable is a Key
						doorStatus[collectEffect[i]] = 0;
						if(soundEffectOn == true) {
							playAudio(keyPickup);
						}
					} else if (collectType[i] == 2){
						//collectable is food, hp restore
						if (difficultyLOS == 150 && characterMode == 0){
							lives = 3;//easy mode with wintersoldier
						} else if (difficultyLOS == 150 && characterMode == 1){
							lives = 6;//easy mode with starlord
						} else if (difficultyLOS == 150 && characterMode == 2){
							lives = 4;//easy mode with starlord
						} else { 
							lives = 1;//hard mode
						}
					}
					
					collectStatus[i] = 1;
					
				}
			}
		}
	}
	
	public void drawCollect(){
		for(int i = 0; i<maxCollectNumber; i++){
			if(collectRoom[i] == roomNumber && collectStatus[i] == 0){
				if (collectType[i] == 0){
					changeColor(yellow);
					drawImage(coin, collectPositionX[i] - collectRadius[i], collectPositionY[i] - collectRadius[i], collectRadius[i] *2, collectRadius[i] *2);
				}
				if (collectType[i] == 1){
					changeColor(169,169,169);
					drawImage(key,collectPositionX[i] - collectRadius[i], collectPositionY[i] - collectRadius[i], collectRadius[i] *2, collectRadius[i]*2); 
				}
				if (collectType[i] == 2){
					//changeColor(169,169,169);
					drawImage(food,collectPositionX[i] - collectRadius[i], collectPositionY[i] - collectRadius[i], collectRadius[i] *2, collectRadius[i]*2); 
				}
			}
		}
		changeColor(black);
	}
	
	
	
	//-------------------------------------------
	//PLAYER
	//-------------------------------------------
	
	double playerPositionX, playerPositionY;
	double playerVelocityX, playerVelocityY;
	int playerWidth, playerHeight;
	double playerTopEdge, playerBottomEdge, playerRightEdge, playerLeftEdge;
	
	boolean playerUp, playerDown, playerLeft, playerRight;
	boolean playerAlive;
	
	
	double[] roomspawnX, roomspawnY;

	
	//Animations
	Image playerSpritesheet;
	Image playerDownIdle, playerUpIdle, playerLeftIdle, playerRightIdle;
	Image heart;
	
	Image[] downWalk = new Image[4];
	Image[] upWalk = new Image[4];
	Image[] leftWalk = new Image[4];
	Image[] rightWalk = new Image[4];
	boolean playerDirectionUp, playerDirectionDown, playerDirectionLeft, playerDirectionRight;
	double playerMovementTimer;
	double playerMovementDuration;
	int playerFrame;
	
	//--------------
	
	int score;
	String scoreS;
	int lives;
	
	public void initPlayer(){
		
		roomspawnX = new double[30];
		roomspawnY = new double[30];
		
		if (characterMode == 0 ){playerSpritesheet = loadImage("wintersoldier.png");}
		else if (characterMode == 1 ){playerSpritesheet = loadImage("starlord_mask.png");}
		else if (characterMode == 2 ){playerSpritesheet = loadImage("deadpool.png");}
		
		heart = loadImage("heart.png");

		
		playerWidth = 48;
		playerHeight = 72;
		playerPositionX = 500 - (playerWidth/2);
		playerPositionY = 750 - playerHeight-2;
		roomspawnX[0] = playerPositionX;
		roomspawnY[0] = playerPositionY;
		playerVelocityY = 0;
		playerVelocityX = 0;
		score = 0;
		lives = 3;
		
		playerUp = false;
		playerDown = false;
		playerLeft = false;
		playerRight = false;
		
		int n = 0;
		int y = 0;
		
		playerDownIdle = subImage(playerSpritesheet, 0, 0, 32, 48);
		playerUpIdle = subImage(playerSpritesheet, 0, 144, 32, 48);
		playerRightIdle = subImage(playerSpritesheet, 0, 96, 32, 48);
		playerLeftIdle = subImage(playerSpritesheet, 0, 48, 32, 48);
		playerDirectionUp = false; 
		playerDirectionDown = true; 
		playerDirectionLeft = false;
		playerDirectionRight = false;
		
		playerMovementTimer = 0;
		playerMovementDuration = 0.5;
		
		for( int x = 0; x < 128; x+= 32) {
			downWalk[n] = subImage(playerSpritesheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y += 48;
		
		for( int x = 0; x < 128; x+= 32) {
			leftWalk[n] = subImage(playerSpritesheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y += 48;
		
		for( int x = 0; x < 128; x+= 32) {
			rightWalk[n] = subImage(playerSpritesheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y += 48;
		
		for( int x = 0; x < 128; x+= 32) {
			upWalk[n] = subImage(playerSpritesheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y += 48;
	}
	
	
	public void updatePlayer(double dt){
		
		
		//Keyboard input movement
		if(playerUp) {
			playerVelocityY = -400;
			playerVelocityX = 0;
			playerMovementTimer += dt;
		}
		if(playerRight) {
			playerVelocityX = 400;
			playerVelocityY = 0;
			playerMovementTimer += dt;
		}
		if(playerDown) {
			playerVelocityY = 400;
			playerVelocityX = 0;
			playerMovementTimer += dt;
		}
		if(playerLeft) {
			playerVelocityX = -400;
			playerVelocityY = 0;
			playerMovementTimer += dt;
		}
		
		if(playerMovementTimer >= playerMovementDuration) {
			playerFrame = 0;
		}
		
		playerFrame = getAnimationFrame(playerMovementTimer, playerMovementDuration, 4);
		
		playerPositionY += playerVelocityY*dt;
		playerPositionX += playerVelocityX*dt;
		
		//Collision with walls
		if(playerPositionX+playerWidth>1000){
			playerPositionX = 1000 - playerWidth;
		}
		if(playerPositionX<0){
			playerPositionX = 0;
		}
		if(playerPositionY<0){
			playerPositionY = 0;
		}
		if(playerPositionY+playerHeight>750){
			playerPositionY = 750-playerHeight;
		}
		
		playerTopEdge = playerPositionY;
		playerBottomEdge = playerPositionY + playerHeight;
		playerRightEdge = playerPositionX + playerWidth;
		playerLeftEdge = playerPositionX;
	}
	
	

	
	public int getAnimationFrame(double timer, double duration, int numFrames) {
		int i = (int)floor(((timer % duration) / duration) * numFrames);
		if(i >= numFrames) {
			i = numFrames-1;
		}
		return i;
	}
	
	
	AudioClip deathsound;
	
	public void death(int i) {	
		//System.out.println(lives);
		deathsound = loadAudio("explosion.wav");
		if(soundEffectOn == true) {
			if (laserdeath == false) {
				playAudio(deathsound);
			}
		}
		
		if ( lives >= 1) {
			lives -= 1;
			playerDown = false;
			playerUp = false;
			playerLeft = false;
			playerRight = false;
			playerDirectionUp = false;
			playerDirectionDown = false;		
			playerDirectionLeft = false;
			playerDirectionRight = false;	
			playerVelocityX = 0;
			playerVelocityY = 0;	
			createExplosion(playerPositionX, playerPositionY);	
			for(int j = 0; j < maxGhosts; j++){
			ghostActive[j] = false;
			}
			newRoom();
			state = GameState.loseLife;
		} 
		if (lives <1) {
			lastroomTimer = 0;
			state = GameState.gameOver;
			initRoom();
		}
	}
	
	
	
	public void drawPlayer(){
		saveCurrentTransform();
		translate(playerPositionX, playerPositionY);
		
		
		if((playerDirectionDown) && (playerLeft == false) && (playerRight == false) && (playerUp == false) && (playerDown==false)) {
			drawImage (playerDownIdle, 0, 0, playerWidth, playerHeight);
		}
		if((playerDirectionUp) && (playerLeft == false) && (playerRight == false) && (playerUp == false) && (playerDown==false)) {
			drawImage (playerUpIdle, 0, 0, playerWidth, playerHeight);
		}
		if((playerDirectionRight) && (playerLeft == false) && (playerRight == false) && (playerUp == false) && (playerDown==false)) {
			drawImage (playerRightIdle, 0, 0, playerWidth, playerHeight);
		}
		if((playerDirectionLeft) && (playerLeft == false) && (playerRight == false) && (playerUp == false) && (playerDown==false)) {
			drawImage (playerLeftIdle, 0, 0, playerWidth, playerHeight);
		}
		if(playerDown) {
			drawImage(downWalk[playerFrame], 0, 0, playerWidth, playerHeight);
		}
		if(playerUp) {
			drawImage(upWalk[playerFrame], 0, 0, playerWidth, playerHeight);
		}
		if(playerLeft) {
			drawImage(leftWalk[playerFrame],0, 0, playerWidth, playerHeight);
		}
		if(playerRight) {
			drawImage(rightWalk[playerFrame], 0, 0, playerWidth, playerHeight);
		}
		
		restoreLastTransform();
		
		scoreS = Integer.toString(score);
		changeColor(yellow);
		drawText(playerPositionX + playerWidth + 2, playerPositionY+20, "Score: " + scoreS, "Arial", 20);
		
		if (lives == 1) {
			drawImage(heart, playerPositionX -10, playerPositionY, 15, 15);
		}
		if (lives == 2) {
			drawImage(heart, playerPositionX -10, playerPositionY, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 15, 15, 15);
		}
		if (lives == 3) {
			drawImage(heart, playerPositionX -10, playerPositionY, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 10, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 20, 15, 15);
		}
		if (lives == 4) {
			drawImage(heart, playerPositionX -10 -15, playerPositionY, 15, 15);
			
			drawImage(heart, playerPositionX -10, playerPositionY, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 10, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 20, 15, 15);
		}
		if (lives == 5) {
			drawImage(heart, playerPositionX -10-15, playerPositionY, 15, 15);
			drawImage(heart, playerPositionX -10-15, playerPositionY + 15, 15, 15);
			
			drawImage(heart, playerPositionX -10, playerPositionY, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 10, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 20, 15, 15);
		}
		if (lives == 6) {
			drawImage(heart, playerPositionX -10-15, playerPositionY, 15, 15);
			drawImage(heart, playerPositionX -10-15, playerPositionY + 10, 15, 15);
			drawImage(heart, playerPositionX -10-15, playerPositionY + 20, 15, 15);

			drawImage(heart, playerPositionX -10, playerPositionY, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 10, 15, 15);
			drawImage(heart, playerPositionX -10, playerPositionY + 20, 15, 15);
		}
	}

	public void noPlayerIdleImage() {
			playerDirectionUp = false; 
			playerDirectionDown = false; 
			playerDirectionLeft = false;
			playerDirectionRight = false;
	}
	
	//-------------------------------------------
	//Death Explosion
	//-------------------------------------------
	
	double explosionX, explosionY;
	Image explosionSheet; 
	Image[] explosionFrames = new Image[13];
	
	boolean explosionActive;

	double explosionTimer;
	double explosionDuration;
	
	int explosionFrame;
	
	public void initExplosion() {
		explosionSheet = loadImage("explosion_03_strip13.png");
		explosionActive = false;
		int n = 0;
		for(int x = 0; x < 2496; x += 192) {
			explosionFrames[n] = subImage(explosionSheet, x, 0, 192, 193);
			n++;
		}
	}
	
	public void createExplosion(double x, double y) {

		explosionX = x;
		explosionY = y;


		explosionTimer = 0;
		explosionDuration = 1.0;
		explosionActive = true;
	}
	public void updateExplosion(double dt) {
		if(explosionActive) {

			explosionTimer += dt;

			if(explosionTimer >= explosionDuration) {
				explosionActive = false;
			}
			explosionFrame = getAnimationFrame(explosionTimer, explosionDuration, 13);
		}
	}

	

	
	public void drawExplosion() {

		if(explosionActive) {
			saveCurrentTransform();
			translate(explosionX, explosionY);
			drawImage(explosionFrames[explosionFrame], -59, -51, 150, 150);
			restoreLastTransform();
		}
	}

	//-------------------------------------------
	//ENEMYS
	//-------------------------------------------
	
	
	//Enemys created in newRoom() function
	int  maxEnemyNumber, enemyNumber;
	double enemyTimer, enemyDelay;
	
	double enemyH;
	double enemyW;
	
	double[] enemyPositionX, enemyPositionY, enemyVelocityX, enemyVelocityY, enemyWidth, enemyHeight, enemyLOS, enemyBoundry1, enemyBoundry2;
	int[] enemyDirection, enemyStatus, enemyType;
	
	Image[] enemyFrames = new Image[16];
	Image enemySheet;
	Image flashlightsheet1,flashlightsheet2, flashlight, flashLong;
	
	Image[] enemyDownWalk = new Image[4];
	Image[] enemyUpWalk = new Image[4];
	Image[] enemyLeftWalk = new Image[4];
	Image[] enemyRightWalk = new Image[4];
	
	double[] enemyMovementTimer;
	double[] enemyMovementDuration;
	int[] enemyFrame;
	
	public void initEnemy(){
		enemySheet = loadImage("rhodey.png");
		flashlightsheet1 = loadImage("light.png");
		flashlightsheet2 = loadImage("lightlong.png");
		flashlight = subImage(flashlightsheet1, 0, 0, 64, 64);
		flashLong = subImage(flashlightsheet2, 0, 0, 64, 64);
		
		enemyH = 48*1.5 ;
		enemyW = 32*1.5;
		maxEnemyNumber = 30;
		enemyTimer = 0.0;
		enemyDelay = 4.0;
		enemyPositionX = new double[maxEnemyNumber];
		enemyPositionY = new double[maxEnemyNumber];
		enemyVelocityX = new double[maxEnemyNumber];
		enemyVelocityY = new double[maxEnemyNumber];
		enemyWidth = new double[maxEnemyNumber];
		enemyHeight = new double[maxEnemyNumber];
		enemyLOS = new double[maxEnemyNumber];
		enemyBoundry1 = new double[maxEnemyNumber];
		enemyBoundry2 = new double[maxEnemyNumber];
		enemyDirection = new int[maxEnemyNumber];
		enemyStatus = new int[maxEnemyNumber];
		playerSpotted = new boolean[maxEnemyNumber];
		
		LOSTopEdge = new double[maxEnemyNumber];
		LOSBottomEdge = new double[maxEnemyNumber];
		LOSLeftEdge = new double[maxEnemyNumber]; 
		LOSRightEdge = new double[maxEnemyNumber];
		
		enemyType = new int[maxEnemyNumber];
		
		enemyMovementTimer = new double[maxEnemyNumber];
		enemyMovementDuration = new double[maxEnemyNumber];
		enemyFrame = new int[maxEnemyNumber];
		
		int n = 0;
		int y = 0;
		
		for( int i = 0; i <maxEnemyNumber ; i++) {
			enemyMovementTimer[i] = 0.0;
			enemyMovementDuration[i] = 0.5;
		}
		
		for( int x = 0; x < 128; x+= 32) {
			enemyDownWalk[n] = subImage(enemySheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y += 48;
		
		for( int x = 0; x < 128; x+= 32) {
			enemyLeftWalk[n] = subImage(enemySheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y += 48;
		
		for( int x = 0; x < 128; x+= 32) {
			enemyRightWalk[n] = subImage(enemySheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y += 48;
		
		for( int x = 0; x < 128; x+= 32) {
			enemyUpWalk[n] = subImage(enemySheet, x, y, 32, 48);
			n++;
		}
		n = 0;
		y=+ 48;
	}
	
	//Enemy Line of sight Area are calculated in each direction
	//Then the four new variables LOSTop, LOSBottom, LOSLeft, LOSRight are
	//used for more straightforward collison dectection
	//red rectangle will be drew to indecate the LOSarea---Arthur
	double[] LOSTopEdge, LOSBottomEdge, LOSLeftEdge, LOSRightEdge;
	boolean[] playerSpotted;
	
	public void updateEnemy(double dt){
		
		for(int i=0;i<enemyNumber; i++){
			enemyTimer+=dt;
			if(enemyStatus[i] == 0){
				//Player Not Spoted
				if(enemyDirection[i] ==0){
					enemyMovementTimer[i] += dt;
					enemyVelocityY[i] =-100;
					enemyVelocityX[i] = 0;
					LOSTopEdge[i] = enemyPositionY[i] - enemyLOS[i];
					LOSBottomEdge[i] =  enemyPositionY[i] + enemyHeight[i];
					LOSLeftEdge[i] = enemyPositionX[i];
					LOSRightEdge[i]= enemyPositionX[i] + enemyWidth[i];

					if(enemyPositionY[i] < enemyBoundry1[i] && enemyType[i]==0){
						enemyDirection[i] =2;
					}		
				} else if(enemyDirection[i] == 2){
					enemyMovementTimer[i] += dt;
					enemyVelocityY[i] =100;
					enemyVelocityX[i] = 0;
					LOSTopEdge[i] = enemyPositionY[i];
					LOSBottomEdge[i] =  enemyPositionY[i] + enemyHeight[i] + enemyLOS[i];
					LOSLeftEdge[i] = enemyPositionX[i];
					LOSRightEdge[i] = enemyPositionX[i] + enemyWidth[i] ;

					if(enemyPositionY[i]+enemyHeight[i] > enemyBoundry2[i] && enemyType[i]==0){
						enemyDirection[i] =0;
					}
				} else if(enemyDirection[i] == 1){
					enemyMovementTimer[i] += dt;
					enemyVelocityY[i] =0;
					enemyVelocityX[i] = 100;
					LOSTopEdge[i] = enemyPositionY[i];
					LOSBottomEdge[i] =  enemyPositionY[i] + enemyHeight[i];
					LOSLeftEdge[i] = enemyPositionX[i];
					LOSRightEdge[i] = enemyPositionX[i] + enemyWidth[i] + enemyLOS[i];
					

					if(enemyPositionX[i] +enemyWidth[i] > enemyBoundry2[i] && enemyType[i]==0){
						enemyDirection[i] = 3;
					}
					
				} else if(enemyDirection[i] == 3){
					enemyMovementTimer[i] += dt;
					enemyVelocityY[i] =0;
					enemyVelocityX[i] = -100;
					LOSTopEdge[i] = enemyPositionY[i];
					LOSBottomEdge[i] =  enemyPositionY[i] + enemyHeight[i];
					LOSLeftEdge[i] = enemyPositionX[i] - enemyLOS[i];
					LOSRightEdge[i] = enemyPositionX[i] + enemyWidth[i];

					if(enemyPositionX[i] <enemyBoundry1[i] && enemyType[i]==0){
						enemyDirection[i] = 1;
					}
				}
			}
			//when charactermode == 2 enter cheatmode, disable enemy dectection
			if (characterMode != 2){
			 if (playerTopEdge < LOSBottomEdge[i] && playerBottomEdge > LOSTopEdge[i] &&
			 	playerLeftEdge < LOSRightEdge[i] && playerRightEdge > LOSLeftEdge[i] ){
			 	//player spotted
			 	playerSpotted[i] = true;
			 	death(i);
			 } else {
			 	playerSpotted[i] = false;
			 }
			}
			if(enemyType[i] == 0){
				//--------------------------------------------------------------------------------------------------------
				if (enemyMovementTimer[i] > enemyMovementDuration[i]) {
						enemyMovementTimer[i] = 0;
				}
				enemyFrame[i] = getAnimationFrame(enemyMovementTimer[i], enemyMovementDuration[i], 4);
				//-------------------------------------------------------------------------------------------------------
				
				enemyPositionX[i] += enemyVelocityX[i] *dt;
				enemyPositionY[i] += enemyVelocityY[i] *dt;
			} else {
				if(enemyTimer>enemyDelay){
					enemyTimer -=enemyDelay;
					enemyDirection[i]++;
					while(enemyDirection[i] == enemyBoundry1[i] || enemyDirection[i] == enemyBoundry2[i]){
						enemyDirection[i]++;
					}
					if(enemyDirection[i]>3){
						enemyDirection[i] = 0;
						while(enemyDirection[i] == enemyBoundry1[i] || enemyDirection[i] == enemyBoundry2[i]){
							enemyDirection[i]++;
						}
					}
				}
			}
		}
	}
	
	public void drawEnemy(){
			
		changeColor(red);
		
		for(int i=0;i<enemyNumber; i++){
			if (playerSpotted[i]) {drawText(500, 500, "YOU ARE DEAD", "Chiller", 50);}
			for (int j=0; j<=50; j+=10){
				if(enemyDirection[i] ==0){
					drawImage(flashlight, enemyPositionX[i], enemyPositionY[i] + enemyHeight[i], enemyWidth[i], -enemyLOS[i] - enemyHeight[i]);
				} else if(enemyDirection[i] == 2){
					drawImage(flashlight, enemyPositionX[i], enemyPositionY[i] +enemyHeight[i]/2, enemyWidth[i], enemyLOS[i] + enemyHeight[i]/2 );
				} else if(enemyDirection[i] == 1){	
					drawImage(flashLong, enemyPositionX[i] + enemyLOS[i] + enemyWidth[i], enemyPositionY[i], -enemyLOS[i] - enemyWidth[i]/2 , enemyHeight[i]) ;
				} else if(enemyDirection[i] == 3){
					drawImage(flashLong, enemyPositionX[i] - enemyLOS[i], enemyPositionY[i], enemyLOS[i] + enemyWidth[i]/2 , enemyHeight[i]) ;
				}
				//if(j == 50){j = 0 ;}
			}
		}
		
		for(int i=0;i<enemyNumber; i++){
			if(enemyDirection[i] ==0){
				drawImage(enemyUpWalk[enemyFrame[i]], enemyPositionX[i],enemyPositionY[i], enemyWidth[i], enemyHeight[i]);
			}
			if(enemyDirection[i] == 2) {
				drawImage(enemyDownWalk[enemyFrame[i]], enemyPositionX[i],enemyPositionY[i], enemyWidth[i], enemyHeight[i]);
			}
			if(enemyDirection[i] == 1) {
				drawImage(enemyRightWalk[enemyFrame[i]], enemyPositionX[i],enemyPositionY[i], enemyWidth[i], enemyHeight[i]);
			}
			if(enemyDirection[i] == 3) {
				drawImage(enemyLeftWalk[enemyFrame[i]], enemyPositionX[i],enemyPositionY[i], enemyWidth[i], enemyHeight[i]);
			}
		}
		changeColor(black);
	}
	
	
	//Stationary Enemy: createEnemy(ID num, posX, posY, width, height, direction(dont face, 4 if blank), direction(dont face, 4 if blank), starting direction, enemy type(1 for startionary))
	//Moving Enemy: createEnemy(ID num, posX, posY, width, height, boundy(X or Y depending on start direction), boundy(X or Y depending on start direction), starting direction, enemy type(0 for moving))
	public void createEnemy(int x, double enemyX, double enemyY, double enemyW, double enemyH, double enemyB1, double enemyB2, int enemyD, int enemyT){
		enemyPositionX[x] = enemyX;
		enemyPositionY[x] = enemyY;
		enemyVelocityX[x] = 0;
		enemyVelocityY[x] = 0;
		enemyWidth[x] = enemyW;
		enemyHeight[x] = enemyH;
		enemyLOS[x] = difficultyLOS;
		enemyBoundry1[x] = enemyB1;
		enemyBoundry2[x] = enemyB2;
		enemyDirection[x] = enemyD;
		enemyStatus[x] = 0;
		enemyType[x] = enemyT;
	}
	
	//-----------------------
	//LAZERS!
	//----------------------
	
	//Image laserImage;
	Image laser;
	AudioClip laserSound;

	double[] laserPositionX;
	double[] laserPositionY;
	double[] laserAngle;
	double[] laserLength;
	boolean[] laserActive;
	int[] laserDirection;
	double[] laserTimer;
	double[] laserOn;
	double[] laserOff;
	int laserAmount, maxLasers;
	boolean laserdeath;
	
	
	public void createLaser(int x, double laserX, double laserY, double laserL, int laserD, double LOn, double LOff){
		laserPositionX[x] = laserX;
		laserPositionY[x] = laserY;
		laserLength[x] = laserL;
		laserOn[x] = LOn;
		laserOff[x] = LOff;
		laserDirection[x] = laserD;
		if (laserD == 0) {laserAngle[x] = 0;}
		else {laserAngle[x] = 270;} 
		laserTimer[x] = 0;
	}
	
	public void initLaser(){
		maxLasers = 40;
		laserPositionX = new double[maxLasers];
		laserPositionY = new double[maxLasers];
		laserLength = new double[maxLasers];
		laserAngle = new double[maxLasers];
		laserActive = new boolean[maxLasers];
		laserOn = new double[maxLasers];
		laserOff = new double[maxLasers];
		laserDirection = new int[maxLasers];
		laserTimer = new double[maxLasers];
		
	
		laser = loadImage("laser.png");
		//laserImage = subImage(spritesheet, 252, 0, 240, 240);
		laserSound = loadAudio("laser2.wav");
		
		for(int i = 0; i < maxLasers; i++) {
			laserActive[i] = false;
			laserTimer[i] = 0;
		}
	}
	
	
	public void drawLaser(){
		for(int i = 0; i < laserAmount; i++){
			if(laserActive[i]){
				saveCurrentTransform();
				translate(laserPositionX[i], laserPositionY[i]);
				rotate(laserAngle[i]);
				drawImage(laser, -30, 0, 60, laserLength[i]);
				restoreLastTransform();
			}
		}
	}
	
	
	public void updateLaser(double dt){
		
		for (int j = 0; j < laserAmount; j++){
			laserTimer[j] += dt;
		}
		
		for (int i = 0; i < laserAmount; i++){
			if (laserTimer[i] < laserOn[i]){
				laserActive[i] = true;
				if (laserDirection[i] == 0){
					if (laserPositionX[i] > playerLeftEdge && laserPositionX[i] < playerRightEdge && laserPositionY[i] < playerBottomEdge && laserPositionY[i] + laserLength[i] > playerTopEdge){
						laserdeath = true;
						if (soundEffectOn == true) {
							playAudio(laserSound);
						}
						//when haracterMode = 2, enter cheatmode, laser disabled
						if (characterMode != 2){
							death(i);
						}
					}else{
						laserdeath = false;
					}
				}
				if(laserDirection[i] != 0){
					if (laserPositionX[i] + laserLength[i] > playerLeftEdge && laserPositionX[i] < playerRightEdge && laserPositionY[i] < playerBottomEdge && laserPositionY[i] > playerTopEdge){
						laserdeath = true;
						if (soundEffectOn == true) {
							playAudio(laserSound);
						}
						//when haracterMode = 2, enter cheatmode, laser disabled
						if (characterMode != 2){
							death(i);
						}
					}else{
						laserdeath = false;
					}
				}
			} 
			
			else if(laserTimer[i] > laserOn[i] && laserTimer[i] < laserOn[i] + laserOff[i]){
				laserActive[i] = false;
			}
			else {
				laserTimer[i] = laserTimer[i] - (laserOn[i] + laserOff[i]);
			}
		}
	}
	
	//-------------------------------------------
	//Ghosts
	//-------------------------------------------
double[] ghostPositionX, ghostPositionY;
	double ghostVelocityX, ghostVelocityY;
	int maxGhosts = 4; 
	int ghostAmount;
	double[] ghostDetect;
	boolean[] ghostActive;
	double[] ghostTopEdge, ghostBottomEdge, ghostLeftEdge, ghostRightEdge;
	int ghostFrame;
	double ghostTimer;
	double ghostDuration;
	
	Image[] ghostImage;
	Image ghostSheet;
	
	public void createGhost(int x, double ghostX, double ghostY, double ghostD){
		ghostPositionX[x] = ghostX;
		ghostPositionY[x] = ghostY;
		ghostDetect[x] = ghostD;
		ghostActive[x] = false;
	}
	
	public void initGhost() {
		ghostSheet =  loadImage("ghost.png");
		ghostPositionX = new double[maxGhosts];
		ghostPositionY = new double[maxGhosts];
		ghostActive = new boolean[maxGhosts];
		ghostTopEdge = new double[maxGhosts];
		ghostBottomEdge = new double[maxGhosts];
		ghostLeftEdge = new double[maxGhosts];
		ghostRightEdge = new double[maxGhosts];
		ghostDetect = new double[maxGhosts];		
		ghostImage = new Image[10];
		ghostDuration = 1.0;
		
		ghostVelocityX = 0;
		ghostVelocityY = 0;
		
		int y = 32;
		for (int x = 0; x < 6; x ++){
			ghostImage[x] = subImage(ghostSheet, x*32, y, 32, 32);
		}
			
		
		for(int i = 0; i < maxGhosts; i++){
			ghostActive[i] = false;
		}
	}
	
	public void drawGhost(){
		for (int i = 0; i < ghostAmount; i++){
			saveCurrentTransform();
			translate(ghostPositionX[i], ghostPositionY[i]);
			drawImage(ghostImage[ghostFrame], 0, 0, 70, 70);
			restoreLastTransform();
		}
	}
	
	public void updateGhost(double dt){
		ghostTimer += dt;
		ghostFrame = getAnimationFrame(ghostTimer, ghostDuration, 6);
		
		for (int i = 0; i < ghostAmount; i++){
			if (distance(playerPositionX, playerPositionY, ghostPositionX[i], ghostPositionY[i]) < ghostDetect[i]){
				ghostActive[i] = true;
			}
			if(ghostActive[i]){
				ghostVelocityX = playerPositionX - ghostPositionX[i];
				ghostVelocityY = playerPositionY - ghostPositionY[i];
			
				double l = length(ghostVelocityX, ghostVelocityY);
			
				ghostVelocityX *= 50/l ;
				ghostVelocityY *= 50/l;
			
				ghostPositionX[i] += ghostVelocityX * dt;
				ghostPositionY[i] += ghostVelocityY * dt;
			}
			
			ghostTopEdge[i] = ghostPositionY[i];
			ghostBottomEdge[i] = ghostPositionY[i] + 70;
			ghostRightEdge[i] = ghostPositionX[i] + 70;
			ghostLeftEdge[i] = ghostPositionX[i];
			
			if(playerTopEdge < ghostBottomEdge[i] && playerBottomEdge > ghostTopEdge[i] 
				&& playerRightEdge > ghostLeftEdge[i] && playerLeftEdge < ghostRightEdge[i]){
					death(i);
			}
		}		
	}
	
	//running ninja
	Image ninjaRun;
	Image[] ninjaImage;
	int ninjaFrame;
	double ninjaTimer = 0;
	
	public void initNinja(){
		ninjaRun = loadImage("ninja.png");
		ninjaImage = new Image[9];

		
		int y = 200;
		for (int x = 0; x < 8; x ++){
			ninjaImage[x] = subImage(ninjaRun, x*100 + 100, y, 100, 100);
		}
	}

	
	public void updateNinja(double dt){
		ninjaTimer += dt;
		ninjaFrame = getAnimationFrame(ninjaTimer, 0.7, 8);
	}
	
	public void drawNinja(double x, double y){
		saveCurrentTransform();
		translate(x, y);
		drawImage(ninjaImage[ninjaFrame], 0, 0, 60, 60);
		restoreLastTransform();
	}
	
	
	
	//-------------------------------------------
	//Music
	//-------------------------------------------
	AudioClip menuMusic;
	AudioClip backgroundMusic;
	AudioClip gameoverMusic;
	boolean gamemusicActive;
	boolean menumusicActive;
	boolean gameovermusicActive;
	
	
	public void initMusic() {
		gamemusicActive = false;
		menumusicActive = false;
		gameovermusicActive = false;
		menuMusic = loadAudio("menu.wav");
		backgroundMusic = loadAudio("gameMusic.wav");
		gameoverMusic = loadAudio("gameoverMusic.wav");
	}
	
	public void playMusic() {
		if (musicOn) {
			if (state == GameState.startPage) {
			if (menumusicActive == false) {
				stopAudioLoop(backgroundMusic);
				stopAudioLoop(gameoverMusic);
				startAudioLoop(menuMusic);
				
				menumusicActive = true;
				gamemusicActive = false;
				gameovermusicActive = false;
			}
		}
		
			if (state == GameState.gamePage){
				if (gamemusicActive == false) {
					stopAudioLoop(menuMusic);
					stopAudioLoop(gameoverMusic);
					startAudioLoop(backgroundMusic);
				
					gamemusicActive = true;
					menumusicActive = false;
					gameovermusicActive = false;
				}
			}
			if (state == GameState.gameOver) {
				if (gameovermusicActive == false) {
					stopAudioLoop(backgroundMusic);
					stopAudioLoop(menuMusic);
					startAudioLoop(gameoverMusic);
				
					gameovermusicActive = true;
					menumusicActive = false;
					gamemusicActive = false;
				}
			}
		} else {
			stopAudioLoop(backgroundMusic);
			stopAudioLoop(menuMusic);
			stopAudioLoop(gameoverMusic);
		}
	}
	
	
	//---------------------
	//GameState
	//---------------------
	enum GameState {startPage, optionPage, gamePage, gameOver, pausePage, creditPage, loseLife};
	GameState state = GameState.startPage;

	int menuOption = 0;
	

	
	

	//---------------------
	//INIT functions
	//---------------------
	//easyMode() have to be placed before initRoom() to correctly update enemyLOS[x]
	public void init(){
		setWindowSize (1000,750);
		//initPlayer();
		easyMode();
		initMusic();
		initLaser();
		initEnemy();
		initGhost();
		initRoom(); 
		initDoors();
		initCollect();
		initExplosion();
	
		musicOn = true;
		soundEffectOn = true;
		
	}
	
	//---------------------
	//UPDATE functions
	//---------------------
	//some timers here
	double minimapFlashing, lastroomTimer;
	public void update(double dt){
		if (state == GameState.startPage) {
			playMusic();
			updateNinja(dt);
		}else if (state == GameState.gamePage){
			playMusic(); 
			updatePlayer(dt);
			updateExplosion(dt);
			updateRoom(dt);
			updateDoors(dt);
			updateCollect(dt);
			updateEnemy(dt);
			updateLaser(dt);
			updateGhost(dt);
			if (roomNumber == 16){
				lastroomTimer += dt;
				newRoom();
			}
		} else if (state == GameState.loseLife){
			updatePlayer(dt);
			updateExplosion(dt);
		} else if (state == GameState.pausePage){
			minimapFlashing += dt*2;
			if (minimapFlashing >=1) {minimapFlashing = 0;}
		} else if (state == GameState.gameOver){
			lastroomTimer += dt;
			playMusic();
		} else {
			return;
		}
	}
	
	//---------------------
	//PAINT COMPONENT
	//---------------------

	
	
	
	
	int width = width();int height = height();
	
	public void paintComponent(){
		clearBackground(1000,750);
		changeBackgroundColor(white);
		drawBackground();
		
		if (state == GameState.startPage){
			initNinja();
			paintStartPage();
		} else if (state == GameState.optionPage){
			paintOptionPage();			
		} else if (state == GameState.gamePage){
			paintGamePage();		
		} else if (state == GameState.loseLife){
			paintLoseLife();			
		} else if (state == GameState.gameOver){
			paintGameOver();
		} else if (state == GameState.pausePage){
			paintPausePage();
		} else if (state == GameState.creditPage){
			paintCreditPage();
		}
		System.out.println(lastroomTimer);
	}
	
	public void paintGamePage(){
		drawRoom();
		drawCollect();
		drawPlayer();
		drawExplosion();
		drawDoors();
		drawLaser();
		drawEnemy();
		drawGhost();
		
	}
	
	Image startPage;
	
	public void paintStartPage(){
		startPage = loadImage("moon.png");
		drawImage(startPage, 0, 0, width(), height());
		
		changeColor(blue);
		drawBoldText(width/4+width/100, height/4-height/100, "Unlucky Ninja 2.0", "Chiller", height/5);

		changeColor(yellow);
		drawBoldText(width/4, height/4, "Unlucky Ninja 2.0", "Chiller", height/5);

		if(menuOption == 0) {
			drawNinja(width/3 - 70, height/2 - 60);
			changeColor(red);
		} else {
			changeColor(white);
		}
        drawText(width/3, height/2, "NEW GAME", "Chiller", 60 );

		if(menuOption == 1) {
			drawNinja(width/3 - 70, height/2);
			changeColor(red);
		} else {
			changeColor(white);
		}
        drawText(width/3, height/2 + 60, "OPTIONS", "Chiller", 60);

		if(menuOption == 2) {
			drawNinja(width/3 - 70, height/2 + 60);
			changeColor(red);
		} else {
			changeColor(white);
		}
		if (characterMode == 0){
        drawText(width/3, height/2 + 120, "CHARACTER WINTERSOLDIER", "Chiller", 60);
		} else if (characterMode == 1){
			drawText(width/3, height/2 + 120, "CHARACTER STARLORD", "Chiller", 60);
		} else if (characterMode == 2){
			drawText(width/3, height/2 + 120, "CHARACTER DEADPOOL", "Chiller", 60);
		}
		if(menuOption == 3) {
			drawNinja(width/3 - 70, height/2 + 120);
			changeColor(red);
		} else {
			changeColor(white);
		}
        drawText(width/3, height/2 + 180, "CREDIT", "Chiller", 60);

		if(menuOption == 4) {
			drawNinja(width/3 - 70, height/2 + 180);
			changeColor(red);
		} else {
			changeColor(white);
		}	
		if(musicOn){
			drawText(width/3, height/2 + 240, "MUSIC  ON", "Chiller", 60);
		} else {
			drawText(width/3, height/2 + 240, "MUSIC  OFF", "Chiller", 60);
		}
		
		if(menuOption == 5) {
			drawNinja(width/3 - 70, height/2 + 240);
			changeColor(red);
		} else {
			changeColor(white);
		}	
		if(soundEffectOn){
			drawText(width/3, height/2 + 300, "SOUND EFFECT  ON", "Chiller", 60);
		} else {
			drawText(width/3, height/2 + 300, "SOUND EFFECT  OFF", "Chiller", 60);
		}

		if(menuOption == 6) {
			drawNinja(width/3 - 70, height/2 + 300);
			changeColor(red);
		} else {
			changeColor(white);
		}
        drawText(width/3, height/2 + 360, "Exit", "Chiller", 60);
	}
	
	Image gameover;
	public void paintGameOver(){
		gameover = loadImage("gameover.png");
		drawImage(gameover, 0,0, width(), height());
		if (lastroomTimer <= 5){
			changeColor(red);
			if (roomNumber == 16){
				drawBoldText(width()/2-400, height()/2 + 200, "Thank you for playing", "Chiller", 100);
			}
			drawBoldText(width()/2-width()/4, height()/2 - 200, "GAME OVER", "Chiller", 100);
			drawText(width()/2-200, height()/2 - 100, "Press ENTER to restart", "Chiller", 50);
			changeColor(yellow);
			drawText(width()/2-200, height()/2 +100, "Your Score: " + scoreS, "Chiller", 70);
		}
		if (lastroomTimer > 5){
			changeColor(white);
			drawBoldText(width()/2-width()/4, height()/2 - 100, "FIN", "Chiller", 200);
		}
	}

	public void paintOptionPage(){
		startPage = loadImage("moon.png");
		drawImage(startPage, 0, 0, width(), height());

		//changeColor(black);
		//drawSolidRectangle(0,0, width(), height());
		
		//easy
		if(menuOption == 0) {
			changeColor(red);
		} else if (difficultyLOS == 150){
			changeColor(yellow);
		} else {
			changeColor(white);
		}
        drawText(50, 50, "Chicken Mode", "Chiller", 50);

		//hard
		if(menuOption == 1) {
			changeColor(red);
		} else if (difficultyLOS == 250){
			changeColor(yellow);
		} else {
			changeColor(white);
		}
        drawText(50, 100, "Hardcore Extreme One Life One Hit Die Hard Mode", "Chiller", 50);

		//Exit
		if(menuOption == 2) {
			changeColor(red);
		} else {
			changeColor(white);
		}
        drawText(50, 200, "Back to Menu", "Chiller", 50);
	}

	

	public void paintPausePage(){
		startPage = loadImage("moon.png");
		drawImage(startPage, 0, 0, width(), height());

		//changeColor(black);
		//drawSolidRectangle(0,0, width(), height());
		
		changeColor(yellow);
		drawText(50, 50, "Game Paused", "Chiller", 50);
		changeColor(white);
		drawText(50, 100, "Press ESC to resume the game", "Chiller", 50);
		drawText(50, 150, "Press BACKSPACE to go back to start page", "Chiller", 50);
		drawText(50, 200, "Press ENTER to restart the game", "Chiller", 50);
		
		for (int i = 0 ; i < 7; i++) {
			drawRectangle(460, 230 + 60*i + 10*i, 80, 60);
		}		
		for (int i = 0 ; i < 8; i++) {
			drawRectangle(190 + 80*i + 10*i, 510, 80, 60);
		}
		for (int i = 0 ; i < 3; i++) {
			drawRectangle(370 + 80*i + 10*i, 370, 80, 60);
		}

		//minimap
		changeColor(red);
		if (minimapFlashing <= 0.5){
			if (roomNumber==0){drawSolidCircle(190 + 40 + (80+10)*3, 230 + 30 + (60+10)*6, 10);}
			if (roomNumber==1){drawSolidCircle(190 + 40 + (80+10)*3, 230 + 30 + (60+10)*5, 10);}
			if (roomNumber==2){drawSolidCircle(190 + 40 + (80+10)*3, 230 + 30 + (60+10)*4, 10);}

			if (roomNumber==3){drawSolidCircle(190 + 40 + (80+10)*4, 230 + 30 + (60+10)*4, 10);}
			if (roomNumber==5){drawSolidCircle(190 + 40 + (80+10)*5, 230 + 30 + (60+10)*4, 10);}
			if (roomNumber==6){drawSolidCircle(190 + 40 + (80+10)*6, 230 + 30 + (60+10)*4, 10);}
			if (roomNumber==7){drawSolidCircle(190 + 40 + (80+10)*7, 230 + 30 + (60+10)*4, 10);}

			if (roomNumber==4){drawSolidCircle(190 + 40 + (80+10)*2, 230 + 30 + (60+10)*4, 10);}
			if (roomNumber==8){drawSolidCircle(190 + 40 + (80+10)*1, 230 + 30 + (60+10)*4, 10);}
			if (roomNumber==9){drawSolidCircle(190 + 40 + (80+10)*0, 230 + 30 + (60+10)*4, 10);}
			
			if (roomNumber==11){drawSolidCircle(190 + 40 + (80+10)*3, 230 + 30 + (60+10)*3, 10);}		
			if (roomNumber==10){drawSolidCircle(190 + 40 + (80+10)*3, 230 + 30 + (60+10)*2, 10);}
			if (roomNumber==15){drawSolidCircle(190 + 40 + (80+10)*3, 230 + 30 + (60+10)*1, 10);}

			if (roomNumber==12){drawSolidCircle(190 + 40 + (80+10)*2, 230 + 30 + (60+10)*2, 10);}
			if (roomNumber==13){drawSolidCircle(190 + 40 + (80+10)*4, 230 + 30 + (60+10)*2, 10);}
			if (roomNumber==14){drawSolidCircle(190 + 40 + (80+10)*5, 230 + 30 + (60+10)*2, 10);}

			if (roomNumber==16){drawSolidCircle(190 + 40 + (80+10)*3, 230 + 30 + (60+10)*0, 10);}
		}			
	}

	public void paintCreditPage(){
		startPage = loadImage("moon.png");
		drawImage(startPage, 0, 0, width(), height());

		//changeColor(black);
		//drawSolidRectangle(0,0, width(), height());
		
		changeColor(yellow);
		drawText(50, 50, "Credit", "Chiller", 50);
		changeColor(white);
		drawText(50, 100, "All music tracks and sound effects in this game are", "Arial", width/30);
		drawText(50, 150, "created by http://www.soundjay.com/", "Arial", width/30);
		drawText(50, 200, "Programmer: Team Cheese Sandwich", "Arial", width/30);
		drawText(50, 250, "GameEngine: Dr Daniel Playne, Dr Arno Leist", "Arial", width/30);
	}
	
	public void paintLoseLife(){
		startPage = loadImage("moon.png");
		drawImage(startPage, 0, 0, width(), height());
		
		//changeColor(black);
		//drawSolidRectangle(0,0, width(), height());
		
		drawPlayer();
		drawExplosion();
		
		changeColor(red);
		drawText(width/2-width/4, height/2, "You died", "Chiller", 50);
		drawText(width/2-width/4, height/2+100, "Press ENTER to respawn", "Chiller", 50);
	}
	
	//---------------------
	//KEY LISTENERS
	//---------------------
	
	boolean musicOn, soundEffectOn;
	public void keyPressed(KeyEvent e) {
    	if (state == GameState.startPage){
			keyPressedStartPage(e);
		} else if (state == GameState.optionPage){
			keyPressedOptions(e);			
		} else if (state == GameState.gamePage){
			keyPressedGamePage(e);	
		} else if (state == GameState.loseLife){
			keyPressedLoseLife(e);		
		} else if (state == GameState.gameOver){
			keyPressedGameOver(e);
		} else if (state == GameState.pausePage){
			keyPressedPausePage(e);
		} else if (state == GameState.creditPage){
			keyPressedCreditPage(e);
		}
	}
	
	public void keyPressedGamePage(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			minimapFlashing = 0;
			state = GameState.pausePage;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			playerUp = true;
			playerDirectionUp = false; 
			playerDirectionDown = false; 
			playerDirectionLeft = false;
			playerDirectionRight = false;
			
			if ((playerRight)|| (playerLeft) || (playerDown)) {
				playerRight = false;
				playerLeft = false;
				playerDown = false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){			
			playerDown = true;
			playerDirectionUp = false; 
			playerDirectionDown = false; 
			playerDirectionLeft = false;
			playerDirectionRight = false;
			
			if ((playerRight)|| (playerUp) || (playerLeft)) {
				playerRight = false;
				playerUp = false;
				playerLeft = false;
			}
		}		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			playerRight = true;
			playerDirectionUp = false; 
			playerDirectionDown = false; 
			playerDirectionLeft = false;
			playerDirectionRight = false;
			
			if ((playerLeft)|| (playerUp) || (playerDown)) {
				playerLeft = false;
				playerUp = false;
				playerDown = false;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			
			playerLeft = true;
			playerDirectionUp = false; 
			playerDirectionDown = false; 
			playerDirectionLeft = false;
			playerDirectionRight = false;
			
			if ((playerRight)|| (playerUp) || (playerDown)) {
				playerRight = false;
				playerUp = false;
				playerDown = false;
			}
		}
	}
	
	public void keyPressedStartPage(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(menuOption > 0) {
				menuOption--;
			}
		}
		//Down Arrow
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(menuOption < 6) {
				menuOption++;
			}
		}
		//Enter Key
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(menuOption == 0) {
				//Start Game
				menuOption = 0;
				lastroomTimer = 0;
				initLaser();
				initEnemy();
				initGhost();
				initRoom(); 
				initDoors();
				initCollect();
				initPlayer();
				initExplosion();
				if (difficultyLOS == 150 && characterMode == 0){
					lives = 3;//easy mode with wintersoldier
				} else if (difficultyLOS == 150 && characterMode == 1){
					lives = 6;//easy mode with starlord
				} else if (difficultyLOS == 150 && characterMode == 2){
					lives = 4;//easy mode with starlord
				} else { 
					lives = 1;//hard mode
				}
				state = GameState.gamePage;
				
			} else if(menuOption == 1) {
				//Option
				menuOption = 0;
				state = GameState.optionPage;
				
			} else if(menuOption == 2) {
				//Player Skin Change 
				if (characterMode == 0){characterMode = 1;}
				else if (characterMode == 1){characterMode = 2;}	
				else if (characterMode == 2){characterMode = 0;}
					
			} else if(menuOption == 3){
				//option
				menuOption = 0;
				state = GameState.creditPage;
			} else if(menuOption == 4){
				//Music on/off
				if (musicOn){ musicOn = false;} 
				else {musicOn = true;}
				
			} else if(menuOption == 5){
				//Sound Effect on/off
				if (soundEffectOn){ soundEffectOn = false;} 
				
				else {soundEffectOn = true;}
			}else {
				//Exit
				System.exit(0);
			}
		}
	}		
	
	public void keyPressedOptions(KeyEvent e) {
    	//Up Arrow
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(menuOption > 0) {
				menuOption--;
			}
		}
		//Down Arrow
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(menuOption < 2) {
				menuOption++;
			}
		}
		//Enter Key
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(menuOption == 0) {
				//You chicken?
				easyMode();
			} else if(menuOption == 1) {
				//hardCore Extreme 
				hardMode();
			} else {
				//Back
				state = GameState.startPage;
				menuOption = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			state = GameState.startPage;
		}
	}
	

	public void keyPressedPausePage(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			state = GameState.gamePage;
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			state = GameState.startPage;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			state = GameState.gamePage;
		}
	}

	public void keyPressedCreditPage(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			state = GameState.startPage;
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			state = GameState.startPage;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			state = GameState.startPage;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			state = GameState.startPage;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (state == GameState.gamePage) {
		
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				playerLeft = false;
				
				playerDirectionUp = false; 
				playerDirectionDown = false; 
				playerDirectionLeft = true;
				playerDirectionRight = false;
				
				playerVelocityX = 0;
				
			}
		
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				playerRight = false;
				
				playerDirectionUp = false; 
				playerDirectionDown = false; 
				playerDirectionLeft = false;
				playerDirectionRight = true;
				
				playerVelocityX = 0;
				
			}
			
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				
				playerUp = false;
				
				playerDirectionUp = true; 
				playerDirectionDown = false; 
				playerDirectionLeft = false;
				playerDirectionRight = false;

				playerVelocityY = 0;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			
				playerDown = false;
				
				playerDirectionUp = false; 
				playerDirectionDown = true; 
				playerDirectionLeft = false;
				playerDirectionRight = false;
				
				playerVelocityY = 0;
			}
		}
	}
	
	public void keyPressedLoseLife(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			state = GameState.gamePage;
			playerPositionX = roomspawnX[roomNumber];
			playerPositionY = roomspawnY[roomNumber];
			playerDirectionUp = true;
		}
	}
	
	
	public void keyPressedGameOver(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			state = GameState.startPage;
			menuOption = 0;
		}
	}
	
}