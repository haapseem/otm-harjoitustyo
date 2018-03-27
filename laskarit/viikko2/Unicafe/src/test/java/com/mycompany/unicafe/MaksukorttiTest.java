package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    int arvo = 10;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikein(){
        kortti = new Maksukortti(arvo);
        assertTrue(kortti.saldo()==arvo);
    }
    
    @Test
    public void lataaOikei(){
        kortti = new Maksukortti(arvo);
        kortti.lataaRahaa(arvo);
        assertTrue(kortti.saldo()==arvo*2);
    }
    
    @Test
    public void ottaaOikein(){
        kortti = new Maksukortti(arvo);
        kortti.otaRahaa(arvo);
        assertTrue(kortti.saldo()==0);
    }
    
    @Test
    public void ottaaOikein2(){
        kortti = new Maksukortti(arvo);
        kortti.otaRahaa(arvo+1);
        assertTrue(kortti.saldo()==arvo);
    }
    
    @Test
    public void ottaaOikein3(){
        kortti = new Maksukortti(arvo);
        
        assertTrue(kortti.otaRahaa(arvo));
    }
    
    @Test
    public void ottaaOikein4(){
        kortti = new Maksukortti(arvo);
        //kortti.otaRahaa(arvo+1);
        assertTrue(!kortti.otaRahaa(arvo+1));
    }
    
}
