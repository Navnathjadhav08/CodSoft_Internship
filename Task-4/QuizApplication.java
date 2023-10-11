import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        // Sample quiz questions
        questions.add(new Question("What is the capital of France?",
                List.of("London", "Paris", "Berlin", "Madrid"), 1));
        questions.add(new Question("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Venus", "Jupiter"), 1));
        // Add more questions here...

        startQuiz();
    }

    private static void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question: " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            // Set timer for 10 seconds to answer the question
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    showNextQuestion();
                }
            }, 10000); // 10 seconds in milliseconds

            // User input for answering the question
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();
            checkAnswer(userChoice - 1, currentQuestion);
        } else {
            endQuiz();
        }
    }

    private static void checkAnswer(int userChoice, Question question) {
        if (userChoice == question.getCorrectOptionIndex()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect!");
        }
        showNextQuestion();
    }

    private static void showNextQuestion() {
        currentQuestionIndex++;
        timer.cancel(); // Cancel the previous timer
        timer = new Timer();
        startQuiz();
    }

    private static void endQuiz() {
        System.out.println("Quiz ended!");
        System.out.println("Your score: " + score + " out of " + questions.size());
        // Display other summary information if needed
    }
}


class Question {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

