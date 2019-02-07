package beans;

import database.HibernateUtil;
import database.User;
import helping.Result;
import helping.ResultStuart;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "stuart")
@SessionScoped
public class StuartBean {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private User u;
    private HibernateUtil helper;
    private Session session;
    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public HibernateUtil getHelper() {
        return helper;
    }

    public void setHelper(HibernateUtil helper) {
        this.helper = helper;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String loginStuart() {
        FacesContext context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM User u where u.username=:id and u.password=:uk";
        Query query = session.createQuery(hql);
        query.setParameter("id", username);
        query.setParameter("uk", password);

        List result = query.list();
        if (result.isEmpty()) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Pogrešno ste uneli username ili password "));
            session.close();
            return null;
        } else {
            u = (User) result.get(0);
            session.close();
            prosli.removeAll(prosli);
            buduci.removeAll(buduci);
            postaviProsli();
            postaviBuduci();
            return "mainStuart";
        }
    }
    private String porukaPromena="";

    public String getPorukaPromena() {
        return porukaPromena;
    }

    public void setPorukaPromena(String porukaPromena) {
        this.porukaPromena = porukaPromena;
    }
     private String newAirline;

    public String getNewAirline() {
        return newAirline;
    }

    public void setNewAirline(String newAirline) {
        this.newAirline = newAirline;
    }

      public String changeAirline() {
        dohvati();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        u.setAirline(newAirline);
        session.update(u);

        tx.commit();
        session.close();
        porukaPromena="Uspesno ste promenili kompaniju za koju radite.";
        return null;

    }
      public void dohvati() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM User u where u.username=:id and u.password=:uk";
        Query query = session.createQuery(hql);
        query.setParameter("id", username);
        query.setParameter("uk", password);
        List result = query.list();
        u = (User) result.get(0);
        session.close();
    }

    private ArrayList<ResultStuart> prosli = new ArrayList<>();
    private ArrayList<ResultStuart> buduci = new ArrayList<>();

    public ArrayList<ResultStuart> getProsli() {
        return prosli;
    }

    public void setProsli(ArrayList<ResultStuart> prosli) {
        this.prosli = prosli;
    }

    public ArrayList<ResultStuart> getBuduci() {
        return buduci;
    }

    public void setBuduci(ArrayList<ResultStuart> buduci) {
        this.buduci = buduci;
    }

    public void postaviProsli() {
        
        FacesContext context;
        context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select f.idflight,a.name,a.city,a.country,b.name,b.city,b.country,f.duration,f.reservation,f.startDate,f.startTime,f.number from Flight f,Airport a,Airport b,FlightCrew fc where f.airportByStartAirport=a.idairport and f.airportByEndAirport=b.idairport and fc.user=:id and fc.flight=f.idflight and f.status=:status";
        Query query = session.createQuery(hql);
        query.setParameter("id", u);
        query.setParameter("status", 0);
        List<Object[]> lista = query.list();
        if (lista.isEmpty()) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nema karata za avion u odlasku "));
            session.close();

        } else {

            for (Object[] row : lista) {
                int ID = (int) row[0];
                String start_city = (String) row[2];
                String end_city = (String) row[5];
                String end_country = (String) row[6];
                String start_country = (String) row[3];
                String start_air = (String) row[1];
                String end_air = (String) row[4];
                String duration = (String) row[7];

                Time start_time = (Time) row[10];
                int number=(int) row[11];
                int free = (int) row[8];
                Date start = (Date) row[9];
                ResultStuart res = new ResultStuart(ID, start_city, end_city, end_country, start_country, start_air, end_air, duration, start, free, start_time,number);
                prosli.add(res);
            }
            session.close();

        }

    }

    public void postaviBuduci() {
        FacesContext context;
        context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select f.idflight,a.name,a.city,a.country,b.name,b.city,b.country,f.duration,f.reservation,f.startDate,f.startTime,f.number from Flight f,Airport a,Airport b,FlightCrew fc where f.airportByStartAirport=a.idairport and f.airportByEndAirport=b.idairport and fc.user=:id and fc.flight=f.idflight and f.status=:status";
        Query query = session.createQuery(hql);
        query.setParameter("id", u);
        query.setParameter("status", 3);
        List<Object[]> lista = query.list();
        if (lista.isEmpty()) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nema karata za avion u odlasku "));
            session.close();

        } else {

            for (Object[] row : lista) {
                int ID = (int) row[0];
                String start_city = (String) row[2];
                String end_city = (String) row[5];
                String end_country = (String) row[6];
                String start_country = (String) row[3];
                String start_air = (String) row[1];
                String end_air = (String) row[4];
                String duration = (String) row[7];

                Time start_time = (Time) row[10];
                int number=(int) row[11];
                int free = (int) row[8];
                Date start = (Date) row[9];
                ResultStuart res = new ResultStuart(ID, start_city, end_city, end_country, start_country, start_air, end_air, duration, start, free, start_time,number);
                buduci.add(res);
            }
            session.close();

        }

    }
    
    private ArrayList<ResultStuart> lista=new ArrayList<>();

    public ArrayList<ResultStuart> getLista() {
        return lista;
    }

    public void setLista(ArrayList<ResultStuart> pom) {
        this.lista = pom;
    }

   

    
    public String prikaziDetalje(ResultStuart res){
       lista.removeAll(lista);
       lista.add(res);
        
       return "detaljiStuart";
        
        
    }
public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "index";
    }

    public String promenaLozinke(){
         ChangeBeanClass.tip=4;
         return "promenaLozinke";
         
         
         
     }

}
