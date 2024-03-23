import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}

public class QuizApplication {
    private static final int QUIZ_DURATION = 10; // Quiz duration in seconds
    private static final int QUESTION_COUNT = 5; // Number of questions in the quiz
    private static final Question[] questions = {
            new Question("What is the capital of France?",
                    new String[] { "A. Paris", "B. London", "C. Rome", "D. Madrid" }, 0),
            new Question("What is the largest planet in our solar system?",
                    new String[] { "A. Earth", "B. Jupiter", "C. Mars", "D. Venus" }, 1),
            new Question("Who wrote 'Romeo and Juliet'?",
                    new String[] { "A. William Shakespeare", "B. Jane Austen", "C. Charles Dickens", "D. Mark Twain" },
                    0),
            new Question("What is the chemical symbol for water?",
                    new String[] { "A. H2O", "B. CO2", "C. O2", "D. NaCl" }, 0),
            new Question("What is the capital of Japan?",
                    new String[] { "A. Beijing", "B. Tokyo", "C. Seoul", "D. Bangkok" }, 1)
    };

    private int score;
    private int questionIndex;
    private Timer timer;
    private Scanner scanner;

    public QuizApplication() {
        score = 0;
        questionIndex = 0;
        timer = new Timer();
        scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You will have " + QUIZ_DURATION + " seconds to answer each question.");
        System.out.println("Let's begin!");

        askQuestion();
    }

    private void askQuestion() {
        if (questionIndex < QUESTION_COUNT) {
            Question currentQuestion = questions[questionIndex];

            System.out.println("\nQuestion " + (questionIndex + 1) + ": " + currentQuestion.getQuestion());
            String[] options = currentQuestion.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            System.out.print("Enter your choice (A/B/C/D): ");

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    nextQuestion();
                }
            }, QUIZ_DURATION * 1000);

            String selectedOption = scanner.next().toUpperCase();
            int optionIndex = selectedOption.charAt(0) - 'A';
            if (optionIndex >= 0 && optionIndex < options.length) {
                if (currentQuestion.isCorrect(optionIndex)) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect!");
                }
            } else {
                System.out.println("Invalid option!");
            }

            timer.cancel();
            questionIndex++;
            askQuestion();
        } else {
            showResult();
        }
    }

    private void nextQuestion() {
        questionIndex++;
        askQuestion();
    }

    private void showResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + "/" + QUESTION_COUNT);
        scanner.close();
    }

    public static void main(String[] args) {
        QuizApplication quizApp = new QuizApplication();
        quizApp.startQuiz();
    }
}
