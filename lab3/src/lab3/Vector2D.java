package lab3;
public class Vector2D{
    public double x,y; //wspolrzedne x i y
    public Vector2D()
    {
        this.x = 0;
        this.y = 0;
    }
    public Vector2D(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    public Vector2D sumVect(Vector2D vect) //dodawanie wektorow
    {
        return new Vector2D(this.x + vect.x, this.y + vect.y);
    }
    public Vector2D subVect(Vector2D vect) //odejmowanie wektorow
    {
        return new Vector2D(this.x - vect.x , this.y - vect.y);
    }
    public Vector2D constMultVect(int k) //mnozenie wektora przez k
    {
        return new Vector2D(this.x *k,this.y * k);
    }
    public double length() //dlugosc wektora
    {
        return Math.sqrt((this.x*this.x ) + (this.y *this.y));
    }
    public Vector2D normalize() //wersor
    {
        try
        {
            return new Vector2D(this.x/this.length(),this.y/this.length());
        }
        catch (ArithmeticException e)
        {
            System.out.println("Unable to normalize, length equals 0");
            return null;
        }
    }
}