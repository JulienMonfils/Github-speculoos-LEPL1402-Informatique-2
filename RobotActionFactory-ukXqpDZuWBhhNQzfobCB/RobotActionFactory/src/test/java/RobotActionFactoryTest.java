// This file must *not* be modified!

import com.github.guillaumederval.javagrading.Grade;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotActionFactoryTest {
    private static class RobotTest implements RobotActionFactory.Robot {
        private String result = new String();

        @Override
        public void moveForward() {
            result += "F";
        }

        @Override
        public void turnLeft() {
            result += "L";
        }

        @Override
        public void turnRight() {
            result += "R";
        }

        public String getResult() {
            return result;
        }
    }        
    
    private String execute(String[] commands) {
        RobotActionFactory f = new RobotActionFactory();
        RobotTest robot = new RobotTest();
        RobotActionFactory.Action action = f.parse(commands);
        action.apply(robot);
        return robot.getResult();
    }        
    
    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testNoAction() {
        assertEquals("", execute(new String[] { }));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testSingleAction() {
        assertEquals("F", execute(new String[] { "FORWARD" }));
        assertEquals("L", execute(new String[] { "LEFT" }));
        assertEquals("R", execute(new String[] { "RIGHT" }));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testSequence() {
        assertEquals("FRFLLF", execute(new String[] { "FORWARD",
                                                      "RIGHT",
                                                      "FORWARD",
                                                      "LEFT",
                                                      "LEFT",
                                                      "FORWARD" }));
    }
    
    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testNoRepeat() {
        assertEquals("", execute(new String[] { "REPEAT 0",
                                                "FORWARD",
                                                "RIGHT",
                                                "END REPEAT" }));
    }
   
    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testOneRepeat() {
        assertEquals("FR", execute(new String[] { "REPEAT 1",
                                                  "FORWARD",
                                                  "RIGHT",
                                                  "END REPEAT" }));
    }
   
    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testThreeRepeats() {
        assertEquals("FRFRFR", execute(new String[] { "REPEAT 3",
                                                      "FORWARD",
                                                      "RIGHT",
                                                      "END REPEAT" }));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testStatement() {
        // Those are the commands from the example in "RobotActionFactory.java"
        assertEquals("FFRFRFRFF", execute(new String[] { "FORWARD",
                                                         "REPEAT 3",
                                                         "FORWARD",
                                                         "RIGHT",
                                                         "END REPEAT",
                                                         "FORWARD",
                                                         "FORWARD" }));
    }

    @Test(expected = IllegalArgumentException.class)
    @Grade(value = 1, cpuTimeout = 1000)
    public void testRepeatWithoutEnd() {
        execute(new String[] { "REPEAT 1" });
    }
    
    @Test(expected = IllegalArgumentException.class)
    @Grade(value = 1, cpuTimeout = 1000)
    public void testUnknownAction() {
        execute(new String[] { "FORWARD 100" });
    }    

    @Test
    @Grade(value = 1, cpuTimeout = 1000)
    public void testNested() {
        assertEquals("FRFRFRLFRFRFRL", execute(new String[] { "REPEAT 2",
                                                              "REPEAT 3",
                                                              "FORWARD",
                                                              "RIGHT",
                                                              "END REPEAT",
                                                              "LEFT",
                                                              "END REPEAT" }));
    }

}
