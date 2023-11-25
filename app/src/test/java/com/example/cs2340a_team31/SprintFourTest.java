//package com.example.cs2340a_team31;
//
//import static org.junit.Assert.assertEquals;
//
//import com.example.cs2340a_team31.model.Player;
//import com.example.cs2340a_team31.model.enemyFactoryPattern.DawgEnemy;
//import com.example.cs2340a_team31.model.enemyFactoryPattern.*;
//
//import org.junit.Test;
//
//public class SprintFourTest {
//
//    private Player player;
//
//    //#1 Thomas Wang
//    @Test
//    public void testInitialization() {
//        player = Player.getPlayer();
//        player.setHealth(100);
//
//        assertEquals(0.0,player.getHeight(),0);
//        assertEquals(0.0, player.getWidth(), 0);
//        assertEquals(0.0,player.getX(),0);
//        assertEquals(0.0,player.getY(),0);
//        assertEquals(25.0,player.getMovementSpeedX(),0);
//        assertEquals(100,player.getHealth(),0);
//    }
//
//    //#2 Thomas Wang
//    @Test
//    public void testMiceEnemyLocation() {//Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("mice", 12.0 * 100.0, 9.0 * 100.0, 20.0, 100.0,
//                100, "DOWN");
//
//        player = Player.getPlayer();
//        player.setHealth(100);
//
//        assertEquals(1200.0,enemy.getX(),0);
//        assertEquals(900.0,enemy.getY(),0);
//
//
//        assertEquals(25.0,player.getMovementSpeedX(),0);
//        assertEquals(100,player.getHealth(),0);
//    }
//    //#3 Kenny Nguyen
//    @Test
//    public void testRatEnemyLocation() { //Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("rat", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        //System.out.println(enemy.getDamage());
//        player = Player.getPlayer();
//        player.setHealth(100);
//
//        assertEquals(1200.0,enemy.getX(),0);
//        assertEquals(900.0,enemy.getY(),0);
//
//        assertEquals(25.0,player.getMovementSpeedX(),0);
//        assertEquals(100,player.getHealth(),0);
//    }
//    //#4 Kenny Nguyen
//    @Test
//    public void testDogEnemyLocation() { //Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("dog", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//
//        player = Player.getPlayer();
//        player.setHealth(100);
//
//        assertEquals(1200.0,enemy.getX(),0);
//        assertEquals(900.0,enemy.getY(),0);
//
//
//        assertEquals(25.0,player.getMovementSpeedX(),0);
//        assertEquals(100,player.getHealth(),0);
//    }
//    //#5 Joshua Johnson
//    @Test
//    public void testdawgEnemyLocation() {  //Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("dawg", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//
//        player = Player.getPlayer();
//        player.setHealth(100);
//
//        assertEquals(1200.0,enemy.getX(),0);
//        assertEquals(900.0,enemy.getY(),0);
//
//
//        assertEquals(25.0,player.getMovementSpeedX(),0);
//        assertEquals(100,player.getHealth(),0);
//    }
//    //#6 Joshua Johnson
//    @Test
//    public void testRatEnemyDamage() { //Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("rat", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        assertEquals(200,enemy.getDamage(),0);
//
//    }
//    //#7 Shabin Hossain
//    @Test
//    public void testDogEnemyDamage() { //Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("dog", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        assertEquals(300,enemy.getDamage(),0);
//
//    }
//    //#8 Shabin Hossain
//    @Test
//    public void testMiceEnemyDamage() { //Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("mice", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        assertEquals(100,enemy.getDamage(),0);
//
//    }
//    //#9 Hoangyen Nguyen
//    @Test
//    public void testDawgEnemyDamage() { //Testing Factory Pattern
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("dawg", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        assertEquals(500,enemy.getDamage(),0);
//
//    }
//    //#10 Hoangyen Nguyen
//    @Test
//    public void testRatEnemyDamageOnPlayerCollision() { //Testing collision damage
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("rat", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        player = player.getPlayer();
//        player.setHealth(1000);
//        assertEquals(200,enemy.getDamage(),0);
//        player.notifyCollision(enemy.getDamage());
//        assertEquals(800.0,player.getHealth(),0);
//
//    }
//    //#11 Tran Ha
//    @Test
//    public void testDogEnemyDamageOnPlayerCollision() { //Testing collision damage
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("dog", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        player = player.getPlayer();
//        player.setHealth(1000);
//        assertEquals(300,enemy.getDamage(),0);
//        player.notifyCollision(enemy.getDamage());
//        assertEquals(700,player.getHealth(),0);
//
//    }
//    //#12 Tran Ha
//    @Test
//    public void testDawgEnemyDamageOnPlayerCollision() { //Testing collision damage
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("dawg", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        player = player.getPlayer();
//        player.setHealth(1000);
//        assertEquals(500,enemy.getDamage(),0);
//        player.notifyCollision(enemy.getDamage());
//        assertEquals(500.0,player.getHealth(),0);
//
//    }
//    //#13 Tran Ha
//    @Test
//    public void testMiceEnemyDamageOnPlayerCollision() { //Testing collision damage
//        EnemyFactory enemyFactory;
//        enemyFactory = new SpaceEnemyFactory();
//        Enemy enemy = enemyFactory.spawnEnemy("mice", 12F * 100, 9F * 100, 20, 100,
//                100, "DOWN");
//        player = player.getPlayer();
//        player.setHealth(1000);
//        assertEquals(100,enemy.getDamage(),0);
//        player.notifyCollision(enemy.getDamage());
//        assertEquals(900.0,player.getHealth(),0);
//
//    }
//
//
//
//}
