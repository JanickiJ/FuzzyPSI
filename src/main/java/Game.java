import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sourceforge.jFuzzyLogic.FIS;

import static java.lang.Math.round;

public class Game extends Application {
    private Engine engine;
    private final Timeline timeline;
    private GraphicsContext gc;
    private int games_counter;
    private int wins_counter;
    private Text result;
    private Text rules;
    private Group root;

    public Game() {
        this.engine = new Engine();
        games_counter = 0;
        wins_counter = 0;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            simulation();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        result = new Text("stats: " + wins_counter + "/" + games_counter);
        rules = new Text("");
        engine.getFuzzy().getFuzzyRuleSet().chart();
    }

    public void main() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rocket Game");
        root = new Group();
        Scene scene = new Scene(root, OptionsParser.screenWidth + OptionsParser.rocketSize/2, OptionsParser.screenHeight + OptionsParser.rocketSize/2);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(OptionsParser.screenWidth + OptionsParser.rocketSize/2, OptionsParser.screenHeight + OptionsParser.rocketSize/2);
        root.getChildren().add(new TextFlow(result, rules));
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        timeline.play();
        primaryStage.show();
    }

    public void drawObjects(GraphicsContext gc) {
        gc.setFill(engine.getPlatform().getColor());
        gc.clearRect(0, 0, OptionsParser.screenWidth + OptionsParser.rocketSize, OptionsParser.screenHeight + OptionsParser.rocketSize);
        gc.fillRect(engine.getPlatform().getX(), engine.getPlatform().getY(), engine.getPlatform().getWeight(), engine.getPlatform().getHeight());
        gc.setFill(engine.getRocket().getColor());
        gc.fillRect(engine.getRocket().getX(), engine.getRocket().getY(), engine.getRocket().getWeight(), engine.getRocket().getHeight());
    }

    public void simulation() {
        result.setText("stats: " + wins_counter + "/" + games_counter + "\n");
        drawObjects(gc);
        engine.run();
        rules.setText("x: " + round(engine.getRocket().getX()) + "\n" +
                "y: " + round(engine.getRocket().getY()) + "\n" +
                "ax:" + (double) round(engine.getRocket().getAx() * 10000.0) / 10000 + "\n" +
                "ay:" + (double) round(engine.getRocket().getAy() * 10000.0) / 10000 + "\n" +
                "vx:" + (double) round(engine.getRocket().getVx() * 100.0) / 100 + "\n" +
                "vy:" + (double) round(engine.getRocket().getVy() * 100.0) / 100 + "\n");
        if (engine.isEndOfGame()) {
            if (engine.isWin()) {
                wins_counter++;
            }
            engine = new Engine();
            games_counter++;
        }
    }
}