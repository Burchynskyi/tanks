public enum Direction
{
    UP(1), RIGHT(2), DOWN(3), LEFT(4);

    private int id;

    Direction(int id)
    {
        this.id = id;
    }

    public static Direction getById(int id)
    {
        for(Direction d : Direction.values())
        {
            if (d.id == id)
            {
                return d;
            }
        }

        return null;
    }
}
