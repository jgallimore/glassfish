/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.glassfish.embed;

import com.sun.enterprise.universal.io.SmartFile;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author bnevins
 */

public class DeployWebAppTest {

    public DeployWebAppTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            System.out.println("Starting Server on port 9999");
            myGF = Server.create(9999, null);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        myGF.stop();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //@Test( expected=MiniXmlParserException.class)
    @Test
    public void garbageWar() throws Exception{
        File qqq = new File("xxxxxx");
        Application app = null;
        
        try {
            app = myGF.deploy(qqq);
        }
        catch(Exception e) {
            System.out.println("Caught an Exception as Expected: " + e);
            return;
        }
        fail("Did not get expected Exception from deploying garbage!");
    }

    @Test
    public void foo() throws Exception{
        File simpleWar = SmartFile.sanitize(new File("target/test-classes/simple.war"));
        assertTrue(simpleWar.exists());
        System.out.println("Located simple.war");
        Application app = null;
        
        try {
            app = myGF.deploy(simpleWar);
        }
        catch(Exception e) {
            System.out.println("Unexpected Exception: " + e);
            fail("Failure deploying SimpleWar");
        }
        app.undeploy();
        System.out.println("Simple War deployed and undeployed OK...");
    }
    
    private static Server myGF;
}
