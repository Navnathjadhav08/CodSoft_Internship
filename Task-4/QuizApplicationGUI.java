import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplicationGUI extends Application {
    private List<Question> questions = new ArrayList<>();
    private int score = 0;
    private int currentQuestionIndex = 0;
    private Timer timer = new Timer();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Sample quiz questions
        questions.add(new Question("What is the capital of France?",
                List.of("London", "Paris", "Berlin", "Madrid"), 1));
        questions.add(new Question("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Venus", "Jupiter"), 1));

        primaryStage.setTitle("Quiz Application");

        Label questionLabel = new Label();
        ToggleGroup optionsToggleGroup = new ToggleGroup();
        RadioButton[] optionButtons = new RadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new RadioButton();
            optionButtons[i].setToggleGroup(optionsToggleGroup);
        }

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            int selectedOption = -1;
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }

            if (selectedOption == -1) {
                showAlert("Error", "Please select an option.");
            } else {
                timer.cancel();
                checkAnswer(selectedOption);
            }
        });

        VBox vbox = new VBox(10, questionLabel, optionButtons[0], optionButtons[1], optionButtons[2], optionButtons[3], submitButton);
        vbox.setMinWidth(300);
        vbox.setMinHeight(200);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-font-size: 14;");

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);

        showNextQuestion(questionLabel, optionButtons);
        primaryStage.show();
    }

    private void showNextQuestion(Label questionLabel, RadioButton[] optionButtons) {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options.get(i));
                optionButtons[i].setSelected(false);
            }

            // Set timer for 10 seconds to answer the question
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    showAlert("Time's Up!", "You ran out of time!");
                    showNextQuestion(questionLabel, optionButtons);
                }
            }, 10000); // 10 seconds in milliseconds
        } else {
            showAlert("Quiz Ended", "Your score: " + score + " out of " + questions.size());
            // You can perform other actions here after the quiz ends.
        }
    }

    private void checkAnswer(int selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (selectedOption == currentQuestion.getCorrectOptionIndex()) {
            score++;
        }
        currentQuestionIndex++;
        showNextQuestion();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
