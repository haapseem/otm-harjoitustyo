/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sleepingduck
 */
public class MaksukorttiTest {
    
    public MaksukorttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        Maksukortti kortti = new Maksukortti(10);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void negLataus() {
        Maksukortti kortti = new Maksukortti(10);
        String s = kortti.toString();
        kortti.lataaRahaa(-10);
        assertEquals(s, kortti.toString());
    }
    
    @Test
    public void syoEdullisesti(){
        Maksukortti m = new Maksukortti(2.5);
        m.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa", m.toString());
    }
    
    @Test
    public void syoMaukkaasti(){
        Maksukortti m = new Maksukortti(4);
        m.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 0.0 euroa", m.toString());
    }
    
}
