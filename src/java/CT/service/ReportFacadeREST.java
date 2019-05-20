/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CT.service;

import CT.Consumption;
import CT.Report;
import CT.ReportPK;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author yi
 */
@Stateless
@Path("ct.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @EJB
    private AppuserFacadeREST appuserFacadeREST;

    @EJB
    private ConsumptionFacadeREST consumptionFacadeREST;

    @PersistenceContext(unitName = "Calorie_TrackerPU")
    private EntityManager em;

    private ReportPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;date=dateValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        CT.ReportPK key = new CT.ReportPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Integer(userId.get(0)));
        }
        java.util.List<String> date = map.get("date");
        if (date != null && !date.isEmpty()) {
            key.setDate(new java.util.Date(date.get(0)));
        }
        return key;
    }

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Report entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        CT.ReportPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Report find(@PathParam("id") PathSegment id) {
        CT.ReportPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    public List<Report> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Report.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDate/{date}")
    @Produces("application/json")
    public List<Report> findByDate(@PathParam("date")String str) throws ParseException{
        Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);
        Query query = em.createNamedQuery("Report.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalC/{calC}")
    @Produces("application/json")
    public List<Report> findByCalC(@PathParam("calC") int calC) {
        Query query = em.createNamedQuery("Report.findByCalC");
        query.setParameter("calC", calC);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalB/{calB}")
    @Produces("application/json")
    public List<Report> findByCalB(@PathParam("calB") int calB) {
        Query query = em.createNamedQuery("Report.findByCalB");
        query.setParameter("calB", calB);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalStep/{totalStep}")
    @Produces("application/json")
    public List<Report> findByTotalStep(@PathParam("totalStep") int totalStep) {
        Query query = em.createNamedQuery("Report.findByTotalStep");
        query.setParameter("totalStep", totalStep);
        return query.getResultList();
    }
    
    
    @GET
    @Path("findByCalG/{calG}")
    @Produces("application/json")
    public List<Report> findByCalG(@PathParam("calG") int calG) {
        Query query = em.createNamedQuery("Report.findByCalG");
        query.setParameter("calG", calG);
        return query.getResultList();
    }
    
     
    
    

    //5.1 Just Query the Report table
    
    @GET
    @Path("CalReportOneDay2/{userId}/{date}")
    @Produces("application/json")
    public Object CalReportOneDay2(@PathParam("userId") int userId, @PathParam("date") String str) throws ParseException{
        Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);
        TypedQuery query = em.createQuery("SELECT r.calC,r.calB,(r.calG-r.calC+r.calB) FROM Report r WHERE r.reportPK.userId = :userId "
                + "AND r.reportPK.date = :date",Object[].class);
        query.setParameter("userId", userId);
        query.setParameter("date", date);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row:queryList){
            JsonObject oneDayReport  = Json.createObjectBuilder().add("CalConsumed",(int)row[0]).add("CalBurned", (int)row[1]).add("RemainCalorie", (int)row[2]).build();
            arrayBuilder.add(oneDayReport);
        }
        JsonArray jArray = arrayBuilder.build();
        JsonObject result = (JsonObject) jArray.get(0);
        return result;
    }
    
    //5.2 Just Query the report table
    @GET
    @Path("CalReportPeriodTime2/{userId}/{startDate}/{endDate}")
    @Produces("application/json")
    public Object CalReportPeriodTime2(@PathParam("userId") int userId,@PathParam("startDate")String str1,@PathParam("endDate")String str2) throws ParseException{
        int totalCalB = 0;
        int totalCalC = 0;
        int totalStep = 0;
        Date startDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str1);
        Date endDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str2);
        TypedQuery query = em.createQuery("SELECT r.calB,r.calC,r.totalStep FROM Report r WHERE r.reportPK.userId = :userId "
                + "AND r.reportPK.date BETWEEN :startDate and :endDate",Object[].class);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row:queryList){
            totalCalB += (int)row[0];
            totalCalC += (int)row[1];
            totalStep += (int)row[2];
        }
        JsonObject periodReport  = Json.createObjectBuilder().add("CalConsumed",totalCalB).add("CalBurned", totalCalC).add("totalSteps", totalStep).build();
        return periodReport;
    }
    // RETURN every day 
    @GET
    @Path("CalReportPeriodTimePerDay/{userId}/{startDate}/{endDate}")
    @Produces("application/json")
    public Object CalReportPeriodTimePerDay(@PathParam("userId") int userId,@PathParam("startDate")String str1,@PathParam("endDate")String str2) throws ParseException{
        int totalCalB = 0;
        int totalCalC = 0;
        int totalStep = 0;
        Date startDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str1);
        Date endDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str2);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        TypedQuery query = em.createQuery("SELECT r.calB,r.calC,r.totalStep, r.reportPK.date FROM Report r WHERE r.reportPK.userId = :userId "
                + "AND r.reportPK.date BETWEEN :startDate and :endDate",Object[].class);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row:queryList){
            String strDate = dateFormat.format(row[3]);
            JsonObject oneDayReport  = Json.createObjectBuilder().add("CalBurned",(int)row[0]).add("CalConsumed", (int)row[1]).add("Date", (String)strDate).build();
            arrayBuilder.add(oneDayReport);   
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    
    
        //Task 5.1.2 Get values using EJB
    @GET
    @Path("CalReportOneDay/{userId}/{date}")
    @Produces("application/json")
    public Object CalReportOneDay(@PathParam("userId") int userId, @PathParam("date") String str) throws ParseException{
        Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.reportPK.userId = :userId "
                + "AND r.reportPK.date = :date",Report.class);
        query.setParameter("userId", userId);
        query.setParameter("date", date);
        Report r = query.getResultList().get(0);
        String totalCalc = consumptionFacadeREST.totalCalcByDate(userId, str);
        String totalCalb = appuserFacadeREST.dailyCalB(userId);
        String calG = String.valueOf(r.getCalG());
        String remainCal = String.valueOf(r.getCalG()-Double.parseDouble(totalCalc)+Double.parseDouble(totalCalb));
            
        JsonObject oneDayReport = Json.createObjectBuilder().add("totalCalC",totalCalc).add("totalCalB",totalCalb).add("remainCal",remainCal).build();       
        return oneDayReport;
    }
        
        
    
    //Task 5.2.2 Get values using EJB
    @GET
    @Path("CalReportPeriodTime/{userId}/{startDate}/{endDate}")
    @Produces("application/json")
    public Object CalReportPeriodTime(@PathParam("userId") int userId,@PathParam("startDate")String str1,@PathParam("endDate")String str2) throws ParseException{
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Double totalCalc = 0.1;
        String totalCalB = "";
        int totalStep = 0;
        
        Date startDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str1);
        Date endDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str2);
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.reportPK.userId = :userId "
                + "AND r.reportPK.date BETWEEN :startDate and :endDate",Report.class);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Report> r = query.getResultList();
        for(int i=0;i<r.size();i++){
            Date date = r.get(i).getReportPK().getDate();
            String strDate = dateFormat.format(date);
            Double Calc = Double.parseDouble(consumptionFacadeREST.totalCalcByDate(userId, strDate));
            totalCalc += Calc;
            totalStep += r.get(i).getTotalStep();    
        }
        String cc = String.valueOf(totalCalc);
        String bb = String.valueOf(Double.parseDouble(appuserFacadeREST.dailyCalB(userId)) * r.size());
        JsonObject periodReport = Json.createObjectBuilder().add("TotalCalB",bb).add("TotalCalC",cc).add("TotalStep",totalStep).build();       
        return periodReport;
        
    }
    
    
    
   

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
