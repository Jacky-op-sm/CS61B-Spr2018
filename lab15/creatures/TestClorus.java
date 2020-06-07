package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus cRep = c.replicate();
        assertNotSame(c, cRep);
        assertEquals(1.00, cRep.energy(), 0.01);
        assertEquals(1.00, c.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2);
        Plip p1 = new Plip(0.5);
        Plip p2 = new Plip(1.5);
        c.attack(p1);
        assertEquals(2.5, c.energy(), 0.01);
        c.attack(p2);
        assertEquals(4, c.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(0.5);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //test Attack plip

        HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
        surrounded2.put(Direction.TOP, new Impassible());
        surrounded2.put(Direction.BOTTOM, new Impassible());
        surrounded2.put(Direction.RIGHT, new Empty());
        surrounded2.put(Direction.LEFT, new Plip());
        Action actual2 = c.chooseAction(surrounded2);
        Action expected2 = new Action(Action.ActionType.ATTACK, Direction.LEFT);
        assertEquals(expected2, actual2);

        //test Replicate
        HashMap<Direction, Occupant> surrounded3 = new HashMap<Direction, Occupant>();
        surrounded3.put(Direction.TOP, new Impassible());
        surrounded3.put(Direction.BOTTOM, new Impassible());
        surrounded3.put(Direction.RIGHT, new Empty());
        surrounded3.put(Direction.LEFT, new Impassible());
        Action actual3 = c.chooseAction(surrounded3);
        Action expected3 = new Action(Action.ActionType.REPLICATE, Direction.RIGHT);
        assertEquals(expected3, actual3);
    }
}
