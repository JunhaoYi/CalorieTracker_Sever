/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CT.service;

import CT.Appuser;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Path("ct.appuser")
public class AppuserFacadeREST extends AbstractFacade<Appuser> {

    @PersistenceContext(unitName = "Calorie_TrackerPU")
    private EntityManager em;

    public AppuserFacadeREST() {
        super(Appuser.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Appuser entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Appuser entity) {
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
    public Appuser find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appuser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appuser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    //Task3
    @GET
    @Path("findByFirstname/{firstname}")
    @Produces({"application/json"})
    public List<Appuser> findByFirstname(@PathParam("firstname")String firstname){
        Query query = em.createNamedQuery("Appuser.findByFirstname");
        query.setParameter("firstname",firstname);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySurname/{surname}")
    @Produces("application/json")
    public List<Appuser> findBySurname(@PathParam("surname") String surname) {
        Query query = em.createNamedQuery("Appuser.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    @GET
    @Path("findByEmail/{email}")
    @Produces("application/json")
    public List<Appuser> findByEmail(@PathParam("email") String email) {
        Query query = em.createNamedQuery("Appuser.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDob/{dob}")
    @Produces("application/json")
    public List<Appuser> findByDob(@PathParam("dob")String str) throws ParseException{
        Date dob = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);
        Query query = em.createNamedQuery("Appuser.findByDob");
        query.setParameter("dob", dob);
        return query.getResultList();
    }
    
    @GET
    @Path("findByHeight/{height}")
    @Produces("application/json")
    public List<Appuser> findByHeight(@PathParam("height") int height) {
        Query query = em.createNamedQuery("Appuser.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWeight/{weight}")
    @Produces("application/json")
    public List<Appuser> findByWeight(@PathParam("weight") int weight) {
        Query query = em.createNamedQuery("Appuser.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }
    
    @GET
    @Path("findByGender/{gender}")
    @Produces("application/json")
    public List<Appuser> findByGender(@PathParam("gender") String gender) {
        Query query = em.createNamedQuery("Appuser.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
    
    @GET
    @Path("findByAddress/{address}")
    @Produces("application/json")
    public List<Appuser> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("Appuser.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces("application/json")
    public List<Appuser> findByPostcode(@PathParam("postcode") String postcode) {
        Query query = em.createNamedQuery("Appuser.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    
    @GET
    @Path("findByLoa/{loa}")
    @Produces("application/json")
    public List<Appuser> findByLoa(@PathParam("loa") int loa) {
        Query query = em.createNamedQuery("Appuser.findByLoa");
        query.setParameter("loa", loa);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySpm/{spm}")
    @Produces("application/json")
    public List<Appuser> findBySpm(@PathParam("spm") int spm) {
        Query query = em.createNamedQuery("Appuser.findBySpm");
        query.setParameter("spm", spm);
        return query.getResultList();
    }
    
    
    //Task 3.2
    @GET
    @Path("combineFindByFullname/{firstname}/{surname}")
    @Produces("application/json")
    public List<Appuser> combineFindByFullname(@PathParam("firstname")String firstname, @PathParam("surname")String surname){
        TypedQuery<Appuser> query = em.createQuery("SELECT a FROM Appuser a WHERE a.firstname = :firstname AND a.surname = :surname",Appuser.class);
        query.setParameter("firstname", firstname);
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    
    //Task 4.1
    //JSON only accept string and json
    @GET
    @Path("CalBurnPS/{userId}")
    @Produces("application/json")
    public String CalBurnPS(@PathParam("userId") int userId){
        TypedQuery<Appuser> query = em.createQuery("SELECT a FROM Appuser a WHERE a.userId = :userId", Appuser.class);
        query.setParameter("userId", userId);
        Integer weight = query.getResultList().get(0).getWeight();
        Integer spm = query.getResultList().get(0).getSpm();
        double result = weight * 2.205 *0.49 / spm;
        return "{'CalBurnPS': '"+result+"' }";
    }
    
    //Task 4.2
    @GET
    @Path("BRM/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String BRM(@PathParam("userId") int userId){
        TypedQuery<Appuser> query = em.createQuery("SELECT a FROM Appuser a WHERE a.userId = :userId",Appuser.class);
        query.setParameter("userId", userId);
        Appuser a = query.getResultList().get(0);
        Integer weight = a.getWeight();
        Integer height = a.getHeight();
        Date current = new Date();
        Date dob = a.getDob();
        long timeBetween = current.getTime() - dob.getTime();
        double yearBetween = timeBetween / 3.15576e+10;
        int age = (int) Math.floor(yearBetween);
        String gender = a.getGender();
        
        double BMR = 0.0;
        if(gender.equalsIgnoreCase("Male"))
            BMR = (13.75*weight)+(5.003*height)-(6.775*age) + 66.5;
        else
            BMR = (9.563*weight)+(1.85*height)-(4.676*age) + 655.1;
        String result =  String.valueOf(BMR);
        return result;
    }
    
    //Task 4.3
    @GET
    @Path("dailyCalB/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String dailyCalB(@PathParam("userId") int userId){
        double totalCalB = 0.0;
        String str = BRM(userId);
        Double brm = Double.parseDouble(str);
        TypedQuery<Appuser> query = em.createQuery("SELECT a FROM Appuser a WHERE a.userId = :userId",Appuser.class);
        query.setParameter("userId", userId);
        int actLevel = query.getResultList().get(0).getLoa();
        switch(actLevel){
            case 1: 
                totalCalB = brm * 1.2;break;
            case 2:
                totalCalB = brm * 1.375;break;
            case 3:
                totalCalB = brm * 1.55;break;
            case 4:
                totalCalB = brm * 1.725;break;
            case 5:
                totalCalB = brm * 1.9;break;
        }
        String result =  String.valueOf(totalCalB);
        return result;
    }
    
    
    
    

    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
