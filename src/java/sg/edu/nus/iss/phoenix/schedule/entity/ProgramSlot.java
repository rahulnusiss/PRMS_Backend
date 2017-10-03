/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;
import java.sql.Date;
import java.sql.Time;

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
     * @param id
     * @param duration
     * @param dateofProgram
     * @param startTime
     * @param programName
     * @param PresenterId
     * @param ProducerId
     */
    public ProgramSlot(int id,Time duration, Date dateofProgram, Time startTime, String programName, String PresenterId,String ProducerId) {
        this.id=id;
        this.duration = duration;
        this.dateofProgram = dateofProgram;
        this.startTime = startTime;
        this.programName = programName;
        this.presenterId=PresenterId;
        this.producerId=ProducerId;
    }

    /**
     *
     * @param presenterId
     */
    public void setPresenterId(String presenterId) {
        this.presenterId = presenterId;
    }

    /**
     *
     * @return
     */
    public String getPresenterId() {
        return presenterId;
    }

    /**
     *
     * @param producerId
     */
    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    /**
     *
     * @return
     */
    public String getProducerId() {
        return producerId;
    }
    
    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param dateofProgram
     */
    public void setDateofProgram(Date dateofProgram) {
        this.dateofProgram = dateofProgram;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }

    /**
     *
     * @param programName
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     */
    public Date getDateofProgram() {
        return dateofProgram;
    }

    /**
     *
     * @return
     */
    public Time getDuration() {
        return duration;
    }

    /**
     *
     * @return
     */
    public String getProgramName() {
        return programName;
    }

    /**
     *
     * @return
     */
    public Time getStartTime() {
        return startTime;
    }
    
    
    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     * @return  String format for ProgramSlot object
     */
        @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nProgramSlot class, mapping to table program-slot\n");
        out.append("Persistent attributes: \n"); 
        out.append("Id=").append(this.id).append("\n");
        out.append("date = ").append(this.dateofProgram).append("\n");
        out.append("duration = ").append(this.duration).append("\n");
        out.append("program Name = ").append(this.programName).append("\n"); 
        out.append("startTime = ").append(this.startTime).append("\n"); 
        out.append("presenterId = ").append(this.presenterId).append("\n"); 
        out.append("producerId = ").append(this.producerId).append("\n"); 
        
        return out.toString();
    }

}
