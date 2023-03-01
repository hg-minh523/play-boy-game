package main;

import entity.Entity;

public class ColissionChecker {
	GamePanel gp;
	public int check = 0;
	public ColissionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile (Entity e) {
		int entityWorldLeftX = e.worldX + e.solidArea.x;
		int entityWorldRightX = e.worldX + e.solidArea.x + e.solidArea.width;
		int entityWorldTopY = e.worldY + e.solidArea.y ;
		int entityWorldBotY = e.worldY + e.solidArea.y + e.solidArea.height;
		
		int entityColLeftX =  entityWorldLeftX/gp.titleSize;
		int entityColRightX = entityWorldRightX/gp.titleSize;
		int entityRowTopY = entityWorldTopY/gp.titleSize;
		int entityRowBotY = entityWorldBotY/gp.titleSize;
		int numTile1,numTile2,numTile3;
		switch (e.derection) {
		case "up": 
			entityRowTopY = (entityWorldTopY- e.speed)/gp.titleSize;
			numTile1 = gp.tileManagerment.mapTileNum[entityColLeftX][entityRowTopY];
			numTile2 = gp.tileManagerment.mapTileNum[entityColRightX][entityRowTopY];
			if (gp.tileManagerment.tile[numTile2].colission == true) {
				e.collisionOn = true;
			}
			if (gp.tileManagerment.tile[numTile1].colission == true) {
				e.collisionOn = true;
			}
			break;
		case "down": 
			entityRowBotY = (entityWorldBotY + e.speed)/gp.titleSize;
			numTile1 = gp.tileManagerment.mapTileNum[entityColLeftX][entityRowBotY];
			numTile2 = gp.tileManagerment.mapTileNum[entityColRightX][entityRowBotY];
			if (gp.tileManagerment.tile[numTile2].colission == true) {
				e.collisionOn = true;
			}
			if (gp.tileManagerment.tile[numTile1].colission == true) {
				e.collisionOn = true;
			}
			break;
		case "left": 
			entityColLeftX = (entityWorldLeftX - e.speed)/gp.titleSize;
			numTile1 = gp.tileManagerment.mapTileNum[entityColLeftX][entityRowTopY];
			numTile2 = gp.tileManagerment.mapTileNum[entityColLeftX][entityRowBotY];
			if (gp.tileManagerment.tile[numTile2].colission == true) {
				e.collisionOn = true;
			}
			if (gp.tileManagerment.tile[numTile1].colission == true) {
				e.collisionOn = true;
			}
			break;
		case "right": 
			entityColRightX = (entityWorldRightX + e.speed)/gp.titleSize;
			numTile1 = gp.tileManagerment.mapTileNum[entityColRightX][entityRowTopY];
			numTile2 = gp.tileManagerment.mapTileNum[entityColRightX][entityRowBotY];
			if (gp.tileManagerment.tile[numTile2].colission == true) {
				e.collisionOn = true;
			}
			if (gp.tileManagerment.tile[numTile1].colission == true) {
				e.collisionOn = true;
			}
			break;
		}
		gp.eventH.checkEvent();
	}

	public int checkObject(Entity e, boolean player) {
		int index = 999;
		for (int i = 0 ; i < gp.obj_Arr.length ; i ++) {
			if( gp.obj_Arr[i] != null) {
				e.solidArea.x = e.worldX + e.solidArea.x;
				e.solidArea.y = e.worldY + e.solidArea.y;
				
				gp.obj_Arr[i].solidArea.x = gp.obj_Arr[i].solidArea.x + gp.obj_Arr[i].worldX;
				gp.obj_Arr[i].solidArea.y = gp.obj_Arr[i].worldY + gp.obj_Arr[i].solidArea.y;
				switch (e.derection) {
					case "up": {
						e.solidArea.y -= e.speed;
						if (e.solidArea.intersects(gp.obj_Arr[i].solidArea)) {
							gp.obj_Arr[i].collision = true;
							index= i;
						}
						break;
					}
					case "down": {
						e.solidArea.y += e.speed;
						if (e.solidArea.intersects(gp.obj_Arr[i].solidArea)) {
							gp.obj_Arr[i].collision = true;
							index= i;
						}
						break;
					}
					case "left": {
						e.solidArea.x -= e.speed;
						if (e.solidArea.intersects(gp.obj_Arr[i].solidArea)) {
							gp.obj_Arr[i].collision = true;
							index= i;
						}
						break;
					}
					case "right": {
						e.solidArea.x -= e.speed;
						if (e.solidArea.intersects(gp.obj_Arr[i].solidArea)) {
							gp.obj_Arr[i].collision = true;
							index= i;
						}
						break;
					}
				}
				e.solidArea.x = e.solidAreaDefaulX;
				e.solidArea.y = e.solidAreaDefaulY;
				gp.obj_Arr[i].solidArea.x = 0;
				gp.obj_Arr[i].solidArea.y = 0;
			}
			
		}
		return index;
	}
	
	public int checkEntity(Entity e, Entity[] target) {
		int index = 999;
		for (int i = 0 ; i <target.length ; i ++) {
			if( target[i] != null) {
				e.solidArea.x = e.worldX + e.solidArea.x;
				e.solidArea.y = e.worldY + e.solidArea.y;
				
				target[i].solidArea.x = target[i].solidArea.x + target[i].worldX;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch (e.derection) {
					case "up": {
						e.solidArea.y -= e.speed;
						if (e.solidArea.intersects(target[i].solidArea)) {
							e.collisionOn = true;
							index= i;
						}
						break;
					}
					case "down": {
						e.solidArea.y += e.speed;
						if (e.solidArea.intersects(target[i].solidArea)) {
							e.collisionOn = true;
							index= i;
						}
						break;
					}
					case "left": {
						e.solidArea.x -= e.speed;
						if (e.solidArea.intersects(target[i].solidArea)) {
							e.collisionOn = true;
							index= i;
						}
						break;
					}
					case "right": {
						e.solidArea.x += e.speed;
						if (e.solidArea.intersects(target[i].solidArea)) {
							e.collisionOn = true;
							index= i;
						}
						break;
					}
				}
				e.solidArea.x = e.solidAreaDefaulX;
				e.solidArea.y = e.solidAreaDefaulY;
				target[i].solidArea.x = target[i].solidAreaDefaulX;
				target[i].solidArea.y = target[i].solidAreaDefaulX;
			}
			
		}
		return index;
	}
	
	public void checkPlayer(Entity e) {
		
				e.solidArea.x = e.worldX + e.solidArea.x;
				e.solidArea.y = e.worldY + e.solidArea.y;
				
				gp.player.solidArea.x = gp.player.solidArea.x + gp.player.worldX;
				gp.player.solidArea.y = gp.player.worldY +gp.player.solidArea.y;
				
				switch (e.derection) {
					case "up": {
						e.solidArea.y -= e.speed;
						if (e.solidArea.intersects(gp.player.solidArea)) {
							e.collisionOn = true;
						}
						break;
					}
					case "down": {
						e.solidArea.y += e.speed;
						if (e.solidArea.intersects(gp.player.solidArea)) {
							e.collisionOn = true;
						}
						break;
					}
					case "left": {
						e.solidArea.x -= e.speed;
						if (e.solidArea.intersects(gp.player.solidArea)) {
							e.collisionOn = true;
						}
						break;
					}
					case "right": {
						e.solidArea.x += e.speed;
						if (e.solidArea.intersects(gp.player.solidArea)) {
							e.collisionOn = true;
						}
						break;
					}
				}
				e.solidArea.x = e.solidAreaDefaulX;
				e.solidArea.y = e.solidAreaDefaulY;
				gp.player.solidArea.x = gp.player.solidAreaDefaulX;
				gp.player.solidArea.y = gp.player.solidAreaDefaulX;
			}
			
		
}
