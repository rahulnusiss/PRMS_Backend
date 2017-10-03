/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author Prasanna
 */
@Path("programslot")
@RequestScoped
public class ScheduleRESTService {
    
    @Context
    private UriInfo context;
    
    private final ScheduleService service;

    /**
     * Creates a new instance of ScheduleRESTService
     */
    public ScheduleRESTService() {
        service = new ScheduleService();
    }

    
   /**
     * Retrieves representation of an instance of resource
     * @param id
     * @return an instance of resource
     */
    @GET
    @Path("/retrieve/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlot getProgramSlotById(@PathParam("id") Integer id) {
       ProgramSlot ps = null;
        if(id != null){
        ps = service.findProgramSlotById(id);
        }
        return ps;
    }
    
     /**
     * Retrieves representation of an instance of resource
     * @param dateofProgram
     * @return an instance of resource
     */
    @GET
    @Path("/retrievebyDate/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlot getProgramSlotByDate(@PathParam("date") String dateofProgram) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date date = null;
        try {
            date = dateFormatter.parse(dateofProgram);
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleRESTService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (date != null) {
            java.sql.Date sqlDateOfProgram = new java.sql.Date(date.getTime());

            ProgramSlot ps = service.findProgramSlotByDate(sqlDateOfProgram);

            return ps;

        }
        return null;
    }
  
    
    /**
     *Retrieves all Program Slots
     * @return
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlots getAllProgramSlots() {
        ArrayList<ProgramSlot> pslist = service.findAllProgramSlots();
        ProgramSlots programSlotList = new ProgramSlots();
        programSlotList.setPsList(new ArrayList<>());
        for (int i = 0; i < pslist.size(); i++) {
            programSlotList.getPsList().add(
            new ProgramSlot(pslist.get(i).getId(),pslist.get(i).getDuration(), pslist.get(i).getDateofProgram(),pslist.get(i).getStartTime(), pslist.get(i).getProgramName(),pslist.get(i).getPresenterId(),pslist.get(i).getProducerId()));
                
        }
   return programSlotList;
    }
    
    /**
     * PUT method for updating an instance of resource
     * @param ps
     * @return 
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String modifySchedule(ProgramSlot ps) {
        return service.processModify(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param ps
     * @return 
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createSchedule(ProgramSlot ps) {
        return service.processCreate(ps);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param id
     * @return 
     */
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteSchedule(@PathParam("id") int id) {
         return service.processDelete(id);
    }

}
