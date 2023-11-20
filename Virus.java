package inClass;
import java.util.Random;
import java.util.Scanner;

public class EpidemicSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;
        int T;
        double alpha;
        double beta;

        // Prompt user for input and validate it
        do {
            System.out.print("Enter the number of individuals (perfect square): ");
            N = scanner.nextInt();
        } while (!isPerfectSquare(N));

        System.out.print("Enter the number of time steps: ");
        T = scanner.nextInt();

        do {
            System.out.print("Enter the infection rate (0 <= α <= 1): ");
            alpha = scanner.nextDouble();
        } while (alpha < 0 || alpha > 1);

        do {
            System.out.print("Enter the recovery rate (0 <= β <= 1): ");
            beta = scanner.nextDouble();
        } while (beta < 0 || beta > 1);

        char[][] grid = initializeGrid(N);
        simulateEpidemic(grid, T, alpha, beta);

        scanner.close();
    }

    public static boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return n == sqrt * sqrt;
    }

    public static char[][] initializeGrid(int N) {
        char[][] grid = new char[(int) Math.sqrt(N)][(int) Math.sqrt(N)];
        // Initialize the grid with all individuals as 'S' except one as 'I' (patient zero).
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 'S';
            }
        }
        grid[0][0] = 'I'; // Patient zero
        return grid;
    }

    public static void simulateEpidemic(char[][] grid, int T, double alpha, double beta) {
        for (int t = 1; t <= T; t++) {
            int infectedCount = 0;
            int recoveredCount = 0;
            int totalIndividuals = grid.length * grid[0].length;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    char status = grid[i][j];
                    if (status == 'S') {
                        double infectionProbability = calculateInfectionProbability(grid, i, j, alpha);
                        if (Math.random() < infectionProbability) {
                            grid[i][j] = 'I';
                            infectedCount++;
                        }
                    } else if (status == 'I') {
                        if (Math.random() < beta) {
                            grid[i][j] = 'R';
                            recoveredCount++;
                        }
                    }
                }
            }

            double infectionRatio = (double) infectedCount / totalIndividuals;
            System.out.println("Time Step " + t + ":");
            System.out.println("Infected: " + infectedCount);
            System.out.println("Recovered: " + recoveredCount);
            System.out.println("Susceptible: " + (totalIndividuals - infectedCount - recoveredCount));
            System.out.println("Infection Ratio: " + infectionRatio);
            System.out.println();
        }
    }

    public static double calculateInfectionProbability(char[][] grid, int x, int y, double alpha) {
        int infectedNeighbors = 0;
        int totalNeighbors = 0;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                totalNeighbors++;
                if (grid[newX][newY] == 'I') {
                    infectedNeighbors++;
                }
            }
        }

        return alpha * (double) infectedNeighbors / totalNeighbors;
    }
}
