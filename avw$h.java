import java.util.List;
import java.util.Random;

public class avw$h
  extends avw.m
{
  private boolean a;
  
  public avw$h() {}
  
  public avw$h(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    
    a(☃);
    this.l = ☃;
    
    this.a = (☃.nextInt(3) == 0);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    
    this.a = ☃.p("Chest");
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    
    ☃.a("Chest", this.a);
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    b((avw.q)☃, ☃, ☃, 0, 1, true);
  }
  
  public static h a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    avp ☃ = avp.a(☃, ☃, ☃, -1, 0, 0, 5, 7, 5, ☃);
    if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
      return null;
    }
    return new h(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    a(☃, ☃, 0, 0, 0, 4, 1, 4, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 0, 2, 0, 4, 5, 4, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 4, 2, 0, 4, 5, 4, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 4, 3, 1, 4, 4, 1, aju.bz.u(), aju.bz.u(), false);
    a(☃, ☃, 4, 3, 3, 4, 4, 3, aju.bz.u(), aju.bz.u(), false);
    
    a(☃, ☃, 0, 2, 0, 0, 5, 0, aju.by.u(), aju.by.u(), false);
    
    a(☃, ☃, 0, 2, 4, 3, 5, 4, aju.by.u(), aju.by.u(), false);
    a(☃, ☃, 1, 3, 4, 1, 4, 4, aju.bz.u(), aju.by.u(), false);
    a(☃, ☃, 3, 3, 4, 3, 4, 4, aju.bz.u(), aju.by.u(), false);
    if ((this.a) && 
      (☃.b(new cj(a(3, 3), d(2), b(3, 3)))))
    {
      this.a = false;
      a(☃, ☃, ☃, 3, 2, 3, azt.g);
    }
    a(☃, ☃, 0, 6, 0, 4, 6, 4, aju.by.u(), aju.by.u(), false);
    for (int ☃ = 0; ☃ <= 4; ☃++) {
      for (int ☃ = 0; ☃ <= 4; ☃++) {
        b(☃, aju.by.u(), ☃, -1, ☃, ☃);
      }
    }
    return true;
  }
}
