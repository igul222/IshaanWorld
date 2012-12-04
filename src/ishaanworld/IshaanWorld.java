package ishaanworld;

public class IshaanWorld {
    private static int boardSize = 20;
    private static int squareSize = 20;
    private static int animationDelay = 200;
    private static int cellPadding = 20;

    public static int getBoardSize() {
        return boardSize;
    }

    public static void setBoardSize(int aBoardSize) {
        if(Grid.exists())
            IshaanWorld.error("Can't change board size: " +
                    "grid has already been initialized.");
        else
            boardSize = aBoardSize;
    }

    public static int getSquareSize() {
        return squareSize;
    }

    public static void setSquareSize(int aSquareSize) {
        if(Grid.exists())
            IshaanWorld.error("Can't change square size: " +
                    "grid has already been initialized.");
        else
            squareSize = aSquareSize;
    }

    public static int getAnimationDelay() {
        return animationDelay;
    }

    public static void setAnimationDelay(int aAnimationDelay) {
        if(aAnimationDelay<=0)
            IshaanWorld.error("Animation delay must " +
                    "be positive!");
        else {
            animationDelay = aAnimationDelay;
            Grid.get().initTimer();
        }
    }

    public static int getCellPadding() {
        return cellPadding;
    }

    public static void setCellPadding(int aCellPadding) {
        cellPadding = aCellPadding;
    }

    // package visibility
    static void error(String message) {
        System.out.println("ERROR: "+message);
        System.out.println("Exiting...");
        System.exit(1); // indicates an abnormal exit
    }
    
}