/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
     * Creates a new instance of RadioProgramRESTService
     */
    public ScheduleRESTService() {
        service = new ScheduleService();
    }

    /**
     * Retrieves representation of an instance of resource
     * @param psName
     * @return an instance of resource
     */
    @GET
    @Path("/retrieve")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlot getProgramSlot(String psName) {
       ProgramSlot ps = null;
        if(psName != null){
        ps = service.findProgramSlot(psName);
        }
        return ps;
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlots getAllProgramSlots() {
        ArrayList<ProgramSlot> pslist = service.findAllProgramSlots();
        ProgramSlots programSlotList = new ProgramSlots();
        programSlotList.setPsList(new ArrayList<>());
        for (int i = 0; i < pslist.size(); i++) {
            programSlotList.getPsList().add(
            new ProgramSlot(pslist.get(i).getDuration(), pslist.get(i).getDateofProgram(),pslist.get(i).getStartTime(), pslist.get(i).getProgramName()));
                
        }
   return programSlotList;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param ps
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifySchedule(ProgramSlot ps) {
        service.processModify(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param ps
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createSchedule(ProgramSlot ps) {
        service.processCreate(ps);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param name
     */
    @DELETE
    @Path("/delete/{psname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteSchedule(@PathParam("psname") String name) {
        String name2;
        try { 
            name2 = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDelete(name2);
    }

    
}
