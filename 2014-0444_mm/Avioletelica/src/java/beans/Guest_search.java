package beans;

import database.Flight;
import database.HibernateUtil;
import database.Rezervacije;
import helping.LetoviDanas;
import helping.Result;
import helping.ResultCross;
import java.util.Date;

import java.sql.Time;
import java.util.ArrayList;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "guest")
@SessionScoped
public class Guest_search {

    private int direction;
    private String city_start;
    private String city_end;
    private Date start_date;
    private Date end_date;
    private int person;
    private boolean direct;
    private String ime;
    private String prezime;
    private int pasos;
    private int credit_card;
    private String kartica;

    public String getKartica() {
        return kartica;
    }

    public void setKartica(String kartica) {
        this.kartica = kartica;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public int getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(int credit_card) {
        this.credit_card = credit_card;
    }

    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getCity_start() {
        return city_start;
    }

    public void setCity_start(String city_start) {
        this.city_start = city_start;
    }

    public String getCity_end() {
        return city_end;
    }

    public void setCity_end(String city_end) {
        this.city_end = city_end;
    }

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

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }
    private ArrayList<Result> directNot = new ArrayList<>();
    private ArrayList<Result> directReturn = new ArrayList<>();
    private ArrayList<ResultCross> notDirectNot = new ArrayList<>();
    private ArrayList<ResultCross> notDirectReturn = new ArrayList<>();

    public ArrayList<ResultCross> getNotDirectReturn() {
        return notDirectReturn;
    }

    public void setNotDirectReturn(ArrayList<ResultCross> notDirectReturn) {
        this.notDirectReturn = notDirectReturn;
    }

    public ArrayList<ResultCross> getNotDirectNot() {
        return notDirectNot;
    }

    public void setNotDirectNot(ArrayList<ResultCross> notDirectNot) {
        this.notDirectNot = notDirectNot;
    }

    public ArrayList<Result> getDirectNot() {
        return directNot;
    }

    public void setDirectNot(ArrayList<Result> directNot) {
        this.directNot = directNot;
    }

    public ArrayList<Result> getDirectReturn() {
        return directReturn;
    }

    public void setDirectReturn(ArrayList<Result> directReturn) {
        this.directReturn = directReturn;
    }

    private HibernateUtil helper;
    private Session session;
    private Flight f;

    public Flight getF() {
        return f;
    }

    public void setF(Flight f) {
        this.f = f;
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

    private Result res;

    public Result getRes() {
        return res;
    }

    public void setRes(Result res) {
        this.res = res;
    }

    public String search() {
        porukaDirect = "";
        directNot.removeAll(directNot);
        directReturn.removeAll(directReturn);
        notDirectNot.removeAll(notDirectNot);
        notDirectReturn.removeAll(notDirectReturn);
        if (direct == true) {

            if (direction == 1) {
                postaviDirectNot();
                return "direct-one";
            } else {

                postaviDirectNot();
                postaviDirectReturn();
                return "direct-return";

            }
        } else {

            if (direction == 1) {
                postaviDirectNot();
                postaviNotDirectNot();
                return "not-one";
            } else {
                postaviDirectNot();
                postaviDirectReturn();
                postaviNotDirectNot();
                postaviNotDirectReturn();
                return "notdirect-return";

            }

        }

    }

    private String poruka = "";

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public int postaviDirectNot() {

        FacesContext context;
        context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select f.idflight,a.name,a.city,a.country,b.name,b.city,b.country,f.duration,f.reservation,f.startDate,f.startTime from Flight f,Airport a,Airport b where f.airportByStartAirport=a.idairport and f.airportByEndAirport=b.idairport and a.name=:start  and b.name=:end and f.reservation>:res and f.startDate=:prvi";
        Query query = session.createQuery(hql);
        query.setParameter("start", city_start);
        query.setParameter("res", person);

        query.setParameter("end", city_end);
        query.setParameter("prvi", start_date);
        List<Object[]> lista = query.list();
        if (lista.isEmpty()) {

            session.close();
            return 0;
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

                int free = (int) row[8];
                res = new Result(ID, start_city, end_city, end_country, start_country, start_air, end_air, duration, start_date, free, start_time);
                directNot.add(res);
            }
            session.close();
            return 1;
        }
    }

    public int postaviDirectReturn() {

        FacesContext context;
        context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select f.idflight,a.name,a.city,a.country,b.name,b.city,b.country,f.duration,f.reservation,f.startDate,f.startTime from Flight f,Airport a,Airport b where f.airportByStartAirport=a.idairport and f.airportByEndAirport=b.idairport and a.name=:end and f.reservation>:res and b.name=:start and f.startDate=:drugi";
        Query query = session.createQuery(hql);
        query.setParameter("start", city_start);
        query.setParameter("res", person);
        query.setParameter("end", city_end);
        query.setParameter("drugi", end_date);
        List<Object[]> lista = query.list();
        if (lista.isEmpty()) {

            session.close();
            return 0;
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

                int free = (int) row[8];
                Time start_time = (Time) row[10];
                res = new Result(ID, start_city, end_city, end_country, start_country, start_air, end_air, duration, end_date, free, start_time);
                directReturn.add(res);
            }
            session.close();
            return 1;
        }

    }

    public int postaviNotDirectNot() {
        FacesContext context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select f1.idflight,f2.idflight,a1.name,a1.city,a1.country,b1.name,b1.city,b1.country,b2.name,b2.city,b2.country,f1.duration,f1.reservation,f1.startDate,f1.startTime,f2.duration,f2.reservation,f2.startDate,f2.startTime from Flight f1,Airport a1,Airport b1,Flight f2,Airport b2 where f1.airportByStartAirport=a1.idairport and f1.airportByEndAirport=b1.idairport and b1.idairport=f2.airportByStartAirport and f2.airportByEndAirport=b2.idairport and a1.name=:start and f1.plannedDate=f2.startDate and f1.plannedTime<f2.startTime and b2.name=:end and f1.startDate=:prvi and f1.reservation>:res and f2.reservation>:res";
        Query query = session.createQuery(hql);
        query.setParameter("start", city_start);
        query.setParameter("res", person);
        query.setParameter("end", city_end);
        query.setParameter("prvi", start_date);
        List<Object[]> lista = query.list();
        for (Object[] row : lista) {

            int ID1 = (int) row[0];
            int ID2 = (int) row[1];
            String start_air1 = (String) row[2];
            String start_city1 = (String) row[3];
            String start_country1 = (String) row[4];
            String end_air1 = (String) row[5];
            String end_city1 = (String) row[6];
            String end_country1 = (String) row[7];

            String duration1 = (String) row[11];
            int free1 = (int) row[12];
            Time start_time1 = (Time) row[14];
            String end_air2 = (String) row[8];
            String end_city2 = (String) row[9];
            String end_country2 = (String) row[10];
            Time start_time2 = (Time) row[18];
            String duration2 = (String) row[15];
            int free2 = (int) row[16];
            ResultCross res = new ResultCross(ID1, start_city1, end_city1, end_country1, start_country1, start_air1, end_air1, duration1, start_date, start_time1, ID2, start_city1, end_city2, end_country2, start_country1, start_air1, end_air2, duration2, start_date, start_time2);
            notDirectNot.add(res);

        }
        session.close();
        return 1;
    }

    public int postaviNotDirectReturn() {
        FacesContext context = FacesContext.getCurrentInstance();
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "select f1.idflight,f2.idflight,a1.name,a1.city,a1.country,b1.name,b1.city,b1.country,b2.name,b2.city,b2.country,f1.duration,f1.reservation,f1.startDate,f1.startTime,f2.duration,f2.reservation,f2.startDate,f2.startTime from Flight f1,Airport a1,Airport b1,Flight f2,Airport b2 where f1.airportByStartAirport=a1.idairport and f1.airportByEndAirport=b1.idairport and b1.idairport=f2.airportByStartAirport and f2.airportByEndAirport=b2.idairport and a1.name=:end and f1.plannedDate=f2.startDate and f1.plannedTime<f2.startTime  and b2.name=:start and f1.startDate=:drugi and f1.reservation>:res and f2.reservation>:res";
        Query query = session.createQuery(hql);
        query.setParameter("start", city_start);
        query.setParameter("res", person);
        query.setParameter("end", city_end);
        query.setParameter("drugi", end_date);
        List<Object[]> lista = query.list();
        for (Object[] row : lista) {

            int ID1 = (int) row[0];
            int ID2 = (int) row[1];
            String start_air1 = (String) row[2];
            String start_city1 = (String) row[3];
            String start_country1 = (String) row[4];
            String end_air1 = (String) row[5];
            String end_city1 = (String) row[6];
            String end_country1 = (String) row[7];

            String duration1 = (String) row[11];
            int free1 = (int) row[12];
            Time start_time1 = (Time) row[14];
            String end_air2 = (String) row[8];
            String end_city2 = (String) row[9];
            String end_country2 = (String) row[10];
            Time start_time2 = (Time) row[18];
            String duration2 = (String) row[15];
            int free2 = (int) row[16];
            ResultCross res = new ResultCross(ID1, start_city1, end_city1, end_country1, start_country1, start_air1, end_air1, duration1, start_date, start_time1, ID2, start_city1, end_city2, end_country2, start_country1, start_air1, end_air2, duration2, start_date, start_time2);
            notDirectReturn.add(res);

        }
        session.close();
        return 1;
    }
    private int idReserve;
    private int idReserve2;

    public int getIdReserve2() {
        return idReserve2;
    }

    public void setIdReserve2(int idReserve2) {
        this.idReserve2 = idReserve2;
    }

    public int getIdReserve() {
        return idReserve;
    }

    public void setIdReserve(int idReserve) {
        this.idReserve = idReserve;
    }

    private int pomocnaNotReturn = 0;

    public String rezervisiJedinstven(Result pom) {
        idReserve = pom.getID();

        pomocna = 1;
        return "podaciR";

    }

    public String rezervisiJedinstvenNot(Result pom) {
        idReserve = pom.getID();
        pomocnaNotReturn = 1;
        return "PodaciNot";
    }

    public String rezervisiJedinstvenNotRet(Result pom) {
        idReserve = pom.getID();
        pomocnaNotReturn = 3;
        return "PodaciNot";
    }

    public String rezervisiJedinstvenRet(Result pom) {
        idReserve = pom.getID();
        pomocna = 2;
        return "podaciR";

    }

    public String rezervisiDva(ResultCross pom) {
        idReserve = pom.getID1();
        idReserve2 = pom.getID2();

        return "podaciRpresedanje";

    }

    public String rezervisiDvaNot(ResultCross pom) {
        idReserve = pom.getID1();
        idReserve2 = pom.getID2();
        pomocnaNotReturn = 2;
        return "PodaciNotRet";

    }

    public String rezervisiDvaNotReturn(ResultCross pom) {
        idReserve = pom.getID1();
        idReserve2 = pom.getID2();
        pomocnaNotReturn = 4;
        return "PodaciNotRet";

    }

    private String porukaDirect = "";

    public String getPorukaDirect() {
        return porukaDirect;
    }

    public void setPorukaDirect(String porukaDirect) {
        this.porukaDirect = porukaDirect;
    }

    private String porukaPrva = "";

    public String getPorukaPrva() {
        return porukaPrva;
    }

    public void setPorukaPrva(String porukaPrva) {
        this.porukaPrva = porukaPrva;
    }

    private int pomocna = 0;

    public String Reserve() {
        int pom = Result.getId();

        Rezervacije r = new Rezervacije(ime, prezime, pasos, person, pom, idReserve);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();
        session.close();

        updateFree();

        if (direction == 1) {
            porukaDirect = "Broj vase rezervacije je " + pom + ";";
            pom++;
            Result.setId(pom);
            return "Guest-search";

        } else if (pomocna == 1) {

            porukaPrva = "Broj vase rezervacije u direktnom smeru je " + pom + ";";
            pom++;
            Result.setId(pom);

            return "direct-return";

        } else {
            porukaDruga = "Broj vase rezervacije u povratnom smeru  je " + pom + ".";
            pom++;
            Result.setId(pom);

            return "direct-return";
        }

    }
    private String porukaM = "";
    private String porukaI = "";
    private String porukaN = "";
    private String porukaA = "";

    public String getPorukaI() {
        return porukaI;
    }

    public void setPorukaI(String porukaI) {
        this.porukaI = porukaI;
    }

    public String getPorukaN() {
        return porukaN;
    }

    public void setPorukaN(String porukaN) {
        this.porukaN = porukaN;
    }

    public String getPorukaA() {
        return porukaA;
    }

    public void setPorukaA(String porukaA) {
        this.porukaA = porukaA;
    }

    public String getPorukaM() {
        return porukaM;
    }

    public void setPorukaM(String porukaM) {
        this.porukaM = porukaM;
    }

    public String getPorukaDruga() {
        return porukaDruga;
    }

    public void setPorukaDruga(String porukaDruga) {
        this.porukaDruga = porukaDruga;
    }

    public String ReserveNot() {
        int pom = Result.getId();
        int id1 = pom;
        Rezervacije r = new Rezervacije(ime, prezime, pasos, person, pom, idReserve);
        pom++;
        Result.setId(pom);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();
        session.close();

        updateFree();

        if (pomocnaNotReturn == 1) {
            porukaM = "Broj vase rezervacije u direktnom smeru bez presedanja je " + id1 + ";";

        }
        if (pomocnaNotReturn == 3) {
            porukaN = "Broj vase rezervacije u povratnom smeru  je " + id1 + ".";

        }
        return "notdirect-return";

    }

    private String porukaDruga = "";

    public String ReservePresedanje() {
        int pom = Result.getId();
        int id1 = pom;
        Rezervacije r = new Rezervacije(ime, prezime, pasos, person, pom, idReserve);
        pom++;
        Result.setId(pom);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();
        session.close();
        int pom1 = Result.getId();
        int id2 = pom1;
        Rezervacije r2 = new Rezervacije(ime, prezime, pasos, person, pom1, idReserve2);
        pom1++;
        Result.setId(pom1);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(r2);
        session.getTransaction().commit();
        session.close();
        updateFree();
        updateFreePresedanje();

        if (direction == 1) {

            porukaDirect = "Broj rezervacija za oba leta su : " + id1 + "(prvi) i " + id2 + "(drugi);";
            return "Guest-search";
        } else {
            return null;
        }

    }

    public String ReserveNotPresedanje() {
        int pom = Result.getId();
        int id1 = pom;
        Rezervacije r = new Rezervacije(ime, prezime, pasos, person, pom, idReserve);
        pom++;
        Result.setId(pom);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();
        session.close();
        int pom1 = Result.getId();
        int id2 = pom1;
        Rezervacije r2 = new Rezervacije(ime, prezime, pasos, person, pom1, idReserve2);
        pom1++;
        Result.setId(pom1);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(r2);
        session.getTransaction().commit();
        session.close();
        updateFree();
        updateFreePresedanje();

        if (pomocnaNotReturn == 2) {
            porukaI = "Broj rezervacija za oba leta u odlaznom smeru su : " + id1 + "(prvi) i " + id2 + "(drugi);";
        }
        if (pomocnaNotReturn == 4) {
            porukaA = "Broj rezervacija za oba leta u povratnom smeru su : " + id1 + "(prvi) i " + id2 + "(drugi);";
        }
        return "notdirect-return";

    }

    public String ReserveReturn() {
        int pom = Result.getId();

        Rezervacije r = new Rezervacije(ime, prezime, pasos, person, pom, idReserve);
        pom++;
        Result.setId(pom);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(r);
        session.getTransaction().commit();
        session.close();

        updateFree();
        return "Pomocna";

    }

    public Flight dohvati() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM Flight f where f.idflight=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", idReserve);

        List result = query.list();
        Flight f1;
        f1 = (Flight) result.get(0);
        session.close();
        return f1;
    }

    private void updateFree() {
        Flight help = dohvati();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        int free = help.getReservation();
        int update = free - person;
        help.setReservation(update);
        session.update(help);

        tx.commit();
        session.close();

    }

    public Flight dohvatiPresedanje() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        hql = "FROM Flight f where f.idflight=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", idReserve2);

        List result = query.list();
        Flight f1;
        f1 = (Flight) result.get(0);
        session.close();
        return f1;
    }

    private void updateFreePresedanje() {
        Flight help = dohvatiPresedanje();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        int free = help.getReservation();
        int update = free - person;
        help.setReservation(update);
        session.update(help);

        tx.commit();
        session.close();
    }
    private UIComponent brojRezervacije1;
    private UIComponent brojRezervacije2;

    public UIComponent getBrojRezervacije1() {
        return brojRezervacije1;
    }

    public void setBrojRezervacije1(UIComponent brojRezervacije1) {
        this.brojRezervacije1 = brojRezervacije1;
    }

    public UIComponent getBrojRezervacije2() {
        return brojRezervacije2;
    }

    public void setBrojRezervacije2(UIComponent brojRezervacije2) {
        this.brojRezervacije2 = brojRezervacije2;
    }

    private ArrayList<LetoviDanas> letoviDanasKrecu = new ArrayList<>();
    private ArrayList<LetoviDanas> letoviDanasSlecu = new ArrayList<>();

    public ArrayList<LetoviDanas> getLetoviDanasKrecu() {
        return letoviDanasKrecu;
    }

    public void setLetoviDanasKrecu(ArrayList<LetoviDanas> letoviDanasKrecu) {
        this.letoviDanasKrecu = letoviDanasKrecu;
    }

    public ArrayList<LetoviDanas> getLetoviDanasSlecu() {
        return letoviDanasSlecu;
    }

    public void setLetoviDanasSlecu(ArrayList<LetoviDanas> letoviDanasSlecu) {
        this.letoviDanasSlecu = letoviDanasSlecu;
    }

    @PostConstruct
    public void init() {
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
        Date danas = new Date();

        hql = "select f.number,a.name,a.city,a.country,b.name,b.city,b.country,f.duration,f.reservation,f.startDate,f.startTime,f.plannedDate,f.plannedTime from Flight f,Airport a,Airport b where f.airportByStartAirport=a.idairport and f.airportByEndAirport=b.idairport and f.startDate=:prvi";
        Query query = session.createQuery(hql);
        query.setParameter("prvi", danas);

        List<Object[]> lista = query.list();
        if (lista.isEmpty()) {

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

              
                Time planned_time = (Time) row[12];
                int free = (int) row[8];
                LetoviDanas ld = new LetoviDanas(ID, start_city, end_city, end_country, start_country, start_air, end_air, duration, danas, start_time, danas, planned_time);
                letoviDanasKrecu.add(ld);
            }
            session.close();
        }

        session = helper.getSessionFactory().openSession();
        session.beginTransaction();

        hql = "select f.number,a.name,a.city,a.country,b.name,b.city,b.country,f.duration,f.reservation,f.startDate,f.startTime,f.plannedDate,f.plannedTime from Flight f,Airport a,Airport b where f.airportByStartAirport=a.idairport and f.airportByEndAirport=b.idairport and f.plannedDate=:prvi";
        query = session.createQuery(hql);
        query.setParameter("prvi", danas);

        List<Object[]> lista2 = query.list();
        if (lista2.isEmpty()) {

            session.close();

        } else {

            for (Object[] row : lista2) {
                int ID = (int) row[0];
                String start_city = (String) row[2];
                String end_city = (String) row[5];
                String end_country = (String) row[6];
                String start_country = (String) row[3];
                String start_air = (String) row[1];
                String end_air = (String) row[4];
                String duration = (String) row[7];

                Time start_time = (Time) row[10];
                
            
                Time planned_time = (Time) row[12];
                int free = (int) row[8];
                LetoviDanas ld = new LetoviDanas(ID, start_city, end_city, end_country, start_country, start_air, end_air, duration, danas, start_time, danas, planned_time);
                letoviDanasSlecu.add(ld);
            }
            session.close();
        }

    }

    
    
    
}
