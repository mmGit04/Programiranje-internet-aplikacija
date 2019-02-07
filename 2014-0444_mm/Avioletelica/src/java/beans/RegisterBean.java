package beans;

import database.AirlineCompany;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import database.HibernateUtil;
import database.RegisterUser;
import database.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean(name = "register")
@RequestScoped
public class RegisterBean {

    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    private ArrayList<String> lista = new ArrayList<>();

    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }
   @PostConstruct
    public void init() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "SELECT E.name FROM AirlineCompany E";
        Query query = session.createQuery(hql);
      
        
         lista = (ArrayList<String>) query.list();
        
        session.close();
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String conpassword;

    public String getConpassword() {
        return conpassword;
    }

    public void setConpassword(String conpassword) {
        this.conpassword = conpassword;
    }
    private String email;
    private int type;
    private Date datum;
    private String airline;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void validateDate(FacesContext context, UIComponent inComponent, Object value) {

        Date birth = (Date) value;
        Calendar present = Calendar.getInstance();
        Calendar past = Calendar.getInstance();
        past.setTime(birth);

        int years = 0;

        while (past.before(present)) {
            past.add(Calendar.YEAR, 1);
            if (past.before(present)) {
                years++;
            }
        }
        if (years < 18) {
            FacesMessage msg = new FacesMessage("premladi ste");
            throw new ValidatorException(msg);

        }

    }

    public String registerUser() {

        FacesContext context = FacesContext.getCurrentInstance();

        User koris = getUserUsername(username);
        if (koris != null) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Korisnik sa datim korisničkim imenom već postoji u sistemu."));
            return null;
        } else {

            RegisterUser ru = new RegisterUser(username, password, firstname, lastname, email, type, airline, datum);
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(ru);
            session.getTransaction().commit();
            session.close();

            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Vas zahtev za registraciju je uspesno poslat"));
           
            return "index";

        }

    }

    public static User getUserUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM User u where u.username=:id ";
        Query query = session.createQuery(hql);
        query.setParameter("id", username);

        List result = query.list();
        if(result.isEmpty()) {session.close(); return null;}
        User u = (User) result.get(0);
        session.close();
        return u;
    }
}
