public class Bullet implements Movable
{
    private final int speed = 8;

    private int x;
    private int y;
    private Direction direction;

    public Bullet(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void updateX(int num)
    {
        x += num;
    }

    public void updateY(int num)
    {
        y += num;
    }

    public void destroy()
    {
        x = -100;
        y = -100;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Direction getDirection()
    {
        return direction;
    }
}