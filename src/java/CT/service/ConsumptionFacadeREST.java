/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CT.service;

import CT.Consumption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author yi
 */
@Stateless
@Path("ct.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "Calorie_TrackerPU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //Task 3
    @GET
    @Path("findByUserId/{userId}")
    @Produces("application/json")
    public List<Consumption> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Consumption.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDate/{date}")
    @Produces("application/json")
    public List<Consumption> findByDate(@PathParam("date")String str) throws ParseException{
        Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);
        Query query = em.createNamedQuery("Consumption.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFoodId/{foodId}")
    @Produces("application/json")
    public List<Consumption> findByFoodId(@PathParam("foodId") int foodId) {
        Query query = em.createNamedQuery("Consumption.findByFoodId");
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }
    
    @GET
    @Path("findByQuantity/{quantity}")
    @Produces("application/json")
    public List<Consumption> findByQuantity(@PathParam("quantity") int quantity) {
        Query query = em.createNamedQuery("Consumption.findByQuantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }
    
    //Task 3.3
    @GET
    @Path("findByUseridAndDate/{userid}/{date}")
    @Produces("application/json")
    public List<Consumption> findByUseridAndDate(@PathParam("userid") int userid,@PathParam("date") String str) throws ParseException {
        Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);
        TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.userId.userId = :userid AND c.date = :date",Consumption.class);
        query.setParameter("userid", userid);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    //Task 3.4
    @GET
    @Path("findByUseridAndDate2/{userid}/{date}")
    @Produces("application/json")
    public List<Consumption> findByUseridAndDate2(@PathParam("userid") int userid, @PathParam("date") String str) throws ParseException {
        Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);
        Query query = em.createNamedQuery("Consumption.findByUseridAndDate2");
        query.setParameter("userid", userid);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    
    //Task 4.4
    @GET
    @Path("totalCalcByDate/{userId}/{date}")
    @Produces(MediaType.TEXT_PLAIN)
    public String totalCalcByDate(@PathParam("userId") int userId, @PathParam("date") String str) throws ParseException{
        List<Consumption> userConsumptionByDate = findByUseridAndDate(userId,str);
        int totalCalOneDay = 0;
        for(Consumption c: userConsumptionByDate){
            int cal = c.getFoodId().getCalAmount();
            int quantity = c.getQuantity();
            totalCalOneDay += cal * quantity;
        }
        return String.valueOf(totalCalOneDay);
    }
    

    
    
    
    
   

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
