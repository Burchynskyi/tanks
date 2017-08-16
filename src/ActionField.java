import javax.swing.*;
import java.awt.*;

public class ActionField extends JPanel
{
    final boolean COLORED_MODE = false;
    final boolean IS_GRID = true;

    private BattleField battleField;
    private Tank tank;
    private Bullet bullet;

    public ActionField() throws Exception
    {
        battleField = new BattleField();
        tank = new Tank(this, this.battleField);
        bullet = new Bullet(-100, -100, Direction.UP);

        JFrame frame = new JFrame("TANK");
        frame.setLocation(650, 50);
        frame.setMinimumSize(new Dimension(battleField.getBfWidth() + 50, battleField.getBfHeight() + 50));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    void runTheGame() throws Exception
    {
        battleField.printBattleField();

//        tank.move();
//        tank.move();
//        tank.move();
//        tank.move();
//
//        tank.turn(Direction.LEFT);
//
//        tank.move();
//        tank.move();
//        tank.move();
//        tank.move();
        tank.move();
        tank.move();
        tank.turn(Direction.RIGHT);
        tank.fire();
        tank.fire();
    }

    public void processMove(Tank tank)
    {
        this.tank = tank;
        Direction direction = tank.getDirection();

        int maxX = battleField.getBfWidth() - 64;
        int maxY = battleField.getBfHeight() - 64;

        int step = 1;
        int covered = 0;

        if ((direction == Direction.UP && tank.getY() == 0)
                || (direction == Direction.DOWN && tank.getY() >= maxY)
                || (direction == Direction.LEFT && tank.getX() == 0)
                || (direction == Direction.RIGHT && tank.getX() >= maxX))
        {
            System.out.println("[illegal move] direction " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            return;
        }

        while (covered < 64)
        {
            updateCoordinates(tank, step);

            tank.printCurrentPosition();
            covered += step;
            repaint();
            sleep(tank.getSpeed());
        }
    }

    public void processTurn(Tank tank)
    {
        repaint();
    }

    public void processFire(Bullet bullet)
    {
        this.bullet = bullet;
        int step = 1;

        while ((bullet.getX() > -14 && bullet.getX() < battleField.getBfWidth() + 14)
                && (bullet.getY() > -14 && bullet.getY() < battleField.getBfHeight() + 14))
        {
            updateCoordinates(bullet, step);

            if (checkAndProcessInterception())
            {
                battleField.updateQuadrant(bullet.getY() / 64, bullet.getX() / 64, " ");
                bullet.destroy();
            }
            repaint();
            sleep(bullet.getSpeed());
        }
    }

    private void updateCoordinates(Movable movable, int step)
    {
        Direction direction = movable.getDirection();

        switch (direction)
        {
            case UP:
                movable.updateY(-step);
                break;
            case DOWN:
                movable.updateY(-step);
                break;
            case LEFT:
                movable.updateX(-step);
                break;
            case RIGHT:
                movable.updateX(step);
                break;
        }
    }

    private boolean checkAndProcessInterception()
    {
        int[] bulletQuad = getQuadrant(bullet.getX(), bullet.getY());

        if (battleField.scanQuadrant(bulletQuad[1], bulletQuad[0]).equals("B"))
        {
            return true;
        }

        return false;
    }

    private void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (Exception ignore)
        {
        }
    }

    // 1 - top, 2 - right, 3 - down, 4 - left
    @Override
    protected void paintComponent(Graphics g)
    {
        paintBF(g);

        paintBorders(g);

        g.setColor(new Color(255, 0, 0));
        g.fillRect(tank.getX(), tank.getY(), 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (tank.getDirection() == Direction.UP)
        {
            g.fillRect(tank.getX() + 20, tank.getY(), 24, 34);
        } else if (tank.getDirection() == Direction.DOWN)
        {
            g.fillRect(tank.getX() + 20, tank.getY() + 30, 24, 34);
        } else if (tank.getDirection() == Direction.LEFT)
        {
            g.fillRect(tank.getX(), tank.getY() + 20, 34, 24);
        } else
        {
            g.fillRect(tank.getX() + 30, tank.getY() + 20, 34, 24);
        }

        g.setColor(new Color(255, 255, 0));
        g.fillRect(bullet.getX(), bullet.getY(), 14, 14);
    }

    private void paintBorders(Graphics g)
    {
        for (int j = 0; j < battleField.getDimentionY(); j++)
        {
            for (int k = 0; k < battleField.getDimentionX(); k++)
            {
                if (battleField.scanQuadrant(j, k).equals("B"))
                {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x, y, 64, 64);

                    if (IS_GRID)
                    {
                        g.setColor(new Color(0, 0, 0));
                        g.drawRect(x, y, 64, 64);
                    }
                }
            }
        }
    }

    private void paintBF(Graphics g)
    {
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++)
        {
            for (int h = 0; h < 9; h++)
            {
                if (COLORED_MODE)
                {
                    if (i % 2 == 0)
                    {
                        cc = new Color(252, 241, 177);
                    } else
                    {
                        cc = new Color(233, 243, 255);
                    }
                } else
                {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }
    }

    private String getQuadrantXY(int v, int h)
    {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    private int[] getQuadrant(int x, int y)
    {
        return new int[]{x / 64, y / 64};
    }
}