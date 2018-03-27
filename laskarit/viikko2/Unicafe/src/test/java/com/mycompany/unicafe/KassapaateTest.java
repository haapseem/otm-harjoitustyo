/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
    
    Kassapaate kp;
    
    public KassapaateTest() {
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
    public void luotuPaateOikein(){
        kp = new Kassapaate();
        assertTrue((kp.kassassaRahaa()==100000)&&
                (kp.edullisiaLounaitaMyyty() + 
                        kp.maukkaitaLounaitaMyyty()==0));
    }
    
    @Test
    public void edullinen1(){
        kp = new Kassapaate();
        int x = kp.syoEdullisesti(500);
        assertTrue(x==260&&kp.kassassaRahaa()==100240);
    }
    
    @Test
    public void edullinen2(){
        kp = new Kassapaate();
        assertTrue(kp.syoEdullisesti(230)==230&&kp.kassassaRahaa()==100000);
    }
    
    @Test
    public void edullinen3(){
        kp = new Kassapaate();
        Maksukortti mk = new Maksukortti(240);
        assertTrue(kp.syoEdullisesti(mk));
    }
    
    @Test
    public void edullinen4(){
        kp = new Kassapaate();
        Maksukortti mk = new Maksukortti(230);
        assertTrue(!kp.syoEdullisesti(mk));
    }
    
    @Test
    public void maukas1(){
        kp = new Kassapaate();
        int x = kp.syoMaukkaasti(500);
        assertTrue((x==100)&&(kp.kassassaRahaa()==100400));
    }
    
    @Test
    public void maukas2(){
        kp = new Kassapaate();
        assertTrue(kp.syoMaukkaasti(230)==230&&kp.kassassaRahaa()==100000);
    }
    
    @Test
    public void maukas3(){
        kp = new Kassapaate();
        Maksukortti mk = new Maksukortti(400);
        assertTrue(kp.syoMaukkaasti(mk));
    }
    
    @Test
    public void maukas4(){
        kp = new Kassapaate();
        Maksukortti mk = new Maksukortti(230);
        assertTrue(!kp.syoMaukkaasti(mk));
    }
    
    @Test
    public void kortti1(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(240);
        kp.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==0);
    }
    
    @Test
    public void kortti2(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(230);
        kp.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==230);
    }
    
    @Test
    public void kortti3(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(240);
        kp.syoEdullisesti(kortti);
        assertTrue(kp.kassassaRahaa()==100000);
    }
    
    @Test
    public void kortti4(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(640);
        kp.syoEdullisesti(kortti);
        kp.syoMaukkaasti(kortti);
        assertTrue(kp.edullisiaLounaitaMyyty()+kp.maukkaitaLounaitaMyyty()==2);
    }
    
    @Test
    public void kortti5(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(540);
        kp.syoEdullisesti(kortti);
        kp.syoMaukkaasti(kortti);
        assertTrue(kp.edullisiaLounaitaMyyty()+kp.maukkaitaLounaitaMyyty()==1);
    }
    
    @Test
    public void kortti6(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(640);
        kp.syoEdullisesti(kortti);
        kp.syoMaukkaasti(kortti);
        assertTrue(kp.kassassaRahaa()==100000);
    }
    
    @Test
    public void lataa1(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(0);
        kp.lataaRahaaKortille(kortti, 10);
        assertTrue(kp.kassassaRahaa()==100010);
    }
    
    @Test
    public void lataa2(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(0);
        kp.lataaRahaaKortille(kortti, 10);
        assertTrue(kortti.saldo()==10);
    }
    
    @Test
    public void lataa3(){
        kp = new Kassapaate();
        Maksukortti kortti = new Maksukortti(0);
        kp.lataaRahaaKortille(kortti, -1);
        assertTrue(kortti.saldo()==0);
    }
    
    
}
