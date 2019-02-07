package beans;

import com.sun.xml.wss.util.DateUtils;
import database.Flight;
import database.FlightCrew;
import database.HibernateUtil;
import database.User;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "pilot")
@SessionScoped
public class PilotLogin {

    private String username;
    private String password;
    private String licence;

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    private UIComponent component;
    private User u;
    private HibernateUtil helper;

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
    private Session session;

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

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public String loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM User u where u.username=:id and u.password=:uk and u.type=:tip";
        Query query = session.createQuery(hql);
        query.setParameter("id", username);
        query.setParameter("uk", password);
        query.setParameter("tip", 1);
        List result = query.list();
        if (result.isEmpty()) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Pogrešno ste uneli username ili password"));
            session.close();
            return null;
        } else {

            u = (User) result.get(0);

            session.close();
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();

            hql = "FROM PilotLicence p ,AirplaneLicence l where p.airplaneLicence=l.idairplaneLicence and p.user=:id and l.licence=:pom";
            query = session.createQuery(hql);
            query.setParameter("id", u);
            query.setParameter("pom", licence);
            result = query.list();
            if (result.isEmpty()) {
                context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Vi ne posedujete tu licencu"));
                session.close();
                return null;
            } else {
                return "pilotMain";
            }

        }

    }
    private String porukaPromena = "";

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
        porukaPromena = "Uspesno ste promenili kompaniju za koju radite.";
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

    private int brojLeta;

    public int getBrojLeta() {
        return brojLeta;
    }

    public void setBrojLeta(int brojLeta) {
        this.brojLeta = brojLeta;
    }

    public String zapocniLet() {
        Flight let = dohvatiBrojLeta();
        if (let == null) {
            return null;
        }
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM FlightCrew f where f.flight=:id and f.user=:u and f.job=:j";
        Query query = session.createQuery(hql);
        query.setParameter("id", let);
        query.setParameter("u", u);
        query.setParameter("j", 0);
        List result = query.list();

        if (result.isEmpty()) {
            porukaPoletanje = "Vi niste postavljeni kao pilot ili kopilot na tom letu.";
            session.close();
            return null;
        } else {
            session.close();

            return "pilotRad";

        }

    }

    private String porukaPoletanje = "";

    public String getPorukaPoletanje() {
        return porukaPoletanje;
    }

    public void setPorukaPoletanje(String porukaPoletanje) {
        this.porukaPoletanje = porukaPoletanje;
    }

    public Flight dohvatiBrojLeta() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM Flight f where f.number=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", brojLeta);

        List result = query.list();
        if (result.isEmpty()) {
            porukaPoletanje = "Ne postoji let sa takvim brojem.";
            session.close();
            return null;
        }
        Flight f = (Flight) result.get(0);

        session.close();

        return f;

    }

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void promenaStatusa() {

        Flight let = dohvatiBrojLeta();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        let.setStatus(status);
        session.update(let);

        tx.commit();
        Date danas = new Date();
        session.close();
        if (status == 3) {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = null;
            tx = session.beginTransaction();
            let.setEndTime(danas);
            session.update(let);

            tx.commit();
            session.close();
        }
        porukaStatus = "Uspesno je promenjen trenutni status leta.";

    }
    private String porukaStatus = "";

    public String getPorukaStatus() {
        return porukaStatus;
    }

    public void setPorukaStatus(String porukaStatus) {
        this.porukaStatus = porukaStatus;
    }
    private String porukaOtkazi = "";

    public String getPorukaOtkazi() {
        return porukaOtkazi;
    }

    public void setPorukaOtkazi(String porukaOtkazi) {
        this.porukaOtkazi = porukaOtkazi;
    }

    public String otkaziLet() {
        if (status != 0) {
            porukaOtkazi = "Ne mozete otkazati let koji je vec poleteo.";
        }
        Flight let = dohvatiBrojLeta();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Byte vrednost = 1;
        let.setCanceled(vrednost);
        session.update(let);

        tx.commit();
        session.close();

        return null;
    }

    private int duzinaPuta;
    private int brzina;

    public int getDuzinaPuta() {
        return duzinaPuta;
    }

    public void setDuzinaPuta(int duzinaPuta) {
        this.duzinaPuta = duzinaPuta;
    }

    public int getBrzina() {
        return brzina;
    }

    public void setBrzina(int brzina) {
        this.brzina = brzina;
    }

    private String porukaAzuriraj = "";

    public String getPorukaAzuriraj() {
        return porukaAzuriraj;
    }

    public void setPorukaAzuriraj(String porukaAzuriraj) {
        this.porukaAzuriraj = porukaAzuriraj;
    }

    public void azurirajVreme() {

        int vreme = duzinaPuta / brzina;
        Date trenutno = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(trenutno);
        cl.add(Calendar.HOUR, vreme);
        Date novo = new Date(cl.getTimeInMillis());
        Flight let = dohvatiBrojLeta();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        let.setExpectTime(novo);
        session.update(let);

        tx.commit();
        session.close();

        porukaAzuriraj = "Uspesno je azurirano vreme sletanja.";

    }

    public String promenaLozinke() {
        ChangeBeanClass.tip = 1;
        return "promenaLozinke";

    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "index";
    }
}
