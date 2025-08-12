/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.elisha.validator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sanuga
 */
public class UserValidateTest {
    
    public UserValidateTest() {
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

    /**
     * Test of isEmailValid method, of class UserValidate.
     */
    @Test
    public void testIsEmailValid() {
        System.out.println("isEmailValid");
        String email = "";
        boolean expResult = false;
        boolean result = UserValidate.isEmailValid(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPwdValid method, of class UserValidate.
     */
    @Test
    public void testIsPwdValid() {
        System.out.println("isPwdValid");
        String pwd = "";
        boolean expResult = false;
        boolean result = UserValidate.isPwdValid(pwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class UserValidate.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        String text = "";
        boolean expResult = false;
        boolean result = UserValidate.isEmpty(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMobileValid method, of class UserValidate.
     */
    @Test
    public void testIsMobileValid() {
        System.out.println("isMobileValid");
        String mobile = "";
        boolean expResult = false;
        boolean result = UserValidate.isMobileValid(mobile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPWDValid method, of class UserValidate.
     */
    @Test
    public void testIsPWDValid() {
        System.out.println("isPWDValid");
        String pwd = "";
        boolean expResult = false;
        boolean result = UserValidate.isPWDValid(pwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmailExist method, of class UserValidate.
     */
    @Test
    public void testIsEmailExist() {
        System.out.println("isEmailExist");
        String email = "";
        boolean expResult = false;
        boolean result = UserValidate.isEmailExist(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSupplierExist method, of class UserValidate.
     */
    @Test
    public void testIsSupplierExist() {
        System.out.println("isSupplierExist");
        String email = "";
        boolean expResult = false;
        boolean result = UserValidate.isSupplierExist(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
