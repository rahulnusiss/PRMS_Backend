/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
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
public class ScheduleServiceTest {
    
    public ScheduleServiceTest() {
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
     * Test of findProgramSlotByCriteria method, of class ScheduleService.
     */
     private Date getFormatedDate(String inputDate){
    
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
    public void testFindProgramSlotByCriteria() {
        System.out.println("findProgramSlotByCriteria");
        ProgramSlot expectedPs = new ProgramSlot();
        
        expectedPs.setProgramName("test");
        expectedPs.setDateofProgram(getFormatedDate("2017-09-14"));
        expectedPs.setStartTime(Time.valueOf("07:30:00"));
        expectedPs.setDuration(Time.valueOf("03:00:00"));
        expectedPs.setPresenterId("dilbert");
        expectedPs.setProducerId("wally");
 
        ScheduleService instance = new ScheduleService();
        ArrayList<ProgramSlot> result = instance.findProgramSlotByCriteria(expectedPs);
        
        for(ProgramSlot ps: result){
            if (ps != null) {
                assertEquals(expectedPs.getDateofProgram(), ps.getDateofProgram());
                assertEquals(expectedPs.getStartTime(), ps.getStartTime());
                assertEquals(expectedPs.getDuration(), ps.getDuration());
                assertEquals(expectedPs.getProgramName(), ps.getProgramName());

            }
        }
    }

    /**
     * Test of findProgramSlotByDate method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotByDate() {
        System.out.println("findProgramSlotByDate");
        ScheduleService instance = new ScheduleService();
        
        ProgramSlot expectedPs = new ProgramSlot();
        expectedPs.setDateofProgram(getFormatedDate("2018-09-14"));
        expectedPs.setDuration(Time.valueOf("00:30:00"));
        expectedPs.setStartTime(Time.valueOf("12:30:00"));
        expectedPs.setProgramName("test");
        expectedPs.setPresenterId("dilbert");
        expectedPs.setProducerId("wally");
        
 
        ProgramSlot result = instance.findProgramSlotByDate(expectedPs.getDateofProgram());
        if(result==null){
                instance.processCreate(expectedPs);
                ProgramSlot createdPs = instance.findProgramSlotByDate(expectedPs.getDateofProgram());
                assertEquals(expectedPs.getId(), createdPs.getId());
            }else {
        
                        assertEquals(expectedPs.getDateofProgram(), result.getDateofProgram());
            }
        
    }

    /**
     * Test of findProgramSlotById method, of class ScheduleService.
     */
    @Test
    public void testFindProgramSlotById() {
        System.out.println("findProgramSlotById");
        int id = 49;
        ScheduleService instance = new ScheduleService();
        ProgramSlot expResult = new ProgramSlot();
        expResult.setId(id);
        
        ProgramSlot result = instance.findProgramSlotById(id);
        assertTrue(Objects.equals(expResult.getId(), result.getId()));
       
    }

    /**
     * Test of findAllProgramSlots method, of class ScheduleService.
     */
    @Test
    public void testFindAllProgramSlots() {
        System.out.println("findAllProgramSlots");
        ScheduleService instance = new ScheduleService();
        ArrayList<ProgramSlot> result = instance.findAllProgramSlots();
        assertNotNull(result.size()>0);
    }

    /**
     * Test of processCreate method, of class ScheduleService.
     */
    @Test
    public void testProcessCreate() {
        System.out.println("processCreate");
        
        ScheduleService instance = new ScheduleService();
        
        ProgramSlot expectedPs = new ProgramSlot();
        expectedPs.setDateofProgram(getFormatedDate("2018-09-14"));
        expectedPs.setDuration(Time.valueOf("00:30:00"));
        expectedPs.setStartTime(Time.valueOf("12:30:00"));
        expectedPs.setProgramName("test");
        expectedPs.setPresenterId("dilbert");
        expectedPs.setProducerId("wally");
                

        String expected="{\"status\":\"true\",\"message\":\"ProgramSlot details Inserted Successfully...\"}";
        String result = instance.processCreate(expectedPs);
        
        assertEquals(expected, result);
    }

    /**
     * Test of processModify method, of class ScheduleService.
     */
    @Test
    public void testProcessModify() {
        System.out.println("modifySchedule");
        
        ProgramSlot sampleProgramSlot = new ProgramSlot();
        
        sampleProgramSlot.setDateofProgram(getFormatedDate("2017-09-29"));
        sampleProgramSlot.setStartTime(Time.valueOf("12:00:00"));
        sampleProgramSlot.setDuration(Time.valueOf("0:30:00"));
        sampleProgramSlot.setProgramName("test");
        sampleProgramSlot.setPresenterId("dilbert");
        sampleProgramSlot.setProducerId("wally");
        sampleProgramSlot.setId(51);
                
        
        ScheduleService instance = new ScheduleService();
        
        ProgramSlot editProgramSlot = instance.findProgramSlotById(sampleProgramSlot.getId());
        
        if(editProgramSlot == null ){
        
                instance.processCreate(sampleProgramSlot);
                editProgramSlot = instance.findProgramSlotById(sampleProgramSlot.getId());
        }
        
        editProgramSlot.setProgramName("test");
        editProgramSlot.setStartTime(Time.valueOf("13:20:00"));
        editProgramSlot.setDuration(Time.valueOf("0:30:00"));
        editProgramSlot.setPresenterId("dilbert");
        editProgramSlot.setProducerId("wally");
        editProgramSlot.setDateofProgram(getFormatedDate("2016-09-09"));
        editProgramSlot.setId(sampleProgramSlot.getId());
        
        String resultStr =instance.processModify(editProgramSlot);
        ProgramSlot targetProgramSlot = instance.findProgramSlotById(editProgramSlot.getId());
       
        assertEquals(editProgramSlot.getProgramName(), targetProgramSlot.getProgramName());
        assertEquals(editProgramSlot.getStartTime(), targetProgramSlot.getStartTime());
        assertEquals(editProgramSlot.getDuration() , targetProgramSlot.getDuration());
        assertEquals(editProgramSlot.getDateofProgram(), targetProgramSlot.getDateofProgram());
        assertEquals(editProgramSlot.getPresenterId(), targetProgramSlot.getPresenterId());
        assertEquals(editProgramSlot.getProducerId(), targetProgramSlot.getProducerId());
        
        String expResultStr = "{\"status\":\"true\",\"message\":\"ProgramSlot Modified Successfully...\"}";
        assertEquals(expResultStr, resultStr);
        
    }

    /**
     * Test of processDelete method, of class ScheduleService.
     */
    @Test
    public void testProcessDelete() {
        System.out.println("processDelete");
        ProgramSlot sampleProgramSlot = new ProgramSlot();
        
        sampleProgramSlot.setDateofProgram(getFormatedDate("2017-09-28"));
        sampleProgramSlot.setStartTime(Time.valueOf("08:00:00"));
        sampleProgramSlot.setDuration(Time.valueOf("02:00:00"));
        sampleProgramSlot.setProgramName("test");
        sampleProgramSlot.setPresenterId("dilbert");
        sampleProgramSlot.setProducerId("wally");
        sampleProgramSlot.setId(200);
        
        
        ScheduleService instance = new ScheduleService();
        ProgramSlot ps = instance.findProgramSlotById(sampleProgramSlot.getId());
        if(ps == null){
                instance.processCreate(sampleProgramSlot);
        }
        instance.processDelete(sampleProgramSlot.getId());
        ProgramSlot result= instance.findProgramSlotById(sampleProgramSlot.getId());
        assertNull(result);

    }
    
}
