import java.util.Arrays;

public class BattleField
{
    private int bfWidth = 576;
    private int bfHeight = 576;

    private String[][] battleField = {
            {"B", "B", "B", "B", "B", "B", "B", "B", "B"},
            {" ", " ", " ", " ", " ", " ", " ", " ", "B"},
            {"B", "B", "B", " ", "B", " ", "B", "B", "B"},
            {"B", "B", "B", " ", " ", " ", "B", "B", "B"},
            {"B", "B", "B", " ", "B", " ", "B", "B", "B"},
            {"B", "B", " ", "B", "B", "B", " ", "B", "B"},
            {"B", "B", " ", " ", " ", " ", " ", "B", "B"},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"}
    };

    public String scanQuadrant(int v, int h)
    {
        return battleField[v][h];
    }

    public void updateQuadrant(int v, int h, String str)
    {
        battleField[v][h] = str;
    }

    public int getDimentionX()
    {
        return battleField[0].length;
    }

    public int getDimentionY()
    {
        return battleField.length;
    }

    public void printBattleField()
    {
        for (String[] row : battleField)
        {
            System.out.println(Arrays.toString(row));
        }
    }

    public int getBfWidth()
    {
        return bfWidth;
    }

    public int getBfHeight()
    {
        return bfHeight;
    }
}