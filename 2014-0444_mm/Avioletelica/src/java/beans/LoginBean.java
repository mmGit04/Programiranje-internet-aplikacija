
package beans;

import database.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import database.HibernateUtil;

@ManagedBean(name="login")
@SessionScoped

public class LoginBean {
     public static final int TEAM=1;
    public static final int ADMIN=2;
    public static final int GUEST=3;
    public static final int STUART=4;
    public static final int CREW=5;
    
   private String username;
   private String password;
   
    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
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
    private int type;
    
    private User u;
    private HibernateUtil helper;
    private Session session;
 
    
    
   
    public String loginUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        session=helper.getSessionFactory().openSession();
        session.beginTransaction();
        String hql;
         hql = "FROM User u where u.username=:id and u.password=:uk" ;
        Query query=session.createQuery(hql);
        query.setParameter("id", username);
        query.setParameter("uk" , password);
        List result=query.list();
        if (result.isEmpty()){
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Pogrešno ste uneli username ili password"));
            session.close();
            return null;
        }
        else 
            
        {  
            u=(User) result.get(0);
            session.close();
            return "dobrodosli";
        }     
   }
    
    
       
        
       
   
    
}