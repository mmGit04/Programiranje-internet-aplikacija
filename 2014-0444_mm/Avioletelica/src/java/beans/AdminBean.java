/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.AirlineCompany;
import database.AirplaneLicence;
import database.Airport;
import database.Gates;
import database.HibernateUtil;
import database.PilotLicence;
import database.RegisterUser;
import database.User;
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

/**
 *
 * @author MINKA
 */
@ManagedBean(name = "admin")
@SessionScoped
public class AdminBean {

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

    private UIComponent component;
    private User u;
    private HibernateUtil helper;
    private Session session;

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
      

    public String loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Session session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM User u where u.username=:id and u.password=:uk and u.type=:tip";
        Query query = session.createQuery(hql);
        query.setParameter("id", username);
        query.setParameter("tip", 3);

        query.setParameter("uk", password);
        List result = query.list();
        if (result.isEmpty()) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Pogrešno ste uneli username ili password"));
            session.close();
            return null;
        } else {
            u = (User) result.get(0);
            inicijalizuj();
            session.close();

            return "adminMain";

        }

    }

    public String promenaLozinke() {
        ChangeBeanClass.tip = 3;
        return "promenaLozinke";

    }
    private ArrayList<RegisterUser> registrovani = new ArrayList<RegisterUser>();

    public ArrayList<RegisterUser> getRegistrovani() {
        return registrovani;
    }

    public void setRegistrovani(ArrayList<RegisterUser> registrovani) {
        this.registrovani = registrovani;
    }
    private String porukaRegister = "";

    public String getPorukaRegister() {
        return porukaRegister;
    }

    public void setPorukaRegister(String porukaRegister) {
        this.porukaRegister = porukaRegister;
    }

    private void inicijalizuj() {
        registrovani.removeAll(registrovani);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from RegisterUser ";
        Query query = session.createQuery(hql);
        List results = query.list();
        if (results.isEmpty()) {
            porukaRegister = "Ne postoje novi zahtevi za registraciju";

        } else {
            registrovani = (ArrayList<RegisterUser>) results;
        }
        session.close();

    }

    public String dozvoliReg(RegisterUser ru) {
        int id = ru.getIdregisterUser();
        String user = ru.getUsername();
        String pass = ru.getPassword();
        String first = ru.getFirstName();
        String last = ru.getLastName();
        String email = ru.getEmail();
        String airline = ru.getAirline();
        int type = ru.getType();
        Date birth = ru.getBirthdate();

        User korisnik = new User(user, pass, first, last, email, type, airline, birth);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(korisnik);
        session.getTransaction().commit();
        session.close();
        izbrisiRegUser(ru);
        registrovani.remove(ru);
        return null;
    }

    private void izbrisiRegUser(RegisterUser ru) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        session.delete(ru);
        session.flush();
        porukaRegister = "Uspesno ste dozvolili registraciju korisniku.";
        tx.commit();
        session.close();

    }
    private String ime;
    private String IATA;
    private String city;
    private String country;
    private int runnum;
    private int ternum;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRunnum() {
        return runnum;
    }

    public void setRunnum(int runnum) {
        this.runnum = runnum;
    }

    public int getTernum() {
        return ternum;
    }

    public void setTernum(int ternum) {
        this.ternum = ternum;
    }
    private String porukaDodavanje = "";

    public String getPorukaDodavanje() {
        return porukaDodavanje;
    }

    public void setPorukaDodavanje(String porukaDodavanje) {
        this.porukaDodavanje = porukaDodavanje;
    }

    private Airport novi;

    public String dodajAerodrom() {

        Airport aerodrom = new Airport(ime, IATA, city, country, runnum, ternum);
        novi = aerodrom;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(aerodrom);
        session.getTransaction().commit();
        session.close();
        porukaDodavanje = "Uspesno ste dodali informacije o ovom aerodromu u bazu.";
        return null;
    }

    private String gateIATA;
    private String terminal;
    private String gate;

    public String getGateIATA() {
        return gateIATA;
    }

    public void setGateIATA(String gateIATA) {
        this.gateIATA = gateIATA;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String dodajGate() {
        if (novi == null) {
            porukaInfo = "Morate prvo uneti informacije o aerodromu pa tek onda o njegovim gejtovima.";
            return null;
        }
        Gates novaKapija = new Gates(novi, terminal, gate);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(novaKapija);
        session.getTransaction().commit();
        session.close();
        porukaInfo = "Uspesno ste dodali informacije o terminalu za predhodno unet aerodrom.";

        return null;
    }
    private String porukaInfo = "";

    public String getPorukaInfo() {
        return porukaInfo;
    }

    public void setPorukaInfo(String porukaInfo) {
        this.porukaInfo = porukaInfo;
    }

    private String porukaKompanijaAdd = "";

    public String getPorukaKompanijaAdd() {
        return porukaKompanijaAdd;
    }

    public void setPorukaKompanijaAdd(String porukaKompanijaAdd) {
        this.porukaKompanijaAdd = porukaKompanijaAdd;
    }

    private String imeKomp;
    private String adressKomp;
    private String countryKomp;
    private String webKomp;
    private String emailKomp;

    public String getImeKomp() {
        return imeKomp;
    }

    public void setImeKomp(String imeKomp) {
        this.imeKomp = imeKomp;
    }

    public String getAdressKomp() {
        return adressKomp;
    }

    public void setAdressKomp(String adressKomp) {
        this.adressKomp = adressKomp;
    }

    public String getCountryKomp() {
        return countryKomp;
    }

    public void setCountryKomp(String countryKomp) {
        this.countryKomp = countryKomp;
    }

    public String getWebKomp() {
        return webKomp;
    }

    public void setWebKomp(String webKomp) {
        this.webKomp = webKomp;
    }

    public String getEmailKomp() {
        return emailKomp;
    }

    public void setEmailKomp(String emailKomp) {
        this.emailKomp = emailKomp;
    }

    public String dodajKompaniju() {
        AirlineCompany comp = new AirlineCompany(imeKomp, adressKomp, countryKomp, webKomp, emailKomp);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(comp);
        session.getTransaction().commit();
        session.close();
        porukaKompanijaAdd = "Uspesno ste dodali informacije o terminalu za predhodno unet aerodrom.";

        return null;
    }
    private int polje;

    public int getPolje() {
        return polje;
    }

    public void setPolje(int polje) {
        this.polje = polje;
    }
    private String novaVrednost;
    private String porukaUpdate = "";

    public String getPorukaUpdate() {
        return porukaUpdate;
    }

    public void setPorukaUpdate(String porukaUpdate) {
        this.porukaUpdate = porukaUpdate;
    }

    public String getNovaVrednost() {
        return novaVrednost;
    }

    public void setNovaVrednost(String novaVrednost) {
        this.novaVrednost = novaVrednost;
    }
    private String updateKomp;

    public String getUpdateKomp() {
        return updateKomp;
    }

    public void setUpdateKomp(String updateKomp) {
        this.updateKomp = updateKomp;
    }

    public String promeniKompaniju() {
        AirlineCompany ac = dohvatiKomp();
        if (ac == null) {
            porukaUpdate = "Ne postoji kompanija sa takvim nazivom.";
            return null;
        }
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        if (polje == 1) {
            ac.setName(novaVrednost);
        }
        if (polje == 2) {
            ac.setAddress(novaVrednost);
        }
        if (polje == 3) {
            ac.setCountry(novaVrednost);
        }
        if (polje == 4) {
            ac.setWebSite(novaVrednost);
        }
        if (polje == 5) {
            ac.setEmail(novaVrednost);
        }

        session.update(ac);

        tx.commit();
        porukaUpdate = "Uspesno ste promenili podatke o avio kompaniji;";
        session.close();
        return null;
    }

    private AirlineCompany dohvatiKomp() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM AirlineCompany ac where ac.name=:id ";
        Query query = session.createQuery(hql);
        query.setParameter("id", updateKomp);
        List result = query.list();
        if (result.isEmpty()) {
            session.close();
            return null;
        }
        AirlineCompany ac = (AirlineCompany) result.get(0);
        session.close();
        return ac;

    }

    private String imePilota;
    private String prezimePilota;
    private String licence;

    public Airport getNovi() {
        return novi;
    }

    public void setNovi(Airport novi) {
        this.novi = novi;
    }

    public String getImePilota() {
        return imePilota;
    }

    public void setImePilota(String imePilota) {
        this.imePilota = imePilota;
    }

    public String getPrezimePilota() {
        return prezimePilota;
    }

    public void setPrezimePilota(String prezimePilota) {
        this.prezimePilota = prezimePilota;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String dodajLicencu() {
        User pomK = dohvatiUser();
        AirplaneLicence pomAC = dohvatiLicencu();
        if(pomK==null || pomAC==null) {return null;}
        PilotLicence pl=new PilotLicence(pomAC, pomK);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(pl);
        session.getTransaction().commit();
        session.close();
        porukaLicenca = "Uspesno ste dodali licencu pilotu.";

   

        return null;
    }
    private String porukaLicenca = "";

    public String getPorukaLicenca() {
        return porukaLicenca;
    }

    public void setPorukaLicenca(String porukaLicenca) {
        this.porukaLicenca = porukaLicenca;
    }

    private User dohvatiUser() {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM User u where u.firstName=:id and u.lastName=:uk and u.type=:tip";
        Query query = session.createQuery(hql);
        query.setParameter("id", imePilota);
        query.setParameter("uk", prezimePilota);
        query.setParameter("tip", 1);
        List result = query.list();
        if (result.isEmpty()) {
            porukaLicenca = "Ne postoji pilot u bazi tog imena i prezimena.";
            session.close();
            return null;
        }
        User k = (User) result.get(0);
        session.close();
        return k;
    }

    private AirplaneLicence dohvatiLicencu() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM AirplaneLicence u where u.licence=:id ";
        Query query = session.createQuery(hql);
        query.setParameter("id", licence);

        List result = query.list();
        if (result.isEmpty()) {
            porukaLicenca = "Ne postoji licenca u bazi.";
            session.close();
            return null;
        }
        AirplaneLicence k = (AirplaneLicence) result.get(0);
        session.close();
        return k;
    }
    
    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "index";
    } 
}
