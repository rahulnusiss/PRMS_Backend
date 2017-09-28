/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.edu.nus.iss.phoenix.schedule.restful;


import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Prasanna
 */
public class ScheduleRESTServiceTest {
    
    
    
    public ScheduleRESTServiceTest() {
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
     * Test of getProgramSlot method, of class ScheduleRESTService.
     * @param inputDate
     * @return 
     */
    
    public Date getFormatedDate(String inputDate){
    
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDateOfProgram = null;
        try{
             sqlDateOfProgram= new java.sql.Date(formatter.parse(inputDate).getTime());
        }catch(ParseException ex){
                ex.printStackTrace();
        }
        return sqlDateOfProgram;
    }
    
    
    @Test
    public void testGetProgramSlot() {
        System.out.println("getProgramSlot");
        ScheduleRESTService instance = new ScheduleRESTService();
        ProgramSlot expectedPs = new ProgramSlot();
        expectedPs.setProgramName("test");
        expectedPs.setDateofProgram(getFormatedDate("2017-09-14"));
        expectedPs.setStartTime(Time.valueOf("07:30:00"));
        expectedPs.setDuration(Time.valueOf("03:00:00"));
        
        instance.createSchedule(expectedPs);
        ProgramSlot resultPs=  instance.getProgramSlot(getFormatedDate("2017-09-14"));
        
        assertEquals(expectedPs.getDateofProgram(), resultPs.getDateofProgram());
        assertEquals(expectedPs.getDuration(), resultPs.getDuration());
        assertEquals(expectedPs.getStartTime(), resultPs.getStartTime());
        assertEquals(expectedPs.getProgramName(), resultPs.getProgramName());
        
    }

    /**
     * Test of getAllProgramSlots method, of class ScheduleRESTService.
     */
    @Test
    public void testGetAllProgramSlots() {
        System.out.println("getAllProgramSlots");
        ScheduleRESTService instance = new ScheduleRESTService();
        ProgramSlots expResult = null;
        ProgramSlots result = instance.getAllProgramSlots();
        
        if(result.getPsList() ==  null || result.getPsList().isEmpty()){
                ProgramSlot expectedPs = new ProgramSlot();
                expectedPs.setDateofProgram(getFormatedDate("2018-09-14"));
                expectedPs.setDuration(Time.valueOf("00:30:00"));
                expectedPs.setStartTime(Time.valueOf("12:30:00"));
                expectedPs.setProgramName("test");
                
                instance.createSchedule(expectedPs);
                result = instance.getAllProgramSlots();
        }
        
        assertNotNull(result.getPsList().size()>0);
    }

    /**
     * Test of createSchedule method, of class ScheduleRESTService.
     */
    @Test
    public void testCreateSchedule() {
       System.out.println("createSchedule");
       ScheduleRESTService instance = new ScheduleRESTService();
       //instance.deleteSchedule("2016-08-14");
       
       ProgramSlot expectedPs = new ProgramSlot();
        expectedPs.setDateofProgram(getFormatedDate("2018-09-14"));
        expectedPs.setDuration(Time.valueOf("00:30:00"));
        expectedPs.setStartTime(Time.valueOf("12:30:00"));
        expectedPs.setProgramName("test");

        instance.createSchedule(expectedPs);
        ProgramSlot resultPs=  instance.getProgramSlot(getFormatedDate("2018-09-14"));
        
        assertEquals(expectedPs.getDateofProgram(), resultPs.getDateofProgram());
        assertEquals(expectedPs.getDuration(), resultPs.getDuration());
        assertEquals(expectedPs.getStartTime(), resultPs.getStartTime());
        assertEquals(expectedPs.getProgramName(), resultPs.getProgramName());

    }

    /**
     * Test of modifySchedule method, of class ScheduleRESTService.
     */
    @Test
    public void testModifySchedule() {
        System.out.println("modifySchedule");
        ProgramSlot sampleProgramSlot = new ProgramSlot();
        sampleProgramSlot.setDateofProgram(getFormatedDate("2017-09-29"));
        sampleProgramSlot.setStartTime(Time.valueOf("09:00:00"));
        sampleProgramSlot.setDuration(Time.valueOf("01:00:00"));
        sampleProgramSlot.setProgramName("test");
        
        ScheduleRESTService instance = new ScheduleRESTService();
        
        ProgramSlot editProgramSlot = instance.getProgramSlot(getFormatedDate("2017-09-29"));
        
        if(editProgramSlot == null ){
        
                instance.createSchedule(sampleProgramSlot);
                editProgramSlot = instance.getProgramSlot(getFormatedDate("2017-09-29"));
        }
        
        editProgramSlot.setProgramName("test");
        editProgramSlot.setStartTime(Time.valueOf("13:20:00"));
        editProgramSlot.setDuration(Time.valueOf("0:30:00"));
        //editProgramSlot.setDateofProgram(getFormatedDate("2016-09-09"));
        
        instance.modifySchedule(editProgramSlot);
        ProgramSlot targetProgramSlot = instance.getProgramSlot(getFormatedDate("2017-09-29"));
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(editProgramSlot.getProgramName(), targetProgramSlot.getProgramName());
        assertEquals(editProgramSlot.getStartTime(), targetProgramSlot.getStartTime());
        assertEquals(editProgramSlot.getDuration() , targetProgramSlot.getDuration());
        assertEquals(editProgramSlot.getDateofProgram(), targetProgramSlot.getDateofProgram());
        
        
    }

    
    /**
     * Test of deleteSchedule method, of class ScheduleRESTService.
     */
    @Test
    public void testDeleteSchedule() {
        System.out.println("deleteSchedule");
        ProgramSlot sampleProgramSlot = new ProgramSlot();
        sampleProgramSlot.setDateofProgram(getFormatedDate("2017-09-28"));
        sampleProgramSlot.setStartTime(Time.valueOf("08:00:00"));
        sampleProgramSlot.setDuration(Time.valueOf("02:00:00"));
        sampleProgramSlot.setProgramName("test");
        
        ScheduleRESTService instance = new ScheduleRESTService();
        ProgramSlot ps = instance.getProgramSlot(getFormatedDate("2017-09-28"));
        if(ps == null){
                instance.createSchedule(sampleProgramSlot);
        }
        instance.deleteSchedule("2017-09-28");
        ProgramSlot result= instance.getProgramSlot(getFormatedDate("2017-09-28"));
        assertNull(result);
        
    }
    
}
