/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sg.edu.nus.iss.phoenix.maintainuser.restful.UserRESTServiceTest;
import sg.edu.nus.iss.phoenix.schedule.restful.ScheduleRESTServiceTest;

/**
 *
 * @author Prasanna
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ScheduleServiceTest.class,ScheduleRESTServiceTest.class,UserRESTServiceTest.class})
public class NewTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
