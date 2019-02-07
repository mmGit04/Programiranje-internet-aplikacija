
package beans;

import database.User;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import database.HibernateUtil;

@ManagedBean(name="change")
@RequestScoped
public class ChangeBeanClass {
    
    private String username;
    private String password;
    private String newPass;
    private String conPass;
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
    
    
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
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

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConPass() {
        return conPass;
    }

    public void setConPass(String conPass) {
        this.conPass = conPass;
    }
    public static int tip=0;

    public static int getTip() {
        return tip;
    }

    public static void setTip(int tip) {
        ChangeBeanClass.tip = tip;
    }
    
    public String changePass(){
        dohvati();
        if(u==null) return null;
        session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        tx=session.beginTransaction();
        u.setPassword(newPass);
        session.update(u);
        
        tx.commit();/*Ovde moram da sredim sta ako se pogresno unese lozinka*/
        session.close();
        if(tip==1) return "pilot";
        if(tip==5) return "crewLogin";
        if (tip==4) return "stuartLogin";
        if(tip==3) return "adminLogin";
        return null;
        
    }
    public void dohvati(){
        session=HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
         hql = "FROM User u where u.username=:id and u.password=:uk" ;
        Query query=session.createQuery(hql);
        query.setParameter("id", username);
        query.setParameter("uk" , password);
        
        List result=query.list();
        if(result.isEmpty()) {porukaPromena="Ne postoji korisnik sa takvim korisnickim imenom i lozinkom.";u=null;}
        else u=(User) result.get(0);
        session.close();
    }
   
}
