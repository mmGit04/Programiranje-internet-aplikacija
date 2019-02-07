package beans;

import database.AirlineCompany;
import database.Airplane;

import database.AirplaneTypes;
import database.Airport;
import database.Flight;
import database.HibernateUtil;
import database.MadeFinal;
import database.Ponude;
import database.RadarCenter;
import database.User;
import helping.LetoviCrew;
import helping.MadeAirplane;
import helping.RentHelp;
import helping.mojePonudeHelp;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

@ManagedBean(name = "crew")
@SessionScoped
public class CrewBean {

    private String username;
    private String password;
    private UIComponent component;
    private User u;
    private HibernateUtil helper;
    private Session session;

       private String porukaPromena="";

    public String getPorukaPromena() {
        return porukaPromena;
    }

    public void setPorukaPromena(String porukaPromena) {
        this.porukaPromena = porukaPromena;
    }
    
    
    
       public String promenaLozinke() {
        ChangeBeanClass.tip = 5;
        return "promenaLozinke";

    }
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

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

    public String loginUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Session session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM User u where u.username=:id and u.password=:uk and u.type=:tip";
        Query query = session.createQuery(hql);
        query.setParameter("id", username);
        query.setParameter("uk", password);
        query.setParameter("tip",5);
        List result = query.list();
        if (result.isEmpty()) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Pogrešno ste uneli username ili password"));
            session.close();
            return null;
        } else {
            u = (User) result.get(0);
            session.close();
            inicijalizuj();
            return "crewMain";

        }

    }

    private ArrayList<Flight> letovi = new ArrayList<>();
    private ArrayList<LetoviCrew> letinfo = new ArrayList<>();

    public ArrayList<LetoviCrew> getLetinfo() {
        return letinfo;
    }

    public void setLetinfo(ArrayList<LetoviCrew> letinfo) {
        this.letinfo = letinfo;
    }

    public ArrayList<Flight> getLetovi() {
        return letovi;
    }

    public void setLetovi(ArrayList<Flight> letovi) {
        this.letovi = letovi;
    }

    private boolean visible = false;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String prikaziLetove() {
        ArrayList<Integer> pom;
        pom = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Date danas = new Date();

        String hql = "select f.idflight from Flight f,Airplane a,AirlineCompany ac where f.airplane=a.idairplane and a.airlineCompany=ac.idairlineCompany and ac.name=:name and f.startDate=:datum and f.canceled=:status";
        Query query = session.createQuery(hql);
        query.setParameter("datum", danas);
        query.setParameter("name", u.getAirline());

        byte broj = 0;
        query.setParameter("status", broj);

        List<Integer> lista = query.list();
        for (int row : lista) {

            pom.add(row);
        }

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        hql = "select f.idflight from Flight f,Airplane a,AirlineCompany ac where f.airplane=a.idairplane and a.airlineCompany=ac.idairlineCompany and ac.name=:name and f.plannedDate=:datum and f.canceled=:status";
        query = session.createQuery(hql);
        query.setParameter("datum", danas);
        query.setParameter("name", u.getAirline());

        broj = 0;
        query.setParameter("status", broj);

        List<Integer> lista2 = query.list();
        for (int row : lista2) {

            pom.add(row);
        }

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        hql = "select f.idflight from Flight f,Airplane a,AirlineCompany ac where f.airplane=a.idairplane and a.airlineCompany=ac.idairlineCompany and ac.name=:name and f.startDate>:datum";
        query = session.createQuery(hql);
        query.setParameter("datum", danas);
        query.setParameter("name", u.getAirline());

        List<Integer> lista3 = query.list();
        for (int row : lista3) {

            pom.add(row);
        }

        session.close();

        Set<Integer> hs = new HashSet<>();
        hs.addAll(pom);
        pom.clear();
        pom.addAll(hs);
        letovi.removeAll(letovi);
        for (int i : pom) {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            hql = "from Flight f where f.idflight=:id";
            query = session.createQuery(hql);
            query.setParameter("id", i);
            List result = query.list();
            Flight f = (Flight) result.get(0);
            letovi.add(f);
            session.close();
        }

        podesiLetinfo();

        visible = true;
        return null;
    }

    private void podesiLetinfo() {
        for (Flight f : letovi) {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            String hql;

            hql = "select f.idflight,f.number,a.name,a.city,a.country,b.name,b.city,b.country,c.name,c.city,c.country,g1.gateName,g2.gateName,f.duration,f.expectTime,f.endTime,f.reservation,f.startDate,f.startTime, f.plannedDate,f.plannedTime,f.status,f.carter,f.canceled,ap.name from Flight f,Airport a,Airport b,Airport c,Airplane ap,Gates g1,Gates g2 where f.idflight=:id and f.airportByStartAirport=a.idairport and f.airportByEndAirport=b.idairport and f.airportByEndAirport=c.idairport and f.airplane=ap.idairplane and f.startGate=g1.idgates and f.endGate=g2.idgates";

            Query query = session.createQuery(hql);

            query.setParameter("id", f.getIdflight());

            List<Object[]> lista = query.list();
            for (Object[] row : lista) {
                int ID = (int) row[0];
                int number = (int) row[1];
                String start_air = (String) row[2];
                String start_city = (String) row[3];
                String start_country = (String) row[4];
                String end_air = (String) row[5];
                String end_city = (String) row[6];
                String end_country = (String) row[7];
                String unexp_airport = (String) row[8];
                String unexp_city = (String) row[9];
                String unexp_country = (String) row[10];
                String start_gate = (String) row[11];
                String end_gate = (String) row[12];
                String duration = (String) row[13];

                Date expect_time = (Date) row[14];
                Date end_time = (Date) row[15];
                int reservation = (int) row[16];
                Date start_date = (Date) row[17];
                Time start_time = (Time) row[18];
                Date planned_date = (Date) row[19];
                Time planned_time = (Time) row[20];
                int status = (int) row[21];
                byte carter = (byte) row[22];
                byte canceled = (byte) row[23];
                String airplane = (String) row[24];
                LetoviCrew lc = new LetoviCrew(ID, number, start_city, start_air, start_country, end_city, end_country, end_air, unexp_airport, unexp_city, unexp_country, start_gate, end_gate, duration, expect_time, end_time, reservation, start_date, start_time, planned_date, planned_time, status, carter, canceled, airplane);
                letinfo.add(lc);
            }
            session.close();
        }
    }
    private LetoviCrew currFlight;

    public LetoviCrew getCurrFlight() {
        return currFlight;
    }

    public void setCurrFlight(LetoviCrew currFlight) {
        this.currFlight = currFlight;
    }

    public String vidiDetalje(LetoviCrew lc) {

        currFlight = lc;
        postaviMarker();
        return "flightDetails";
    }

    private MapModel mapModel = new DefaultMapModel();

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    private Polyline polyline = new Polyline();

    private void postaviMarker() {
        mapModel=new DefaultMapModel();
        polyline = new Polyline();
        polyline.setStrokeWeight(10);
        polyline.setStrokeColor("#FF9900");
        polyline.setStrokeOpacity(0.7);

        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select f.airportByStartAirport,f.airportByEndAirport from Flight f where f.idflight=:i";
        Query query = session.createQuery(hql);

        int pom = currFlight.getID();
        query.setParameter("i", pom);

        List<Object[]> lista = query.list();
        Airport idS = null;
        Airport idE = null;
        for (Object[] row : lista) {
            idS = (Airport) row[0];
            idE = (Airport) row[1];
        }
        session.close();

        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        hql = "select c.lat,c.lng from AirportCord c where c.airport=:idS";
        query = session.createQuery(hql);
        query.setParameter("idS", idE);
        List<Object[]> lista1 = query.list();
        double latS = 0, lngS = 0;
        for (Object[] row : lista1) {
            latS = (double) row[0];
            lngS = (double) row[1];
        }

        LatLng coord1 = new LatLng(latS, lngS);
        mapModel.addOverlay(new Marker(coord1));
        polyline.getPaths().add(coord1);

        session.close();

        postaviRadioMarkere();

        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        hql = "select c.lat,c.lng from AirportCord c where c.airport=:idS";
        query = session.createQuery(hql);
        query.setParameter("idS", idS);
        List<Object[]> lista2 = query.list();
        double latE = 0, lngE = 0;
        for (Object[] row : lista2) {
            latE = (double) row[0];
            lngE = (double) row[1];
        }
        LatLng coord2 = new LatLng(latE, lngE);
        mapModel.addOverlay(new Marker(coord2));

        polyline.getPaths().add(coord2);

        session.close();

        mapModel.addOverlay(polyline);

    }

    private void postaviRadioMarkere() {
        Session session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select rf.radarCenter from RadarFlight rf where rf.flight=:id";
        Query query = session.createQuery(hql);
        Flight f = dohvatiLet();
        query.setParameter("id", f);
        List<RadarCenter> result = query.list();
        session.close();
        for (RadarCenter rc : result) {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            hql = "select c.lat,c.lng from RadarCord c where c.radarCenter=:radar";
            query = session.createQuery(hql);
            query.setParameter("radar", rc);
            List<Object[]> lista2 = query.list();
            double latE = 0, lngE = 0;
            for (Object[] row : lista2) {
                latE = (double) row[0];
                lngE = (double) row[1];
            }
            LatLng coord = new LatLng(latE, lngE);
            mapModel.addOverlay(new Marker(coord));

            polyline.getPaths().add(coord);
            session.close();

        }

    }

    public Flight dohvatiLet() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM Flight f where f.idflight=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", currFlight.getID());

        List result = query.list();
        Flight f1;
        f1 = (Flight) result.get(0);
        session.close();
        return f1;
    }

    public Airplane dohvatiAvion(int ID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM Airplane a where a.idairplane=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", ID);

        List result = query.list();
        Airplane f1;
        f1 = (Airplane) result.get(0);
        session.close();
        return f1;
    }

    private ArrayList<MadeAirplane> napravljeni = new ArrayList<>();

    public ArrayList<MadeAirplane> getNapravljeni() {
        return napravljeni;
    }

    public void setNapravljeni(ArrayList<MadeAirplane> napravljeni) {
        this.napravljeni = napravljeni;
    }

    public void inicijalizuj() {
        napravljeni.removeAll(napravljeni);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "select am.idmadeFinal,am.name,a.name from MadeFinal am,AirplaneTypes a where am.tip=a.idairplaneTypes";
        Query query = session.createQuery(hql);

        List<Object[]> result = query.list();
        for (Object[] row : result) {
            int ID = (int) row[0];
            String name = (String) row[1];
            String tip = (String) row[2];
            MadeAirplane ma = new MadeAirplane(ID, name, tip);

            napravljeni.add(ma);

        }
        session.close();
    }

    private int flota;

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public int getFlota() {
        return flota;
    }

    public void setFlota(int flota) {
        this.flota = flota;
    }

    public String ubaciFlotu(MadeAirplane ma) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from AirplaneTypes at where at.name=:name ";
        Query query = session.createQuery(hql);
        query.setParameter("name", ma.getTip());
        List result = query.list();
        AirplaneTypes at = (AirplaneTypes) result.get(0);
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        hql = "from AirlineCompany ac where ac.name=:name ";
        query = session.createQuery(hql);
        query.setParameter("name", u.getAirline());
        result = query.list();
        session.close();

        AirlineCompany ac = (AirlineCompany) result.get(0);
        Airplane ar;
        ar = new Airplane(ac, at, ma.getName());
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ar);
        session.getTransaction().commit();
        session.close();
        MadeFinal mf=dohvatiNapravljen(ma.getId());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        tx=session.beginTransaction();
        
        session.delete(mf);
        session.flush();
        porukaDodajFlota = "Uspesno ste dodali avion u svoju flotu.";
        tx.commit();
        session.close();
        napravljeni.remove(ma);

        return null;
    }
    
    private MadeFinal dohvatiNapravljen(int id){
          
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM MadeFinal a where a.idmadeFinal=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        List result = query.list();
        MadeFinal f1;
        f1 = (MadeFinal) result.get(0);
        session.close();
        return f1;
    
        
    }
   private String porukaDodajFlota="";

    public String getPorukaDodajFlota() {
        return porukaDodajFlota;
    }

    public void setPorukaDodajFlota(String porukaDodajFlota) {
        this.porukaDodajFlota = porukaDodajFlota;
    }
   
    /*deo za iznajmljivanje carter letova*/
    private String proizvodjac;
    private String modelAviona;
    private int brojsedista = 0;
    private Date start_date;
    private Date end_date;

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getModelAviona() {
        return modelAviona;
    }

    public void setModelAviona(String modelAviona) {
        this.modelAviona = modelAviona;
    }

    public int getBrojsedista() {
        return brojsedista;
    }

    public void setBrojsedista(int brojsedista) {
        this.brojsedista = brojsedista;
    }

    public ArrayList<RentHelp> getPom() {
        return pom;
    }

    public void setPom(ArrayList<RentHelp> pom) {
        this.pom = pom;
    }

    private ArrayList<RentHelp> pom = new ArrayList<RentHelp>();

    public String dostupni() {
        pom = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select a.idairplane,ac.name,ac.idairlineCompany from Airplane a,AirplaneTypes at,AirplaneMaker am,AirlineCompany ac where ac.idairlineCompany=a.airlineCompany and a.airplaneTypes=at.idairplaneTypes and at.airplaneMaker=am.idairplaneMaker and am.name=:proizvodjac and at.name=:model and  at.passNum>:broj and ac.name!=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name", u.getAirline());
        query.setParameter("proizvodjac", proizvodjac);
        query.setParameter("model", modelAviona);
        query.setParameter("broj", brojsedista);

        List<Object[]> result = query.list();
        
        session.close();
         if (result.isEmpty()){porukaRez="Trenutno nema dostupnih aviona za iznajmljivanje."; return null;}
        for (Object[] row : result) {
            int ID = (int) row[0];
            String imeC = (String) row[1];
            int idC = (int) row[2];
            Airplane a1 = dohvatiAvion(ID);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            hql = "select f.idflight from Flight f where f.airplane=:ID and f.startDate>:start and f.plannedDate<:end";
            query = session.createQuery(hql);
            query.setParameter("ID", a1);
            query.setParameter("start", start_date);
            query.setParameter("end", end_date);

            List result3 = query.list();
            if (result3.isEmpty()) {
                pom.add(new RentHelp(a1.getName(), ID, imeC, idC));
            }
            session.close();
        }
        vidljivo = true;
        return null;
    }
   private String porukaRez="";

    public String getPorukaRez() {
        return porukaRez;
    }

    public void setPorukaRez(String porukaRez) {
        this.porukaRez = porukaRez;
    }
   
    private boolean vidljivo = false;

    public boolean isVidljivo() {
        return vidljivo;
    }

    public void setVidljivo(boolean vidljivo) {
        this.vidljivo = vidljivo;
    }

    public String dajPonudu(RentHelp rh) {

        int id = dohvatiIdKompanije(u.getAirline());
        Ponude ru = new Ponude(id, rh.getIdC(), rh.getID(), rh.getPonuda(), 0);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ru);
        session.getTransaction().commit();
        session.close();
         porukaRez="Uspesno ste poslali svoju ponudu";
        return null;
    }

    private int dohvatiIdKompanije(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM AirlineCompany a where a.name=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", name);

        List result = query.list();
        AirlineCompany f1;
        f1 = (AirlineCompany) result.get(0);
        session.close();
        return f1.getIdairlineCompany();
    }

    private ArrayList<mojePonudeHelp> mojeP = new ArrayList<>();

    public ArrayList<mojePonudeHelp> getMojeP() {
        return mojeP;
    }

    public void setMojeP(ArrayList<mojePonudeHelp> mojeP) {
        this.mojeP = mojeP;
    }
  private boolean info=false;

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }
  
    public String mojePonude() {
        mojeP.removeAll(mojeP);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        int id = dohvatiIdKompanije(u.getAirline());
        hql = "select p.kupac,ac.name,p.ponuda,a.name,p.avion,p.idponude from Ponude p,AirlineCompany ac,Airplane a where p.prodavac=:id and p.kupac=ac.idairlineCompany and p.avion=a.idairplane and p.odobreno=:o";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("o", 0);

        List<Object[]> result = query.list();
       
        session.close();
         if (result.isEmpty()){porukaRez="Trenutno ne postoje ponude za carter letovima."; return null;}
        for (Object[] row : result) {

            int kupac = (int) row[0];
            String nameK = (String) row[1];
            double ponuda = (double) row[2];
            String nameA = (String) row[3];
            int idA = (int) row[4];
            int idP=(int) row[5];
            mojePonudeHelp mp = new mojePonudeHelp(kupac, nameK, ponuda, nameA, idA,idP);
            mojeP.add(mp);
        }
     info=true;
        return null;
    }
   public String odobri(mojePonudeHelp mph){
       Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        tx=session.beginTransaction();
        Ponude p=dohvatiPonudu(mph.getIdP());
        p.setOdobreno(1);
        session.update(p);
        
        tx.commit();
        session.close();
        mojeP.remove(mph);
       return null;
   }
   
   
    public Ponude dohvatiPonudu(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM Ponude p where p.idponude=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        List result = query.list();
        Ponude f1;
        f1 = (Ponude) result.get(0);
        session.close();
        return f1;
    }
    
     public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "index";
    }
}
