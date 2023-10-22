package com.example.cs2340a_team31;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340a_team31.model.Player;
import com.example.cs2340a_team31.model.strategypattern.MoveDownStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveLeftStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveRightStrategy;
import com.example.cs2340a_team31.model.strategypattern.MoveUpStrategy;

import java.util.NoSuchElementException;

public class PlayerMovementTest {

    private Player player;


    //#1 Kenny Nguyen
    @Test
    public void testInitialization() {
        player = Player.getPlayer();

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1135,player.getX(),0);
        assertEquals(482,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);
    }

    //#2 Kenny Nguyen
    @Test
    public void testMoveRight() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveRightStrategy());
        float num = (float) (2220 - player.getWidth());
        assertEquals(1110.0, player.getX(), 0);
        player.move(num);
        assertEquals(1135.0, player.getX(), 0);
    }

    // #3 Tran Ha
    @Test
    public void testMoveLeft() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveLeftStrategy());
        float num = 0;
        assertEquals(1110.0, player.getX(), 0);
        player.move(num);
        assertEquals(1085.0, player.getX(), 0);
    }

    //#4 Tran Ha
    @Test
    public void testMoveUp() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveUpStrategy());
        float num = 0;
        assertEquals(507.0, player.getY(), 0);
        player.move(num);
        assertEquals(482.0, player.getY(), 0);
    }

    //#5 Hoangyen Nguyen
    @Test
    public void testMoveDown() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveDownStrategy());
        float num = (float) (1014 - player.getWidth());
        assertEquals(507, player.getY(), 0);
        player.move(num);
        assertEquals(532, player.getY(), 0);
    }

    //#6 Hoangyen Nguyen
    @Test
    public void testMoveRightLeft() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveRightStrategy());
        float num = (float) (2220 - player.getWidth());
        assertEquals(1110.0, player.getX(), 0);
        player.move(num);
        assertEquals(1135.0, player.getX(), 0);

        player.setMovementStrategy(new MoveLeftStrategy());
        num = 0;
        assertEquals(1135.0, player.getX(), 0);
        player.move(num);
        assertEquals(1110.0, player.getX(), 0);
    }

    //#7 Thomas Wang
    @Test
    public void testMoveRightUp() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveRightStrategy());
        float num = (float) (2220 - player.getWidth());
        assertEquals(1110.0, player.getX(), 0);
        player.move(num);
        assertEquals(1135.0, player.getX(), 0);

        player.setMovementStrategy(new MoveUpStrategy());
        num = 0;
        assertEquals(507.0, player.getY(), 0);
        player.move(num);
        assertEquals(482.0, player.getY(), 0);
    }
    // #8 Thomas Wang
    @Test
    public void testMoveRightDown() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveRightStrategy());
        float num = (float) (2220 - player.getWidth());
        assertEquals(1110.0, player.getX(), 0);
        player.move(num);
        assertEquals(1135.0, player.getX(), 0);

        player.setMovementStrategy(new MoveDownStrategy());
        num = (float) (1014 - player.getWidth());
        assertEquals(507, player.getY(), 0);
        player.move(num);
        assertEquals(532, player.getY(), 0);
    }

    // #9 Shahbin Hossain
    @Test
    public void testMoveLeftUp() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveLeftStrategy());
        float num = 0;
        assertEquals(1110.0, player.getX(), 0);
        player.move(num);
        assertEquals(1085.0, player.getX(), 0);

        player.setMovementStrategy(new MoveUpStrategy());
        num = 0;
        assertEquals(507.0, player.getY(), 0);
        player.move(num);
        assertEquals(482.0, player.getY(), 0);
    }
    //#10 Shahbin Hossain
    @Test
    public void testMoveLeftDown() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveLeftStrategy());
        float num = 0;
        assertEquals(1110.0, player.getX(), 0);
        player.move(num);
        assertEquals(1085.0, player.getX(), 0);

        player.setMovementStrategy(new MoveDownStrategy());
        num = (float) (1014 - player.getWidth());
        assertEquals(507, player.getY(), 0);
        player.move(num);
        assertEquals(532, player.getY(), 0);
    }
    //#11 Joshua Johnson
    @Test
    public void testMoveDownUp() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveDownStrategy());
        float num = (float) (1014 - player.getWidth());
        assertEquals(507, player.getY(), 0);
        player.move(num);
        assertEquals(532, player.getY(), 0);

        player.setMovementStrategy(new MoveUpStrategy());
        num = 0;
        assertEquals(532, player.getY(), 0);
        player.move(num);
        assertEquals(507, player.getY(), 0);
    }
    //#12 Joshua Johnson
    @Test
    public void testMoveCollisionDown() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveDownStrategy());
        float num = (float) (1014 - player.getWidth());
        assertEquals(507, player.getY(), 0);

        for (int i = 0; i < 1000; i++) {
            player.move(num);
        }
        //cant go higher than 1014 which is the max height
        assertEquals(1014, player.getY(), 0);


    }
    //#13 Joshua Johnson
    @Test
    public void testMoveCollisionUp() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveUpStrategy());
        float num = 0;
        assertEquals(507, player.getY(), 0);

        for (int i = 0; i < 1000; i++) {
            player.move(num);
        }
        //cant go higher than 1014 which is the max height
        assertEquals(0, player.getY(), 0);


    }
    //#14 Kenny Nguyen
    @Test
    public void testMoveCollisionRight() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveRightStrategy());
        float num = (float) (2220 - player.getWidth());
        assertEquals(1110.0, player.getX(), 0);
        for (int i = 0; i < 1000; i++) {
            player.move(num);
        }
        assertEquals(2220, player.getX(), 0);
    }
    //#15 Thomas Wang
    @Test
    public void testMoveCollisionLeft() {
        player = Player.getPlayer();
        player.setPosition(2220 / 2, 1014/ 2);
        //width = 2220
        //height = 1014

        assertEquals(0.0,player.getHeight(),0);
        assertEquals(0.0, player.getWidth(), 0);
        assertEquals(1110,player.getX(),0);
        assertEquals(507,player.getY(),0);
        assertEquals(25.0,player.getMovementSpeed(),0);

        player.setMovementStrategy(new MoveRightStrategy());
        float num = 0;
        assertEquals(1110.0, player.getX(), 0);
        for (int i = 0; i < 1000; i++) {
            player.move(num);
        }
        assertEquals(0, player.getX(), 0);
    }




}
