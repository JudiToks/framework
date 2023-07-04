package util;

import etu1820.framework.ModelView;
import etu1820.framework.annotation.*;

public class Emp 
{
    @Annotation(url = "/getEmp")
    public void getEmp()
    {
        
    }

    @Annotation(url = "Acceuil")
    public ModelView Acceuil()
    {
        return new ModelView("Acceuil.jsp");
    }
}
