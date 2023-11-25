package inClass;

import java.util.Random;
import java.util.Scanner;

public class EpidemicSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;//number of people
        int T;// number of time step
        double alpha;
        double beta;

        // Prompt user for input and validate it
        do {
            System.out.print("Enter the number of individuals (perfect square): ");
            N = scanner.nextInt();
        } while (!isPerfectSquare(N));

        System.out.print("Enter the number of time steps(value greater than 0): ");
        T = scanner.nextInt();

        do {
            System.out.print("Enter the infection rate (0 <= α <= 1): ");
            alpha = scanner.nextDouble();
        } while (alpha < 0 || alpha > 1);

        do {
            System.out.print("Enter the recovery rate (0 <= β <= 1): ");
            beta = scanner.nextDouble();
        } while (beta < 0 || beta > 1);

        // Create an instance of EpidemicSimulator
        EpidemicSimulator simulator = new EpidemicSimulator(N, T, alpha, beta);

        // Run the simulation
        simulator.runSimulation();

        scanner.close();
    }

    // Validation Method
    public static boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return n == sqrt * sqrt;
    }

    // Simulation Result Class
    static class SimulationResult {
        char[][] grid;
        int infectedCount;
        int recoveredCount;
        double infectionRatio;

        public SimulationResult(char[][] grid, int infectedCount, int recoveredCount, double infectionRatio) {
            this.grid = grid;
            this.infectedCount = infectedCount;
            this.recoveredCount = recoveredCount;
            this.infectionRatio = infectionRatio;
        }
    }

    // Epidemic Simulator Class
    static class EpidemicSimulator {
        private char[][] grid;
        private int N;
        private int T;
        private double alpha;
        private double beta;

        public EpidemicSimulator(int N, int T, double alpha, double beta) {
            this.N = N;
            this.T = T;
            this.alpha = alpha;
            this.beta = beta;
            this.grid = initializeGrid(N);
        }

        public void runSimulation() {
            for (int t = 1; t <= T; t++) {
                SimulationResult result = simulateEpidemic();
                printSimulationResult(t, result);
            }
        }

        private SimulationResult simulateEpidemic() {
            int infectedCount = 0;
            int recoveredCount = 0;
            int totalIndividuals = grid.length * grid[0].length;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    char status = grid[i][j];
                    if (status == 'S') {
                        double infectionProbability = calculateInfectionProbability(i, j);
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
            return new SimulationResult(cloneGrid(grid), infectedCount, recoveredCount, infectionRatio);
        }

        private void printSimulationResult(int timeStep, SimulationResult result) {
            System.out.println("Time Step " + timeStep + ":");
            System.out.println("Infected: " + result.infectedCount);
            System.out.println("Recovered: " + result.recoveredCount);
            System.out.println("Susceptible: " + (N - result.infectedCount - result.recoveredCount));
            System.out.println("Infection Ratio: " + result.infectionRatio);

            // Print the 2D array
            printGrid(result.grid);

            System.out.println();
        }

        private double calculateInfectionProbability(int x, int y) {
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


        private char[][] initializeGrid(int N) {
            char[][] grid = new char[(int) Math.sqrt(N)][(int) Math.sqrt(N)];
            Random random = new Random();
            
            // Initialize the grid with all individuals as 'S'
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = 'S';
                }
            }
            
            // Place the infected individual at a random location
            int randomX = random.nextInt(grid.length);
            int randomY = random.nextInt(grid[0].length);
            grid[randomX][randomY] = 'I';

            return grid;
        }

        private char[][] cloneGrid(char[][] original) {
            char[][] clone = new char[original.length][original[0].length];
            for (int i = 0; i < original.length; i++) {
                for (int j = 0; j < original[i].length; j++) {
                    clone[i][j] = original[i][j];
                }
            }
            return clone;
        }

        private void printGrid(char[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
