public class Tank implements Movable
{
    private int speed = 8;

    private int x;
    private int y;
    private Direction direction;
    private ActionField actionField;
    private BattleField battleField;

    public Tank(ActionField actionField, BattleField battleField)
    {
        this.actionField = actionField;
        this.battleField = battleField;
        direction = Direction.UP;
        x = 128;
        y = 512;
    }

    public void turn(Direction direction)
    {
        this.direction = direction;
        actionField.processTurn(this);
    }

    public void move()
    {
        actionField.processMove(this);
    }

    public void fire()
    {
        Bullet bullet = new Bullet((x + 25), (y + 25), direction);
        actionField.processFire(bullet);
    }

    public void moveRandom()
    {

    }

    public void moveToQuadrant()
    {

    }

    public void printCurrentPosition()
    {
        System.out.println("[move " + direction + "] tankX: " + x + ", tankY: " + y);
    }

    public void updateX(int num)
    {
        x += num;
    }

    public void updateY(int num)
    {
        y += num;
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