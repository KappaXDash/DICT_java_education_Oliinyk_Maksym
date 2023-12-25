package CoffeeMachine;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class CoffeeMachine {

    LinkedHashMap<String, Integer> resources = new LinkedHashMap<>();
    LinkedHashMap<String, Object> espresso = new LinkedHashMap<>();
    LinkedHashMap<String, Object> latte = new LinkedHashMap<>();
    LinkedHashMap<String, Object> cappuccino = new LinkedHashMap<>();

    private CoffeeMachine() {
        resources.put("water", 400);
        resources.put("milk", 540);
        resources.put("coffeeBeans", 120);
        resources.put("disposableCups", 9);
        resources.put("money", 550);

        espresso.put("name", "espresso");
        espresso.put("water", 250);
        espresso.put("milk", 0);
        espresso.put("coffeeBeans", 16);
        espresso.put("cost", 4);

        latte.put("name", "latte");
        latte.put("water", 350);
        latte.put("milk", 75);
        latte.put("coffeeBeans", 20);
        latte.put("cost", 7);

        cappuccino.put("name", "cappuccino");
        cappuccino.put("water", 200);
        cappuccino.put("milk", 100);
        cappuccino.put("coffeeBeans", 12);
        cappuccino.put("cost", 6);
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        serveCoffee(coffeeMachine);
    }

    public static void showStatus(CoffeeMachine coffeeMachine) {
        System.out.println("Current Inventory:");
        System.out.println("Water - " + coffeeMachine.resources.get("water"));
        System.out.println("Milk - " + coffeeMachine.resources.get("milk"));
        System.out.println("Coffee Beans - " + coffeeMachine.resources.get("coffeeBeans"));
        System.out.println("Disposable Cups - " + coffeeMachine.resources.get("disposableCups"));
        System.out.println("Money - " + coffeeMachine.resources.get("money"));
    }

    public static void refill(CoffeeMachine coffeeMachine) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter resource to add (water, milk, coffeeBeans, disposableCups) or 'back' to return to the main menu:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("back")) {
                    System.out.println("Returning to the main menu.");
                    break;
                } else if (coffeeMachine.resources.containsKey(input)) {
                    while (true) {
                        System.out.println("Enter the amount of " + input + " to add:");
                        if (scanner.hasNextInt()) {
                            int currentAmount = coffeeMachine.resources.get(input);
                            int amountToAdd = scanner.nextInt();
                            int newAmount = currentAmount + amountToAdd;
                            coffeeMachine.resources.put(input, newAmount);
                            System.out.println(coffeeMachine.resources);
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Error! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid resource or 'back'.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void withdrawMoney(CoffeeMachine coffeeMachine) {
        System.out.println("Withdrawing money - " + coffeeMachine.resources.get("money") + "$");
        coffeeMachine.resources.put("money", 0);
    }

    public static void makeCoffee(CoffeeMachine coffeeMachine, String coffeeType) {
        try (Scanner scanner = new Scanner(System.in)) {
            LinkedHashMap<String, Object> recipe = null;
            int water, milk, coffeeBeans, cost;

            switch (coffeeType) {
                case "1":
                    recipe = coffeeMachine.espresso;
                    break;
                case "2":
                    recipe = coffeeMachine.latte;
                    break;
                case "3":
                    recipe = coffeeMachine.cappuccino;
                    break;
                default:
                    System.out.println("Invalid coffee choice");
                    return;
            }

            water = (int) recipe.get("water");
            milk = (int) recipe.get("milk");
            coffeeBeans = (int) recipe.get("coffeeBeans");
            cost = (int) recipe.get("cost");

            while (true) {
                System.out.println("How many cups of coffee do you want?");
                if (scanner.hasNextInt()) {
                    int userCups = scanner.nextInt();
                    if (0 < userCups && userCups < 100) {
                        System.out.println("For " + userCups + " cup/s of coffee you will need:\n" + userCups * water + "ml of water");
                        System.out.println(userCups * milk + "ml of milk");
                        System.out.println(userCups * coffeeBeans + " coffee beans");
                        System.out.println(userCups * cost + "$");

                        if (coffeeMachine.resources.get("water") < userCups * water) {
                            System.out.println("Sorry, not enough water");
                        } else if (coffeeMachine.resources.get("milk") < userCups * milk) {
                            System.out.println("Sorry, not enough milk");
                        } else if (coffeeMachine.resources.get("coffeeBeans") < userCups * coffeeBeans) {
                            System.out.println("Sorry, not enough coffee beans");
                        } else if (coffeeMachine.resources.get("disposableCups") < userCups) {
                            System.out.println("Sorry, not enough disposable cups");
                        } else {
                            System.out.println("Making " + coffeeType);
                            coffeeMachine.resources.put("water", coffeeMachine.resources.get("water") - userCups * water);
                            coffeeMachine.resources.put("milk", coffeeMachine.resources.get("milk") - userCups * milk);
                            coffeeMachine.resources.put("coffeeBeans", coffeeMachine.resources.get("coffeeBeans") - userCups * coffeeBeans);
                            coffeeMachine.resources.put("money", coffeeMachine.resources.get("money") + userCups * cost);
                            coffeeMachine.resources.put("disposableCups", coffeeMachine.resources.get("disposableCups") - userCups);
                            serveCoffee(coffeeMachine);
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and 99.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void orderCoffee(CoffeeMachine coffeeMachine) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back to main menu");
            while (true) {
                String order = scanner.nextLine().toLowerCase();
                switch (order) {
                    case "1":
                    case "2":
                    case "3":
                        makeCoffee(coffeeMachine, order);
                        break;
                    case "4":
                        serveCoffee(coffeeMachine);
                        break;
                    default:
                        System.out.println("Error! Incorrect input.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void serveCoffee(CoffeeMachine coffeeMachine) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                String userChoiceString = scanner.nextLine().toUpperCase();
                switch (userChoiceString) {
                    case "BUY":
                        orderCoffee(coffeeMachine);
                        break;
                    case "FILL":
                        refill(coffeeMachine);
                        break;
                    case "TAKE":
                        withdrawMoney(coffeeMachine);
                        break;
                    case "REMAINING":
                        showStatus(coffeeMachine);
                        break;
                    case "EXIT":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Error! Incorrect input.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}