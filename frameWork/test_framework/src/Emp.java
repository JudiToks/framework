package util;

import etu1820.framework.ModelView;
import etu1820.framework.annotation.*;

public class Emp 
{
    String nom;
    int age;

    // getters & setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Annotation(url = "/getEmp")
    public void getEmp()
    {
        
    }

    @Annotation(url = "Acceuil")
    public ModelView Acceuil()
    {
        ModelView view = new ModelView("Acceuil.jsp");
        view.addItem("employe", this);
        return view;
    }
}
