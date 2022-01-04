import Entities.Platform;
import Entities.Rocket;

import java.util.List;
import java.util.Random;

public class Engine {
    private final Platform platform;
    private final Rocket rocket;
    private final Fuzzy fuzzy;
    private boolean endOfGame;
    private boolean win;

    public Engine() {
        Random random = new Random();
        rocket = new Rocket(OptionsParser.screenWidth / 2, 10, OptionsParser.screenWidth, OptionsParser.screenHeight - OptionsParser.platformOriginY - OptionsParser.rocketSize);
        platform = new Platform(
                random.nextDouble(OptionsParser.platformOriginX, OptionsParser.screenWidth - OptionsParser.platformOriginX),
                OptionsParser.screenHeight - OptionsParser.platformOriginY,
                OptionsParser.platformHeight,
                OptionsParser.platformWeight);
        fuzzy = new Fuzzy();
    }

    public void run() {
        if (rocket.getY() == (OptionsParser.screenHeight - 90)) {
            endOfGame = true;
            if (rocket.getX() > (platform.getX() + OptionsParser.rocketSize / 2) && rocket.getX() < (platform.getX() + OptionsParser.platformWeight - OptionsParser.rocketSize / 2)) {
                win = true;
            }
        }
        List<Double> variables = fuzzy.evaluate(getSensors());
        rocket.setAx(variables.get(0));
        rocket.setAy(variables.get(1));
        rocket.move();
    }

    public List<Double> getSensors() {
        double distanceX = platform.getX() - rocket.getX();
        double distanceY = platform.getY() - rocket.getY() - OptionsParser.rocketSize;
        double vy = rocket.getVy();
        double vx = rocket.getVx();
        double ax = rocket.getAx();
        double ay = rocket.getAy();
        List<Double> sensors = List.of(distanceX, distanceY, vx, vy, ax, ay);
        return sensors;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public boolean isWin() {
        return win;
    }

    public Fuzzy getFuzzy() {
        return fuzzy;
    }
}
