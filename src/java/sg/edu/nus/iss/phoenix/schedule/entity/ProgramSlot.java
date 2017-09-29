/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;
import java.sql.Date;
import java.sql.Time;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author Prasanna
 */
public class ProgramSlot {
    
    private Integer id;
    private String presenterId;
    private String producerId;
    private Date dateofProgram;
    private Time startTime;
    private Time duration;
    private String programName;

    /**
     *default constructor for programSlot
     */
    public ProgramSlot() {
        
    }
    
    /**
     *Parameterized Constructor for programSlot
     * @param duration
     * @param dateofProgram
     * @param startTime
     * @param programName
     */
    public ProgramSlot(Time duration, Date dateofProgram, Time startTime, String programName) {
        this.duration = duration;
        this.dateofProgram = dateofProgram;
        this.startTime = startTime;
        this.programName = programName;
    }

    public void setPresenterId(String presenterId) {
        this.presenterId = presenterId;
    }

    public String getPresenterId() {
        return presenterId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getProducerId() {
        return producerId;
    }
    
   
 
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    
    
    public void setDateofProgram(Date dateofProgram) {
        this.dateofProgram = dateofProgram;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getDateofProgram() {
        return dateofProgram;
    }

    public Time getDuration() {
        return duration;
    }

    public String getProgramName() {
        return programName;
    }

    public Time getStartTime() {
        return startTime;
    }
    
    
    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
        @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nProgramSlot class, mapping to table program-slot\n");
        out.append("Persistent attributes: \n"); 
        out.append("date = ").append(this.dateofProgram).append("\n");
        out.append("duration = ").append(this.duration).append("\n");
        out.append("program Name = ").append(this.programName).append("\n"); 
        out.append("startTime = ").append(this.startTime).append("\n"); 
        return out.toString();
    }

}
