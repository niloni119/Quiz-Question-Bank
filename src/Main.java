import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static HashMap<String, Integer> operations = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Quiz quiz = new Quiz();

        while (true) {
            System.out.println("\n----- Quiz Menu -----");
            System.out.println("1. Add question.");
            System.out.println("2. Add some questions.");
            System.out.println("3. Delete question.");
            System.out.println("4. Change question.");
            System.out.println("5. Show the quiz.");
            System.out.println("6. Save to CSV.");
            System.out.println("7. Load quiz from CSV.");
            System.out.println("8. Take the quiz.");
            System.out.println("9. Show Report.");
            System.out.println("10. Exit.");
            System.out.print("Print number of your choice: ");

            int number = input.nextInt();
            input.nextLine();

            switch (number) {
                case 1 -> {
                    quiz.addque(createQuestion(input));
                    Operation("Add one question");
                }
                case 2 -> {
                    int num;
                    while (true) {
                        System.out.print("How many questions do you want to add? ");
                        num = input.nextInt();
                        input.nextLine();
                        if (num > 0) {
                            break;
                        } else {
                            System.out.println("Please, enter a number greater than 0.");
                        }
                    }

                    for (int i = 0; i < num; i++) {
                        quiz.addque(createQuestion(input));
                    }
                    Operation("Add multiple questions");
                }
                case 3 -> {
                    System.out.print("Enter the number of the question to delete: ");
                    int index = input.nextInt() - 1;
                    input.nextLine();
                    quiz.deletque(index);
                    Operation("Delete question");
                }
                case 4 -> {
                    System.out.print("Enter the number of the question to change: ");
                    int index = input.nextInt() - 1;
                    input.nextLine();
                    quiz.changeque(index, input);
                    Operation("Change question");
                }
                case 5 -> {
                    quiz.printQuizWithoutAns();
                    Operation("Show quiz");
                }
                case 6 -> {
                    saveToCSV("quiz.csv", quiz);
                    System.out.println("Data written successfully.");
                    Operation("Save to CSV");
                }
                case 7 -> {
                    ArrayList<Question> read = readData("quiz.csv");
                    int count = 1;
                    for (Question r : read) {
                        System.out.print(count + ". ");
                        r.printWithoutAns();
                        count++;
                    }
                    System.out.println("Quiz loaded from file.");
                    quiz.quiz = read;
                    Operation("Load from CSV");
                }
                case 8 -> {
                    int score = 0;
                    for (int i = 0; i < quiz.quiz.size(); i++) {
                        Question q = quiz.quiz.get(i);
                        System.out.println((i + 1) + ". " + q.que);
                        for (int j = 0; j < q.options.size(); j++) {
                            System.out.println((j + 1) + ") " + q.options.get(j));
                        }

                        int ans;
                        while (true) {
                            System.out.print("Your answer (1-4): ");
                            ans = input.nextInt() - 1;
                            input.nextLine();
                            if (ans >= 0 && ans < 4) break;
                            System.out.println("Invalid input. Try again.");
                        }

                        if (ans == q.ans) {
                            score++;
                            System.out.println("Correct!\n");
                        } else {
                            System.out.println("Wrong! Correct answer: " + (q.ans + 1) + ") " + q.options.get(q.ans) + "\n");
                        }
                    }
                    System.out.println("Quiz finished! Your score: " + score + "/" + quiz.quiz.size());
                    Operation("Take quiz");
                }
                case 9 -> showReport();
                case 10 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please enter a number between 1 and 10.");
            }
        }
    }

    static void Operation(String operation) {
        operations.put(operation, operations.getOrDefault(operation, 0) + 1);
    }

    static void showReport() {
        System.out.println("\n----- Report Summary -----");
        if (operations.isEmpty()) {
            System.out.println("No operations have been performed yet.");
        } else {
            for (Map.Entry<String, Integer> entry : operations.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " time(s).");
            }
        }
    }

    public static void saveToCSV(String filename, Quiz quizis) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Question q : quizis.quiz) {
                writer.write(q.que + ",");
                for (String opt : q.options) {
                    writer.write(opt + ",");
                }
                writer.write((q.ans + 1) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static ArrayList<Question> readData(String filename) {
        ArrayList<Question> quizis = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                if (data.length < 6) continue;

                String que = data[0];
                ArrayList<String> options = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    options.add(data[i]);
                }

                int ans = Integer.parseInt(data[5]) - 1;

                Question q = new Question(que, options, ans);
                quizis.add(q);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return quizis;
    }

    public static Question createQuestion(Scanner input) {
        String que;
        while (true) {
            System.out.println("Enter your question:");
            que = input.nextLine().trim();
            if (!que.isEmpty()) {
                break;
            } else {
                System.out.println("You should print the question!");
            }
        }

        ArrayList<String> options = new ArrayList<>();
        System.out.println("Enter 4 options:");
        for (int j = 0; j < 4; j++) {
            String option;
            while (true) {
                System.out.print((j + 1) + ") ");
                option = input.nextLine().trim();
                if (!option.isEmpty()) {
                    options.add(option);
                    break;
                } else {
                    System.out.println("Option cannot be empty. Please enter again.");
                }
            }
        }

        int correctIndex;
        while (true) {
            System.out.print("Enter the correct answer number (1–4): ");
            correctIndex = input.nextInt() - 1;
            input.nextLine();
            if (correctIndex >= 0 && correctIndex < 4) break;
            System.out.println("Invalid number. Try again.");
        }

        return new Question(que, options, correctIndex);
    }
}

class Question {
    String que;
    ArrayList<String> options;
    int ans;

    Question(String que, ArrayList<String> options, int ans) {
        this.que = que;
        this.options = options;
        this.ans = ans;
    }

    void printWithoutAns() {
        System.out.println(que);
        for (int i = 0; i < options.size(); i++) {
            System.out.print((i + 1) + ") " + options.get(i) + "  ");
        }
        System.out.println();
        System.out.println();
    }
}

class Quiz {
    ArrayList<Question> quiz = new ArrayList<>();

    void addque(Question que) {
        quiz.add(que);
        System.out.println("Your question was added!");
    }

    void deletque(int index) {
        if (index >= 0 && index < quiz.size()) {
            quiz.remove(index);
            System.out.println("Question deleted successfully!");
        } else {
            System.out.println("Invalid question number!");
        }
    }

    void changeque(int index, Scanner input) {
        if (index >= 0 && index < quiz.size()) {
            System.out.println("Editing question " + (index + 1));
            quiz.set(index, Main.createQuestion(input));
            System.out.println("Question updated successfully!");
        } else {
            System.out.println("Invalid question number!");
        }
    }

    void printQuizWithoutAns() {
        for (int i = 0; i < quiz.size(); i++) {
            System.out.print((i + 1) + ". ");
            quiz.get(i).printWithoutAns();
        }
    }
}
