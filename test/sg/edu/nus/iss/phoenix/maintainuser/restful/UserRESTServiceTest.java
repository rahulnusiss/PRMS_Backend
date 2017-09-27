/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.maintainuser.restful;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author jackle
 */
public class UserRESTServiceTest {
    
    public UserRESTServiceTest() {
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
     * Test of getAllUsers method, of class UserRESTService.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        UserRESTService instance = new UserRESTService();
        Users expResult = null;
        Users result = instance.getAllUsers();
        if(result.getUsrList() == null ||
               result.getUsrList().size() == 0) {
            User expectedUsr = new User();
            expectedUsr.setName("Jack Le");
            expectedUsr.setPassword("password");
            Role e1 = new Role("producer");
            Role e2 = new Role("presenter");
            ArrayList<Role> roles = new ArrayList<Role>();
            roles.add(e1);
            roles.add(e2);
            expectedUsr.setRoles(roles);
            expectedUsr.setId("jackle2");
            instance.createUser(expectedUsr);
            result = instance.getAllUsers();
        }
        
        assertNotNull(result.getUsrList().size() > 0);
        
    }

    /**
     * Test of updateUser method, of class UserRESTService.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User sampleUser = new User();
        sampleUser.setName("Jack Le");
        sampleUser.setPassword("password");
        Role e1 = new Role("producer");
        Role e2 = new Role("presenter");
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(e1);
        roles.add(e2);
        sampleUser.setRoles(roles);
        sampleUser.setId("jackle2");
        
        
        UserRESTService instance = new UserRESTService();
        User editUser = instance.getUser("jackle2");
        if(editUser == null) {
            instance.createUser(sampleUser);
            editUser = instance.getUser("jackle2");
        }
        
        editUser.setName("Jack Le1");
        editUser.setPassword("password1");
        e1 = new Role("producer1");
        e2 = new Role("presenter1");
        roles = new ArrayList<Role>();
        roles.add(e1);
        roles.add(e2);
        editUser.setRoles(roles);
        editUser.setId("jackle2");
        instance.updateUser(editUser);
        User targetUser = instance.getUser("jackle2");
        assertEquals(editUser.getId(), targetUser.getId());
        assertEquals(editUser.getName(), targetUser.getName());
        assertEquals(editUser.getPassword(), targetUser.getPassword());
        assertEquals(editUser.getRoles().get(0).getRole(), targetUser.getRoles().get(0).getRole());
        assertEquals(editUser.getRoles().get(1).getRole(), targetUser.getRoles().get(1).getRole());
    }

    /**
     * Test of createUser method, of class UserRESTService.
     */
    @Test
    public void testCreateUser() {
        
        System.out.println("createUser");
        UserRESTService instance = new UserRESTService();
        instance.deleteUser("jackle2");
        User expectedUsr = new User();
        expectedUsr.setName("Jack Le");
        expectedUsr.setPassword("password");
        Role e1 = new Role("producer");
        Role e2 = new Role("presenter");
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(e1);
        roles.add(e2);
        expectedUsr.setRoles(roles);
        expectedUsr.setId("jackle2");
        
        instance.createUser(expectedUsr);
        User user = instance.getUser("jackle2");
        
        assertEquals(expectedUsr.getId(), user.getId());
        assertEquals(expectedUsr.getName(), user.getName());
        assertEquals(expectedUsr.getPassword(), user.getPassword());
        assertEquals(expectedUsr.getRoles().get(0).getRole(), user.getRoles().get(0).getRole());
        assertEquals(expectedUsr.getRoles().get(1).getRole(), user.getRoles().get(1).getRole());
    }

    /**
     * Test of deleteUser method, of class UserRESTService.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        User expectedUsr = new User();
        expectedUsr.setName("Jack Le");
        expectedUsr.setPassword("password");
        Role e1 = new Role("producer");
        Role e2 = new Role("presenter");
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(e1);
        roles.add(e2);
        expectedUsr.setRoles(roles);
        expectedUsr.setId("jackle2");
        
        
        UserRESTService instance = new UserRESTService();
        User user = instance.getUser("jackle2");
        if(user == null) {
            instance.createUser(expectedUsr);
        }
        
        instance.deleteUser("jackle2");
        user = instance.getUser("jackle2");
        assertNull(user);
    }

    /**
     * Test of getUser method, of class UserRESTService.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        UserRESTService instance = new UserRESTService();
        instance.deleteUser("jackle2");
        User expectedUsr = new User();
        expectedUsr.setName("Jack Le");
        expectedUsr.setPassword("password");
        Role e1 = new Role("producer");
        Role e2 = new Role("presenter");
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(e1);
        roles.add(e2);
        expectedUsr.setRoles(roles);
        expectedUsr.setId("jackle2");
        
        instance.createUser(expectedUsr);
        User user = instance.getUser("jackle2");
        
        assertEquals(expectedUsr.getId(), user.getId());
        assertEquals(expectedUsr.getName(), user.getName());
        assertEquals(expectedUsr.getPassword(), user.getPassword());
        assertEquals(expectedUsr.getRoles().get(0).getRole(), user.getRoles().get(0).getRole());
        assertEquals(expectedUsr.getRoles().get(1).getRole(), user.getRoles().get(1).getRole());
    }
    
}
